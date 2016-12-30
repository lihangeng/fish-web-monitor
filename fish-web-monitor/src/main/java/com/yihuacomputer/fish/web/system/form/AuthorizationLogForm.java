package com.yihuacomputer.fish.web.system.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.log.IAuthorizationLog;

/**
 * @author YiHua
 *
 */
public class AuthorizationLogForm {
	private long id;
	private String authorizeeCode;
	private String authorizerCode;
	private String ip;
	private String loginTime;
	private boolean success;
	private String remark;
	private String content;
	private String sessionId;
	private String type;

	public AuthorizationLogForm() {
	}

	/**
	 * @param authorizationLog
	 */
	public AuthorizationLogForm(IAuthorizationLog authorizationLog) {
		id = authorizationLog.getId();
		authorizeeCode = authorizationLog.getAuthorizeeCode();
		authorizerCode = authorizationLog.getAuthorizerCode();
		ip = authorizationLog.getIp().toString();
		loginTime = DateUtils.getTimestamp(authorizationLog.getLoginTime());
		success = authorizationLog.isSuccess();
		remark = authorizationLog.getRemark();
		content = authorizationLog.getContent();
		sessionId = authorizationLog.getSessionId();
		type = authorizationLog.getType();
	}

	/**
	 * @param resources
	 * @return
	 */
	public static List<AuthorizationLogForm> convert(
			List<IAuthorizationLog> resources) {
		List<AuthorizationLogForm> result = new ArrayList<AuthorizationLogForm>();
		for (IAuthorizationLog resource : resources) {
			result.add(new AuthorizationLogForm(resource));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthorizeeCode() {
		return authorizeeCode;
	}

	public void setAuthorizeeCode(String authorizeeCode) {
		this.authorizeeCode = authorizeeCode;
	}

	public String getAuthorizerCode() {
		return authorizerCode;
	}

	public void setAuthorizerCode(String authorizerCode) {
		this.authorizerCode = authorizerCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
