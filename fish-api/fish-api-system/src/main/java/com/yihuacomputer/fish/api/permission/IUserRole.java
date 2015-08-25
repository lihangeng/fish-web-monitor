package com.yihuacomputer.fish.api.permission;


/**
 * 用户角色表
 * 
 * @version 0.3
 * @since 0.1
 * @author yantao
 *
 */
public interface IUserRole  {
	/**
	 * 返回ID
	 * @param system
	 * 
	 */
	public Long getId() ;
	/**
	 * 设置ID
	 * @param system
	 * 
	 */
	public void setId(Long id) ;

	/**
	 * 返回userId
	 * @param system
	 * 
	 */
	public Long getUserId() ;
	/**
	 * 设置userId
	 * @param system
	 * 
	 */
	public void setUserId(Long userId);
	/**
	 * 返回roleCode
	 * @param system
	 * 
	 */
	public String getRoleCode() ;
	/**
	 * 设置roleCode
	 * @param system
	 * 
	 */
	public void setRoleCode(String roleCode) ;
	
}
