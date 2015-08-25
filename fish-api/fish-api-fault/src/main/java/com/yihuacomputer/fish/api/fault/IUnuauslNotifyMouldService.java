package com.yihuacomputer.fish.api.fault;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IUnuauslNotifyMouldService {

	public IUnusualNotifyMould make();
	
	public void save(IUnusualNotifyMould unusualNotifyMould);
	
	/**
	 * 设置异常交易模板. 
	 * @param unusualNotifyMould
	 */
	public void updateUnuauslNotifyMould(IUnusualNotifyMould unusualNotifyMould);
	
	/**
	 * 列出所有异常交易信息.
	 * @return
	 */
	public List<IUnusualNotifyMould> listUnusualNotifyMould();
		
	/**
	 * 分页显示
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IUnusualNotifyMould> page(int offset, int limit, IFilter filter);
	
	/**
	 * 根据id查找记录
	 * @param id
	 * @return
	 */
	public IUnusualNotifyMould getUnusualNotifyMouldById(long id);
}
