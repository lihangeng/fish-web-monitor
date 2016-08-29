package com.yihuacomputer.fish.report.service.trans;

import com.yihuacomputer.fish.api.report.trans.ITransResultCountRpt;

public class TransResultCountRpt implements ITransResultCountRpt {

	private String orgName;

	private String result;

	private String countName;

	private double transCount;

	private String orgNameColumn;

	private String transResultColumn;

	private String subtotalColumn;

	private String totalColumn;

	public String getTotalColumn() {
		return totalColumn;
	}

	public void setTotalColumn(String totalColumn) {
		this.totalColumn = totalColumn;
	}

	public String getOrgNameColumn() {
		return orgNameColumn;
	}

	public void setOrgNameColumn(String orgNameColumn) {
		this.orgNameColumn = orgNameColumn;
	}

	public String getTransResultColumn() {
		return transResultColumn;
	}

	public void setTransResultColumn(String transResultColumn) {
		this.transResultColumn = transResultColumn;
	}

	public String getSubtotalColumn() {
		return subtotalColumn;
	}

	public void setSubtotalColumn(String subtotalColumn) {
		this.subtotalColumn = subtotalColumn;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setCountName(String countName) {
		this.countName = countName;
	}

	public void setTransCount(double transCount) {
		this.transCount = transCount;
	}

	@Override
	public String getOrgName() {
		return this.orgName;
	}

	@Override
	public String getResult() {
		return this.result;
	}

	@Override
	public String getCountName() {
		return this.countName;
	}

	@Override
	public double getTransCount() {
		return this.transCount;
	}

}
