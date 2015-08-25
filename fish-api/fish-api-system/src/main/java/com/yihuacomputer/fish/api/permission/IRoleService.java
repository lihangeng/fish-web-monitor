package com.yihuacomputer.fish.api.permission;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IRoleService {

	/**
	 * 创建角色实例
	 * @param name
	 * @return
	 */
	public IRole make(String name);
	/**
	 * 根据Id获取角色实例
	 * @param id
	 * @return
	 */
	public IRole get(long id);

	/**
	 * 添加角色
	 * @param name
	 * @return
	 */
	public IRole add(String name);
	/**
	 * 添加角色
	 * @param role
	 * @return
	 */
	public IRole add(IRole role);
	/**
	 * 删除角色
	 * @param id
	 */
	public void remove(long id);
	/**
	 * 根据角色名删除
	 * @param name
	 */
	public void remove(String name);

	/**
	 * 更新角色
	 * @param role
	 */
	public void update(IRole role);
	/**
	 * 返回角色列表
	 * @return
	 */
	public List<IRole> list();
	/**
	 * 根据过滤条件返回角色列表
	 * @param filter
	 * @return
	 */
	public List<IRole> list(IFilter filter);

	/**
	 * 分页
	 * @param offset
	 * @param limit
	 * @return
	 */
	public IPageResult<IRole> page(int offset, int limit);
	/**
	 * 查询分页   LDAP需要用到
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IRole> page(int offset, int limit,IFilter filter);

	/**
	 *  根据角色名称获取角色实例  LDAP需要用到
	 * @param name
	 * @return
	 */
	public IRole get(String name);

	/**
	 * 根据角色对象删除   LDAP需要用到
	 * @param irole
	 */
	public void remove(IRole irole);

	/**
	 * 根据角色编号查找角色 LDAP需要用到
	 * @param roleCode
	 * @return
	 */
	public IRole getByCode(String roleCode);

	/**
	 *  根据角色名称获取角色信息  LDAP需要用到
	 * @param name
	 * @return
	 */
	public IRole getByName(String roleName);

}
