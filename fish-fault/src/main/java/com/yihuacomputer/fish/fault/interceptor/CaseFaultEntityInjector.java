package com.yihuacomputer.fish.fault.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.fault.entity.CaseFault;

@Component("caseFaultEntityInjector")
public class CaseFaultEntityInjector implements IEntityInjector {
	@Autowired
	private IDevicePersonRelation devicePersonService;
	@Autowired
	private IDeviceService deviceService;

	@Override
	public void injectDependencies(Object entity) {
		if(entity instanceof CaseFault){
			CaseFault caseFault = (CaseFault)entity;
			caseFault.setDevicePersonService(devicePersonService);
			caseFault.setDeviceService(deviceService);
		}
	}
}
