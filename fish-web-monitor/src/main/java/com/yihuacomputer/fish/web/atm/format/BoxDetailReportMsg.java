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

}
