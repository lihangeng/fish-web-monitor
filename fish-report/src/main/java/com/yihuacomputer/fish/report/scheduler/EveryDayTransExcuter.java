package com.yihuacomputer.fish.report.scheduler;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.batch.base.IETLjobDaysService;
import com.yihuacomputer.fish.api.report.base.ITransactionDaysService;
import com.yihuacomputer.fish.api.report.engine.IExportDataETLService;
import com.yihuacomputer.fish.api.report.engine.IReportDataETL;

@Service
public class EveryDayTransExcuter implements IReportDataETL {

	private final String etlName = "atmc_transaction_days";
	@Autowired
	private ITransactionDaysService transactionDaysService;

	@Autowired
	private IETLjobDaysService iDaysService;
	
	@Autowired
	private IExportDataETLService exportDataETLService;

	@Override
	public void reportETL(String date) {
		String yestoday = DateUtils.getLastShortDate();
		transactionDaysService.extractDate(yestoday);
		iDaysService.extractDate(yestoday);
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
