package com.yihuacomputer.fish.report.engine.pdf;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;

/**
 * 段落管理器
 * @author xuxiang
 * @since 2.1.1.1
 */
public class ParagraphMgr {

	private Paragraph paragraph = new Paragraph();
	
	private ParagraphMgr(){
	}
	
	public static ParagraphMgr getInstance(){
		return new ParagraphMgr();
	}
	
	/**
	 * 
	 * @param text 文本 
	 * @param font 字体
	 * @return
	 */
	public ParagraphMgr addChunk(String text,Font font){
		Chunk chunk = new Chunk(text);
		chunk.setFont(font);
		paragraph.add(chunk);
		return this;
	}
	
	/**
	 * 使用默认字体
	 * @param text 文字，
	 * @return
	 */
	public ParagraphMgr addChunk(String text){
		Chunk chunk = new Chunk(text);
		paragraph.add(chunk);
		return this;
	}
	
	public Paragraph getParagraph(){
		paragraph.setFirstLineIndent(20);
		paragraph.setSpacingBefore(3f);
		paragraph.setSpacingAfter(3f);
		paragraph.setLeading(22f);
		return this.paragraph;
	}
}
