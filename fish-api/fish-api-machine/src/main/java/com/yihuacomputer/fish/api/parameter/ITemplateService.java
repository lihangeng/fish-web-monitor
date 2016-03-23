package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;


public interface ITemplateService {
	public ITemplate make();

	public ITemplate get(long id);

	public ITemplate get(String name);

	public ITemplate add(ITemplate element);

	public void remove(long id);

	public void update(ITemplate element);

//	public Iterable<IElement> list();
//
	public IPageResult<ITemplate> page(int offset,int limit,IFilter filter);
//
//	public Iterable<IElement> list(IFilter filter);

}
