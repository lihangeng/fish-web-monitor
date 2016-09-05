package com.yihuacomputer.fish.report.engine.pdf;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.util.DateUtils;

/**
 * PDF配置信息
 * @author xuxiang
 * @since 2.1.1.1
 */
public class PdfConfig{
	
	/**
	 * 获得PDF的存放路径
	 * @return
	 */
	public static String getPdfReportDir(){
		String dir = FishCfg.getFishHome() +File.separator + "report";
		File report =new File(dir);
		if(!report.exists()){
			report.mkdirs();
		}
		return dir;
	}
	
	public static String getWeekReportDir(){
		String dir = PdfConfig.getPdfReportDir() + File.separator + "week"; 
		File report =new File(dir);
		if(!report.exists()){
			report.mkdirs();
		}
		return dir;
	}
	
	public static String getMonthReportDir(){
		String dir = PdfConfig.getPdfReportDir() + File.separator + "month"; 
		File report =new File(dir);
		if(!report.exists()){
			report.mkdirs();
		}
		return dir;
	}

	/**
	 * 获得周报告的名字
	 * @param weekOfYear yyyyww
	 * @return
	 */
	public static String getWeekPdfFileName(int weekOfYear){
		Date week = DateUtils.get(String.valueOf(weekOfYear), DateUtils.STANDARD_WEEK);
		String year = DateUtils.get(week, "yyyy");
		String weekStr = DateUtils.get(week, "ww");
		Calendar cal = Calendar.getInstance();
		cal.setTime(week);
		DateUtils.getFirstDayOfWeek(cal);
		Date firstDay = DateUtils.getFirstDayOfWeek(cal);
		String start= DateUtils.get(firstDay, "MM.dd");
		Date endDay = DateUtils.getLastDayOfWeek(cal);
		String end= DateUtils.get(endDay, "MM.dd");
		return String.format("自助设备运行分析报告_%s年%s周(%s-%s).pdf",year,weekStr,start,end);
	}
	
	/**
	 * 获得周报告的名字
	 * @param month yyyyMM
	 * @return
	 */
	public static String getMonthPdfFileName(int month){
		String strMonth = String.valueOf(month);
		return String.format("自助设备运行分析报告_%s年%s月.pdf",strMonth.substring(0, 4),strMonth.substring(4));
	}
	
	/**
	 * 获取的周报文件
	 * @param weekOfYear
	 * @return
	 */
	public static File getWeekReportFile(int weekOfYear){
		String fileName = getWeekPdfFileName(weekOfYear);
		return new File(PdfConfig.getWeekReportDir() + File.separator + fileName);
	}
	
	/**
	 * 获得月报文件
	 * @param month
	 * @return
	 */
	public static File getMonthReportFile(int month){
		String fileName = getMonthPdfFileName(month);
		return new File(PdfConfig.getMonthReportDir() + File.separator + fileName);
	}

}
