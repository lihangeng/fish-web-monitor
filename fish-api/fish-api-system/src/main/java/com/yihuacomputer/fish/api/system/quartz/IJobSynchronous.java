package com.yihuacomputer.fish.api.system.quartz;

import java.util.Date;

/**
 * 作业同步执行
 * @author Yihua
 *
 */
public interface IJobSynchronous {

	/**
	 * 作业唯一编号
	 * @return
	 */
	public String getId();
	
	/**
	 * 作业名称
	 * @return
	 */
	public String getJobName();
	
	
	/**
	 * 作业触发器
	 * @return
	 */
	public String getJobTriger();
	
	/**
	 * 上次执行时间
	 * @return
	 */
	public Date getDateTime();
	
	/**
	 * @param dateTime
	 */
	public void setDateTime(Date dateTime);
	
	/**
	 * 执行任务服务器IP地址
	 * @return
	 */
	public String getServerIp();
	
	/**
	 * @param serverIp
	 */
	public void setServerIp(String serverIp);
	
}
