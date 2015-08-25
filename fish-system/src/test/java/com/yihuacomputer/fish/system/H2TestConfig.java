package com.yihuacomputer.fish.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;

@Configuration
@Import({DomainModule.class,SystemModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/system/spring-system-h2.xml")
public class H2TestConfig {

}
