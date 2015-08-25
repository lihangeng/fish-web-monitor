package com.yihuacomputer.fish.web.atm.format;

/**
 * 设备注册信息
 * 
 * @author YiHua
 * 
 */
public class RegistrationMsg {
	private String termId;
	private String regId;
	private String regSn;
	private String ret;

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTermId() {
		return this.termId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegId() {
		return this.regId;
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

}
