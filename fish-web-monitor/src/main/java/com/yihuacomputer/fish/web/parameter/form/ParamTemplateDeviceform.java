package com.yihuacomputer.fish.web.parameter.form;

public class ParamTemplateDeviceform
{
    private Long id;
    private Long groupId;
    private Long deviceId;
    
    public ParamTemplateDeviceform(){
        
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }


    public Long getDeviceId()
    {
        return deviceId;
    }

    public void setDeviceId(Long deviceId)
    {
        this.deviceId = deviceId;
    }

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
    
    
    
}
