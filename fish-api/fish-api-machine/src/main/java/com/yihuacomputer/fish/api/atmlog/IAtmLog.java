package com.yihuacomputer.fish.api.atmlog;

public interface IAtmLog {
	
	public long getId();

	public void setId(long id);

	public long getLogSize();

	public void setLogSize(long logSize);
	
	/**设备号*/
	public String getTerminalId();

	public void setTerminalId(String terminalId);

	/**备份日期*/
	public String getDateTime();

	public void setDateTime(String dateTime) ;

	/**备份次数*/
	public int getDoTimes();

	public void setDoTimes(int doTimes);

	/**最后一次备份时间*/
	public String getLastDoDate();

	public void setLastDoDate(String lastDoDate);

	/**备份结果*/
	public BackupResult getBackupResult();

	public void setBackupResult(BackupResult result);
	
	/**冠字号信息是否已经导入*/
	public boolean isCrownImport();

	public void setCrownImport(boolean isCrownImport);
}
