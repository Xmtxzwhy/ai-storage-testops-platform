# LLM Storage Chat Agent Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add a conversational storage agent endpoint and frontend chat panel with LLM-ready architecture, safe tool traces, confirmation boundaries, and no-key fallback behavior.

**Architecture:** FastAPI owns the agent chat loop and fallback intelligence. Spring Boot proxies browser requests to FastAPI and preserves the existing `/storage-agent/parse` behavior. Vue adds a chat panel above the existing workflow controls and displays messages, tool calls, and pending actions.

**Tech Stack:** FastAPI, Pydantic, optional OpenAI Python SDK, Spring Boot RestTemplate, Vue 2, Element UI, existing Axios wrapper.

## Global Constraints

- Keep `/api/agents/storage/parse` unchanged for backward compatibility.
- Add `/api/agents/storage/chat` without requiring an OpenAI API key.
- If `OPENAI_API_KEY` is absent, fallback mode must still answer capability, metric, unsupported, and confirmation-required demo prompts.
- High-risk actions such as task creation, execution, and report generation must return confirmation instead of executing autonomously in this iteration.
- Do not modify the unrelated dirty SQL file `db/springbootarkc6v1u.sql`.
- Verification commands: Python tests, `npm run build`, and `mvn -DskipTests compile`.

---

### Task 1: FastAPI Chat Schema and Fallback Agent

**Files:**
- Create: `agent-service/app/schemas/storage_chat.py`
- Create: `agent-service/app/services/storage_chat_agent.py`
- Modify: `agent-service/app/routers/storage_agent.py`
- Test: `agent-service/tests/test_storage_chat_agent.py`

**Interfaces:**
- Produces `StorageChatRequest(sessionId: str, message: str, confirmActionId: str | None)`.
- Produces `StorageChatResponse(sessionId, reply, intent, needConfirm, toolCalls, actions, data)`.
- Produces `StorageChatAgent.chat(request: StorageChatRequest) -> StorageChatResponse`.
- Produces `POST /api/agents/storage/chat`.

- [x] Write tests for fallback capability, unsupported request, metric query trace, and task confirmation.
- [x] Run `python -m pytest tests/test_storage_chat_agent.py -q` and verify tests fail because files are missing.
- [x] Implement schemas, fallback agent, and router endpoint.
- [x] Run `python -m pytest tests/test_storage_chat_agent.py -q` and verify tests pass.

### Task 2: LLM Client Stub and Dependency

**Files:**
- Modify: `agent-service/requirements.txt`
- Modify: `agent-service/app/services/storage_chat_agent.py`
- Test: `agent-service/tests/test_storage_chat_agent.py`

**Interfaces:**
- Adds optional OpenAI dependency.
- Adds `llmMode` field in response data: `fallback` or `openai`.
- Does not make network calls when `OPENAI_API_KEY` is absent.

- [x] Extend tests to assert fallback mode when no API key exists.
- [x] Run the test and verify it fails before implementation if `llmMode` is missing.
- [x] Add `openai>=1.0.0` to requirements.
- [x] Add a guarded OpenAI client path that is disabled without `OPENAI_API_KEY`.
- [x] Run Python tests and verify pass.

### Task 3: Spring Boot Chat Proxy

**Files:**
- Create: `src/main/java/com/dto/storage/StorageAgentChatRequest.java`
- Create: `src/main/java/com/dto/storage/StorageAgentChatResponse.java`
- Modify: `src/main/java/com/controller/storage/StorageAgentController.java`
- Modify: `src/main/java/com/service/storage/StorageAgentFacadeService.java`
- Modify: `src/main/java/com/service/impl/storage/StorageAgentFacadeServiceImpl.java`

**Interfaces:**
- Adds Spring endpoint `POST /storage-agent/chat`.
- Spring sends chat request to FastAPI `/api/agents/storage/chat`.
- Response is wrapped in existing `R.ok().put("data", ...)`.

- [x] Add DTOs matching the FastAPI schema.
- [x] Add `chat(StorageAgentChatRequest request)` to facade service.
- [x] Add controller method `/storage-agent/chat`.
- [x] Run `mvn -DskipTests compile` and verify compilation.

### Task 4: Vue Chat Panel

**Files:**
- Modify: `src/main/resources/admin/admin/src/views/modules/storage-agent-demo/index.vue`

**Interfaces:**
- Calls `POST storage-agent/chat`.
- Shows chat messages, tool calls, and pending action buttons.
- Keeps existing workflow panels available below the chat.

- [x] Add chat state, text input, send button, and example chips.
- [x] Add `sendChatMessage()` using existing `$http`.
- [x] Render assistant `toolCalls` as compact timeline rows.
- [x] Render `needConfirm` actions as visible buttons; first iteration buttons show a confirmation message rather than executing risky tools.
- [x] Run `npm run build` and verify pass.

### Task 5: Verification, Commit, Push

**Files:**
- Check all modified files.

**Interfaces:**
- Local FastAPI docs expose `/api/agents/storage/chat`.
- Vue page can chat without OpenAI key.

- [x] Run `python -m pytest tests/test_storage_chat_agent.py tests/test_storage_agent_parser.py -q`.
- [x] Run `npm run build` from `src/main/resources/admin/admin`.
- [x] Run `mvn -DskipTests compile`.
- [x] Confirm `git status --short` only has intended changes plus the pre-existing dirty SQL file.
- [x] Commit with `feat: add llm-ready storage chat agent`.
- [x] Push to GitHub.
