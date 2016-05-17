package com.yihuacomputer.fish.web.version.form;


public class TaskForm {
    private long id;

    private long deviceId;

    private String terminalId;

    private String orgName;

    private String deviceIp;

    private String excuteTime;

    private String taskStatus;

    private String taskStatusText;

    private boolean success;

    private String reason;

    private long jobId;

    private String version;

    private String state;

    private String versionBeforeUpdate;

    private String exceptVersion;

    private String currentVersion;

    private double process;

    private String downloadStartTime ;

    private String downloadFinishTime ;

    public TaskForm() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(String excuteTime) {
        this.excuteTime = excuteTime;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getVersionBeforeUpdate() {
        return versionBeforeUpdate;
    }

    public void setVersionBeforeUpdate(String versionBeforeUpdate) {
        this.versionBeforeUpdate = versionBeforeUpdate;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getExceptVersion() {
        return exceptVersion;
    }

    public void setExceptVersion(String exceptVersion) {
        this.exceptVersion = exceptVersion;
    }

    public String getTaskStatusText() {
        return taskStatusText;
    }

    public void setTaskStatusText(String taskStatusText) {
        this.taskStatusText = taskStatusText;
    }

    public double getProcess() {
        return process;
    }

    public void setProcess(double process) {
        this.process = process;
    }

	public String getDownloadStartTime() {
		return downloadStartTime;
	}

	public void setDownloadStartTime(String downloadStartTime) {
		this.downloadStartTime = downloadStartTime;
	}

	public String getDownloadFinishTime() {
		return downloadFinishTime;
	}

	public void setDownloadFinishTime(String downloadFinishTime) {
		this.downloadFinishTime = downloadFinishTime;
	}

}
