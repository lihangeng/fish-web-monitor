package com.yihuacomputer.fish.api.atm;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAtmTypeService {
	/**
	 * @return
	 */
	public IAtmType make();
	
	/**
	 * @param id
	 * @return
	 */
	public IAtmType get(long id);
	
	/**
	 * @param name
	 * @return
	 */
	public IAtmType get(String name);
	
	/**
	 * @param type
	 * @return
	 */
	public IAtmType add(IAtmType type);
	
	/**
	 * @param id
	 */
	public void remove(long id);
	
	/**
	 * @param type
	 */
	public void update(IAtmType type);
	
	/**
	 * @return
	 */
	public List<IAtmType> list();
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAtmType> page(int offset, int limit,IFilter filter);
	
	/**
	 * @param filter
	 * @return
	 */
	public List<IAtmType> list(IFilter filter);
	
	/**
	 * @param vendor
	 * @return
	 */
	public List<IAtmType> listByBrand(IAtmVendor vendor);
	
	/**
	 * @param vendorId
	 * @return
	 */
	public List<String> getByVendor(long vendorId);
}
