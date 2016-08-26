package com.yihuacomputer.fish.web.cashbox.form;

public class CashInitPlanInfoForm {
	private long id;
	private int date;
	private String cashInitCode;
	private String orgName;
	private double amt;
	private long orgId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getCashInitCode() {
		return cashInitCode;
	}
	public void setCashInitCode(String cashInitCode) {
		this.cashInitCode = cashInitCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}
	
}
