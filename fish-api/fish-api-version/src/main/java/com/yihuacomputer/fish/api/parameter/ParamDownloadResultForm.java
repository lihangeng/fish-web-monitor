package com.yihuacomputer.fish.api.parameter;

/**
 * @author YiHua
 *
 */
public class ParamDownloadResultForm {
	private long id;
	
	private long deviceId;
	
	private String terminalId;
	
	private long versionNo;
	
	private String downloadStartTime;
	
	private String downloadFinishTime;
	
	private boolean success;
	
	private String reason;
	
	private String taskStatus;

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

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
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

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	
	public ParamDownloadResultForm(){
		
	}
	
}
