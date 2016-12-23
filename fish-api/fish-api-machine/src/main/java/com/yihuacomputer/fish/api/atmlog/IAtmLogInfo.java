package com.yihuacomputer.fish.api.atmlog;

import java.util.Date;

/**
 * @author YiHua
 *
 */
public interface IAtmLogInfo {
	
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
	 * 日志备份日期
	 * @return
	 */
	public Date getBackupDate();
	
	/**
	 * @param backupDate
	 */
	public void setBackupDate(Date backupDate);
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
	/**
	 * 总共备份的台数
	 * @return
	 */
	public int getTotalBackupNumber() ;

	/**
	 * @param totalBackupNumber
	 */
	public void setTotalBackupNumber(int totalBackupNumber) ;
	
}
