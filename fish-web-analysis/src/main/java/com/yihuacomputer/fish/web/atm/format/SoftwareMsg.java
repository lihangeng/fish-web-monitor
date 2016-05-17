package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.monitor.entity.software.Anti;
import com.yihuacomputer.fish.monitor.entity.software.OS;
import com.yihuacomputer.fish.monitor.entity.software.SP;

/**
 * 软件信息
 * 
 * @author YiHua
 * 
 */
public class SoftwareMsg {
	private String termId;
	private OS os;
	private Anti anti;
	private String atmcVer;
	private String chkCashData;
	private SP sp;

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public OS getOS() {
		return os;
	}

	public void setOS(OS os) {
		this.os = os;
	}

	public Anti getAnti() {
		return anti;
	}

	public void setAnti(Anti anti) {
		this.anti = anti;
	}

	public String getAtmcVer() {
		return atmcVer;
	}

	public void setAtmcVer(String atmcVer) {
		this.atmcVer = atmcVer;
	}

	public String getChkCashData() {
		return chkCashData;
	}

	public void setChkCashData(String chkCashData) {
		this.chkCashData = chkCashData;
	}

	public SP getSP() {
		return sp;
	}

	public void setSP(SP sp) {
		this.sp = sp;
	}

}
