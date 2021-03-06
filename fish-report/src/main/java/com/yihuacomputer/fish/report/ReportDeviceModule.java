package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceUseCountRptService;
import com.yihuacomputer.fish.api.report.device.IRetainCardRptService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardExtractDataService;
import com.yihuacomputer.fish.report.service.device.DeviceBoxDetailRptService;
import com.yihuacomputer.fish.report.service.device.DeviceHardwareRptService;
import com.yihuacomputer.fish.report.service.device.DeviceRptService;
import com.yihuacomputer.fish.report.service.device.DeviceTypeCountRptService;
import com.yihuacomputer.fish.report.service.device.DeviceUseCountRptService;
import com.yihuacomputer.fish.report.service.device.RetainCardRptService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceExtractDataMonthService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceExtractDataWeekService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.report.service.device.etl.DeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.report.service.device.etl.RetainCardEtlService;
import com.yihuacomputer.fish.report.service.device.etl.RetainCardExtractDataService;

/**
 * 设备关联报表模块配置
 *
 * @author xuxiang
 * @since 2.1.1.1
 *
 */
@Configuration
public class ReportDeviceModule {
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceBoxDetailRptService deviceBoxDetailRptService() {
		return new DeviceBoxDetailRptService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceHardwareRptService deviceHardwareRptService() {
		return new DeviceHardwareRptService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceRptService deviceRptService() {
		return new DeviceRptService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceTypeCountRptService deviceTypeCountRptService() {
		return new DeviceTypeCountRptService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceUseCountRptService deviceUseCountRptService() {
		return new DeviceUseCountRptService();
	}

	/**
	 * @return
	 */
	@Bean
	public IRetainCardRptService retainCardRptService() {
		return new RetainCardRptService();
	}

	/**
	 * @return
	 */
	@Bean
	public IRetainCardEtlService retainCardEtlService(){
		return new RetainCardEtlService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceCatalogSummaryMonthService deviceCatalogSummaryMonthService(){
		return new DeviceCatalogSummaryMonthService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService(){
		return new DeviceCatalogSummaryWeekService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceTypeSummaryMonthService deviceTypeSummaryMonthService(){
		return new DeviceTypeSummaryMonthService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService(){
		return new DeviceTypeSummaryWeekService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceExtractDataMonthService deviceExtractDataMonthService(){
		return new DeviceExtractDataMonthService();
	}
	
	/**
	 * @return
	 */
	@Bean 
	public IDeviceExtractDataWeekService deviceExtractDataWeekService(){
		return new DeviceExtractDataWeekService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IRetainCardExtractDataService retainCardExtractDataService(){
		return new RetainCardExtractDataService();
	}

}
