package com.yihuacomputer.fish.report.engine.pdf;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 * 在插入在PDF中的图的简单封装
 * 
 * @author xuxiang
 *
 */
public class PdfChart {
	private static final String EMTPY_STR = "";

	/**
	 * 生成折线图
	 * 
	 * @param dataset
	 * @return
	 */
	public static JFreeChart generateLineChart(DefaultCategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createLineChart("", "", "", dataset,
	            PlotOrientation.VERTICAL, // 绘制方向
	            true, // 显示图例
	            true, // 采用标准生成器
	            false // 是否生成超链接
	            );
		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
	    LineAndShapeRenderer lineandshaperenderer = (LineAndShapeRenderer) categoryplot.getRenderer();
	    lineandshaperenderer.setBaseShapesVisible(true); // series 点（即数据点）可见
	    lineandshaperenderer.setBaseLinesVisible(true); // series 点（即数据点）间有连线可见
	 
	    // 显示折点数据
	    lineandshaperenderer
	            .setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    lineandshaperenderer.setBaseItemLabelsVisible(true);

		return chart;
	}
	
	/**
	 * 生成柱状图
	 * @param dataSet
	 * @return
	 */
	public static JFreeChart generateBarChart(DefaultCategoryDataset dataSet) {
		JFreeChart chart = ChartFactory.createBarChart(EMTPY_STR, EMTPY_STR, EMTPY_STR,
				dataSet, PlotOrientation.VERTICAL, false, true, false);
		 CategoryPlot categoryPlot = (CategoryPlot) chart.getPlot();
		  BarRenderer barRenderer = (BarRenderer) categoryPlot.getRenderer();
		  barRenderer.setIncludeBaseInRange(true);
		    barRenderer.setBaseItemLabelGenerator(
		            new StandardCategoryItemLabelGenerator());
		    barRenderer.setBaseItemLabelsVisible(true);
		return chart;
	}
	
	/**
	 * 生成饼图
	 * @param dataSet
	 * @return
	 */
	public static JFreeChart generatePieChart(DefaultPieDataset dataSet) {
		JFreeChart chart = ChartFactory.createPieChart("", dataSet, true, true, false);
		return chart;
	}
}
