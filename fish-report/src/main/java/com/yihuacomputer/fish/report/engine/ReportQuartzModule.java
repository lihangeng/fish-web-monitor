package com.yihuacomputer.fish.report.engine;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 报表定时任务模块配置
 * @author xuxiang
 *
 */
@Configuration
@ImportResource("classpath:/com/yihuacomputer/fish/report/report-quartz.xml")
public class ReportQuartzModule {

}
