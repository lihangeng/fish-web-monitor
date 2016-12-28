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
import com.yihuacomputer.fish.api.session.ISessionManage;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.api.system.im.IAnnouncementService;
import com.yihuacomputer.fish.api.system.im.IMessageService;
import com.yihuacomputer.fish.api.system.quartz.IJobSynchService;
import com.yihuacomputer.fish.api.system.quartz.IQuartzService;
import com.yihuacomputer.fish.api.system.sms.IShortMessageService;
import com.yihuacomputer.fish.permission.service.db.GroupService;
import com.yihuacomputer.fish.permission.service.db.PermissionEntityInjector;
import com.yihuacomputer.fish.permission.service.db.PermissionService;
import com.yihuacomputer.fish.permission.service.db.RoleService;
import com.yihuacomputer.fish.person.interceptor.PersonCoreEntityInjector;
import com.yihuacomputer.fish.person.service.api.IDomainUserService;
import com.yihuacomputer.fish.person.service.db.OrganizationService;
import com.yihuacomputer.fish.person.service.db.PersonJobService;
import com.yihuacomputer.fish.person.service.db.PersonService;
import com.yihuacomputer.fish.person.service.db.SrcbDevicePersonRelation;
import com.yihuacomputer.fish.person.service.db.UserLogService;
import com.yihuacomputer.fish.person.service.db.UserService;
import com.yihuacomputer.fish.relation.service.db.RelationService;
import com.yihuacomputer.fish.relation.service.db.RolePermissionRelation;
import com.yihuacomputer.fish.relation.service.db.UserGroupRelation;
import com.yihuacomputer.fish.relation.service.db.UserPermissionRelation;
import com.yihuacomputer.fish.relation.service.db.UserRoleRelation;
import com.yihuacomputer.fish.session.SessionManage;
import com.yihuacomputer.fish.system.quartz.QuartzService;
import com.yihuacomputer.fish.system.service.AnnouncementService;
import com.yihuacomputer.fish.system.service.JobSynchService;
import com.yihuacomputer.fish.system.service.MessageService;
import com.yihuacomputer.fish.system.service.ParamService;
import com.yihuacomputer.fish.system.service.ShortMessageService;

/**
 * @author YiHua
 *
 */
@Configuration
public class SystemModule {
	/**
	 * @return
	 */
	@Bean
	public IAnnouncementService announcementService() {
		return new AnnouncementService();
	}

	/**
	 * @return
	 */
	@Bean
	public IMessageService messageService() {
		return new MessageService();
	}

	/**
	 * @return
	 */
	@Bean
	public IParamService paramService() {
		return new ParamService();
	}

	/**
	 * @return
	 */
	@Bean
	public IShortMessageService shortMessageService() {
		return new ShortMessageService();
	}

	/**
	 * @return
	 */
	@Bean
	public IJobSynchService jobSynchService() {
		return new JobSynchService();
	}

	/**
	 * @return
	 */
	public @Bean IRoleService roleService() {
		return new RoleService();
	}

	/**
	 * @return
	 */
	public @Bean IPermissionService permissionService() {
		return new PermissionService();
	}
	
	/**
	 * @return
	 */
	public @Bean IOrganizationService organizationService() {
		return new OrganizationService();
	}
	
	/**
	 * @return
	 */
	public @Bean IPersonService personService() {
		return new PersonService();
	}

	/**
	 * @return
	 */
	public @Bean IUserService userService() {
		return new UserService();
	}

	/**
	 * @return
	 */
	public @Bean IDomainUserService domainUserService() {
		return new UserService();
	}
	
	/**
	 * @return
	 */
	public @Bean IUserLogService userLogService() {
		return new UserLogService();
	}

	/**
	 * @return
	 */
	public @Bean IPersonJobService personJobService() {
		return new PersonJobService();
	}

	/**
	 * @return
	 */
	public @Bean IEntityInjector personCoreEntityInjector() {
		return new PersonCoreEntityInjector();
	}

	/**
	 * @return
	 */
	public @Bean IGroupService groupService() {
		return new GroupService();
	}

	/**
	 * @return
	 */
	public @Bean IEntityInjector permissionEntityInjector() {
		return new PermissionEntityInjector();
	}

	/**
	 * @return
	 */
	@Bean
	public IRelationService relationService() {
		return new RelationService();
	}

	/**
	 * @return
	 */
	@Bean
	public IUserRoleRelation userRoleRelation() {
		return new UserRoleRelation();
	}

	/**
	 * @return
	 */
	@Bean
	public IUserPermissionRelation userPermissionRelation() {
		return new UserPermissionRelation();
	}

	/**
	 * @return
	 */
	@Bean
	public IRolePermissionRelation rolePermissionRelation() {
		return new RolePermissionRelation();
	}

	/**
	 * @return
	 */
	@Bean
	public IUserGroupRelation userGroupRelation() {
		return new UserGroupRelation();
	}
	/**
	 * @return
	 */
	@Bean
	public SrcbDevicePersonRelation srcbDevicePersonRelation() {
		return new SrcbDevicePersonRelation();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IQuartzService quartzService() {
		return new QuartzService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public ISessionManage sessionManage() {
		return new SessionManage();
	}

}
