package com.yihuacomputer.fish.report.engine.scheduler;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.fault.etl.IFaultEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;

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
	private IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService;
	
	@Autowired
	private IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService;
	
	/**
	 * 每周统计
	 */
	public void execute(){
		logger.debug("week data ETL finished");
		Date date =DateUtils.getDate(DateUtils.getLastDate());
		//0.设备类统计
		deviceCatalogSummaryWeekService.loadBaseData(date);
		deviceTypeSummaryWeekService.loadBaseData(date);
		//1.每周开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		avgOpenRateEtlService.extractByWeek(date);
		//2.每周设备开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceOpenRateEtlService.extractByWeek(date);
		//3.每周设备型号开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		deviceTypeOpenRateEtlService.extractByWeek(date);
		//4.每周机构开机率统计(2016年3季度需求综合报告之开机率信息汇总)
		orgOpenRateEtlService.extractByWeek(date);
		//5.每周交易类型统计(2016年3季度需求综合报告之交易信息汇总)
		transTypeEtlService.extractByWeek(date);
		//6.每周吞卡统计(2016年3季度需求综合报告之吞卡信息汇总)
		retainCardEtlService.extractByWeek(date);
		//7.每周故障类型统计(2016年3季度需求综合报告之故障信息汇总)
		faultEtlService.extractStatusByWeek(date);
		faultEtlService.extractFaultClassifyByWeek(date);
		faultEtlService.extractDurationByWeek(date);
	}
	
}
