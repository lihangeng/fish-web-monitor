package com.yihuacomputer.fish.report.service.etl;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.yihuacomputer.fish.report.engine.pdf.FontMgr;

public class PdfTest {

	public static void main(String[] args) throws FileNotFoundException, DocumentException {
		writeChartToPDF(generateLineChart(), 500, 400, "D://linechart.pdf");
//		writeChartToPDF(generateBarChart(), 500, 400, "D://barchart.pdf");
//		writeChartToPDF(generatePieChart(), 590, 200, "D://piechart2.pdf");
	}
	
	public static void writeChartToPDF(JFreeChart chart, int width, int height, String fileName) {
		PdfWriter writer = null;
		BaseFont bfChinese = null;
		try{
			bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
		}catch(Exception ex){
			
		}
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese, 18, com.itextpdf.text.Font.BOLD); 

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			document.open();
			document.add(new Paragraph("你好Hello World",FontChinese18));
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d =  new PdfGraphics2D(template, width, height,FontMgr.asianFontMapper);
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,height);
			chart.draw(graphics2d, rectangle2d);
			graphics2d.dispose();
			Image img = Image.getInstance(template);
			img.setAlignment(Image.MIDDLE);
			img.setBorder(Image.NO_BORDER);  
			img.setBorderWidth(10);  
			img.setBorderColor(BaseColor.WHITE); 
			document.add(img); 

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}
	
	public static JFreeChart generatePieChart(DefaultPieDataset dataSet) {

		JFreeChart chart = ChartFactory.createPieChart(
				"", dataSet, true, true, false);
		
		return chart;
	}
	
	public static JFreeChart generateBarChart(DefaultCategoryDataset dataSet) {

		JFreeChart chart = ChartFactory.createBarChart(
				"World Population growth", "Year", "Population in millions",
				dataSet, PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}
	
	public static JFreeChart generateLineChart() {
		JFreeChart chart = ChartFactory.createLineChart(
	            "",      // chart title
	            "",                      // x axis label
	            "",                      // y axis label
	            createDataset(),                  // data
	            PlotOrientation.VERTICAL,
	            true,                     // include legend
	            true,                     // tooltips
	            false                     // urls
	        );
		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.LIGHT_GRAY);
		plot.setRangeGridlinesVisible(true);
		return chart;
	}
	
private static CategoryDataset createDataset() {
        
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	dataset.addValue(212, "ATM", "2016033");
	dataset.addValue(504, "VTM", "2016033");
	dataset.addValue(1520, "ATM", "J2016034");
	dataset.addValue(1842, "VTM", "2016034");
	dataset.addValue(2991, "ATM", "2016035");
	dataset.addValue(3500, "VTM", "2016035");
     /*   series1.add(6.0, 7.0);
        series1.add(7.0, 7.0);
        series1.add(8.0, 8.0);*/

/*        final XYSeries series2 = new XYSeries("Second");
        series2.add(1.0, 5.0);
        series2.add(2.0, 7.0);
        series2.add(3.0, 6.0);
        series2.add(4.0, 8.0);
        series2.add(5.0, 4.0);
        series2.add(6.0, 4.0);
        series2.add(7.0, 2.0);
        series2.add(8.0, 1.0);

        final XYSeries series3 = new XYSeries("Third");
        series3.add(3.0, 4.0);
        series3.add(4.0, 3.0);
        series3.add(5.0, 2.0);
        series3.add(6.0, 3.0);
        series3.add(7.0, 6.0);
        series3.add(8.0, 3.0);
        series3.add(9.0, 4.0);
        series3.add(10.0, 3.0);*/

//        final XYSeriesCollection dataset = new XYSeriesCollection();
//        dataset.addSeries(series1);
    /*    dataset.addSeries(series2);
        dataset.addSeries(series3);*/
                
        return dataset;
        
    }

}
