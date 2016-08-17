package com.yihuacomputer.fish.session;

import javax.servlet.http.HttpSession;

public class SessionInfo {
	private String sessionId;
	private HttpSession session;

	public SessionInfo(String sessionId, HttpSession session) {
		this.sessionId = sessionId;
		this.session = session;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

}
