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
    public long getOpenTimes();

    public void setOpenTimes(long openTimes);

    /**
     * 设备正常开机时长
     *
     * @return
     */
    public long getHealthyTimeReal();

    public void setHealthyTimeReal(long healthyTimeReal);

    /**
     * 设备未知时长
     *
     * @return
     */
    public long getUnknownTimeReal();

    public void setUnknownTimeReal(long unknownTimeReal);

    /**
     * 设备维护时长
     *
     * @return
     */
    public long getMaintainTimeReal();

    public void setMaintainTimeReal(long maintainTimeReal);

    /**
     * 设备故障时长
     *
     * @return
     */
    public long getFaultTimeReal();

    public void setFaultTimeReal(long faultTimeReal);

    /**
     * ATMP故障时长
     *
     * @return
     */
    public long getAtmpTimeReal();

    public void setAtmpTimeReal(long atmpTimeReal);

    /**
     * 暂停时长
     *
     * @return
     */
    public long getStopTimeReal();

    public void setStopTimeReal(long stopTimeReal);

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
    public long getProgramTimes();

    public void setProgramTimes(long programTimes);

    /**
     * 方案有效开机时长
     *
     * @return
     */
    public long getProgramTimeReal();

    public void setProgramTimeReal(long programTimeReal);

    /**
     * 无对外画面时长
     */
    public long getNoScreenReal();

    public void setNoScreenReal(long noScreenReal);
}
