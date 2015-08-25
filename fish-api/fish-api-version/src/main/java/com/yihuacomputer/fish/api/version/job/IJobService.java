/**
 *
 */
package com.yihuacomputer.fish.api.version.job;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 作业服务，用于保存相关的作业信息
 *
 * @author xuxigang
 */
public interface IJobService {

	/**
	 * 创建一个job实例
	 * @return job实例
	 */
	public IJob make();

	/**
	 * 根据作业ID查找作业信息，没有找到抛出异常
	 * @param jobId 作业ID
	 * @return 作业
	 */
	public IJob getById(long jobId);

	/**
	 * 根据作业名称查找作业信息,没有找到抛出异常
	 * @param jobName 作业名称
	 * @return 作业
	 */
	public IJob getByName(String jobName);

	/**
	 * 增加一个作业信息
	 * @param job
	 * @return
	 */
	public IJob add(IJob job);

	/**
	 * 修改一个作业信息
	 * @param job
	 */
	public void update(IJob job);
	/**
	 * 删除一个指定的作业信息
	 * @param job
	 */
	public void delete(IJob job);

	public List<IJob> list(IFilter filter);

	/**
	 * 分页显示作业信息
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IJob> page(int offset, int limit, IFilter filter);

}
