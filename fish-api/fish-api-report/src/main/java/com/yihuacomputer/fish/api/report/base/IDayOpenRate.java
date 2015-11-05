package com.yihuacomputer.fish.api.report.base;

public interface IDayOpenRate {

    /**
     * ID
     *
     * @return
     */
    public long getId();

    public void setId(long id);

    /**
     * 设备号
     *
     * @return
     */
    public String getTerminalId();

    public void setTerminalId(String terminalId);

    /**
     * 统计日期
     *
     * @return
     */
    public String getStatDate();

    public void setStatDate(String statDate);

    /**
     * 设备应开机时长
     *
     * @return
     */
    public int getOpenTimes();

    public void setOpenTimes(int openTimes);

    /**
     * 设备正常开机时长
     *
     * @return
     */
    public int getHealthyTimeReal();

    public void setHealthyTimeReal(int healthyTimeReal);

    /**
     * 设备未知时长
     *
     * @return
     */
    public int getUnknownTimeReal();

    public void setUnknownTimeReal(int unknownTimeReal);

    /**
     * 设备维护时长
     *
     * @return
     */
    public int getMaintainTimeReal();

    public void setMaintainTimeReal(int maintainTimeReal);

    /**
     * 设备故障时长
     *
     * @return
     */
    public int getFaultTimeReal();

    public void setFaultTimeReal(int faultTimeReal);

    /**
     * ATMP故障时长
     *
     * @return
     */
    public int getAtmpTimeReal();

    public void setAtmpTimeReal(int atmpTimeReal);

    /**
     * 暂停时长
     *
     * @return
     */
    public int getStopTimeReal();

    public void setStopTimeReal(int stopTimeReal);

    /**
     * 方案开机时间
     *
     * @return
     */
    public String getProgramOpenTime();

    public void setProgramOpenTime(String programOpenTime);

    /**
     * 方案关机时间
     *
     * @return
     */
    public String getProgramCloseTime();

    public void setProgramCloseTime(String programCloseTime);

    /**
     * 方案应开机时长
     *
     * @return
     */
    public int getProgramTimes();

    public void setProgramTimes(int programTimes);

    /**
     * 方案有效开机时长
     *
     * @return
     */
    public int getProgramTimeReal();

    public void setProgramTimeReal(int programTimeReal);





	public String getOrgName();

	public void setOrgName(String orgName);

	public String getDevCatalogName();

	public void setDevCatalogName(String devCatalogName) ;





}
