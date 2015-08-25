package com.yihuacomputer.fish.api.system;

import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.person.IUser;

public interface ILoginResult {

	public boolean isSuccess();
	
	public IUser getMaster();
	
	public List<IPermission> listPermission();
	
	public String getReason();
	
}
