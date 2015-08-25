package com.yihuacomputer.fish.api.report.engine;


public interface IExportReportService {

	/**
	 * 处理报表输出
	 * */
	public IReportExport genReport(IReportParam reportParam);
}
