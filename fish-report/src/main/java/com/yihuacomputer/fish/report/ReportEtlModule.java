package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceTypeSummaryWeekService;


/**
 * 数据分析模块配置
 * @author xuxiang
 *
 */
@Configuration
public class ReportEtlModule {

	@Bean
	public IDeviceCatalogSummaryMonthService makeCatalogSummaryMonthService(){
		return new DeviceCatalogSummaryMonthService();
	}
	@Bean
	public IDeviceCatalogSummaryWeekService makeCatalogSummaryWeekService(){
		return new DeviceCatalogSummaryWeekService();
	}
	@Bean
	public IDeviceTypeSummaryMonthService makeTypeSummaryWeekService(){
		return new DeviceTypeSummaryMonthService();
	}
	@Bean
	public IDeviceTypeSummaryWeekService makeTypeSummaryMonthService(){
		return new DeviceTypeSummaryWeekService();
	}
}