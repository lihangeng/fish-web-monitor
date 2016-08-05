package com.yihuacomputer.fish.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.sms.SMSModule;

@Configuration
@Import({DomainModule.class,SMSModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/system/spring-sms-mysql.xml")
public class MySQLTestConfig {

}
