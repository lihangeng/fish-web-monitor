package com.yihuacomputer.fish.api.advert;

/**
 * 广告有效期
 * 
 * @author xuxigang
 * 
 */
public enum AdvertValidity {
	ALWAYS(0, "永久播放"),
	TEMP(1, "临时");

	private int id;
	private String text;

	private AdvertValidity(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
