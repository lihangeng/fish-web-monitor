package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.report.service.fault.etl.FaultEtlService;

/**
 * 故障模块配置
 *
 * @author xuxiang
 * @since 1.4.0
 *
 */
@Configuration
public class ReportFaultModule {
	
	@Bean
	public IFaultEtlService faultEtlService(){
		return new FaultEtlService();
	}
	
	

}
