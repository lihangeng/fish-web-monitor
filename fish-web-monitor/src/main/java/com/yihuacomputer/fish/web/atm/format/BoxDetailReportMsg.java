package com.yihuacomputer.fish.web.atm.format;

/**
 * @author GQ
 *
 */
public class BoxDetailReportMsg {
	/**
	 * 获取钱箱标识.
	 */
	private String id;
	
	
	/**初始化张数*/
	private int initialCount;
	
	/**存入张数*/
	private int cashInCount;

	/**
	 *取出张数 
	 */
	private int dispenseNumber; 
	/**
	 * 获取钱箱币种.
	 */
	private String currency;
	/**
	 * 获取钞票面值
	 */
	private int value;
	/**
	 * 获取钞票张数.
	 */
	private int number;
	/**
	 * 设置钞箱类型
	 */
	private String boxType;
	/**
	 * 钞箱允许存入的最大张数Maximum
	 */
	private int maximum;

	/**
	 * 获取钞箱状态
	 */
	private String boxStatus;

	/**
	 * 获取钱箱标识
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * 获取钱箱标识
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取钱箱币种
	 * @return
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * 获取钱箱币种
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * 获取钞票面值
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * 获取钞票面值
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * 获取钞票张数
	 * @return
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * 获取钞票张数
	 * @param number
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * 设置钞箱类型
	 * @return
	 */
	public String getBoxType() {
		return boxType;
	}

	/**
	 * 设置钞箱类型
	 * @param boxType
	 */
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	/**
	 * 钞箱允许存入的最大张数Maximum
	 * @return
	 */
	public int getMaximum() {
		return maximum;
	}

	/**
	 * 钞箱允许存入的最大张数Maximum
	 * @param maximum
	 */
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	/**
	 * 获取钞箱状态
	 * @return
	 */
	public String getBoxStatus() {
		return boxStatus;
	}

	/**
	 * 获取钞箱状态
	 * @param boxStatus
	 */
	public void setBoxStatus(String boxStatus) {
		this.boxStatus = boxStatus;
	}

	public int getDispenseNumber() {
		return dispenseNumber;
	}

	public void setDispenseNumber(int dispenseNumber) {
		this.dispenseNumber = dispenseNumber;
	}

	public int getInitialCount() {
		return initialCount;
	}

	public void setInitialCount(int initialCount) {
		this.initialCount = initialCount;
	}

	public int getCashInCount() {
		return cashInCount;
	}

	public void setCashInCount(int cashInCount) {
		this.cashInCount = cashInCount;
	}

}
