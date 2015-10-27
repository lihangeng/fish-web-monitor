package com.yihuacomputer.fish.api.atm;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IAtmModuleService {

	public IAtmModule make();

	public IAtmModule get(long id);

	public IAtmModule add(IAtmModule module);

	public void remove(long id);

	public void update(IAtmModule module);

	public List<IAtmModule> list();

	public IPageResult<IAtmModule> page(int offset, int limit,IFilter filter);

	public Iterable<IAtmModule> list(IFilter filter);

	public Map<String,IAtmModule> getModules();

	public void init();
}
