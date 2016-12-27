package com.yihuacomputer.fish.api.relation;

import java.util.List;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * @author YiHua
 *
 */
public interface IUserGroupRelation {

	/**
	 * @param user
	 * @param group
	 */
	public void link(IUser user, IGroup group);
	
	/**
	 * @param user
	 * @param group
	 */
	public void unlink(IUser user, IGroup group);
	
	/**
	 * @param user
	 * @return
	 */
	public List<IGroup> listGroupByUser(IUser user);
	
	/**
	 * @param offset
	 * @param limit
	 * @param user
	 * @return
	 */
	public IPageResult<IGroup> pageGroupByUser(int offset, int limit, IUser user);
	
	/**
	 * @param offset
	 * @param limit
	 * @param user
	 * @return
	 */
	public IPageResult<IGroup> pageUnlinkGroupByUser(int offset, int limit, IUser user);
	
	/**
	 * @param group
	 * @return
	 */
	public List<IUser> listUserByGroup(IGroup group);
	
	/**
	 * @param offset
	 * @param limit
	 * @param group
	 * @return
	 */
	public IPageResult<IUser> pageUserByGroup(int offset, int limit, IGroup group);
	
}
