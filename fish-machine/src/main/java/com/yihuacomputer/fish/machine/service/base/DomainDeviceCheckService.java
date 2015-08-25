package com.yihuacomputer.fish.machine.service.base;

import com.yihuacomputer.fish.machine.entity.device.Device;
import com.yihuacomputer.fish.machine.service.api.IDomainDeviceCheckService;

public abstract class DomainDeviceCheckService implements IDomainDeviceCheckService {

	@Override
	public Device make() {
		return new Device();
	}

}
