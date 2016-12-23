package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAtmGroupService {
	/**
	 * @return
	 */
	public IAtmGroup make();

	/**
	 * @param id
	 * @return
	 */
	public IAtmGroup get(long id);
	
	/**
	 * @param name
	 * @return
	 */
	public IAtmGroup get(String name);

	/**
	 * @param atmGroup
	 * @return
	 */
	public IAtmGroup add(IAtmGroup atmGroup);

	/**
	 * @param id
	 */
	public void remove(long id);

	/**
	 * @param atmGroup
	 */
	public void update(IAtmGroup atmGroup);

	/**
	 * @return
	 */
	public Iterable<IAtmGroup> list();

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAtmGroup> page(int offset, int limit, IFilter filter);

	/**
	 * @param filter
	 * @return
	 */
	public Iterable<IAtmGroup> list(IFilter filter);
}
