package com.yihuacomputer.fish.permission.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.permission.entity.Permission;
import com.yihuacomputer.fish.permission.service.api.IDomainPermissionService;

@Component("permissionEntityInjector")
public class PermissionEntityInjector implements IEntityInjector {
	@Autowired
	private IDomainPermissionService domainPermissionService;

	@Override
	public void injectDependencies(Object entity) {
		if(entity instanceof Permission){
			Permission advert = (Permission)entity;
			advert.setService(domainPermissionService);
		}
	}
}
