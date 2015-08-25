package com.yihuacomputer.fish.api.expand;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.IUser;


public interface IUserExpand {
	
	public IUser getUser();

	//这个user下所有的组
	public List<IGroup> listGroup();
	
	//这个user下所有的角色
	public List<IRole> listRole();
	public List<IRole> listRole(IFilter filter);
	
	//平台管理角色
	public List<IPermission> listPermission();
	
}
