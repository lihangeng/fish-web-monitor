package com.yihuacomputer.fish.report.pdf;

import java.io.File;

import org.jfree.chart.JFreeChart;

import com.itextpdf.text.PageSize;
import com.yihuacomputer.fish.report.engine.pdf.FontMgr;
import com.yihuacomputer.fish.report.engine.pdf.ParagraphMgr;
import com.yihuacomputer.fish.report.engine.pdf.Pdf;

public class ReportTest {

	public static void main(String[] args) {
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(new File("D:/自助设备运行分析报告_2016年第36周(8.29-9.4).pdf"));
			pdf.addTitle("中国农业银行");
			pdf.addSubTitle("自助设备运行分析报告");
			pdf.addTriTitle("2016年第36周(8.29-9.4)");
			pdf.addEmptyLine(2);
			pdf.addChapter("一、设备信息汇总");
			ParagraphMgr mgr = ParagraphMgr.getInstance();
			mgr.addChunk("1. 截止2016 年9月4日，共有设备200台，其中",FontMgr.getFont14())
			   .addChunk("ATM")
			   .addChunk("有60台，",FontMgr.getFont14())
			   .addChunk("CDS")
			   .addChunk("有130台，",FontMgr.getFont14())
			   .addChunk("ASM")
			   .addChunk("有10台。上周（上个月）新增设备0台，报废设备0台。",FontMgr.getFont14());
			pdf.addParagraph(mgr);
			pdf.addContent("2.设备型号统计");
			JFreeChart chart = PdfTest.generatePieChart();
			int chartWidth = (int)(PageSize.A4.getWidth() * 0.8);
			pdf.addChart(chart, chartWidth, 200);
			pdf.addChapter("二、开机率汇总");
			pdf.addContent("1. 上周所有设备平均开机率为96.78%，低于平均值的23台。");
			pdf.addChart(PdfTest.generateBarChart(), chartWidth, 200);
			
			pdf.addTable();
			pdf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
