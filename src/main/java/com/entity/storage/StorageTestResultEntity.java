package com.entity.storage;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@TableName("storage_test_result")
public class StorageTestResultEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String sourceType;
    private Long taskId;
    private Long sampleId;
    private Long nodeId;
    private String suite;
    private String scene;
    private String metricName;
    private BigDecimal round1Value;
    private BigDecimal round2Value;
    private BigDecimal round3Value;
    private BigDecimal averageValue;
    private String unit;
    private String resultStatus;
    private String logSummary;
    private String errorReason;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date executedTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public Long getSampleId() { return sampleId; }
    public void setSampleId(Long sampleId) { this.sampleId = sampleId; }
    public Long getNodeId() { return nodeId; }
    public void setNodeId(Long nodeId) { this.nodeId = nodeId; }
    public String getSuite() { return suite; }
    public void setSuite(String suite) { this.suite = suite; }
    public String getScene() { return scene; }
    public void setScene(String scene) { this.scene = scene; }
    public String getMetricName() { return metricName; }
    public void setMetricName(String metricName) { this.metricName = metricName; }
    public BigDecimal getRound1Value() { return round1Value; }
    public void setRound1Value(BigDecimal round1Value) { this.round1Value = round1Value; }
    public BigDecimal getRound2Value() { return round2Value; }
    public void setRound2Value(BigDecimal round2Value) { this.round2Value = round2Value; }
    public BigDecimal getRound3Value() { return round3Value; }
    public void setRound3Value(BigDecimal round3Value) { this.round3Value = round3Value; }
    public BigDecimal getAverageValue() { return averageValue; }
    public void setAverageValue(BigDecimal averageValue) { this.averageValue = averageValue; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public String getResultStatus() { return resultStatus; }
    public void setResultStatus(String resultStatus) { this.resultStatus = resultStatus; }
    public String getLogSummary() { return logSummary; }
    public void setLogSummary(String logSummary) { this.logSummary = logSummary; }
    public String getErrorReason() { return errorReason; }
    public void setErrorReason(String errorReason) { this.errorReason = errorReason; }
    public Date getExecutedTime() { return executedTime; }
    public void setExecutedTime(Date executedTime) { this.executedTime = executedTime; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
