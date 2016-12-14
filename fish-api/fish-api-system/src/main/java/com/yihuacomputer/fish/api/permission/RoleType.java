package com.yihuacomputer.fish.api.permission;


public enum RoleType {

	/**
	 * 总行角色
	 */
	PERMISSION(1, "RoleType.PERMISSION"),
	
	/**
	 * 分行角色
	 */
	SLAVE_USE(2, "RoleType.SLAVE_USE"),
	
	/**
	 * 支行角色
	 */
	RESOURCE(3, "RoleType.RESOURCE"),
	
	/**
	 * 网点角色
	 */
	INNER(4, "RoleType.INNER");
	
	private int id;
	private String text;
	
	private RoleType(int id ,String text){
		this.id = id;
		this.text= text;
	}
	
	public int getId() {
		return id;
	}

	public String getText() {
		return text;
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
