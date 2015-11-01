package com.yihuacomputer.fish.api.device;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface ITempDevInfoService {
	
	
    public ITempDevInfo make();
	/*
	 * 通过id得到未生效设备信息表
	 */
	public ITempDevInfo get(long id);	
	
	/*
	 * 通过设备编号得到未生效设备信息表
	 */
	public ITempDevInfo get(String code);
	
	/*
	 * 通过条件分页查询
	 */
	public IPageResult<ITempDevInfo> page(int offset, int limit, IFilter filter, String orgId);
	
	
	/*
	 * 添加未生效设备信息
	 */
	public ITempDevInfo add(ITempDevInfo device);
	
	
	/*
	 * 根据条件得到未生效设备信息集合
	 */	
	public List<ITempDevInfo> list(IFilter filter);
	
	
	
	/*
	 * 根据id删除未生效设备记录
	 */
	public void remove(long id);
	
	
	/*
	 * 根据code删除未生效设备记录
	 */
	public void remove(String code);
	
	
	/*
	 * 更新某一设备信息记录
	 */
	public void update(ITempDevInfo device);
	
	
	/*
	 * 根据hql语句混合查询
	 */
	public List<ITempDevInfo> list(String hql, List<Object> fixedFilters);
	
}
