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
			
			pdf.addEmptyLine(1);
			pdf.addContent("3.最近4周新增设备和报废设备的趋势图");
			JFreeChart devChart = PdfTest.generateLineChart();
			int devChartWidth = (int)(PageSize.A4.getWidth() * 0.9);
			pdf.addChart(devChart, devChartWidth, 260);
			
			pdf.addEmptyLine(1);
			pdf.addChapter("二、开机率汇总");
			pdf.addContent("1. 上周所有设备平均开机率为96.78%，低于平均值的23台。");
			pdf.addContent("2.上周每日的开机率趋势");
			pdf.addChart(PdfTest.generateLineChart(), devChartWidth, 260);
			
			pdf.addContent("3.上周所有设备型号的开机率，从高到低排列");
			pdf.addTable();
			
			pdf.addContent("4.上周各网点统计的开机率，较好网点Top10，较差网点Top10");
			pdf.addTable();
			
			pdf.addContent("5.上周设备统计的开机率，较好设备Top10，较差设备Top10");
			pdf.addTable();
			
			pdf.addEmptyLine(1);
			pdf.addChapter("三、交易数据汇总");
			pdf.addContent("1.上周共产生交易2000次，平均20次/日。相比较上周交易次数增加（减少）1000次。");
			
			pdf.addContent("2.上周按照交易类型统计的交易次数和交易金额");
			pdf.addTable();
			
			pdf.addEmptyLine(1);
			pdf.addChapter("四、吞卡数据汇总");
			pdf.addContent("1.上周共产生吞卡200次，平均20次/日（/台）。相比较上周增加（减少）10次吞卡。");
			pdf.addContent("2.上周各型号设备吞卡次数统计表");
			pdf.addTable();
			
			pdf.addEmptyLine(1);
			pdf.addChapter("五、设备故障数据汇总");
			pdf.addContent("1.上周共产生故障数200个，其中已关闭20个，未关闭100个。");
			pdf.addContent("2.上周按照故障类型统计故障数量");
			pdf.addChart(PdfTest.generateBarChart(), chartWidth, 260);
			pdf.addContent("3.上周故障关闭时间的分布图");
			JFreeChart faultChart = PdfTest.generatePieChart();
			pdf.addChart(faultChart, chartWidth, 250);
			
			
//			pdf.addChart(PdfTest.generateBarChart(), chartWidth, 300);
//			pdf.addTable();
			pdf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
