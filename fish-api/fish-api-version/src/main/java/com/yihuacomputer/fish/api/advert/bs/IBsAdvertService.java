package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IBsAdvertService {
//	 *删除注意事项 判断广告组中是否有被使用的资源，relation存在（正在使用广告，将要使用广告）,都不能进行删除
	public IBsAdvert make();
	
	public IBsAdvert save(IBsAdvert advertGroup);

	public IBsAdvert update(IBsAdvert advertGroup);
	
	public void deleteById(long id);
	
	/**
	 * 判断广告组中是否有被使用的资源，relation存在（正在使用组，将要使用组）,都不能进行删除
	 * @param advertGroup
	 */
	public void delete(IBsAdvert advertGroup);
	
	public IBsAdvert getById(long id);
	
	public IPageResult<IBsAdvert> page(int offset, int limit, IFilter filter);
	
	public List<IBsAdvert> list(IFilter filter);
}