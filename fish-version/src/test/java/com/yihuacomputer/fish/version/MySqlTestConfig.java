package com.yihuacomputer.fish.version;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.person.PersonCoreModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import({DomainModule.class,PersonCoreModule.class,MachineModule.class,SystemModule.class,VersionModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/version/spring-version-mysql.xml")
public class MySqlTestConfig {

}
