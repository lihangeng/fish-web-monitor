package com.yihuacomputer.fish.api.permission;

import java.util.List;

import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IOrganization;

public interface IGroup {

	public long getId();
	
	public void setId(long id);
	/**
	 * 返回名称
	 * @return
	 */
	public String getName();
	
	public void setName(String name);
	/**
	 * 列出所有用户
	 * @return
	 */
	public List<IUser> listUser();
	/**
	 * 列出所有的角色
	 * @return
	 */
	public List<IRole> listRole();
	/**
	 * 返回组织机构
	 * @return
	 */
	public IOrganization getOrganization();
	
	public void setOrganization(IOrganization organization);
	
}
