package com.yihuacomputer.fish.api.monitor.box;

public class CashInitPlanDeviceInfoForm {
	private long id;
	private String terminalId;
	private String devType;
	private String awayFlag;
	private String orgName;
	private long lastAmt;
	private String lastDate;
	private double adviceAmt;
	private double actualAmt;
	private long  cashInitPlanInfoId;
	private int flag;//加钞类型标识(1:钞箱预警.2:超过加钞预警天数.4.手工强制清机加钞)
	private String address;
	private long maxAmt;
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public long getLastAmt() {
		return lastAmt;
	}
	public void setLastAmt(long lastAmt) {
		this.lastAmt = lastAmt;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public double getAdviceAmt() {
		return adviceAmt;
	}
	public void setAdviceAmt(double adviceAmt) {
		this.adviceAmt = adviceAmt;
	}
	public double getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(double actualAmt) {
		this.actualAmt = actualAmt;
	}
	public long getCashInitPlanInfoId() {
		return cashInitPlanInfoId;
	}
	public void setCashInitPlanInfoId(long cashInitPlanInfoId) {
		this.cashInitPlanInfoId = cashInitPlanInfoId;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	public String getAwayFlag() {
		return awayFlag;
	}
	public void setAwayFlag(String awayFlag) {
		this.awayFlag = awayFlag;
	}
	public long getMaxAmt() {
		return maxAmt;
	}
	public void setMaxAmt(long maxAmt) {
		this.maxAmt = maxAmt;
	}
	
}
