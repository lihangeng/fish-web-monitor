package com.yihuacomputer.fish.api.log;

import java.util.Date;

import com.yihuacomputer.common.ITypeIP;

/**
 * 主账号授权日志
 * @author diaojunpeng
 * @version 0.7
 * @since 0.7
 * @date 2011-02-25
 */
public interface IAuthorizationLog {
	public void setId(long id);
	public long getId();
	/**
	 * 设置授权人
	 * @param authorizerCode
	 */
	public void setAuthorizerCode(String authorizerCode);
	public String getAuthorizerCode();
	/**
	 * 设置被授权人
	 * @param authorizeeCode
	 */	
	public void setAuthorizeeCode(String authorizeeCode);
	public String getAuthorizeeCode();
	/**
	 * 设置操作时间
	 * @param loginTime
	 */
	public void setLoginTime(Date loginTime);
	public Date getLoginTime();
	/**
	 * 设置请求的客户端端IP
	 * @param clientIp
	 */
	public void setIp(ITypeIP ip);
	public ITypeIP getIp();
	
	/**
	 * 设置操作内容
	 * @param roleCode
	 */
	public void setContent(String content);
	public String getContent();
	
	/**
	 * 设置操作类型type
	 * @param 
	 */
	public void setType(String tpye);
	public String getType();

	/**
	 * 设置会话ID
	 * @param sessionId
	 */
	public void setSessionId(String sessionId);
	public String getSessionId();
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
	public String getRemark();
	
}
