package com.yihuacomputer.fish.api.report.fault;

public class FaultRateReport {

	private long vendorId;
	
	private long devTypeId;
	
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

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public long getDevTypeId() {
		return devTypeId;
	}

	public void setDevTypeId(long devTypeId) {
		this.devTypeId = devTypeId;
	}
	
}
