package com.entity.storage;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("storage_report_dataset")
public class StorageReportDatasetEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long reportId;
    private String datasetRole;
    private String label;
    private String projectName;
    private String fwVersion;
    private String soc;
    private String particle;
    private String capacity;
    private Long sampleId;
    private String queryFiltersJson;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getReportId() { return reportId; }
    public void setReportId(Long reportId) { this.reportId = reportId; }
    public String getDatasetRole() { return datasetRole; }
    public void setDatasetRole(String datasetRole) { this.datasetRole = datasetRole; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
    public String getFwVersion() { return fwVersion; }
    public void setFwVersion(String fwVersion) { this.fwVersion = fwVersion; }
    public String getSoc() { return soc; }
    public void setSoc(String soc) { this.soc = soc; }
    public String getParticle() { return particle; }
    public void setParticle(String particle) { this.particle = particle; }
    public String getCapacity() { return capacity; }
    public void setCapacity(String capacity) { this.capacity = capacity; }
    public Long getSampleId() { return sampleId; }
    public void setSampleId(Long sampleId) { this.sampleId = sampleId; }
    public String getQueryFiltersJson() { return queryFiltersJson; }
    public void setQueryFiltersJson(String queryFiltersJson) { this.queryFiltersJson = queryFiltersJson; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
