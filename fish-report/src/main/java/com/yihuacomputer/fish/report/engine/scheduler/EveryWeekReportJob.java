package com.yihuacomputer.fish.report.engine.scheduler;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardExtractDataService;
import com.yihuacomputer.fish.api.report.engine.IWeekPdfReportService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeExtractDataService;

/**
 * 每周报表数据生成
 * 
 * @author xuxigang
 * @since 2016-8-29
 */
@Service("WeekETLJob")
public class EveryWeekReportJob {
	
	private static Log logger = LogFactory.getLog(EveryWeekReportJob.class);
	
	@Autowired
	private IAvgOpenRateExtractDataService avgOpenRateExtractDataService;
	
	@Autowired
	private IDeviceOpenRateExtractDataService deviceOpenRateExtractDataService;
	
	@Autowired
	private IDeviceTypeOpenRateExtractDataService deviceTypeOpenRateExtractDataService;
	
	@Autowired
	private IOrgOpenRateExtractDataService orgOpenRateExtractDataService;
	
	@Autowired
	private ITransTypeExtractDataService transTypeExtractDataService;
	
	@Autowired
	private IRetainCardExtractDataService retainCardExtractDataService;

	@Autowired
	private IFaultExtractDataService faultExtractDataService;
	
	@Autowired
	private IDeviceExtractDataWeekService deviceExtractDataWeekService;
	
	@Autowired
	private IWeekPdfReportService pdfReportService;
	
	/**
	 * 每周统计
	 */
	public void execute(){
		logger.info("Week data ETL finished");
		long start = System.currentTimeMillis();
		executeWeekDevice();
		executeWeekOpenRate();
		executeWeekTrans();
		executeWeekFault();
		executeWeekPdfReport();
		logger.info(String.format("Week data ETL finished,SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 生成周综合分析报告
	 */
	private void executeWeekPdfReport() {
		long start = System.currentTimeMillis();
		Date lastWeek = DateUtils.getLastWeek();
		pdfReportService.generateWeekPDF(DateUtils.getWeek(lastWeek).intValue());
		logger.info(String.format("executeWeekPdfReport SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 故障类相关周统计
	 */
	private void executeWeekFault() {
		long start = System.currentTimeMillis();
		//1.每周故障类型统计(2016年3季度需求综合报告之故障信息汇总)
		Date lastWeek = DateUtils.getLastWeek();
		faultExtractDataService.extractStatusByWeek(lastWeek);
		faultExtractDataService.extractFaultClassifyByWeek(lastWeek);
		faultExtractDataService.extractDurationByWeek(lastWeek);
		logger.info(String.format("executeWeekFault SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 交易相关周统计
	 */
	private void executeWeekTrans() {
		long start = System.currentTimeMillis();
		//1.每周交易类型统计(2016年3季度需求综合报告之交易信息汇总)
		Date lastWeek = DateUtils.getLastWeek();
		transTypeExtractDataService.extractByWeek(lastWeek);
		logger.info(String.format("executeWeekTrans SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 开机率相关的周统计
	 */
	private void executeWeekOpenRate() {
		long start = System.currentTimeMillis();
		Date lastWeek = DateUtils.getLastWeek();
		//1.每周开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		avgOpenRateExtractDataService.extractByWeek(lastWeek);
		//2.每周设备开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceOpenRateExtractDataService.extractByWeek(lastWeek);
		//3.每周设备型号开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceTypeOpenRateExtractDataService.extractByWeek(lastWeek);
		//4.每周机构开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		orgOpenRateExtractDataService.extractByWeek(lastWeek);
		logger.info(String.format("executeWeekOpenRate SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 执行设备基础信息的相关周统计
	 */
	private void executeWeekDevice() {
		long start = System.currentTimeMillis();
//		Date now = new Date();
		Date lastWeek = DateUtils.getLastWeek();
		//1.设备类型统计
		deviceExtractDataWeekService.loadCatalogBaseData(lastWeek);
		//2.设备型号统计
		deviceExtractDataWeekService.loadTypeBaseData(lastWeek);
		//3.每周吞卡统计(2016年3季度需求综合报告之吞卡信息汇总)
//		Date lastWeek = DateUtils.getLastWeek();
		retainCardExtractDataService.extractByWeek(lastWeek);
		logger.info(String.format("executeWeekDevice SPEND [%s]ms",System.currentTimeMillis() - start));
	}
	
}
