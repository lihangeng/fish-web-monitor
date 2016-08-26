package com.yihuacomputer.fish.api.monitor.box;

import com.yihuacomputer.fish.api.device.AwayFlag;


/**
 * @author GQ
 * 加钞计划中的设备加钞信息
 */
public interface ICashInitPlanDeviceInfo {
	public long getId();
	public void setId(long id);
	/**
	 * 设备终端号
	 * @return
	 */
	public String getTerminalId();
	/**
	 * 设备终端号
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);
	/**
	 * 设备所在机构名称
	 * @return
	 */
	public String getOrgName();
	/**
	 * 设备所在机构名称
	 * @param orgName
	 */
	public void setOrgName(String orgName);
	/**
	 * 上次加钞金额
	 * @return
	 */
	public long getLastAmt();
	/**
	 * 上次加钞金额
	 * @param lastAmt
	 */
	public void setLastAmt(long lastAmt);
	/**
	 * 上次加钞日期
	 * @return
	 */
	public String getLastDate();
	/**
	 * 上次加钞日期
	 * @param lastDate
	 */
	public void setLastDate(String lastDate);
	/** 
	 * 建议加钞金额
	 * @return
	 */
	public double getAdviceAmt();
	/**
	 * 建议加钞金额
	 * @param adviceAmt
	 */
	public void setAdviceAmt(double adviceAmt);
	/**
	 * 实际加钞
	 * @return
	 */
	public double getActualAmt();
	/**
	 * 实际加钞
	 * @param actualAmt
	 */
	public void setActualAmt(double actualAmt);
	/**
	 * 加钞原因标识
	 * @return
	 */
	public BoxInitRuleType getFlag();
	/**
	 * 加钞原因标识
	 * @param flag
	 */
	public void setFlag(BoxInitRuleType flag);
	/**
	 * 加钞设备地址
	 * @return
	 */
	public String getAddress();
	/**
	 * 加钞设备地址
	 * @param address
	 */
	public void setAddress(String address);
	/**
	 * 设备加钞信息隶属加钞计划
	 * @return
	 */
	public ICashInitPlanInfo getCashInitPlanInfo();
	/**
	 * 设备加钞信息隶属加钞计划
	 * @param cashInitPlanInfo
	 */
	public void setCashInitPlanInfo(ICashInitPlanInfo cashInitPlanInfo);
	
	/**
	 * 设备型号
	 * @return
	 */
	public String getDevType();
	/**
	 * 设备型号
	 * @param devType
	 */
	public void setDevType(String devType);
	/**
	 * 在离行标识
	 * @return
	 */
	public AwayFlag getAwayFlag();
	/**
	 * 在离行标识
	 * @param awayFlag
	 */
	public void setAwayFlag(AwayFlag awayFlag);
	
}
