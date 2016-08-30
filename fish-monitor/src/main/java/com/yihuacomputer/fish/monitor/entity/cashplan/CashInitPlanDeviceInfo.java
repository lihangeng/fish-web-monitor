package com.yihuacomputer.fish.monitor.entity.cashplan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;

@Entity
@Table(name = "DEV_CASH_INIT_PLAN_DEVICE")
public class CashInitPlanDeviceInfo implements ICashInitPlanDeviceInfo {
//	设备编号，网点名称，上次加钞金额，上次加钞时间，余额，建议加钞金额，实际加钞金额(可编辑)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_CASH_INIT_PLAN_DEVICE")
	@SequenceGenerator(name = "SEQ_DEV_CASH_INIT_PLAN_DEVICE", sequenceName = "SEQ_DEV_CASH_INIT_PLAN_DEVICE")
	@Column(name = "ID")
	private long id;
	@Column(name = "TERMINAL_ID")
	private String terminalId;
	@Column(name = "DEV_TYPE")
	private String devType;
	@Enumerated(EnumType.STRING)
	@Column(name = "AWAY_FLAG", length = 15)
	private AwayFlag awayFlag;
	@Column(name = "ORG_NAME")
	private String orgName;
	@Column(name = "LAST_AMT")
	private long lastAmt;
	@Column(name = "LAST_DATE")
	private String lastDate;
	@Column(name = "ADVICE_AMT")
	private double adviceAmt;
	@Column(name = "ACTUAL_AMT")
	private double actualAmt;

    @Enumerated(EnumType.ORDINAL)
	@Column(name = "INIT_FLAG")
	private BoxInitRuleType flag;//加钞类型标识(1:钞箱预警.2:超过加钞预警天数.4.手工强制清机加钞)
//	@org.hibernate.annotations.Type(type = "com.yihuacomputer.domain.util.BooleanUserType")
//	@Column(name = "CASH_INIT", columnDefinition = "CHAR", length = 1)
//	private boolean init;
	
	@Column(name = "ADDRESS")
	private String address;
	
    @ManyToOne(targetEntity = CashInitPlanInfo.class)
    @JoinColumn(name = "CASH_INIT_PLAN_ID", insertable = true, updatable = false)
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
	public double getAdviceAmt() {
		return adviceAmt;
	}
	public void setAdviceAmt(double adviceAmt) {
		this.adviceAmt = adviceAmt;
	}
	public double getActualAmt() {
		return actualAmt;
	}
	public void setActualAmt(double actualAmt) {
		this.actualAmt = actualAmt;
	}
	public BoxInitRuleType getFlag() {
		return flag;
	}
	public void setFlag(BoxInitRuleType flag) {
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
	public String getDevType() {
		return devType;
	}
	public void setDevType(String devType) {
		this.devType = devType;
	}
	public AwayFlag getAwayFlag() {
		return awayFlag;
	}
	public void setAwayFlag(AwayFlag awayFlag) {
		this.awayFlag = awayFlag;
	}
	
}
