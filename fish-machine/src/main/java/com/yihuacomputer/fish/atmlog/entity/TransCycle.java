package com.yihuacomputer.fish.atmlog.entity;

import java.io.Serializable;

import com.yihuacomputer.fish.api.atmlog.ITransCycle;

/**
 * 交易信息
 * @author YiHua
 *
 */
public class TransCycle implements ITransCycle,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5746769044277486797L;

	/*终端流水号*/
	private String terminalSerial; 
	
	/*主机流水号*/
	private String hostserial;
	
	/*交易代码*/
	private String transType;
	
	/*金额*/
	private String transAmount;
	
	/*主机返回码*/
	private String hostreturn;
	
	private String boxList;

	public String getTerminalSerial() {
		return terminalSerial;
	}

	public void setTerminalSerial(String terminalSerial) {
		this.terminalSerial = terminalSerial;
	}

	public String getHostserial() {
		return hostserial;
	}

	public void setHostserial(String hostserial) {
		this.hostserial = hostserial;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public String getHostreturn() {
		return hostreturn;
	}

	public void setHostreturn(String hostreturn) {
		this.hostreturn = hostreturn;
	}

	public String getBoxList() {
		return boxList;
	}

	public void setBoxList(String boxList) {
		this.boxList = boxList;
	}	
	
}
