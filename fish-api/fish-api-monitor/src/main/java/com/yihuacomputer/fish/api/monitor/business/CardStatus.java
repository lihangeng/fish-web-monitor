package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public enum CardStatus {

	/**
	 * 待领
	 */
	WAIT_RECEIVE(0,"CardStatus.WAIT_RECEIVE"),

	/**
	 * 已领
	 */
	ALREADY_RECEIVE(1,"CardStatus.ALREADY_RECEIVE"),

	/**
	 * 销毁
	 */
	DESTORYED(2,"CardStatus.DESTORYED"),

	/**
	 * 调出
	 */
	HAND_OVER(3,"CardStatus.HAND_OVER"),


	/////////江苏农信专用///////////////
	/**
	 * 新吞卡
	 */
	NEW_CREATE(4, "CardStatus.NEW_CREATE"),
	/**
	 * 保管
	 */
	SAVE(5, "CardStatus.SAVE"),
	/**
	 * 已登记
	 */
	REGISTER(6, "CardStatus.REGISTER");
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

	public String getText() {
		return text;
	}

    /**
     * @param id
     * @return
     */
    public static CardStatus getById(int id){
    	for(CardStatus each : CardStatus.values()){
    		if(each.getId() == id){
    			return each;
    		}
    	}
    	throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
