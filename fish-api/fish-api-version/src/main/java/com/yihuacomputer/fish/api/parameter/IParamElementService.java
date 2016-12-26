package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IParamElementService {
	/**
	 * @return
	 */
	public IParamElement make();

	/**
	 * @param id
	 * @return
	 */
	public IParamElement get(long id);

	/**
	 * @param name
	 * @return
	 */
	public IParamElement get(String name);

	/**
	 * @param element
	 * @return
	 */
	public IParamElement add(IParamElement element);

	/**
	 * @param name
	 * @param classifyId
	 * @param paramBelongsId
	 * @return
	 */
	public IParamElement get(String name,long classifyId,long paramBelongsId);

	/**
	 * @param id
	 */
	public void remove(long id);

	/**
	 * @param element
	 */
	public void update(IParamElement element);

	/**
	 * @return
	 */
	public List<IParamElement> list();

	/**
	 * @param paramClassify
	 * @return
	 */
	public List<IParamElement> listByClassify(IParamClassify paramClassify);

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IParamElement> page(int offset, int limit, IFilter filter);

	/**
	 * @param filter
	 * @return
	 */
	public List<IParamElement> list(IFilter filter);

	/**
	 * @param paramElement
	 */
	public void save(IParamElement paramElement);

	/**
	 * @param paramBelongs
	 * @return
	 */
	public List<IParamElement> getByAppSystem(IAppSystem paramBelongs);

	/**
	 * 得到参数值以及参数归属
	 *
	 * @return
	 */
	public List<IParamElement> getValue();

}
