package com.yihuacomputer.fish.report.scheduler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.device.IDeviceOpenRateService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
/**
 * 启动每日日志备份工作
 * 
 * @author YiHua
 */
@Service
public class DayOpenRateExcuter implements IReportDataETL{

	private final String etlName = "SRCB_OPEN_RATE";
	@Autowired
	private IDeviceOpenRateService openRateService;

	@Autowired
	private IExportDataETLService exportDataETLService;
	
	@Autowired(required=false)
	private IAvgOpenRateEtlService avgDayOpenRateService;
	
	/**
	 * 定时任务调用执行每日备份工作
	 * @throws Exception
	 */
	public void reportETL(String date){		
		String yestoday = DateUtils.getLastDate();
		//1.计算前一天所有设备的开机率
		openRateService.dayOpenRate(yestoday);		
		//2.计算掐一天所有设备平均开机率
		if(avgDayOpenRateService != null){
			avgDayOpenRateService.extractByDay(DateUtils.getDate(yestoday));
		}
	}

	@Override
	@PostConstruct
	public void init() {
		exportDataETLService.addEveryDayETL(this);
	}

	@Override
	public String getReportETLName() {
		return etlName;
	}
}
