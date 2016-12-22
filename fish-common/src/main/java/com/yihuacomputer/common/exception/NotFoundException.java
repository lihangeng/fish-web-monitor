package com.yihuacomputer.common.exception;

/**
 * 记录不存在异常.
 * @author yantao
 *
 */
public class NotFoundException extends ServiceException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public NotFoundException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public NotFoundException(String message, Throwable e) {
		super(message, e);
	}

}
