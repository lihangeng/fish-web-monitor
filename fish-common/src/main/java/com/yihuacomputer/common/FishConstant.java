package com.yihuacomputer.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

public class FishConstant {

	/**
	 * 数据
	 */
	public static String DATA = "data";

	/**
	 * 成功
	 */
	public static String SUCCESS = "success";

	/**
	 * 错误
	 */
	public static String ERROR_MSG = "errorMsg";

	/**
	 * 总数
	 */
	public static String TOTAL = "total";

	/**
	 * 模拟全局applicationContext对象
	 */
	public static final Map<String,Map<String,HttpSession>> APPLICATION_MAP = new  HashMap<String,Map<String,HttpSession>>();;
}
