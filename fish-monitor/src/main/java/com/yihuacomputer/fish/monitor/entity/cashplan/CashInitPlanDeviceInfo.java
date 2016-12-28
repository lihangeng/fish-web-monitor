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

/**
 * @author YiHua
 *
 */
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
	
	@Column(name = "ADDRESS")
	private String address;
	
    @ManyToOne(targetEntity = CashInitPlanInfo.class)
    @JoinColumn(name = "CASH_INIT_PLAN_ID", insertable = true, updatable = false)
	private ICashInitPlanInfo cashInitPlanInfo;
    
    @Override
	public long getId() {
		return id;
	}
    
    @Override
	public void setId(long id) {
		this.id = id;
	}
    
    @Override
	public String getTerminalId() {
		return terminalId;
	}
    
    @Override
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
    
    @Override
	public String getOrgName() {
		return orgName;
	}
    
    @Override
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
    @Override
	public long getLastAmt() {
		return lastAmt;
	}
    
    @Override
	public void setLastAmt(long lastAmt) {
		this.lastAmt = lastAmt;
	}
    
    @Override
	public String getLastDate() {
		return lastDate;
	}
    
    @Override
	public void setLastDate(String lastDate) {
		this.lastDate = lastDate;
	}
    
    @Override
	public double getAdviceAmt() {
		return adviceAmt;
	}
    
    @Override
	public void setAdviceAmt(double adviceAmt) {
		this.adviceAmt = adviceAmt;
	}
    
    @Override
	public double getActualAmt() {
		return actualAmt;
	}
    
    @Override
	public void setActualAmt(double actualAmt) {
		this.actualAmt = actualAmt;
	}
    
    @Override
	public BoxInitRuleType getFlag() {
		return flag;
	}
    
    @Override
	public void setFlag(BoxInitRuleType flag) {
		this.flag = flag;
	}
    
    @Override
	public String getAddress() {
		return address;
	}
    
    @Override
	public void setAddress(String address) {
		this.address = address;
	}
    
    @Override
	public ICashInitPlanInfo getCashInitPlanInfo() {
		return cashInitPlanInfo;
	}
    
    @Override
	public void setCashInitPlanInfo(ICashInitPlanInfo cashInitPlanInfo) {
		this.cashInitPlanInfo = cashInitPlanInfo;
	}
    
    @Override
	public String getDevType() {
		return devType;
	}
    
    @Override
	public void setDevType(String devType) {
		this.devType = devType;
	}
    
    @Override
	public AwayFlag getAwayFlag() {
		return awayFlag;
	}
    
    @Override
	public void setAwayFlag(AwayFlag awayFlag) {
		this.awayFlag = awayFlag;
	}
	
}
