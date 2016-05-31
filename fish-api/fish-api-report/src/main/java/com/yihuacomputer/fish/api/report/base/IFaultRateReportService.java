package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmType;


public interface IFaultRateReportService {
	
	List<FaultRateReport> listByBrand(String time);
	
	List<FaultRateReport> listByType(String name,String time);
	
	List<FaultRateReport> listByModule(String name,String time);
	
	Iterable<ITransactionMonths> typeTrans();
	
	Iterable<IEveryMonthFaultCount> typeFault();
	
	List<IAtmType> getType(String name);
	
	List<IAtmModule> getModule(String name);

}
