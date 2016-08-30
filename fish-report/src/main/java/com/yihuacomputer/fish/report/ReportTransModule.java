package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.report.service.trans.etl.TransTypeEtlService;

/**
 * 报表分析模块配置
 *
 * @author xuxiang
 * @since 1.4.0
 *
 */
@Configuration
public class ReportTransModule {

	@Bean
	public ITransTypeEtlService transTypeEtlService(){
		return new TransTypeEtlService();
	}
	
}
