package com.yihuacomputer.fish.api.report.engine;

public interface IReportExport {

	/**
	 * 生成的PDF文件路径
	 * @return
	 */
	public String getPdfFile();
	
	/**
	 * 生成的HTML文件路径
	 * @return
	 */
	public String getHtmlFile();
	
	/**
	 * 生成的XLS文件路径
	 * @return
	 */
	public String getXlsFile();
	
	/**
	 * 是否成功
	 * @return
	 */
	public boolean isSuccess();
}
