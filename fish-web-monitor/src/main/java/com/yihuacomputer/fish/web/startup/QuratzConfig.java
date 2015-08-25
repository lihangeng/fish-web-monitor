package com.yihuacomputer.fish.web.startup;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
//@Import({
//		MonitorQuartzModule.class,
//		ReportQuartzModule.class,
//		AtmLogQuartzModule.class,
//		JournalQuartzModule.class
//})
@ImportResource("classpath:/spring-quartz.xml")
public class QuratzConfig {

}
