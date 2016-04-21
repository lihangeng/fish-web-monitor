package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.fish.api.parameter.IParamPublishResult;

public class ParamPublishMsg {
	private String terminalId;
	private String ret;
	private long taskId;
	private long versionNo;
	private String serverPath;

	public ParamPublishMsg(){
	}
	
	public ParamPublishMsg(IParamPublishResult result){
		this.taskId = result.getId();
		this.versionNo = result.getVersionNo();
	}
	
	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getRet() {
		return ret;
	}

	public void setRet(String ret) {
		this.ret = ret;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

}
