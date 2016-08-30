package com.yihuacomputer.fish.report.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;
import com.yihuacomputer.fish.report.engine.scheduler.EveryDayReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryMonthReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryWeekReportJob;
import com.yihuacomputer.fish.report.engine.scheduler.EveryYearReportJob;
@Service
public class ExportDataETLService implements IExportDataETLService {

	@Autowired
	private EveryDayReportJob dayReportJob;
	
	@Autowired
	private EveryMonthReportJob monthReportJob;
	
	@Autowired
	private EveryYearReportJob yearReportJob;
	
	@Autowired
	private EveryWeekReportJob weekReportJob;
	
	@Override
	public void addEveryDayETL(IReportDataETL dataETL) {
		dayReportJob.addDataETL(dataETL);
	}

	@Override
	public void addEveryMonthETL(IReportDataETL dataETL) {
		monthReportJob.addDataETL(dataETL);

	}

	@Override
	public void addEveryYearETL(IReportDataETL dataETL) {
		yearReportJob.addDataETL(dataETL);
	}

	@Override
	public void addEveryWeekETL(IReportDataETL dataETL) {
		weekReportJob.addDataETL(dataETL);
	}

}
