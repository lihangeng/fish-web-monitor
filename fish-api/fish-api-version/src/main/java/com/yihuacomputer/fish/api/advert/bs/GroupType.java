package com.yihuacomputer.fish.api.advert.bs;

/**
 * @author YiHua
 *
 */
public enum GroupType {
	NORMAl(0,"GroupType.NORMAL"),
	DEFAULT(1,"GroupType.DEFAULT");
	private int id;
	private String name;
	private GroupType(int id,String name){
		this.id = id;
		this.name = name;
	}
	/**
	 * @param id
	 * @return
	 */
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
	public String getName() {
		return name;
	}
	
}
