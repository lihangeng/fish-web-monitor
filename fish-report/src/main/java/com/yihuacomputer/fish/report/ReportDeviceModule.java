package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRptService;
import com.yihuacomputer.fish.api.report.device.IDeviceUseCountRptService;
import com.yihuacomputer.fish.api.report.device.IRetainCardRptService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.report.service.device.DeviceBoxDetailRptService;
import com.yihuacomputer.fish.report.service.device.DeviceHardwareRptService;
import com.yihuacomputer.fish.report.service.device.DeviceRptService;
import com.yihuacomputer.fish.report.service.device.DeviceTypeCountRptService;
import com.yihuacomputer.fish.report.service.device.DeviceUseCountRptService;
import com.yihuacomputer.fish.report.service.device.RetainCardRptService;
import com.yihuacomputer.fish.report.service.device.etl.RetainCardEtlService;

/**
 * 设备关联报表模块配置
 *
 * @author xuxiang
 * @since 2.1.1.1
 *
 */
@Configuration
public class ReportDeviceModule {
	
	@Bean
	public IDeviceBoxDetailRptService deviceBoxDetailRptService() {
		return new DeviceBoxDetailRptService();
	}

	@Bean
	public IDeviceHardwareRptService deviceHardwareRptService() {
		return new DeviceHardwareRptService();
	}

	@Bean
	public IDeviceRptService deviceRptService() {
		return new DeviceRptService();
	}

	@Bean
	public IDeviceTypeCountRptService deviceTypeCountRptService() {
		return new DeviceTypeCountRptService();
	}

	@Bean
	public IDeviceUseCountRptService deviceUseCountRptService() {
		return new DeviceUseCountRptService();
	}

	@Bean
	public IRetainCardRptService retainCardRptService() {
		return new RetainCardRptService();
	}

	@Bean
	public IRetainCardEtlService retainCardEtlService(){
		return new RetainCardEtlService();
	}
	

}
