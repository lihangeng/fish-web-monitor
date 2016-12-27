package com.yihuacomputer.fish.api.report.batch;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * @author YiHua
 *
 */
public interface IETLjobService {

	/**
	 * 删除历史日报表数据
	 * @param tradeTime
	 */
	public void deteleDayOpera(String tradeTime);
	/**
	 * 删除历史月报表数据
	 * @param tradeTime
	 */
	public void deteleMonthOpera(String tradeTime);
	/**
	 * 删除历史月报表job相关数据
	 * @param tradeTime
	 */
	public void reStartMonthOpera(String tradeTime);
	/**
	 * 得到job错误提示
	 * @param id
	 * @return
	 */
	public String getErrorMsg(long id);
	
	/**
	 * 分页查询
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	
	public IPageResult<Object> listData(int offset,int limit,IFilter filter);
}
