package com.yihuacomputer.fish.report.engine.exporter;

import java.io.File;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 生成处理PDF报表
 * 
 * @author YiHua
 * @since 2010-8-3
 */
@Service
public class PdfExporter implements IExportExporter {

	private static Log log=LogFactory.getLog(PdfExporter.class);

	/**
	 * 生成报表
	 * 
	 * @param outputFile
	 * @param jasperPrint
	 */
	public void exportReport(String outputFile, JasperPrint jasperPrint){
		try{
			File pdfFile =new File(outputFile);
			JRAbstractExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, pdfFile);	
			exporter.exportReport();	
		}catch (JRException e){	
			log.error(String.format("生成报表PDF文件出错！[%s]",e));
		}		 
	}
}
