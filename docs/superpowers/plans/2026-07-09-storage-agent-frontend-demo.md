# Storage Agent Frontend Demo Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Add a browser demo page for AI storage test task creation, mock execution, report generation, and metric Q&A.

**Architecture:** Implement one Vue 2 + Element UI page inside the existing admin frontend. The page calls existing Spring Boot storage APIs through the existing Axios wrapper, and Spring Boot continues to proxy natural language parsing to the FastAPI Agent service.

**Tech Stack:** Vue 2, Element UI, Axios wrapper at `src/main/resources/admin/admin/src/utils/http.js`, Spring Boot storage APIs.

## Global Constraints

- Keep the feature focused on interview demo usability.
- Do not modify the unrelated dirty SQL file `db/springbootarkc6v1u.sql`.
- Reuse existing APIs; do not add backend endpoints in this iteration.
- Keep static route access enough for the demo; do not add database menu migration yet.
- Verification commands: `npm run build` in the Vue app and `mvn -DskipTests compile` at project root.

---

### Task 1: Add Static Route and Sidebar Entry

**Files:**
- Modify: `src/main/resources/admin/admin/src/router/router-static.js`
- Modify: `src/main/resources/admin/admin/src/components/index/IndexAsideStatic.vue`

**Interfaces:**
- Produces route: `/storage-agent-demo`
- Produces menu item label: `AI测管演示台`

- [x] Import the new view component as `StorageAgentDemo`.
- [x] Add a child route under the existing root `Index` route.
- [x] Add a static menu item in `IndexAsideStatic.vue` that navigates to `storage-agent-demo`.
- [x] Run the Vue build to verify route compilation.

### Task 2: Build Demo Page

**Files:**
- Create: `src/main/resources/admin/admin/src/views/modules/storage-agent-demo/index.vue`

**Interfaces:**
- Consumes route `/storage-agent-demo`.
- Consumes Spring Boot APIs:
  - `POST storage-agent/parse`
  - `POST storage-tasks/create`
  - `POST storage-tasks/{id}/run`
  - `GET storage-tasks/{id}/results`
  - `POST storage-reports/create`
  - `POST storage-reports/{id}/generate`
  - `GET storage-reports/{id}`
  - `POST storage-metrics/query`

- [x] Add natural language textarea with a default demo sentence.
- [x] Add parse button and parsed field descriptions.
- [x] Add task form fields for task name, sample id, node id, suites, and scenes.
- [x] Add create task and mock run buttons.
- [x] Add result table showing suite, scene, metric, three rounds, average, unit, status, and log summary.
- [x] Add report form with sample ids, create/generate buttons, preview text, and Excel download link.
- [x] Add metric query form and answer display.
- [x] Add Element UI error messages for missing inputs and API failures.

### Task 3: Verify and Commit

**Files:**
- Check: all files modified in Tasks 1 and 2.

**Interfaces:**
- Browser entry: `http://localhost:8081/#/storage-agent-demo` for Vue dev server.
- Packaged entry: `http://localhost:8080/springbootarkc6v1u/admin/dist/index.html#/storage-agent-demo` after build if served from Spring Boot static resources.

- [x] Run `npm run build` in `src/main/resources/admin/admin`.
- [x] Run `mvn -DskipTests compile` at project root.
- [x] Confirm `git status --short` only includes intended frontend/docs changes plus the pre-existing dirty SQL file.
- [x] Commit frontend changes with message `feat: add storage agent frontend demo`.
