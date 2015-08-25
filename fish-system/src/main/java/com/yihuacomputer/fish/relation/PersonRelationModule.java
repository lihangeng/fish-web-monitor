package com.yihuacomputer.fish.relation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.relation.IRelationService;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserGroupRelation;
import com.yihuacomputer.fish.api.relation.IUserPermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.relation.service.db.RelationService;
import com.yihuacomputer.fish.relation.service.db.RolePermissionRelation;
import com.yihuacomputer.fish.relation.service.db.UserGroupRelation;
import com.yihuacomputer.fish.relation.service.db.UserPermissionRelation;
import com.yihuacomputer.fish.relation.service.db.UserRoleRelation;

@Configuration
public class PersonRelationModule {

	@Bean
	public IRelationService relationService() {
		return new RelationService();
	}

	@Bean
	public IUserRoleRelation userRoleRelation() {
		return new UserRoleRelation();
	}

	@Bean
	public IUserPermissionRelation userPermissionRelation() {
		return new UserPermissionRelation();
	}

	@Bean
	public IRolePermissionRelation rolePermissionRelation() {
		return new RolePermissionRelation();
	}

	@Bean
	public IUserGroupRelation userGroupRelation() {
		return new UserGroupRelation();
	}


}
