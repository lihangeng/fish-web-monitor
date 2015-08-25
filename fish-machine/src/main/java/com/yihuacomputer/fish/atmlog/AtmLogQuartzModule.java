package com.yihuacomputer.fish.atmlog;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * ATM日志定时任务模块配置
 * @author xuxiang
 * @since 1.4.0
 */
@Configuration
@ImportResource("classpath:/com/yihuacomputer/fish/atmlog/atmlog-quartz.xml")
public class AtmLogQuartzModule {

}
