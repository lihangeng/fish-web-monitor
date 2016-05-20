package com.yihuacomputer.fish.api.relation;


import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * 用户-角色使用授权
 *
 * @author xuxigang
 * @version
 * @since  
 */
public interface IUserRoleRelation {
	
	/**
	 * 用户和角色建立关系
	 * @param user 用户
	 * @param role 角色
	 */
	public void link(IUser user, IRole role);
	
	/**
	 * 用户和角色解除关系
	 * @param user 用户
	 * @param role 角色
	 */
	public void unlink(IUser user, IRole role);
	
	public IPageResult<IRole> pageUnlinkRoleByUser(int offset, int limit, IUser user,IFilter filter);

	/**
	 * 列出某账号的所有关联可以使用的角色
	 * 
	 * @param user
	 * @return
	 */
	public List<IRole> listRoleByUser(IUser user);
	
	/**
	 * 根据用户ID查找用户菜单功能
	 * @return
	 */
	public List<IPermission> listUserPermission(long userId);
	
	/**
	 * 根据用户ID和父类菜单权限ID获取直接的子菜单权限
	 * @param userId
	 * @param parentPermissionId 父类菜单权限ID
	 * @return
	 * @since 2.1.0.6
	 */
	public List<IPermission> findDirectChildPermissionsByUser(long userId,String parentPermissionId);
	
	/**
	 * 根据用户ID和父类菜单权限ID获取所有子类的菜单权限
	 * @param userId
	 * @param parentPermissionId 父类菜单权限ID
	 * @return
	 *  @since 2.1.0.6
	 */
	public List<IPermission> findAllChildPermissionsByUser(long userId,String parentPermissionId);

	/**
	 * 根据某账号的所有关联可以使用角色分页
	 * 
	 * @param offset
	 * @param limit
	 * @param user 选中用户
	 * @param filter
	 * @return
	 */
	public IPageResult<IRole> pageRoleByUser(int offset, int limit, IUser user, IFilter filter);
	
	public List<IUser> listUserByRole(IRole role);

}
