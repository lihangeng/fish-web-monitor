package com.yihuacomputer.fish.web.report.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.report.IStartPlan;


public class StartPlanForm {

    private long id;

    /**
     * 方案名称
     */
    private String name;
    
    /**
     * 有效开始时间
     */
	private String startDate;
	
    /**
     * 有效结束时间
     */
	private String endDate;
    
    /**
     * 描述
     */
    private String note;
    
    public StartPlanForm(){
    }
    
    public StartPlanForm(IStartPlan startPlan){
    	setId(startPlan.getId());
    	setName(startPlan.getName());
    	setNote(startPlan.getNote());
    	setStartDate(startPlan.getStartDate());
    	setEndDate(startPlan.getEndDate());
    }
    
    public void translate(IStartPlan startPlan) {
    	startPlan.setName(getName());
    	startPlan.setNote(getNote());
    	startPlan.setStartDate(getStartDate());
    	startPlan.setEndDate(getEndDate());
    }
    
    public static List<StartPlanForm> convert(List<IStartPlan> target) {
        List<StartPlanForm> result = new ArrayList<StartPlanForm>();

        for (IStartPlan startPlan : target) {
            result.add(new StartPlanForm(startPlan));
        }
        return result;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
