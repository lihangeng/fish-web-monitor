package com.yihuacomputer.fish.report.scheduler;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.classic.Logger;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.report.IDeviceOpenRateService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;
import com.yihuacomputer.fish.report.engine.scheduler.EveryDayReportJob;
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
	
	/**
	 * 定时任务调用执行每日备份工作
	 * @throws Exception
	 */
	public void reportETL(String date){		
		String yestoday = DateUtils.getLastDate();
		openRateService.dayOpenRate(yestoday);		
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
