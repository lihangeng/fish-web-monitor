package com.yihuacomputer.fish.report.scheduler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;

@Service
public class AtmcDayTransCount implements IReportDataETL{

	private final String etlName = "ATMC_TRANS_COUNT";
	
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
		// TODO Auto-generated method stub
	}

	

}
