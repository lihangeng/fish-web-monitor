package com.yihuacomputer.fish.api.atm;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAtmGroupService {
	public IAtmGroup make();

	public IAtmGroup get(long id);
	
	public IAtmGroup get(String name);

	public IAtmGroup add(IAtmGroup atmGroup);

	public void remove(long id);

	public void update(IAtmGroup atmGroup);

	public Iterable<IAtmGroup> list();

	public IPageResult<IAtmGroup> page(int offset, int limit, IFilter filter);

	public Iterable<IAtmGroup> list(IFilter filter);
}
