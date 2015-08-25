package com.yihuacomputer.fish.monitor.service.mem;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.mem.BaseMemoryService;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCard;
import com.yihuacomputer.fish.monitor.entity.business.BlackListCard;
import com.yihuacomputer.fish.monitor.service.base.DomainBlackListCardService;

@Service
public class BlackListCardService extends DomainBlackListCardService {

    private final BaseMemoryService memService = new BaseMemoryService();

    private final List<BlackListCard> data = new ArrayList<BlackListCard>();

    @Override
    public BlackListCard add(IBlackListCard entity) {
        BlackListCard result = memService.interface2Entity(entity);
        result.setId(memService.nextId());
        data.add(result);

        return result;
    }

    @Override
    public BlackListCard get(long id) {
        for (BlackListCard item : data) {
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
    public void remove(IBlackListCard blacklistcard) {
        remove(blacklistcard.getId());
    }

    @Override
    public Iterable<IBlackListCard> list() {
        return EntityUtils.<IBlackListCard> convert(data);
    }

    @Override
    public Iterable<IBlackListCard> list(IFilter filter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPageResult<IBlackListCard> page(int offset, int limit) {
        return EntityUtils
                .<IBlackListCard> convert(new PageResult<BlackListCard>(data,
                        offset, limit));
    }

    @Override
    public IPageResult<IBlackListCard> page(int offset, int limit,
            IFilter filter) {
        List<BlackListCard> result = memService.filter(data, filter);
        return EntityUtils
                .<IBlackListCard> convert(new PageResult<BlackListCard>(result,
                        offset, limit));
    }

    @Override
    public BlackListCard findByCardNoUserName(String cardNo, String userName) {
        for (BlackListCard entity : this.data) {
            if ((entity.getCardNo() != null && entity.getCardNo()
                    .equals(cardNo))
                    && (entity.getUserName() != null && entity.getUserName()
                            .equals(userName))) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public IBlackListCard findByCardNo(String cardNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void update(IBlackListCard entity) {
        // TODO Auto-generated method stub
        
    }

}
