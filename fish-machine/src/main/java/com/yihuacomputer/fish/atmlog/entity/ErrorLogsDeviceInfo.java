package com.yihuacomputer.fish.atmlog.entity;

import java.io.Serializable;

import com.yihuacomputer.fish.api.atmlog.IErrorLogsDeviceInfo;

public class ErrorLogsDeviceInfo implements IErrorLogsDeviceInfo,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5142377225032867483L;

	private long id;
	
	private String terminalId;
	
	private String orgId;
	
	private String orgName;
	
	private String installDate;
	
	private String backupResult;
	
	private String address;
	
	private String DateTime;
	
	private String IP;
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getTerminalId() {
		return terminalId;
	}

	@Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	@Override
	public String getOrgId() {
		return orgId;
	}

	@Override
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
	public String getInstallDate() {
		return installDate;
	}

	@Override
	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	@Override
	public String getBackupResult() {
		return backupResult;
	}

	@Override
	public void setBackupResult(String backupResult) {
		this.backupResult = backupResult;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getDateTime() {
		return DateTime;
	}

	@Override
	public void setDateTime(String DateTime) {
		this.DateTime = DateTime;
	}

	@Override
	public String getIP() {
		return IP;
	}

	@Override
	public void setIP(String IP) {
		this.IP = IP;
	}

}
