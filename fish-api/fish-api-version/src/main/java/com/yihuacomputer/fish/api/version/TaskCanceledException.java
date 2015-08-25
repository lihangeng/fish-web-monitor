package com.yihuacomputer.fish.api.version;

import com.yihuacomputer.common.exception.AppException;

public class TaskCanceledException extends AppException {
	private static final long serialVersionUID = 1L;

	public TaskCanceledException(String message) {
		super(message);
	}

	public TaskCanceledException(String message, Throwable e) {
		super(message, e);
	}
}
