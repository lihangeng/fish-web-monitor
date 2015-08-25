package com.yihuacomputer.fish.api.monitor.software;
/**
 * 系统安装信息
 * @author Administrator
 *
 */
public interface IInstation {
	/**
	 * 获取程序名称.
	 * 
	 * @return 程序名称
	 */
	public String getProgramName();

	/**
	 * 获取发布者.
	 * 
	 * @return 发行商
	 */
	public String getPublisher();

	/**
	 * 获取安装日期.
	 * 
	 * @return 安装日期
	 */
	public String getInstallDate();

	/**
	 * 获取磁盘占用空间.
	 * 
	 * @return 磁盘占用空间
	 */
	public long getDiskUsed();

	/**
	 * 获取版本.
	 * 
	 * @return 版本
	 */
	public String getVersion();

}
