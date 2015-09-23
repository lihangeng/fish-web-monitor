package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.Gender;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * 账户信息：
 * @author huxiaobao
 *
 */
public class UserForm
{
    private Long id;
    private String code;
    private String userState;
    private String userGuid;
    private String name;
    private String mobile;
    private String phone;
    private String email;
    private String organizationName;
    private Gender gender;
    private int userType;
    
    private String roles;
    
    public UserForm() {
    }
    
    public UserForm(IUser user){
        this.id = user.getId();
        this.code = user.getCode();
        this.userGuid = user.getPerson().getGuid();
        this.name = user.getPerson().getName();
        this.gender = user.getPerson().getGender();
        this.mobile = user.getPerson().getMobile();
        this.phone = user.getPerson().getPhone();
        this.email = user.getPerson().getEmail();
        this.userType = user.getUserType().getId();
        userState = Integer.toString(user
                .getState() == null ? 0 : user
                .getState().getId());
        if(user.getPerson().getOrganization()!=null){
            this.organizationName = user.getPerson().getOrganization().getName();
        }
        String roleVal="";
        if(user.getRoles()!=null){
        	int counter = 0;
        	for(IRole role:user.getRoles())
        	{
        		counter++;
        		roleVal+=role.getName();
        		if(user.getRoles().size()==counter){
        			break;
        		}
        			roleVal+=";";
        	}
        }
        this.roles = roleVal;
    }
    
    /**
     * 
     * 方法描述 : 转换
     * 
     * @param resources
     * @return List
     */

    public static List<UserForm> convert(List<IUser> resources) {
        List<UserForm> result = new ArrayList<UserForm>();
        for(IUser resource : resources) {
            result.add(new UserForm(resource));
        }
        return result;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getUserState()
    {
        return userState;
    }

    public void setUserState(String userState)
    {
        this.userState = userState;
    }

    public String getUserGuid()
    {
        return userGuid;
    }

    public void setUserGuid(String userGuid)
    {
        this.userGuid = userGuid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getOrganizationName()
    {
        return organizationName;
    }

    public void setOrganizationName(String organizationName)
    {
        this.organizationName = organizationName;
    }

    public Gender getGender()
    {
        return gender;
    }

    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    public String getRoles()
    {
        return roles;
    }

    public void setRoles(String roles)
    {
        this.roles = roles;
    }

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

}
