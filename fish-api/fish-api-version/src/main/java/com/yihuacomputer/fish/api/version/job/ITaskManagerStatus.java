package com.yihuacomputer.fish.api.version.job;

import java.lang.Thread.State;

/**
 * 版本下发服务控制器，
 * 通过接口可以访问内部线程执行情况
 * @author Administrator
 *
 */
public interface ITaskManagerStatus {

	/**
	 * 版本下发作业状态
	 * @return
	 */
	public State getJobManagerState();
	
	/**
	 * 版本下发任务执行状态
	 * @return
	 */
	public State getTaskMangerState();
	
	/**
	 * 最大作业队列数
	 * @return
	 */
	public int getMaxJobCount();
	
	/**
	 * 队列中作业数
	 * @return
	 */
	public int getJobQueueCount();
	
	/**
	 * 正在执行任务数
	 * 
	 * @return
	 */
	public int getActiveTaskCount();
}
