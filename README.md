# AI Storage TestOps Platform

AI Storage TestOps Platform is a storage-performance test management demo built
on top of an existing Spring Boot test management system. It focuses on a real
enterprise testing workflow: selecting test nodes, matching test cases, running
storage benchmarks, collecting structured results, answering metric questions,
and generating Excel-style comparison reports.

The project is designed for test development / test platform development roles,
while also showing an AI application full-stack direction through a natural
language Agent sidecar.

## Highlights

- Natural language Agent entry for storage test tasks, reports, and metric Q&A.
- Storage-domain data model covering samples, nodes, test cases, tasks, results,
  report datasets, reports, and Agent request logs.
- Simulated distributed node runner with node availability checks, phone
  connection state, and ADB state.
- Independent report module: reports can be generated from historical data
  without requiring a new test run.
- Metric query API that can answer questions such as: "Which sample/version has
  the highest CDM sequential read speed?"
- Excel report generation with preview JSON, delta calculation, warning/fail
  classification, and `N/A` handling for missing or zero baselines.
- Interview-ready Chinese PRD, architecture design, database design, demo
  script, and project story documents.

## Demo Scenarios

### 1. Create And Run A Storage Test Task

Example user input:

```text
Node-3 has WM6000 N38B 256G V2.0.4 connected. Run CDM, AS SSD, and FIO
clean/dirty performance tests.
```

Flow:

1. Agent parses the natural language request.
2. Platform creates a structured test task draft.
3. Platform validates sample, node, phone connection, ADB state, and matching
   test cases.
4. Mock runner simulates execution on the node.
5. Results are written into the standardized result table.

### 2. Query Existing Performance Data

Example user input:

```text
Which sample and version has the highest CDM sequential read speed?
```

Flow:

1. Agent identifies the request as a metric query.
2. Platform queries standardized test results.
3. The response includes sample code, project, firmware version, scene, metric,
   average value, and unit.

### 3. Generate A Comparison Report

Example user input:

```text
Generate a comparison report for WM6000 N38B 256G V2.0.4, baseline V2.0.3,
and competitor 2730AB V2.2.5.
```

Flow:

1. Platform creates a report draft and report datasets.
2. Report generator loads target, baseline, and competitor result data.
3. Platform calculates averages, deltas, warning/fail status, and summary.
4. Platform returns preview JSON and exports an `.xlsx` report.

## Architecture

```text
User
  |
  v
Spring Boot Test Management System
  |-- Storage REST APIs
  |-- Storage task / node / sample / case management
  |-- Mock runner
  |-- Metric query service
  |-- Report generator
  |
  +--> FastAPI Agent Sidecar
  |      |-- Intent parsing
  |      |-- Task/report/query slot extraction
  |
  +--> MySQL
         |-- storage_sample
         |-- storage_test_node
         |-- storage_test_case
         |-- storage_test_task
         |-- storage_test_result
         |-- storage_test_report
         |-- storage_report_dataset
         |-- storage_agent_request
```

## Tech Stack

Backend:

- Java 8
- Spring Boot 2.2.2
- MyBatis Plus
- MySQL
- Apache POI
- FastJSON

Agent sidecar:

- Python
- FastAPI
- Pydantic
- Pytest

Testing:

- JUnit 5
- Mockito
- Pytest

## Key Modules

```text
src/main/java/com/controller/storage
src/main/java/com/service/storage
src/main/java/com/service/impl/storage
src/main/java/com/entity/storage
src/main/java/com/dto/storage
agent-service
db
docs
```

Important backend services:

- `StorageAgentFacadeServiceImpl`: calls the FastAPI Agent sidecar and records
  Agent parse requests.
- `StorageMockRunnerServiceImpl`: creates and simulates test task execution.
- `StorageMetricQueryServiceImpl`: answers structured metric questions.
- `StorageReportGeneratorServiceImpl`: generates preview data and Excel reports.

## Database Setup

Import the base database and storage extension scripts:

```sql
db/springbootarkc6v1u.sql
db/storage_testops_schema.sql
db/storage_testops_seed.sql
```

The seed data includes:

- WM6000 N38B 256G V2.0.3 baseline sample
- WM6000 N38B 256G V2.0.4 target sample
- 2730AB AHGB 256G V2.2.5 competitor sample
- CDM, AS SSD, and FIO historical performance rows
- Node records with phone/ADB availability states

## Run The Agent Service

```powershell
cd agent-service
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8090
```

Agent API:

```text
POST http://localhost:8090/api/agents/storage/parse
```

## Run The Spring Boot Service

```powershell
mvn spring-boot:run
```

Default backend base URL:

```text
http://localhost:8080/springbootarkc6v1u
```

## Example APIs

Parse a natural language storage request:

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8080/springbootarkc6v1u/storage-agent/parse `
  -Method Post `
  -ContentType 'application/json' `
  -Body '{"text":"Node-3 has WM6000 N38B 256G V2.0.4 connected. Run CDM, AS SSD and FIO clean/dirty tests."}'
```

Run a mock task:

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/run `
  -Method Post
```

Query the highest CDM sequential read result:

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8080/springbootarkc6v1u/storage-metrics/query `
  -Method Post `
  -ContentType 'application/json' `
  -Body '{"suite":"CDM","metricName":"SEQ R 1M Q8T1","aggregation":"MAX","rankLimit":1}'
```

## Tests

Agent parser tests:

```powershell
cd agent-service
python -m pytest tests/test_storage_agent_parser.py -q
```

Java core tests:

```powershell
mvn --% -Dtest=StorageMockRunnerServiceTest,StorageMetricQueryServiceTest,StorageReportGeneratorServiceTest -Dmaven.test.skip=false -DskipTests=false -Dsurefire.skipTests=false test
```

Compile:

```powershell
mvn -DskipTests compile
```

## Documentation

- Product PRD: `docs/product/AI-Storage-TestOps-PRD.md`
- Technical design: `docs/superpowers/specs/2026-07-08-ai-storage-testops-design.md`
- Database design: `docs/database/storage-testops-schema.md`
- Demo script: `docs/demo/AI-Storage-TestOps-Demo-Script.md`
- Interview story: `docs/interview/AI-Storage-TestOps-Interview-Story.md`

## Current Scope

Implemented for the MVP:

- Agent parsing for task creation, report generation, and metric query intents.
- Simulated node execution instead of real ADB execution.
- Structured storage performance result persistence.
- Metric query and report generation from database records.
- Excel report export with a fallback workbook template.

Not implemented yet:

- Real ADB device execution.
- Production-grade distributed scheduler.
- Full RAG over an enterprise database schema.
- Complex approval workflow.
- Production authentication and authorization redesign.

## Interview Talking Points

This project can be explained as a practical AI + TestOps platform upgrade:

- It does not replace an enterprise test platform. It adds an AI Agent entry and
  automation layer on top of existing nodes, cases, result tables, and reports.
- It separates test execution from report generation, matching real enterprise
  workflows where data may be analyzed long after a test run.
- It keeps AI output controlled: the Agent parses intent and fills structured
  fields, while the platform owns validation, execution, database queries, and
  report generation.
- It demonstrates backend engineering, database modeling, AI sidecar integration,
  test automation thinking, and report automation in one coherent scenario.
