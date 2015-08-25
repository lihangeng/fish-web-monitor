package com.yihuacomputer.fish.api.advert;

/**
 * 广告下发方式
 * 
 * @author xuxigang
 * 
 */
public enum AdvertDownMethod {
	APPEND(0, "追加"),
	COVER(1, "覆盖");

	private int id;
	private String text;

	private AdvertDownMethod(int id, String text) {
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
