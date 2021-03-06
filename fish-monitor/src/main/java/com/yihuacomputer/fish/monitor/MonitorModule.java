package com.yihuacomputer.fish.monitor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.IMonitorService;
import com.yihuacomputer.fish.api.monitor.alarm.IProcessService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoExtractDataService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitRuleService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitUniqueService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxDetailInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCardService;
import com.yihuacomputer.fish.api.monitor.business.ICashInitService;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoneyService;
import com.yihuacomputer.fish.api.monitor.business.IHostRetService;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHistService;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.ISettlementService;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.monitor.business.ITransactionColorService;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.monitor.business.ITransactionViewService;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTransService;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.hardware.IHardwareService;
import com.yihuacomputer.fish.api.monitor.software.IRuntimeParamService;
import com.yihuacomputer.fish.api.monitor.software.ISoftwareService;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeExtractDataService;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeService;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsChartService;
//import com.yihuacomputer.fish.api.monitor.xfs.IStateCodeService;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.monitor.entity.volume.DayTradingVolumeExtractDataService;
import com.yihuacomputer.fish.monitor.entity.volume.DayTradingVolumeService;
import com.yihuacomputer.fish.monitor.entity.volume.MonthDailyTradingVolumeService;
import com.yihuacomputer.fish.monitor.service.BlackListCardService;
import com.yihuacomputer.fish.monitor.service.CashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.monitor.service.CashInitPlanInfoExtractDataService;
import com.yihuacomputer.fish.monitor.service.CashInitPlanInfoService;
import com.yihuacomputer.fish.monitor.service.CashInitRuleService;
import com.yihuacomputer.fish.monitor.service.CashInitService;
import com.yihuacomputer.fish.monitor.service.CashInitUniqueService;
import com.yihuacomputer.fish.monitor.service.CollectService;
import com.yihuacomputer.fish.monitor.service.CounterFeitMoneyService;
import com.yihuacomputer.fish.monitor.service.DeviceBoxDetailInfoService;
import com.yihuacomputer.fish.monitor.service.DeviceBoxInfoService;
import com.yihuacomputer.fish.monitor.service.DeviceOfflineCheck;
import com.yihuacomputer.fish.monitor.service.FilterService;
import com.yihuacomputer.fish.monitor.service.HardwareService;
import com.yihuacomputer.fish.monitor.service.HostRetService;
import com.yihuacomputer.fish.monitor.service.MonitorService;
//import com.yihuacomputer.fish.monitor.service.StateCodeService;
import com.yihuacomputer.fish.monitor.service.ProcessService;
import com.yihuacomputer.fish.monitor.service.RegistService;
import com.yihuacomputer.fish.monitor.service.RemoteCommHistService;
import com.yihuacomputer.fish.monitor.service.RetaincardService;
import com.yihuacomputer.fish.monitor.service.RunInfoService;
import com.yihuacomputer.fish.monitor.service.RuntimeParamService;
import com.yihuacomputer.fish.monitor.service.SettlementService;
import com.yihuacomputer.fish.monitor.service.SoftwareService;
import com.yihuacomputer.fish.monitor.service.TransTypeService;
import com.yihuacomputer.fish.monitor.service.TransactionColorService;
import com.yihuacomputer.fish.monitor.service.TransactionService;
import com.yihuacomputer.fish.monitor.service.TransactionViewService;
import com.yihuacomputer.fish.monitor.service.UncommonTransService;
import com.yihuacomputer.fish.monitor.service.XfsChartService;
import com.yihuacomputer.fish.monitor.service.XfsService;

/**
 * @author YiHua
 *
 */
@Configuration
public class MonitorModule {

	/**
	 * @return
	 */
	@Bean
	public ICollectService collectService() {
		return new CollectService();
	}

	/**
	 * @return
	 */
	@Bean
	public ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService(){
		return new CashInitPlanDeviceInfoService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public ICashInitUniqueService cashInitUniqueService(){
		return new CashInitUniqueService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public ICashInitPlanInfoService cashInitPlanInfoService(){
		return new CashInitPlanInfoService();
	}

	/**
	 * @return
	 */
	@Bean
	public DeviceOfflineCheck offlineJob() {
		return new DeviceOfflineCheck();
	}

	/**
	 * @return
	 */
	@Bean
	public IMonitorService monitorService() {
		return new MonitorService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public ICashInitRuleService deviceBoxInitRuleService(){
		return new CashInitRuleService();
	}
//	@Bean
//	public IStateCodeService stateCodeService() {
//		return new StateCodeService();
//	}

	/**
	 * @return
	 */
	@Bean
	public IBlackListCardService blackListCardService() {
		return new BlackListCardService();
	}

	/**
	 * @return
	 */
	@Bean
	public ICashInitService cashInitService() {
		return new CashInitService();
	}

	/**
	 * @return
	 */
	@Bean
	public IFilterService filterService() {
		return new FilterService();
	}

	/**
	 * @return
	 */
	@Bean
	public IHardwareService hardwareService() {
		return new HardwareService();
	}

	/**
	 * @return
	 */
	@Bean
	public IHostRetService hostRetService() {
		return new HostRetService();
	}

	/**
	 * @return
	 */
	@Bean
	public IProcessService processService() {
		return new ProcessService();
	}

	/**
	 * @return
	 */
	@Bean
	public IRegistService registService() {
		return new RegistService();
	}

	/**
	 * @return
	 */
	@Bean
	public IRetaincardService retaincardService() {
		return new RetaincardService();
	}

	/**
	 * @return
	 */
	@Bean
	public IRunInfoService runInfoService() {
		return new RunInfoService();
	}

	/**
	 * @return
	 */
	@Bean
	public IRuntimeParamService runtimeParamService() {
		return new RuntimeParamService();
	}

	/**
	 * @return
	 */
	@Bean
	public ISettlementService settlementService() {
		return new SettlementService();
	}

	/**
	 * @return
	 */
	@Bean
	public ISoftwareService softwareService() {
		return new SoftwareService();
	}

	/**
	 * @return
	 */
	@Bean
	public ITransactionService transactionService() {
		return new TransactionService();
	}

	/**
	 * @return
	 */
	@Bean
	public ITransTypeService transTypeService() {
		return new TransTypeService();
	}

	/**
	 * @return
	 */
	@Bean
	public IXfsService xfsService() {
		return new XfsService();
	}

	/**
	 * @return
	 */
	@Bean
	public ICounterFeitMoneyService counterFeitMoneyService() {
		return new CounterFeitMoneyService();
	}

	/**
	 * @return
	 */
	@Bean
	public IUncommonTransService uncommonTransService() {
		return new UncommonTransService();
	}


	/**
	 * @return
	 */
	@Bean
	public IXfsChartService xfsChartService() {
		return new XfsChartService();
	}

	/**
	 * @return
	 */
	@Bean
	public ITransactionViewService transactionViewService() {
		return new TransactionViewService();
	}
	
	/**
	 * @return
	 */
	@Bean
    public IRemoteCommHistService remoteCommHistService() {
        return new RemoteCommHistService();
    }
	
	/**
	 * @return
	 */
	@Bean
    public ITransactionColorService transactionColorService() {
        return new TransactionColorService();
    }

	/**
	 * @return
	 */
	@Bean
	public IDeviceBoxDetailInfoService deviceBoxDetailInfoService(){
		return new DeviceBoxDetailInfoService();
	}
	/**
	 * @return
	 */
	@Bean
	public IDeviceBoxInfoService deviceBoxInfoService(){
		return new DeviceBoxInfoService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IMonthDailyTradingVolumeService monthDailyTradingVolumeService(){
		return new MonthDailyTradingVolumeService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDayTradingVolumeService dayTradingVolumeService(){
		return new DayTradingVolumeService();
	}
	
	/**
	 * @return
	 */
	@Bean 
	public IDayTradingVolumeExtractDataService dayTradingVolumeExtractDataService(){
		return new DayTradingVolumeExtractDataService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public ICashInitPlanInfoExtractDataService cashInitPlanInfoExtractDataService(){
		return new CashInitPlanInfoExtractDataService();
	}
}
