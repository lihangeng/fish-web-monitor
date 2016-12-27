package com.yihuacomputer.fish.api.system;

import java.util.List;

import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.person.IUser;

/**
 * @author YiHua
 *
 */
public interface ILoginResult {

	/**
	 * @return
	 */
	public boolean isSuccess();
	
	/**
	 * @return
	 */
	public IUser getMaster();
	
	/**
	 * @return
	 */
	public List<IPermission> listPermission();
	
	/**
	 * @return
	 */
	public String getReason();
	
}
