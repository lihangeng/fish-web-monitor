package com.yihuacomputer.fish.api.expand;

import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IOrganization;

public interface IExpandFactory {

	public IOrganizationExpand getOrganizationExpand(IOrganization organization);
	
	public IOrganizationExpand getOrganizationExpand(String organizationCode);
	
	public IUserExpand getUserExpand(IUser user);
	
	public IUserExpand getUserExpand(String userCode);
	
}
