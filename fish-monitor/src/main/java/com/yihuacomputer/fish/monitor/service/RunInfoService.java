package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.monitor.entity.business.RunInfo;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class RunInfoService implements IRunInfoService {

    @Autowired
    private IGenericDao dao;

    @Override
    public IRunInfo make() {
        return new RunInfo();
    }

    @Override
    public void save(IRunInfo runInfo) {
         this.dao.save(runInfo);
    }

    @Override
    public List<IRunInfo> list() {
        return list(new Filter());
    }

    @Override
    public List<IRunInfo> list(IFilter filter) {
        return dao.findByFilter(filter, IRunInfo.class);
    }

    @Override
    @SuppressWarnings("unchecked")
	public IPageResult<IRunInfo> page(int offet,int limit,IFilter filter){
    	StringBuffer hql = new StringBuffer();

    	hql.append(" select ri from RunInfo ri,Device d where ri.terminalId = d.terminalId ");
    	hql.append(" order by  ri.terminalId,ri.statusTime");
    	return (IPageResult<IRunInfo>) dao.page(offet, limit, filter, hql.toString());
    }
}
