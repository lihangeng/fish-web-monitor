package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHist;
import com.yihuacomputer.fish.api.monitor.business.IRemoteCommHistService;
import com.yihuacomputer.fish.monitor.entity.business.RemoteCommHist;

/**
 * 远程集合历史服务接口
 * 
 * @author pengwenchao
 *
 */
@Service
@Transactional
public class RemoteCommHistService implements IRemoteCommHistService {

    @Autowired
    private IGenericDao dao;

    @Override
    public IRemoteCommHist make() {
        return new RemoteCommHist();
    }

    @Override
    public IRemoteCommHist get(long id) {
        return dao.get(id, RemoteCommHist.class);
    }

    @Override
    public void delete(long id) {
        dao.delete(id, RemoteCommHist.class);
    }

    @Override
    public void save(IRemoteCommHist remoteCommHist) {
        dao.save(remoteCommHist);
    }

    @Override
    public void update(IRemoteCommHist remoteCommHist) {
        dao.update(remoteCommHist);
    }

    @Override
    public List<IRemoteCommHist> list() {
        return list(new Filter());
    }

    @Override
    public List<IRemoteCommHist> list(IFilter filter) {
        return dao.findByFilter(filter, IRemoteCommHist.class);
    }

    @Override
    public IPageResult<IRemoteCommHist> page(int start, int limit, IFilter filter) {
        return dao.page(start, limit, filter, RemoteCommHist.class);
    }

    @Override
    public IPageResult<Object> pageObj(int start, int limit, IFilter filter) {

        String hql = "select d,r from Device d, RemoteCommHist r where d.terminalId=r.terminalId order by r.id desc ";

        return (IPageResult<Object>) dao.page(start, limit, filter, hql, (new ArrayList<Object>()).toArray());
    }
}
