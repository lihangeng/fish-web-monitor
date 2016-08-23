package com.yihuacomputer.fish.monitor.entity.cashplan;

import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;

public class CashInitPlanDeviceInfo implements ICashInitPlanDeviceInfo {
//	设备编号，网点名称，上次加钞金额，上次加钞时间，余额，建议加钞金额，实际加钞金额(可编辑)
	private long id;
	private String terminalId;
	private String orgName;
	private long lastAmt;
	private String lastDate;
	private long adviceAmt;
	private long actualAmt;
	private int flag;//加钞标识(1:钞箱预警.2:超过加钞预警天数.4.手工强制清机加钞)
	private String address;
	private ICashInitPlanInfo cashInitPlanInfo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public long getLastAmt() {
		return lastAmt;
	}
	public void setLastAmt(long lastAmt) {
		this.lastAmt = lastAmt;
	}
	public String getLastDate() {
		return lastDate;
	}
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
	public long getAdviceAmt() {
		return adviceAmt;
	}
	public void setAdviceAmt(long adviceAmt) {
		this.adviceAmt = adviceAmt;
	}
	public long getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(long actualAmt) {
		this.actualAmt = actualAmt;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public ICashInitPlanInfo getCashInitPlanInfo() {
		return cashInitPlanInfo;
	}
	public void setCashInitPlanInfo(ICashInitPlanInfo cashInitPlanInfo) {
		this.cashInitPlanInfo = cashInitPlanInfo;
	}
	
}
