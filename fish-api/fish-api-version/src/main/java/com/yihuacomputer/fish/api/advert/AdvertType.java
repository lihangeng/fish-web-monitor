package com.yihuacomputer.fish.api.advert;

/**
 * 广告类型
 * 
 * @author xuxigang
 * 
 */
public enum AdvertType {
	/**
	 * 交易页面广告
	 */
	TRANS(0, "AdvertType.TRANS"),
	/**
	 * 等待插卡广告
	 */
	WAIT_INSERT_CARD(1, "AdvertType.WAIT_INSERT_CARD"),
	/**
	 * 文字滚动广告
	 */
	TEXT(2, "AdvertType.TEXT"),
	/**
	 * 公告
	 */
	ANNOUNCEMENT(3,"AdvertType.ANNOUNCEMENT");

	private int id;
	private String text;

	private AdvertType(int id, String text) {
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	/**判断广告类型是否为文字
	 * @param type
	 * @return
	 */
	public static boolean isWords(AdvertType type){
	    if(type.equals(TEXT) || type.equals(ANNOUNCEMENT)){
	        return true;
	    }
	    return false;
	}

}
