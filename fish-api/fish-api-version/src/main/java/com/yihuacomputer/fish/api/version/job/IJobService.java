/**
 *
 */
package com.yihuacomputer.fish.api.version.job;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.version.IVersion;

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
	 * 增加一个作业信息同时保存任务列表信息
	 * @param job
	 * @return
	 */
	public IJob cascadeAdd(IJob job);

	/**
	 * 修改一个作业信息
	 * @param job
	 */
	public void onlyUpdateJob(IJob job);
	/**
	 * 删除一个指定的作业信息，同时删除作业下的任务信息
	 * @param job
	 */
	public void cascadeDelete(IJob job);
	/**
	 * 根据作业ID删除一个作业信息，同时删除作业下的任务信息
	 * @param id
	 */
	public void cascadeDelete(long id);

	public List<IJob> list(IFilter filter);

	/**
	 * 分页显示作业信息
	 * @param offset
	 * @param limit
	 * @param filter
	 * @return
	 */
	public IPageResult<IJob> page(int offset, int limit, IFilter filter);

	/**
	 * 修改部署时间（江苏农行的要求）
	 * 修改作业的部署生效时间，并且通知作业中还没有“部署确认”的任务
	 * 只用“应用”才有此功能，广告或者其它软件没有。
	 * @param jobId
	 * @param deployStartDate
	 * @param deployEndDate
	 * @since 0.22
	 */
	public void updateDeployDate(long jobId,Date deployStartDate,Date deployEndDate);


	/**
	 * 根据版本ID查询该版本的自动更新作业（江苏农信最新需求）
	 * @param version
	 * @return
	 */
	public IJob getAutoJobByVersionId(IVersion version) ;

	/**
	 * 根据作业ID查询该作业下重复任务数量
	 * @param jobId
	 * @return
	 */
	public int getRepeatTaskByJob(long jobId) ;

}
