from fastapi import APIRouter

from app.schemas.testcase import GenerateTestCasesRequest, GenerateTestCasesResponse
from app.services.testcase_generator import TestCaseGenerator


router = APIRouter(tags=["testcase-agent"])
generator = TestCaseGenerator()


@router.post("/test-cases/generate", response_model=GenerateTestCasesResponse)
def generate_test_cases(request: GenerateTestCasesRequest):
    return generator.generate(request)

