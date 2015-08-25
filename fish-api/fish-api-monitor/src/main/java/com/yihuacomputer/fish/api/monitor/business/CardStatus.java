package com.yihuacomputer.fish.api.monitor.business;

public enum CardStatus {

	/**
	 * 待领
	 */
	WAIT_RECEIVE(0,"待领"),

	/**
	 * 已领
	 */
	ALREADY_RECEIVE(1,"已领"),

	/**
	 * 销毁
	 */
	DESTORYED(2,"销毁"),

	/**
	 * 调出
	 */
	HAND_OVER(3,"调出"),


	/////////江苏农信专用///////////////
	/**
	 * 新吞卡
	 */
	NEW_CREATE(4, "新吞卡"),
	/**
	 * 保管
	 */
	SAVE(5, "保管"),
	/**
	 * 已登记
	 */
	REGISTER(6, "已登记");
	///////////////////////////////////

    private int id;
    private String text;

    private CardStatus(int id , String text){
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

    public static CardStatus getById(int id){
    	for(CardStatus each : CardStatus.values()){
    		if(each.getId() == id){
    			return each;
    		}
    	}
    	throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
