package com.yihuacomputer.fish.web.daily.form;
import java.util.List;

/**
 * @author YiHua
 *
 */
public class RuntimeInfoListForm {

	private List<RuntimeInfoForm> runtimeInfoList;
	
	private String appRet;
	
	private String cmdKey;
	
	private String url;
	
	public String getCmdKey() {
		return cmdKey;
	}

	public void setCmdKey(String cmdKey) {
		this.cmdKey = cmdKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<RuntimeInfoForm> getRuntimeInfoList() {
		return runtimeInfoList;
	}

	public void setRuntimeInfoList(List<RuntimeInfoForm> runtimeInfoList) {
		this.runtimeInfoList = runtimeInfoList;
	}

	public String getAppRet() {
		return appRet;
	}

	public void setAppRet(String appRet) {
		this.appRet = appRet;
	}

	
}
