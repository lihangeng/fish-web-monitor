/**
 * 
 */
package com.yihuacomputer.fish.version.service.api;

import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;

/**
 * @author xuxigang
 *
 */
public interface IDomainDeviceSoftVersionService extends IDeviceSoftVersionService{
	public IVersion findVersion(String typeName, String versionNo);
}
