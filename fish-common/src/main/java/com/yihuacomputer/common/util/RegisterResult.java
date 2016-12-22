package com.yihuacomputer.common.util;

/**
 * 注册结果
 *
 */
public class RegisterResult {
	
	private boolean success;
	private String message;
	
	
	public RegisterResult() {
		
	}


	/**
	 * @param success
	 * @param message
	 */
	public RegisterResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}	

}
