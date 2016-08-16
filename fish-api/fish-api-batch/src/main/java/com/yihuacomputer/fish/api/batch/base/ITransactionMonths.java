package com.yihuacomputer.fish.api.batch.base;

public interface ITransactionMonths {

	public long getId();

	public void setId(long id);

	/**
	 * 交易日期
	 * @return
	 */
	public long getTransDate();
	/**
	 * 交易日期
	 * @param transDate
	 */
	public void setTransDate(long transDate);

	/**
	 * 交易次数
	 * @return
	 */
	public long getTransCount();
	/**
	 * 交易次数
	 * @param transCount
	 */
	public void setTransCount(long transCount);

	/**
	 * 厂商名称
	 * @return
	 */
	public String getVendorName();
	/**
	 * 厂商名称
	 * @param vendorName
	 */
	public void setVendorName(String vendorName);

	/**
	 * 设备型号
	 * @return
	 */
	public String getDevType();
	/**
	 * 设备型号
	 * @param devType
	 */
	public void setDevType(String devType);

	/**
	 * 交易码
	 * @return
	 */
	public String getTransCode();
	/**
	 * 交易码
	 * @return
	 */
	public void setTransCode(String transCode);
	
	/**
	 * 卡类型
	 * @return
	 */
	public String getCardType();
	/**
	 * 卡类型
	 * @return
	 */
	public void setCardType(String cardType);
	
	/**
	 * 交易金额
	 * @return
	 */
	public long getTransAmt();
	/**
	 * 交易金额
	 * @param transAmt
	 */
	public void setTransAmt(long transAmt);
	
	/**
	 * 品牌ID
	 * @return
	 */
	public long getVendorId() ;
	/**
	 * 品牌ID
	 * @param vendorId
	 */
	public void setVendorId(long vendorId);

	/**
	 * 型号ID
	 * @return
	 */
	public long getDevTypeId();
	/**
	 * 型号ID
	 * @param devTypeId
	 */
	public void setDevTypeId(long devTypeId);

}
