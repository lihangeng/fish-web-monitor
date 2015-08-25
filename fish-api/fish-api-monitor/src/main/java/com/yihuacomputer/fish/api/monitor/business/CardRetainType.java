package com.yihuacomputer.fish.api.monitor.business;

public enum CardRetainType {

	MANUAL_CARD(1, "手动添加"),

	AUTOMATIC_CARD(2, "自动添加");

	private int id;

	private String text;

	private CardRetainType(int id, String text) {
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
	
	public static CardRetainType getById(int id){
		for(CardRetainType each : CardRetainType.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
