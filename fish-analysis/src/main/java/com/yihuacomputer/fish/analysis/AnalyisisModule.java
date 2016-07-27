package com.yihuacomputer.fish.analysis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.analysis.service.device.DeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.analysis.service.device.DeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryWeekService;


/**
 * 故障管理模块配置
 * @author xuxiang
 *
 */
@Configuration
public class AnalyisisModule {

	@Bean
	public IDeviceCatalogSummaryMonthService makeCatalogSummaryMonthService(){
		return new DeviceCatalogSummaryMonthService();
	}
	@Bean
	public IDeviceCatalogSummaryWeekService makeCatalogSummaryWeekService(){
		return new DeviceCatalogSummaryWeekService();
	}
}