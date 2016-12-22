package com.yihuacomputer.common.exception;

/**
 * 连接异常
 *
 */
public class ConnectionException extends RemoteException {

	private static final long serialVersionUID = -8640171588708190622L;

	/**
	 * @param message
	 */
	public ConnectionException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public ConnectionException(String message, Throwable e) {
		super(message, e);
	}

}
