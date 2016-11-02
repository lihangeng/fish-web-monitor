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
	
	public void setParentId(String parentId);
	
	public String getParentId();
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
	
	/**
	 * 获得视图名称
	 * @return
	 * @since 2.1.0.6
	 */
	public String getViewName();
	public void   setViewName(String viewName);
	
	/**
	 * 是否是叶子节点
	 * @return
	 */
    public boolean isLeaf();

	public void setLeaf(boolean isLeaf);
	
	/**
	 * 设置图标样式
	 * @return
	 */
	public String getIconCls();
	public void   setIconCls(String iconCls);
	
	/**
	 * 设置路由名称
	 * @return
	 */
	public String getRouteId();
	public void   setRouteId(String routeId);
	
	/**
	 * 选中样式
	 * @return
	 */
	public String getSelectCls();
	public void setSelectCls(String selectCls);
	
	/**
	 * 是否需要像树上的节点一样进行渲染(单机模式不需要再树上渲染)
	 * @return
	 */
	public boolean isTreeNode();
	public void setTreeNode(boolean isTreeNode);
}
