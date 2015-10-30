package com.yihuacomputer.fish.web.version.form;

public class TaskForm {
	private long id;

	private long deviceId;

	private String terminalId;

	private String orgName;

	private String deviceIp;

	private String excuteTime;

	private String taskStatus;

	private boolean success;

	private String reason;

	private String jobName;

	private String version;

	private String state;

	private String versionBeforeUpdate;

	private String exceptVersion;

	private String currentVersion;

	private String planTime;
	private String excuteMachine;
	private String downSource;

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

	public String getJobName() {
		return jobName;
	}

	public void setJobId(String jobName) {
		this.jobName = jobName;
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

	public String getPlanTime() {
		return planTime;
	}

	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getExcuteMachine() {
		return excuteMachine;
	}

	public void setExcuteMachine(String excuteMachine) {
		this.excuteMachine = excuteMachine;
	}

	public String getDownSource() {
		return downSource;
	}

	public void setDownSource(String downSource) {
		this.downSource = downSource;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

}
