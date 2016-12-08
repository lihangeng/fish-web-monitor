package com.yihuacomputer.fish.api.report.batch;

/**
 * @author GQ
 *
 */
interface ITransactionDays {

	/**
	 * @return
	 */
	long getId();

	/**
	 * @param id
	 */
	void setId(long id);

	/**
	 * 交易日期
	 * @return
	 */
	long getTransDate();
	/**
	 * 交易日期
	 * @param transDate
	 */
	void setTransDate(long transDate);

	/**
	 * 交易次数
	 * @return
	 */
	long getTransCount();
	/**
	 * 交易次数
	 * @param transCount
	 */
	void setTransCount(long transCount);

	/**
	 * 厂商名称
	 * @return
	 */
	String getVendorName();
	/**
	 * 厂商名称
	 * @param vendorName
	 */
	void setVendorName(String vendorName);

	/**
	 * 设备型号
	 * @return
	 */
	String getDevType();
	/**
	 * 设备型号
	 * @param devType
	 */
	void setDevType(String devType);

	/**
	 * 交易码
	 * @return
	 */
	String getTransCode();
	/**
	 * 交易码
	 * @return
	 */
	void setTransCode(String transCode);
	
	/**
	 * 卡类型
	 * @return
	 */
	String getCardType();
	/**
	 * 卡类型
	 * @return
	 */
	void setCardType(String cardType);
	
	/**
	 * 交易金额
	 * @return
	 */
	long getTransAmt();
	/**
	 * 交易金额
	 * @param transAmt
	 */
	void setTransAmt(long transAmt);
	
	/**
	 * 品牌ID
	 * @return
	 */
	long getVendorId() ;
	/**
	 * 品牌ID
	 * @param vendorId
	 */
	void setVendorId(long vendorId);

	/**
	 * 型号ID
	 * @return
	 */
	long getDevTypeId();
	/**
	 * 型号ID
	 * @param devTypeId
	 */
	void setDevTypeId(long devTypeId);
}
