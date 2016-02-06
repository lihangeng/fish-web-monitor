package com.yihuacomputer.fish.api.advert.bs;

public enum BsAdvertStatus {
	//未激活
	UNACTIVE(0,"BsAdvertStatus.UNACTIVE"),
	/**
	 * 更改未激活
	 */
	UPDATEUNACTIVE(1,"BsAdvertStatus.UPDATEUNACTIVE"),
	/**
	 *已激活 
	 */
	ACTIVED(2,"BsAdvertStatus.ACTIVED");
	private int id;
	private String name;
	private BsAdvertStatus(int id,String name){
		this.id = id;
		this.name = name;
	}
	public static BsAdvertStatus getBsAdvertStatusById(int id){
		BsAdvertStatus defaultStatus = BsAdvertStatus.UNACTIVE;
		BsAdvertStatus[] advertStatuss = BsAdvertStatus.values();
		for(BsAdvertStatus advertStatus:advertStatuss){
			if(advertStatus.getId()==id){
				defaultStatus = advertStatus;
			}
		}
		return defaultStatus;
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
