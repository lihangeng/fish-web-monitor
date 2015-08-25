package com.yihuacomputer.fish.atmlog.service.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.atmlog.IDayBackupLog;
import com.yihuacomputer.fish.api.atmlog.IDayBackupService;
import com.yihuacomputer.fish.atmlog.entity.DayBackupLog;

/**
 * 每日备份服务
 */
@Service
@Transactional
public class DayBackupService implements IDayBackupService {

    @Autowired
    private IGenericDao dao;

    @Override
    public void save(IDayBackupLog dayBackupLog) {
        this.dao.save(dayBackupLog);
    }

    @Override
    @Transactional(readOnly=true)
    public IDayBackupLog get(String date) {
        return this.dao.get(date, DayBackupLog.class);
    }

    @Override
    public IDayBackupLog make() {
        return new DayBackupLog();
    }

    @Override
    public void update(IDayBackupLog dayBackupLog) {
        this.dao.update(dayBackupLog);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly=true)
    public IPageResult<IDayBackupLog> pageList(int start, int limit, IFilter filter) {
    	StringBuffer hql = new StringBuffer();
    	hql.append(" from DayBackupLog dayBackupLog where 1=1 order by dayBackupLog.date desc ");
    	
//        List<IDayBackupLog> dayBackupLogs = dao.findByFilter(filter, IDayBackupLog.class);
//        return new PageResult<IDayBackupLog>(dayBackupLogs, start, limit);
    	return (IPageResult<IDayBackupLog>)dao.page(start, limit, filter, hql.toString());
    }

}
