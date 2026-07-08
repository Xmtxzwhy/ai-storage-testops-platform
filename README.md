# AI 存储性能 TestOps 平台

这是一个面向“测试开发 / 测试平台开发 / AI 应用开发”岗位的项目 Demo。项目基于已有 Spring Boot 测试管理系统扩展，模拟企业里常见的存储性能测试流程：选择测试节点、匹配测试用例、执行测试、收集结构化结果、查询历史指标、生成对比报告和导出 Excel。

项目刻意采用“AI Agent 作为自然语言入口，平台负责受控执行”的设计。Agent 不直接编造测试数据，也不直接执行危险动作，而是把用户口语化需求解析成结构化字段，再由后端平台完成校验、任务创建、结果查询和报告生成。

## 项目亮点

- 支持自然语言入口：创建测试任务、生成报告、查询性能指标。
- 面向存储性能测试场景建模：样品、节点、手机连接状态、ADB 状态、用例、任务、结果、报告数据集。
- 模拟分布式测试节点：节点状态、手机连接状态、ADB 状态都会参与可用性判断。
- 测试任务和报告生成解耦：可以先跑测试，也可以后续单独基于历史数据生成报告。
- 支持指标问答：例如“CDM 顺序读最高速率是哪个样品、哪个版本下的？”
- 支持 Excel 报告导出：包含均值、基线对比、差异百分比、warning/fail/N/A 状态。
- 配套中文 PRD、技术设计、数据库设计、Demo 脚本和面试讲述文档。

## 脱敏说明

仓库中的样品、版本和竞品名称均已泛化处理，例如：

- `Project-A`：目标项目
- `Flash-X`：目标颗粒或存储介质类型
- `FW-v2`：目标固件版本
- `FW-v1`：基线固件版本
- `Competitor-X`：竞品样品
- `Flash-Y`：竞品颗粒或存储介质类型

这些名称只用于演示数据结构和测试流程，不指向真实产品信息。

## 核心场景

### 1. 自然语言创建测试任务

示例输入：

```text
Node-3 上挂了 Project-A Flash-X 256G FW-v2，帮我跑 CDM、AS SSD、FIO 的 clean/dirty 性能测试。
```

执行流程：

1. Agent 解析用户需求。
2. 平台生成结构化测试任务草稿。
3. 平台校验样品、节点、手机连接、ADB 状态和用例匹配情况。
4. Mock Runner 模拟节点执行。
5. 测试结果写入标准化结果表。

### 2. 查询已有性能数据

示例输入：

```text
CDM 顺序读最高速率是哪个样品、哪个版本下的？
```

执行流程：

1. Agent 识别这是查询类问题。
2. 平台从标准化结果表查询数据。
3. 返回样品、项目、版本、场景、指标、均值和单位。

### 3. 生成版本 / 竞品对比报告

示例输入：

```text
生成 Project-A Flash-X 256G FW-v2、基线 FW-v1 和竞品 Competitor-X FW-c1 的性能对比报告。
```

执行流程：

1. 平台创建报告草稿。
2. 选择目标样品、基线样品和竞品样品作为报告数据集。
3. 报告服务查询历史测试结果。
4. 计算均值、差异百分比、warning/fail/N/A 状态。
5. 返回网页预览 JSON，并导出 `.xlsx` 报告文件。

## 系统架构

```text
用户
  |
  v
Spring Boot 测试管理系统
  |-- Storage REST APIs
  |-- 样品 / 节点 / 用例 / 任务管理
  |-- Mock Runner 模拟执行
  |-- 指标查询服务
  |-- 报告生成服务
  |
  +--> FastAPI Agent Sidecar
  |      |-- 意图识别
  |      |-- 任务 / 报告 / 查询字段抽取
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

## 技术栈

后端：

- Java 8
- Spring Boot 2.2.2
- MyBatis Plus
- MySQL
- Apache POI
- FastJSON

Agent 服务：

- Python
- FastAPI
- Pydantic
- Pytest

测试：

- JUnit 5
- Mockito
- Pytest

## 关键目录

```text
src/main/java/com/controller/storage       存储测试 REST API
src/main/java/com/service/storage          存储测试服务接口
src/main/java/com/service/impl/storage     任务、指标、报告、Agent facade 实现
src/main/java/com/entity/storage           存储测试实体
src/main/java/com/dto/storage              请求和响应 DTO
agent-service                              FastAPI Agent 服务
db                                         数据库脚本和演示数据
docs                                       PRD、设计、演示和面试文档
```

重要服务：

- `StorageAgentFacadeServiceImpl`：调用 FastAPI Agent，并记录 Agent 解析请求。
- `StorageMockRunnerServiceImpl`：创建任务并模拟节点执行。
- `StorageMetricQueryServiceImpl`：查询性能指标并生成可读答案。
- `StorageReportGeneratorServiceImpl`：生成报告预览数据和 Excel 文件。

## 数据库初始化

按顺序导入：

```sql
db/springbootarkc6v1u.sql
db/storage_testops_schema.sql
db/storage_testops_seed.sql
```

演示数据包含：

- `Project-A Flash-X 256G FW-v1` 基线样品
- `Project-A Flash-X 256G FW-v2` 目标样品
- `Competitor-X Flash-Y 256G FW-c1` 竞品样品
- CDM、AS SSD、FIO 历史性能数据
- 带手机连接和 ADB 状态的测试节点

## 启动 Agent 服务

```powershell
cd agent-service
python -m venv .venv
.\.venv\Scripts\Activate.ps1
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8090
```

Agent 接口：

```text
POST http://localhost:8090/api/agents/storage/parse
```

## 启动 Spring Boot 服务

```powershell
mvn spring-boot:run
```

默认后端地址：

```text
http://localhost:8080/springbootarkc6v1u
```

## API 示例

解析自然语言需求：

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8080/springbootarkc6v1u/storage-agent/parse `
  -Method Post `
  -ContentType 'application/json' `
  -Body '{"text":"Node-3 上挂了 Project-A Flash-X 256G FW-v2，跑 CDM、AS SSD 和 FIO clean/dirty 测试。"}'
```

运行模拟任务：

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/run `
  -Method Post
```

查询 CDM 顺序读最高值：

```powershell
Invoke-RestMethod `
  -Uri http://localhost:8080/springbootarkc6v1u/storage-metrics/query `
  -Method Post `
  -ContentType 'application/json' `
  -Body '{"suite":"CDM","metricName":"SEQ R 1M Q8T1","aggregation":"MAX","rankLimit":1}'
```

## 测试

Agent 解析测试：

```powershell
cd agent-service
python -m pytest tests/test_storage_agent_parser.py -q
```

Java 核心测试：

```powershell
mvn --% -Dtest=StorageMockRunnerServiceTest,StorageMetricQueryServiceTest,StorageReportGeneratorServiceTest -Dmaven.test.skip=false -DskipTests=false -Dsurefire.skipTests=false test
```

编译：

```powershell
mvn -DskipTests compile
```

## 文档

- 产品 PRD：`docs/product/AI-Storage-TestOps-PRD.md`
- 技术设计：`docs/superpowers/specs/2026-07-08-ai-storage-testops-design.md`
- 数据库设计：`docs/database/storage-testops-schema.md`
- Demo 脚本：`docs/demo/AI-Storage-TestOps-Demo-Script.md`
- 面试讲述：`docs/interview/AI-Storage-TestOps-Interview-Story.md`

## 当前 MVP 范围

已实现：

- Agent 解析任务创建、报告生成、指标查询三类意图。
- Mock Runner 模拟节点执行。
- 标准化存储性能结果入库。
- 基于历史结果的指标查询。
- 基于报告数据集的预览 JSON 和 Excel 导出。
- 基线缺失或为 0 时输出 `N/A`，避免错误计算百分比。

暂未实现：

- 真实 ADB 设备执行。
- 生产级分布式调度。
- 完整 RAG 知识库。
- 复杂审批流。
- 生产级权限系统重构。

## 面试讲法

这个项目可以概括为：

> 我没有重做一套测试平台，而是在已有测试管理系统上增加了一个 AI Agent 入口和存储性能测试自动化模块。Agent 负责把自然语言需求解析成结构化任务、报告或查询条件；平台负责节点校验、用例匹配、模拟执行、结果入库、指标查询和报告导出。这样既贴近企业已有系统的改造方式，也能体现测试平台开发和 AI 应用开发的结合。

可重点展开：

- 为什么把“测试任务”和“报告生成”拆成两个独立模块。
- 为什么 Agent 只做意图解析和字段抽取，不直接生成测试结果。
- 如何模拟节点不可用、手机未连接、ADB 异常等真实测试平台问题。
- 如何从结构化结果表生成版本对比和竞品对比报告。
- 后续如何接入真实 ADB、Jenkins、消息队列、RAG 和 LangGraph 工作流。
