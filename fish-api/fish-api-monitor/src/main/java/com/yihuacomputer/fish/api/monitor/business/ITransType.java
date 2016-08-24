package com.yihuacomputer.fish.api.monitor.business;

public interface ITransType{

	public long getId();

	public String getTransCode();

	public void setTransCode(String transCode);

	public String getCodeDesc();

	public void setCodeDesc(String codeDesc);
	
	public int getTransSeq();

	public void setTransSeq(int transSeq);
	
	/**
	 * @return 
	 * 0:不涉及现金交易
	 * 1:存款/钞箱入钞
	 * 2.取款/钞箱出钞
	 */
	public int getInOutFlag();

	/**
	 * @param inOutFlag 
	 * 0:不涉及现金交易
	 * 1:存款/钞箱入钞
	 * 2.取款/钞箱出钞
	 */
	public void setInOutFlag(int inOutFlag);
}
