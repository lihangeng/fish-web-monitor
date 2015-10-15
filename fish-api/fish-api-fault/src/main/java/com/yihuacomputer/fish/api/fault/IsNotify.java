package com.yihuacomputer.fish.api.fault;


public enum IsNotify {

	YES(1,"IsNotify.YES"),
	
	NO(2,"IsNotify.NO");

    public String getText(){
		return text;
    }
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
