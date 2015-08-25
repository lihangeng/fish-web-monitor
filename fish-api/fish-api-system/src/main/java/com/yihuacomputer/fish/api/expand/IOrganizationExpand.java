package com.yihuacomputer.fish.api.expand;

import java.util.List;

import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IUser;

public interface IOrganizationExpand {
	
	public IOrganization getOrganization();
	
	public IOrganizationExpand getParent();
	
	public Iterable<IOrganizationExpand> listChildren();

	public Iterable<IPerson> listPerson();
	
	public Iterable<IUser> listUser();

	/**
	 * 
	 * @return 获得当前组织下的所有的子组织信息(直接和间接的组织)
	 * 
	 */
	public List<IOrganization> listAllChildren();
	
}
