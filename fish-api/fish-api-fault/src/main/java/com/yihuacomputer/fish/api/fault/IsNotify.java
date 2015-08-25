package com.yihuacomputer.fish.api.fault;

public enum IsNotify {

	YES(1,"是"),
	
	NO(2,"否");
	
	private int id;
	
	private String text;
	
	private IsNotify(int id, String text) {
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
	public static IsNotify parsGender(String isNotify){
		if(isNotify.equals("是")||isNotify.equals("YES")){
			return IsNotify.YES;
		}else{
			return IsNotify.NO;
		}
	}
}
