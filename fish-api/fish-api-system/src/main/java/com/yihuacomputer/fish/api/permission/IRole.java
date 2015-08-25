package com.yihuacomputer.fish.api.permission;

import java.util.List;

/**
 * 角色.
 * 包括实体级角色和实体内角色
 * @version 0.3
 * @since 0.1
 * @author yantao
 *
 */
public interface IRole extends Comparable<IRole> {
	
	/**
	 * 返回ldap中的code
	 * @return
	 */
	public String getCode();
	 /**
	  * ldap中的code
	  * 
	  */
	public void setCode(String code);

	/**
	 * 返回ID
	 * @param system
	 * 
	 */
	public long getId();
		
	/**
	 * 设置id
	 * @param system
	 * 
	 */
	public void setId(long id);
	/**
	 * 返回角色类型
	 * @return
	 */
	public RoleType getType();
	/**
	 * 角色类型
	 * @param type
	 */
	public void setType(RoleType type);
	/**
	 * 返回角色名称
	 * @return
	 */
	public String getName();
	 /**
	  * 角色名称
	  * @param Name
	  */
	public void setName(String name);
	/**
	 * 是否有继承关系
	 * @return
	 */
	public boolean getInherit();
	/**
	 * 添加继承关系 
	 * @param inherit
	 */
	public void setInherit(boolean inherit);
	/**
	 * 是否自动关联
	 * @return
	 */
	public boolean getAutoRelation();
	/**
	 * 设置关联
	 * @param auto
	 */
	public void setAutoRelation(boolean auto);
	/**
	 *  返回权限列表
	 * @return
	 */
	public List<IPermission> listPermission();
	/**
	 * 设置备注
	 * @param descrption
	 */
	public void setDescription(String descrption);
	/**
	 * 获得备注
	 * @return
	 */
	public String getDescription();
	
	/**
	 * 是否为系统内置的角色
	 * @return
	 * 
	 */	
	public boolean isSystem();
	/**
	 * 设置系统内置角色
	 * @param system
	 * 
	 */
	public void setSystem(boolean system);
	
	
	/**
	 * 返回唯一的成员
	 * @param system
	 * 
	 */
	public String getUniqueMember() ;
	
	/**
	 * 设置唯一的成员
	 * @param system
	 * 
	 */
	public void setUniqueMember(String uniqueMember);
	
	/**
	 * 返回组类型
	 * @param system
	 * 
	 */
	public String getGroupType();
	/**
	 * 设置组类型
	 * @param system
	 * 
	 */
	public void setGroupType(String groupType) ;

	/**
	 * 返回组唯一
	 * @param system
	 * 
	 */
	public String getGroupMember() ;
	/**
	 * 设置组唯一
	 * @param system
	 * 
	 */
	public void setGroupMember(String groupMember) ;
	
	/**
	 * 返回组规则
	 * @param system
	 * 
	 */
	public String getGroupRule();
	/**
	 * 设置组规则
	 * @param system
	 * 
	 */
	public void setGroupRule(String groupRule) ;

	
}
