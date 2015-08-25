package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAtmBrandService {
	public IAtmVendor make();
	
	public IAtmVendor get(long id);	
	
	public IAtmVendor add(IAtmVendor brand);
	
	public void remove(long id);
	
	public IAtmVendor get(String name);	
	
	public void update(IAtmVendor brand);
	
	public Iterable<IAtmVendor> list();
	
	public IPageResult<IAtmVendor> page(int offset, int limit,IFilter filter);
	
	public Iterable<IAtmVendor> list(IFilter filter);
}
