package com.yihuacomputer.fish.web.report.form;

public class PlanDeviceForm
{
    private Long id;
    private Long planId;
    private Long deviceId;
    
    public PlanDeviceForm(){
        
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPlanId()
    {
        return planId;
    }

    public void setPlanId(Long planId)
    {
        this.planId = planId;
    }

    public Long getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }
    
    
    
}
