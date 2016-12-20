package com.yihuacomputer.fish.atmlog.entity;

import java.io.Serializable;
import java.util.List;

import com.yihuacomputer.fish.api.atmlog.IAtmCycle;
import com.yihuacomputer.fish.api.atmlog.ICustomerCycle;

/**
 * ATM账务周期信息
 * @author YiHua
 *
 */
public class AtmCycle implements IAtmCycle,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8323574070145667179L;

	/*结账ID*/
	private String cashInId;
	
	/*结账时间*/
	private String dateTime;
	
	/*客户交易列表*/
	private List<ICustomerCycle> customers;

	@Override
	public String getCashInId() {
		return cashInId;
	}

	public void setCashInId(String cashInId) {
		this.cashInId = cashInId;
	}

	@Override
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public List<ICustomerCycle> getCustomers() {
		return customers;
	}

	public void setCustomers(List<ICustomerCycle> customers) {
		this.customers = customers;
	}
	
}
