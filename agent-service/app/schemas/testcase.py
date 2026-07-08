from enum import Enum
from typing import List, Optional

from pydantic import BaseModel, Field


class Priority(str, Enum):
    P0 = "P0"
    P1 = "P1"
    P2 = "P2"
    P3 = "P3"


class TestType(str, Enum):
    FUNCTIONAL = "functional"
    NEGATIVE = "negative"
    BOUNDARY = "boundary"
    PERMISSION = "permission"
    COMPATIBILITY = "compatibility"
    RELIABILITY = "reliability"
    PERFORMANCE = "performance"
    DATA_CONSISTENCY = "data_consistency"


class GenerateTestCasesRequest(BaseModel):
    project_name: str = Field(..., description="Project or product name")
    module_name: str = Field(..., description="Feature module under test")
    requirement: str = Field(..., description="Requirement, PRD fragment, or defect description")
    role: Optional[str] = Field(default="tester", description="User role or test role")
    platform: Optional[str] = Field(default="web", description="web, Android, iOS, embedded, etc.")
    risk_keywords: List[str] = Field(default_factory=list, description="Known risk keywords")
    max_cases: int = Field(default=8, ge=1, le=30)


class GeneratedTestCase(BaseModel):
    title: str
    test_type: TestType
    priority: Priority
    preconditions: List[str]
    steps: List[str]
    expected_result: str
    test_data: List[str] = Field(default_factory=list)
    risk_point: str
    traceability: str = Field(..., description="Requirement fragment or reason used to generate the case")


class GenerateTestCasesResponse(BaseModel):
    agent_name: str
    generation_strategy: str
    requirement_summary: str
    test_cases: List[GeneratedTestCase]
    review_checklist: List[str]

