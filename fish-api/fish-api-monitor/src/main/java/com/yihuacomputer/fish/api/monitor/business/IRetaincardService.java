package com.yihuacomputer.fish.api.monitor.business;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * C端应用吞卡通知服务：
 * 
 * @author huxiaobao
 * 
 */
public interface IRetaincardService {

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void remove(long id);

	/**
	 * 通过Id获取吞卡信息
	 * 
	 * @param id
	 * @return
	 */
	public IRetaincard get(long id);

	/**
	 * 更新吞卡信息
	 * 
	 * @param retaincard
	 */
	public void update(IRetaincard retaincard);

	/**
	 * 创建吞卡信息
	 * 
	 * @return
	 */
	public IRetaincard make();

	/**
	 * 分页
	 * 
	 * @param offset
	 * @param limit
	 * @param filter
	 *            条件
	 * @param orgId
	 * @return
	 */
	public IPageResult<IRetaincard> page(int offset, int limit, IFilter filter,long orgId);

	/**
	 * 列出吞卡信息
	 * 
	 * @return
	 */
	public Iterable<IRetaincard> list();

	/**
	 * 根据过滤条件列出吞卡信息
	 * 
	 * @param filter
	 * @return
	 */
	public Iterable<IRetaincard> list(IFilter filter);

	/**
	 * 增加一条吞卡信息
	 * 
	 * @param retaincard
	 * @return
	 */
	public IRetaincard add(IRetaincard retaincard);
	
	/**
	 * 
	 * @param terminalId
	 * @return
	 */
	public List<IRetaincard> getCardByTerminalId(String terminalId);

	/**
	 * @param orgId
	 * @param filter
	 * @return
	 */
	public List<IRetaincard> listCardByOrgId(long orgId,IFilter filter);
	
	/**
	 * @param offset
	 * @param limit
	 * @param filter
	 * @param orgId
	 * @param status1
	 * @param status2
	 * @return
	 */
	public IPageResult<IRetaincard> page(int offset, int limit, IFilter filter,long orgId,CardStatus status1,CardStatus status2);
	
	 /**
	 * @param days
	 * @return
	 */
	public List<Object> statisticsReatainCardTrend(int days);
	 
	 /**
	  * 根据设备号查找近一个周的吞卡统计信息
	 * @param terminalId
	 * @return
	 */
	public List<Object> statisticsReatainCardTrendByTerminalId(String terminalId);
}
