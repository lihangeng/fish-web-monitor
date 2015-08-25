package com.yihuacomputer.fish.api.advert;

/**
 * 广告类型
 * 
 * @author xuxigang
 * 
 */
public enum AdvertType {
	TRANS(0, "交易页面广告"),
	WAIT_INSERT_CARD(1, "等待插卡广告"),
	TEXT(2, "文字滚动广告"),
	ANNOUNCEMENT(3,"公告");

	private int id;
	private String text;

	private AdvertType(int id, String text) {
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
	
	public static boolean isWords(AdvertType type){
	    if(type.equals(TEXT) || type.equals(ANNOUNCEMENT)){
	        return true;
	    }
	    return false;
	}

}
