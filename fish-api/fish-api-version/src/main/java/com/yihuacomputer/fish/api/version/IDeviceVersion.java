package com.yihuacomputer.fish.api.version;

import java.util.Date;

import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

public interface IDeviceVersion {

	public long getId();

	public void setId(long id);

	public long getVersionId();

	public void setVersionId(long versionId);

	public Date getCreatedTime();

	public void setCreatedTime(Date createdTime);

	public Date getLastUpdatedTime();

	public void setLastUpdatedTime(Date lastUpdatedTime);

	public String getDesc();

	public void setDesc(String desc);

	public long getDeviceId();

	public void setDeviceId(long deviceId);

	public TaskStatus getTaskStatus();

	public void setTaskStatus(TaskStatus taskStatus);
	
	/**
	 * 获得关联的任务数量 
	 * @return
	 * @since 0.17
	 */
	public int getRelationTaskSize();
	
	/**
	 * 获得第一次完成的任务编号
	 * 当存在任务编号时，有新的任务在执行时，则取消
	 * @return
	 * @since 0.17
	 */
	public long getCompleteTaskId();
	
	public void setCompleteTaskId(long taskId);
	

}