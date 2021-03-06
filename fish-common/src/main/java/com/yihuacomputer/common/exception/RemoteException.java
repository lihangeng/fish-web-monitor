package com.yihuacomputer.common.exception;


/**
 * 远程异常
 *
 */
public class RemoteException extends AppException {

	private static final long serialVersionUID = -5199099412099958127L;

	/**
	 * @param message
	 */
	public RemoteException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public RemoteException(String message, Throwable e) {
		super(message, e);
	}

}
