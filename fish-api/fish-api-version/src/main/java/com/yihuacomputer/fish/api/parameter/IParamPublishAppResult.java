package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.fish.api.version.job.task.TaskStatus;

/**
 * @author YiHua
 *
 */
public interface IParamPublishAppResult {
	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param id
	 */
	public void setId(long id) ;
	/**
	 * 应用ID
	 * @return
	 */
	public IAppSystem getAppSystem() ;
	/**
	 * @param appSystem
	 */
	public void setAppSystem(IAppSystem appSystem) ;
	/**
	 * 部署结果
	 * @return
	 */
	public TaskStatus getStatus() ;
	/**
	 * @param status
	 */
	public void setStatus(TaskStatus status) ;
	
	/**
	 * 归属的发布任务
	 * @return
	 */
	public IParamPublishResult getParamPublishResult();
	/**
	 * @param paramPublishResult
	 */
	public void setParamPublishResult(IParamPublishResult paramPublishResult);
	
	/**
	 * 部署出现问题的原因
	 * @return
	 */
	public String getReason() ;
	/**
	 * @param reason
	 */
	public void setReason(String reason) ;
}
