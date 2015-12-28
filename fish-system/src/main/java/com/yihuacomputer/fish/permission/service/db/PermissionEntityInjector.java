package com.yihuacomputer.fish.permission.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.system.entity.Permission;

@Component("permissionEntityInjector")
public class PermissionEntityInjector implements IEntityInjector {
	@Autowired
	private IPermissionService domainPermissionService;

	@Override
	public void injectDependencies(Object entity) {
		if(entity instanceof Permission){
			Permission advert = (Permission)entity;
			advert.setService(domainPermissionService);
		}
	}
}
