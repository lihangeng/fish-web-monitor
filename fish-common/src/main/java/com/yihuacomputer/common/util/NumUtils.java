package com.yihuacomputer.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 数字格式化工具
 * 
 * @author xuxiang
 *
 */
public class NumUtils {

	public static final String SIX = "0.000000";

	public static final String TWO = "0.00";
	
	public static final String THREE = "0.000";

	/**
	 * 算百分比
	 * 
	 * @param value1
	 *            小值
	 * @param value2
	 *            大值
	 * @return
	 */
	public static double getPercent(long value1, long value2) {
		double v1 = Long.valueOf(value1).doubleValue();
		double v2 = Long.valueOf(value2).doubleValue();
		NumberFormat formatter = new DecimalFormat(NumUtils.SIX);
		String s = formatter.format(v1 / v2 * 100);
		return Double.parseDouble(s);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String format(double value) {
		NumberFormat formatter = new DecimalFormat(NumUtils.TWO);
		return formatter.format(value);
	}
}
