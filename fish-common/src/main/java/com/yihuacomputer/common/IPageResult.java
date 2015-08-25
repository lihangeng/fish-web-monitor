package com.yihuacomputer.common;

import java.util.List;

/**
 * 分页结果.
 * @author yantao
 *
 * @param <T> 分页操作泛型对象
 */
public interface IPageResult<T> {

	/**
	 * 获取总记录数.
	 * @return 总记录数
	 */
	public int getTotal();
	
	/**
	 * 获取当前页数据列表.
	 * @return 数据列表
	 */
	public List<T> list();
	
}
