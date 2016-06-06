package com.yihuacomputer.fish.api.report.base;

import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmType;


public interface IFaultRateReportService {
	
	Iterable<ITransactionMonths> typeTrans();
	
	Iterable<IEveryMonthFaultCount> typeFault();
	
	List<IAtmType> getType(String name);
	
	List<IAtmModule> getModule(long typeId);
	
	List<FaultRateReport> listAllHql(String monthStr);
	
	List<FaultRateReport> listByVendorHql(String monthStr,long vendorId);
	
	List<FaultRateReport> listByDevTypeHql(String monthStr,long vendorId,long devTypeId);
	
	FaultRateReport getTradeCount(long vendorId,long devTypeId);

}
