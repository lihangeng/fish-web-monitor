package com.yihuacomputer.fish.web.index.form;

/**
 * @author YiHua
 *
 */
public class LoginBackForm
{
    private String userId;
    private String userCode;
    private String name;
    private String organizationId;
    private String organizationType;
    private String organizationName;
    
    public LoginBackForm(){
        
    }
    
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public String getUserCode()
    {
        return userCode;
    }
    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getOrganizationId()
    {
        return organizationId;
    }

    public void setOrganizationId(String organizationId)
    {
        this.organizationId = organizationId;
    }

    public String getOrganizationType()
    {
        return organizationType;
    }

    public void setOrganizationType(String organizationType)
    {
        this.organizationType = organizationType;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }
    
    
    
}
