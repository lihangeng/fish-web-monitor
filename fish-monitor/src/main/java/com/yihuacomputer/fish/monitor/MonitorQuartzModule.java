package com.yihuacomputer.fish.monitor;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author YiHua
 *
 */
@Configuration
@ImportResource("classpath:/com/yihuacomputer/fish/monitor/monitor-quartz.xml")
public class MonitorQuartzModule {

}
