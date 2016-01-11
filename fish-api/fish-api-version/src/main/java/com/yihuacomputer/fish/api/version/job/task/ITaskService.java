package com.yihuacomputer.fish.api.version.job.task;

import java.util.Date;
import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;

/**
 * 任务服务
 *
 * @author xuxigang
 *
 */
public interface ITaskService {
	/**
	 * 创建任务
	 * @return
	 */
	public ITask make(Date firstCreateDate);

	/**
	 * 根据设备Id查找任务列表
	 * @param deviceId 设备ID
	 * @return
	 */
	public List<ITask> findTasks(long deviceId);
	
	/**
	 * 根据计划执行时间获取任务列表
	 * @param planDate　计划执行时间
	 * @return
	 * @since 2.0
	 */
	public List<ITask> findTasks(Date planDate);

	public List<ITask> findTasksByJobId(long jobId);
	/**
	 * 根据设备ID和版本ID查找唯一的任务
	 * @param deviceId
	 * @param versionId
	 * @return 没有找到返回null
	 */
	public ITask findTask(long deviceId,long versionId);

	/**
	 * 根据设备ID，查找失败的任务
	 * @param deviceId
	 * @return
	 */
//	public List<ITask> findFailTasks(long deviceId);

	/**
	 * 增加任务
	 * @param task
	 * @return
	 */
	public ITask addTask(ITask task);

	public void updateTask(ITask task);

	public void removeTask(ITask task);

	public List<ITask> list(IFilter filter);

	public IPageResult<ITask> page(int start, int limit, IFilter filter);

    public List<ITask> findTask(long deviceId, String typeName, String versionNo);

    public ITask get(long taskId);
    /**
     * 获得自动更新的任务列表
     * @param start
     * @param limit
     * @param filter
     * @return
     * @since 0.17
     */
    public IPageResult<ITask> pageAutoUpdateTasks(int start,int limit,IFilter filter);
    /**
     * 重新分发任务
     * @param taskId
     * @since 0.22
     */
    public void reDistribute(long taskId);

    /**
     * 深度取消任务
     * 只能是应用才可以执行此动作
     * 已经部署完成的不能再取消
     * @param taskId
     */
    public void deepCancelApp(long taskId);

    /**
     * 重新通知修改时间
     * @param updateDeployDateHisotryId
     */
    public void reNoticeApp(long updateDeployDateHisotryId);

    /**
     * 通知下发
     * @param task
     * @return true 通知被忽略
     */
    public boolean noticeATM(ITask task);
    
    public List<ITask> findTaskByDeviceIdAndVersionId(long deviceId,long versionId);
    
    public void cancelTask(ITask task);

    public void updateTaskStatus(ITask task);

    public void onlyUpdateTask(ITask task);
    
    /**
     * 获取自动升级任务列表
     * @param filter
     * @return
     */
    public IPageResult<AutoUpdateTaskForm> pageAutoUpdateTask(int start,int limit,IFilter filter);
	public IPageResult<ITask> export( IFilter filter) ;
	
	/**
	 * 网页上重置任务状态(标记为部署失败)
	 * @param taskId
	 * @return
	 */
	public ITask webResetTaskStatus(long taskId);
	
	/**
	 * 根据批次取消任务,如果有任务被取消反回true，否则返回false
	 * @param batchName
	 * @param versionId
	 * @return
	 */
	public boolean cancelTasks(String batchName,long versionId);
}
