package com.yihuacomputer.fish.api.report.base;

public class FaultRateReport {

	private String name;
	
	private long tradeCount;
	
	private long faultCount;
	
	private String rate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(long transCount) {
		this.tradeCount = transCount;
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
	
}
