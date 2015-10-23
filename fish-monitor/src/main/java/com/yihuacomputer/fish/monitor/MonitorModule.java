package com.yihuacomputer.fish.monitor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.alarm.IProcessService;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCardService;
import com.yihuacomputer.fish.api.monitor.business.ICashInitService;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoneyService;
import com.yihuacomputer.fish.api.monitor.business.IHostRetService;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.ISettlementService;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTransService;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.api.monitor.software.IRuntimeParamService;
import com.yihuacomputer.fish.api.monitor.software.ISoftwareService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsChartService;
//import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.monitor.service.BlackListCardService;
import com.yihuacomputer.fish.monitor.service.CashInitService;
import com.yihuacomputer.fish.monitor.service.CollectService;
import com.yihuacomputer.fish.monitor.service.CounterFeitMoneyService;
import com.yihuacomputer.fish.monitor.service.DeviceOfflineCheck;
import com.yihuacomputer.fish.monitor.service.FilterService;
import com.yihuacomputer.fish.monitor.service.HardwareService;
import com.yihuacomputer.fish.monitor.service.HostRetService;
import com.yihuacomputer.fish.monitor.service.MonitorService;
//import com.yihuacomputer.fish.monitor.service.StateCodeService;
import com.yihuacomputer.fish.monitor.service.ProcessService;
import com.yihuacomputer.fish.monitor.service.RegistService;
import com.yihuacomputer.fish.monitor.service.RetaincardService;
import com.yihuacomputer.fish.monitor.service.RunInfoService;
import com.yihuacomputer.fish.monitor.service.RuntimeParamService;
import com.yihuacomputer.fish.monitor.service.SettlementService;
import com.yihuacomputer.fish.monitor.service.SoftwareService;
import com.yihuacomputer.fish.monitor.service.TransTypeService;
import com.yihuacomputer.fish.monitor.service.TransactionService;
import com.yihuacomputer.fish.monitor.service.UncommonTransService;
import com.yihuacomputer.fish.monitor.service.XfsChartService;
import com.yihuacomputer.fish.monitor.service.XfsService;

@Configuration
public class MonitorModule {

	@Bean
	public ICollectService collectService() {
		return new CollectService();
	}

	@Bean
	public DeviceOfflineCheck offlineJob() {
		return new DeviceOfflineCheck();
	}

	@Bean
	public IMonitorService monitorService() {
		return new MonitorService();
	}

//	@Bean
//	public IStateCodeService stateCodeService() {
//		return new StateCodeService();
//	}

	@Bean
	public IBlackListCardService blackListCardService() {
		return new BlackListCardService();
	}

	@Bean
	public ICashInitService cashInitService() {
		return new CashInitService();
	}

	@Bean
	public IFilterService filterService() {
		return new FilterService();
	}

	@Bean
	public IHardwareService hardwareService() {
		return new HardwareService();
	}

	@Bean
	public IHostRetService hostRetService() {
		return new HostRetService();
	}

	@Bean
	public IProcessService processService() {
		return new ProcessService();
	}

	@Bean
	public IRegistService registService() {
		return new RegistService();
	}

	@Bean
	public IRetaincardService retaincardService() {
		return new RetaincardService();
	}

	@Bean
	public IRunInfoService runInfoService() {
		return new RunInfoService();
	}

	@Bean
	public IRuntimeParamService runtimeParamService() {
		return new RuntimeParamService();
	}

	@Bean
	public ISettlementService settlementService() {
		return new SettlementService();
	}

	@Bean
	public ISoftwareService softwareService() {
		return new SoftwareService();
	}

	@Bean
	public ITransactionService transactionService() {
		return new TransactionService();
	}

	@Bean
	public ITransTypeService transTypeService() {
		return new TransTypeService();
	}

	@Bean
	public IXfsService xfsService() {
		return new XfsService();
	}

	@Bean
	public ICounterFeitMoneyService counterFeitMoneyService() {
		return new CounterFeitMoneyService();
	}
	
	@Bean
	public IUncommonTransService uncommonTransService() {
		return new UncommonTransService();
	}
	

	@Bean
	public IXfsChartService xfsChartService() {
		return new XfsChartService();
	}
}
