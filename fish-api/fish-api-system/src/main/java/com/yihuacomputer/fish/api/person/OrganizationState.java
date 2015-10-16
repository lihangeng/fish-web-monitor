package com.yihuacomputer.fish.api.person;


public enum OrganizationState {
	/**
	 * 新设立
	 */
	NEW(0,"OrganizationState.NEW"),
	/**
	 * 正在审批
	 */
	APPROVING(1, "OrganizationState.APPROVING"),
	/**
	 * 启用
	 */
	START(2, "OrganizationState.START"),//超过60天没有修改密码，会被锁定
	/**
	 * 停用申请
	 */
	STOP_APPLICATION(3, "OrganizationState.STOP_APPLICATION"),//假删除
	/**
	 * 停用
	 */
	STOP(4, "OrganizationState.STOP");
	
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
