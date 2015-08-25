package com.yihuacomputer.fish.machine.service.base;

import com.yihuacomputer.fish.machine.entity.device.Device;
import com.yihuacomputer.fish.machine.service.api.IDomainDeviceService;

public abstract class DomainDeviceService implements IDomainDeviceService {

	@Override
	public Device make() {
		return new Device(this);
	}

}
