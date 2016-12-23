package com.yihuacomputer.fish.api.advert.bs;

/**
 * @author YiHua
 *
 */
public enum BsAdvertStatus {
	/**
	 * 未激活
	 */
	UNACTIVE(0,"BsAdvertStatus.UNACTIVE"),
	/**
	 *已激活 
	 */
	ACTIVED(1,"BsAdvertStatus.ACTIVED");
	private int id;
	private String name;
	private BsAdvertStatus(int id,String name){
		this.id = id;
		this.name = name;
	}
	/**
	 * @param id
	 * @return
	 */
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
	public String getName() {
		return name;
	}
}
