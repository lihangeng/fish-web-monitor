package com.yihuacomputer.fish.api.version.job;

import java.util.Date;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.fish.api.version.job.task.ITask;

public interface IUpdateDeployDateHistoryService {
    public IUpdateDeployDateHistory make(ITask task);

    public void add(IUpdateDeployDateHistory history);

    public void update(IUpdateDeployDateHistory histroy);

    public IUpdateDeployDateHistory getByTaskIdAndDeployDate(long taskId,Date deployStartDate);

    public IUpdateDeployDateHistory getById(long updateDeployDateHistoryId);

    public IPageResult<IUpdateDeployDateHistory> page(int start, int offset, IFilter filter);

    public IPageResult<IUpdateDeployDateHistory> page(int start, int offset, long jobId,Date deployStartDate,IFilter filter);
}
