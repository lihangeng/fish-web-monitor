package com.yihuacomputer.fish.api.permission;


public enum RoleType {

	PERMISSION(1, "总行角色"),
	
	SLAVE_USE(2, "分行角色"),
	
	RESOURCE(3, "支行角色"),
	
	INNER(4, "网点角色");
	
	private int id;
	private String text;
	
	private RoleType(int id ,String text){
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
	
	public boolean isEntityRole(RoleType type){
		if(type.getId() == 1 || type.getId() == 3){
			return true;
		}
		return false;
	}
	
	public boolean isInnerRole(RoleType type){
		if(type.getId() == 4){
			return true;
		}
		return false;
	}
	
	public static RoleType getById(int id){
		for(RoleType each : RoleType.values()){
			if(each.getId() == id){
				return each;
			}
		}
		throw new IllegalArgumentException(String.format("id=[%d] error", id));
	}
	
	public static RoleType getById(String id){
		return getById(Integer.parseInt(id));
	}
	
}
