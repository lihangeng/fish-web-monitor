package com.yihuacomputer.fish.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.system.im.IAnnouncement;
import com.yihuacomputer.fish.api.system.im.IAnnouncementService;
import com.yihuacomputer.fish.system.entity.Announcement;

/**
 * 数据库版AnnouncementService实现：
 * 
 * @author shixiaolong
 * 
 */
@Service
@Transactional
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    private IGenericDao dao;
    
    @Override
	public Announcement make(){
		Announcement ann = new Announcement(this);
		return ann;
	}

    @Override
    public Announcement add(IAnnouncement entity) {
        return dao.save(interface2Entity(entity, false));
    }

    @Override
    public Announcement get(long id) {
        return dao.get(id, Announcement.class);
    }

    @Override
    public void remove(long id) {
        dao.delete(id, Announcement.class);
    }

    @Override
    public void remove(IAnnouncement announcement) {
        remove(announcement.getId());
    }

    @Override
    public void update(IAnnouncement announcement) {
        this.dao.saveOrUpdate((Announcement) announcement);
    }

    @Override
    public Iterable<IAnnouncement> list() {
        return dao.loadAll(IAnnouncement.class);
    }

    /**
     * 根据过滤条件分页显示公告信息
     */
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IAnnouncement> page(int offset, int limit) {
        return page(offset, limit, new Filter());
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IAnnouncement> page(int offset, int limit, IFilter filter) {
        return dao.page(offset, limit, filter, Announcement.class);
    }

    /**
     * 保证公告信息准确
     * 
     * @param person
     * @param load
     * @return
     */
    private Announcement interface2Entity(IAnnouncement entity, boolean load) {
        if (entity instanceof Announcement) {
            return (Announcement) entity;
        }
        return null;
    }

}
