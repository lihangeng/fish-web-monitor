package com.yihuacomputer.fish.web.command.format;

import java.util.List;
/**
 * 进行信息
 * @author YiHua
 *
 */
public class ListProcessForm {
	private List<ProcessForm> processList;

	private String appRet;

	public List<ProcessForm> getProcessList() {
		return processList;
	}

	public void setProcessList(List<ProcessForm> processList) {
		this.processList = processList;
	}

	public String getAppRet() {
		return appRet;
	}

	public void setAppRet(String appRet) {
		this.appRet = appRet;
	}

}
