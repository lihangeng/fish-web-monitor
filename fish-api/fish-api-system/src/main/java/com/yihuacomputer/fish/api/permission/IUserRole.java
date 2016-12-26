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
	 * @return
	 * 
	 */
	public Long getId() ;
	/**
	 * 设置ID
	 * @param id
	 * 
	 */
	public void setId(Long id) ;

	/**
	 * 返回userId
	 * @return
	 * 
	 */
	public Long getUserId() ;
	/**
	 * 设置userId
	 * @param userId
	 * 
	 */
	public void setUserId(Long userId);
	/**
	 * 返回roleCode
	 * @return
	 * 
	 */
	public String getRoleCode() ;
	/**
	 * 设置roleCode
	 * @param roleCode
	 * 
	 */
	public void setRoleCode(String roleCode) ;
	
}
