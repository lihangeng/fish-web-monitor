package com.yihuacomputer.common.exception;

/**
 * 服务层的异常
 * @author xuxigang
 * @version
 * @since
 * @date 2010-8-7
 */
public class ServiceException extends AppException{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public ServiceException(String message, Throwable e) {
		super(message, e);
	}

}
