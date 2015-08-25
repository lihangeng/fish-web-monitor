package com.yihuacomputer.fish.version.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskDetail;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.version.entity.TaskDetail;
import com.yihuacomputer.fish.version.service.api.IDomainTaskDetailService;

@Service
@Transactional
public class TaskDetailService implements IDomainTaskDetailService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private ITaskService taskService;

    @Override
    public ITaskDetail make(long taskId) {
        return make(taskService.get(taskId));
    }

    @Override
    public ITaskDetail make(ITask task) {
        ITaskDetail td = new TaskDetail();
        td.setTaskId(task.getId());
        td.setTaskAction(task.getStatus().name());
        td.setReason(task.getReason());
        return td;
    }

    public ITaskDetail add(ITaskDetail td) {
        return dao.save(td);
    }

    public void deleteByTaskId(long taskId) {
        dao.batchUpdate("delete TaskDetail t where t.taskId = ?", taskId);
    }

    @Override
    public List<ITaskDetail> findByTaskId(long taskId) {
        return dao.findByHQL("from TaskDetail t where t.taskId = ?", taskId);
    }

}
