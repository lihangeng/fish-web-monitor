package com.yihuacomputer.fish.permission.service.api;

import java.util.List;

import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IGroupService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * @author 樊晓雨
 * @version
 * @date 2010-8-25
 */
public interface IDomainGroupService extends IGroupService{

	public List<IUser> listUser(IGroup group);

	public List<IRole> listRole(IGroup group);

}
