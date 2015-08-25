package com.yihuacomputer.fish.api.atmlog;

import java.lang.Thread.State;

/**
 * 日志文件备份管理器，通过接口可以得知内部线程状态
 * @author YiHua
 *
 */
public interface IBackupManagerStatus {

	/**
	 * 每日备份主线程状态
	 * @return
	 */
	public State getDaybackThreadState();
	
	/**
	 * 失败任务备份主线程状态
	 * @return
	 */
	public State getRedoBackTreadState();
	
	/**
	 * 并发执行任务数
	 * @return
	 */
	public int getActiveRuners();
}
