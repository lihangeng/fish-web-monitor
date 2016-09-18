package com.yihuacomputer.fish.report.engine.pdf;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLabelLocation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

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
	public static JFreeChart generateLineChart(DefaultCategoryDataset dataset,String XVallue,String YValue) {
		JFreeChart chart = ChartFactory.createLineChart(EMTPY_STR, XVallue, YValue, dataset,
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
	    lineandshaperenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
	    lineandshaperenderer.setBaseItemLabelsVisible(true);
	    lineandshaperenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE10, TextAnchor.BOTTOM_CENTER));
	    categoryplot.setDomainGridlinePaint(Color.blue); 
	    categoryplot.setDomainGridlinesVisible(true); 
	    categoryplot.setDomainGridlinePosition(CategoryAnchor.MIDDLE);
		  //设置网格横线颜色 
	    categoryplot.setRangeGridlinePaint(Color.blue); 
	    categoryplot.setRangeGridlinesVisible(true); 
		  //图片背景色 
	    categoryplot.setBackgroundPaint(Color.white); 
	    categoryplot.setOutlineVisible(false); 
		  //背景色边框颜色 
	    categoryplot.setOutlinePaint(Color.magenta); 
	    CategoryAxis domainAxis = categoryplot.getDomainAxis();
	    domainAxis.setLabelLocation(AxisLabelLocation.HIGH_END);
	    domainAxis.setLowerMargin(0.01d);
	    domainAxis.setUpperMargin(0.01d);
	    domainAxis.setCategoryLabelPositionOffset(1);
	    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
	    NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
	    numberaxis.setLabelLocation(AxisLabelLocation.MIDDLE);
	    numberaxis.setUpperMargin(0.1d);
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
		  barRenderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		  barRenderer.setBaseItemLabelsVisible(true);
		  barRenderer.setMaximumBarWidth(0.04);
		  barRenderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER));//数字上下位置
		  barRenderer.setItemLabelAnchorOffset(15);//设置数字显示位置，值越大，越向左偏
		  
		  barRenderer.setSeriesPaint(0,new Color(65,155,245)); //设置柱的颜色 
//		  barRenderer.setSeriesPaint(1,Color.yellow);
//		  barRenderer.setSeriesPaint(2,Color.green);
//		  barRenderer.setSeriesPaint(3,Color.GRAY);
//		  barRenderer.setDrawBarOutline(true);
		//设置网格竖线颜色 
		  categoryPlot.setDomainGridlinePaint(Color.lightGray); 
		  categoryPlot.setDomainGridlinesVisible(true); 
		  categoryPlot.setDomainGridlinePosition(CategoryAnchor.MIDDLE);
		  //设置网格横线颜色 
		  categoryPlot.setRangeGridlinePaint(Color.blue); 
		  categoryPlot.setRangeGridlinesVisible(true); 
		  //图片背景色 
		  categoryPlot.setBackgroundPaint(Color.white); 
		  categoryPlot.setOutlineVisible(false); 
		  //图边框颜色 
//		  categoryPlot.setOutlinePaint(Color.magenta); 
		  //设置柱的透明度 
//		  categoryPlot.setForegroundAlpha(0.7f); 
		  categoryPlot.setRenderer(barRenderer);
		  CategoryAxis domainAxis = categoryPlot.getDomainAxis();
		  domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
		  NumberAxis numberaxis = (NumberAxis) categoryPlot.getRangeAxis();
		  numberaxis.setUpperMargin(0.1d);
		return chart;
	}
	
	/**
	 * 生成饼图
	 * @param dataSet
	 * @return
	 */
	public static JFreeChart generatePieChart(DefaultPieDataset dataSet) {
		JFreeChart chart = ChartFactory.createPieChart(EMTPY_STR, dataSet, true, true, false);
		PiePlot plot = (PiePlot) chart.getPlot();
	 // 设置无数据时的信息
	    plot.setNoDataMessage("无对应的数据，请重新查询。");
	    // 设置无数据时的信息显示颜色
	    plot.setNoDataMessagePaint(Color.red);
	    plot.setBackgroundPaint(new Color(210,222,239));
	    plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
        "{0}={1}", NumberFormat.getNumberInstance(),
        new DecimalFormat("0.00%")));
		return chart;
	}
}
