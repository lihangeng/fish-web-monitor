package com.yihuacomputer.fish.api.parameter;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IAppSystemService {
	
	/**
	 * @param id
	 * @return
	 */
	IAppSystem get(long id);

	/**
	 * @param appSystem
	 */
	void update(IAppSystem appSystem);

	/**
	 * @param start
	 * @param limit
	 * @param filter
	 * @return
	 */
	IPageResult<IAppSystem> page(int start, int limit, IFilter filter);

	/**
	 * @return
	 */
	List<IAppSystem> list();

	/**
	 * @param filter
	 * @return
	 */
	List<IAppSystem> listContainsApp(IFilter filter);

	/**
	 * 得到参数应用分类
	 *
	 * @return
	 */
	List<IAppSystem> getBelongs();
	
	/**
	 * 根据应用唯一名称获取应用
	 * @param name
	 * @return
	 */
	public IAppSystem get(String name);
}
