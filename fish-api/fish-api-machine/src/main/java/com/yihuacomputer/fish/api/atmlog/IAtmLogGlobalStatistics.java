package com.yihuacomputer.fish.api.atmlog;

import java.util.Date;

/**
 * @author YiHua
 *
 */
public interface IAtmLogGlobalStatistics {
	
	/**
	 * @return
	 */
	public long getId();
	
	/**
	 * @param id
	 */
	public void setId(long id);
	
	/**
	 * 所属机构
	 * @return
	 */
	
	public String getOrgName();
	
	/**
	 * @param orgName
	 */
	public void setOrgName(String orgName);
	
	/**
	 * @return
	 */
	public String getOrgCode();
	
	/**
	 * @param orgCode
	 */
	public void setOrgCode(String orgCode);
	
	
	/**
	 * 日志备份日期
	 * @return
	 */
	public Date getBackupDate();
	
	/**
	 * @param backupDate
	 */
	public void setBackupDate(Date backupDate);
	
	/**
	 * 设备所属品牌名称
	 * @return
	 */
	public String getDeviceVendor();
	
	/**
	 * @param deviceVendor
	 */
	public void setDeviceVendor(String deviceVendor);
	/**
	 * 备份成功台数
	 * @return
	 */
	public int getBackupSuccessNumber();
	
	/**
	 * @param backupSuccessNumber
	 */
	public void setBackupSuccessNumber(int backupSuccessNumber);
	/**
	 * 备份失败台数
	 * @return
	 */
	public int getBackupErrorNumber();
	
	/**
	 * @param backupErrorNumber
	 */
	public void setBackupErrorNumber(int backupErrorNumber);
	
}
