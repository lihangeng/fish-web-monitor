package com.yihuacomputer.fish.web.version.form;

import java.util.Date;


public class JobForm {

	private long id;

	private String jobName;

	private long versionId = 1;

	private String versionName;

	private Date planTime;

	private String taskType;

	private String jobStatus;

	private String deviceIds;

	private String versionFile;

	private String deployStartDate;

	private String deployEndDate;
	
	private int downLoadCounter;
	
	private boolean allDevice;

	public boolean isAllDevice() {
		return allDevice;
	}

	public void setAllDevice(boolean allDevice) {
		this.allDevice = allDevice;
	}

	public JobForm() {
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public long getVersionId() {
		return versionId;
	}

	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionFile() {
		return versionFile;
	}

	public void setVersionFile(String versionFile) {
		this.versionFile = versionFile;
	}

	public String getDeployStartDate() {
		return deployStartDate;
	}

	public void setDeployStartDate(String deployStartDate) {
		this.deployStartDate = deployStartDate;
	}

	public String getDeployEndDate() {
		return deployEndDate;
	}

	public void setDeployEndDate(String deployEndDate) {
		this.deployEndDate = deployEndDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getDownLoadCounter() {
		return downLoadCounter;
	}

	public void setDownLoadCounter(int downLoadCounter) {
		this.downLoadCounter = downLoadCounter;
	}

}
