package com.yihuacomputer.fish.api.person;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IRole;

public interface IUser {

	public void setId(long id);

	public long getId();

	public String getCode();

	public void setCode(String code);

	public IOrganization getOrganization();

	public IPerson getPerson();

	public void setPerson(IPerson person);

	public UserState getState();

	public void setState(UserState state);

	public String getName();

	public String getMobile();

	public String getPhone();

	public String getEmail();

	public String getPassword();

	public Date getAccessTime();

	public void setAccessTime(Date accessTime);

	public String getCheckRemark();

	public void setCheckRemark(String checkRemark);

	/**
	 * 获得密码的明文.
	 *
	 * @return 明文
	 */
	public String getPlainText();

	/**
	 * 设置密码的明文.
	 *
	 * @param plaintext
	 *            明文
	 */
	public void setPlainText(String plainText);

	/**
	 * 迁移到目标组织
	 *
	 * @param organization
	 *            组织
	 */
	public void moveTo(IOrganization organization);

	/**
	 * 是否为系统内置用户
	 *
	 * @return
	 *
	 */
	public boolean isSystem();

	/**
	 * 设置系统内置用户
	 *
	 * @param system
	 *
	 */
	public void setSystem(boolean system);

	/**
	 * 设置当前用户角色
	 *
	 * @param roles
	 */
	public void setRoles(List<IRole> roles);

	/**
	 * 获取当前用户角色
	 *
	 * @param roles
	 */
	public List<IRole> getRoles();
}
