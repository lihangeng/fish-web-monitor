package com.yihuacomputer.fish.api.monitor.box;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface ICashInitPlanInfoService {
	
	/**
	 * @return
	 */
	public ICashInitPlanInfo make();
	/**
	 * @param cashInitPlanInfo
	 * @return
	 */
	public ICashInitPlanInfo save(ICashInitPlanInfo cashInitPlanInfo);
	/**
	 * @param cashInitPlanInfo
	 * @return
	 */
	public ICashInitPlanInfo update(ICashInitPlanInfo cashInitPlanInfo);
	/**
	 * @param cashInitPlanInfo
	 */
	public void remove(ICashInitPlanInfo cashInitPlanInfo);
	/**
	 * @param id
	 * @return
	 */
	public ICashInitPlanInfo get(long id);
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<ICashInitPlanInfo> page(int offset,int limit,IFilter filter);
	/**
	 * @param filter
	 * @return
	 */
	public List<ICashInitPlanInfo> list(IFilter filter);
}
