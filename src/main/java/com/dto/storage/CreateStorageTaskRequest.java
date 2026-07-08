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
