package com.yihuacomputer.common.exception;

/**
 * 记录重复异常.
 * @author yantao
 *
 */
public class DuplicateException extends AppException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public DuplicateException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public DuplicateException(String message, Throwable e) {
		super(message, e);
	}

}
