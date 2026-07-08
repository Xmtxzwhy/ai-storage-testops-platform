from app.services.storage_agent_parser import StorageAgentParser


def test_parse_create_task_with_node_and_suites():
    parser = StorageAgentParser()
    result = parser.parse("Node-3 Project-A FW-v2 CDM AS SSD FIO clean dirty")
    assert result.intent == "CREATE_TEST_TASK"
    assert result.projectName == "Project-A"
    assert result.targetVersion == "FW-v2"
    assert result.nodeCode == "Node-3"
    assert result.testSuites == ["CDM", "AS_SSD", "FIO"]
    assert result.scenes == ["clean", "dirty"]
    assert result.missingFields == []


def test_parse_create_task_with_chinese_node_keyword():
    parser = StorageAgentParser()
    result = parser.parse("节点 3 挂了 Project-A Flash-X 256G FW-v2，跑 CDM clean")
    assert result.intent == "CREATE_TEST_TASK"
    assert result.nodeCode == "Node-3"
    assert result.projectName == "Project-A"
    assert result.particle == "Flash-X"
    assert result.capacity == "256G"
    assert result.missingFields == []


def test_parse_create_task_without_suites_or_scenes_still_valid():
    parser = StorageAgentParser()
    result = parser.parse("Node-3 Project-A FW-v2")
    assert result.intent == "CREATE_TEST_TASK"
    assert result.projectName == "Project-A"
    assert result.targetVersion == "FW-v2"
    assert result.nodeCode == "Node-3"
    assert result.missingFields == []


def test_parse_report_with_baseline_and_competitor():
    parser = StorageAgentParser()
    result = parser.parse("Project-A FW-v2 compare FW-v1 competitor Competitor-X CDM FIO report")
    assert result.intent == "CREATE_REPORT"
    assert result.projectName == "Project-A"
    assert result.targetVersion == "FW-v2"
    assert result.baselineVersion == "FW-v1"
    assert result.competitor == "Competitor-X"
    assert result.testSuites == ["CDM", "FIO"]
    assert result.reportTypes == ["single", "comparison"]


def test_parse_chinese_report_with_baseline_and_competitor():
    parser = StorageAgentParser()
    result = parser.parse("生成 Project-A FW-v2、FW-v1 和竞品 Competitor-X 的 CDM、AS SSD、FIO 对比报告")
    assert result.intent == "CREATE_REPORT"
    assert result.projectName == "Project-A"
    assert result.targetVersion == "FW-v2"
    assert result.baselineVersion == "FW-v1"
    assert result.competitor == "Competitor-X"
    assert result.testSuites == ["CDM", "AS_SSD", "FIO"]
    assert result.reportTypes == ["single", "comparison"]


def test_parse_report_without_baseline_does_not_require_baseline_version():
    parser = StorageAgentParser()
    result = parser.parse("Project-A FW-v2 report")
    assert result.intent == "CREATE_REPORT"
    assert result.projectName == "Project-A"
    assert result.targetVersion == "FW-v2"
    assert "baselineVersion" not in result.missingFields


def test_parse_metric_query():
    parser = StorageAgentParser()
    result = parser.parse("CDM seq read top speed by sample and version")
    assert result.intent == "QUERY_RESULT"
    assert result.suite == "CDM"
    assert result.metricName == "SEQ R 1M Q8T1"
    assert result.aggregation == "MAX"
    assert result.rankLimit == 1
    assert result.dimensions == ["sample", "version", "scene"]
    assert result.needConfirm is False
