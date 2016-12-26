package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IBsAdvertService {
//	 *删除注意事项 判断广告组中是否有被使用的资源，relation存在（正在使用广告，将要使用广告）,都不能进行删除
	/**
	 * @return
	 */
	public IBsAdvert make();
	
	/**
	 * @param advertGroup
	 * @return
	 */
	public IBsAdvert save(IBsAdvert advertGroup);

	/**
	 * @param advertGroup
	 * @return
	 */
	public IBsAdvert update(IBsAdvert advertGroup);
	
	/**
	 * @param id
	 */
	public void deleteById(long id);
	
	/**
	 * 判断广告组中是否有被使用的资源，relation存在（正在使用组，将要使用组）,都不能进行删除
	 * @param advertGroup
	 */
	public void delete(IBsAdvert advertGroup);
	
	/**
	 * @param id
	 * @return
	 */
	public IBsAdvert getById(long id);
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<Object> page(int offset, int limit, IFilter filter);
	
	/**
	 * @param filter
	 * @return
	 */
	public List<IBsAdvert> list(IFilter filter);
	
	/**
	 * @return
	 */
	public IBsAdvertResourceService getBsAdvertResourceService();
	
	/**
	 * 激活广告
	 * @param bsAdvert
	 * @return
	 */
	public IBsAdvert actived(IBsAdvert bsAdvert);
	
	/**
	 * 根据机构和名称查找广告信息
	 * @param orgId
	 * @param advertName
	 * @param advertId
	 * @return
	 */
	public List<IBsAdvert> getBsAdvertByNameAndOrgId(long orgId,String advertName,long advertId);
}
