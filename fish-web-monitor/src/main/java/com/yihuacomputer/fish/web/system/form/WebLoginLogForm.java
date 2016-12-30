package com.yihuacomputer.fish.web.system.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.fish.api.log.IWebLoginLog;


/**
 * @author shengjie
 *
 */
public class WebLoginLogForm {
	private long id;
	private String clientIp;	
	private String loginTime;
	private String logoutTime;	
	private String masterCode;
	private String remark;	
	private String serverIp;	
	private String sessionId;	
	private String sessioName;
	private String userName;
	private String isSuccess;
	public WebLoginLogForm() {
		
	}
	
	/**
	 * @param webLoginLog
	 */
	public WebLoginLogForm(IWebLoginLog webLoginLog) {
		this.id  = webLoginLog.getId();
		clientIp = webLoginLog.getClientIp().toString();
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		loginTime = timeFormat.format(webLoginLog.getLoginTime());
		if (webLoginLog.getLogoutTime() != null) {
			logoutTime = timeFormat.format(webLoginLog.getLogoutTime());
		} 		
		masterCode = webLoginLog.getMasterCode();
		remark = webLoginLog.getRemark();
		serverIp = webLoginLog.getServerIp().toString();
		sessionId = webLoginLog.getSessionId().toString();
		sessioName = webLoginLog.getSessioName();
		userName = webLoginLog.getUserName();
		isSuccess = webLoginLog.isSuccess()?"1":"0";
	}
	
	/**
	  * 方法描述 : 转换
	  * @param parameters
	  * @return
	  */
	 public static List<WebLoginLogForm> convert(List<IWebLoginLog> parameters){
		 List<WebLoginLogForm> result =new ArrayList<WebLoginLogForm>();
		 for(IWebLoginLog webLoginLog : parameters){
			 result.add(new WebLoginLogForm(webLoginLog));
		 }
		 return result;
	 }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getServerIp() {
		return serverIp;
	}
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getSessioName() {
		return sessioName;
	}
	public void setSessioName(String sessioName) {
		this.sessioName = sessioName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMasterCode() {
		return masterCode;
	}

	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String success) {
		this.isSuccess = success;
	} 
	
}
