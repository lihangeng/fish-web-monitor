package com.yihuacomputer.fish.api.advert.bs;

public enum GroupType {
	NORMAl(0,"GroupType.NORMAL"),
	DEFAULT(1,"GroupType.DEFAULT");
	private int id;
	private String name;
	private GroupType(int id,String name){
		this.id = id;
		this.name = name;
	}
	public static GroupType getGroupTypeById(int id){
		GroupType defaultType = GroupType.NORMAl;
		GroupType[] groupTypes = GroupType.values();
		for(GroupType groupType:groupTypes){
			if(groupType.getId()==id){
				defaultType = groupType;
			}
		}
		return defaultType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
