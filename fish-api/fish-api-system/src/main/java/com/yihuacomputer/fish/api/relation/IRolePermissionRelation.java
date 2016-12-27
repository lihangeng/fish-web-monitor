package com.yihuacomputer.fish.api.relation;

import java.util.List;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;


/**
 * @author YiHua
 *
 */
public interface IRolePermissionRelation {
	/**
	 * 建立角色与权限之间的关联
	 * @param role
	 * @param permission
	 */
	public void link(IRole role, IPermission permission);
	/**
	 * 解除角色与权限之间的关联 
	 * @param role
	 * @param permission
	 */
	public void unlink(IRole role, IPermission permission);
	/**
	 * 检查角色与权限之间是否有关联 
	 * @param role
	 * @param permission
	 * @return
	 */
	public boolean haslink(IRole role, IPermission permission);
	/**
	 * 返回角色所具有的权限列表 
	 * @param role
	 * @return
	 */
	public List<IPermission> listPermissionByRole(IRole role);
	
	/**
	 * @param role
	 * @return
	 */
	public List<IPermission> listMenuByRole(IRole role);
	
	/**
	 * @param offset
	 * @param limit
	 * @param role
	 * @return
	 */
	public IPageResult<IPermission> pagePermissionByRole(int offset, int limit, IRole role);
	
	/**
	 * @param offset
	 * @param limit
	 * @param role
	 * @return
	 */
	public IPageResult<IPermission> pageUnlinkPermissionByRole(int offset, int limit, IRole role);
	/**
	 * 返回改权限下的角色列表
	 * @param permission
	 * @return
	 */
	public List<IRole> listRoleByPermission(IPermission permission);
	
}
