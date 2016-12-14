package com.yihuacomputer.fish.api.advert;

/**
 * 广告有效期
 * 
 * @author xuxigang
 * 
 */
public enum AdvertValidity {
	/**
	 * 永久播放
	 */
	ALWAYS(0, "AdvertValidity.ALWAYS"),
	/**
	 * 临时
	 */
	TEMP(1, "AdvertValidity.TEMP");

	private int id;
	private String text;

	private AdvertValidity(int id, String text) {
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
