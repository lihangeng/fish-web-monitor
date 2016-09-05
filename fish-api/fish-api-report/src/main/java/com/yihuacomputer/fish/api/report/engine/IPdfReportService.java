package com.yihuacomputer.fish.api.report.engine;

/**
 * 综合分析报告服务
 * @author xuxiang
 * @since 2.1.1.1
 */
public interface IPdfReportService {
	
	/**
	 * 发送pdf
	 * @param pdfName 文件名称，绝对路径
	 */
	public void sendPdf(String pdfName);

}
