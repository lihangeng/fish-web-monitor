package com.yihuacomputer.fish.report.base;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.base.ICashInRptService;
import com.yihuacomputer.fish.api.report.base.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.base.IDeviceBoxDetailRptService;
import com.yihuacomputer.fish.api.report.base.IDeviceHardwareRptService;
import com.yihuacomputer.fish.api.report.base.IDeviceOpenRateService;
import com.yihuacomputer.fish.api.report.base.IDeviceRptService;
import com.yihuacomputer.fish.api.report.base.IDeviceTypeCountRptService;
import com.yihuacomputer.fish.api.report.base.IDeviceUseCountRptService;
import com.yihuacomputer.fish.api.report.base.IRetainCardRptService;
import com.yihuacomputer.fish.api.report.base.ISettlementCashInRptService;
import com.yihuacomputer.fish.api.report.base.ISettlementRptService;
import com.yihuacomputer.fish.api.report.base.ITransRptService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.report.base.schedule.AtmcDayTransCount;
import com.yihuacomputer.fish.report.engine.ExportDataETLService;
import com.yihuacomputer.fish.report.scheduler.DayOpenRateExcuter;
import com.yihuacomputer.fish.report.service.db.DeviceOpenRateService;

/**
 * 报表基础模块配置
 * @author xuxiang
 * @since
 *
 */
@Configuration
public class ReportBaseModule {

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
}
