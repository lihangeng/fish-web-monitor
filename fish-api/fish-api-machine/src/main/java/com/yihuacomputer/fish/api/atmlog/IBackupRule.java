package com.yihuacomputer.fish.api.atmlog;

public interface IBackupRule {
	
	public String getTerminalId();

	public void setTerminalId(String terminalId);

	public String getTerminalIp();

	public void setTerminalIp(String terminalIp);

	public String getBackupDate();

	public void setBackupDate(String backupDate);
	
	public String getOrgId();
	
	public void setOrgId(String orgId);
}
