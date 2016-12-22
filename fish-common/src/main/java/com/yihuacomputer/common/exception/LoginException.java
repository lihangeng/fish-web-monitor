package com.yihuacomputer.common.exception;

/**
 * 登陆异常
 *
 */
public class LoginException extends RemoteException {

	private static final long serialVersionUID = 8468488001028184160L;

	/**
	 * @param message
	 */
	public LoginException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public LoginException(String message, Throwable e) {
		super(message, e);
	}

}
