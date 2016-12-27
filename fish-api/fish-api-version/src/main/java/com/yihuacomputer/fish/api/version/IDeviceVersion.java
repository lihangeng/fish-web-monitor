package com.yihuacomputer.fish.api.version;

import java.util.Date;

import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

/**
 * @author YiHua
 *
 */
public interface IDeviceVersion {

	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public long getVersionId();

	/**
	 * @param versionId
	 */
	public void setVersionId(long versionId);

	/**
	 * @return
	 */
	public Date getCreatedTime();

	/**
	 * @param createdTime
	 */
	public void setCreatedTime(Date createdTime);

	/**
	 * @return
	 */
	public Date getLastUpdatedTime();

	/**
	 * @param lastUpdatedTime
	 */
	public void setLastUpdatedTime(Date lastUpdatedTime);

	/**
	 * @return
	 */
	public String getDesc();

	/**
	 * @param desc
	 */
	public void setDesc(String desc);

	/**
	 * @return
	 */
	public long getDeviceId();

	/**
	 * @param deviceId
	 */
	public void setDeviceId(long deviceId);

	/**
	 * @return
	 */
	public TaskStatus getTaskStatus();

	/**
	 * @param taskStatus
	 */
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
	
	/**
	 * @param taskId
	 */
	public void setCompleteTaskId(long taskId);
	

}