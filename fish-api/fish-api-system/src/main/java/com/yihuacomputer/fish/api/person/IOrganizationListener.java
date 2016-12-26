/**
 *
 */
package com.yihuacomputer.fish.api.person;


/**
 * 组织的操作监听器
 * @author xuxiang
 *
 */
public interface IOrganizationListener {


	/**
	 * @param organization
	 */
	public void beforeAdd(IOrganization organization);

	/**
	 * @param organization
	 */
	public void afterAdd(IOrganization organization);

	/**
	 * @param organization
	 */
	public void beforeDelete(IOrganization organization);

	/**
	 * @param organization
	 */
	public void afterDelete(IOrganization organization);

}
