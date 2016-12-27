package com.yihuacomputer.fish.api.system.quartz;

/**
 * 作业同步服务
 * @author Yihua
 *
 */
public interface IJobSynchService {

	/**
	 * 获取作业执行权限
	 * @param jobId
	 * @return true-表示可以执行
	 * @return false-表示未获取到定时任务的执行权限
	 */
	public boolean getJobRunPriviledge(String jobId);
}
