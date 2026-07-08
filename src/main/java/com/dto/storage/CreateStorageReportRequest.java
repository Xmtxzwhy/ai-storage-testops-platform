package com.dto.storage;

import java.util.List;

public class CreateStorageReportRequest {
    private String reportName;
    private String reportType;
    private List<Long> sampleIds;

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public List<Long> getSampleIds() {
        return sampleIds;
    }

    public void setSampleIds(List<Long> sampleIds) {
        this.sampleIds = sampleIds;
    }
}
