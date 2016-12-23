package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAtmBrandService {
	/**
	 * @return
	 */
	public IAtmVendor make();
	
	/**
	 * @param id
	 * @return
	 */
	public IAtmVendor get(long id);	
	
	/**
	 * @param brand
	 * @return
	 */
	public IAtmVendor add(IAtmVendor brand);
	
	/**
	 * @param id
	 */
	public void remove(long id);
	
	/**
	 * @param name
	 * @return
	 */
	public IAtmVendor get(String name);	
	
	/**
	 * @param brand
	 */
	public void update(IAtmVendor brand);
	
	/**
	 * @return
	 */
	public Iterable<IAtmVendor> list();
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAtmVendor> page(int offset, int limit,IFilter filter);
	
	/**
	 * @param filter
	 * @return
	 */
	public Iterable<IAtmVendor> list(IFilter filter);
}
