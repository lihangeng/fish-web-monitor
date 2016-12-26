package com.yihuacomputer.fish.api.atm;

import java.util.List;
import java.util.Map;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAtmModuleService {

	/**
	 * @return
	 */
	public IAtmModule make();

	/**
	 * @param id
	 * @return
	 */
	public IAtmModule get(long id);

	/**
	 * @param module
	 * @return
	 */
	public IAtmModule add(IAtmModule module);

	/**
	 * @param id
	 */
	public void remove(long id);

	/**
	 * @param module
	 */
	public void update(IAtmModule module);

	/**
	 * @return
	 */
	public List<IAtmModule> list();

	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IAtmModule> page(int offset, int limit,IFilter filter);

	/**
	 * @param filter
	 * @return
	 */
	public Iterable<IAtmModule> list(IFilter filter);

	/**
	 * @return
	 */
	public Map<String,IAtmModule> getModules();

	/**
	 * 初始化
	 */
	public void init();
}
