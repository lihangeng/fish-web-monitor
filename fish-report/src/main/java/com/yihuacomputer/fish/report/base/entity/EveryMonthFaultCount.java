package com.yihuacomputer.fish.report.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCount;



@Entity
@Table(name = "CASE_FAULT_MONTH")
public class EveryMonthFaultCount implements IEveryMonthFaultCount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_CASE_FAULT_MONTH")
    @SequenceGenerator(name = "SEQ_CASE_FAULT_MONTH", sequenceName = "SEQ_CASE_FAULT_MONTH")
    @Column(name = "ID")
    private long id;

    /**
     * 设备号
     */
    @Column(name = "TERMINAL_ID", length = 20)
    private String terminalId;

    /**
     * 设备模块
     */
    @Column(name = "DEV_MOD", length = 5)
    private String devMod;

    /** 
     * 故障类型
     */
    @Column(name = "CLASSIFY_ID",length = 10)
    private String classifyId;

    /** 
     * 故障日期
     */
    @Column(name = "FAULT_DATE",length = 20)
    private long faultDate;

    /** 
     * 故障时次数
     */
    @Column(name = "FAULT_COUNT",length = 10)
    private long faultCount;

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

	public String getDevMod() {
		return devMod;
	}

	public void setDevMod(String devMod) {
		this.devMod = devMod;
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public long getFaultDate() {
		return faultDate;
	}

	public void setFaultDate(long faultDate) {
		this.faultDate = faultDate;
	}

	public long getFaultCount() {
		return faultCount;
	}

	public void setFaultCount(long faultCount) {
		this.faultCount = faultCount;
	}


}
