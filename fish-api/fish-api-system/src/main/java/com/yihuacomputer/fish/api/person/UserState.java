package com.yihuacomputer.fish.api.person;


public enum UserState {
	WAITCHECK(0,"待审核"),
	NEW(1,"新建"),
	NORMAL(2, "正常"),
	LOCK(3, "锁定"),//超过60天没有修改密码，会被锁定
	REMOVED(4, "已删除"),//假删除
	@Deprecated
	FREEZE(4, "冻结"),//用user.getFreezeTime()是否为null来判断用户是否被冻结
	NOTPASS(6,"审核未通过");

	private int id;
	private String text;

	private UserState(int id ,String text){
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

	public static UserState getById(int id){
		for(UserState each : UserState.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}

}
