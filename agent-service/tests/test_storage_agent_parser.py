from app.services.storage_agent_parser import StorageAgentParser


def test_parse_create_task_with_node_and_suites():
    parser = StorageAgentParser()
    result = parser.parse("Node-3 WM6000 V2.0.4 CDM AS SSD FIO clean dirty")
    assert result.intent == "CREATE_TEST_TASK"
    assert result.projectName == "WM6000"
    assert result.targetVersion == "V2.0.4"
    assert result.nodeCode == "Node-3"
    assert result.testSuites == ["CDM", "AS_SSD", "FIO"]
    assert result.scenes == ["clean", "dirty"]
    assert result.missingFields == []


def test_parse_create_task_with_chinese_node_keyword():
    parser = StorageAgentParser()
    result = parser.parse("节点 3 上挂了 WM6000 N38B 256G V2.0.4，跑 CDM clean")
    assert result.intent == "CREATE_TEST_TASK"
    assert result.nodeCode == "Node-3"
    assert result.missingFields == []


def test_parse_create_task_without_suites_or_scenes_still_valid():
    parser = StorageAgentParser()
    result = parser.parse("Node-3 WM6000 V2.0.4")
    assert result.intent == "CREATE_TEST_TASK"
    assert result.projectName == "WM6000"
    assert result.targetVersion == "V2.0.4"
    assert result.nodeCode == "Node-3"
    assert result.missingFields == []


def test_parse_report_with_baseline_and_competitor():
    parser = StorageAgentParser()
    result = parser.parse("WM6000 V2.0.4 对比 V2.0.3 competitor 2730AB CDM FIO 报告")
    assert result.intent == "CREATE_REPORT"
    assert result.projectName == "WM6000"
    assert result.targetVersion == "V2.0.4"
    assert result.baselineVersion == "V2.0.3"
    assert result.competitor == "2730AB"
    assert result.testSuites == ["CDM", "FIO"]
    assert result.reportTypes == ["single", "comparison"]


def test_parse_chinese_report_with_baseline_and_competitor():
    parser = StorageAgentParser()
    result = parser.parse("生成 WM6000 V2.0.4、V2.0.3 和竞品 2730AB 的 CDM、AS SSD、FIO 对比报告")
    assert result.intent == "CREATE_REPORT"
    assert result.projectName == "WM6000"
    assert result.targetVersion == "V2.0.4"
    assert result.baselineVersion == "V2.0.3"
    assert result.competitor == "2730AB"
    assert result.testSuites == ["CDM", "AS_SSD", "FIO"]
    assert result.reportTypes == ["single", "comparison"]


def test_parse_report_without_baseline_does_not_require_baseline_version():
    parser = StorageAgentParser()
    result = parser.parse("WM6000 V2.0.4 report")
    assert result.intent == "CREATE_REPORT"
    assert result.projectName == "WM6000"
    assert result.targetVersion == "V2.0.4"
    assert "baselineVersion" not in result.missingFields


def test_parse_metric_query():
    parser = StorageAgentParser()
    result = parser.parse("CDM 顺序读最高速率是哪个样品，哪个版本下的？")
    assert result.intent == "QUERY_RESULT"
    assert result.suite == "CDM"
    assert result.metricName == "SEQ R 1M Q8T1"
    assert result.aggregation == "MAX"
    assert result.rankLimit == 1
    assert result.dimensions == ["sample", "version", "scene"]
    assert result.needConfirm is False
