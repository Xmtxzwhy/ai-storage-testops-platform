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
