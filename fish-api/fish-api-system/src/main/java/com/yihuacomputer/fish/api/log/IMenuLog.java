package com.yihuacomputer.fish.api.log;

import java.util.Date;

import com.yihuacomputer.common.ITypeIP;

/**
 * 菜单操作日志
 * @author diaojunpeng
 * @version 0.7
 * @since 0.7
 * @date 2011-03-15
 */
public interface IMenuLog {
	/**
	 * @param id
	 */
	public void setId(long id);
	/**
	 * @return
	 */
	public long getId();
	/**
	 * 设置操作人
	 * @param code
	 */
	public void setCode(String code);
	/**
	 * @return
	 */
	public String getCode();

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
