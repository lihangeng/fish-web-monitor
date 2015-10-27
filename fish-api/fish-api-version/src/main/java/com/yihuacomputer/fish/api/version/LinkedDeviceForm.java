package com.yihuacomputer.fish.api.version;

import com.yihuacomputer.fish.api.device.IDevice;

public class LinkedDeviceForm {
	private long id;
	private String code;
	private String ip;
	private int port;
	private String address;
	private String installDate;
	private String deviceVersion;
	private String orgName;
	private String deviceType;
//	private boolean selectable = true;
	private String targetVersion;
	private String taskStatus;
	private boolean success;
	private String reason;
	private boolean taskable = true;// 可以任务化的，意思可以加入任务。默认是可以加入。

	public LinkedDeviceForm() {

	}

	public LinkedDeviceForm(IDevice device) {
		setId(device.getId());
		setCode(device.getTerminalId());
		setIp(device.getIp().toString());
		setAddress(device.getAddress());
		this.deviceType = device.getDevType().getName();
		this.orgName = device.getOrganization().getName();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

//	public boolean isSelectable() {
//		return selectable;
//	}
//
//	public void setSelectable(boolean selectable) {
//		this.selectable = selectable;
//	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isTaskable() {
		return taskable;
	}

	public void setTaskable(boolean taskable) {
		this.taskable = taskable;
	}

	public String getTargetVersion() {
		return targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}
}
