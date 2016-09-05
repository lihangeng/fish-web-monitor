package com.yihuacomputer.fish.report.engine.scheduler;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.engine.IMonthPdfReportService;
import com.yihuacomputer.fish.api.report.fault.IEveryMonthFaultCountService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.trans.ITransactionMonthsService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;

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
	private IEveryMonthFaultCountService everyMonthFaultCountService;
	
	@Autowired
	private IMonthDailyTradingVolumeService monthDailyTradingVolumeService;
	
	@Autowired
	private IAvgOpenRateEtlService avgOpenRateEtlService;
	
	@Autowired
	private IDeviceOpenRateEtlService deviceOpenRateEtlService;
	
	@Autowired
	private IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService;
	
	@Autowired
	private IOrgOpenRateEtlService orgOpenRateEtlService;
	
	@Autowired
	private ITransTypeEtlService transTypeEtlService;
	
	@Autowired
	private IRetainCardEtlService retainCardEtlService;

	@Autowired
	private IFaultEtlService faultEtlService;
	
	@Autowired
	private IDeviceCatalogSummaryMonthService deviceCatalogSummaryMonthService;
	
	@Autowired
	private IDeviceTypeSummaryMonthService deviceTypeSummaryMonthService;
	
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
		everyMonthFaultCountService.extractMonthFault(yestoday);
		
		//2.每月故障类型统计(2016年3季度需求综合报告之故障信息汇总)
		Date lastMonth =DateUtils.getLastMonth();
		faultEtlService.extractStatusByMonth(lastMonth);
		faultEtlService.extractFaultClassifyByMonth(lastMonth);
		faultEtlService.extractDurationByMonth(lastMonth);
		logger.info(String.format("executeMonthFault SPEND [%s]ms",System.currentTimeMillis() - start));
	}

	/**
	 * 开机率相关月统计
	 */
	private void executeMonthOpenRate(){
		long start = System.currentTimeMillis();
		Date lastMonth =DateUtils.getLastMonth();
		//1.每月开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		avgOpenRateEtlService.extractByMonth(lastMonth);
		//2.每月设备开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceOpenRateEtlService.extractByMonth(lastMonth);
		//3.每月设备型号开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceTypeOpenRateEtlService.extractByMonth(lastMonth);
		//4.每月机构开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		orgOpenRateEtlService.extractByMonth(lastMonth);
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
		transTypeEtlService.extractByMonth(lastMonth);
		logger.info(String.format("executeMonthTrans SPEND [%s]ms",System.currentTimeMillis() - start));

	}

	/**
	 * 设备相关月统计
	 */
	private void executeMonthDevice() {
		long start = System.currentTimeMillis();
		//1.设备类型统计
		Date dateNow = new Date();
		deviceCatalogSummaryMonthService.loadBaseData(dateNow);
		//2.设备型号统计
		deviceTypeSummaryMonthService.loadBaseData(dateNow);
		//3.每月吞卡统计(2016年3季度需求综合报告之吞卡信息汇总)
		Date lastMonth =DateUtils.getLastMonth();
		retainCardEtlService.extractByMonth(lastMonth);
		logger.info(String.format("executeMonthDevice SPEND [%s]ms",System.currentTimeMillis() - start));

	}	
}
