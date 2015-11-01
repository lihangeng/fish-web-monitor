package com.yihuacomputer.fish.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.report.IDayOpenRate;

@Entity
@Table(name = "DEV_OPEN_RATE")
public class DayOpenRate implements IDayOpenRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_DEV_OPEN_RATE")
    @SequenceGenerator(name = "SEQ_DEV_OPEN_RATE", sequenceName = "SEQ_DEV_OPEN_RATE")
    @Column(name = "ID")
    private long id;

    @Column(name = "TERMINAL_ID", length = 20)
    private String terminalId;

    /**
     * 统计日期
     */
    @Column(name = "STAT_DATE", length = 20)
    private String statDate;

    /** 设备应开机时长 */
    @Column(name = "OPENTIMES")
    private int openTimes;

    /** 设备正常时间段内实际开机时长 */
    @Column(name = "HEALTHY_TIMEREAL")
    private int healthyTimeReal;

    /** 设备未知时长 */
    @Column(name = "UNKNOWN_TIMEREAL")
    private int unknownTimeReal;

    /** 设备维护时长 */
    @Column(name = "MAINTAIN_TIMEREAL")
    private int maintainTimeReal;

    /** 设备故障时长 */
    @Column(name = "FAULT_TIMEREAL")
    private int faultTimeReal;

    /** 设备atmp故障时长 */
    @Column(name = "ATMP_TIMEREAL")
    private int atmpTimeReal;

    /** 设备暂停时长 */
    @Column(name = "STOP_TIMEREAL")
    private int stopTimeReal;

    /**
     * 方案开机时间
     */
    @Column(name = "PROGRAM_OPENTIME")
    private String programOpenTime;

    /**
     * 方案关机时间
     */
    @Column(name = "PROGRAM_CLOSETIME")
    private String programCloseTime;

    /**
     * 方案应开机时长
     */
    @Column(name = "PROGRAM_TIMES")
    private int programTimes;

    /**
     * 方案有效开机时长
     */
    @Column(name = "PROGRAM_TIMEREAL")
    private int programTimeReal;

    @Transient
    private String orgName;

    @Transient
    private String devCatalogName;

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

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public int getOpenTimes() {
        return openTimes;
    }

    public void setOpenTimes(int openTimes) {
        this.openTimes = openTimes;
    }

    public int getHealthyTimeReal() {
        return healthyTimeReal;
    }

    public void setHealthyTimeReal(int healthyTimeReal) {
        this.healthyTimeReal = healthyTimeReal;
    }

    public int getUnknownTimeReal() {
        return unknownTimeReal;
    }

    public void setUnknownTimeReal(int unknownTimeReal) {
        this.unknownTimeReal = unknownTimeReal;
    }

    public int getMaintainTimeReal() {
        return maintainTimeReal;
    }

    public void setMaintainTimeReal(int maintainTimeReal) {
        this.maintainTimeReal = maintainTimeReal;
    }

    public int getFaultTimeReal() {
        return faultTimeReal;
    }

    public void setFaultTimeReal(int faultTimeReal) {
        this.faultTimeReal = faultTimeReal;
    }

    public int getAtmpTimeReal() {
        return atmpTimeReal;
    }

    public void setAtmpTimeReal(int atmpTimeReal) {
        this.atmpTimeReal = atmpTimeReal;
    }

    public int getStopTimeReal() {
        return stopTimeReal;
    }

    public void setStopTimeReal(int stopTimeReal) {
        this.stopTimeReal = stopTimeReal;
    }

    public String getProgramOpenTime() {
        return programOpenTime;
    }

    public void setProgramOpenTime(String programOpenTime) {
        this.programOpenTime = programOpenTime;
    }

    public String getProgramCloseTime() {
        return programCloseTime;
    }

    public void setProgramCloseTime(String programCloseTime) {
        this.programCloseTime = programCloseTime;
    }

    public int getProgramTimes() {
        return programTimes;
    }

    public void setProgramTimes(int programTimes) {
        this.programTimes = programTimes;
    }

    public int getProgramTimeReal() {
        return programTimeReal;
    }

    public void setProgramTimeReal(int programTimeReal) {
        this.programTimeReal = programTimeReal;
    }

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDevCatalogName() {
		return devCatalogName;
	}

	public void setDevCatalogName(String devCatalogName) {
		this.devCatalogName = devCatalogName;
	}




}
