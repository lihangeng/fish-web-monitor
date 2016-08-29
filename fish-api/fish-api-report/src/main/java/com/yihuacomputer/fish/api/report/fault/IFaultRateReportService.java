package com.yihuacomputer.fish.api.report.fault;

import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.report.trans.ITransactionMonths;



public interface IFaultRateReportService {
	
	Iterable<ITransactionMonths> typeTrans();
	
	Iterable<IEveryMonthFaultCount> typeFault();
	
	List<IAtmType> getType(String name);
	
	List<IAtmModule> getModule(long typeId);
	
	List<FaultRateReport> listAllHql(String monthStr);
	
	List<FaultRateReport> listByVendorHql(String monthStr,long vendorId);
	
	List<FaultRateReport> listByDevTypeHql(String monthStr,long vendorId,long devTypeId);
	
	FaultRateReport getTradeCount(String time,long vendorId,long devTypeId);

}
