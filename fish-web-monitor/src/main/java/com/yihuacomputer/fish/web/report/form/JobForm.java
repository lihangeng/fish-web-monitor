package com.yihuacomputer.fish.web.report.form;

public class JobForm {
	private int id;
	private String jobName;
	private String startTime;
	private String endTime;
	private String tradeTime;
	private String operaResult;

	public JobForm() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getOperaResult() {
		return operaResult;
	}

	public void setOperaResult(String operaResult) {
		this.operaResult = operaResult;
	}

}
