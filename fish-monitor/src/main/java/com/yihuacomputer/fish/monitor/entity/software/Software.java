package com.yihuacomputer.fish.monitor.entity.software;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yihuacomputer.fish.api.monitor.software.IAnti;
import com.yihuacomputer.fish.api.monitor.software.IOS;
import com.yihuacomputer.fish.api.monitor.software.ISP;
import com.yihuacomputer.fish.api.monitor.software.ISoftware;


/**
 * @author YiHua
 *
 */
@Entity
@Table(name = "DEV_SOFTWARE")
public class Software implements ISoftware {

	@Id
	@Column(name = "TERMINAL_ID",length=20,nullable=false)
	private String terminalId;
	
	/**
	 * 操作系统
	 */
	@Embedded
	private OS os;

	/**
	 * 病毒软件
	 */
	@Embedded
	private Anti anti;

	/**
	 * C端应用版本
	 */
	@Column(name = "ATMC_VERSION",length=30)
	private String atmcVer;

	/**
	 * 验钞数据版本
	 */
	@Column(name = "CHK_CASH_DATA",length=50)
	private String chkCashData;

	/**
	 * SP软件信息
	 */
	@Embedded
	private SP sp;

	@Override
	public void setTerminalId(String terminalId){
		this.terminalId = terminalId;
	}
	
	@Override
	public String getTerminalId(){
		return this.terminalId;
	}
	
	@Override
	public void setOS(IOS os) {
		this.os = (OS)os;
	}

	@Override
	public void setAnti(IAnti anti) {
		this.anti = (Anti)anti;
	}

	@Override
	public void setAtmcVer(String atmcVer) {
		this.atmcVer = atmcVer;
	}

	@Override
	public void setChkCashData(String chkCashData) {
		this.chkCashData = chkCashData;
	}

	@Override
	public void setSP(ISP sp) {
		this.sp = (SP)sp;
	}

	@Override
	public IOS getOS() {
		return this.os;
	}

	@Override
	public IAnti getAnti() {
		return this.anti;
	}

	@Override
	public String getAtmcVer() {
		return this.atmcVer;
	}

	@Override
	public String getChkCashData() {
		return this.chkCashData;
	}

	@Override
	public ISP getSP() {
		return this.sp;
	}
	
	public void setSoftware(ISoftware software){
		this.setAnti(software.getAnti());
		this.setAtmcVer(software.getAtmcVer());
		this.setChkCashData(software.getChkCashData());
		this.setOS(software.getOS());
		this.setSP(software.getSP());
	}
}
