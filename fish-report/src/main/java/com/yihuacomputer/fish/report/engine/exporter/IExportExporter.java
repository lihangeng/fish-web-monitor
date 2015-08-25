package com.yihuacomputer.fish.report.engine.exporter;

import net.sf.jasperreports.engine.JasperPrint;

/**
 * @author YiHua
 */
public interface IExportExporter {

	/**
	 * 生成报表
	 * 
	 * @param outputFile
	 * @param jasperPrint
	 */
	public void exportReport(String outputFile, JasperPrint jasperPrint);

}
