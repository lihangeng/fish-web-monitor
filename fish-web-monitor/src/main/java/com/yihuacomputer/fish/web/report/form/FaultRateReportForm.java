package com.yihuacomputer.fish.web.report.form;

public class FaultRateReportForm {
	
	private String name;
	
	private long transCount;
	
	private long faultCount;
	
	private String rate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTransCount() {
		return transCount;
	}

	public void setTransCount(long transCount) {
		this.transCount = transCount;
	}

	public long getFaultCount() {
		return faultCount;
	}

	public void setFaultCount(long faultCount) {
		this.faultCount = faultCount;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public FaultRateReportForm(){
		
	}

}
