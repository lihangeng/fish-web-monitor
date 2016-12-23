package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IBsAdvertResourceService {

	/**
	 * @return
	 */
	public IBsAdvertResource make();

	/**
	 * @param advert
	 * @return
	 */
	public IBsAdvertResource save(IBsAdvertResource advert);

	/**
	 * @param advert
	 * @return
	 */
	public IBsAdvertResource update(IBsAdvertResource advert);

	/**
	 * @param id
	 */
	public void deleteById(long id);

	/**
	 * @param advert
	 */
	public void delete(IBsAdvertResource advert);

	/**
	 * @param id
	 * @return
	 */
	public IBsAdvertResource getById(long id);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IBsAdvertResource> page(int offset, int limit, IFilter filter);
	/**
	 * @param filter
	 * @return
	 */
	public List<IBsAdvertResource> list(IFilter filter);
	/**
	 * @param advert
	 * @param fileName
	 */
	public void delete(IBsAdvertResource advert,String fileName);
}
