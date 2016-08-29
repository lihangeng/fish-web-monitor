package com.yihuacomputer.fish.report.service.transaction;

import com.yihuacomputer.fish.api.report.transaction.ITransCountRpt;

public class TransCountRpt implements ITransCountRpt {

	private String terminalId;

	private String orgName;

	private String transType;

	private double transCount;

	private String countName;

	private String orgNameColumn;

	private String transTypeColumn;

	private String totalColumn;

	private String subtotalColumn;

	private String devNoColumn;

	public String getOrgNameColumn() {
		return orgNameColumn;
	}

	public void setOrgNameColumn(String orgNameColumn) {
		this.orgNameColumn = orgNameColumn;
	}

	public String getTransTypeColumn() {
		return transTypeColumn;
	}

	public void setTransTypeColumn(String transTypeColumn) {
		this.transTypeColumn = transTypeColumn;
	}

	public String getTotalColumn() {
		return totalColumn;
	}

	public void setTotalColumn(String totalColumn) {
		this.totalColumn = totalColumn;
	}

	public String getSubtotalColumn() {
		return subtotalColumn;
	}

	public void setSubtotalColumn(String subtotalColumn) {
		this.subtotalColumn = subtotalColumn;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	@Override
	public String getCountName() {
		return countName;
	}

	@Override
	public double getTransCount() {
		return transCount;
	}

	public void setTransCount(double transCount) {
		this.transCount = transCount;
	}

	public void setCountName(String countName) {
		this.countName = countName;
	}

	@Override
	public String getTerminalId() {
		return this.terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getDevNoColumn() {
		return devNoColumn;
	}

	public void setDevNoColumn(String devNoColumn) {
		this.devNoColumn = devNoColumn;
	}
}
