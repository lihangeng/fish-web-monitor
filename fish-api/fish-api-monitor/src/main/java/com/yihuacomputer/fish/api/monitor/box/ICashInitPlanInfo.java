package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.fish.api.person.IOrganization;


/**
 * @author GQ
 * 加钞计划信息(每个钞计划有唯一的加钞单号),可以含有多个加钞设备
 */
public interface ICashInitPlanInfo {
	public long getId();
	public void setId(long id);
	/**
	 * 加钞日期
	 * @return
	 */
	public int getDate();
	/**
	 * 加钞日期
	 * @param date
	 */
	public void setDate(int date);
	/**
	 * 加钞单号
	 * @return
	 */
	public String getCashInitCode();
	/**
	 * 加钞单号
	 * @param cashInitCode
	 */
	public void setCashInitCode(String cashInitCode);
	/**
	 * 加钞机构
	 * @return
	 */
	public IOrganization getOrg();
	/**
	 * 加钞机构
	 * @param org
	 */
	public void setOrg(IOrganization org);
	/**
	 * 加钞金额
	 * @return
	 */
	public long getAmt();
	/**
	 * 加钞金额
	 * @param amt
	 */
	public void setAmt(long amt);
	/**
	 * 加钞计划设备列表
	 * @return
	 */
	public List<ICashInitPlanDeviceInfo> getCashInitPlanDeviceList();
	/**
	 * 加钞计划设备列表
	 * @param cashInitPlanDeviceList
	 */
	public void setCashInitPlanDeviceList(List<ICashInitPlanDeviceInfo> cashInitPlanDeviceList);
	/**
	 * 加钞计划设备列表
	 * @param cashPlanDeviceInfo
	 */
	public void add(ICashInitPlanDeviceInfo cashPlanDeviceInfo);
}
