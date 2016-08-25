package com.yihuacomputer.fish.api.monitor.box;

/**
 * @author GQ
 * 加钞信息以设备号为唯一约束
 */
public interface ICashInitUnique {
	/**
     * 设置终端号.
     * 
     * @param termialId
     */
    public void setTerminalId(String terminalId);

    /**
     * 获取设备号
     * 
     * @return
     */
    public String getTerminalId();

    /**
     * 加钞ID
     * 
     * @param uuId
     */
    public void setUuId(String uuId);

    /**
     * 设备加钞日期
     * 
     * @param date
     */
    public void setDate(String date);

    public String getDate();

    /**
     * 获取加钞ID.
     * 
     * @return
     */
    public String getUuId();

    /**
     * 加钞总金额.
     * 
     * @param amt
     */
    public void setAmt(long amt);

    /**
     * 取加钞金额.
     * 
     * @return
     */
    public long getAmt();
}
