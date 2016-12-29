package com.yihuacomputer.fish.web.report.form;


/**
 * @author YiHua
 *
 */
public class BrandForm {

	private String brandName;

	private int fault;

	private int trade;

	private double rate;

	public BrandForm(){

	}

	/**
	 * @param brandName
	 * @param fault
	 * @param trade
	 * @param rate
	 */
	public BrandForm(String brandName,int fault,int trade, double rate){

		this.brandName=brandName;
		this.fault=fault;
		this.trade=trade;
		this.rate=rate;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
