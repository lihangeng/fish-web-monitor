package com.yihuacomputer.fish.fault;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.fault.ICaseFaultFlowService;
import com.yihuacomputer.fish.api.fault.ICaseFaultService;
import com.yihuacomputer.fish.api.fault.ICaseNotifyService;
import com.yihuacomputer.fish.api.fault.IFaultClassifyService;
import com.yihuacomputer.fish.api.fault.IFaultFilter;
import com.yihuacomputer.fish.api.fault.IFaultStatisticsService;
import com.yihuacomputer.fish.api.fault.INotifyMouldService;
import com.yihuacomputer.fish.api.fault.IUnuauslNotifyMouldService;
import com.yihuacomputer.fish.api.fault.IVendorCodeService;
import com.yihuacomputer.fish.api.fault.monitor.IFaultManager;
import com.yihuacomputer.fish.api.fault.monitor.IMessagHandleCollection;
import com.yihuacomputer.fish.fault.interceptor.CaseFaultEntityInjector;
import com.yihuacomputer.fish.fault.monitor.FaultManager;
import com.yihuacomputer.fish.fault.monitor.MessagHandleCollection;
import com.yihuacomputer.fish.fault.service.CaseFaultService;
import com.yihuacomputer.fish.fault.service.CaseNotifyService;
import com.yihuacomputer.fish.fault.service.DefaultCaseFaultFlowService;
import com.yihuacomputer.fish.fault.service.FaultClassifyService;
import com.yihuacomputer.fish.fault.service.FaultFilter;
import com.yihuacomputer.fish.fault.service.FaultStatisticsService;
import com.yihuacomputer.fish.fault.service.NotifyMouldService;
import com.yihuacomputer.fish.fault.service.UnuauslNotifyMouldService;
import com.yihuacomputer.fish.fault.service.VendorCodeService;

/**
 * 故障管理模块配置
 * @author xuxiang
 *
 */
@Configuration
public class FaultModule {

	@Bean
	public IVendorCodeService vendorCodeService() {
		return new VendorCodeService();
	}

	@Bean
	public ICaseFaultService caseFaultService() {
		return new CaseFaultService();
	}

	@Bean
	public ICaseNotifyService caseNotifyService() {
		return new CaseNotifyService();
	}

	@Bean
	public IFaultClassifyService faultClassifyService() {
		return new FaultClassifyService();
	}

	@Bean
	public INotifyMouldService notifyMouldService() {
		return new NotifyMouldService();
	}

	@Bean
	public IFaultFilter faultFilter() {
		return new FaultFilter();
	}

	@Bean
	public CaseFaultEntityInjector caseFaultEntityInjector() {
		return new CaseFaultEntityInjector();
	}

	@Bean
	public IUnuauslNotifyMouldService unuauslNotifyMouldService() {
		return new UnuauslNotifyMouldService();
	}
	
	@Bean
	public IFaultStatisticsService faultStatisticsService(){
		return new FaultStatisticsService();
	}

	@Bean
	public IMessagHandleCollection msgHandleCollection(){
		return  new MessagHandleCollection();
	}

	@Bean
	public IFaultManager faultManager(){
		return new FaultManager();
	}
	
	@Bean
	public ICaseFaultFlowService caseFaultFlowService(){
		return new DefaultCaseFaultFlowService();
	}
}
