package com.yihuacomputer.fish.api.advert.bs;

/**
 * @author YiHua
 *
 */
public interface IAdvertGroup {
	/**
	 * @return
	 */
	public long getId();
	/**
	 * @param id
	 */
	public void setId(long id) ;
	
	/**
	 * @return
	 */
	public GroupType getGroupType();
	/**
	 * @param groupType
	 */
	public void setGroupType(GroupType groupType);
	
	/**
	 * @return
	 */
	public long getOrgId();
	/**
	 * @param orgId
	 */
	public void setOrgId(long orgId);

	/**
	 * @return
	 */
	public String getGroupName();
	/**
	 * @param groupName
	 */
	public void setGroupName(String groupName);
	
	/**
	 * 广告组资源路径
	 * @return
	 */
	public String getPath();
	/**
	 * @param path
	 */
	public void setPath(String path);
	
	/**
	 * @return
	 */
	public String getGuid();
}
