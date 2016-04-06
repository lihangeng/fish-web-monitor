package com.yihuacomputer.fish.web.parameter.form;

import com.yihuacomputer.fish.api.device.IDevice;

public class DevParameterForm {
	private long id;

	/**
	 * 设备号
	 */
	private String terminalId;

	/**
	 * 设备IP地址
	 */
	private String ip;

	/**
	 * 所属机构
	 */
	private String orgId;

	private String orgName;

	/**
	 * 设备型号
	 */
	private long devTypeId;

	private String devTypeName;
	
	private String devCatalogName;

	
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public long getDevTypeId() {
		return devTypeId;
	}

	public void setDevTypeId(long devTypeId) {
		this.devTypeId = devTypeId;
	}

	public String getDevTypeName() {
		return devTypeName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public String getDevCatalogName() {
		return devCatalogName;
	}

	public void setDevCatalogName(String devCatalogName) {
		this.devCatalogName = devCatalogName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public DevParameterForm(){
		
	}
	public DevParameterForm(IDevice device){
		this.id=device.getId();
		this.terminalId=device.getTerminalId();
		this.ip=device.getIp().toString();
		if (device.getDevType() != null) {
			setDevTypeId(device.getDevType().getId());
			setDevTypeName(device.getDevType().getName());
			setDevCatalogName(device.getDevType().getDevCatalog().getName());
		}
		if (device.getOrganization() != null) {
			setOrgId(device.getOrganization().getGuid());
			setOrgName(device.getOrganization().getName());
		}
	}
	
	

}
