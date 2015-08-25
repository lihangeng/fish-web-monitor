package com.yihuacomputer.fish.api.permission;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IPermissionService {
	/**
	 * 添加权限
	 * @param id
	 * @return
	 */
	public IPermission make(String id);
	/**
	 * 获取权限，根据ID
	 * @param id
	 * @return
	 */
	public IPermission getById(String id);
	/**
	 * 根据Code获取权限信息
	 * @param code
	 * @return
	 */
	public IPermission get(String code);

	public IPermission getRoot();
	/**
	 * 增加权限
	 * @param permission
	 * @return
	 */
	public IPermission add(IPermission permission);
	/**
	 * 根据code，description增加权限
	 * @param code
	 * @param description
	 * @return
	 */

	public IPermission add(String id,String code, String description,boolean isButton);
	/**
	 * 删除
	 * @param id
	 */
	public void removeById(String id);
	/**
	 * 根据code，删除权限
	 * @param code
	 */
	public void remove(String code);
	/**
	 * 更新权限
	 * @param permission
	 */
	public void update(IPermission permission);
	/**
	 * 返回权限列表
	 * @return
	 */
	public List<IPermission> list();
	/**
	 * 分页
	 * @param offset
	 * @param limit
	 * @return
	 */
	public IPageResult<IPermission> page(int offset, int limit);
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IPermission> page(int offset, int limit,IFilter filter);

	/**
	 * 获得短信群发的权限
	 * @return
	 */
	public List<IPermission> getGroupSendByCode();

}
