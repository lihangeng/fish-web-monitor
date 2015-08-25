package com.yihuacomputer.fish.api.atm;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAtmTypeService {
	public IAtmType make();
	
	public IAtmType get(long id);
	
	public IAtmType get(String name);
	
	public IAtmType add(IAtmType type);
	
	public void remove(long id);
	
	public void update(IAtmType type);
	
	public List<IAtmType> list();
	
	public IPageResult<IAtmType> page(int offset, int limit,IFilter filter);
	
	public List<IAtmType> list(IFilter filter);
	
	public List<IAtmType> listByBrand(IAtmVendor vendor);
	
	/**
	 * 
	 */
	public List<String> getByVendor(long vendorId);
}
