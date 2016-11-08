package com.yihuacomputer.fish.api.report.fault;

public interface IEveryMonthFaultCountExtractDataService {

	/**
	 * @param date yyyyMM
	 */
	public void extractMonthFault(String date);
}
