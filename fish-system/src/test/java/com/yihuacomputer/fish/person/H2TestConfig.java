package com.yihuacomputer.fish.person;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;

@Configuration
@Import({DomainModule.class,PersonCoreModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/person/spring-person-core-h2.xml")
public class H2TestConfig {

}
