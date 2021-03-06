package com.yihuacomputer.fish.api.version;

import com.yihuacomputer.common.FishCfg;

/**
 * @author YiHua
 *
 */
public class VersionCfg {

	/**
	 * 获得版本文件的存放路径
	 */
	public static String getVersionDir() {
		return FishCfg.getFishHome() + FishCfg.FILESEP + "version";
	}

	/**
	 * 获得广告的存放路径
	 *
	 * @return
	 */
	public static String getAdvertDir() {
		return FishCfg.getFishHome() + FishCfg.FILESEP + "advert";
	}
	
	/**
	 * 获得广告的存放路径
	 *
	 * @return
	 */
	public static String getBsAdvertDir() {
		return FishCfg.getFishHome() + FishCfg.FILESEP + "bsAdvert";
	}

	/**
	 * 获得修改的ATM参数文件存放路径
	 *
	 * @return
	 */
	public static String getAtmParamDir() {
		return FishCfg.getFishHome() + FishCfg.FILESEP + "temp"+FishCfg.FILESEP+"atmParam";
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(FishCfg.getFishHome()+FishCfg.FILESEP+"temp");
	}
}
