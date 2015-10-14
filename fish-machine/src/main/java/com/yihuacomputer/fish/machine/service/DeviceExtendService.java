package com.yihuacomputer.fish.machine.service;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDeviceExtend;
import com.yihuacomputer.fish.api.device.IDeviceExtendService;
import com.yihuacomputer.fish.machine.entity.DeviceExtend;

/**
 * @author 彭文超
 * @E-mail pengwenchao@yihuacomputer
 * @date 2012-2-22 下午04:07:39
 * @version 类说明
 */
@Service
@Transactional
public class DeviceExtendService implements IDeviceExtendService {
    @Autowired
    private IGenericDao dao;

    @Override
    public IDeviceExtend make()
    {
        return new DeviceExtend();
    }
    
    @Override
    @Transactional(readOnly = true)
    public IDeviceExtend get(long id) {
        return dao.get(id, DeviceExtend.class);
    }

    @Override
    @Transactional(readOnly = true)
    public IDeviceExtend get(String code) {
        IDeviceExtend deviceExtend = (IDeviceExtend) dao.getCriteria(DeviceExtend.class)
                .add(Restrictions.eq("terminalId", code)).uniqueResult();
        return deviceExtend;
    }

    @Override
    public IDeviceExtend add(IDeviceExtend deviceExtend) {
        return dao.save(deviceExtend);
    }

    @Override
    public void remove(long id) {
        dao.delete(id, DeviceExtend.class);
    }

    @Override
    public void remove(String code) {
        dao.delete(this.get(code).getId(), DeviceExtend.class);
    }

    @Override
    public void update(IDeviceExtend deviceExtend) {
        dao.update(deviceExtend);
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<IDeviceExtend> list() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IDeviceExtend> page(int offset, int limit, IFilter filter) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<IDeviceExtend> list(IFilter filter) {
        return null;
    }
}
