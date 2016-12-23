/**
 * 
 */
package com.yihuacomputer.fish.api.advert;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 广告资源服务
 * 
 * @author xuxigang
 * 
 */
public interface IAdvertResourceService {
	/**
	 * @return
	 */
	public IAdvertResource make();

	/**
	 * @param id
	 * @return
	 */
	public IAdvertResource getById(long id);

	/**
	 * @param advert
	 * @return
	 */
	public IAdvertResource add(IAdvertResource advert);

	/**
	 * @param advert
	 */
	public void update(IAdvertResource advert);

	/**
	 * @param advert
	 */
	public void delete(IAdvertResource advert);

	/**
	 * @param id
	 */
	public void delete(long id);

	/**
	 * @param filter
	 * @return
	 */
	public List<IAdvertResource> list(IFilter filter);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAdvertResource> page(int offset, int limit, IFilter filter);

}
