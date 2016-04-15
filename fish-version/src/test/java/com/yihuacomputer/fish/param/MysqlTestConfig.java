package com.yihuacomputer.fish.param;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.system.SystemModule;
import com.yihuacomputer.fish.version.VersionModule;

@Configuration
@Import({DomainModule.class,SystemModule.class,MachineModule.class,SystemModule.class,VersionModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/version/spring-version-mysql.xml")
public class MysqlTestConfig {

}
