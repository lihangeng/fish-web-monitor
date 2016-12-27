package com.yihuacomputer.fish.api.version.job.task;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.charts.ChartsInfo;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.version.VersionCatalog;
import com.yihuacomputer.fish.api.version.job.IJob;

/**
 * 任务服务
 *
 * @author xuxigang
 *
 */
public interface ITaskService {
    /**
     * @return
     */
    public ITask make();

    /**
     * @param device
     * @return
     */
    public ITask make(IDevice device);

    /**
     * @param deviceId
     * @return
     */
    public ITask make(long deviceId);
    
    /**
     * @return
     */
    public IDeviceService getDeviceService();

    /**
     * @param job
     */
    public void removeTasks(IJob job);

    /**
     * @param task
     */
    public void cancelTask(ITask task);

    /**
     * @param task
     */
    public void updateTaskStatus(ITask task);

    /**
     * @param task
     */
    public void onlyUpdateTask(ITask task);

    /**
     * @param tasks
     */
    public void cancelTasks(List<ITask> tasks);
    /**
     * 根据设备和软件分类创建任务
     *
     * @param deviceId
     * @param versionTypeName
     *            软件分类，用于记录升级前的版本
     * @return
     */
    public ITask make(long deviceId, String versionTypeName);

    /**
     * @param device
     * @param versionTypeName
     * @return
     */
    public ITask make(IDevice device, String versionTypeName);

    /*	*//**
     * 根据设备ID和版本号创建唯一的任务
     *
     * @param deviceId
     * @param versionId
     * @return
     */
    /*
     * public ITask make(long deviceId,long versionId);
     */
    /**
     * 根据设备Id查找任务列表
     *
     * @param deviceId
     *            设备ID
     * @return
     */
    public List<ITask> findTasks(long deviceId);

    /**
     * @param jobId
     * @return
     */
    public List<ITask> findTasksByJobId(long jobId);

    /**
     * 根据设备ID和版本ID查找唯一的任务
     *
     * @param deviceId
     * @param versionId
     * @return 没有找到返回null
     */
    public ITask findTask(long deviceId, long versionId);

    /**
     * @param task
     * @return
     */
    public ITask addTask(ITask task);

    /**
     * @param task
     */
    public void updateTask(ITask task);

    /**
     * @param task
     */
    public void removeTask(ITask task);

    /**
     * @param filter
     * @return
     */
    public List<ITask> list(IFilter filter);

    /**
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<ITask> page(int start, int limit, IFilter filter);

    /**
     * @param deviceId
     * @param typeName
     * @param versionNo
     * @return
     */
    public List<ITask> findTask(long deviceId, String typeName, String versionNo);

    /**
     * @param taskId
     * @return
     */
    public ITask get(long taskId);

    /**
     * 获得自动更新的任务列表
     *
     * @param start
     * @param limit
     * @param filter
     * @return
     * @since 0.17
     */
    public IPageResult<ITask> pageAutoUpdateTasks(int start, int limit, IFilter filter);

    /**
     * 重新分发任务
     *
     * @param taskId
     * @since 0.22
     */
    public void reDistribute(long taskId);

    /**
     * 深度取消任务 只能是应用才可以执行此动作 已经部署完成的不能再取消
     *
     * @param taskId
     */
    public void deepCancelApp(long taskId);

    /**
     * 重新通知修改时间
     *
     * @param updateDeployDateHisotryId
     */
    public void reNoticeApp(long updateDeployDateHisotryId);

    /**
     * 通知下发
     *
     * @param task
     * @return true 通知被忽略
     */
    public boolean noticeATM(ITask task);

    /**
     * @param deviceId
     * @param versionId
     * @return
     */
    public List<ITask> findTaskByDeviceIdAndVersionId(long deviceId, long versionId);

    /**
     * 跟新版本文件下载进度
     *
     * @param taskId
     * @param process
     */
    public void collectTaskReport(long taskId, double process);

    /**
     * 获取下发任务
     *
     * @param taskType
     * @return
     */
    public List<ITask> findTaskCount(TaskType taskType);

    /**
     * 根据机构条件查询任务列表
     * @param filter
     * @return
     */
    public List<ITask> findOrg(IFilter filter);

   /**
    * 根据设备ID查询task
    * @param deviceId
    * @param versionCatalog
    * @param statusList
    * @return
    */
    public List<ITask> findTaskByDeviceId(long deviceId,VersionCatalog versionCatalog,List<TaskStatus> statusList) ;

    /**
     * @param start
     * @param limit
     * @param filter
     * @return
     */
    public IPageResult<Object> pageForRepeat(int start, int limit, IFilter filter);

    /**
     * @param jobId
     * @return
     */
    public List<Object> findTerminalForRepeat(long jobId) ;
    
    /**
     * 运行中卡死，手工重置
     * @param task
     */
    public void resetTask(ITask task);
    /**
     * 运行中卡死，手工重置
     * @param tasks
     */
    public void resetTasks(List<ITask> tasks);
    
    /**
     * 当前作业己完成下载任务的平均用时
     * @param jobId
     * @return 毫秒 -1代表没有完成下载的Task
     */
    public long getDownloadTimeAvg(long jobId);

	/**
	 * 当前作业每种状态下任务数量
	 * @param jobId
	 * @return
	 */
	public List<ChartsInfo> listTaskGroupbyTaskStatus(long jobId);
}
