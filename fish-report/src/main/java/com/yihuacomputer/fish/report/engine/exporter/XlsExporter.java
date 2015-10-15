package com.yihuacomputer.fish.report.engine.exporter;

import java.io.File;

import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 * 处理XLS报表
 * 
 * @author YiHua
 * @since 2010-8-3
 */
@Service
public class XlsExporter implements IExportExporter {

	private static Log log=LogFactory.getLog(XlsExporter.class);
	
	private String[] sheetNames;
	
	/**
	 * 生成报表
	 * 
	 * @param outputFile
	 * @param jasperPrint
	 */
	public void exportReport(String outputFile, JasperPrint jasperPrint){
		try	{
			File xlsFile = new File(outputFile);
			JRAbstractExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE, xlsFile);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.FALSE);//去掉excel单元格自动变换
			exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);//自动修正Excel每个栏位的大小

			if (sheetNames != null && sheetNames.length > 0) {
				exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames); // 工作薄的名称	
			}
			exporter.exportReport();	
		}catch (JRException e){	
			log.error(String.format("Genarate report HTML files Error![%s]",e));
		}		 
	}


	public void setXlsSheetNames(String[] sheetNames) {
		this.sheetNames = sheetNames;
	}
}
