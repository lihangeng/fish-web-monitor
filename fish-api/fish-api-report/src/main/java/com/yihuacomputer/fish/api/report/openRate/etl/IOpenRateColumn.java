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
	/**
	 * @return
	 */
	long getId();
	
	/**
	 * 应开机时长，单位为秒
	 * @return
	 */
	long getOpenTimes();
	/**
	 * @param openTimes
	 */
	void setOpenTimes(long openTimes);
	
	/**
	 * 获得实际的开机时长，单位为秒
	 * @return
	 */
	long getHealthyTimeReal();
	/**
	 * @param healthyTimeReal
	 */
	void setHealthyTimeReal(long  healthyTimeReal);
	
    /**
     * 获得开机率，正常开机时间/应开机时间
     * @return
     */
    public double getOpenRate();
    
    /**
     * 设置开机率
     * @param openRate
     */
    public void setOpenRate(double openRate);
}
