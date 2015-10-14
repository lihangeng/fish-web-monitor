package com.yihuacomputer.fish.monitor;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import(value = {DomainModule.class,SystemModule.class,MachineModule.class,MonitorModule.class})
@ImportResource("classpath:/com/yihuacomputer/fish/monitor/spring-monitor-h2.xml")
public class H2TestConfig {


}
