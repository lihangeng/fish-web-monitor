/**
 * 
 */
package com.yihuacomputer.fish.version.service.api;

import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IVersionService;

/**
 * @author xuxigang
 *
 */
public interface IDomainVersionService extends IVersionService{
	public IUserService getUserService();
}
