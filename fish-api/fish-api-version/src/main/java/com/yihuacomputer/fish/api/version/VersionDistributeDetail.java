package com.yihuacomputer.fish.api.version;

public class VersionDistributeDetail {
	private String terminalId;
	private String orgName;
	private String deviceType;
	private String vendor;
	private String beforeUpdateVersionNo;
	private String afterUpdateVersionNo;
	private String statusText;
	private String ip;
	private String updateType;
	
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * 设备型号
	 * @return
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * 设备型号
	 * @param deviceType
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * 设备品牌
	 * @return
	 */
	public String getVendor() {
		return vendor;
	}
	/**
	 * 设备品牌
	 * @param vendor
	 */
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	/**
	 * 升级前版本号
	 * @return
	 */
	public String getBeforeUpdateVersionNo() {
		return beforeUpdateVersionNo;
	}
	/**
	 * 升级前版本号
	 * @param beforeUpdateVersionNo
	 */
	public void setBeforeUpdateVersionNo(String beforeUpdateVersionNo) {
		this.beforeUpdateVersionNo = beforeUpdateVersionNo;
	}
	/**
	 * 升级后版本号
	 * @return
	 */
	public String getAfterUpdateVersionNo() {
		return afterUpdateVersionNo;
	}
	/**
	 * 升级后版本号
	 * @param afterUpdateVersionNo
	 */
	public void setAfterUpdateVersionNo(String afterUpdateVersionNo) {
		this.afterUpdateVersionNo = afterUpdateVersionNo;
	}
	/**
	 * 升级状态
	 * @return
	 */
	public String getStatusText() {
		return statusText;
	}
	/**
	 * 升级状态
	 * @param statusText
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	/**
	 * 设备IP
	 * @return
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设备IP
	 * @param ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 升级类型
	 * @return
	 */
	public String getUpdateType() {
		return updateType;
	}
	/**
	 * 升级类型
	 * @param updateType
	 */
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}
	
}
