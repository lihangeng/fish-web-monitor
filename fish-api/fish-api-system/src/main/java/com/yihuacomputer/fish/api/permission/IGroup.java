package com.yihuacomputer.fish.api.permission;

import java.util.List;

import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * @author YiHua
 *
 */
public interface IGroup {

	/**
	 * @return
	 */
	public long getId();
	
	/**
	 * @param id
	 */
	public void setId(long id);
	/**
	 * 返回名称
	 * @return
	 */
	public String getName();
	
	/**
	 * @param name
	 */
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
	
	/**
	 * @param organization
	 */
	public void setOrganization(IOrganization organization);
	
}
