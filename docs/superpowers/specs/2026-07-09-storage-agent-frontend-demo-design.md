# AI Storage TestOps Frontend Demo Design

## Goal

Build a browser-based demo page inside the existing Vue 2 admin UI so an interviewer can see the AI Storage TestOps workflow without using Swagger or PowerShell.

## Scope

This iteration adds one focused page: `AI测管演示台`.

The page demonstrates three interview-critical flows:

1. Natural language request parsing.
2. Test task creation, mock execution, and result viewing.
3. Report creation, preview, generation, Excel download, and metric Q&A.

The page reuses the existing Spring Boot storage APIs and does not replace the original management modules.

## User Experience

The user enters a sentence such as:

```text
Node-3 上挂了 Project-A Flash-X 256G FW-v2，跑 CDM clean
```

The page shows parsed fields including intent, project, version, particle, capacity, node, suite, scene, confidence, and whether confirmation is needed.

After parsing, the user can create a test task from the parsed result, run the mock task, view generated metric rows, create a comparison report, generate the Excel file, and download it.

The page also includes a small metric question form for examples like "CDM 顺序读最高速率是哪个样品、哪个版本".

## Architecture

Frontend:

- Existing Vue 2 and Element UI application.
- Add a new view component under `src/main/resources/admin/admin/src/views/modules/storage-agent-demo/`.
- Add one static router entry under `/storage-agent-demo`.
- Use the existing Axios wrapper `src/main/resources/admin/admin/src/utils/http.js`.

Backend:

- Spring Boot remains the browser-facing API under `/springbootarkc6v1u`.
- Spring Boot calls the FastAPI sidecar for natural language parsing through `/storage-agent/parse`.
- Existing storage task, report, and metric endpoints are reused.

## API Flow

Natural language parse:

- `POST /storage-agent/parse`
- Body: `{ "text": string }`

Create task:

- `POST /storage-tasks/create`
- Body is assembled from parsed fields and demo defaults.

Run task:

- `POST /storage-tasks/{id}/run`

Read results:

- `GET /storage-tasks/{id}/results`

Create and generate report:

- `POST /storage-reports/create`
- `POST /storage-reports/{id}/generate`
- `GET /storage-reports/{id}`
- Download through `/springbootarkc6v1u/storage-reports/{id}/download`.

Metric Q&A:

- `POST /storage-metrics/query`

## Error Handling

The page shows Element UI messages for API failures, missing parsed fields, missing task id, and missing report id.

If the Agent service is not running on port `8090`, the parse call may fail through Spring Boot. The page should surface that as "Agent服务不可用，请先启动 agent-service".

## Testing

Because this is a Vue 2 project without existing frontend unit tests, verification for this iteration is:

- `npm run build` in `src/main/resources/admin/admin`.
- `mvn -DskipTests compile` at project root.
- Manual browser flow:
  1. Open the admin UI.
  2. Enter the demo sentence.
  3. Parse.
  4. Create task.
  5. Run task.
  6. View results.
  7. Generate report.
  8. Download Excel.

## Out of Scope

- Docker Compose packaging.
- A new frontend project.
- Fully dynamic report template editing.
- Real ADB node execution.
- User permission/menu database migration. Static route access is enough for the demo.
