package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.monitor.MonitorModule;
import com.yihuacomputer.fish.report.base.ReportBaseModule;
import com.yihuacomputer.fish.report.engine.ReportEngineModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import(value = {DomainModule.class,SystemModule.class,MachineModule.class,MonitorModule.class,ReportEngineModule.class,ReportBaseModule.class})
@ImportResource(value = "classpath:/com/yihuacomputer/fish/report/spring-report-mysql.xml")
public class MysqlTestConfig {

}
