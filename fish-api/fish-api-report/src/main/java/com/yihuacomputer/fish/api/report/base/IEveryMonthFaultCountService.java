package com.yihuacomputer.fish.api.report.base;

public interface IEveryMonthFaultCountService {

	public void add(IEveryMonthFaultCount everyMonthFaultCount);

	public void extractMonthFault(String date);

}
