package com.yihuacomputer.fish.api.monitor.software;


/**
 * 系统软件信息
 * @author YiHua
 *
 */
public interface ISoftware {
	/**
	 * @return
	 */
	public String getTerminalId();
	
	/**
	 * @return
	 */
	public IOS getOS();

	/**
	 * @return
	 */
	public IAnti getAnti();

	/**
	 * @return
	 */
	public String getAtmcVer();

	/**
	 * @return
	 */
	public String getChkCashData();

	/**
	 * @return
	 */
	public ISP getSP();
	
	/**
	 * @param terminalId
	 */
	public void setTerminalId(String terminalId);
	
	/**
	 * @param os
	 */
	public void setOS(IOS os);

	/**
	 * @param anti
	 */
	public void setAnti(IAnti anti) ;

	/**
	 * @param atmcVer
	 */
	public void setAtmcVer(String atmcVer);

	/**
	 * @param chkCashData
	 */
	public void setChkCashData(String chkCashData);

	/**
	 * @param sp
	 */
	public void setSP(ISP sp);
}
