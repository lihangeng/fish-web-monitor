package com.yihuacomputer.fish.api.parameter;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IParamClassifyService {

	public IParamClassify make();

	public IParamClassify get(long id);

	public IParamClassify get(String name);

	public IParamClassify add(IParamClassify classify);

	public void remove(long id);

	public Iterable<IParamClassify> list();

	public void update(IParamClassify classify);

	public IPageResult<IParamClassify> page(int offset, int limit, IFilter filter);

}
