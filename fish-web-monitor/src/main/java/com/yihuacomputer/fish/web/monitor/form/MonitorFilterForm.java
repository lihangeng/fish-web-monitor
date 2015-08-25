package com.yihuacomputer.fish.web.monitor.form;

import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;

public class MonitorFilterForm {
	private String userId;

	private String areaCode;

	private String brandItem;

	private String classifyItem;

	private String ingItem;

	private String sellItem;

	private String atmGroup;

	public MonitorFilterForm() {
	}

	public MonitorFilterForm(IStatusFilter statusFilter) {
		this.userId = statusFilter.getUserId();
		this.areaCode = statusFilter.getOrgId();
		this.brandItem = String.valueOf(statusFilter.getDevVendor());
		this.classifyItem = String.valueOf(statusFilter.getDevType());
		this.ingItem = String.valueOf(statusFilter.getAwayFlag());
		this.sellItem = String.valueOf(statusFilter.getWorkType());
		this.atmGroup = String.valueOf(statusFilter.getAtmGroup());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getBrandItem() {
		return brandItem;
	}

	public void setBrandItem(String brandItem) {
		this.brandItem = brandItem;
	}

	public String getClassifyItem() {
		return classifyItem;
	}

	public void setClassifyItem(String classifyItem) {
		this.classifyItem = classifyItem;
	}

	public String getIngItem() {
		return ingItem;
	}

	public void setIngItem(String ingItem) {
		this.ingItem = ingItem;
	}

	public String getSellItem() {
		return sellItem;
	}

	public void setSellItem(String sellItem) {
		this.sellItem = sellItem;
	}

	public String getAtmGroup() {
		return atmGroup;
	}

	public void setAtmGroup(String atmGroup) {
		this.atmGroup = atmGroup;
	}

}
