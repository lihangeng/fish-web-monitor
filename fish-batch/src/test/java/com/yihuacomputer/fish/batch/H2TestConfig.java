package com.yihuacomputer.fish.batch;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;

@Configuration
@Import({DomainModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/batch/batch-core.xml")
public class H2TestConfig {

}
