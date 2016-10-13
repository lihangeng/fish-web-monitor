package com.yihuacomputer.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 加钞清机配置项
 * @author GQ
 *
 */
public class CashInitPlanCfg {

	private static Logger logger = LoggerFactory.getLogger(CashInitPlanCfg.class);
	/**
	 * 获取规定的清机加钞周期
	 * @return
	 */
	public static int getCashInitDays(){
		int days = 7;
		try{
			days = Integer.parseInt(FishCfg.getEntities().get("cashinit_days"));
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return days;
	}
	
	
	/**
	 * 加钞计划执行单位
	 * @return
	 */
	public static int getCashInitPlanUtils(){
		int orgLevel = 0;
		// 获取加钞计划机构单位(总分支行)
		try {
			orgLevel = Integer.parseInt(FishCfg.getEntities().get("cashinit_orglevel"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return orgLevel;
	}
	/**
	 * 存款日均交易量
	 * @return
	 */
	public static long getDaliyTradingVolumeCashIn(){
		long tradingVolumeCashIn = 50000l;
		try {
			tradingVolumeCashIn = Long.parseLong(FishCfg.getEntities().get("trading_volume_in"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return tradingVolumeCashIn;
	}
	/**
	 * 取款日均交易量
	 * @return
	 */
	public static long getDaliyTradingVolumeBill(){
		long tradingVolumeBill = 50000l;
		try {
			tradingVolumeBill = Long.parseLong(FishCfg.getEntities().get("trading_volume_out"));
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return tradingVolumeBill;
	}
	
}
