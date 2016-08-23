package com.yihuacomputer.fish.monitor.entity.cashplan;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.person.IOrganization;

public class CashInitPlanInfo implements ICashInitPlanInfo {
	private long id;
	private int date;
	private String cashInitCode;
	private IOrganization org;
	private List<ICashInitPlanDeviceInfo> cashInitPlanDeviceList = new ArrayList<ICashInitPlanDeviceInfo>();
	private long amt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getCashInitCode() {
		return cashInitCode;
	}
	public void setCashInitCode(String cashInitCode) {
		this.cashInitCode = cashInitCode;
	}
	public IOrganization getOrg() {
		return org;
	}
	public void setOrg(IOrganization org) {
		this.org = org;
	}
	public long getAmt() {
		return amt;
	}
	public void setAmt(long amt) {
		this.amt = amt;
	}
	public List<ICashInitPlanDeviceInfo> getCashInitPlanDeviceList() {
		return cashInitPlanDeviceList;
	}
	public void setCashInitPlanDeviceList(List<ICashInitPlanDeviceInfo> cashInitPlanDeviceList) {
		this.cashInitPlanDeviceList = cashInitPlanDeviceList;
	}
	
	public void add(ICashInitPlanDeviceInfo cashPlanDeviceInfo){
		this.cashInitPlanDeviceList.add(cashPlanDeviceInfo);
	}
}
