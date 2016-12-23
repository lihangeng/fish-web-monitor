package com.yihuacomputer.fish.api.monitor.business;

/**
 * @author YiHua
 *
 */
public enum CardRetainType {

	MANUAL_CARD(1, "CardRetainType.MANUAL_CARD"),

	AUTOMATIC_CARD(2, "CardRetainType.AUTOMATIC_CARD");

	private int id;

	private String text;

	private CardRetainType(int id, String text) {
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
	public static CardRetainType getById(int id){
		for(CardRetainType each : CardRetainType.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
