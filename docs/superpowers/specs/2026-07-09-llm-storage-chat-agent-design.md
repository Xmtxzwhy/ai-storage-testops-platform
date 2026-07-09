# LLM Storage Chat Agent Design

## Goal

Upgrade the current rule-based storage parser into a conversational AI agent that can call an LLM API, choose tools, execute supported platform actions, explain results, and clearly reject unsupported requests.

## Current State

The current `agent-service` exposes:

- `POST /api/agents/storage/parse`
- A deterministic `StorageAgentParser` based on regular expressions and keywords.

The Vue page can parse a sentence and let the user click workflow buttons. This is useful as a natural-language entry point, but it is not yet a true LLM agent because it does not call a model, maintain conversation state, or decide tool usage.

## Target User Experience

The user talks to the system through a chat panel:

```text
用户：帮我查一下 CDM 顺序读最高的是哪个样品哪个版本？
Agent：我会查询 CDM / SEQ R 1M Q8T1 的最高平均值。
工具调用：query_metrics
Agent：最高的是 Project-A FW-v2，平均值 340.46 MB/s。
```

If information is missing:

```text
用户：帮我跑 CDM
Agent：我还需要确认样品、版本、节点和场景。你要跑哪个项目版本？clean 还是 dirty？
```

If the action is high risk:

```text
用户：Node-3 跑 Project-A FW-v2 的 CDM clean
Agent：我已整理好任务参数。创建并执行测试任务会占用节点，需要你确认后继续。
```

If outside capability:

```text
用户：帮我订一张机票
Agent：抱歉，这个能力暂时不支持。我目前可以处理测试任务、节点/样品/用例查询、性能指标分析、报告生成、当前时间、天气和简单联网搜索。
```

## Architecture

Add a new chat endpoint:

```text
POST /api/agents/storage/chat
```

The endpoint uses a layered agent architecture:

1. **Conversation API** receives `sessionId`, `message`, and optional `confirmActionId`.
2. **LLM Client** calls OpenAI when `OPENAI_API_KEY` is available.
3. **Tool Registry** exposes explicit safe tools to the model.
4. **Tool Executor** executes only registered tools.
5. **Response Composer** returns user-facing reply, tool call trace, pending confirmations, and structured data.
6. **Fallback Parser** handles demo mode when no LLM API key is configured.

Spring Boot remains the business API gateway. The Python Agent service may call Spring Boot endpoints for platform actions through `SPRING_API_BASE_URL`, or initially return planned tool calls for the Spring side to execute where that is safer.

## Tool Set

First iteration tools:

- `get_capabilities`: explain what the agent can do.
- `get_current_time`: return server current time.
- `query_metric`: answer storage performance ranking questions using existing metric fields.
- `parse_storage_request`: reuse existing parser for backward compatibility.
- `plan_test_task`: produce task parameters and confirmation request.
- `plan_report`: produce report parameters and confirmation request.

Second iteration tools:

- `create_test_task`
- `run_test_task`
- `create_report`
- `generate_report`
- `web_search`
- `get_weather`

High-risk tools such as task creation, task execution, and report generation require confirmation before execution.

## LLM Behavior

The LLM is instructed to:

- Stay inside the registered capability boundary.
- Ask concise follow-up questions when required fields are missing.
- Use tools instead of guessing platform data.
- Never claim a task was created, run, or reported unless the corresponding tool succeeded.
- Return unsupported requests with a polite limitation message.
- For weather/search, use a registered external tool only; if unavailable, say the tool is not enabled yet.

## Configuration

Environment variables:

```text
OPENAI_API_KEY=<set in local environment when LLM mode is enabled>
OPENAI_MODEL=gpt-4.1-mini
SPRING_API_BASE_URL=http://localhost:8080/springbootarkc6v1u
AGENT_LLM_ENABLED=true
```

If `OPENAI_API_KEY` is missing or `AGENT_LLM_ENABLED=false`, `/storage/chat` returns a fallback response powered by the existing parser and capability rules. This keeps the interview demo usable without network/API access.

## API Schema

Request:

```json
{
  "sessionId": "demo-001",
  "message": "帮我查 CDM 顺序读最高的是哪个样品哪个版本",
  "confirmActionId": null
}
```

Response:

```json
{
  "sessionId": "demo-001",
  "reply": "CDM 顺序读最高的是 Project-A FW-v2，平均值 340.46 MB/s。",
  "intent": "QUERY_METRIC",
  "needConfirm": false,
  "toolCalls": [
    {
      "tool": "query_metric",
      "status": "success",
      "summary": "suite=CDM, metric=SEQ R 1M Q8T1, aggregation=MAX"
    }
  ],
  "actions": [],
  "data": {}
}
```

## Frontend Changes

Enhance the existing `AI测管演示台` page:

- Add a chat panel at the top.
- Show user and assistant messages.
- Show tool call timeline under each assistant answer.
- Show pending confirmation actions as buttons.
- Keep the existing workflow panels below as visible platform controls.

This lets an interviewer see both the conversational layer and the underlying platform functions.

## Testing

Backend tests:

- Fallback chat returns capabilities without an API key.
- Fallback chat rejects unsupported requests.
- Fallback chat recognizes metric query and produces a tool call trace.
- Confirmation-required requests return `needConfirm=true`.

Frontend verification:

- `npm run build`
- `mvn -DskipTests compile`
- Manual chat smoke test with no API key.

LLM-enabled manual test:

- Set `OPENAI_API_KEY`.
- Ask a capability question.
- Ask a metric question.
- Ask an unsupported question.
- Ask a task execution question and confirm it requests approval.

## Out of Scope

- Full LangGraph migration.
- Real web search and weather API implementation in the first iteration.
- Persistent long-term memory.
- Autonomous task execution without confirmation.
- Replacing existing fixed workflow controls.
