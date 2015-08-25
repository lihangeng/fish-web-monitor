package com.yihuacomputer.fish.api.relation;

import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IUserRole;
import com.yihuacomputer.fish.api.person.IUser;

public interface IUserPermissionRelation {
	/**
	 * 建立user 与permission之间的关联
	 * @param user
	 * @param permission
	 */
	public void link(IUser user, IPermission permission);
	/**
	 * 解除user与permission之间的关联
	 * @param user
	 * @param permission
	 */
	public void unlink(IUser user, IPermission permission);
	/**
	 * 检查user与permission之间是否有关联
	 * @param user
	 * @param permission
	 * @return
	 */
	public boolean haslink(IUser user, IPermission permission);
	/**
	 * 返回user所具有的快捷菜单shortMenu列表
	 * @param user
	 * @return
	 */
	public List<IPermission> listShortMenuByUser(IUser user);
	
	/**
	 * 根据userId 找出用户所具有的所有的权限,不包含button
	 * @param userId
	 * @return
	 */
	public List<IPermission> listPermissionsByUser(long userId);
	/**
	 * 根据rolecode 找出用户所具有的所有的权限,不包含button
	 * @param userId
	 * @return
	 */
	public List<IPermission> listPermissionsByUser(String roleId);
	
	/**
	 * 根据userId 查找对应的roleCode
	 * @param userId
	 * @return
	 */
	public List<IUserRole> listRoleCodeByUserId(long userId);
}
