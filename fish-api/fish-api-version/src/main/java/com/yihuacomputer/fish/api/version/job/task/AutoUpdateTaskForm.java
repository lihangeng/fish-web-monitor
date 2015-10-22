package com.yihuacomputer.fish.api.version.job.task;

public class AutoUpdateTaskForm {

	private long id;
	//版本类型
	private String versionType;

	private String terminalId;

	private String orgName;

	private String deviceIp;
	
	/**
	 *	执行机器 
	 */
	private String excuteMachine;
	
	/**
	 * 下载源
	 */
	private String downSource;
	
	/**
	 * 设备型号
	 */
	private String atmType;

	/**
	 * 任务状态
	 */
	private String taskStatus;
	
	/**
	 * 预期版本号
	 */
	private String version;
	
	/**
	 * 自动更新时间
	 */
	private String excuteTime;
	
	/**
	 * 版本更新前的版本号
	 */
	private String versionBeforeUpdate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
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

	public String getDeviceIp() {
		return deviceIp;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
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

	public String getAtmType() {
		return atmType;
	}

	public void setAtmType(String atmType) {
		this.atmType = atmType;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(String excuteTime) {
		this.excuteTime = excuteTime;
	}

	public String getVersionBeforeUpdate() {
		return versionBeforeUpdate;
	}

	public void setVersionBeforeUpdate(String versionBeforeUpdate) {
		this.versionBeforeUpdate = versionBeforeUpdate;
	}

}
