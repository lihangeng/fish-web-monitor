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

	public void execute(){
		logger.debug("month data ETL ....");
		//1.每月交易统计（2016年2季度增加）ATMC_TRANSACTION_MONTHS
		String yestoday = DateUtils.getLastMonthShortDates();
		transactionMonthsService.extractDate(yestoday);
		//2.（2016年2季度增加）计算设备故障率 CASE_FAULT_MONTH
		everyMonthFaultCountService.extractMonthFault(yestoday);
		//3.每台设备日均交易统计(加钞计划使用)
		String curdate = DateUtils.getDateShort(new Date());
		monthDailyTradingVolumeService.generalMonthDailyTradingVolumeByDate(curdate);
		
		//0.设备类统计
		Date dateNow = new Date();
		deviceCatalogSummaryMonthService.loadBaseData(dateNow);
		deviceTypeSummaryMonthService.loadBaseData(dateNow);
		Date date =DateUtils.getDate(DateUtils.getLastDate());
		//1.每周开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		avgOpenRateEtlService.extractByMonth(date);
		//2.每周设备开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceOpenRateEtlService.extractByMonth(date);
		//3.每周设备型号开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceTypeOpenRateEtlService.extractByMonth(date);
		//4.每周机构开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		orgOpenRateEtlService.extractByMonth(date);
		//5.每周交易类型统计(2016年3季度需求综合报告之交易信息汇总)
		transTypeEtlService.extractByMonth(date);
		//6.每周吞卡统计(2016年3季度需求综合报告之吞卡信息汇总)
		retainCardEtlService.extractByMonth(date);
		//7.每周故障类型统计(2016年3季度需求综合报告之故障信息汇总)
		faultEtlService.extractStatusByMonth(date);
		faultEtlService.extractFaultClassifyByMonth(date);
		faultEtlService.extractDurationByMonth(date);
		logger.debug("month data ETL finished");
	}	
}
