package com.yihuacomputer.fish.web.atm.format;

import com.yihuacomputer.fish.api.device.RegStatus;

/**
 * 设备签到信息
 * @author YiHua
 *
 */
public class SignMsg {
	private String termId;
	private String termIp;
	private String regSn;
	private String ret;
	private String atmcVersion;
	private String serverDateTime;
	private RegStatus regStatus;

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermId() {
		return this.termId;
	}

	public void setTermIp(String termIp) {
		this.termIp = termIp;
	}

	public String getTermIp() {
		return this.termIp;
	}

	
	public String getAtmcVersion() {
		return atmcVersion;
	}

	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}

	public RegStatus getRegStatus() {
		if(regStatus!=null){
			return regStatus;
		}else{
			return RegStatus.UNREGISTERED;
		}		
	}

	public void setRegStatus(RegStatus regStatus) {
		this.regStatus = regStatus;
	}

	public void setRegSn(String regSn) {
		this.regSn = regSn;
	}

	public String getRegSn() {
		return this.regSn;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public String getRet() {
		return this.ret;
	}

	public String getServerDateTime() {
		return serverDateTime;
	}

	public void setServerDateTime(String serverDateTime) {
		this.serverDateTime = serverDateTime;
	}
	
}
