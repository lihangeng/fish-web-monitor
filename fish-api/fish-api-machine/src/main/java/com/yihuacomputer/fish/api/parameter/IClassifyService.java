package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IClassifyService {

	public IClassify make();

	public IClassify get(long id);

	public IClassify get(String name);

	public IClassify add(IClassify classify);

	public void remove(long id);

	public Iterable<IClassify> list();

	public void update(IClassify classify);

	public IPageResult<IClassify> page(int offset, int limit, IFilter filter);

}
