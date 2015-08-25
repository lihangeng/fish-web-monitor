package com.yihuacomputer.fish.web.atm.format;

import java.util.List;

import com.yihuacomputer.fish.monitor.entity.alarm.SysProcess;

/**
 * 白名单进程信息
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2011-12-26 下午03:23:42
 * @version 进程报文
 */
public class SchindlerMSg {

	/**
	 * 设备号
	 */
	private String termId;

	/**
	 * 有效进程列表
	 */
	private List<SysProcess> procList;

	/**
	 * 响应码
	 */
	private String ret;

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public void setProcList(List<SysProcess> procList) {
		this.procList = procList;
	}

	public String getTermId() {
		return this.termId;
	}

	public List<SysProcess> getProcList() {
		return this.procList;
	}

	public String getRet() {
		return this.ret;
	}
}
