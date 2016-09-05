package com.yihuacomputer.fish.api.report.engine;

/**
 * 周生成报告
 * 
 * @author xuxiang
 *
 */
public interface IWeekPdfReportService extends IPdfReportService {
	/**
	 * 生成周报
	 * 
	 * @param weekOfYear
	 *            yyyyww 201636
	 * @return 返回生成的pdf的绝对路径
	 */
	public String generateWeekPDF(int weekOfYear);

}
