package com.yihuacomputer.fish.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.api.permission.IGroupService;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPersonJobService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUserLogService;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.relation.IRelationService;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserGroupRelation;
import com.yihuacomputer.fish.api.relation.IUserPermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.api.system.im.IAnnouncementService;
import com.yihuacomputer.fish.api.system.im.IMessageService;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;
import com.yihuacomputer.fish.api.system.sms.IShortMessageService;
import com.yihuacomputer.fish.permission.service.db.GroupService;
import com.yihuacomputer.fish.permission.service.db.PermissionEntityInjector;
import com.yihuacomputer.fish.permission.service.db.PermissionService;
import com.yihuacomputer.fish.permission.service.db.RoleService;
import com.yihuacomputer.fish.person.interceptor.PersonCoreEntityInjector;
import com.yihuacomputer.fish.person.service.api.IDomainUserService;
import com.yihuacomputer.fish.person.service.db.SrcbDevicePersonRelation;
import com.yihuacomputer.fish.person.service.db.OrganizationService;
import com.yihuacomputer.fish.person.service.db.PersonJobService;
import com.yihuacomputer.fish.person.service.db.PersonService;
import com.yihuacomputer.fish.person.service.db.UserLogService;
import com.yihuacomputer.fish.person.service.db.UserService;
import com.yihuacomputer.fish.relation.service.db.RelationService;
import com.yihuacomputer.fish.relation.service.db.RolePermissionRelation;
import com.yihuacomputer.fish.relation.service.db.UserGroupRelation;
import com.yihuacomputer.fish.relation.service.db.UserPermissionRelation;
import com.yihuacomputer.fish.relation.service.db.UserRoleRelation;
import com.yihuacomputer.fish.system.service.AnnouncementService;
import com.yihuacomputer.fish.system.service.JobSynchService;
import com.yihuacomputer.fish.system.service.MessageService;
import com.yihuacomputer.fish.system.service.ParamService;
import com.yihuacomputer.fish.system.service.ShortMessageService;

@Configuration
public class SystemModule {
	@Bean
	public IAnnouncementService announcementService() {
		return new AnnouncementService();
	}

	@Bean
	public IMessageService messageService() {
		return new MessageService();
	}

	@Bean
	public IParamService paramService() {
		return new ParamService();
	}

	@Bean
	public IShortMessageService shortMessageService() {
		return new ShortMessageService();
	}

	@Bean
	public IJobSynchService jobSynchService() {
		return new JobSynchService();
	}

	public @Bean IRoleService roleService() {
		return new RoleService();
	}

	public @Bean IPermissionService permissionService() {
		return new PermissionService();
	}
	
	public @Bean IOrganizationService organizationService() {
		return new OrganizationService();
	}

	public @Bean IPersonService personService() {
		return new PersonService();
	}

	public @Bean IUserService userService() {
		return new UserService();
	}

	public @Bean IDomainUserService domainUserService() {
		return new UserService();
	}
	
	public @Bean IUserLogService userLogService() {
		return new UserLogService();
	}

	public @Bean IPersonJobService personJobService() {
		return new PersonJobService();
	}

	public @Bean IEntityInjector personCoreEntityInjector() {
		return new PersonCoreEntityInjector();
	}

	public @Bean IGroupService groupService() {
		return new GroupService();
	}

	public @Bean IEntityInjector permissionEntityInjector() {
		return new PermissionEntityInjector();
	}

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
	@Bean
	public SrcbDevicePersonRelation srcbDevicePersonRelation() {
		return new SrcbDevicePersonRelation();
	}

}
