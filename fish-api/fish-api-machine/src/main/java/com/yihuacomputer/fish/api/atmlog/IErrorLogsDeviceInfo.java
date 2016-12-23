package com.yihuacomputer.fish.api.atmlog;

/**
 * 错误日志设备详细信息
 * @author wangchao
 *
 */
public interface IErrorLogsDeviceInfo {
	
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * 获取设备编号
	 * @return
	 */
	public String getTerminalId();

	/**
	 * 设置设备编号
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * 获取网点机构id
	 * 
	 * @return
	 */
	public String getOrgId();
	
	/**
	 * 设置网点机构Id
	 * @param orgId
	 */
	public void setOrgId(String orgId);
	
	/**
	 * 获取网点名称
	 * @return
	 */
	public String getOrgName();
	
	/**
	 * 设置网点名称
	 * @param orgName
	 */
	public void setOrgName(String orgName);
	
	/**
	 * 获取安装日期
	 * @return
	 */
	public String getInstallDate();
	
	/**
	 * 设置安装日期
	 * @param installDate
	 */
	public void setInstallDate(String installDate);
	
	/**
	 * 获取错误原因
	 * @return
	 */
	public String getBackupResult();
	
	/**
	 * 设置错误原因
	 * @param backupResult
	 */
	public void setBackupResult(String backupResult);
	
	/**
	 * 获取设备地址
	 * @return
	 */
	public String getAddress();
	
	/**设置设备地址
	 * @param address
	 */
	public void setAddress(String address);
	
	/**
	 * 获取备份时间
	 * @return
	 */
	public String getDateTime();
	
	/**
	 * 设置备份时间
	 * @param dateTime
	 */
	public void setDateTime(String dateTime);
	
	/**
	 * 获取设备IP
	 * @return
	 */
	public String getIP();
	
	/**
	 * 设置设备IP
	 * @param ip
	 */
	public void setIP(String ip);
	
	
}
