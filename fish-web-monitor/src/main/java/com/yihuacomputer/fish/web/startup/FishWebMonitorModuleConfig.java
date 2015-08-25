package com.yihuacomputer.fish.web.startup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.advert.AdvertModule;
import com.yihuacomputer.fish.atmlog.AtmLogModule;
import com.yihuacomputer.fish.fault.FaultModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.monitor.MonitorModule;
import com.yihuacomputer.fish.permission.PersonPermissionModule;
import com.yihuacomputer.fish.person.PersonCoreModule;
import com.yihuacomputer.fish.relation.PersonRelationModule;
import com.yihuacomputer.fish.report.base.ReportBaseModule;
import com.yihuacomputer.fish.report.engine.ReportEngineModule;
import com.yihuacomputer.fish.system.SystemModule;
import com.yihuacomputer.fish.version.VersionModule;

/**
 * fish-web-base依赖项目的的模块配置
 * @author xuxiang
 *
 */
@Configuration
@Import({
		DomainModule.class,
		PersonCoreModule.class,
		PersonPermissionModule.class,
		PersonRelationModule.class,
		MachineModule.class,
		SystemModule.class,
		MonitorModule.class,
		VersionModule.class,
		AdvertModule.class,
		AtmLogModule.class,
		FaultModule.class,
		ReportBaseModule.class,
		ReportEngineModule.class
		})
@ImportResource(value = {"classpath:/fish.xml","classpath:/spring-quartz.xml"})
public class FishWebMonitorModuleConfig {

	@Bean(initMethod="init")
	public SystemService systemService(){
		return new SystemService();
	}

}
