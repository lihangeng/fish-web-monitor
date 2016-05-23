package com.yihuacomputer.fish.api.report.base;

public interface IEveryMonthFaultCountService {

	public void add(IEveryMonthFaultCount everyMonthFaultCount);

	/**
	 * @param date yyyyMM
	 */
	public void extractMonthFault(String date);

}
