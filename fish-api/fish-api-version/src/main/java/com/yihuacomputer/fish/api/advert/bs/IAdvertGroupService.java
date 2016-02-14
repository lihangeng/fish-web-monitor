package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAdvertGroupService {
	public IAdvertGroup make();
	
	public IAdvertGroup save(IAdvertGroup advertGroup);

	public IAdvertGroup update(IAdvertGroup advertGroup);
	
	public void deleteById(long id);
	
	/**
	 * 判断广告组中是否有被使用的资源，relation存在（正在使用组，将要使用组）,都不能进行删除
	 * @param advertGroup
	 */
	public void delete(IAdvertGroup advertGroup);
	
	public IAdvertGroup getById(long id);
	
	public IPageResult<Object> page(int offset, int limit, IFilter filter);
	
	public List<IAdvertGroup> list(IFilter filter);
	
	public IAdvertGroup orgHasAG(long orgId);
	
	public List<IAdvertGroup> list(long orgId);
}
