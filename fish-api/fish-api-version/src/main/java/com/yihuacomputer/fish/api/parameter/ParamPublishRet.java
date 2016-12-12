package com.yihuacomputer.fish.api.parameter;

public enum ParamPublishRet {
	CHECKED("00"), 
	/**
	 *新建 
	 */
	NEW("01"), 
	/**
	 * 已通知客户端
	 */
	NOTICED("02"), 
	/**
	 * 通知客户端失败
	 */
	NOTICED_FAILER("03"), 
	/**
	 * 下载完毕
	 */
	DOWNLOADED("04"), 
	/**
	 * 下载失败
	 */
	DOWNLOAD_FAILER("05"), 
	/**
	 * 部署失败
	 */
	DEPLOYE_FAILER("07"),
	/**
	 * 部署
	 */
	DEPLOYED("08");

	private String code;

	private ParamPublishRet(String code) {
		this.code = code;
	}

	public String getText() {
		return code;
	}
	public static ParamPublishRet getById(String code) {
		for (ParamPublishRet each : ParamPublishRet.values()) {
			if (each.code == code) {
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%s] error", code));
	}
}
