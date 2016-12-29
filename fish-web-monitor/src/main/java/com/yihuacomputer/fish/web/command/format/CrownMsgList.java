package com.yihuacomputer.fish.web.command.format;

import java.util.List;

/**
 * @author YiHua
 *
 */
public class CrownMsgList {
	private List<CrownMsg> crownList;

	private String appRet;
	
	public List<CrownMsg> getCrownList() {
		return crownList;
	}

	public void setCrownList(List<CrownMsg> crownList) {
		this.crownList = crownList;
	}

	public String getAppRet() {
		return appRet;
	}

	public void setAppRet(String appRet) {
		this.appRet = appRet;
	}
}
