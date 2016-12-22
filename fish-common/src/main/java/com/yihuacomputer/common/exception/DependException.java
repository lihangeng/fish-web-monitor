package com.yihuacomputer.common.exception;

/**
 * 记录不存在异常.
 * @author yantao
 *
 */
public class DependException extends AppException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public DependException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public DependException(String message, Throwable e) {
		super(message, e);
	}

}
