package com.yihuacomputer.fish.atmlog.entity;

import java.io.Serializable;
import java.util.List;

import com.yihuacomputer.fish.api.atmlog.ICustomerCycle;
import com.yihuacomputer.fish.api.atmlog.ITransCycle;

/**
 * 客户交易
 * @author YiHua
 *
 */
public class CustomerCycle implements ICustomerCycle,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2886270693176824976L;

	/*交易时间 */
	private String dateTime;
	
	/*交易账号*/
	private String account;
	
	/*交易列表*/
	private List<ITransCycle> trans;

	@Override
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public List<ITransCycle> getTrans() {
		return trans;
	}

	public void setTrans(List<ITransCycle> trans) {
		this.trans = trans;
	}	
	
}
