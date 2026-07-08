from fastapi import APIRouter

from app.schemas.storage_agent import StorageAgentParseRequest, StorageAgentParseResponse
from app.services.storage_agent_parser import StorageAgentParser


router = APIRouter(tags=["storage-agent"])
parser = StorageAgentParser()


@router.post("/storage/parse", response_model=StorageAgentParseResponse)
def parse_storage_request(request: StorageAgentParseRequest):
    return parser.parse(request.text)
