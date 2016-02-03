package com.yihuacomputer.fish.api.advert.bs;

public interface IAdvertGroup {
	public long getId();
	public void setId(long id) ;
	
	public GroupType getGroupType();
	public void setGroupType(GroupType groupType);
	
	public long getOrgId();
	public void setOrgId(long orgId);

	public String getGroupName();
	public void setGroupName(String groupName);
	
	/**
	 * 广告组资源路径
	 * @return
	 */
	public String getPath();
	public void setPath(String path);
	
	public String getGuid();
}
