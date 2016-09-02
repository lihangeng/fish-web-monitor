package com.yihuacomputer.fish.report.engine.pdf;

import java.io.IOException;

import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

/**
 * 中文字体管理
 * @author xuxiang
 *
 */
public class FontMgr {
	
	public static BaseFont chinaFont;
	
	public static final AsianFontMapper asianFontMapper = new AsianFontMapper(AsianFontMapper.ChineseSimplifiedFont,AsianFontMapper.ChineseSimplifiedEncoding_H);

	
	static {
		try {
			chinaFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont, AsianFontMapper.ChineseSimplifiedEncoding_H,BaseFont.NOT_EMBEDDED);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Font getFont20(){
		return new Font(chinaFont, 20, Font.NORMAL);
	}
	
	public static Font getFont18(){
		return new Font(chinaFont, 18, Font.NORMAL);
	}
	
	public static Font getFont16(){
		return new Font(chinaFont, 16, Font.NORMAL);
	}
	
	public static Font getFont14(){
		return new Font(chinaFont, 14, Font.NORMAL);
	}
	
	public static Font getFont12(){
		return new Font(chinaFont, 12, Font.NORMAL);
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public static Font getFont16(BaseColor color){
		return new Font(chinaFont, 16, Font.NORMAL,color);
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public static Font getFont14(BaseColor color){
		return new Font(chinaFont, 14, Font.NORMAL,color);
	}
	
	/**
	 * 
	 * @param color
	 * @return
	 */
	public static Font getFont12(BaseColor color){
		return new Font(chinaFont, 12, Font.NORMAL,color);
	}
	
	/**
	 * 
	 * @param size
	 * @param style
	 * @return
	 */
	public static Font getFont(float size,int style,BaseColor color){
		return new Font(chinaFont, size, style,color); 
	}
	
}