package com.yihuacomputer.fish.fault;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;

@Configuration
@Import(value = { DomainModule.class, FaultModule.class })
@ImportResource("classpath:/com/yihuacomputer/fish/fault/spring-fault-h2.xml")
public class H2TestConfig {


}
