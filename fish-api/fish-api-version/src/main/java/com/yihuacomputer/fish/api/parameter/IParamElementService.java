package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IParamElementService {
	public IParamElement make();

	public IParamElement get(long id);

	public IParamElement get(String name);

	public IParamElement add(IParamElement element);

	public IParamElement get(String name,long classifyId,long paramBelongsId);

	public void remove(long id);

	public void update(IParamElement element);

	public List<IParamElement> list();

	public List<IParamElement> listByClassify(IParamClassify paramClassify);

	public IPageResult<IParamElement> page(int offset, int limit, IFilter filter);

	public Iterable<IParamElement> list(IFilter filter);

	public void save(IParamElement paramElement);

	public List<IParamElement> getByAppSystem(IAppSystem paramBelongs);

	/**
	 * 得到参数值以及参数归属
	 *
	 * @return
	 */
	public List<IParamElement> getValue();

}
