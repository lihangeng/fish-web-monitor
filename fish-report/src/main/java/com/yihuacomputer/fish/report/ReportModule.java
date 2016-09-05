package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.yihuacomputer.fish.api.report.batch.IETLjobDaysService;
import com.yihuacomputer.fish.api.report.batch.IETLjobService;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.api.report.engine.IMonthPdfReportService;
import com.yihuacomputer.fish.api.report.engine.IWeekPdfReportService;
import com.yihuacomputer.fish.api.report.fault.ICaseStatisticsRptService;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountService;
import com.yihuacomputer.fish.api.report.fault.IFaultRateReportService;
import com.yihuacomputer.fish.report.batch.day.DayTransBatchService;
import com.yihuacomputer.fish.report.batch.day.ETLjobService;
import com.yihuacomputer.fish.report.engine.ExportReportService;
import com.yihuacomputer.fish.report.engine.exporter.HtmlExporter;
import com.yihuacomputer.fish.report.engine.exporter.PdfExporter;
import com.yihuacomputer.fish.report.engine.exporter.XlsExporter;
import com.yihuacomputer.fish.report.engine.scheduler.EveryDayReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryMonthReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryWeekReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryYearReportJob;
import com.yihuacomputer.fish.report.service.MonthPdfReportService;
import com.yihuacomputer.fish.report.service.WeekPdfReportService;
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
@Import(value = { ReportDeviceModule.class,
				  ReportOpenRateModule.class,
				  ReportTransModule.class,
				  ReportFaultModule.class})
public class ReportModule {

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
	
	@Bean
	public IExportReportService exportReportService() {
		return new ExportReportService();
	}

	@Bean
	public HtmlExporter htmlExporter() {
		return new HtmlExporter();
	}

	@Bean
	public PdfExporter pdfExporter() {
		return new PdfExporter();
	}

	@Bean
	public XlsExporter xlsExporter() {
		return new XlsExporter();
	}

	@Bean(name = "DayETLJob")
	public EveryDayReportJob everyDayReportJob() {
		return new EveryDayReportJob();
	}
	
	@Bean(name = "WeekETLJob")
	public EveryWeekReportJob everyWeekReportJob() {
		return new EveryWeekReportJob();
	}

	@Bean(name = "MonthETLJob")
	public EveryMonthReportJob everyMonthReportJob() {
		return new EveryMonthReportJob();
	}

	@Bean(name = "YearETLJob")
	public EveryYearReportJob everyYearReportJob() {
		return new EveryYearReportJob();
	}
	
	@Bean
	public IETLjobService  EtljobService(){
		return new ETLjobService();
	}
	
	@Bean
	public IETLjobDaysService iDaysService()
	{
		return new DayTransBatchService();
	}
	
	@Bean
	public IWeekPdfReportService weekPdfReportService()
	{
		return new WeekPdfReportService();
	}
	
	@Bean
	public IMonthPdfReportService monthPdfReportService()
	{
		return new MonthPdfReportService();
	}
	
}
