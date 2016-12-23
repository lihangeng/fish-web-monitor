package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public interface ITransType{

	/**
	 * @return
	 */
	public long getId();

	/**
	 * @return
	 */
	public String getTransCode();

	/**
	 * @param transCode
	 */
	public void setTransCode(String transCode);

	/**
	 * @return
	 */
	public String getCodeDesc();

	/**
	 * @param codeDesc
	 */
	public void setCodeDesc(String codeDesc);
	
	/**
	 * @return
	 */
	public int getTransSeq();

	/**
	 * @param transSeq
	 */
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
