package com.yihuacomputer.fish.web.index.form;

import java.util.ArrayList;
import java.util.List;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.log.IMenuLog;

public class MenuLogForm {
	private long id;
	private String code;
	private String ip;
	private String loginTime;
	private boolean success;
	private String remark;
	private String content;
	private String sessionId;

	public MenuLogForm() {
	}

	public MenuLogForm(IMenuLog menuLog) {
		id = menuLog.getId();
		code = menuLog.getCode();
		ip = menuLog.getIp().toString();
		loginTime = DateUtils.getTimestamp(menuLog.getLoginTime());
		success = menuLog.isSuccess();
		remark = menuLog.getRemark();
		content = menuLog.getContent();
		sessionId = menuLog.getSessionId();
	}

	public static List<MenuLogForm> convert(List<IMenuLog> resources) {
		List<MenuLogForm> result = new ArrayList<MenuLogForm>();
		for (IMenuLog resource : resources) {
			result.add(new MenuLogForm(resource));
		}
		return result;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
