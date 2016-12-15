package com.yihuacomputer.fish.api.system.config;

import com.yihuacomputer.common.FishCfg;

public class MonitorCfg {
	/**
	 * 获取设备离线时间(单位:分钟) 默认时间:30分钟
	 *
	 * @return
	 */
	public static int getDeviceOffline() {
		String offlineTime = FishCfg.getParamValue("device_offline");
		if (offlineTime != null) {
			return Integer.parseInt(offlineTime);
		} else {
			return 30;
		}
	}

	/**
	 * 检查设备离线时间间隔(单位:毫秒) 默认时间:5分钟
	 *
	 * @return
	 */
	public static int getStatusCheckTime() {
		String checkTime = FishCfg.getParamValue("offline_check");
		if (checkTime != null) {
			return Integer.parseInt(checkTime) * 60000;
		} else {
			return 300000;
		}
	}

	/**
	 * 获取Remote服务监听端口 默认端口8085
	 *
	 * @return
	 */
	public static String getRemotePort() {
		String remotePort = FishCfg.getParamValue("atm_port");
		if (remotePort != null) {
			return remotePort;
		} else {
			return "8080";
		}
	}

	/**
	 * 根据IP地址得到URL头
	 *
	 * @param ip
	 * @return http://IP:PORT
	 */
	public static String getHttpUrl(String ip) {
		return "http://" + ip + ":" + MonitorCfg.getRemotePort();
	}

	/**
	 * 根据IP地址,port端口得到URL头
	 *
	 * @param ip
	 * @param port
	 * @return http://IP:PORT
	 */
	public static String getHttpUrl(String ip, int port) {
		return "http://" + ip + ":" + port + "/";
	}

	/**
	 * 获取设备开机对时开、关
	 *
	 * @return
	 */
	public static boolean getCheckTime() {
		if (FishCfg.getParamValue("check_time") != null && "ON".equalsIgnoreCase(FishCfg.getParamValue("check_time"))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取黑名单卡模版导入文件存放路径
	 *
	 * @return
	 */
	public static String getFishModelDoc() {
	    return FishCfg.getFishHome() + FishCfg.FILESEP + "model";
	}

	/**
	 * 获取短信文件存放路径
	 *
	 * @return
	 */
	public static String getFishNotifyDoc() {
		return FishCfg.getFishHome() + FishCfg.FILESEP + "notifySms";
	}


}
