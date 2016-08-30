package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.fault.ICaseStatisticsRptService;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountService;
import com.yihuacomputer.fish.api.report.fault.IFaultRateReportService;
import com.yihuacomputer.fish.report.engine.ExportDataETLService;
import com.yihuacomputer.fish.report.scheduler.DayOpenRateExcuter;
import com.yihuacomputer.fish.report.scheduler.EveryDayTransExcuter;
import com.yihuacomputer.fish.report.scheduler.EveryMonthFaultExcuter;
import com.yihuacomputer.fish.report.scheduler.EveryMonthTransExcuter;
import com.yihuacomputer.fish.report.service.fault.CaseStatisticsRptService;
import com.yihuacomputer.fish.report.service.fault.EveryMonthFaultCountService;
import com.yihuacomputer.fish.report.service.fault.FaultRateReportService;

/**
 * 报表模块配置
 * 
 * @author xuxiang
 * @since
 *
 */
@Configuration
@Import(value = { ReportDeviceModule.class,ReportOpenRateModule.class,ReportTransModule.class })
public class ReportModule {
	
	@Bean
	public DayOpenRateExcuter dayOpenRateExcuter() {
		return new DayOpenRateExcuter();
	}
	
	@Bean
	public IExportDataETLService exportDataETLService() {
		return new ExportDataETLService();
	}

	@Bean
	public EveryMonthFaultExcuter everyMonthFaultJob() {
		return new EveryMonthFaultExcuter();
	}

	@Bean
	public EveryDayTransExcuter everyDayTransExcuter() {
		return new EveryDayTransExcuter();
	}

	@Bean
	public EveryMonthTransExcuter everyMonthTransExcuter() {
		return new EveryMonthTransExcuter();
	}

	@Bean
	public ICaseStatisticsRptService caseStatisticsRptService() {
		return new CaseStatisticsRptService();
	}

	@Bean
	public IEveryMonthFaultCountService everyMonthFaultCountService() {
		return new EveryMonthFaultCountService();
	}

	@Bean
	public IFaultRateReportService faultRateReportService() {
		return new FaultRateReportService();
	}
}
