package com.yihuacomputer.fish.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;

@Configuration
@Import({DomainModule.class,SystemModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/system/spring-relation-db2.xml")
public class DB2TestConfig {

}
