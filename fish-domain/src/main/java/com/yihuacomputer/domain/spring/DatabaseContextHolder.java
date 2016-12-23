package com.yihuacomputer.domain.spring;

/**
 * @author YiHua
 *
 */
public class DatabaseContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDataSource(String customerType) {
		contextHolder.set(customerType);
	}

	public static String getDataSource() {
		return contextHolder.get();
	}

	/**
	 * 清除数据
	 */
	public static void clearDataSource() {
		contextHolder.remove();
	}
}