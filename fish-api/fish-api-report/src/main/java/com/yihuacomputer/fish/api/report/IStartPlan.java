package com.yihuacomputer.fish.api.report;


public interface IStartPlan {
	public long getId();

	public void setId(long id);

	public String getName();

	public void setName(String name);
	
	public String getStartDate();
	
	public void setStartDate(String startDate);
	
	public String getEndDate();
	
	public void setEndDate(String endDate);

	public String getNote();

	public void setNote(String note);
}
