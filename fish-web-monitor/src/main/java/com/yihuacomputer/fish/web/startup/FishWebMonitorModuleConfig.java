package com.yihuacomputer.fish.web.startup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.domain.DomainModule;
import com.yihuacomputer.fish.advert.AdvertModule;
import com.yihuacomputer.fish.api.fault.IDeviceCaseService;
import com.yihuacomputer.fish.api.fault.INotifyContentService;
import com.yihuacomputer.fish.atmlog.AtmLogModule;
import com.yihuacomputer.fish.fault.FaultModule;
import com.yihuacomputer.fish.machine.MachineModule;
import com.yihuacomputer.fish.monitor.MonitorModule;
import com.yihuacomputer.fish.report.ReportModule;
import com.yihuacomputer.fish.system.SystemModule;
import com.yihuacomputer.fish.version.VersionModule;
import com.yihuacomputer.fish.web.interceptor.EntitySaveInterceptor;
import com.yihuacomputer.fish.web.mock.NotifyContentService;
import com.yihuacomputer.fish.web.mock.service.DefaultDeviceCaseService;
import com.yihuacomputer.fish.web.mock.service.DefaultHwFaultService;

/**
 * fish-web-base依赖项目的的模块配置
 * @author xuxiang
 *
 */
@Configuration
@Import({
		DomainModule.class,
		MachineModule.class,
		SystemModule.class,
		MonitorModule.class,
		VersionModule.class,
		AdvertModule.class,
		AtmLogModule.class,
		FaultModule.class,
		ReportModule.class
		})
@ImportResource(value = {"classpath:/fish.xml"})
@EnableAspectJAutoProxy
public class FishWebMonitorModuleConfig {

	/**
	 * @return
	 */
	@Bean
	public DefaultHwFaultService hwFaultService() {
		return new DefaultHwFaultService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceCaseService deviceCaseService() {
		return new DefaultDeviceCaseService();
	}

	/**
	 * @return
	 */
	@Bean
	public INotifyContentService notifyContentService(){
		return new NotifyContentService() ;
	}

	/**
	 * @return
	 */
	@Bean(initMethod="init")
	public SystemService systemService(){
		return new SystemService();
	}
	
	/**
	 * @return
	 */
	@Bean
    public EntitySaveInterceptor serviceAspect(){
        return new EntitySaveInterceptor();
    }
}
