package com.yihuacomputer.fish.api.atmlog;

public interface IDayBackupExcuter {

	/**
	 * 重做某日失败的日志任务
	 * @param date
	 */
	public void redoDayJob(String date)throws Exception;
}
