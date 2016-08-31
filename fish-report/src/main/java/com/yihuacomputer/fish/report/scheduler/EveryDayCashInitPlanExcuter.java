package com.yihuacomputer.fish.report.scheduler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;

public class EveryDayCashInitPlanExcuter implements IReportDataETL {

	@Autowired
	private ICashInitPlanInfoService cashInitPlanInfoService;
	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IExportDataETLService exportDataETLService;
	@Override
	@PostConstruct
	public void init() {
		exportDataETLService.addEveryDayETL(this);
	}

	@Override
	public String getReportETLName() {
		return "EveryDayCashInitPlanExcuter";
	}

	@Override
	public void reportETL(String date) {
		IOrganization org = orgService.get("1");
		date = date.replaceAll("-", "");
		cashInitPlanInfoService.generalCashInitPlan(org, date);
	}

}
