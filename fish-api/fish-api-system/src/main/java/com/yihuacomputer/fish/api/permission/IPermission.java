package com.yihuacomputer.fish.api.permission;

import java.util.List;

public interface IPermission {
	
	public String getId();
	
	public void setId(String id);
	/**
	 * 返回编号
	 * @return
	 */
	public String getCode();
	/**
	 * 设置编号
	 * @param code
	 */
	public void setCode(String code);
	/**
	 * 获取菜单动作
	 * @return
	 */
	public String getAction();
	/**
	 * 
	 * @param action
	 */
	public void setAction(String action);
	/**
	 * 返回描述
	 * @return
	 */
	public String getDescription();
	/**
	 * 设置描述
	 * @param description
	 */
	public void setDescription(String description);

	/**
	 * 返回权限
	 * @return
	 */
	public IPermission getParent();
	/**
	 * 
	 * @param permission
	 */
	public void setParent(IPermission permission);
	/**
	 * 根据code返回权限
	 * @param code
	 * @return
	 */
	public IPermission getChild(String code);
	/**
	 * 增加权限
	 * @param code
	 * @param description
	 * @return
	 */
	public IPermission addChild(String id,String code, String description,boolean isButton);
	
	public void removeChild(String code);
	/**
	 * 返回权限列表
	 * @return
	 */
	public List<IPermission>  listChildren();
	
	public boolean isButton();

	public void setButton(boolean isButton);

}
