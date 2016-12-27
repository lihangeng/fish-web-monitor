package com.yihuacomputer.fish.api.version.job;

import java.util.Date;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.version.job.task.ITask;

/**
 * @author YiHua
 *
 */
public interface IUpdateDeployDateHistoryService {
    /**
     * @param task
     * @return
     */
    public IUpdateDeployDateHistory make(ITask task);

    /**
     * @param history
     */
    public void add(IUpdateDeployDateHistory history);

    /**
     * @param histroy
     */
    public void update(IUpdateDeployDateHistory histroy);

    /**
     * @param taskId
     * @param deployStartDate
     * @return
     */
    public IUpdateDeployDateHistory getByTaskIdAndDeployDate(long taskId,Date deployStartDate);

    /**
     * @param updateDeployDateHistoryId
     * @return
     */
    public IUpdateDeployDateHistory getById(long updateDeployDateHistoryId);

    /**
     * @param start
     * @param offset
     * @param filter
     * @return
     */
    public IPageResult<IUpdateDeployDateHistory> page(int start, int offset, IFilter filter);

    /**
     * @param start
     * @param offset
     * @param jobId
     * @param deployStartDate
     * @param filter
     * @return
     */
    public IPageResult<IUpdateDeployDateHistory> page(int start, int offset, long jobId,Date deployStartDate,IFilter filter);
}
