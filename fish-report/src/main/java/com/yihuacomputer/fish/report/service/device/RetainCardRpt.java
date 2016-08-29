package com.yihuacomputer.fish.report.service.device;

import com.yihuacomputer.fish.api.report.device.IRetainCardRpt;

public class RetainCardRpt implements IRetainCardRpt {

	private String terminalId;
	private String orgName;
	private String account;
	private String retainDate;
	private String address;
	private String reason;
	private String orgCode;
	private String status;

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public void setRetainDate(String retainDate) {
		this.retainDate = retainDate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getAccount() {
		return account;
	}

	public String getRetainDate() {
		return retainDate;
	}

	public String getAddress() {
		return address;
	}

	public String getReason() {
		return reason;
	}

	@Override
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
