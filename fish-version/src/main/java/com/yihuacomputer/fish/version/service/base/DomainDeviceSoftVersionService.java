package com.yihuacomputer.fish.version.service.base;

import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.service.api.IDomainDeviceSoftVersionService;

/**
 * @author YiHua
 *
 */
public abstract class DomainDeviceSoftVersionService implements IDomainDeviceSoftVersionService {
	
	@Override
	public IDeviceSoftVersion make() {
		return new DeviceSoftVersion(this);
	}
}
