package com.yihuacomputer.fish.report.service.device;

import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRpt;

/**
 * @author YiHua
 *
 */
public class DeviceBoxDetailRpt implements IDeviceBoxDetailRpt {

	private String deviceNo;

	private String orgName;

	private String vendorName;

	private String typeName;

	private String setupType;

	private String status;

	private String boxLeft;

	@Override
	public String getDeviceNo() {
		return deviceNo;
	}

	@Override
	public String getOrgName() {
		return orgName;
	}

	@Override
	public String getVendorName() {
		return vendorName;
	}

	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public String getSetupType() {
		return setupType;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public String getBoxLeft() {
		return boxLeft;
	}

	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public void setSetupType(String setupType) {
		this.setupType = setupType;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public void setBoxLeft(String boxLeft) {
		this.boxLeft = boxLeft;
	}
}
