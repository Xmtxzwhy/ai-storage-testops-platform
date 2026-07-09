import os

from app.schemas.storage_chat import StorageChatRequest
from app.services.storage_chat_agent import StorageChatAgent


def test_capability_question_returns_agent_boundary(monkeypatch):
    monkeypatch.delenv("OPENAI_API_KEY", raising=False)

    response = StorageChatAgent().chat(
        StorageChatRequest(sessionId="demo", message="你能做什么？")
    )

    assert response.sessionId == "demo"
    assert response.intent == "CAPABILITY"
    assert response.needConfirm is False
    assert "测试任务" in response.reply
    assert response.data["llmMode"] == "fallback"


def test_unsupported_request_is_rejected(monkeypatch):
    monkeypatch.delenv("OPENAI_API_KEY", raising=False)

    response = StorageChatAgent().chat(
        StorageChatRequest(sessionId="demo", message="帮我订一张去上海的机票")
    )

    assert response.intent == "UNSUPPORTED"
    assert response.needConfirm is False
    assert "暂时不支持" in response.reply
    assert response.toolCalls == []


def test_metric_query_returns_tool_call_trace(monkeypatch):
    monkeypatch.delenv("OPENAI_API_KEY", raising=False)

    response = StorageChatAgent().chat(
        StorageChatRequest(sessionId="demo", message="CDM 顺序读最高的是哪个样品哪个版本")
    )

    assert response.intent == "QUERY_METRIC"
    assert response.needConfirm is False
    assert response.toolCalls[0].tool == "query_metric"
    assert response.toolCalls[0].status == "planned"
    assert response.data["metricRequest"]["suite"] == "CDM"
    assert response.data["metricRequest"]["metricName"] == "SEQ R 1M Q8T1"
    assert response.data["metricRequest"]["aggregation"] == "MAX"


def test_task_execution_request_requires_confirmation(monkeypatch):
    monkeypatch.delenv("OPENAI_API_KEY", raising=False)

    response = StorageChatAgent().chat(
        StorageChatRequest(sessionId="demo", message="Node-3 跑 Project-A FW-v2 的 CDM clean")
    )

    assert response.intent == "CREATE_TEST_TASK"
    assert response.needConfirm is True
    assert response.actions[0].type == "CONFIRM_CREATE_TASK"
    assert "确认" in response.reply
    assert response.data["parsed"]["nodeCode"] == "Node-3"


def test_openai_key_marks_response_as_openai_ready(monkeypatch):
    monkeypatch.setenv("OPENAI_API_KEY", "test-key")
    monkeypatch.setenv("AGENT_LLM_ENABLED", "true")

    class FakeResponse:
        output_text = "我是通过LLM生成的回答。"

    class FakeResponses:
        def __init__(self):
            self.calls = []

        def create(self, **kwargs):
            self.calls.append(kwargs)
            return FakeResponse()

    class FakeClient:
        def __init__(self):
            self.responses = FakeResponses()

    fake_client = FakeClient()

    response = StorageChatAgent(llm_client=fake_client).chat(
        StorageChatRequest(sessionId="demo", message="你能做什么？")
    )

    assert response.reply == "我是通过LLM生成的回答。"
    assert response.data["llmMode"] == "openai"
    assert response.toolCalls[0].tool == "llm_router"
    assert fake_client.responses.calls[0]["model"]


def test_missing_openai_dependency_falls_back(monkeypatch):
    monkeypatch.setenv("OPENAI_API_KEY", "test-key")
    monkeypatch.setenv("AGENT_LLM_ENABLED", "true")

    class MissingDependencyAgent(StorageChatAgent):
        def _create_openai_client(self):
            raise ModuleNotFoundError("No module named 'openai'")

    response = MissingDependencyAgent().chat(
        StorageChatRequest(sessionId="demo", message="你能做什么？")
    )

    assert response.intent == "CAPABILITY"
    assert response.data["llmMode"] == "fallback"
    assert response.toolCalls[0].tool == "llm_router"
    assert response.toolCalls[0].status == "failed"
