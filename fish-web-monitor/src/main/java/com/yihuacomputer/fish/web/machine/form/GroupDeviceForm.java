package com.yihuacomputer.fish.web.machine.form;

/**
 * @author YiHua
 *
 */
public class GroupDeviceForm {
	 	private Long id;
	    private Long groupId;
	    private Long deviceId;
	    
	    public GroupDeviceForm(){
	        
	    }

	    public Long getId()
	    {
	        return id;
	    }

	    public void setId(Long id)
	    {
	        this.id = id;
	    }

	    public Long getGroupId()
	    {
	        return groupId;
	    }

	    public void setGroupId(Long groupId)
	    {
	        this.groupId = groupId;
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
