package com.yihuacomputer.fish.api.advert;

/**
 * 广告下发方式
 * 
 * @author xuxigang
 * 
 */
public enum AdvertDownMethod {
	/**
	 * 追加
	 */
	APPEND(0, "AdvertDownMethod.APPEND"),
	/**
	 * 覆盖
	 */
	COVER(1, "AdvertDownMethod.COVER");

	private int id;
	private String text;

	private AdvertDownMethod(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

}
