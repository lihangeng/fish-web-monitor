package com.yihuacomputer.fish.batch;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@Import({DomainModule.class})
@ImportResource(locations = {
		"classpath:/com/yihuacomputer/fish/batch/batch_core.xml",
		"classpath:/com/yihuacomputer/fish/batch/spring-batch-h2.xml"})
public class H2TestConfig {

}
