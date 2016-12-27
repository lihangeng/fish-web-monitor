package com.yihuacomputer.fish.api.session;


/**
 * @author YiHua
 *
 */
public class LoginMessage {
	private String username;
	private String sessionID;
	
	
	public LoginMessage() {
		
	}
	/**
	 * @param username
	 * @param sessionID
	 */
	public LoginMessage(String username, String sessionID) {
		this.username = username;
		this.sessionID = sessionID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	
	
}
