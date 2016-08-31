package com.yihuacomputer.fish.report.engine;

import java.io.File;
import java.util.UUID;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.api.report.engine.IReportExport;
import com.yihuacomputer.fish.api.report.engine.IReportParam;
import com.yihuacomputer.fish.report.engine.exporter.HtmlExporter;
import com.yihuacomputer.fish.report.engine.exporter.PdfExporter;
import com.yihuacomputer.fish.report.engine.exporter.XlsExporter;

/**
 * @author Yihua
 * @since 2013-1-28
 */
@Service
public class ExportReportService implements IExportReportService {

	private static Log log=LogFactory.getLog(ExportReportService.class);

	@Autowired
	private PdfExporter pdfHander;

	@Autowired
	private XlsExporter xlsHander;

	@Autowired
	private HtmlExporter htmlHander;

	/**
	 * 处理报表输出
	 * 
	 * @param pdf生成该类型报表
	 * @param html生成该类型报表
	 * @param xls生成该类型报表
	 * @param parameters报表参数
	 * @param dataList报表数据
	 * @param reportModule报表模板
	 * */	
	
	public IReportExport genReport(IReportParam params) {
		ReportExport result = new ReportExport();

		String fileName = UUID.randomUUID().toString();

		JasperPrint jasperPrint = null;
		try {
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(params.getDataList());
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(params.getReportModule());
			jasperPrint = JasperFillManager.fillReport(jasperReport,params.getParameters(), ds);
		} catch (JRException e) {
			log.error(String.format("Fill the report data error![%s]", e));
			result.setResult(false);
			return result;
		}

		File reportFilePath = new File(params.getReportFilepath());
		if (!reportFilePath.isDirectory()) {
			reportFilePath.mkdirs();
		}

		/* 设置生成的PDF文件名 */
		if (params.isPdf()) {
			String pdfFile = params.getReportFilepath() + FishCfg.fileSep + fileName + ".pdf";
			pdfHander.exportReport(pdfFile, jasperPrint);
			result.setPdfFile(pdfFile.replace("\\", "/"));
		}

		/* 设置生成的HTML文件名 */
		if (params.isHtml()) {
			String htmlFile = params.getReportFilepath() + FishCfg.fileSep + fileName	+ ".html";
			htmlHander.exportReport(htmlFile, jasperPrint);
			result.setHtmlFile(htmlFile.replace("\\", "/"));
		}

		/* 设置生成的XLS文件名 */
		if (params.isXls()) {
			String xlsFile = params.getReportFilepath() + FishCfg.fileSep + fileName + ".xls";
			xlsHander.setXlsSheetNames(params.getSheetNames());	// 工作薄的名称
			xlsHander.exportReport(xlsFile, jasperPrint);
			result.setXlsFile(xlsFile.replace("\\", "/"));
		}
		result.setResult(true);
		return result;
	}
}


