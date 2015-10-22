package com.yihuacomputer.fish.permission.service.api;

import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.system.entity.Role;

/**
 * @author 樊晓雨
 * @version
 * @date 2010-8-24
 */
public interface IDomainRoleService extends IRoleService{

	public void remove(Role role);

	public List<IPermission> listPermission(IRole role);

}
