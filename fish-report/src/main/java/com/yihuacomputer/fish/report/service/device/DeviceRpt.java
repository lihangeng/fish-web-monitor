package com.yihuacomputer.fish.report.service.device;

import com.yihuacomputer.fish.api.report.device.IDeviceRpt;

/**
 * @author YiHua
 *
 */
public class DeviceRpt implements IDeviceRpt {

	private String terminalId;

	private String deviceIp;

	private String orgName;

	private String devTypeName;

	private String setupType;

	private String mantainOrg;

	private String address;

	private String index;

	private String orgCode;

	private String devVendorName;

	private String awayFlag;

	@Override
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	@Override
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Override
	public String getDevVendorName() {
		return devVendorName;
	}

	public void setDevVendorName(String devVendorName) {
		this.devVendorName = devVendorName;
	}

	@Override
	public String getAwayFlag() {
		return awayFlag;
	}

	public void setAwayFlag(String awayFlag) {
		this.awayFlag = awayFlag;
	}

	@Override
	public String getTerminalId() {
		return terminalId;
	}

	@Override
	public String getDeviceIp() {
		return deviceIp;
	}

	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public String getDevTypeName() {
		return devTypeName;
	}

	@Override
	public String getSetupType() {
		return setupType;
	}

	@Override
	public String getMantainOrg() {
		return mantainOrg;
	}

	@Override
	public String getAddress() {
		return address;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public void setSetupType(String setupType) {
		this.setupType = setupType;
	}

	public void setMantainOrg(String mantainOrg) {
		this.mantainOrg = mantainOrg;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
