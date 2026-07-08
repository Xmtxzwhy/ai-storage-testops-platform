# AI Storage TestOps MVP Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build the first interview-demo version of the AI storage performance TestOps platform: samples, nodes, cases, tasks, mock results, report datasets, Excel export, and basic metric Q&A.

**Architecture:** Keep Spring Boot as the trusted business platform and FastAPI as the Agent sidecar. Add a focused storage-testops domain beside the existing generated test-management modules, avoiding changes to legacy tables except for optional menu integration later.

**Tech Stack:** Spring Boot 2.2.2, Java 8, MyBatis Plus 2.x, MySQL, Apache POI 3.11, FastAPI, Pydantic, existing Vue/Element UI admin pattern.

## Global Constraints

- Use existing backend response wrapper `com.utils.R`.
- Use MyBatis Plus 2.x style entities, DAOs, services, and controllers.
- Do not replace existing `ceshiyongli`, `ceshirenwu`, or `zhixingrenwu` tables.
- Test execution and report generation must remain independent modules.
- Agent must not invent test result data; result answers come from `storage_test_result`.
- MVP execution uses Mock Runner only; real ADB control is out of scope.
- Node availability must include node status, phone connection status, and ADB state.
- First supported suites are `CDM`, `AS_SSD`, and `FIO`.
- Reports must support target, baseline, and competitor datasets.
- Missing or zero baseline values must render as `N/A`, not `#DIV/0!`.

---

## File Structure

Create backend storage domain files:

- `src/main/java/com/entity/storage/StorageTestNodeEntity.java`
- `src/main/java/com/entity/storage/StorageSampleEntity.java`
- `src/main/java/com/entity/storage/StorageTestCaseEntity.java`
- `src/main/java/com/entity/storage/StorageTestTaskEntity.java`
- `src/main/java/com/entity/storage/StorageTestResultEntity.java`
- `src/main/java/com/entity/storage/StorageTestReportEntity.java`
- `src/main/java/com/entity/storage/StorageReportDatasetEntity.java`
- `src/main/java/com/entity/storage/StorageAgentRequestEntity.java`
- `src/main/java/com/dao/storage/*.java`
- `src/main/java/com/service/storage/*.java`
- `src/main/java/com/service/impl/storage/*.java`
- `src/main/java/com/controller/storage/*.java`

Create backend DTO and service files:

- `src/main/java/com/dto/storage/StorageAgentParseRequest.java`
- `src/main/java/com/dto/storage/StorageAgentParseResult.java`
- `src/main/java/com/dto/storage/CreateStorageTaskRequest.java`
- `src/main/java/com/dto/storage/CreateStorageReportRequest.java`
- `src/main/java/com/dto/storage/MetricQueryRequest.java`
- `src/main/java/com/dto/storage/MetricQueryResult.java`
- `src/main/java/com/service/storage/StorageAgentFacadeService.java`
- `src/main/java/com/service/storage/StorageMockRunnerService.java`
- `src/main/java/com/service/storage/StorageReportGeneratorService.java`
- `src/main/java/com/service/storage/StorageMetricQueryService.java`

Create SQL and report template assets:

- `db/storage_testops_schema.sql`
- `db/storage_testops_seed.sql`
- `src/main/resources/templates/storage_performance_report_template.xlsx`

Create Agent files:

- `agent-service/app/schemas/storage_agent.py`
- `agent-service/app/services/storage_agent_parser.py`
- `agent-service/app/routers/storage_agent.py`
- Modify `agent-service/app/main.py` to include the new router.

Create tests:

- `src/test/java/com/storage/StorageMetricCalculatorTest.java`
- `src/test/java/com/storage/StorageMockRunnerServiceTest.java`
- `agent-service/tests/test_storage_agent_parser.py`

---

### Task 1: Database Schema And Seed Data

**Files:**
- Create: `db/storage_testops_schema.sql`
- Create: `db/storage_testops_seed.sql`
- Modify: `docs/database/storage-testops-schema.md`

**Interfaces:**
- Produces: MySQL tables used by all later backend tasks.
- Produces: Demo data for Project-A FW-v1, Project-A FW-v2, competitor Competitor-X, Node-1 through Node-4, and CDM/AS SSD/FIO cases.

- [ ] **Step 1: Create schema SQL**

Add `db/storage_testops_schema.sql` with the exact tables below:

```sql
DROP TABLE IF EXISTS `storage_agent_request`;
DROP TABLE IF EXISTS `storage_report_dataset`;
DROP TABLE IF EXISTS `storage_test_report`;
DROP TABLE IF EXISTS `storage_test_result`;
DROP TABLE IF EXISTS `storage_test_task`;
DROP TABLE IF EXISTS `storage_test_case`;
DROP TABLE IF EXISTS `storage_test_node`;
DROP TABLE IF EXISTS `storage_sample`;

CREATE TABLE `storage_sample` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `project_name` varchar(100) NOT NULL,
  `soc` varchar(100) DEFAULT NULL,
  `particle` varchar(100) DEFAULT NULL,
  `capacity` varchar(50) DEFAULT NULL,
  `fw_version` varchar(100) NOT NULL,
  `sample_code` varchar(100) DEFAULT NULL,
  `batch_no` varchar(100) DEFAULT NULL,
  `sample_type` varchar(30) NOT NULL DEFAULT 'SELF',
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_storage_sample_lookup` (`project_name`,`fw_version`,`particle`,`capacity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_node` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `node_code` varchar(50) NOT NULL,
  `node_name` varchar(100) DEFAULT NULL,
  `ip_address` varchar(100) DEFAULT NULL,
  `node_status` varchar(30) NOT NULL DEFAULT 'IDLE',
  `phone_status` varchar(30) NOT NULL DEFAULT 'NOT_CONNECTED',
  `adb_state` varchar(30) NOT NULL DEFAULT 'NOT_FOUND',
  `device_serial` varchar(100) DEFAULT NULL,
  `current_sample_id` bigint DEFAULT NULL,
  `capabilities` varchar(500) DEFAULT NULL,
  `last_heartbeat_time` datetime DEFAULT NULL,
  `last_adb_check_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_storage_node_code` (`node_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_case` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `case_name` varchar(200) NOT NULL,
  `suite` varchar(30) NOT NULL,
  `scene` varchar(30) NOT NULL,
  `metric_name` varchar(100) NOT NULL,
  `unit` varchar(30) NOT NULL DEFAULT 'MB/s',
  `priority` varchar(30) NOT NULL DEFAULT 'P1',
  `command_template` varchar(1000) DEFAULT NULL,
  `enabled` tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  KEY `idx_storage_case_suite_scene` (`suite`,`scene`,`enabled`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `task_name` varchar(200) NOT NULL,
  `raw_user_input` text,
  `project_name` varchar(100) DEFAULT NULL,
  `target_version` varchar(100) DEFAULT NULL,
  `sample_id` bigint DEFAULT NULL,
  `node_id` bigint DEFAULT NULL,
  `test_suites` varchar(200) DEFAULT NULL,
  `scenes` varchar(200) DEFAULT NULL,
  `task_status` varchar(30) NOT NULL DEFAULT 'DRAFT',
  `started_time` datetime DEFAULT NULL,
  `finished_time` datetime DEFAULT NULL,
  `error_message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_storage_task_status` (`task_status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_result` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `source_type` varchar(30) NOT NULL,
  `task_id` bigint DEFAULT NULL,
  `sample_id` bigint DEFAULT NULL,
  `node_id` bigint DEFAULT NULL,
  `suite` varchar(30) NOT NULL,
  `scene` varchar(30) NOT NULL,
  `metric_name` varchar(100) NOT NULL,
  `round1_value` decimal(12,3) DEFAULT NULL,
  `round2_value` decimal(12,3) DEFAULT NULL,
  `round3_value` decimal(12,3) DEFAULT NULL,
  `average_value` decimal(12,3) DEFAULT NULL,
  `unit` varchar(30) NOT NULL DEFAULT 'MB/s',
  `result_status` varchar(30) NOT NULL DEFAULT 'PASS',
  `log_summary` varchar(1000) DEFAULT NULL,
  `error_reason` varchar(1000) DEFAULT NULL,
  `executed_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_storage_result_lookup` (`sample_id`,`suite`,`scene`,`metric_name`),
  KEY `idx_storage_result_task` (`task_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_test_report` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `report_name` varchar(200) NOT NULL,
  `report_type` varchar(30) NOT NULL,
  `report_status` varchar(30) NOT NULL DEFAULT 'DRAFT',
  `preview_json` longtext,
  `excel_file_path` varchar(500) DEFAULT NULL,
  `summary` text,
  `generated_time` datetime DEFAULT NULL,
  `error_message` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_report_dataset` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `report_id` bigint NOT NULL,
  `dataset_role` varchar(30) NOT NULL,
  `label` varchar(200) DEFAULT NULL,
  `project_name` varchar(100) DEFAULT NULL,
  `fw_version` varchar(100) DEFAULT NULL,
  `soc` varchar(100) DEFAULT NULL,
  `particle` varchar(100) DEFAULT NULL,
  `capacity` varchar(50) DEFAULT NULL,
  `sample_id` bigint DEFAULT NULL,
  `query_filters_json` text,
  PRIMARY KEY (`id`),
  KEY `idx_storage_report_dataset` (`report_id`,`dataset_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `storage_agent_request` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `raw_input` text NOT NULL,
  `intent` varchar(50) NOT NULL,
  `parsed_json` text,
  `missing_fields` varchar(1000) DEFAULT NULL,
  `confidence` decimal(5,2) DEFAULT NULL,
  `need_confirm` tinyint NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

- [ ] **Step 2: Create seed SQL**

Add `db/storage_testops_seed.sql` with representative demo data:

```sql
INSERT INTO storage_sample (project_name, soc, particle, capacity, fw_version, sample_code, batch_no, sample_type, remark)
VALUES
('Project-A','MT6762','Flash-X','256G','FW-v1','Project-A-Flash-X-256G-FW-v1','B01','BASELINE','baseline version'),
('Project-A','MT6762','Flash-X','256G','FW-v2','Project-A-Flash-X-256G-FW-v2','B02','SELF','target version'),
('Competitor-X','Competitor-X','Flash-Y','256G','FW-c1','Competitor-X-Flash-Y-256G-FW-c1','C01','COMPETITOR','competitor sample');

INSERT INTO storage_test_node (node_code, node_name, ip_address, node_status, phone_status, adb_state, device_serial, current_sample_id, capabilities, last_heartbeat_time, last_adb_check_time)
VALUES
('Node-1','Windows Perf Node 1','192.168.10.11','IDLE','CONNECTED','DEVICE','SN-NODE1',2,'ADB,WINDOWS,CDM,AS_SSD,FIO',NOW(),NOW()),
('Node-2','Linux Perf Node 2','192.168.10.12','BUSY','CONNECTED','DEVICE','SN-NODE2',1,'ADB,UBUNTU,FIO',NOW(),NOW()),
('Node-3','Hybrid Perf Node 3','192.168.10.13','IDLE','CONNECTED','DEVICE','SN-NODE3',2,'ADB,WINDOWS,UBUNTU,CDM,AS_SSD,FIO',NOW(),NOW()),
('Node-4','Offline Node 4','192.168.10.14','OFFLINE','NOT_CONNECTED','NOT_FOUND',NULL,NULL,'ADB,WINDOWS',NULL,NULL);

INSERT INTO storage_test_case (case_name, suite, scene, metric_name, unit, priority, command_template, enabled)
VALUES
('CDM clean SEQ R 1M Q8T1','CDM','clean','SEQ R 1M Q8T1','MB/s','P0','mock:cdm:clean:seq_read',1),
('CDM clean SEQ W 1M Q8T1','CDM','clean','SEQ W 1M Q8T1','MB/s','P0','mock:cdm:clean:seq_write',1),
('CDM clean RND R 4K Q32T16','CDM','clean','RND R 4K Q32T16','MB/s','P1','mock:cdm:clean:rnd_read_q32',1),
('CDM clean RND W 4K Q32T16','CDM','clean','RND W 4K Q32T16','MB/s','P1','mock:cdm:clean:rnd_write_q32',1),
('CDM dirty SEQ R 1M Q8T1','CDM','dirty','SEQ R 1M Q8T1','MB/s','P0','mock:cdm:dirty:seq_read',1),
('CDM dirty SEQ W 1M Q8T1','CDM','dirty','SEQ W 1M Q8T1','MB/s','P0','mock:cdm:dirty:seq_write',1),
('AS SSD clean SEQ R','AS_SSD','clean','SEQ R','MB/s','P0','mock:asssd:clean:seq_read',1),
('AS SSD clean SEQ W','AS_SSD','clean','SEQ W','MB/s','P0','mock:asssd:clean:seq_write',1),
('AS SSD dirty RAN 4K R','AS_SSD','dirty','RAN 4K R','MB/s','P1','mock:asssd:dirty:ran4k_read',1),
('AS SSD dirty RAN 4K W','AS_SSD','dirty','RAN 4K W','MB/s','P1','mock:asssd:dirty:ran4k_write',1),
('FIO clean seq_read','FIO','clean','seq_read','MB/s','P0','mock:fio:clean:seq_read',1),
('FIO clean seq_write','FIO','clean','seq_write','MB/s','P0','mock:fio:clean:seq_write',1),
('FIO dirty rand_read_4k','FIO','dirty','rand_read_4k','MB/s','P1','mock:fio:dirty:rand_read_4k',1),
('FIO dirty rand_write_4k','FIO','dirty','rand_write_4k','MB/s','P1','mock:fio:dirty:rand_write_4k',1);
```

- [ ] **Step 3: Verify SQL can be loaded**

Run against the local MySQL used by the project:

```powershell
mysql -u root -p springbootarkc6v1u < db/storage_testops_schema.sql
mysql -u root -p springbootarkc6v1u < db/storage_testops_seed.sql
```

Expected: no SQL errors, and `SELECT COUNT(*) FROM storage_test_case;` returns `14`.

- [ ] **Step 4: Commit**

```bash
git add db/storage_testops_schema.sql db/storage_testops_seed.sql docs/database/storage-testops-schema.md
git commit -m "feat: add storage testops schema"
```

---

### Task 2: Backend Entities, DAOs, And CRUD Services

**Files:**
- Create: `src/main/java/com/entity/storage/*.java`
- Create: `src/main/java/com/dao/storage/*.java`
- Create: `src/main/java/com/service/storage/*.java`
- Create: `src/main/java/com/service/impl/storage/*.java`
- Create: `src/main/java/com/controller/storage/StorageSampleController.java`
- Create: `src/main/java/com/controller/storage/StorageNodeController.java`
- Create: `src/main/java/com/controller/storage/StorageTestCaseController.java`

**Interfaces:**
- Produces CRUD endpoints:
  - `GET/POST /storage-samples/page`, `/storage-samples/save`, `/storage-samples/update`, `/storage-samples/delete`
  - `GET/POST /storage-nodes/page`, `/storage-nodes/save`, `/storage-nodes/update`, `/storage-nodes/delete`
  - `GET/POST /storage-test-cases/page`, `/storage-test-cases/save`, `/storage-test-cases/update`, `/storage-test-cases/delete`

- [ ] **Step 1: Create one entity pattern and repeat for each table**

Use this exact pattern for `StorageSampleEntity.java`; create analogous entity classes for the other storage tables using fields from Task 1:

```java
package com.entity.storage;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("storage_sample")
public class StorageSampleEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String projectName;
    private String soc;
    private String particle;
    private String capacity;
    private String fwVersion;
    private String sampleCode;
    private String batchNo;
    private String sampleType;
    private String remark;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getSoc() { return soc; }
    public void setSoc(String soc) { this.soc = soc; }
    public String getParticle() { return particle; }
    public void setParticle(String particle) { this.particle = particle; }
    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }
    public String getFwVersion() { return fwVersion; }
    public void setFwVersion(String fwVersion) { this.fwVersion = fwVersion; }
    public String getSampleCode() { return sampleCode; }
    public void setSampleCode(String sampleCode) { this.sampleCode = sampleCode; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public String getSampleType() { return sampleType; }
    public void setSampleType(String sampleType) { this.sampleType = sampleType; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
```

- [ ] **Step 2: Create DAO pattern**

Example `StorageSampleDao.java`:

```java
package com.dao.storage;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.entity.storage.StorageSampleEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageSampleDao extends BaseMapper<StorageSampleEntity> {
}
```

- [ ] **Step 3: Create service pattern**

Example `StorageSampleService.java`:

```java
package com.service.storage;

import com.baomidou.mybatisplus.service.IService;
import com.entity.storage.StorageSampleEntity;
import com.utils.PageUtils;

import java.util.Map;

public interface StorageSampleService extends IService<StorageSampleEntity> {
    PageUtils queryPage(Map<String, Object> params);
}
```

Example `StorageSampleServiceImpl.java`:

```java
package com.service.impl.storage;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.storage.StorageSampleDao;
import com.entity.storage.StorageSampleEntity;
import com.service.storage.StorageSampleService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storageSampleService")
public class StorageSampleServiceImpl extends ServiceImpl<StorageSampleDao, StorageSampleEntity> implements StorageSampleService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<StorageSampleEntity> page = this.selectPage(
                new Query<StorageSampleEntity>(params).getPage(),
                new EntityWrapper<StorageSampleEntity>()
        );
        return new PageUtils(page);
    }
}
```

- [ ] **Step 4: Create controller pattern**

Example `StorageSampleController.java`:

```java
package com.controller.storage;

import com.entity.storage.StorageSampleEntity;
import com.service.storage.StorageSampleService;
import com.utils.PageUtils;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/storage-samples")
public class StorageSampleController {
    @Autowired
    private StorageSampleService storageSampleService;

    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params) {
        PageUtils page = storageSampleService.queryPage(params);
        return R.ok().put("data", page);
    }

    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        return R.ok().put("data", storageSampleService.selectById(id));
    }

    @RequestMapping("/save")
    public R save(@RequestBody StorageSampleEntity entity) {
        storageSampleService.insert(entity);
        return R.ok().put("data", entity.getId());
    }

    @RequestMapping("/update")
    public R update(@RequestBody StorageSampleEntity entity) {
        storageSampleService.updateById(entity);
        return R.ok();
    }

    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        storageSampleService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
}
```

- [ ] **Step 5: Run backend compile**

```powershell
mvn -DskipTests compile
```

Expected: `BUILD SUCCESS`.

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/entity/storage src/main/java/com/dao/storage src/main/java/com/service/storage src/main/java/com/service/impl/storage src/main/java/com/controller/storage
git commit -m "feat: add storage domain CRUD"
```

---

### Task 3: Agent Parser Sidecar

**Files:**
- Create: `agent-service/app/schemas/storage_agent.py`
- Create: `agent-service/app/services/storage_agent_parser.py`
- Create: `agent-service/app/routers/storage_agent.py`
- Modify: `agent-service/app/main.py`
- Create: `agent-service/tests/test_storage_agent_parser.py`

**Interfaces:**
- Produces: `POST /api/agents/storage/parse`
- Produces Python model `StorageAgentParseResponse`
- Used by Spring facade in Task 4.

- [ ] **Step 1: Write parser tests**

Create `agent-service/tests/test_storage_agent_parser.py`:

```python
from app.services.storage_agent_parser import StorageAgentParser


def test_parse_create_task_with_node_and_suites():
    parser = StorageAgentParser()
    result = parser.parse("Node-3 涓婃寕浜?Project-A Flash-X 256G FW-v2锛岃窇 CDM銆丄S SSD銆丗IO clean dirty")
    assert result.intent == "CREATE_TEST_TASK"
    assert result.projectName == "Project-A"
    assert result.targetVersion == "FW-v2"
    assert result.nodeCode == "Node-3"
    assert result.testSuites == ["CDM", "AS_SSD", "FIO"]
    assert result.scenes == ["clean", "dirty"]


def test_parse_report_with_baseline_and_competitor():
    parser = StorageAgentParser()
    result = parser.parse("鐢熸垚 Project-A FW-v2 瀵规瘮 FW-v1 鍜岀珵鍝?Competitor-X 鐨?CDM FIO 鎶ュ憡")
    assert result.intent == "CREATE_REPORT"
    assert result.projectName == "Project-A"
    assert result.targetVersion == "FW-v2"
    assert result.baselineVersion == "FW-v1"
    assert result.competitor == "Competitor-X"
    assert result.testSuites == ["CDM", "FIO"]


def test_parse_metric_query():
    parser = StorageAgentParser()
    result = parser.parse("CDM 椤哄簭璇绘渶楂橀€熺巼鏄摢涓牱鍝?鍝釜鐗堟湰")
    assert result.intent == "QUERY_RESULT"
    assert result.suite == "CDM"
    assert result.metricName == "SEQ R 1M Q8T1"
    assert result.aggregation == "MAX"
    assert result.needConfirm is False
```

- [ ] **Step 2: Run tests to verify they fail**

```powershell
cd agent-service
pytest tests/test_storage_agent_parser.py -q
```

Expected: FAIL because `storage_agent_parser` does not exist.

- [ ] **Step 3: Implement schemas**

Create `agent-service/app/schemas/storage_agent.py`:

```python
from typing import List, Optional
from pydantic import BaseModel


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
    testSuites: List[str] = []
    scenes: List[str] = []
    reportTypes: List[str] = []
    suite: Optional[str] = None
    metricName: Optional[str] = None
    aggregation: Optional[str] = None
    rankLimit: Optional[int] = None
    dimensions: List[str] = []
    missingFields: List[str] = []
    needConfirm: bool = True
    confidence: float = 0.80
```

- [ ] **Step 4: Implement parser**

Create `agent-service/app/services/storage_agent_parser.py`:

```python
import re
from app.schemas.storage_agent import StorageAgentParseResponse


class StorageAgentParser:
    def parse(self, text: str) -> StorageAgentParseResponse:
        normalized = text.replace("锛?, " ").replace(",", " ")
        intent = self._detect_intent(normalized)
        suites = self._extract_suites(normalized)
        scenes = self._extract_scenes(normalized)
        project = self._extract_project(normalized)
        versions = re.findall(r"V\d+(?:\.\d+)+", normalized, flags=re.IGNORECASE)
        node_match = re.search(r"Node[- ]?(\d+)|鑺傜偣\s*(\d+)", normalized, flags=re.IGNORECASE)

        result = StorageAgentParseResponse(
            intent=intent,
            projectName=project,
            targetVersion=versions[0] if versions else None,
            baselineVersion=versions[1] if len(versions) > 1 else None,
            competitor=self._extract_competitor(normalized),
            particle=self._extract_particle(normalized),
            capacity=self._extract_capacity(normalized),
            nodeCode=f"Node-{node_match.group(1) or node_match.group(2)}" if node_match else None,
            testSuites=suites,
            scenes=scenes,
            reportTypes=["single", "comparison"] if intent == "CREATE_REPORT" else [],
            needConfirm=intent != "QUERY_RESULT",
        )

        if intent == "QUERY_RESULT":
            result.suite = "CDM" if "CDM" in normalized.upper() else None
            result.metricName = "SEQ R 1M Q8T1" if "椤哄簭璇? in normalized or "seq" in normalized.lower() else None
            result.aggregation = "MAX" if "鏈€楂? in normalized or "鏈€澶? in normalized else "TOP"
            result.rankLimit = 1
            result.dimensions = ["sample", "version", "scene"]

        result.missingFields = self._missing_fields(result)
        return result

    def _detect_intent(self, text: str) -> str:
        if any(word in text for word in ["鏈€楂?, "鏈€浣?, "鍝釜鏍峰搧", "鍝釜鐗堟湰", "Top", "top"]):
            return "QUERY_RESULT"
        if any(word in text for word in ["鎶ュ憡", "瀵规瘮", "绔炲搧"]):
            return "CREATE_REPORT"
        return "CREATE_TEST_TASK"

    def _extract_suites(self, text: str):
        suites = []
        upper = text.upper()
        if "CDM" in upper:
            suites.append("CDM")
        if "AS SSD" in upper or "AS_SSD" in upper:
            suites.append("AS_SSD")
        if "FIO" in upper:
            suites.append("FIO")
        return suites or ["CDM", "AS_SSD", "FIO"]

    def _extract_scenes(self, text: str):
        scenes = []
        lower = text.lower()
        if "clean" in lower:
            scenes.append("clean")
        if "dirty" in lower:
            scenes.append("dirty")
        return scenes or ["clean"]

    def _extract_project(self, text: str):
        match = re.search(r"(WM\d+|Competitor-X)", text, flags=re.IGNORECASE)
        return match.group(1).upper() if match else None

    def _extract_competitor(self, text: str):
        if "绔炲搧" not in text:
            return None
        match = re.search(r"绔炲搧\s*([A-Za-z0-9]+)", text)
        return match.group(1).upper() if match else "Competitor-X"

    def _extract_particle(self, text: str):
        match = re.search(r"\b(N\d+B|Flash-Y)\b", text, flags=re.IGNORECASE)
        return match.group(1).upper() if match else None

    def _extract_capacity(self, text: str):
        match = re.search(r"(\d+\s*G)", text, flags=re.IGNORECASE)
        return match.group(1).replace(" ", "").upper() if match else None

    def _missing_fields(self, result: StorageAgentParseResponse):
        if result.intent == "QUERY_RESULT":
            return []
        required = ["projectName", "targetVersion"]
        if result.intent == "CREATE_TEST_TASK":
            required.append("nodeCode")
        return [field for field in required if getattr(result, field) in (None, "", [])]
```

- [ ] **Step 5: Add router**

Create `agent-service/app/routers/storage_agent.py`:

```python
from fastapi import APIRouter
from app.schemas.storage_agent import StorageAgentParseRequest, StorageAgentParseResponse
from app.services.storage_agent_parser import StorageAgentParser

router = APIRouter(tags=["storage-agent"])
parser = StorageAgentParser()


@router.post("/storage/parse", response_model=StorageAgentParseResponse)
def parse_storage_request(request: StorageAgentParseRequest):
    return parser.parse(request.text)
```

Modify `agent-service/app/main.py`:

```python
from fastapi import FastAPI
from app.routers import testcase_agent, storage_agent

app = FastAPI(title="AI TestOps Agent Service")
app.include_router(testcase_agent.router, prefix="/api/agents")
app.include_router(storage_agent.router, prefix="/api/agents")
```

- [ ] **Step 6: Run parser tests**

```powershell
cd agent-service
pytest tests/test_storage_agent_parser.py -q
```

Expected: `3 passed`.

- [ ] **Step 7: Commit**

```bash
git add agent-service/app/schemas/storage_agent.py agent-service/app/services/storage_agent_parser.py agent-service/app/routers/storage_agent.py agent-service/app/main.py agent-service/tests/test_storage_agent_parser.py
git commit -m "feat: add storage agent parser"
```

---

### Task 4: Spring Agent Facade And Intent Routing Endpoint

**Files:**
- Create: `src/main/java/com/dto/storage/StorageAgentParseRequest.java`
- Create: `src/main/java/com/dto/storage/StorageAgentParseResult.java`
- Create: `src/main/java/com/service/storage/StorageAgentFacadeService.java`
- Create: `src/main/java/com/service/impl/storage/StorageAgentFacadeServiceImpl.java`
- Create: `src/main/java/com/controller/storage/StorageAgentController.java`

**Interfaces:**
- Produces: `POST /storage-agent/parse`
- Consumes: FastAPI `POST http://localhost:8090/api/agents/storage/parse`
- Produces: agent request rows in `storage_agent_request`

- [ ] **Step 1: Create DTOs**

Create request DTO:

```java
package com.dto.storage;

public class StorageAgentParseRequest {
    private String text;
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}
```

Create result DTO with fields matching Agent JSON:

```java
package com.dto.storage;

import java.util.List;

public class StorageAgentParseResult {
    private String intent;
    private String projectName;
    private String targetVersion;
    private String baselineVersion;
    private String competitor;
    private String particle;
    private String capacity;
    private String nodeCode;
    private List<String> testSuites;
    private List<String> scenes;
    private List<String> reportTypes;
    private String suite;
    private String metricName;
    private String aggregation;
    private Integer rankLimit;
    private List<String> dimensions;
    private List<String> missingFields;
    private Boolean needConfirm;
    private Double confidence;

    public String getIntent() { return intent; }
    public void setIntent(String intent) { this.intent = intent; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getTargetVersion() { return targetVersion; }
    public void setTargetVersion(String targetVersion) { this.targetVersion = targetVersion; }
    public String getBaselineVersion() { return baselineVersion; }
    public void setBaselineVersion(String baselineVersion) { this.baselineVersion = baselineVersion; }
    public String getCompetitor() { return competitor; }
    public void setCompetitor(String competitor) { this.competitor = competitor; }
    public String getParticle() { return particle; }
    public void setParticle(String particle) { this.particle = particle; }
    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }
    public String getNodeCode() { return nodeCode; }
    public void setNodeCode(String nodeCode) { this.nodeCode = nodeCode; }
    public List<String> getTestSuites() { return testSuites; }
    public void setTestSuites(List<String> testSuites) { this.testSuites = testSuites; }
    public List<String> getScenes() { return scenes; }
    public void setScenes(List<String> scenes) { this.scenes = scenes; }
    public List<String> getReportTypes() { return reportTypes; }
    public void setReportTypes(List<String> reportTypes) { this.reportTypes = reportTypes; }
    public String getSuite() { return suite; }
    public void setSuite(String suite) { this.suite = suite; }
    public String getMetricName() { return metricName; }
    public void setMetricName(String metricName) { this.metricName = metricName; }
    public String getAggregation() { return aggregation; }
    public void setAggregation(String aggregation) { this.aggregation = aggregation; }
    public Integer getRankLimit() { return rankLimit; }
    public void setRankLimit(Integer rankLimit) { this.rankLimit = rankLimit; }
    public List<String> getDimensions() { return dimensions; }
    public void setDimensions(List<String> dimensions) { this.dimensions = dimensions; }
    public List<String> getMissingFields() { return missingFields; }
    public void setMissingFields(List<String> missingFields) { this.missingFields = missingFields; }
    public Boolean getNeedConfirm() { return needConfirm; }
    public void setNeedConfirm(Boolean needConfirm) { this.needConfirm = needConfirm; }
    public Double getConfidence() { return confidence; }
    public void setConfidence(Double confidence) { this.confidence = confidence; }
}
```

- [ ] **Step 2: Implement service**

Create `StorageAgentFacadeService`:

```java
package com.service.storage;

import com.dto.storage.StorageAgentParseResult;

public interface StorageAgentFacadeService {
    StorageAgentParseResult parse(String text);
}
```

Create implementation using `RestTemplate` or `HttpClient`. If no `RestTemplate` bean exists, instantiate one locally:

```java
package com.service.impl.storage;

import com.alibaba.fastjson.JSON;
import com.dto.storage.StorageAgentParseRequest;
import com.dto.storage.StorageAgentParseResult;
import com.entity.storage.StorageAgentRequestEntity;
import com.service.storage.StorageAgentFacadeService;
import com.service.storage.StorageAgentRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("storageAgentFacadeService")
public class StorageAgentFacadeServiceImpl implements StorageAgentFacadeService {
    private static final String AGENT_URL = "http://localhost:8090/api/agents/storage/parse";

    @Autowired
    private StorageAgentRequestService storageAgentRequestService;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public StorageAgentParseResult parse(String text) {
        StorageAgentParseRequest request = new StorageAgentParseRequest();
        request.setText(text);
        StorageAgentParseResult result = restTemplate.postForObject(AGENT_URL, request, StorageAgentParseResult.class);

        StorageAgentRequestEntity row = new StorageAgentRequestEntity();
        row.setRawInput(text);
        row.setIntent(result == null ? "UNKNOWN" : result.getIntent());
        row.setParsedJson(JSON.toJSONString(result));
        row.setMissingFields(result == null ? "" : JSON.toJSONString(result.getMissingFields()));
        row.setConfidence(result == null ? 0.0 : result.getConfidence());
        row.setNeedConfirm(result == null || Boolean.TRUE.equals(result.getNeedConfirm()) ? 1 : 0);
        storageAgentRequestService.insert(row);

        return result;
    }
}
```

- [ ] **Step 3: Add controller**

```java
package com.controller.storage;

import com.dto.storage.StorageAgentParseRequest;
import com.service.storage.StorageAgentFacadeService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage-agent")
public class StorageAgentController {
    @Autowired
    private StorageAgentFacadeService storageAgentFacadeService;

    @RequestMapping("/parse")
    public R parse(@RequestBody StorageAgentParseRequest request) {
        return R.ok().put("data", storageAgentFacadeService.parse(request.getText()));
    }
}
```

- [ ] **Step 4: Manual verification**

Start Agent:

```powershell
cd agent-service
uvicorn app.main:app --reload --port 8090
```

Start Spring Boot, then run:

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-agent/parse -Method Post -ContentType 'application/json' -Body '{"text":"CDM 椤哄簭璇绘渶楂橀€熺巼鏄摢涓牱鍝?鍝釜鐗堟湰"}'
```

Expected: response `data.intent` is `QUERY_RESULT`.

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/dto/storage src/main/java/com/service/storage/StorageAgentFacadeService.java src/main/java/com/service/impl/storage/StorageAgentFacadeServiceImpl.java src/main/java/com/controller/storage/StorageAgentController.java
git commit -m "feat: route storage agent intents"
```

---

### Task 5: Test Task Creation And Mock Runner

**Files:**
- Create: `src/main/java/com/dto/storage/CreateStorageTaskRequest.java`
- Create: `src/main/java/com/service/storage/StorageMockRunnerService.java`
- Create: `src/main/java/com/service/impl/storage/StorageMockRunnerServiceImpl.java`
- Create: `src/main/java/com/controller/storage/StorageTestTaskController.java`
- Create: `src/test/java/com/storage/StorageMockRunnerServiceTest.java`

**Interfaces:**
- Produces:
  - `POST /storage-tasks/create`
  - `POST /storage-tasks/{id}/run`
  - `GET /storage-tasks/{id}/results`
- Consumes storage sample/node/case services.
- Produces result rows in `storage_test_result`.

- [ ] **Step 1: Create request DTO**

```java
package com.dto.storage;

import java.util.List;

public class CreateStorageTaskRequest {
    private String taskName;
    private String rawUserInput;
    private String projectName;
    private String targetVersion;
    private Long sampleId;
    private Long nodeId;
    private List<String> testSuites;
    private List<String> scenes;

    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
    public String getRawUserInput() { return rawUserInput; }
    public void setRawUserInput(String rawUserInput) { this.rawUserInput = rawUserInput; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getTargetVersion() { return targetVersion; }
    public void setTargetVersion(String targetVersion) { this.targetVersion = targetVersion; }
    public Long getSampleId() { return sampleId; }
    public void setSampleId(Long sampleId) { this.sampleId = sampleId; }
    public Long getNodeId() { return nodeId; }
    public void setNodeId(Long nodeId) { this.nodeId = nodeId; }
    public List<String> getTestSuites() { return testSuites; }
    public void setTestSuites(List<String> testSuites) { this.testSuites = testSuites; }
    public List<String> getScenes() { return scenes; }
    public void setScenes(List<String> scenes) { this.scenes = scenes; }
}
```

- [ ] **Step 2: Implement node availability validation**

In `StorageMockRunnerServiceImpl`, before running:

```java
private void validateNode(StorageTestNodeEntity node) {
    if (node == null) {
        throw new IllegalArgumentException("鑺傜偣涓嶅瓨鍦?);
    }
    if (!"IDLE".equals(node.getNodeStatus())) {
        throw new IllegalArgumentException("鑺傜偣涓嶅彲鐢? 褰撳墠鐘舵€佷负 " + node.getNodeStatus());
    }
    if (!"CONNECTED".equals(node.getPhoneStatus())) {
        throw new IllegalArgumentException("鑺傜偣涓嶅彲鐢? 鎵嬫満鏈繛鎺?);
    }
    if (!"DEVICE".equals(node.getAdbState())) {
        throw new IllegalArgumentException("鑺傜偣涓嶅彲鐢? ADB 鐘舵€佷负 " + node.getAdbState());
    }
}
```

- [ ] **Step 3: Implement deterministic mock values**

Use deterministic values so demo and tests are stable:

```java
private BigDecimal mockValue(String suite, String scene, String metricName, int round) {
    int seed = Math.abs((suite + scene + metricName).hashCode());
    BigDecimal base = new BigDecimal(50 + seed % 250);
    if ("CDM".equals(suite) && metricName.contains("SEQ R")) {
        base = new BigDecimal("330");
    }
    if ("dirty".equals(scene)) {
        base = base.multiply(new BigDecimal("0.88"));
    }
    return base.add(new BigDecimal(round * 2)).setScale(3, BigDecimal.ROUND_HALF_UP);
}
```

- [ ] **Step 4: Implement task run**

`runTask(Long taskId)` must:

1. Load task and node.
2. Validate node.
3. Set task status `RUNNING`.
4. Query enabled cases where suite and scene match task fields.
5. Insert one `storage_test_result` per matched case.
6. Set task status `COMPLETED`.
7. On exception set task status `FAILED` and save error message.

- [ ] **Step 5: Add controller**

Controller methods:

```java
@RequestMapping("/create")
public R create(@RequestBody CreateStorageTaskRequest request)

@RequestMapping("/{id}/run")
public R run(@PathVariable("id") Long id)

@RequestMapping("/{id}/results")
public R results(@PathVariable("id") Long id)
```

Expected JSON response from run:

```json
{
  "code": 0,
  "msg": "success",
  "data": {
    "taskId": 1,
    "status": "COMPLETED"
  }
}
```

- [ ] **Step 6: Verify manually**

Run:

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/create -Method Post -ContentType 'application/json' -Body '{"taskName":"Project-A FW-v2 CDM FIO","projectName":"Project-A","targetVersion":"FW-v2","sampleId":2,"nodeId":3,"testSuites":["CDM","FIO"],"scenes":["clean","dirty"]}'
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/run -Method Post
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-tasks/1/results -Method Get
```

Expected: result list contains CDM and FIO rows with average values.

- [ ] **Step 7: Commit**

```bash
git add src/main/java/com/dto/storage/CreateStorageTaskRequest.java src/main/java/com/service/storage/StorageMockRunnerService.java src/main/java/com/service/impl/storage/StorageMockRunnerServiceImpl.java src/main/java/com/controller/storage/StorageTestTaskController.java
git commit -m "feat: add storage mock task execution"
```

---

### Task 6: Metric Query API For Data Q&A

**Files:**
- Create: `src/main/java/com/dto/storage/MetricQueryRequest.java`
- Create: `src/main/java/com/dto/storage/MetricQueryResult.java`
- Create: `src/main/java/com/service/storage/StorageMetricQueryService.java`
- Create: `src/main/java/com/service/impl/storage/StorageMetricQueryServiceImpl.java`
- Create: `src/main/java/com/controller/storage/StorageMetricQueryController.java`

**Interfaces:**
- Produces: `POST /storage-metrics/query`
- Consumes: `storage_test_result`, `storage_sample`
- Answers: "CDM 椤哄簭璇绘渶楂橀€熺巼鏄摢涓牱鍝併€佸摢涓増鏈?

- [ ] **Step 1: Create DTOs**

```java
package com.dto.storage;

public class MetricQueryRequest {
    private String suite;
    private String metricName;
    private String aggregation;
    private Integer rankLimit;
    public String getSuite() { return suite; }
    public void setSuite(String suite) { this.suite = suite; }
    public String getMetricName() { return metricName; }
    public void setMetricName(String metricName) { this.metricName = metricName; }
    public String getAggregation() { return aggregation; }
    public void setAggregation(String aggregation) { this.aggregation = aggregation; }
    public Integer getRankLimit() { return rankLimit; }
    public void setRankLimit(Integer rankLimit) { this.rankLimit = rankLimit; }
}
```

```java
package com.dto.storage;

import java.math.BigDecimal;

public class MetricQueryResult {
    private Long sampleId;
    private String sampleCode;
    private String projectName;
    private String fwVersion;
    private String scene;
    private String suite;
    private String metricName;
    private BigDecimal averageValue;
    private String unit;
    private String answer;
    // Generate getters and setters for every field.
}
```

- [ ] **Step 2: Implement query service**

Service behavior:

1. Default `rankLimit` to `1`.
2. Query result rows by `suite` and `metricName`.
3. Sort by `averageValue` descending for `MAX`.
4. Join sample by `sampleId`.
5. Build answer in Chinese.

Answer format:

```text
CDM SEQ R 1M Q8T1 鏈€楂樼殑鏄牱鍝?Project-A-Flash-X-256G-FW-v2锛岀増鏈?FW-v2锛屽湪 clean 鍦烘櫙涓嬪钩鍧囧€间负 340.460 MB/s銆?```

- [ ] **Step 3: Add controller**

```java
package com.controller.storage;

import com.dto.storage.MetricQueryRequest;
import com.service.storage.StorageMetricQueryService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/storage-metrics")
public class StorageMetricQueryController {
    @Autowired
    private StorageMetricQueryService storageMetricQueryService;

    @RequestMapping("/query")
    public R query(@RequestBody MetricQueryRequest request) {
        return R.ok().put("data", storageMetricQueryService.query(request));
    }
}
```

- [ ] **Step 4: Verify**

After Task 5 has generated results:

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-metrics/query -Method Post -ContentType 'application/json' -Body '{"suite":"CDM","metricName":"SEQ R 1M Q8T1","aggregation":"MAX","rankLimit":1}'
```

Expected: response contains `answer` with sample code and version.

- [ ] **Step 5: Commit**

```bash
git add src/main/java/com/dto/storage/MetricQueryRequest.java src/main/java/com/dto/storage/MetricQueryResult.java src/main/java/com/service/storage/StorageMetricQueryService.java src/main/java/com/service/impl/storage/StorageMetricQueryServiceImpl.java src/main/java/com/controller/storage/StorageMetricQueryController.java
git commit -m "feat: add storage metric query"
```

---

### Task 7: Report Dataset And Excel Export

**Files:**
- Create: `src/main/java/com/dto/storage/CreateStorageReportRequest.java`
- Create: `src/main/java/com/service/storage/StorageReportGeneratorService.java`
- Create: `src/main/java/com/service/impl/storage/StorageReportGeneratorServiceImpl.java`
- Create: `src/main/java/com/controller/storage/StorageReportController.java`
- Create: `src/main/resources/templates/storage_performance_report_template.xlsx`

**Interfaces:**
- Produces:
  - `POST /storage-reports/create`
  - `POST /storage-reports/{id}/generate`
  - `GET /storage-reports/{id}`
  - `GET /storage-reports/{id}/download`

- [ ] **Step 1: Create simple template**

Create `src/main/resources/templates/storage_performance_report_template.xlsx` manually with one sheet named `鎬ц兘鎶ュ憡`.

Required cells:

- `A1`: `AI 瀛樺偍鎬ц兘娴嬭瘯鎶ュ憡`
- `A3`: `鎶ュ憡鍚嶇О`
- `B3`: blank
- `A4`: `鎶ュ憡绫诲瀷`
- `B4`: blank
- `A6`: `鏁版嵁闆哷
- `A10`: `娴嬭瘯濂椾欢`
- `B10`: `鍦烘櫙`
- `C10`: `鎸囨爣`
- `D10`: `鐩爣骞冲潎鍊糮
- `E10`: `鍩哄噯骞冲潎鍊糮
- `F10`: `宸紓`
- `G10`: `鐘舵€乣
- `A30`: `寮傚父椤筦
- `A45`: `浜哄伐澶囨敞`
- `A55`: `鎬荤粨`

- [ ] **Step 2: Create report request DTO**

```java
package com.dto.storage;

import java.util.List;

public class CreateStorageReportRequest {
    private String reportName;
    private String reportType;
    private List<Long> sampleIds;
    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public List<Long> getSampleIds() { return sampleIds; }
    public void setSampleIds(List<Long> sampleIds) { this.sampleIds = sampleIds; }
}
```

- [ ] **Step 3: Implement report generation rules**

`StorageReportGeneratorServiceImpl.generate(Long reportId)` must:

1. Load report.
2. Load datasets.
3. Treat first dataset as target.
4. Treat second dataset as baseline when present.
5. Query target and baseline results by suite/scene/metric.
6. Calculate delta.
7. Mark `FAIL` for drop <= `-20%`, `WARNING` for drop <= `-10%`, otherwise `PASS`.
8. Use `N/A` when baseline missing or zero.
9. Save `preview_json`.
10. Fill Excel template and save under `upload/reports/storage-report-{id}.xlsx`.

- [ ] **Step 4: Implement controller**

Endpoints:

```java
@RequestMapping("/create")
public R create(@RequestBody CreateStorageReportRequest request)

@RequestMapping("/{id}/generate")
public R generate(@PathVariable("id") Long id)

@RequestMapping("/{id}")
public R info(@PathVariable("id") Long id)

@RequestMapping("/{id}/download")
public void download(@PathVariable("id") Long id, HttpServletResponse response)
```

- [ ] **Step 5: Verify**

```powershell
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/create -Method Post -ContentType 'application/json' -Body '{"reportName":"Project-A FW-v2 瀵规瘮鎶ュ憡","reportType":"COMPARISON","sampleIds":[2,1,3]}'
Invoke-RestMethod -Uri http://localhost:8080/springbootarkc6v1u/storage-reports/1/generate -Method Post
```

Expected:

- `storage_test_report.report_status` becomes `COMPLETED`.
- `upload/reports/storage-report-1.xlsx` exists.
- Preview JSON contains target and baseline values.

- [ ] **Step 6: Commit**

```bash
git add src/main/java/com/dto/storage/CreateStorageReportRequest.java src/main/java/com/service/storage/StorageReportGeneratorService.java src/main/java/com/service/impl/storage/StorageReportGeneratorServiceImpl.java src/main/java/com/controller/storage/StorageReportController.java src/main/resources/templates/storage_performance_report_template.xlsx
git commit -m "feat: add storage report export"
```

---

### Task 8: Demo Script And Verification Pack

**Files:**
- Create: `docs/demo/AI-Storage-TestOps-Demo-Script.md`
- Modify: `docs/interview/AI-Storage-TestOps-Interview-Story.md`

**Interfaces:**
- Produces a repeatable interview demo script.
- Produces expected commands and expected outputs.

- [ ] **Step 1: Create demo script**

Create `docs/demo/AI-Storage-TestOps-Demo-Script.md` with:

```markdown
# AI 瀛樺偍鎬ц兘 TestOps Demo 鑴氭湰

## 婕旂ず A锛氳嚜鐒惰瑷€鍒涘缓娴嬭瘯浠诲姟

杈撳叆锛?
> Node-3 涓婃寕浜?Project-A Flash-X 256G FW-v2锛岃窇 CDM銆丄S SSD銆丗IO clean dirty銆?
棰勬湡锛?
- Agent 璇嗗埆 `CREATE_TEST_TASK`
- 鎻愬彇椤圭洰銆佺増鏈€佽妭鐐广€佹祴璇曞浠躲€佸満鏅?- 骞冲彴妫€鏌?Node-3锛氳妭鐐圭┖闂层€佹墜鏈哄凡杩炴帴銆丄DB 涓?DEVICE
- 鍒涘缓浠诲姟
- Mock Runner 鐢熸垚缁撴灉

## 婕旂ず B锛氭櫤鑳芥祴璇曟暟鎹棶绛?
杈撳叆锛?
> CDM 椤哄簭璇绘渶楂橀€熺巼鏄摢涓牱鍝侊紝鍝釜鐗堟湰涓嬬殑锛?
棰勬湡锛?
- Agent 璇嗗埆 `QUERY_RESULT`
- 骞冲彴鏌ヨ `storage_test_result`
- 杩斿洖鏈€楂樺€煎搴旀牱鍝併€佺増鏈€佸満鏅拰鎸囨爣鍊?
## 婕旂ず C锛氱敓鎴愬姣旀姤鍛?
杈撳叆锛?
> 鐢熸垚 Project-A FW-v2銆乂2.0.3 鍜岀珵鍝?Competitor-X 鐨?CDM銆丄S SSD銆丗IO 瀵规瘮鎶ュ憡銆?
棰勬湡锛?
- Agent 璇嗗埆 `CREATE_REPORT`
- 骞冲彴鍒涘缓鎶ュ憡鏁版嵁闆?- 鎶ュ憡鐢熸垚鍣ㄨ绠楀潎鍊笺€佸樊寮傚拰寮傚父椤?- 瀵煎嚭 Excel
```

- [ ] **Step 2: Verify full flow**

Run:

```powershell
cd agent-service
uvicorn app.main:app --reload --port 8090
```

In another terminal:

```powershell
mvn spring-boot:run
```

Then manually execute:

1. Agent parse task.
2. Create storage task.
3. Run mock task.
4. Query metric.
5. Create report.
6. Generate report.
7. Download Excel.

Expected: every endpoint returns `code: 0` or successful FastAPI JSON.

- [ ] **Step 3: Commit**

```bash
git add docs/demo/AI-Storage-TestOps-Demo-Script.md docs/interview/AI-Storage-TestOps-Interview-Story.md
git commit -m "docs: add storage testops demo script"
```

---

## Self-Review Notes

- Spec coverage: tasks cover schema, CRUD, Agent parse, task execution, metric Q&A, report export, and demo packaging.
- Deferred by design: real ADB execution, full RAG, LangGraph orchestration, production message queue, and complete frontend pages.
- Type consistency: Agent intent values are `CREATE_TEST_TASK`, `CREATE_REPORT`, and `QUERY_RESULT`; Java DTOs and Python schemas use the same names.
- Risk: generated Java entities need all getters/setters; missing accessors will fail compilation.
- Risk: `git` was unavailable in the current desktop environment; commit steps are included for normal environments but may need to be skipped locally until Git is installed.

