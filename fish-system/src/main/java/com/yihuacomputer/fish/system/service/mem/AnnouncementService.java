package com.yihuacomputer.fish.system.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.system.im.IAnnouncement;
import com.yihuacomputer.fish.system.entity.Announcement;
import com.yihuacomputer.fish.system.service.base.DomainAnnouncementService;

@Service
public class AnnouncementService extends DomainAnnouncementService {

    private final BaseMemoryService memService = new BaseMemoryService();

    private final List<Announcement> data = new ArrayList<Announcement>();

    @Override
    public Announcement add(IAnnouncement entity) {
        Announcement result = memService.interface2Entity(entity);
        result.setId(memService.nextId());
        data.add(result);

        return result;
    }

    @Override
    public Announcement get(long id) {
        for (Announcement item : data) {
            if (id == item.getId()) {
                return item;
            }
        }
        throw new NotFoundException("entity not found");
    }

    @Override
    public void remove(long id) {
        data.remove(get(id));
    }

    @Override
    public void remove(IAnnouncement announcement) {
        remove(announcement.getId());
    }

    @SuppressWarnings("unused")
	@Override
    public void update(IAnnouncement announcement) {
        for (Announcement item : data) {
            if (announcement.getId() == item.getId()) {
            	item.setAttachment(announcement.getAttachment());
            	item.setContent(announcement.getContent());
            	item.setEndDate(announcement.getEndDate());
            	item.setGuid(announcement.getGuid());
            	item.setPublishDate(announcement.getPublishDate());
            	item.setPublisher(announcement.getPublisher());
            	item.setTheme(announcement.getTheme());
            	break;
            }
        }

        Announcement o = get(announcement.getId());
        // o.copyProperties(announcement);
    }

    @Override
    public Iterable<IAnnouncement> list() {
        return EntityUtils.<IAnnouncement> convert(data);
    }

    @Override
    public IPageResult<IAnnouncement> page(int offset, int limit) {
        return EntityUtils
                .<IAnnouncement> convert(new PageResult<Announcement>(data,
                        offset, limit));
    }

    @Override
    public IPageResult<IAnnouncement> page(int offset, int limit, IFilter filter) {
        List<Announcement> result = memService.filter(data, filter);
        return EntityUtils
                .<IAnnouncement> convert(new PageResult<Announcement>(result,
                        offset, limit));
    }

}
