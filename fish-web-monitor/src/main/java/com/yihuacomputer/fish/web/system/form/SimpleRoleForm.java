package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.RoleType;
/**
 * 
 * @author xuxigang
 * @version
 * @since  
 * @date 2011-1-4
 */
public class SimpleRoleForm {
    
	/**
	 * ID
	 */
	public long id;
	/**
	 * 名称
	 */
	public String Name;
	private RoleType roleType;

	public SimpleRoleForm() {
	}
	
	/**
	 * @param role
	 * @return 
	 */
	public SimpleRoleForm(IRole role) {
		id = role.getId();
		Name = role.getName();
		roleType = role.getType();
	}

	
	/**
	 * 
	 * 方法描述 : 转换
	 * 
	 * @param roles
	 * @return List
	 */

	public static List<SimpleRoleForm> convert(List<IRole> roles) {
		List<SimpleRoleForm> result = new ArrayList<SimpleRoleForm>();
		for(IRole role : roles) {
			result.add(new SimpleRoleForm(role));
		}
		return result;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}
	
	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
}
