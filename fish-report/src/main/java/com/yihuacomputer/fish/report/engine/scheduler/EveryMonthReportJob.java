package com.yihuacomputer.fish.report.engine.scheduler;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardExtractDataService;
import com.yihuacomputer.fish.api.report.engine.IMonthPdfReportService;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountExtractDataService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.trans.ITransactionMonthsService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeExtractDataService;

/**
 * 每月报表数据生成
 * 
 * @author YiHua
 * 
 * @since 2010-9-8
 */
@Service("MonthETLJob")
public class EveryMonthReportJob {
	
	private static Log logger = LogFactory.getLog(EveryMonthReportJob.class);
	
	@Autowired
	private ITransactionMonthsService transactionMonthsService;
	
	@Autowired
	private IEveryMonthFaultCountExtractDataService everyMonthFaultCountExtractDataService;
	
	@Autowired
	private IMonthDailyTradingVolumeService monthDailyTradingVolumeService;
	
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
	private IDeviceExtractDataMonthService deviceExtractDataMonthService;
	
	@Autowired
	private IMonthPdfReportService pdfReportService;

	/**
	 * 每月任务
	 */
	public void execute(){
		logger.info("starting month data ETL ....");
		long start = System.currentTimeMillis();
		executeMonthDevice();
		executeMonthTrans();
		executeMonthOpenRate();
		executeMonthFault();
		executeMonthPdfReport();
		logger.info(String.format("month data ETL finished. SPEND [%s]ms",System.currentTimeMillis() - start));
	}
	
	/**
	 * 生成月综合分析报告
	 */
	private void executeMonthPdfReport() {
		long start = System.currentTimeMillis();
		Date lastMonth =DateUtils.getLastMonth();
		pdfReportService.generateMonthPDF((int)DateUtils.getLongYM(lastMonth));
		logger.info(String.format("executeMonthPdfReport SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 故障相关月统计
	 */
	private void executeMonthFault() {
		long start = System.currentTimeMillis();
		//1.（2016年2季度增加）计算设备故障率 CASE_FAULT_MONTH
		String yestoday = DateUtils.getLastMonthShortDates();
		everyMonthFaultCountExtractDataService.extractMonthFault(yestoday);
		
		//2.每月故障类型统计(2016年3季度需求综合报告之故障信息汇总)
		Date lastMonth =DateUtils.getLastMonth();
		faultExtractDataService.extractStatusByMonth(lastMonth);
		faultExtractDataService.extractFaultClassifyByMonth(lastMonth);
		faultExtractDataService.extractDurationByMonth(lastMonth);
		logger.info(String.format("executeMonthFault SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 开机率相关月统计
	 */
	private void executeMonthOpenRate(){
		long start = System.currentTimeMillis();
		Date lastMonth =DateUtils.getLastMonth();
		//1.每月开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		avgOpenRateExtractDataService.extractByMonth(lastMonth);
		//2.每月设备开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceOpenRateExtractDataService.extractByMonth(lastMonth);
		//3.每月设备型号开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceTypeOpenRateExtractDataService.extractByMonth(lastMonth);
		//4.每月机构开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		orgOpenRateExtractDataService.extractByMonth(lastMonth);
		logger.info(String.format("executeMonthOpenRate SPEND [%s]ms",System.currentTimeMillis() - start));

	}
	/**
	 * 交易相关月统计
	 */
	private void executeMonthTrans() {
		long start = System.currentTimeMillis();
		//1.每月交易统计（2016年2季度增加）ATMC_TRANSACTION_MONTHS
		String yestoday = DateUtils.getLastMonthShortDates();
		transactionMonthsService.extractDate(yestoday);
		//2.每台设备日均交易统计(加钞计划使用)
		String curdate = DateUtils.getDateShort(new Date());
		monthDailyTradingVolumeService.generalMonthDailyTradingVolumeByDate(curdate);
		//3.每月交易类型统计(2016年3季度需求综合报告之交易信息汇总)
		Date lastMonth =DateUtils.getLastMonth();
		transTypeExtractDataService.extractByMonth(lastMonth);
		logger.info(String.format("executeMonthTrans SPEND [%s]ms",System.currentTimeMillis() - start));

	}

	/**
	 * 设备相关月统计
	 */
	private void executeMonthDevice() {
		long start = System.currentTimeMillis();
		//1.设备类型统计
		Date dateNow = new Date();
		deviceExtractDataMonthService.loadCatalogBaseData(dateNow);
		//2.设备型号统计
		deviceExtractDataMonthService.loadTypeBaseData(dateNow);
		//3.每月吞卡统计(2016年3季度需求综合报告之吞卡信息汇总)
		Date lastMonth =DateUtils.getLastMonth();
		retainCardExtractDataService.extractByMonth(lastMonth);
		logger.info(String.format("executeMonthDevice SPEND [%s]ms",System.currentTimeMillis() - start));

	}	
}
