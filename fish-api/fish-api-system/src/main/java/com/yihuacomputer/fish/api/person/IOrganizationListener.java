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


	public void beforeAdd(IOrganization organization);

	public void afterAdd(IOrganization organization);

	public void beforeDelete(IOrganization organization);

	public void afterDelete(IOrganization organization);

}
