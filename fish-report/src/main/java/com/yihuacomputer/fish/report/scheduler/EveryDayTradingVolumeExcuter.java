package com.yihuacomputer.fish.report.scheduler;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.monitor.volume.IDayTradingVolumeService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;

/**
 * 每天加载前一日的日交易量
 * @author GQ
 *
 */
@Service
public class EveryDayTradingVolumeExcuter  implements IReportDataETL {
	
	private static String etlName = "EveryDayTradingVolumeExcuter";
	
	@Autowired
	private IDayTradingVolumeService dayTradingVolumeService;

	@Autowired
	private IExportDataETLService exportDataETLService;
	@Override
	@PostConstruct
	public void init() {
		exportDataETLService.addEveryDayETL(this);		
	}

	@Override
	public String getReportETLName() {
		return etlName;
	}

	@Override
	public void reportETL(String date) {
		date = date.replaceAll("-", "");
		dayTradingVolumeService.generalDayTradingVolumeByDate(date);
	}
}
