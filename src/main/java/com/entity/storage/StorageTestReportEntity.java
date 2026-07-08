package com.entity.storage;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("storage_test_report")
public class StorageTestReportEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String reportName;
    private String reportType;
    private String reportStatus;
    private String previewJson;
    private String excelFilePath;
    private String summary;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date generatedTime;

    private String errorMessage;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReportName() { return reportName; }
    public void setReportName(String reportName) { this.reportName = reportName; }
    public String getReportType() { return reportType; }
    public void setReportType(String reportType) { this.reportType = reportType; }
    public String getReportStatus() { return reportStatus; }
    public void setReportStatus(String reportStatus) { this.reportStatus = reportStatus; }
    public String getPreviewJson() { return previewJson; }
    public void setPreviewJson(String previewJson) { this.previewJson = previewJson; }
    public String getExcelFilePath() { return excelFilePath; }
    public void setExcelFilePath(String excelFilePath) { this.excelFilePath = excelFilePath; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public Date getGeneratedTime() { return generatedTime; }
    public void setGeneratedTime(Date generatedTime) { this.generatedTime = generatedTime; }
    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
