package com.yihuacomputer.fish.api.report.openRate;


/**
 * @author YiHua
 *
 */
public interface IDayOpenRate {

    /**
     * ID
     *
     * @return
     */
    public long getId();

    /**
     * @param id
     */
    public void setId(long id);

    /**
     * 设备号
     *
     * @return
     */
    public String getTerminalId();

    /**
     * @param terminalId
     */
    public void setTerminalId(String terminalId);

    /**
     * 统计日期
     *
     * @return
     */
    public String getStatDate();

    /**
     * @param statDate
     */
    public void setStatDate(String statDate);

    /**
     * 设备应开机时长
     *
     * @return
     */
    public int getOpenTimes();

    /**
     * @param openTimes
     */
    public void setOpenTimes(int openTimes);

    /**
     * 设备正常开机时长
     *
     * @return
     */
    public int getHealthyTimeReal();

    /**
     * @param healthyTimeReal
     */
    public void setHealthyTimeReal(int healthyTimeReal);

    /**
     * 设备未知时长
     *
     * @return
     */
    public int getUnknownTimeReal();

    /**
     * @param unknownTimeReal
     */
    public void setUnknownTimeReal(int unknownTimeReal);

    /**
     * 设备维护时长
     *
     * @return
     */
    public int getMaintainTimeReal();

    /**
     * @param maintainTimeReal
     */
    public void setMaintainTimeReal(int maintainTimeReal);

    /**
     * 设备故障时长
     *
     * @return
     */
    public int getFaultTimeReal();

    /**
     * @param faultTimeReal
     */
    public void setFaultTimeReal(int faultTimeReal);

    /**
     * ATMP故障时长
     *
     * @return
     */
    public int getAtmpTimeReal();

    /**
     * @param atmpTimeReal
     */
    public void setAtmpTimeReal(int atmpTimeReal);

    /**
     * 暂停时长
     *
     * @return
     */
    public int getStopTimeReal();

    /**
     * @param stopTimeReal
     */
    public void setStopTimeReal(int stopTimeReal);

    /**
     * 方案开机时间
     *
     * @return
     */
    public String getProgramOpenTime();

    /**
     * @param programOpenTime
     */
    public void setProgramOpenTime(String programOpenTime);

    /**
     * 方案关机时间
     *
     * @return
     */
    public String getProgramCloseTime();

    /**
     * @param programCloseTime
     */
    public void setProgramCloseTime(String programCloseTime);

    /**
     * 方案应开机时长
     *
     * @return
     */
    public int getProgramTimes();

    /**
     * @param programTimes
     */
    public void setProgramTimes(int programTimes);

    /**
     * 方案有效开机时长
     *
     * @return
     */
    public int getProgramTimeReal();

    /**
     * @param programTimeReal
     */
    public void setProgramTimeReal(int programTimeReal);





	/**
	 * @return
	 */
	public String getOrgName();

	/**
	 * @param orgName
	 */
	public void setOrgName(String orgName);

	/**
	 * @return
	 */
	public String getDevCatalogName();

	/**
	 * @param devCatalogName
	 */
	public void setDevCatalogName(String devCatalogName) ;


	/**
	 * @return
	 */
	public double getAvgOpenRate();
	
	/**
	 * @param avgOpenRate
	 */
	public void setAvgOpenRate(double avgOpenRate);

	/**
     * 在行离行标志
     *
     * @return
     */
	public String getAwayFlag();


	/**
	 * @param awayFlag
	 */
	public void setAwayFlag(String awayFlag) ;


}
