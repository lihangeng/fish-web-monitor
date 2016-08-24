package com.yihuacomputer.fish.report.scheduler;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;

/**
 * @author GQ
 *	每月加载前一月的日均交易量
 */
@Service
public class EveryMonthDaliyTradingVolumeExcuter implements IReportDataETL {

	private final static String etlName = "EveryMonthDaliyTradingVolumeExcuter";
	
	@Autowired
	private IExportDataETLService exportDataETLService;
	
	@Autowired
	private IMonthDailyTradingVolumeService monthDailyTradingVolumeService;
	
	@Override
	@PostConstruct
	public void init() {
		exportDataETLService.addEveryMonthETL(this);
	}

	@Override
	public String getReportETLName() {
		// TODO Auto-generated method stub
		return etlName;
	}

	@Override
	public void reportETL(String dateStr) {
		Date date = DateUtils.getDate(dateStr);
		dateStr = DateUtils.getNextShortDate(date);
		monthDailyTradingVolumeService.generalMonthDailyTradingVolumeByDate(dateStr);
	}
	
	

}
