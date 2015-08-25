package com.yihuacomputer.fish.web.command.format;

/**
 * atmc应用版本和客户端版本信息
 * @author huxiaobao
 *
 */
public class AtmVersionForm {
	
	/**
	 * ATMC应用版本
	 */
	private String atmcVersion;
	/**
	 * 客户端版本
	 */
	private String agentVersion;
	
	private String url;

	private String cmdKey;

	private String appRet;

	private String httpStatusCode;

	public AtmVersionForm() {

	}

	public AtmVersionForm(AtmVersionForm atmVersionForm) {
		setAtmcVersion(atmVersionForm.getAtmcVersion());
		setAgentVersion(atmVersionForm.getAgentVersion());
		setAppRet(atmVersionForm.getAppRet());
		setCmdKey(atmVersionForm.getCmdKey());
		setHttpStatusCode(atmVersionForm.getHttpStatusCode());
		setUrl(atmVersionForm.getUrl());
	}

	public String getAtmcVersion() {
		return atmcVersion;
	}

	public void setAtmcVersion(String atmcVersion) {
		this.atmcVersion = atmcVersion;
	}

	public String getAgentVersion() {
		return agentVersion;
	}

	public void setAgentVersion(String agentVersion) {
		this.agentVersion = agentVersion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCmdKey() {
		return cmdKey;
	}

	public void setCmdKey(String cmdKey) {
		this.cmdKey = cmdKey;
	}

	public String getAppRet() {
		return appRet;
	}

	public void setAppRet(String appRet) {
		this.appRet = appRet;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

}
