from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.routers.storage_agent import router as storage_agent_router
from app.routers.testcase_agent import router as testcase_agent_router


app = FastAPI(
    title="AI TestOps Agent Service",
    version="0.1.0",
    description="Agent sidecar for test case generation and test analysis.",
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

app.include_router(testcase_agent_router, prefix="/api/agents")
app.include_router(storage_agent_router, prefix="/api/agents")


@app.get("/health")
def health():
    return {"status": "ok"}
