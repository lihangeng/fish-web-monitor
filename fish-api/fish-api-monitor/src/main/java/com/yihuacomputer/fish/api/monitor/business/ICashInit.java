package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

/**
 * 加钞信息
 * 
 * @author YiHua
 * 
 */
public interface ICashInit {
    /**
     * 设置终端号.
     * 
     * @param terminalId
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

    /**
     * @return
     */
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

    /**
     * 钞箱详情.
     * 
     * @param boxAmtDetail
     */
    public void setBoxDetail(List<IBoxInitDetail> boxAmtDetail);

    /**
     * 取设备详情.
     * 
     * @return
     */
    public List<IBoxInitDetail> getBoxDetail();

	/**
	 * 转化为日期 yyyymmdd
	 * @return
	 */
	public int getDates();
	/**
	 * 转化为日期 yyyymmdd
	 * @param dates
	 */
	public void setDates(int dates);
}
