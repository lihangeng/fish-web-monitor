package com.yihuacomputer.fish.api.relation;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IRole;

public interface IGroupRoleRelation {
	/**
	 * 建立组织与角色之间的关联
	 * @param group
	 * @param role
	 */
	public void link(IGroup group, IRole role);
	/**
	 * 解除组织与角色之间的关联
	 * @param group
	 * @param role
	 */
	public void unlink(IGroup group, IRole role);
	/**
	 * 返回组织下的角色列表
	 * @param group
	 * @return
	 */
	public List<IRole> listRoleByGroup(IGroup group);
	/**
	 * 返回角色所属的组织列表
	 * @param role
	 * @return
	 */
	public List<IGroup> listGroupByRole(IRole role);
	
	public IPageResult<IRole> pageRoleByGroup(int start, int limit, IGroup group,IFilter filter);

	public IPageResult<IRole> pageUnlinkRoleByGroup(int start, int limit, IGroup group,IFilter filter);
	
	
	
	
}
