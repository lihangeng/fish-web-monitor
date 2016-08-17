package com.yihuacomputer.fish.api.session;

import javax.servlet.http.HttpSession;



/**
 * 会话管理
 * @author xuxiang
 *
 */
public interface ISessionManage {
	/**
	 * 用户是否已登录
	 * @param session
	 * @return
	 */
	public boolean hasLogin(String userCode);
	
	/**
	 * 强制某个用户退出
	 * @param userCode
	 */
	public void logout(String userCode);
	
	/**
	 * 用户登入
	 * @param userCode
	 * @param session
	 */
	public void login(String userCode ,HttpSession session);
	
	/**
	 * 接到其他节点通知后登记登陆
	 * 
	 * @param loginMessage
	 */
	public void loginByNotice(LoginMessage loginMessage);
	
	/**
	 * 接到其他节点通知后登记退出
	 * 
	 * @param loginMessage
	 */
	public void logoutByNotice(LoginMessage loginMessage);
	
/*	*//**
	 * 某个用户超时退出 
	 * @param userCode
	 *//*
	public void timeout(String userCode,HttpSession session);
	
	public Map<String,HttpSession> getSession(String userCode);
	
	public void add(String userCode,Map<String,HttpSession> map);
	
	public void remove(String userCode);*/
	
}
