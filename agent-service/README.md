# AI TestOps Agent Service

This service is the AI sidecar for the existing Spring Boot test management
system. The first milestone focuses on a test case generation agent:

1. Accept a requirement or defect description.
2. Analyze test dimensions such as positive, negative, boundary, permission,
   compatibility, reliability, and data consistency.
3. Generate structured test cases.
4. Return JSON that can be reviewed and saved into the existing `ceshiyongli`
   module.

The current implementation includes a deterministic local generator so the
project can be demonstrated without an LLM API key. Later milestones can plug in
OpenAI-compatible models, RAG retrieval, and database tools.

## Run

```powershell
cd agent-service
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8090
```

## Try

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8090/api/agents/test-cases/generate `
  -Method Post `
  -ContentType 'application/json' `
  -Body (Get-Content -Raw .\examples\testcase_request.json)
```

