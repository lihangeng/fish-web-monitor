package com.yihuacomputer.fish.web.report.form;

public class TypeForm {

	private String typeName;

	private int fault;

	private int trade;

	private double rate;

	public TypeForm(){

	}

	public TypeForm(String typeName,int fault,int trade,double rate){

		this.typeName=typeName;
		this.fault=fault;
		this.trade=trade;
		this.rate=rate;

	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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
