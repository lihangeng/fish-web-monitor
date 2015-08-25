package com.yihuacomputer.fish.web.command.format;

/**
 * 屏幕截屏
 * 
 * @author wangchao
 * 
 */
public class ScreenForm {
	/**
	 * 唯一
	 */
	private String code;

	/**
	 * ip地址
	 */
	private String ipAddress;

	/**
	 * 设备端口(默认8080)
	 */
	private long port = 8080;

	/**
	 * 设备号
	 */
	private String terminalId;

	/**
	 * 开始录制时间
	 */
	private String startCameraDate;

	/**
	 * 结束录制时间
	 */
	private String stopCameraDate;

	/**
	 * 屏幕类型
	 */
	private ScreenType monitorType;

	/**
	 * 抓屏文件名及存放路径
	 */
	private String fileName;

	/**
	 * 抓去所有屏幕的文件名及存放路劲
	 */
	private String[] names;

	/**
	 * 保存的路径
	 */
	private String filePathClient;

	/**
	 * 保存的名称
	 */
	private String fileNameClient;

	private long continued;

	private String url;

	private String cmdKey;

	private String appRet;

	private String httpStatusCode;

	/**
	 * 用户id,一台设备在同一时间只能由一个用户控制。
	 */
	private String userId;

	/**
	 * 当前正在操作的用户id
	 */
	private String currUserId;

	/**
	 * 0正在将生成的视频文件下载至服务器,1录制,2被其他用户占用,3该次录制已经自动停止,4完成,5不存在该屏幕
	 */
	private String status;

	/**
	 * 分组
	 */
	private String groupField;

	public ScreenForm() {
	}

	public ScreenForm(ScreenForm screen) {
		setMonitorType(screen.getMonitorType());
		setFileName(screen.getFileName());
	}

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getStartCameraDate() {
		return startCameraDate;
	}

	public void setStartCameraDate(String startCameraDate) {
		this.startCameraDate = startCameraDate;
	}

	public String getStopCameraDate() {
		return stopCameraDate;
	}

	public void setStopCameraDate(String stopCameraDate) {
		this.stopCameraDate = stopCameraDate;
	}

	public ScreenType getMonitorType() {
		return monitorType;
	}

	public void setMonitorType(ScreenType monitorType) {
		this.monitorType = monitorType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

	public long getContinued() {
		return continued;
	}

	public void setContinued(long continued) {
		this.continued = continued;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCurrUserId() {
		return currUserId;
	}

	public void setCurrUserId(String currUserId) {
		this.currUserId = currUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGroupField() {
		return groupField;
	}

	public void setGroupField(String groupField) {
		this.groupField = groupField;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public long getPort() {
		return port;
	}

	public void setPort(long port) {
		this.port = port;
	}


	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}
}
