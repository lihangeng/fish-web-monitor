package com.yihuacomputer.fish.relation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.permission.PersonPermissionModule;
import com.yihuacomputer.fish.person.PersonCoreModule;

@Configuration
@Import({DomainModule.class,
		PersonPermissionModule.class,
		PersonRelationModule.class,
		PersonCoreModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/relation/spring-relation-h2.xml")
public class H2TestConfig {

}
