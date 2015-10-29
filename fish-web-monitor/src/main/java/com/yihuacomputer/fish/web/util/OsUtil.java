package com.yihuacomputer.fish.web.util;


/**
 * 操作系统信息工具类
 * 
 * @author maozili
 * @date 2015-05-27
 * 
 * **/
public class OsUtil {
	/** 操作系统名称(小写) **/
	private static String osName;

	static {
		osName = System.getProperty("os.name").toLowerCase();
	}

	/** 是否为Windows操作系统 **/
	public static boolean isWindows() {
		if (osName.startsWith("win") || (osName.indexOf("win") >= 0)) {
			return true;
		}
		return false;
	}

	/** 是否为Linux操作系统 **/
	public static boolean isLinux() {
		if (osName.startsWith("lin") || (osName.indexOf("lin") >= 0)) {
			return true;
		}
		return false;
	}

	/** 是否为Unix操作系统 **/
	public static boolean isUnix() {
		if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0) {
			return true;
		}
		return false;
	}

	/** 获取操作系统版本号 **/
	public static String getOsVersion() {
		return System.getProperty("os.version");
	}
}
