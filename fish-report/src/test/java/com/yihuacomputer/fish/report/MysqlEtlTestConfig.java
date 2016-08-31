package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.fault.FaultModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.monitor.MonitorModule;
import com.yihuacomputer.fish.system.SystemModule;

@Configuration
@Import(value = {DomainModule.class,SystemModule.class,MachineModule.class,MonitorModule.class,ReportModule.class,FaultModule.class})
@ImportResource(value = {"classpath:/com/yihuacomputer/fish/report/spring-report-mysql.xml"})
public class MysqlEtlTestConfig {

}
