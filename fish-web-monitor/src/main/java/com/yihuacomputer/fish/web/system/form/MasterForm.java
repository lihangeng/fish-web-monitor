package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;

/**
 * @author 樊晓雨
 * @version
 * @date 2010-8-28
 */
public class MasterForm {
	private Long id;
	private String code;
	private UserState masterState;
	private String userGuid;
	private String name;
	private String mobile;
	private String email;
	private String organization;
	private String organizationCode;
	private Long permitRuleId;
	private Long loginSecurityRuleId;
	private String permitRule;
	private String loginSecurityRule;
	private String validTime;
	private boolean createCommonRole;
	private String passwordRule;
	private Long passwordRuleId;
	private boolean createFortressSlave;


	public MasterForm() {
	}

	/**
	 * @param master
	 */
	public MasterForm(IUser master){
		this.id=master.getId();
		this.code=master.getCode();
		this.masterState=master.getState();
		this.userGuid=master.getPerson().getGuid();
		this.name=master.getPerson().getName();
		this.mobile=master.getPerson().getMobile();
		this.email=master.getPerson().getEmail();
		this.organization=master.getPerson().getOrganization().getName();
		this.organizationCode=master.getPerson().getOrganization().getCode();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserGuid() {
		return userGuid;
	}

	public void setUserGuid(String userGuid) {
		this.userGuid = userGuid;
	}

	public UserState getMasterState() {
		return masterState;
	}

	public void setMasterState(UserState masterState) {
		this.masterState = masterState;
	}

	public Long getPermitRuleId() {
		return permitRuleId;
	}

	public void setPermitRuleId(Long permitRuleId) {
		this.permitRuleId = permitRuleId;
	}

	public Long getLoginSecurityRuleId() {
		return loginSecurityRuleId;
	}

	public void setLoginSecurityRuleId(Long loginSecurityRuleId) {
		this.loginSecurityRuleId = loginSecurityRuleId;
	}

	public String getPermitRule() {
		return permitRule;
	}

	public void setPermitRule(String permitRule) {
		this.permitRule = permitRule;
	}

	public String getLoginSecurityRule() {
		return loginSecurityRule;
	}

	public void setLoginSecurityRule(String loginSecurityRule) {
		this.loginSecurityRule = loginSecurityRule;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserState getStatus() {
		return masterState;
	}

	public void setStatus(UserState masterState) {
		this.masterState = masterState;
	}

	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}



	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	/**
	 * 方法描述 : 增加
	 * @param service
	 * @param userService
	 * @param roleService
	 * @param masterRoleRelation
	 */
	public void add(IUserService service,IPersonService userService, IRoleService roleService, IUserRoleRelation masterRoleRelation){
		IUser master = service.make();
		master.setCode(code);
		IPerson user = userService.get(userGuid);
		master.setPerson(user);
		master.setPlainText("jsct@123");
		IFilter filter=new Filter();
		filter.eq("def", true);

		service.add(master);
		id = master.getId();
		this.mobile = master.getPerson().getMobile();
		this.email = master.getPerson().getEmail();
		this.masterState = master.getState();
		if(createCommonRole){
		    IFilter filter1=new Filter();
		    filter1.eq("name", "普通用户");
		    filter1.eq("type", RoleType.PERMISSION);

		    List<IRole> roles = roleService.list(filter1);
		    if(roles!=null && !roles.isEmpty()){
		        masterRoleRelation.link(master, roles.get(0));
		    }
		}
	}

	/**
	 * 方法描述 : 更新
	 * @param service
	 * @param userService
	 */
	public void update(IUserService service,IPersonService userService){
	    IUser master = service.get(id);
        master.setCode(code);
        master.setPerson(userService.get(userGuid));
        IFilter filter=new Filter();
        filter.eq("def", true);

        service.update(master);
        this.mobile = master.getPerson().getMobile();
        this.email = master.getPerson().getEmail();
        this.masterState = master.getState();
        this.organization=master.getPerson().getOrganization().getName();
        this.organizationCode=master.getPerson().getOrganization().getCode();

	}


	/**
	 *
	 * 方法描述 : 转换
	 *
	 * @param resources
	 * @return List
	 */

	public static List<MasterForm> convert(List<IUser> resources) {
		List<MasterForm> result = new ArrayList<MasterForm>();
		for(IUser resource : resources) {
			result.add(new MasterForm(resource));
		}
		return result;
	}

    /**
     * createCommonRole
     *
     * @return  the createCommonRole
     * @version 0.9
     * @since     0.8
     */

    public boolean isCreateCommonRole() {
        return createCommonRole;
    }

    /**
     * createCommonRole
     * @param createCommonRole
     */

    public void setCreateCommonRole(boolean createCommonRole) {
        this.createCommonRole = createCommonRole;
    }

    /**
     * passwordRuleId
     *
     * @return  the passwordRuleId
     * @since
     */

    public Long getPasswordRuleId() {
        return passwordRuleId;
    }

    /**
     * passwordRuleId
     * @param passwordRuleId
     */

    public void setPasswordRuleId(Long passwordRuleId) {
        this.passwordRuleId = passwordRuleId;
    }

    /**
     * passwordRule
     *
     * @return  the passwordRule
     * @since
     */

    public String getPasswordRule() {
        return passwordRule;
    }

    /**
     * passwordRule
     * @param passwordRule
     */

    public void setPasswordRule(String passwordRule) {
        this.passwordRule = passwordRule;
    }

    /**
     * createFortressSlave
     *
     * @return  the createFortressSlave
     * @since
     */

    public boolean isCreateFortressSlave() {
        return createFortressSlave;
    }

    /**
     * createFortressSlave
     * @param createFortressSlave
     */

    public void setCreateFortressSlave(boolean createFortressSlave) {
        this.createFortressSlave = createFortressSlave;
    }
}
