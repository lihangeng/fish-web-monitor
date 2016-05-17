package com.yihuacomputer.fish.web.system.form;

public class PersonDeviceForm
{
    private Long id;
    private Long personId;
    private Long deviceId;
    
    public PersonDeviceForm(){
        
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getPersonId()
    {
        return personId;
    }

    public void setPersonId(Long personId)
    {
        this.personId = personId;
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
