package com.yihuacomputer.fish.api.system;

import com.yihuacomputer.fish.api.person.IUser;

/**
 * @author YiHua
 *
 */
public interface ILoginService {

	/**
	 * @param code
	 * @param password
	 * @return
	 */
	public ILoginResult login(String code, String password);
	
	/**
	 * @param master
	 * @return
	 */
	public String makeDynamicPassword(IUser master);
	
	/**
	 * @param master
	 * @param dynamicPassword
	 * @return
	 */
	public String verifyDynamicPassword(IUser master, String dynamicPassword);
	
}
