package com.yihuacomputer.fish.api.person;

public enum Gender {

	MALE(1,"男"),
	
	FEMALE(2,"女");
	
	private int id;
	
	private String text;
	
	private Gender(int id, String text) {
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
	public static Gender parsGender(String gender){
		if(gender.equals("男")||gender.equals("MALE")){
			return Gender.MALE;
		}else{
			return Gender.FEMALE;
		}
	}
}
