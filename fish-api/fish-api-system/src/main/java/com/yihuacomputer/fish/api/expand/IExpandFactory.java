package com.yihuacomputer.fish.api.expand;

import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IOrganization;

/**
 * @author YiHua
 *
 */
public interface IExpandFactory {

	/**
	 * @param organization
	 * @return
	 */
	public IOrganizationExpand getOrganizationExpand(IOrganization organization);
	
	/**
	 * @param organizationCode
	 * @return
	 */
	public IOrganizationExpand getOrganizationExpand(String organizationCode);
	
	/**
	 * @param user
	 * @return
	 */
	public IUserExpand getUserExpand(IUser user);
	
	/**
	 * @param userCode
	 * @return
	 */
	public IUserExpand getUserExpand(String userCode);
	
}
