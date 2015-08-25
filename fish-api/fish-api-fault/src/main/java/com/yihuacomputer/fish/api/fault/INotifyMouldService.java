package com.yihuacomputer.fish.api.fault;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface INotifyMouldService {

	public INotifyMould make();
	
	public void save(INotifyMould notifyMould);
	
	/**
	 * 设置故障工单通知模板. 
	 * @param notifyMould
	 */
	public void updateNotifyMould(INotifyMould notifyMould);
	
	/**
	 * 根据通知创建类型，通知方式获取通知模块信息.
	 * @param classifyName
	 * @param notifyType
	 * @param notifyWay
	 * @return
	 */
	public String getNotifyMould(String classifyName,NotifyType notifyType,NotifyWay notifyWay);
	
	/**
	 * 列出所有故障工单通知模板信息.
	 * @return
	 */
	public List<INotifyMould> listNotifyMould();
		
	/**
	 * 分页显示
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<INotifyMould> page(int offset, int limit, IFilter filter);
	
	/**
	 * 创建实体
	 * @return
	 */
	public INotifyMouldSet makeNotifySet();
	
	/**
	 * 根据id查找记录
	 * @param id
	 * @return
	 */
	public INotifyMould getNotifyMouldById(long id);
}
