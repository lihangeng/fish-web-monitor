package com.yihuacomputer.fish.report.base.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.report.base.IDayOpenRate;

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
    private long openTimes;

    /** 设备正常时间段内实际开机时长 */
    @Column(name = "HEALTHY_TIMEREAL")
    private long healthyTimeReal;

    /** 设备未知时长 */
    @Column(name = "UNKNOWN_TIMEREAL")
    private long unknownTimeReal;

    /** 设备维护时长 */
    @Column(name = "MAINTAIN_TIMEREAL")
    private long maintainTimeReal;

    /** 设备故障时长 */
    @Column(name = "FAULT_TIMEREAL")
    private long faultTimeReal;

    /** 设备atmp故障时长 */
    @Column(name = "ATMP_TIMEREAL")
    private long atmpTimeReal;

    /** 设备暂停时长 */
    @Column(name = "STOP_TIMEREAL")
    private long stopTimeReal;

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
    private long programTimes;

    /**
     * 方案有效开机时长
     */
    @Column(name = "PROGRAM_TIMEREAL")
    private long programTimeReal;

    /** 无对外交易画面时长 */
    @Column(name = "NO_SCREENREAL")
    private long noScreenReal;

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

    public long getOpenTimes() {
        return openTimes;
    }

    public void setOpenTimes(long openTimes) {
        this.openTimes = openTimes;
    }

    public long getHealthyTimeReal() {
        return healthyTimeReal;
    }

    public void setHealthyTimeReal(long healthyTimeReal) {
        this.healthyTimeReal = healthyTimeReal;
    }

    public long getUnknownTimeReal() {
        return unknownTimeReal;
    }

    public void setUnknownTimeReal(long unknownTimeReal) {
        this.unknownTimeReal = unknownTimeReal;
    }

    public long getMaintainTimeReal() {
        return maintainTimeReal;
    }

    public void setMaintainTimeReal(long maintainTimeReal) {
        this.maintainTimeReal = maintainTimeReal;
    }

    public long getFaultTimeReal() {
        return faultTimeReal;
    }

    public void setFaultTimeReal(long faultTimeReal) {
        this.faultTimeReal = faultTimeReal;
    }

    public long getAtmpTimeReal() {
        return atmpTimeReal;
    }

    public void setAtmpTimeReal(long atmpTimeReal) {
        this.atmpTimeReal = atmpTimeReal;
    }

    public long getStopTimeReal() {
        return stopTimeReal;
    }

    public void setStopTimeReal(long stopTimeReal) {
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

    public long getProgramTimes() {
        return programTimes;
    }

    public void setProgramTimes(long programTimes) {
        this.programTimes = programTimes;
    }

    public long getProgramTimeReal() {
        return programTimeReal;
    }

    public void setProgramTimeReal(long programTimeReal) {
        this.programTimeReal = programTimeReal;
    }

	public long getNoScreenReal() {
		return noScreenReal;
	}

	public void setNoScreenReal(long noScreenReal) {
		this.noScreenReal = noScreenReal;
	}

}
