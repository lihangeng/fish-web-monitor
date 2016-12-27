package com.yihuacomputer.fish.api.version;

import com.yihuacomputer.common.exception.AppException;

/**
 * @author YiHua
 *
 */
public class TaskCanceledException extends AppException {
	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 */
	public TaskCanceledException(String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param e
	 */
	public TaskCanceledException(String message, Throwable e) {
		super(message, e);
	}
}
