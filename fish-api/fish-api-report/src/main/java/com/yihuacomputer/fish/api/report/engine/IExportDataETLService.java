package com.yihuacomputer.fish.api.report.engine;

public interface IExportDataETLService {

	/**
	 * 设置每日数据抽取引擎
	 * @param dataETL
	 */
	public void addEveryDayETL(IReportDataETL dataETL);
	
	/**
	 * 设置每月数据抽取引擎
	 * @param dataETL
	 */
	public void addEveryMonthETL(IReportDataETL dataETL);
	
	/**
	 * 设置每年度数据抽取引擎
	 * @param dataETL
	 */
	public void addEveryYearETL(IReportDataETL dataETL);
	
}
