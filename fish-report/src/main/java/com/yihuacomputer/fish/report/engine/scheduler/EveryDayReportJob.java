package com.yihuacomputer.fish.report.engine.scheduler;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.report.device.IDeviceOpenRateService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.trans.ITransactionDaysService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;

/**
 * 每日报表数据生成
 * 
 * @author YiHua 
 * @since 2010-9-8
 */
@Service("DayETLJob")
public class EveryDayReportJob {
	
	private static Log logger = LogFactory.getLog(EveryDayReportJob.class);
	
	@Autowired
	private ITransactionDaysService transactionDaysService;
	
	@Autowired
	private IDeviceOpenRateService openRateService;

	@Autowired(required=false)
	private IAvgOpenRateEtlService avgDayOpenRateService;

	@Autowired
	private ICashInitPlanInfoService cashInitPlanInfoService;
	
	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	private IDayTradingVolumeService dayTradingVolumeService;
	
	@Autowired
	private ITransTypeEtlService transTypeEtlService;

	/**
	 * 每日抽取
	 */
	public void execute(){
		logger.info("starting day data ETL ....");
		//1.计算前一天所有设备的开机率
		String yestoday = DateUtils.getLastDate();
		openRateService.dayOpenRate(yestoday);		
		//2.计算前一天一天所有设备平均开机率(2016年3季度综合报告之开机率报告)
		avgDayOpenRateService.extractByDay(DateUtils.getDate(yestoday));
		//3.每日交易信息统计
		String shortYestory = DateUtils.getLastShortDate();
		transactionDaysService.extractDate(shortYestory);
		//4.每台设备交易量统计(2016年3季度需求加钞设备应加金额的计算)
		dayTradingVolumeService.generalDayTradingVolumeByDate(shortYestory);
		//5.计算每日交易类型(2016年3季度需求综合报告之交易信息汇总)
		transTypeEtlService.extractByDay(DateUtils.getDate(DateUtils.getLastDate()));
		//6.计算加钞设备(2016年3季度加钞计划)
		IOrganization org = orgService.get("1");
		cashInitPlanInfoService.generalCashInitPlan(org, DateUtils.getDateShort(new Date()));
		logger.info("Day data ETL finished");
	}
}
