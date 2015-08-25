package com.yihuacomputer.fish.api.fault;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IFaultClassifyService {

	public IFaultClassify make();
	
	public void save(IFaultClassify faultClassify);
	
	/**
	 * 通过ID查找故障分类.
	 * @param classifyId
	 * @return
	 */
	public IFaultClassify getFaultClassifyById(String classifyId);
	
	/**
	 * 修改故障分类信息.
	 * @param faultClassify
	 */
	public void updateFaultClassify(IFaultClassify faultClassify);
	
	/**
	 * 列出所有故障分类信息.
	 * @return
	 */
	public List<IFaultClassify> listFaultClassify();
	
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IFaultClassify> page(int offset, int limit, IFilter filter);
	
}
