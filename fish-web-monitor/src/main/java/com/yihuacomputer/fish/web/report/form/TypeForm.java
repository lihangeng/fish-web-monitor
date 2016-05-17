package com.yihuacomputer.fish.web.report.form;

public class TypeForm {

	private String type;

	private int fault;

	private int trade;

	private double rate;

	public TypeForm(){

	}

	public TypeForm(String type,int fault,int trade,double rate){

		this.type=type;
		this.fault=fault;
		this.trade=trade;
		this.rate=rate;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
