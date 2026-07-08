# AI 存储性能 TestOps Demo 脚本

## 0. 演示前准备

先启动 Agent 服务：

```powershell
cd agent-service
uvicorn app.main:app --reload --port 8090
```

再启动 Spring Boot：

```powershell
mvn spring-boot:run
```

默认后端地址：

```text
http://localhost:8080/springbootarkc6v1u
```

演示前建议先导入：

- `db/storage_testops_schema.sql`
- `db/storage_testops_seed.sql`

---

## Demo A：自然语言创建测试任务

### 现场输入

```text
Node-3 上挂了 WM6000 N38B 256G V2.0.4，跑 CDM、AS SSD、FIO，场景是 clean 和 dirty，创建一个测试任务。
```

### 实际接口和命令

1. 让 Agent 解析自然语言：

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-agent/parse -Method Post -ContentType 'application/json' -Body '{"text":"Node-3 上挂了 WM6000 N38B 256G V2.0.4，跑 CDM、AS SSD、FIO，场景是 clean 和 dirty，创建一个测试任务。"}'
```

2. 按解析结果创建测试任务：

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/create -Method Post -ContentType 'application/json' -Body '{"taskName":"WM6000 V2.0.4 CDM AS SSD FIO Demo","rawUserInput":"Node-3 上挂了 WM6000 N38B 256G V2.0.4，跑 CDM、AS SSD、FIO，场景是 clean 和 dirty，创建一个测试任务。","projectName":"WM6000","targetVersion":"V2.0.4","sampleId":2,"nodeId":3,"testSuites":["CDM","AS_SSD","FIO"],"scenes":["clean","dirty"]}'
```

3. 启动 Mock Runner 执行：

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/run -Method Post
```

4. 查看执行结果：

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/results -Method Get
```

### 预期输出

- Agent 返回 `intent = CREATE_TEST_TASK`
- 平台识别项目、版本、节点、测试套件和场景
- Node-3 满足 `IDLE / CONNECTED / DEVICE`
- 任务状态从 `DRAFT` 进入 `RUNNING`，最终变为 `COMPLETED`
- `storage_test_result` 生成 CDM、AS SSD、FIO 对应结果

### 现场可说的话

这里不是让 AI 直接执行危险动作，而是先让 Agent 把自然语言拆成结构化任务草稿，再由平台检查节点、手机连接、ADB 状态和用例匹配情况。用户确认后，平台才执行任务。第一版用 Mock Runner 模拟真实节点，后续可以替换成 ADB 脚本、Jenkins 或真实节点 Agent。

---

## Demo B：智能测试数据问答

### 现场输入

```text
CDM 顺序读最高速率是哪个样品，哪个版本下的？
```

### 实际接口和命令

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-metrics/query -Method Post -ContentType 'application/json' -Body '{"suite":"CDM","metricName":"SEQ R 1M Q8T1","aggregation":"MAX","rankLimit":1}'
```

### 预期输出

- Agent 可识别为 `QUERY_RESULT`
- 平台查询 `storage_test_result`
- 平台关联 `storage_sample`
- 返回 `sampleCode`、`fwVersion`、`scene`、`metricName`、`averageValue`
- `answer` 用中文说明最高值对应的样品、版本、场景和数值

### 现场可说的话

这个能力体现的是“测试数据问答”。AI 不自己编答案，而是把问题翻译成受控查询条件，再由平台查询标准化结果表。这样可以回答最高值、最低值、Top N、版本差异等问题。

---

## Demo C：生成对比报告

### 现场输入

```text
生成 WM6000 V2.0.4、V2.0.3 和竞品 2730AB 的 CDM、AS SSD、FIO 对比报告。
```

### 实际接口和命令

1. 创建报告：

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/create -Method Post -ContentType 'application/json' -Body '{"reportName":"WM6000 V2.0.4 对比报告","reportType":"COMPARISON","sampleIds":[2,1,3]}'
```

2. 生成报告：

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/1/generate -Method Post
```

3. 查看报告信息并下载 Excel：

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/1 -Method Get
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/1/download -Method Get -OutFile 'storage-report-1.xlsx'
```

### 预期输出

- Agent 可识别为 `CREATE_REPORT`
- 平台创建报告数据集：`TARGET / BASELINE / COMPETITOR`
- 生成 `preview_json`
- 导出 `upload/reports/storage-report-1.xlsx`
- 基准值为空或为 0 时，预览和 Excel 都显示 `N/A`

### 现场可说的话

报告模块和任务执行模块是解耦的。报告可以来自刚跑完的任务，也可以来自历史数据、导入数据、上版本数据和竞品数据。这样更贴近真实企业里“后续再拉报告、再加竞品对比”的工作方式。

---

## 验证包

演示前后建议按这个顺序快速过一遍：

1. `/storage-agent/parse` 返回正确 intent
2. `/storage-tasks/create` 成功创建任务
3. `/storage-tasks/{id}/run` 生成标准化结果
4. `/storage-metrics/query` 返回中文答案
5. `/storage-reports/create` 成功创建报告数据集
6. `/storage-reports/{id}/generate` 成功生成预览和 Excel
7. `/storage-reports/{id}/download` 可以下载 Excel

这条演示线可以完整讲清楚：

```text
自然语言入口 -> 结构化任务/查询/报告 -> 标准化测试结果 -> 数据问答 -> 对比报告 -> Excel 导出
```
