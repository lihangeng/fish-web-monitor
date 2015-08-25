package com.yihuacomputer.fish.api.person;


public enum OrganizationState {
	NEW(0,"新设立"),
	APPROVING(1, "正在审批"),
	START(2, "启用"),//超过60天没有修改密码，会被锁定
	STOP_APPLICATION(3, "停用申请"),//假删除
	STOP(4, "停用");
	
	private int id;
	private String text;
	
	private OrganizationState(int id ,String text){
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
	
	public static OrganizationState getById(int id){
		for(OrganizationState each : OrganizationState.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
	
}
