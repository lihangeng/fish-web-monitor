package com.yihuacomputer.fish.api.report.base;

import java.util.List;


public interface IFaultRateReportService {
	
	List<FaultRateReport> listByBrand();
	
	List<FaultRateReport> listByType();
	
	List<FaultRateReport> listByModule();
	
	Iterable<ITransactionMonths> typeTrans();
	
	Iterable<IEveryMonthFaultCount> typeFault();

}
