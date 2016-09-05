package com.yihuacomputer.fish.api.report.engine;

/**
 * 周生成报告
 * 
 * @author xuxiang
 *
 */
public interface IMonthPdfReportService extends IPdfReportService {

	/**
	 * 生成月报
	 * 
	 * @param month
	 *            yyyyMM 201608
	 * @return 返回生成的pdf的绝对路径
	 */
	public String generateMonthPDF(int month);
}
