package com.yihuacomputer.fish.web.command.format;

/**
 * 抓屏保存格式
 * 
 * @author wangchao
 * 
 */
public class ScreenSaveForm {

	private ScreenType monitorType;

	private String filePathClient;

	private String fileNameClient;

	private String allPath;

	private String screenShotTime;

	private String src;

	public String getScreenShotTime() {
		return screenShotTime;
	}

	public void setScreenShotTime(String screenShotTime) {
		this.screenShotTime = screenShotTime;
	}

	public ScreenType getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(ScreenType monitorType) {
		this.monitorType = monitorType;
	}

	public String getFilePathClient() {
		return filePathClient;
	}

	public void setFilePathClient(String filePathClient) {
		this.filePathClient = filePathClient;
	}

	public String getFileNameClient() {
		return fileNameClient;
	}

	public void setFileNameClient(String fileNameClient) {
		this.fileNameClient = fileNameClient;
	}

	public void setAllPath(String allPath) {
		this.allPath = allPath;
	}

	public String getAllPath() {
		return allPath;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
