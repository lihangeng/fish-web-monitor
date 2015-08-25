package com.yihuacomputer.fish.permission;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.api.permission.IGroupService;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.permission.service.db.GroupService;
import com.yihuacomputer.fish.permission.service.db.PermissionEntityInjector;
import com.yihuacomputer.fish.permission.service.db.PermissionService;
import com.yihuacomputer.fish.permission.service.db.RoleService;

@Configuration
public class PersonPermissionModule {


	public @Bean IRoleService roleService(){
		return new RoleService();
	}

	public @Bean IPermissionService permissionService(){
		return new PermissionService();
	}

	public @Bean IGroupService groupService(){
		return new GroupService();
	}

	public @Bean IEntityInjector permissionEntityInjector(){
		return new PermissionEntityInjector();
	}

}
