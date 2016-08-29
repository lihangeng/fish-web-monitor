/**
 * 
 */
package com.yihuacomputer.fish.api.report.openRate.etl;

/**
 * 公用的开机率显示列
 * @author xuxiang
 *
 */
public interface IOpenRateColumn {
	long geId();
	
	/**
	 * 应开机时长，单位为秒
	 * @return
	 */
	long getOpenTimes();
	void setOpenTimes(long openTimes);
	
	/**
	 * 获得实际的开机时长，单位为秒
	 * @return
	 */
	long getHealthyTimeReal();
	void setHealthyTimeReal(long  healthyTimeReal);
	
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
     * 获得开机率，正常开机时间/应开机时间
     * @return
     */
    public double getRate();
}
