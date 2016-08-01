package com.yihuacomputer.fish.analysis;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.domain.DomainMultModule;
import com.yihuacomputer.fish.analysis.AnalyisisModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import({DomainModule.class,SystemModule.class,AnalyisisModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/analysis/spring-analysis-mysql.xml")
public class MySQLTestConfig {

}
