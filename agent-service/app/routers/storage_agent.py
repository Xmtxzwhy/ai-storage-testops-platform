from fastapi import APIRouter

from app.schemas.storage_agent import StorageAgentParseRequest, StorageAgentParseResponse
from app.schemas.storage_chat import StorageChatRequest, StorageChatResponse
from app.services.storage_chat_agent import StorageChatAgent
from app.services.storage_agent_parser import StorageAgentParser


router = APIRouter(tags=["storage-agent"])
parser = StorageAgentParser()
chat_agent = StorageChatAgent()


@router.post("/storage/parse", response_model=StorageAgentParseResponse)
def parse_storage_request(request: StorageAgentParseRequest):
    return parser.parse(request.text)


@router.post("/storage/chat", response_model=StorageChatResponse)
def chat_storage_agent(request: StorageChatRequest):
    return chat_agent.chat(request)
