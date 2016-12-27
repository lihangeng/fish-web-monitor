package com.yihuacomputer.fish.api.report.fault;

/**
 * @author YiHua
 *
 */
public interface IEveryMonthFaultCountExtractDataService {

	/**
	 * @param date yyyyMM
	 */
	public void extractMonthFault(String date);
}
