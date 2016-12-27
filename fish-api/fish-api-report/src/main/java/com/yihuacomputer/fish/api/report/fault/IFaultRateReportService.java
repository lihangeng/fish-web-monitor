package com.yihuacomputer.fish.api.report.fault;

import java.util.List;

import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.report.trans.ITransactionMonths;



/**
 * @author YiHua
 *
 */
public interface IFaultRateReportService {
	
	/**
	 * @return
	 */
	Iterable<ITransactionMonths> typeTrans();
	
	/**
	 * @return
	 */
	Iterable<IEveryMonthFaultCount> typeFault();
	
	/**
	 * @param name
	 * @return
	 */
	List<IAtmType> getType(String name);
	
	/**
	 * @param typeId
	 * @return
	 */
	List<IAtmModule> getModule(long typeId);
	
	/**
	 * @param monthStr
	 * @return
	 */
	List<FaultRateReport> listAllHql(String monthStr);
	
	/**
	 * @param monthStr
	 * @param vendorId
	 * @return
	 */
	List<FaultRateReport> listByVendorHql(String monthStr,long vendorId);
	
	/**
	 * @param monthStr
	 * @param vendorId
	 * @param devTypeId
	 * @return
	 */
	List<FaultRateReport> listByDevTypeHql(String monthStr,long vendorId,long devTypeId);
	
	/**
	 * @param time
	 * @param vendorId
	 * @param devTypeId
	 * @return
	 */
	FaultRateReport getTradeCount(String time,long vendorId,long devTypeId);

}
