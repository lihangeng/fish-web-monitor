package com.yihuacomputer.fish.api.person;

public enum Gender {

	/**
	 * 男
	 */
	MALE(0,"Gender.MALE"),
	
	/**
	 * 女
	 */
	FEMALE(1,"Gender.FEMALE");
	
	private int id;
	
	private String text;
	
	private Gender(int id, String text) {
		this.id = id;
		this.text = text;
	}
	public int getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public static Gender parsGender(String gender){
		if(gender.equals("男")||gender.equals("MALE")){
			return Gender.MALE;
		}else{
			return Gender.FEMALE;
		}
	}
}
