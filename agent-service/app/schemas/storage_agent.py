from typing import List, Optional

from pydantic import BaseModel, Field


class StorageAgentParseRequest(BaseModel):
    text: str


class StorageAgentParseResponse(BaseModel):
    intent: str
    projectName: Optional[str] = None
    targetVersion: Optional[str] = None
    baselineVersion: Optional[str] = None
    competitor: Optional[str] = None
    particle: Optional[str] = None
    capacity: Optional[str] = None
    nodeCode: Optional[str] = None
    testSuites: List[str] = Field(default_factory=list)
    scenes: List[str] = Field(default_factory=list)
    reportTypes: List[str] = Field(default_factory=list)
    suite: Optional[str] = None
    metricName: Optional[str] = None
    aggregation: Optional[str] = None
    rankLimit: Optional[int] = None
    dimensions: List[str] = Field(default_factory=list)
    missingFields: List[str] = Field(default_factory=list)
    needConfirm: bool = True
    confidence: float = 0.80
