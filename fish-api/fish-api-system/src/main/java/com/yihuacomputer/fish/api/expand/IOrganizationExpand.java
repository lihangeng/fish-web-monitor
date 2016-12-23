package com.yihuacomputer.fish.api.expand;

import java.util.List;

import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * @author YiHua
 *
 */
public interface IOrganizationExpand {
	
	/**
	 * @return
	 */
	public IOrganization getOrganization();
	
	/**
	 * @return
	 */
	public IOrganizationExpand getParent();
	
	/**
	 * @return
	 */
	public Iterable<IOrganizationExpand> listChildren();

	/**
	 * @return
	 */
	public Iterable<IPerson> listPerson();
	
	/**
	 * @return
	 */
	public Iterable<IUser> listUser();

	/**
	 * 
	 * @return 获得当前组织下的所有的子组织信息(直接和间接的组织)
	 * 
	 */
	public List<IOrganization> listAllChildren();
	
}
