package com.yihuacomputer.fish.api.person;

public enum UserType {
	/**
	 * 超级用户
	 */
	SUPERUSER(0,"UserType.SUPERUSER"),
	/**
	 * 普通用户
	 */
	NORMAlUSER(1,"UserType.NORMAlUSER");
	private int id;
	private String text;
	private UserType(int id,String text){
		this.id = id;
        this.text= text;
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
	public static UserType getById(int id){
        for(UserType each : UserType.values()){
            if(each.getId() == id){
                return each;
            }
        }
        throw new IllegalArgumentException(String.format("id=[%d] error", id));
    }
}
