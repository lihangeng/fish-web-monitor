package com.yihuacomputer.fish.api.atmlog;

/**
 * @author YiHua
 *
 */
public interface IAtmLog {
	
	/**
	 * @return
	 */
	public long getId();

	/**
	 * @param id
	 */
	public void setId(long id);

	/**日志大小
	 * @return
	 */
	public long getLogSize();

	/**
	 * @param logSize
	 */
	public void setLogSize(long logSize);
	
	/**设备号
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**备份日期
	 * @return
	 */
	public String getDateTime();

	/**
	 * @param dateTime
	 */
	public void setDateTime(String dateTime) ;

	/**备份次数
	 * @return
	 */
	public int getDoTimes();

	/**
	 * @param doTimes
	 */
	public void setDoTimes(int doTimes);

	/**最后一次备份时间
	 * @return
	 */
	public String getLastDoDate();

	/**
	 * @param lastDoDate
	 */
	public void setLastDoDate(String lastDoDate);

	/**备份结果
	 * @return
	 */
	public BackupResult getBackupResult();

	/**
	 * @param result
	 */
	public void setBackupResult(BackupResult result);
	
	/**冠字号信息是否已经导入
	 * @return
	 */
	public boolean isCrownImport();

	/**
	 * @param isCrownImport
	 */
	public void setCrownImport(boolean isCrownImport);
}
