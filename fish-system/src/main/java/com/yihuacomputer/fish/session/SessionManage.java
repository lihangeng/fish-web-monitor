package com.yihuacomputer.fish.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.mq.IMqProducer;
import com.yihuacomputer.fish.api.session.ISessionManage;
import com.yihuacomputer.fish.api.session.LoginMessage;

@Service
public class SessionManage implements ISessionManage {

	@Autowired(required = false)
	private IMqProducer mqProducer;

	/**
	 * 保存会话信息
	 */
	private Map<String, SessionInfo> sessions = new HashMap<String, SessionInfo>();

	@Override
	public boolean hasLogin(String userCode) {
		return sessions.containsKey(userCode);
	}

	@Override
	public void logout(String userCode) {
		SessionInfo sessionInfo = sessions.get(userCode);
		if (sessionInfo != null) {
			HttpSession oldSession = sessionInfo.getSession();
			if(oldSession != null){
				oldSession.invalidate();
				// 通知所有节点，包括自己
				if (mqProducer != null) {
					LoginMessage loginMessage = new LoginMessage("LOGIN_OUT", userCode, oldSession.getId());
					mqProducer.put(JsonUtils.toJson(loginMessage));
				}
			}
		}
	}
	
	/**
	 * 接到退出或者超时通知后,做退出处理
	 * 
	 * @param loginMessage
	 */
	public void logoutByNotice(LoginMessage loginMessage) {
		String userCode = loginMessage.getUsername();
		SessionInfo sessionInfo = sessions.get(userCode);
		if (sessionInfo != null) {
			HttpSession session = sessionInfo.getSession();
			if (session != null) {
				session.invalidate();
			}
			sessions.remove(userCode);
		}
	}

	/**
	 * 接到其他节点通知后登记登陆
	 * 
	 * @param loginMessage
	 */
	public void loginByNotice(LoginMessage loginMessage) {
		String userCode = loginMessage.getUsername();
		SessionInfo sessionInfo = sessions.get(userCode);
		if (sessionInfo == null) {
			sessionInfo = new SessionInfo(loginMessage.getSessionID(), null);
			sessions.put(userCode, sessionInfo);
		}
	}

	@Override
	public void login(String userCode, HttpSession session) {
		SessionInfo sessionInfo = new SessionInfo(session.getId(), session);
		sessions.put(userCode, sessionInfo);
		if (mqProducer != null) {
			LoginMessage loginMessage = new LoginMessage("LOGIN_IN", userCode, session.getId());
			mqProducer.put(JsonUtils.toJson(loginMessage));
		}
	}
	
}