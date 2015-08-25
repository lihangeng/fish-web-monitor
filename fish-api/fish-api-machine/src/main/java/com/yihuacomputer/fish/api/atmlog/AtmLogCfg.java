package com.yihuacomputer.fish.api.atmlog;

import com.yihuacomputer.common.FishCfg;

public class AtmLogCfg {
	/**
	 * ATM APP日志存放规则 默认配置{terminalId}_{date}.txt
	 *
	 * @return
	 */
	public static String getAtmLogFileCfg() {

		String logFileCfg = FishCfg.getParamValue("atm_log_file_cfg");
		if (logFileCfg != null) {
			return logFileCfg;
		} else {
			return "{terminalId}_{date}.txt";
		}
	}

	/**
	 * ATM APP 日志存放路径 默认路径c:\\colls_II\\log
	 *
	 * @return
	 */
	public static String getAtmLogDoc() {
		String logDoc = FishCfg.getParamValue("atm_log_doc");
		if (logDoc != null) {
			return logDoc;
		} else {
			return "c:\\yihua\\gump\\log";
		}
	}

	/**
	 * 获取ATM APP 服务器端日志存放根路径
	 *
	 * @return
	 */
	public static String getAtmAppLogDir() {
		return FishCfg.getFishHome() + FishCfg.fileSep + "atmc_log";
	}

}
