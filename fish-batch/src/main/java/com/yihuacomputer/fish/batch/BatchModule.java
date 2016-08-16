package com.yihuacomputer.fish.batch;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.yihuacomputer.fish.api.batch.base.IETLjobDaysService;
import com.yihuacomputer.fish.api.batch.base.IETLjobMonthService;
import com.yihuacomputer.fish.api.batch.base.IETLjobService;
import com.yihuacomputer.fish.batch.service.DayTransBatchService;
import com.yihuacomputer.fish.batch.service.ETLjobService;
import com.yihuacomputer.fish.batch.service.MonthTransService;

/**
 * 批处理模块配置
 * @author shaoxuan
 *
 */
@Configuration
@ImportResource(value = {"classpath:/com/yihuacomputer/fish/batch/NewDays_job.xml"})
public class BatchModule {
	
	@Bean
	public IETLjobDaysService iDaysService()
	{
		return new DayTransBatchService();
	}
	
	@Bean
	public IETLjobMonthService iMonthService()
	{
		return new MonthTransService();
	}
	
	@Bean
	public IETLjobService  EtljobService(){
		return new ETLjobService();
	}
}
