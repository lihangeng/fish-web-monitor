package com.yihuacomputer.fish.api.report.engine;

/**
 * 综合分析报告服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IPdfReportService {
	
	/**
	 * 生成周报
	 * @param weekOfYear yyyyww 201636
	 * @return 返回生成的pdf的绝对路径
	 */
	public String generateWeekPDF(int weekOfYear);
	
	/**
	 * 生成月报
	 * @param month yyyyMM 201608
	 * @return 返回生成的pdf的绝对路径
	 */
	public String generateMonthPDF(int month);

	/**
	 * 发送pdf
	 * @param pdfName 文件名称，绝对路径
	 */
	public void sendPdf(String pdfName);

}
