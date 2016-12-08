package com.yihuacomputer.common.exception;

/**
 * 应用程序异常.
 * @author yantao
 *
 */
public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String message;
	private Throwable e;
	
	/**
	 * 应用异常消息的构造
	 * @param message
	 */
	public AppException(String message) {
		this.message = message;
	}
	
	/**
	 * 应用异常消息和异常的构造
	 * @param message
	 * @param e
	 */
	public AppException(String message, Throwable e) {
		this.message = message;
		this.e = e;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
	
	public Throwable getThrowable() {
		return e;
	}

}
