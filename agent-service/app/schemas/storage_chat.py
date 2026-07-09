from typing import Any, Dict, List, Optional

from pydantic import BaseModel, Field


class StorageChatRequest(BaseModel):
    sessionId: str = "default"
    message: str
    confirmActionId: Optional[str] = None


class StorageToolCall(BaseModel):
    tool: str
    status: str
    summary: str = ""


class StorageAgentAction(BaseModel):
    type: str
    label: str
    actionId: str
    payload: Dict[str, Any] = Field(default_factory=dict)


class StorageChatResponse(BaseModel):
    sessionId: str
    reply: str
    intent: str
    needConfirm: bool = False
    toolCalls: List[StorageToolCall] = Field(default_factory=list)
    actions: List[StorageAgentAction] = Field(default_factory=list)
    data: Dict[str, Any] = Field(default_factory=dict)
