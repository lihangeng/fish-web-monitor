package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistory;
import com.yihuacomputer.fish.api.version.job.IUpdateDeployDateHistoryService;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.version.entity.UpdateDeployDateHistory;

@Service
@Transactional
public class UpdateDeployDateHistoryService implements IUpdateDeployDateHistoryService{


    @Autowired
    private IGenericDao dao;

    @Override
    @Transactional(readOnly = true)
    public IUpdateDeployDateHistory make(ITask task) {
        IUpdateDeployDateHistory his = new UpdateDeployDateHistory(task);
        return his;
    }

    @Override
    public void add(IUpdateDeployDateHistory history) {
        dao.save(history);
    }

    @Override
    public void update(IUpdateDeployDateHistory histroy) {
       dao.update(histroy);
    }

    @Override
    @Transactional(readOnly = true)
    public IUpdateDeployDateHistory getByTaskIdAndDeployDate(long taskId, Date deployStartDate) {
        StringBuffer hql = new StringBuffer("from ");
        hql.append(UpdateDeployDateHistory.class.getName()).append(" t where t.taskId = ? and deployStartDate = ?");
        return dao.findUniqueByHql(hql.toString(), taskId , deployStartDate);
    }

    @SuppressWarnings("unchecked")
    @Override
    public IPageResult<IUpdateDeployDateHistory> page(int start, int limit, IFilter filter) {

        StringBuffer hql = new StringBuffer();
        hql.append("select updateDeployDateHistory, task, device from UpdateDeployDateHistory updateDeployDateHistory,Task task,Device device where task.deviceId = device.id  ");
        hql.append(" and updateDeployDateHistory.taskId = task.id ");

        hql.append(" order by updateDeployDateHistory.noticeTime DESC ");

        IPageResult<Object> result = (IPageResult<Object>) dao.page(start, limit,filter, hql.toString());

        List<IUpdateDeployDateHistory> listHistory = new ArrayList<IUpdateDeployDateHistory>();
        for (Object obj : result.list()) {

            Object[] objs = (Object[]) obj;

            IUpdateDeployDateHistory history = (IUpdateDeployDateHistory) objs[0];
            ITask task = (ITask) objs[1];
            IDevice device = (IDevice) objs[2];
            task.setDevice(device);
            history.setTask(task);

            listHistory.add(history);
        }

        PageResult<IUpdateDeployDateHistory> pageResult = new PageResult<IUpdateDeployDateHistory>();
        pageResult.setData(listHistory);
        pageResult.setTotal(result.getTotal());

        return pageResult;
    }

    @SuppressWarnings("deprecation")
	@Override
    public IPageResult<IUpdateDeployDateHistory> page(int start, int offset, long jobId, Date deployStartDate, IFilter filter) {
        if(filter == null){
            filter = new Filter();
        }
        filter.addFilterEntry(FilterFactory.eq("jobId", jobId));
        return page(start,offset,filter);
    }

    @Override
    public IUpdateDeployDateHistory getById(long updateDeployDateHistoryId) {
        return dao.get(updateDeployDateHistoryId, UpdateDeployDateHistory.class);
    }

}
