package com.yihuacomputer.fish.api.system.config;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

public interface IParamService {

	/**
	 * 保存参数.
	 * 
	 * @param parm
	 */
	public IParam add(IParam param);

	/**
	 * 快捷的创建并保存参数
	 * 
	 * @param name
	 * @param value
	 * @param description
	 * @return
	 */
	public IParam add(String name, String value, String description);

	/**
	 * 获取参数.
	 * 
	 * @param parmKey
	 * @return
	 */
	public IParam getParam(String paramKey);

	/**
	 * 初始化装载参数到FishCfg中.
	 */
	public void init();
	/**
	 * 重新加载参数到FishCfg中
	 */
	public void reload();

	/**
	 * 创建配置信息
	 * 
	 * @return
	 */
	public IParam make();

	/**
	 * 通过id获取参数信息
	 * 
	 * @param id
	 * @return
	 */
	public IParam get(long id);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void remove(long id);

	/**
	 * 根据paramKey删除
	 * 
	 * @param paramKey
	 */
	public void remove(String paramKey);

	/**
	 * 更新配置参数
	 * 
	 * @param param
	 */
	public void update(IParam param);

	/**
	 * 列出配置参数
	 * 
	 * @return
	 */
	public Iterable<IParam> list();

	/**
	 * 根据条件返回参数列表
	 * 
	 * @param filter
	 * @return
	 */
	public Iterable<IParam> list(IFilter filter);

	/**
	 * 分页
	 * 
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IParam> page(int offset, int limit, IFilter filter);

}
