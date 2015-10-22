package com.yihuacomputer.fish.atmlog;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import({DomainModule.class,SystemModule.class,MachineModule.class,AtmLogModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/atmlog/spring-atmlog-h2.xml")
public class H2TestConfig {

}
