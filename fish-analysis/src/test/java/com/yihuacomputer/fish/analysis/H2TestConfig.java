package com.yihuacomputer.fish.analysis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.analysis.AnalyisisModule;

@Configuration
@Import(value = { DomainModule.class, AnalyisisModule.class })
@ImportResource("classpath:/com/yihuacomputer/fish/fault/spring-fault-h2.xml")
public class H2TestConfig {


}
