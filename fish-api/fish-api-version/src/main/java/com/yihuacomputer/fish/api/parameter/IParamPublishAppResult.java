package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

public interface IParamPublishAppResult {
	public long getId() ;
	public void setId(long id) ;
	/**
	 * 应用ID
	 * @return
	 */
	public IAppSystem getAppSystem() ;
	public void setAppSystem(IAppSystem appSystem) ;
	/**
	 * 部署结果
	 * @return
	 */
	public TaskStatus getStatus() ;
	public void setStatus(TaskStatus status) ;
	
	/**
	 * 归属的发布任务
	 * @return
	 */
	public IParamPublishResult getParamPublishResult();
	public void setParamPublishResult(IParamPublishResult paramPublishResult);
	
	/**
	 * 部署出现问题的原因
	 * @return
	 */
	public String getReason() ;
	public void setReason(String reason) ;
}
