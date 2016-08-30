package com.yihuacomputer.fish.report;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yihuacomputer.fish.api.report.batch.IETLjobDaysService;
import com.yihuacomputer.fish.api.report.batch.IETLjobService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.report.batch.day.DayTransBatchService;
import com.yihuacomputer.fish.report.batch.day.ETLjobService;
import com.yihuacomputer.fish.report.engine.ExportDataETLService;
import com.yihuacomputer.fish.report.engine.ExportReportService;
import com.yihuacomputer.fish.report.engine.exporter.HtmlExporter;
import com.yihuacomputer.fish.report.engine.exporter.PdfExporter;
import com.yihuacomputer.fish.report.engine.exporter.XlsExporter;
import com.yihuacomputer.fish.report.engine.scheduler.EveryDayReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryMonthReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryWeekReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryYearReportJob;

/**
 * 报表引擎模块配置
 *
 * @author xuxiang
 * @since 1.4.0
 *
 */
@Configuration
public class ReportEngineModule {

	@Bean
	public IExportDataETLService exportDataETLService() {
		return new ExportDataETLService();
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
	

}
