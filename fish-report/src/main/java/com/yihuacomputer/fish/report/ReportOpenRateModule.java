package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.device.IDeviceOpenRateService;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateExtractDataService;
import com.yihuacomputer.fish.report.service.openRate.DayOpenRateService;
import com.yihuacomputer.fish.report.service.openRate.DeviceOpenRateService;
import com.yihuacomputer.fish.report.service.openRate.etl.AvgOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.etl.AvgOpenRateExtractDataService;
import com.yihuacomputer.fish.report.service.openRate.etl.DeviceOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.etl.DeviceOpenRateExtractDataService;
import com.yihuacomputer.fish.report.service.openRate.etl.DeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.etl.DeviceTypeOpenRateExtractDataService;
import com.yihuacomputer.fish.report.service.openRate.etl.OrgOpenRateEtlService;
import com.yihuacomputer.fish.report.service.openRate.etl.OrgOpenRateExtractDataService;

/**
 * 开机率数据抽取模块配置
 *
 * @author xuxiang
 * @since 2.1.1.1
 *
 */
@Configuration
public class ReportOpenRateModule {

	/**
	 * @return
	 */
	@Bean
	public IDayOpenRateService dayOpenRateService() {
		return new DayOpenRateService();
	}

	/**
	 * @return
	 */
	@Bean
	public IDeviceOpenRateService deviceOpenRateService() {
		return new DeviceOpenRateService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IAvgOpenRateEtlService avgOpenRateEtlService(){
		return new AvgOpenRateEtlService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceOpenRateEtlService deviceOpenRateEtlService(){
		return new DeviceOpenRateEtlService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService(){
		return new DeviceTypeOpenRateEtlService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IOrgOpenRateEtlService orgOpenRateEtlService(){
		return new OrgOpenRateEtlService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IAvgOpenRateExtractDataService avgOpenRateExtractDataService(){
		return new AvgOpenRateExtractDataService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceOpenRateExtractDataService deviceOpenRateExtractDataService(){
		return new DeviceOpenRateExtractDataService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IDeviceTypeOpenRateExtractDataService deviceTypeOpenRateExtractDataService(){
		return new DeviceTypeOpenRateExtractDataService();
	}
	
	/**
	 * @return
	 */
	@Bean
	public IOrgOpenRateExtractDataService orgOpenRateExtractDataService(){
		return new OrgOpenRateExtractDataService();
	}

}
