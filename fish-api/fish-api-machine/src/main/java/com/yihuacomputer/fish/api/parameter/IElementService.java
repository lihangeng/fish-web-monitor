package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IElementService {
	public IElement make();

	public IElement get(long id);

	public IElement get(String name);

	public IElement add(IElement element);

	public void remove(long id);

	public void update(IElement element);

	public Iterable<IElement> list();

	public IPageResult<IElement> page(int offset,int limit,IFilter filter);

	public Iterable<IElement> list(IFilter filter);

}
