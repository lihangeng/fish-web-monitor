package com.yihuacomputer.fish.web.system.form;


/**
 * @author YiHua
 *
 */
public class UserRoleForm
{
    private Long id;
    private Long userId;
    private Long roleId;
    
    public UserRoleForm(){
        
    }
    
    
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public Long getRoleId()
    {
        return roleId;
    }
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }
    
    
}
