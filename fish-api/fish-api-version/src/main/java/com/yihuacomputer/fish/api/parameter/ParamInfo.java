package com.yihuacomputer.fish.api.parameter;


/**
 * @author YiHua
 *
 */
public class ParamInfo {
	/**
	 * 参数文件在服务器上的路径
	 */
	private String serverPath;
	/**
	 * 参数文件版本号--时间戳
	 */
	private long versionNo;
	/**
	 * 设备号
	 */
	private String terminalId;
	/**
	 * 参数类型
	 */
	private String appType;
	/**
	 * 参数更新进度(通知，下载，部署成功..)
	 */
	private String ret;
	
	private long taskId;

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long timestamp) {
		this.versionNo = timestamp;
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

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
}
