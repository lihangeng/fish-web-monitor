package com.yihuacomputer.fish.report.engine.pdf;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;

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
//	public static JFreeChart generateLineChart(XYDataset dataset) {
//		return  ChartFactory.createXYLineChart(EMTPY_STR, EMTPY_STR, EMTPY_STR, dataset, PlotOrientation.VERTICAL, true, true, false);
//	}
	public static JFreeChart generateLineChart(DefaultCategoryDataset dataset) {
	return ChartFactory.createLineChart("", "", "", dataset,
            PlotOrientation.VERTICAL, // 绘制方向
            true, // 显示图例
            true, // 采用标准生成器
            false // 是否生成超链接
            );
	}
	/**
	 * 生成柱状图
	 * @param dataSet
	 * @return
	 */
	public static JFreeChart generateBarChart(DefaultCategoryDataset dataSet) {
		JFreeChart chart = ChartFactory.createBarChart(EMTPY_STR, EMTPY_STR, EMTPY_STR,
				dataSet, PlotOrientation.VERTICAL, false, true, false);
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
