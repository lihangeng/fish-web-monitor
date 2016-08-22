package com.yihuacomputer.fish.batch;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@Import({DomainModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/batch/batch_core.xml")
public class H2TestConfig {

}
