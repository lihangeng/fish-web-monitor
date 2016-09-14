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
		String[]days = getDayByWeek(weekOfYear);
		Date week = DateUtils.get(String.valueOf(weekOfYear), DateUtils.STANDARD_WEEK);
		String year = DateUtils.get(week, "yyyy");
		String weekStr = DateUtils.get(week, "ww");
		return String.format("%s_%s年%s周(%s-%s).pdf",PdfConfig.getTitle(),year,weekStr,days[1],days[2]);
	}
	/**
	 * 获取周的第一天和最后一天
	 * 格式yyyyww
	 */
	public static String[] getDayByWeek(int date){
		String[] days=new String[3];
		Date week = DateUtils.get(String.valueOf(date), DateUtils.STANDARD_WEEK);
		String year = DateUtils.get(week, "yyyy");
		days[0] = year;
		String weekStr = DateUtils.get(week, "ww");
		Calendar cal = Calendar.getInstance();
		cal.clear();
		int weekOf=0;
		if(weekStr.substring(0, 1)=="0"){
			weekOf=Integer.parseInt(weekStr.substring(1));
		}else{
			weekOf = Integer.parseInt(weekStr);
		}
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.WEEK_OF_YEAR,weekOf);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		DateUtils.getFirstDayOfWeek(cal);
		Date firstDay = DateUtils.getFirstDayOfWeek(cal);
		days[1]= DateUtils.get(firstDay, "MM.dd");
		Date endDay = DateUtils.getLastDayOfWeek(cal);
		days[2] = DateUtils.get(endDay, "MM.dd");
		return days;
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getTitle(){
		String value = FishCfg.getParamValue("PDF_REPORT_TITLE");
		return value != null ? value : "自助设备运行分析报告";
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getLogo(){
		String value = FishCfg.getParamValue("PDF_REPORT_LOGO");
		return value != null ? value : "深圳怡化电脑股份有限公司";
	}
	
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSubTile(String fileName){
		String left = fileName.substring(fileName.indexOf('_') + 1);
		return left.substring(0, left.indexOf(".pdf"));
	}
	
	/**
	 * 获得周报告的名字
	 * @param month yyyyMM
	 * @return
	 */
	public static String getMonthPdfFileName(int month){
		String strMonth = String.valueOf(month);
		return String.format("%s_%s年%s月.pdf",PdfConfig.getTitle(),strMonth.substring(0, 4),strMonth.substring(4));
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
