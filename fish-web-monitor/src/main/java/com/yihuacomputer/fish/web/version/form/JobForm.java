package com.yihuacomputer.fish.web.version.form;

import java.util.Date;

import com.yihuacomputer.fish.api.version.job.JobPriority;
import com.yihuacomputer.fish.api.version.job.JobStatus;
import com.yihuacomputer.fish.api.version.job.JobType;

public class JobForm {
	private long id;

	private String jobName;

	private long versionId = 1;

	private String versionName;

	private Date planTime;

	private JobType jobType;

	private JobStatus jobStatus;

	private JobPriority jobPriority;

	private String desc;

	private String deviceIds;

	private String versionFile;

	private boolean eagerRestart;

	private String extraBody;

	private String deployStartDate;

	private String deployEndDate;

	private String versionCatalog;

	private String userName;

	private boolean selectAll;

	private boolean cancelPreVersion;

	private boolean rebootUpdate;

	private int finishTaskCount;

	private int failTaskCount;

	private int runTaskCount;

	private int allTaskCount;

	public JobForm() {
	}

	

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(String deviceIds) {
		this.deviceIds = deviceIds;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public JobStatus getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(JobStatus jobStatus) {
		this.jobStatus = jobStatus;
	}

	public JobPriority getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(JobPriority jobPriority) {
		this.jobPriority = jobPriority;
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

	public boolean isEagerRestart() {
		return eagerRestart;
	}

	public void setEagerRestart(boolean eagerRestart) {
		this.eagerRestart = eagerRestart;
	}

	public String getExtraBody() {
		return extraBody;
	}

	public void setExtraBody(String extraBody) {
		this.extraBody = extraBody;
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

	public String getVersionCatalog() {
		return versionCatalog;
	}

	public void setVersionCatalog(String versionCatalog) {
		this.versionCatalog = versionCatalog;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isSelectAll() {
		return selectAll;
	}

	public void setSelectAll(boolean selectAll) {
		this.selectAll = selectAll;
	}

	public boolean isCancelPreVersion() {
		return cancelPreVersion;
	}

	public void setCancelPreVersion(boolean cancelPreVersion) {
		this.cancelPreVersion = cancelPreVersion;
	}

	public boolean isRebootUpdate() {
		return rebootUpdate;
	}

	public void setRebootUpdate(boolean rebootUpdate) {
		this.rebootUpdate = rebootUpdate;
	}

	public int getFinishTaskCount() {
		return finishTaskCount;
	}

	public void setFinishTaskCount(int finishTaskCount) {
		this.finishTaskCount = finishTaskCount;
	}

	public int getFailTaskCount() {
		return failTaskCount;
	}

	public void setFailTaskCount(int failTaskCount) {
		this.failTaskCount = failTaskCount;
	}

	public int getRunTaskCount() {
		return runTaskCount;
	}

	public void setRunTaskCount(int runTaskCount) {
		this.runTaskCount = runTaskCount;
	}

	public int getAllTaskCount() {
		return allTaskCount;
	}

	public void setAllTaskCount(int allTaskCount) {
		this.allTaskCount = allTaskCount;
	}

}
