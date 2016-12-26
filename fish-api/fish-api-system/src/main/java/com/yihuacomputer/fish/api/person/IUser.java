package com.yihuacomputer.fish.api.person;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.fish.api.permission.IRole;

/**
 * @author YiHua
 *
 */
public interface IUser {

	/**
	 * @param id
	 */
	public void setId(long id);

	/**
	 * @return
	 */
	public long getId();

	/**
	 * @return
	 */
	public String getCode();

	/**
	 * @param code
	 */
	public void setCode(String code);

	/**
	 * @return
	 */
	public IOrganization getOrganization();

	/**
	 * @return
	 */
	public IPerson getPerson();

	/**
	 * @param person
	 */
	public void setPerson(IPerson person);

	/**
	 * @return
	 */
	public UserState getState();

	/**
	 * @param state
	 */
	public void setState(UserState state);

	/**
	 * @return
	 */
	public String getName();

	/**
	 * @return
	 */
	public String getMobile();

	/**
	 * @return
	 */
	public String getPhone();

	/**
	 * @return
	 */
	public String getEmail();

	/**
	 * @return
	 */
	public String getPassword();

	/**
	 * @return
	 */
	public Date getAccessTime();

	/**
	 * @param accessTime
	 */
	public void setAccessTime(Date accessTime);

	/**
	 * @return
	 */
	public String getCheckRemark();

	/**
	 * @param checkRemark
	 */
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
	 * @param plainText
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
	 * @return
	 */
	public List<IRole> getRoles();
	
	/**
	 * 获取用户类型
	 * @return
	 */
	public UserType getUserType();
	/**
	 * 设置用户类型
	 * @param userType
	 */
	public void setUserType(UserType userType);
}
