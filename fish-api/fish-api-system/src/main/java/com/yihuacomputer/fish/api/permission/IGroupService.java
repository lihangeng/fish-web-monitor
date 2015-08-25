package com.yihuacomputer.fish.api.permission;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/** 
 * @author 樊晓雨
 * @version
 * @date 2010-8-18
 */
public interface IGroupService {
	/**
	 * 创建组织实例
	 * @param name
	 * @return
	 */
    public IGroup make(String name);
    
    public IGroup get(long id);
    /**
     * 根据name获取组织实例
     * @param name
     * @return
     */
    public IGroup get(String name);
    /**
     * 增加组织信息
     * @param name
     * @return
     */
    public IGroup add(String name);
    /**
     * 增加组织信息
     * @param group
     * @return
     */
	public IGroup add(IGroup group);
	/**
	 * 删除
	 * @param id
	 */
	public void remove(long id);
	/**
	 * 根据name删除组织
	 * @param name
	 */
	public void remove(String name);
	/**
	 * 更新组织信息
	 * @param group
	 */
	public void update(IGroup group);
	
	public List<IGroup> list();
	/**
	 * 根据条件列出所有的组织
	 * @param filter
	 * @return
	 */
	public List<IGroup> list(IFilter filter);
	/**
	 * 分页
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IGroup> page(int offset, int limit, IFilter filter);
	
}
