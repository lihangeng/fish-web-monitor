package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceOpenRateService;
import com.yihuacomputer.fish.api.report.device.IDeviceRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceUseCountRptService;
import com.yihuacomputer.fish.api.report.device.IRetainCardRptService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.fault.ICaseStatisticsRptService;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountService;
import com.yihuacomputer.fish.api.report.fault.IFaultRateReportService;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.transaction.ICashInRptService;
import com.yihuacomputer.fish.api.report.transaction.ISettlementCashInRptService;
import com.yihuacomputer.fish.api.report.transaction.ISettlementRptService;
import com.yihuacomputer.fish.api.report.transaction.ITransRptService;
import com.yihuacomputer.fish.api.report.transaction.ITransactionDaysService;
import com.yihuacomputer.fish.api.report.transaction.ITransactionMonthsService;
import com.yihuacomputer.fish.report.engine.ExportDataETLService;
import com.yihuacomputer.fish.report.scheduler.AtmcDayTransCount;
import com.yihuacomputer.fish.report.scheduler.DayOpenRateExcuter;
import com.yihuacomputer.fish.report.scheduler.EveryDayTransExcuter;
import com.yihuacomputer.fish.report.scheduler.EveryMonthFaultExcuter;
import com.yihuacomputer.fish.report.scheduler.EveryMonthTransExcuter;
import com.yihuacomputer.fish.report.service.device.DeviceBoxDetailRptService;
import com.yihuacomputer.fish.report.service.device.DeviceHardwareRptService;
import com.yihuacomputer.fish.report.service.device.DeviceRptService;
import com.yihuacomputer.fish.report.service.device.DeviceTypeCountRptService;
import com.yihuacomputer.fish.report.service.device.DeviceUseCountRptService;
import com.yihuacomputer.fish.report.service.device.RetainCardRptService;
import com.yihuacomputer.fish.report.service.fault.CaseStatisticsRptService;
import com.yihuacomputer.fish.report.service.fault.EveryMonthFaultCountService;
import com.yihuacomputer.fish.report.service.fault.FaultRateReportService;
import com.yihuacomputer.fish.report.service.openRate.DayOpenRateService;
import com.yihuacomputer.fish.report.service.openRate.DeviceOpenRateService;
import com.yihuacomputer.fish.report.service.transaction.CashInRptService;
import com.yihuacomputer.fish.report.service.transaction.SettlementCashInRptService;
import com.yihuacomputer.fish.report.service.transaction.SettlementRptService;
import com.yihuacomputer.fish.report.service.transaction.TransRptService;
import com.yihuacomputer.fish.report.service.transaction.TransactionDaysService;
import com.yihuacomputer.fish.report.service.transaction.TransactionMonthsService;

/**
 * 报表模块配置
 * @author xuxiang
 * @since
 *
 */
@Configuration
public class ReportModule {

	@Bean
	public ICashInRptService cashInRptService(){
		return new CashInRptService();
	}

	@Bean
	public IDeviceBoxDetailRptService deviceBoxDetailRptService(){
		return new DeviceBoxDetailRptService();
	}

	@Bean
	public IDeviceHardwareRptService deviceHardwareRptService(){
		return new DeviceHardwareRptService();
	}
	@Bean
	public IDeviceRptService deviceRptService(){
		return new DeviceRptService();
	}
	@Bean
	public IDeviceTypeCountRptService deviceTypeCountRptService(){
		return new DeviceTypeCountRptService();
	}
	@Bean
	public IDeviceUseCountRptService deviceUseCountRptService(){
		return new DeviceUseCountRptService();
	}
	@Bean
	public IRetainCardRptService retainCardRptService(){
		return new RetainCardRptService();
	}
	@Bean
	public ISettlementCashInRptService settlementCashInRptService(){
		return new SettlementCashInRptService();
	}
	@Bean
	public ISettlementRptService settlementRptService(){
		return new SettlementRptService();
	}

	@Bean
	public ITransRptService transRptService(){
		return new TransRptService();
	}

	@Bean
	public AtmcDayTransCount atmcDayTransCount(){
		return new AtmcDayTransCount();
	}

	@Bean
	public  IDayOpenRateService dayOpenRateService(){
	       return new DayOpenRateService();
	}

	@Bean
	 public IDeviceOpenRateService deviceOpenRateService()
	 {
		  return new DeviceOpenRateService();
	 }

	@Bean
	public IExportDataETLService exportDataETLService()
	{
		return new ExportDataETLService();
	}

	@Bean
	public  DayOpenRateExcuter  dayOpenRateExcuter() {
		return new DayOpenRateExcuter();
	}

	@Bean
	public  EveryMonthFaultExcuter  everyMonthFaultJob() {
		return new EveryMonthFaultExcuter();
	}

	@Bean
	public  EveryDayTransExcuter  everyDayTransExcuter() {
		return new EveryDayTransExcuter();
	}

	@Bean
	public  EveryMonthTransExcuter  everyMonthTransExcuter() {
		return new EveryMonthTransExcuter();
	}

	@Bean
	public ICaseStatisticsRptService caseStatisticsRptService()
	{
		return new CaseStatisticsRptService();
	}

	@Bean
	public IEveryMonthFaultCountService everyMonthFaultCountService()
	{
		return new EveryMonthFaultCountService();
	}

	@Bean
	public ITransactionDaysService transactionDaysService()
	{
		return new TransactionDaysService();
	}

	@Bean
	public ITransactionMonthsService transactionMonthsService()
	{
		return new TransactionMonthsService();
	}
	
	@Bean
	public IFaultRateReportService faultRateReportService(){
		return new FaultRateReportService();
	}
}

