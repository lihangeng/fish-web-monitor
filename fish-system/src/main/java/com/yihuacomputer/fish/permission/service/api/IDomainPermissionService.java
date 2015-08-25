package com.yihuacomputer.fish.permission.service.api;

import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.permission.entity.Permission;

public interface IDomainPermissionService extends IPermissionService {
	/**
	 * 增加权限
	 * @param code
	 * @param description
	 * @param parent
	 * @return
	 */
	public Permission add(String id,String code, String description,boolean isButton,Permission parent);
	/**
	 * 删除权限
	 * @param permission
	 */
	public void remove(Permission permission);
	/**
	 * 根据parentId返回子权限列表
	 * @param parentId
	 * @return
	 */
	public List<Permission> listChildByParentId(String parentId);
	
}
