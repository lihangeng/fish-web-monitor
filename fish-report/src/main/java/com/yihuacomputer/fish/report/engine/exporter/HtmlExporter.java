package com.yihuacomputer.fish.report.engine.exporter;

import java.io.File;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 处理Html报表
 * 
 * @author YiHua
 * @since 2010-8-3
 */
@Service
public class HtmlExporter implements IExportExporter {

	private static Log log=LogFactory.getLog(HtmlExporter.class);

	/**
	 * 生成报表
	 * 
	 * @param outputFile
	 * @param jasperPrint
	 */
	public void exportReport(String outputFile, JasperPrint jasperPrint){
		try	{
			File htmlFile = new File(outputFile);
			JRAbstractExporter exporter = new JRHtmlExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, htmlFile);
			exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
			
			exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE); 
			exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
		
			exporter.exportReport();
		}catch (JRException e){	
			log.error(String.format("Genarate report HTML files Error![%s]",e));
		}		 
	}
}
