package com.yihuacomputer.fish.api.report.fault;

/**
 * @author YiHua
 *
 */
public interface IEveryMonthFaultCountService {

	/**
	 * @param everyMonthFaultCount
	 */
	public void add(IEveryMonthFaultCount everyMonthFaultCount);

	/**
	 * @return
	 */
	public IEveryMonthFaultCount make();

}
