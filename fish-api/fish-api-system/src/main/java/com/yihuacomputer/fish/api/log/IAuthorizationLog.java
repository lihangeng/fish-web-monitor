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
	/**
	 * @param id
	 */
	public void setId(long id);
	/**
	 * @return
	 */
	public long getId();
	/**
	 * 设置授权人
	 * @param authorizerCode
	 */
	public void setAuthorizerCode(String authorizerCode);
	/**
	 * @return
	 */
	public String getAuthorizerCode();
	/**
	 * 设置被授权人
	 * @param authorizeeCode
	 */	
	public void setAuthorizeeCode(String authorizeeCode);
	/**
	 * @return
	 */
	public String getAuthorizeeCode();
	/**
	 * 设置操作时间
	 * @param loginTime
	 */
	public void setLoginTime(Date loginTime);
	/**
	 * @return
	 */
	public Date getLoginTime();
	/**
	 * 设置请求的客户端端IP
	 * @param ip
	 */
	public void setIp(ITypeIP ip);
	/**
	 * @return
	 */
	public ITypeIP getIp();
	
	/**
	 * 设置操作内容
	 * @param content
	 */
	public void setContent(String content);
	/**
	 * @return
	 */
	public String getContent();
	
	/**
	 * 设置操作类型type
	 * @param 
	 */
	public void setType(String tpye);
	/**
	 * @return
	 */
	public String getType();

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
