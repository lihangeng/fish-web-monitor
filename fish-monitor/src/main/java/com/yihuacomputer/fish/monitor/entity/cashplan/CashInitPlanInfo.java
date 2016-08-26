package com.yihuacomputer.fish.monitor.entity.cashplan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.person.IOrganization;

@Entity
@Table(name = "DEV_CASH_INIT_PLAN")
public class CashInitPlanInfo implements ICashInitPlanInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_CASH_INIT_PLAN")
	@SequenceGenerator(name = "SEQ_DEV_CASH_INIT_PLAN", sequenceName = "SEQ_DEV_CASH_INIT_PLAN")
	@Column(name = "ID")
	private long id;
	
	@Column(name = "PLAN_DATE")
	private int date;

	@Column(name = "CASH_INIT_CODE")
	private String cashInitCode;

	@ManyToOne(fetch = FetchType.EAGER, targetEntity = com.yihuacomputer.fish.system.entity.Organization.class)
	@JoinColumn(name = "ORG_ID")
	private IOrganization org;
    
	@OneToMany(targetEntity = CashInitPlanDeviceInfo.class, fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "CASH_INIT_PLAN_ID")
	private List<ICashInitPlanDeviceInfo> cashInitPlanDeviceList = new ArrayList<ICashInitPlanDeviceInfo>();

	@Column(name = "CASH_INIT_PLAN_AMT")
    private double amt;
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
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
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
