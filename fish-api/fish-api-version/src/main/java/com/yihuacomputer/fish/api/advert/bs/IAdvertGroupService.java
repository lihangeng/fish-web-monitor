package com.yihuacomputer.fish.api.advert.bs;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAdvertGroupService {
	/**
	 * @return
	 */
	public IAdvertGroup make();
	
	/**
	 * @param advertGroup
	 * @return
	 */
	public IAdvertGroup save(IAdvertGroup advertGroup);

	/**
	 * @param advertGroup
	 * @return
	 */
	public IAdvertGroup update(IAdvertGroup advertGroup);
	
	/**
	 * @param id
	 */
	public void deleteById(long id);
	
	/**
	 * 判断广告组中是否有被使用的资源，relation存在（正在使用组，将要使用组）,都不能进行删除
	 * @param advertGroup
	 */
	public void delete(IAdvertGroup advertGroup);
	
	/**
	 * @param id
	 * @return
	 */
	public IAdvertGroup getById(long id);
	
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
	public List<IAdvertGroup> list(IFilter filter);
	
	/**
	 * @param orgId
	 * @return
	 */
	public IAdvertGroup orgHasAG(long orgId);
	
	/**
	 * @param orgId
	 * @return
	 */
	public List<IAdvertGroup> list(long orgId);
	
	/**
	 * @param groupId
	 * @return
	 */
	public IBsAdvert getBsAdvertByGroupId(long groupId);
	
	/**
	 * @param orgId
	 * @param groupName
	 * @return
	 */
	public boolean dupGroupName(long orgId,String groupName);
	/**
	 * @param groupId
	 * @param orgId
	 * @param groupName
	 * @return
	 */
	public boolean isExist(long groupId,long orgId,String groupName);
}
