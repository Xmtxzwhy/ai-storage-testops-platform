package com.entity.storage;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@TableName("storage_test_node")
public class StorageTestNodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String nodeCode;
    private String nodeName;
    private String ipAddress;
    private String nodeStatus;
    private String phoneStatus;
    private String adbState;
    private String deviceSerial;
    private Long currentSampleId;
    private String capabilities;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date lastHeartbeatTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date lastAdbCheckTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private Date addtime;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNodeCode() { return nodeCode; }
    public void setNodeCode(String nodeCode) { this.nodeCode = nodeCode; }
    public String getNodeName() { return nodeName; }
    public void setNodeName(String nodeName) { this.nodeName = nodeName; }
    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public String getNodeStatus() { return nodeStatus; }
    public void setNodeStatus(String nodeStatus) { this.nodeStatus = nodeStatus; }
    public String getPhoneStatus() { return phoneStatus; }
    public void setPhoneStatus(String phoneStatus) { this.phoneStatus = phoneStatus; }
    public String getAdbState() { return adbState; }
    public void setAdbState(String adbState) { this.adbState = adbState; }
    public String getDeviceSerial() { return deviceSerial; }
    public void setDeviceSerial(String deviceSerial) { this.deviceSerial = deviceSerial; }
    public Long getCurrentSampleId() { return currentSampleId; }
    public void setCurrentSampleId(Long currentSampleId) { this.currentSampleId = currentSampleId; }
    public String getCapabilities() { return capabilities; }
    public void setCapabilities(String capabilities) { this.capabilities = capabilities; }
    public Date getLastHeartbeatTime() { return lastHeartbeatTime; }
    public void setLastHeartbeatTime(Date lastHeartbeatTime) { this.lastHeartbeatTime = lastHeartbeatTime; }
    public Date getLastAdbCheckTime() { return lastAdbCheckTime; }
    public void setLastAdbCheckTime(Date lastAdbCheckTime) { this.lastAdbCheckTime = lastAdbCheckTime; }
    public Date getAddtime() { return addtime; }
    public void setAddtime(Date addtime) { this.addtime = addtime; }
}
