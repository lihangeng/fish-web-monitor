package com.yihuacomputer.fish.api.monitor.software;


/**
 * 系统软件信息
 * @author YiHua
 *
 */
public interface ISoftware {
	public String getTerminalId();
	
	public IOS getOS();

	public IAnti getAnti();

	public String getAtmcVer();

	public String getChkCashData();

	public ISP getSP();
	
	public void setTerminalId(String terminalId);
	
	public void setOS(IOS os);

	public void setAnti(IAnti anti) ;

	public void setAtmcVer(String atmcVer);

	public void setChkCashData(String chkCashData);

	public void setSP(ISP sp);
}
