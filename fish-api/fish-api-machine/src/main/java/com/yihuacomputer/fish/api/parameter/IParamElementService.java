package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IParamElementService {
	public IParamElement make();

	public IParamElement get(long id);

	public IParamElement get(String name);

	public IParamElement add(IParamElement element);

	public void remove(long id);

	public void update(IParamElement element);

	public Iterable<IParamElement> list();

	public IPageResult<IParamElement> page(int offset, int limit, IFilter filter);

	public Iterable<IParamElement> list(IFilter filter);

	/**
	 * 得到参数值
	 * 
	 * @return
	 */
	public List<IParamElement> getValue();

}
