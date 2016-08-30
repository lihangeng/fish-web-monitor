package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.device.IDeviceOpenRateService;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.DayOpenRateService;
import com.yihuacomputer.fish.report.service.openRate.DeviceOpenRateService;
import com.yihuacomputer.fish.report.service.openRate.etl.AvgOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.etl.DeviceOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.etl.DeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.etl.OrgOpenRateEtlService;

/**
 * 开机率数据抽取模块配置
 *
 * @author xuxiang
 * @since 2.1.1.1
 *
 */
@Configuration
public class ReportOpenRateModule {

	@Bean
	public IDayOpenRateService dayOpenRateService() {
		return new DayOpenRateService();
	}

	@Bean
	public IDeviceOpenRateService deviceOpenRateService() {
		return new DeviceOpenRateService();
	}
	
	@Bean
	public IAvgOpenRateEtlService avgOpenRateEtlService(){
		return new AvgOpenRateEtlService();
	}
	
	@Bean
	public IDeviceOpenRateEtlService deviceOpenRateEtlService(){
		return new DeviceOpenRateEtlService();
	}
	
	@Bean
	public IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService(){
		return new DeviceTypeOpenRateEtlService();
	}
	
	@Bean
	public IOrgOpenRateEtlService orgOpenRateEtlService(){
		return new OrgOpenRateEtlService();
	}

}
