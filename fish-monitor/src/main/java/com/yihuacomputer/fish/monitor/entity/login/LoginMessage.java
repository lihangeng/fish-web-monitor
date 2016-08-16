package com.yihuacomputer.fish.monitor.entity.login;


public class LoginMessage {
	private String messageType;
	private String username;
	private String sessionID;
	
	
	public LoginMessage() {
		
	}
	public LoginMessage(String messageType, String username, String sessionID) {
		this.messageType = messageType;
		this.username = username;
		this.sessionID = sessionID;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
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
