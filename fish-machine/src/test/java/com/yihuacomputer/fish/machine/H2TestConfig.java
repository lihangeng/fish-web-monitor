package com.yihuacomputer.fish.machine;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.person.PersonCoreModule;

@Configuration
@Import({DomainModule.class,PersonCoreModule.class,MachineModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/machine/spring-machine-h2.xml")
public class H2TestConfig {

}
