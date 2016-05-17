package com.yihuacomputer.fish.web.machine.form;

public class DeviceDetailForm {
	/**
	 * 设备号
	 */
	private long number;

	/**
	 * 所属机构
	 */
	private String affiliation;

	/**
	 * 设备地址
	 */
	private String address;

	/**
	 * 设备型号
	 */
	private String model;

	/**
	 * 经营方式
	 */
	private String operation;

	public DeviceDetailForm() {
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public String getAffiliation() {
		return affiliation;
	}

	public void setAffiliation(String affiliation) {
		this.affiliation = affiliation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}
