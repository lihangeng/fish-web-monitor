package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IParamClassifyService {

	/**
	 * @return
	 */
	public IParamClassify make();

	/**
	 * @param id
	 * @return
	 */
	public IParamClassify get(long id);

	/**
	 * @param name
	 * @return
	 */
	public IParamClassify get(String name);

	/**
	 * @param classify
	 * @return
	 */
	public IParamClassify add(IParamClassify classify);

	/**
	 * @param id
	 */
	public void remove(long id);

	/**
	 * @return
	 */
	public Iterable<IParamClassify> list();

	/**
	 * @param classify
	 */
	public void update(IParamClassify classify);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IParamClassify> page(int offset, int limit, IFilter filter);

}
