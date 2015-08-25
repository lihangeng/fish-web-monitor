package com.yihuacomputer.fish.api.log;

import java.util.Date;

import com.yihuacomputer.common.ITypeIP;

/**
 * 工作流操作日志
 * @author diaojunpeng
 * @version 0.7
 * @since 0.7
 * @date 2011-03-22
 */
public interface IWorkflowLog {
	public void setId(long id);
	public long getId();
	/**
	 * 设置操作人
	 * @param authorizerCode
	 */
	public void setCode(String code);
	public String getCode();
	
	/**
	 * 设置工作流ID
	 * @param taskID
	 */
	public void setTaskID(String taskID);
	public String getTaskID();
	/**
	 * 设置审批人
	 * @param processAuditerCode
	 */
	public void setProcessAuditerCode(String code);
	public String getProcessAuditerCode();
	/**
	 * 设置文件名
	 * @param fileName
	 */
	public void setFileName(String fileName);
	public String getFileName();
	/**
	 * 设置文件地址
	 * @param filePath
	 */
	public void setFilePath(String filePath);
	public String getFilePath();
	
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
