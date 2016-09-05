package com.yihuacomputer.fish.report.service.etl;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.text.DocumentException;
import com.yihuacomputer.fish.report.engine.pdf.Pdf;

/**
 * jfreechart生成的图属性测试
 * @author xuxiang
 *
 */
public class PdfChartTest {

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		int width = 500;
		int height = 500;
		Pdf pdf = new Pdf();
		try {
			pdf.createPdf(new File("D://chart.pdf"));
			pdf.addChart(generateLineChart(), width, height);
			pdf.addEmptyLine(3);
			pdf.addChart(generateBarChart(), width, height);
			pdf.addEmptyLine(3);
			pdf.addChart(generatePieChart(), width, height);
			pdf.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static JFreeChart generatePieChart() {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		JFreeChart chart = ChartFactory.createPieChart("", dataSet, true, true, false);

		return chart;
	}

	public static JFreeChart generateBarChart() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		JFreeChart chart = ChartFactory.createBarChart("World Population growth", "Year", "Population in millions", dataSet, PlotOrientation.VERTICAL, false, true, false);
		return chart;
	}

	public static JFreeChart generateLineChart() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(212, "ATM", "2016033");
		dataset.addValue(504, "VTM", "2016033");
		dataset.addValue(1520, "ATM", "J2016034");
		dataset.addValue(1842, "VTM", "2016034");
		dataset.addValue(2991, "ATM", "2016035");
		dataset.addValue(3500, "VTM", "2016035");
		JFreeChart chart = ChartFactory.createLineChart("", // chart title
				"", // x axis label
				"", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setRangeGridlinesVisible(true);
		return chart;
	}

}
