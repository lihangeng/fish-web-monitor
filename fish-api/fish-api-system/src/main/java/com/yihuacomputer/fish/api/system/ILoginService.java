package com.yihuacomputer.fish.api.system;

import com.yihuacomputer.fish.api.person.IUser;

public interface ILoginService {

	public ILoginResult login(String code, String password);
	
	public String makeDynamicPassword(IUser master);
	
	public String verifyDynamicPassword(IUser master, String dynamicPassword);
	
}
