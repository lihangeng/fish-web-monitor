package com.yihuacomputer.fish.api.atmlog;

/**
 * @author YiHua
 *
 */
public interface IBackupRule {
	
	/**
	 * @return
	 */
	public String getTerminalId();

	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);

	/**
	 * @return
	 */
	public String getTerminalIp();

	/**
	 * @param terminalIp
	 */
	public void setTerminalIp(String terminalIp);

	/**
	 * @return
	 */
	public String getBackupDate();

	/**
	 * @param backupDate
	 */
	public void setBackupDate(String backupDate);
	
	/**
	 * @return
	 */
	public String getOrgId();
	
	/**
	 * @param orgId
	 */
	public void setOrgId(String orgId);
}
