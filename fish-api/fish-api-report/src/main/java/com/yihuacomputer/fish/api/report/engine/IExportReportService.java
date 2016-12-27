package com.yihuacomputer.fish.api.report.engine;


/**
 * @author YiHua
 *
 */
public interface IExportReportService {

	/**
	 * 处理报表输出
	 * @param reportParam
	 * @return
	 */
	public IReportExport genReport(IReportParam reportParam);
}
