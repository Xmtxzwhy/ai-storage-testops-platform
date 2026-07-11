import os
from datetime import datetime
from typing import Dict

from app.schemas.storage_chat import (
    StorageAgentAction,
    StorageChatRequest,
    StorageChatResponse,
    StorageToolCall,
)
from app.services.storage_agent_parser import StorageAgentParser


class StorageChatAgent:
    def __init__(self, llm_client=None):
        self.parser = StorageAgentParser()
        self.llm_client = llm_client

    def chat(self, request: StorageChatRequest) -> StorageChatResponse:
        message = request.message.strip()
        if self._is_llm_enabled():
            return self._llm_chat(request)
        return self._fallback_chat(request.sessionId, message)

    def _is_llm_enabled(self) -> bool:
        return bool(os.getenv("OPENAI_API_KEY")) and os.getenv("AGENT_LLM_ENABLED", "true").lower() != "false"

    def _llm_chat(self, request: StorageChatRequest) -> StorageChatResponse:
        fallback = self._fallback_chat(request.sessionId, request.message.strip())
        prompt = self._build_llm_prompt(request.message, fallback)
        try:
            client = self.llm_client or self._create_openai_client()
            reply = self._generate_llm_reply(client, prompt) or fallback.reply
            fallback.reply = reply
            fallback.data["llmMode"] = "openai"
            fallback.toolCalls.insert(0, StorageToolCall(
                tool="llm_router",
                status="success",
                summary="OpenAI Responses API generated the final reply.",
            ))
            return fallback
        except Exception as exc:
            fallback.data["llmMode"] = "fallback"
            fallback.toolCalls.insert(0, StorageToolCall(
                tool="llm_router",
                status="failed",
                summary=f"LLM call failed, fallback used: {exc.__class__.__name__}",
            ))
            return fallback

    def _generate_llm_reply(self, client, prompt: str) -> str:
        model = os.getenv("OPENAI_MODEL", "gpt-4.1-mini")
        if os.getenv("OPENAI_BASE_URL"):
            result = client.chat.completions.create(
                model=model,
                messages=[{"role": "user", "content": prompt}],
            )
            choices = getattr(result, "choices", [])
            if not choices:
                return ""
            return getattr(choices[0].message, "content", "") or ""

        result = client.responses.create(
            model=model,
            input=prompt,
        )
        return getattr(result, "output_text", "") or ""

    def _create_openai_client(self):
        from openai import OpenAI

        client_options = {"api_key": os.getenv("OPENAI_API_KEY")}
        base_url = os.getenv("OPENAI_BASE_URL")
        if base_url:
            client_options["base_url"] = base_url
        return OpenAI(**client_options)

    def _build_llm_prompt(self, message: str, fallback: StorageChatResponse) -> str:
        return (
            "你是一个企业存储测试平台的AI Agent。"
            "你只能在能力范围内回答：测试任务、节点/样品/用例、性能指标、报告生成、当前时间和能力说明。"
            "创建任务、执行任务、生成报告必须要求用户确认。"
            "如果用户需求超出范围，必须礼貌说明暂不支持。"
            "\n\n"
            f"用户消息：{message}\n"
            f"规则工具初判意图：{fallback.intent}\n"
            f"规则工具建议回答：{fallback.reply}\n"
            "请输出一段简洁中文回复。"
        )

    def _fallback_chat(self, session_id: str, message: str) -> StorageChatResponse:
        lower = message.lower()
        if self._is_capability_question(message):
            return StorageChatResponse(
                sessionId=session_id,
                reply=self._capability_reply(),
                intent="CAPABILITY",
                data={"llmMode": "fallback"},
            )

        if self._is_time_question(message):
            now = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            return StorageChatResponse(
                sessionId=session_id,
                reply=f"当前服务器时间是 {now}。",
                intent="GET_TIME",
                toolCalls=[StorageToolCall(tool="get_current_time", status="success", summary=now)],
                data={"llmMode": "fallback", "currentTime": now},
            )

        if any(keyword in message for keyword in ["机票", "订票", "外卖", "打车"]):
            return self._unsupported(session_id)

        parsed = self.parser.parse(message)
        if parsed.intent == "QUERY_RESULT" or "最高" in message or "最低" in message or "top" in lower:
            metric_request = self._metric_request(parsed)
            return StorageChatResponse(
                sessionId=session_id,
                reply="我识别到这是性能指标查询，会调用 query_metric 工具查询平台数据。当前演示模式已生成工具调用计划。",
                intent="QUERY_METRIC",
                toolCalls=[StorageToolCall(
                    tool="query_metric",
                    status="planned",
                    summary=f"suite={metric_request['suite']}, metric={metric_request['metricName']}, aggregation={metric_request['aggregation']}",
                )],
                data={"llmMode": "fallback", "metricRequest": metric_request},
            )

        if parsed.intent == "CREATE_REPORT":
            action = StorageAgentAction(
                type="CONFIRM_CREATE_REPORT",
                label="确认生成报告",
                actionId=f"{session_id}:create-report",
                payload=parsed.model_dump(),
            )
            return StorageChatResponse(
                sessionId=session_id,
                reply="我识别到你想生成测试报告。报告生成会写入系统文件，需要你确认后继续。",
                intent="CREATE_REPORT",
                needConfirm=True,
                toolCalls=[StorageToolCall(tool="plan_report", status="success", summary="created report plan")],
                actions=[action],
                data={"llmMode": "fallback", "parsed": parsed.model_dump()},
            )

        if parsed.intent == "CREATE_TEST_TASK":
            action = StorageAgentAction(
                type="CONFIRM_CREATE_TASK",
                label="确认创建任务",
                actionId=f"{session_id}:create-task",
                payload=parsed.model_dump(),
            )
            return StorageChatResponse(
                sessionId=session_id,
                reply="我识别到你想创建测试任务。创建或执行任务会占用节点，需要你确认后继续。",
                intent="CREATE_TEST_TASK",
                needConfirm=True,
                toolCalls=[StorageToolCall(tool="plan_test_task", status="success", summary="created task plan")],
                actions=[action],
                data={"llmMode": "fallback", "parsed": parsed.model_dump()},
            )

        return self._unsupported(session_id)

    def _metric_request(self, parsed) -> Dict[str, object]:
        return {
            "suite": parsed.suite or (parsed.testSuites[0] if parsed.testSuites else "CDM"),
            "metricName": parsed.metricName or "SEQ R 1M Q8T1",
            "aggregation": parsed.aggregation or "MAX",
            "rankLimit": parsed.rankLimit or 1,
        }

    def _is_capability_question(self, message: str) -> bool:
        return any(keyword in message for keyword in ["你能做什么", "能做什么", "能力", "帮助", "help"])

    def _is_time_question(self, message: str) -> bool:
        return any(keyword in message for keyword in ["几点", "时间", "现在几点", "当前时间"])

    def _capability_reply(self) -> str:
        return (
            "我目前可以协助处理测试任务、节点/样品/用例查询、存储性能指标分析、报告生成、"
            "当前时间查询，以及说明能力边界。创建任务、执行任务、生成报告这类动作需要你确认。"
        )

    def _unsupported(self, session_id: str) -> StorageChatResponse:
        return StorageChatResponse(
            sessionId=session_id,
            reply="抱歉，这个能力暂时不支持。我目前可以处理测试任务、节点/样品/用例查询、性能指标分析、报告生成、当前时间查询和普通能力说明。",
            intent="UNSUPPORTED",
            data={"llmMode": "fallback"},
        )
