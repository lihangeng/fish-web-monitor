package com.yihuacomputer.fish.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.domain.interceptor.IEntityInjector;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPersonJobService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUserLogService;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.person.interceptor.PersonCoreEntityInjector;
import com.yihuacomputer.fish.person.service.db.OrganizationService;
import com.yihuacomputer.fish.person.service.db.PersonJobService;
import com.yihuacomputer.fish.person.service.db.PersonService;
import com.yihuacomputer.fish.person.service.db.UserLogService;
import com.yihuacomputer.fish.person.service.db.UserService;

@Configuration
public class PersonCoreModule {

	public @Bean IOrganizationService organizationService(){
		return new OrganizationService();
	}

	public @Bean IPersonService personService(){
		return new PersonService();
	}

	public @Bean IUserService userService(){
		return new UserService();
	}

	public @Bean IUserLogService userLogService(){
		return new UserLogService();
	}

	public @Bean IPersonJobService personJobService(){
		return new PersonJobService();
	}

	public @Bean IEntityInjector personCoreEntityInjector(){
		return new PersonCoreEntityInjector();
	}

}
