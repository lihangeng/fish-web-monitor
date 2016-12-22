package com.yihuacomputer.fish.api.atmlog;

import java.util.Date;

public interface IAtmLogGlobalStatistics {
	
	public long getId();
	
	public void setId(long id);
	
	/**
	 * 所属机构
	 * @return
	 */
	
	public String getOrgName();
	
	public void setOrgName(String orgName);
	
	public String getOrgCode();
	
	public void setOrgCode(String orgCode);
	
	
	/**
	 * 日志备份日期
	 * @return
	 */
	public Date getBackupDate();
	
	public void setBackupDate(Date backupDate);
	
	/**
	 * 设备所属品牌名称
	 * @return
	 */
	public String getDeviceVendor();
	
	public void setDeviceVendor(String deviceVendor);
	/**
	 * 备份成功台数
	 * @return
	 */
	public int getBackupSuccessNumber();
	
	public void setBackupSuccessNumber(int backupSuccessNumber);
	/**
	 * 备份失败台数
	 * @return
	 */
	public int getBackupErrorNumber();
	
	public void setBackupErrorNumber(int backupErrorNumber);
	
}
