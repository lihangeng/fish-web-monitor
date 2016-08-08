package com.yihuacomputer.fish.api.monitor.box;

public interface IDeviceBoxDetailInfo {

	public long getId();
	public void setId(long id);

	/**
	 * 设备钞箱概览ID
	 * @return
	 */
	public IDeviceBoxInfo getDeviceBoxInfo();
	/**
	 * 设备钞箱概览ID
	 * @param devBoxId
	 */
	public void setDeviceBoxInfo(IDeviceBoxInfo deviceBoxInfo);
	/**
	 * 钞箱ID
	 * @return
	 */
	public String getCashId();
	/**
	 * 钞箱ID
	 * @param cashId
	 */
	public void setCashId(String cashId);
	/**
	 * 币种
	 * @return
	 */
	public String getCurrency();
	/**
	 * 币种
	 * @param currency
	 */
	public void setCurrency(String currency);
	/**
	 * 纸币面额
	 * @return
	 */
	public int getValue();
	/**
	 * 纸币面额
	 * @param value
	 */
	public void setValue(int value);
	/**
	 * 钞票张数
	 * @return
	 */
	public int getNumber();
	/**
	 * 钞票张数
	 * @param number
	 */
	public void setNumber(int number);
	/**
	 * 钞箱类型
	 * @return
	 */
	public String getBoxType();
	/**
	 * 钞箱类型
	 * @param boxType
	 */
	public void setBoxType(String boxType);
	/**
	 * 钞箱最大容量
	 * @return
	 */
	public int getMaxiNum();
	/**
	 * 钞箱最大容量
	 * @param maxiNum
	 */
	public void setMaxiNum(int maxiNum);
	/**
	 * 当前钞箱是否有效(替换钞箱后，钞箱ID可能不一致，老钞箱信息不做删除，只做失效标识)
	 * @return
	 */
	public boolean isEffect();
	/**
	 * 当前钞箱是否有效
	 * @param effect
	 */
	public void setEffect(boolean effect);
	
	public boolean equals(IDeviceBoxDetailInfo idbdi);
}
