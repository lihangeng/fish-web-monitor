package com.yihuacomputer.fish.api.version.job.task;

import java.util.List;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
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
    public ITask make();

    public ITask make(IDevice device);

    public ITask make(long deviceId);
    
    public IDeviceService getDeviceService();

    public void removeTasks(IJob job);

    public void cancelTask(ITask task);

    public void updateTaskStatus(ITask task);

    public void onlyUpdateTask(ITask task);

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
     * 根据设备ID，查找失败的任务
     *
     * @param deviceId
     * @return
     */
    // public List<ITask> findFailTasks(long deviceId);

    public ITask addTask(ITask task);

    public void updateTask(ITask task);

    public void removeTask(ITask task);

    public List<ITask> list(IFilter filter);

    public IPageResult<ITask> page(int start, int limit, IFilter filter);

    public List<ITask> findTask(long deviceId, String typeName, String versionNo);

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
     * @param list
     * @return
     */
    public List<ITask> findTaskCount(TaskType taskType);

    /**
     * 根据机构条件查询任务列表
     * @param orgFlag
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

    public IPageResult<Object> pageForRepeat(int start, int limit, IFilter filter);

    public List<Object> findTerminalForRepeat(long jobId) ;
    
    /**
     * 运行中卡死，手工重置
     * @param task
     */
    public void resetTask(ITask task);
    /**
     * 运行中卡死，手工重置
     * @param task
     */
    public void resetTasks(List<ITask> tasks);
}
