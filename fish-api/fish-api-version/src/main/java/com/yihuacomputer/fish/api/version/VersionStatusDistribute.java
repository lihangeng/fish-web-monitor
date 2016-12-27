package com.yihuacomputer.fish.api.version;

/**
 * @author YiHua
 *
 */
public class VersionStatusDistribute {
	
	/**
	 *版本类型ID 
	 */
	private long versionTypeId;
	/**
	 * 版本ID
	 */
	private long versionId;
	/**
	 * 版本状态英文
	 */
	private String taskStatus;
	/**
	 * 版本状态中文
	 */
	private String taskStatusText;
	/**
	 * 版本状态所占百分比
	 */
	private int taskStatusNumber;
	public long getVersionTypeId() {
		return versionTypeId;
	}
	public void setVersionTypeId(long versionTypeId) {
		this.versionTypeId = versionTypeId;
	}
	public long getVersionId() {
		return versionId;
	}
	public void setVersionId(long versionId) {
		this.versionId = versionId;
	}
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskStatusText() {
		return taskStatusText;
	}
	public void setTaskStatusText(String taskStatusText) {
		this.taskStatusText = taskStatusText;
	}
	public int getTaskStatusNumber() {
		return taskStatusNumber;
	}
	public void setTaskStatusNumber(int taskStatusNumber) {
		this.taskStatusNumber = taskStatusNumber;
	}
		
}
