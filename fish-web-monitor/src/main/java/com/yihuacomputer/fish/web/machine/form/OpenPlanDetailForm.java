package com.yihuacomputer.fish.web.machine.form;

import com.yihuacomputer.fish.api.openplan.IOpenPlanDetail;
import com.yihuacomputer.fish.api.openplan.OpenClose;
import com.yihuacomputer.fish.api.openplan.Week;



public class OpenPlanDetailForm {
    
    private long id;
    
    private Week week;
    
    private OpenClose openClose;
    
    private String startTime;
    
    private String endTime;
    
    public OpenPlanDetailForm(){
        
    }
    
    public OpenPlanDetailForm(IOpenPlanDetail openPlanDetail){
        this.id = openPlanDetail.getId();
        this.week = openPlanDetail.getWeek();
        this.openClose = openPlanDetail.getOpenClose();
        this.startTime = openPlanDetail.getStartTime();
        this.endTime = openPlanDetail.getEndTime();
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public OpenClose getOpenClose() {
        return openClose;
    }

    public void setOpenClose(OpenClose openClose) {
        this.openClose = openClose;
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
}
