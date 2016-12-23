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
	/**
	 * @param advertType
	 * @return
	 */
	public IAdvert make(AdvertType advertType);

	/**
	 * @param id
	 * @return
	 */
	public IAdvert getById(long id);

	/**
	 * @param advert
	 * @return
	 */
	public IAdvert add(IAdvert advert);

	/**
	 * @param advert
	 */
	public void update(IAdvert advert);

	/**
	 * @param advert
	 */
	public void delete(IAdvert advert);

	/**
	 * @param id
	 */
	public void delete(long id);

	/**
	 * @param filter
	 * @return
	 */
	public List<IAdvert> list(IFilter filter);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAdvert> page(int offset, int limit, IFilter filter);

}
