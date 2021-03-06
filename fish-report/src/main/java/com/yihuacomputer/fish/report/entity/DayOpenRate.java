package com.yihuacomputer.fish.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yihuacomputer.fish.api.report.openRate.IDayOpenRate;

/**
 * 开机率数据表和对象映射关系
 * @author xuxiang
 *
 */
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
    
    @Transient
    private String devTypeName;
    
    @Transient 
    private String devVendorName;
    
    @Transient 
    private String awayFlag;
    
    @Transient 
    private double openRate;
    
    @Transient 
    private double avgOpenRate;

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
    public String getStatDate() {
        return statDate;
    }

    @Override
    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    @Override
    public int getOpenTimes() {
        return openTimes;
    }

    @Override
    public void setOpenTimes(int openTimes) {
        this.openTimes = openTimes;
    }

    @Override
    public int getHealthyTimeReal() {
        return healthyTimeReal;
    }

    @Override
    public void setHealthyTimeReal(int healthyTimeReal) {
        this.healthyTimeReal = healthyTimeReal;
    }

    @Override
    public int getUnknownTimeReal() {
        return unknownTimeReal;
    }

    @Override
    public void setUnknownTimeReal(int unknownTimeReal) {
        this.unknownTimeReal = unknownTimeReal;
    }

    @Override
    public int getMaintainTimeReal() {
        return maintainTimeReal;
    }

    @Override
    public void setMaintainTimeReal(int maintainTimeReal) {
        this.maintainTimeReal = maintainTimeReal;
    }

    @Override
    public int getFaultTimeReal() {
        return faultTimeReal;
    }

    @Override
    public void setFaultTimeReal(int faultTimeReal) {
        this.faultTimeReal = faultTimeReal;
    }

    @Override
    public int getAtmpTimeReal() {
        return atmpTimeReal;
    }

    @Override
    public void setAtmpTimeReal(int atmpTimeReal) {
        this.atmpTimeReal = atmpTimeReal;
    }

    @Override
    public int getStopTimeReal() {
        return stopTimeReal;
    }

    @Override
    public void setStopTimeReal(int stopTimeReal) {
        this.stopTimeReal = stopTimeReal;
    }

    @Override
    public String getProgramOpenTime() {
        return programOpenTime;
    }

    @Override
    public void setProgramOpenTime(String programOpenTime) {
        this.programOpenTime = programOpenTime;
    }

    @Override
    public String getProgramCloseTime() {
        return programCloseTime;
    }

    @Override
    public void setProgramCloseTime(String programCloseTime) {
        this.programCloseTime = programCloseTime;
    }

    @Override
    public int getProgramTimes() {
        return programTimes;
    }

    @Override
    public void setProgramTimes(int programTimes) {
        this.programTimes = programTimes;
    }

    @Override
    public int getProgramTimeReal() {
        return programTimeReal;
    }

    @Override
    public void setProgramTimeReal(int programTimeReal) {
        this.programTimeReal = programTimeReal;
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
	public String getDevCatalogName() {
		return devCatalogName;
	}

    @Override
	public void setDevCatalogName(String devCatalogName) {
		this.devCatalogName = devCatalogName;
	}

	public String getDevTypeName() {
		return devTypeName;
	}

	public void setDevTypeName(String devTypeName) {
		this.devTypeName = devTypeName;
	}

	public String getDevVendorName() {
		return devVendorName;
	}

	public void setDevVendorName(String devVendorName) {
		this.devVendorName = devVendorName;
	}

	@Override
	public String getAwayFlag() {
		return awayFlag;
	}

	@Override
	public void setAwayFlag(String awayFlag) {
		this.awayFlag = awayFlag;
	}

	@Override
	public double getAvgOpenRate() {
		return avgOpenRate;
	}

	@Override
	public void setAvgOpenRate(double avgOpenRate) {
		this.avgOpenRate = avgOpenRate;
	}
	

}
