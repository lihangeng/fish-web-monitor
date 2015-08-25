package com.yihuacomputer.fish.web.command.format;

/**
 * 重启
 * 
 * @author YiHua
 * 
 */
public class RestartForm {
	private String url;

	private String cmdKey;

	private String appRet;

	private String httpStatusCode;

	private CommandLevel restartType;

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

	public CommandLevel getRestartType() {
		return restartType;
	}

	public void setRestartType(CommandLevel restartType) {
		this.restartType = restartType;
	}

}
