package com.yihuacomputer.common.exception;

/**
 * 记录不存在异常.
 * @author yantao
 *
 */
public class IDNotExistException extends NotFoundException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public IDNotExistException(String message) {
		super(message);
	}

	public IDNotExistException(String message, Throwable e) {
		super(message, e);
	}

}
