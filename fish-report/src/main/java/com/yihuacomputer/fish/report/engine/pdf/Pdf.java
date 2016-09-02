package com.yihuacomputer.fish.report.engine.pdf;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.jfree.chart.JFreeChart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * PDF的文档
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
public class Pdf {
	
	private Logger logger = LoggerFactory.getLogger(Pdf.class);
	
	public Document document;
	private PdfWriter writer;
	
	/**
	 * 创建一个PDF文档
	 * @param outFile
	 * @return
	 */
	public void createPdf(File outFile) throws Exception{
		document = new Document();
		writer = PdfWriter.getInstance(document, new FileOutputStream(outFile));
		writer.setStrictImageSequence(true); 
		openPdf();
		logger.info("open pdf success.");
	}
	
	public void newPage(){
		 document.newPage();
	}
	
	public void addChunk(List<String> chunks) throws DocumentException{
		Paragraph paragraph = new Paragraph();
		for(String chunk : chunks){
			paragraph.add(chunk);
		}
	}
	
	public void addParagraph(ParagraphMgr mgr) throws DocumentException{
		 document.add(mgr.getParagraph());
	}
	
	/**
	 * 创建文档头
	 * @param title
	 */
	public Paragraph addTitle(String title) throws DocumentException{
		Paragraph paragraph = new Paragraph(title,FontMgr.getFont20());
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		return paragraph;
	}
	
	public Paragraph addSubTitle(String title) throws DocumentException{
		Paragraph paragraph = new Paragraph(title,FontMgr.getFont18());
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		return paragraph;
	}
	
	public Paragraph addTriTitle(String title) throws DocumentException{
		Paragraph paragraph = new Paragraph(title,FontMgr.getFont16());
		paragraph.setAlignment(Element.ALIGN_CENTER);
		document.add(paragraph);
		return paragraph;
	}
	
	public void addEmptyLine(int line) throws DocumentException{
		for(int i =0 ;i < line ;i ++){
			document.add(Chunk.NEWLINE);
		}
	}
	
	public void addChapter(String content) throws DocumentException{
		Paragraph paragraph = new Paragraph(content,FontMgr.getFont16());
		document.add(paragraph);
	}
	
	public void addContent(String content) throws DocumentException{
		Paragraph paragraph = new Paragraph(content,FontMgr.getFont14());
		paragraph.setFirstLineIndent(20);//首行缩进
		paragraph.setSpacingBefore(5f);
		paragraph.setSpacingAfter(5f);
		document.add(paragraph);
	}
	
	public void addTable() throws DocumentException{
		PdfPTable table = new PdfPTable(3);
		table.setWidthPercentage(90);  
		table.setTotalWidth(PageSize.A4.getWidth());
//		table.setTotalWidth(new float[]{120f,250f,150f});
		PdfPCell cell = new PdfPCell(new Phrase("编号",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell);  
		cell = new PdfPCell(new Phrase("设备型号",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell); 
		cell = new PdfPCell(new Phrase("数量",FontMgr.getFont14()));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		table.addCell(cell); 
		table.addCell("1");  
		table.addCell("CDS6040W");  
		table.addCell("100"); 
		table.addCell("2");  
		table.addCell("CDS6040T");  
		table.addCell("50"); 
		document.add(table);  
	}
	
		
	/**
	 * 增加图
	 */
	public void addChart(JFreeChart chart,int width, int height) throws DocumentException{
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
	}
	
	/**
	 * 打开PDF
	 * @param doc
	 * @return
	 */
	public void openPdf(){
		document.open();
	}
	
	/**
	 * 关闭PDF
	 * @param doc
	 */
	public void close(){
		document.close();
	}

	public Document getDocument() {
		return document;
	}

	public PdfWriter getWriter() {
		return writer;
	}
	
}
