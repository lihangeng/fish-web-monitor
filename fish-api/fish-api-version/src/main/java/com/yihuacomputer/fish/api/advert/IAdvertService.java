/**
 * 
 */
package com.yihuacomputer.fish.api.advert;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 广告服务
 * 
 * @author xuxigang
 * 
 */
public interface IAdvertService {
	public IAdvert make(AdvertType advertType);

	public IAdvert getById(long id);

	public IAdvert add(IAdvert advert);

	public void update(IAdvert advert);

	public void delete(IAdvert advert);

	public void delete(long id);

	public List<IAdvert> list(IFilter filter);

	public IPageResult<IAdvert> page(int offset, int limit, IFilter filter);

}
