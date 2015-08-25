package com.yihuacomputer.fish.api.relation;

import java.util.List;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.person.IUser;

public interface IUserGroupRelation {

	public void link(IUser user, IGroup group);
	
	public void unlink(IUser user, IGroup group);
	
	public List<IGroup> listGroupByUser(IUser user);
	
	public IPageResult<IGroup> pageGroupByUser(int offset, int limit, IUser user);
	
	public IPageResult<IGroup> pageUnlinkGroupByUser(int offset, int limit, IUser user);
	
	public List<IUser> listUserByGroup(IGroup group);
	
	public IPageResult<IUser> pageUserByGroup(int offset, int limit, IGroup group);
	
}
