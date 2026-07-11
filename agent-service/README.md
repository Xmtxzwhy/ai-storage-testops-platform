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

## Run with DeepSeek

DeepSeek provides an OpenAI-compatible API. Create an API key at
`https://platform.deepseek.com/api_keys`, then start this service with:

```powershell
cd "E:\Mywork\TestManagement\springbootarkc6v1u(run success)\agent-service"
.\.venv\Scripts\Activate.ps1
$env:OPENAI_API_KEY="你的 DeepSeek API Key"
$env:OPENAI_BASE_URL="https://api.deepseek.com"
$env:OPENAI_MODEL="deepseek-v4-flash"
uvicorn app.main:app --reload --port 8090
```

If the service is already running, stop it with `Ctrl+C`, set the environment
variables above in the same PowerShell window, then start `uvicorn` again.

## Try

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8090/api/agents/test-cases/generate `
  -Method Post `
  -ContentType 'application/json' `
  -Body (Get-Content -Raw .\examples\testcase_request.json)
```
