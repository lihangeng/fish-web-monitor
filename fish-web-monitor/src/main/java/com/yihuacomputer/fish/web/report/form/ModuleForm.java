package com.yihuacomputer.fish.web.report.form;

public class ModuleForm {

	private String moduleName;

	private int fault;

	private int trade;

	private double rate;

	public ModuleForm(){

	}

	public ModuleForm(String moduleName,int fault,int trade,double rate){

		this.moduleName=moduleName;

		this.fault=fault;

		this.trade=trade;

		this.rate=rate;

	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getFault() {
		return fault;
	}

	public void setFault(int fault) {
		this.fault = fault;
	}

	public int getTrade() {
		return trade;
	}

	public void setTrade(int trade) {
		this.trade = trade;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}





}
