package com.yihuacomputer.fish.web.bsadvert.form;

/**
 * @author YiHua
 *
 */
public class BsAdvertGroupDeviceForm
{
    private Long id;
    private Long groupId;
    private Long deviceId;
    
    public BsAdvertGroupDeviceForm(){
        
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
