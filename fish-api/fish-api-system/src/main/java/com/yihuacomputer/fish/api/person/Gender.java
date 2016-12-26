package com.yihuacomputer.fish.api.person;

/**
 * @author YiHua
 *
 */
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

	/**
	 * @param gender
	 * @return
	 */
	public static Gender parsGender(String gender){
		if("MALE".equals(gender)){
			return Gender.MALE;
		}else{
			return Gender.FEMALE;
		}
	}
}
