package com.yihuacomputer.fish.api.log;

import java.util.Date;

import com.yihuacomputer.common.ITypeIP;

/**
 * web系统登录日志
 * @author xuxigang
 * @version 0.2
 * @since 0.2
 * @date 2010-11-15
 */
public interface IWebLoginLog {
	/**
	 * @param id
	 */
	public void setId(long id);
	/**
	 * @return
	 */
	public long getId();
	/**
	 * 设置主账号编码
	 * @param code
	 */
	public void setMasterCode(String code);
	/**
	 * @return
	 */
	public String getMasterCode();
	/**
	 * 设置用户名
	 * @param userName
	 */
	public void setUserName(String userName);
	/**
	 * @return
	 */
	public String getUserName();
	/**
	 * 设置登录时间
	 * @param loginTime
	 */
	public void setLoginTime(Date loginTime);
	/**
	 * @return
	 */
	public Date getLoginTime();
	/**
	 * 设置退出时间
	 * @param logoutTime
	 */
	public void setLogoutTime(Date logoutTime);
	/**
	 * @return
	 */
	public Date getLogoutTime();
	/**
	 * 设置请求的客户端端IP
	 * @param clientIp
	 */
	public void setClientIp(ITypeIP clientIp);
	/**
	 * @return
	 */
	public ITypeIP getClientIp();
	/**
	 * 设置web服务器IP
	 * @param serverIp
	 */
	public void setServerIp(ITypeIP serverIp);
	/**
	 * @return
	 */
	public ITypeIP getServerIp();
	/**
	 * 设置会话ID
	 * @param sessionId
	 */
	public void setSessionId(String sessionId);
	/**
	 * @return
	 */
	public String getSessionId();
	/**
	 * 设置会话名称
	 * @param sessioName
	 */
	public void setSessionName(String sessioName);
	/**
	 * @return
	 */
	public String getSessioName();
	/**
	 * 登录是否成功
	 * @return
	 */
	public boolean isSuccess();
	/**
	 * 设置登录失败原因
	 * @param remark
	 */
	public void setRemark(String remark);
	/**
	 * @return
	 */
	public String getRemark();
	
}
