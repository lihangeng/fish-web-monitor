package com.yihuacomputer.fish.atmlog.rule;

import com.yihuacomputer.fish.api.atmlog.IBackupRule;

/**
 * 备份规则
 * @author YiHua
 *
 */
public class BackupRule implements IBackupRule{
	
	/**设备ID*/
	private String terminalId;
	
	/**设备IP*/
	private String terminalIp;
	
	/**备份日期*/
	private String backupDate;
	
	/**机构编号*/
	private String orgId;
	

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getTerminalIp() {
		return terminalIp;
	}

	public void setTerminalIp(String terminalIp) {
		this.terminalIp = terminalIp;
	}

	public String getBackupDate() {
		return backupDate;
	}

	public void setBackupDate(String backupDate) {
		this.backupDate = backupDate;
	}	
	public String getOrgId(){
		return orgId;
	}
	
	public void setOrgId(String orgId){
		this.orgId = orgId;
	}
}
