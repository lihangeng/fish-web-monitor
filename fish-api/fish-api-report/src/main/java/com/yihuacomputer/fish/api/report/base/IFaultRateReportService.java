package com.yihuacomputer.fish.api.report.base;

public interface IFaultRateReportService {
	
	
	Iterable<ITransactionMonths> typeTrans();
	
	Iterable<IEveryMonthFaultCount> typeFault();

}
