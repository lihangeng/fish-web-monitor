package com.yihuacomputer.fish.monitor.service;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCard;
import com.yihuacomputer.fish.api.monitor.business.IBlackListCardService;
import com.yihuacomputer.fish.monitor.entity.business.BlackListCard;

/**
 * 数据库版BlackListCardService实现：
 *
 * @author shixiaolong
 *
 */
@Service
@Transactional
public class BlackListCardService implements IBlackListCardService {

    @Autowired
    private IGenericDao dao;
    
    @Override
	public BlackListCard make(){
		return new BlackListCard();
	}

    @Override
    public BlackListCard add(IBlackListCard entity) {
        return dao.save(interface2Entity(entity, false));
    }

    @Override
    public BlackListCard get(long id) {
        return dao.get(id, BlackListCard.class);
    }

    @Override
    public void remove(long id) {
        dao.delete(id, BlackListCard.class);
    }

    @Override
    public void remove(IBlackListCard blacklistcard) {
        remove(blacklistcard.getId());
    }

    @Override
    public Iterable<IBlackListCard> list() {
        return dao.loadAll(IBlackListCard.class);
    }

    @Override
    public Iterable<IBlackListCard> list(IFilter filter) {
        return dao.findByFilter(filter, IBlackListCard.class);
    }

    /**
     * 根据过滤条件分页显示黑名单卡信息
     */
    @Override
    @Transactional(readOnly = true)
    public IPageResult<IBlackListCard> page(int offset, int limit) {
        return page(offset, limit, new Filter());
    }

    @Override
    @Transactional(readOnly = true)
    public IPageResult<IBlackListCard> page(int offset, int limit,
            IFilter filter) {
        return dao.page(offset, limit, filter, BlackListCard.class);
    }

    /**
     * 根据交易卡号，用户姓名获得黑名单卡信息
     */
    @Override
    public BlackListCard findByCardNoUserName(String cardNo, String userName) {
        BlackListCard blackListCard = (BlackListCard) dao
                .getCriteria(BlackListCard.class)
                .add(Restrictions.eq("cardNo", cardNo))
                .add(Restrictions.eq("userName", userName)).uniqueResult();
        return blackListCard;
    }

    /**
     * 保证黑名单卡信息准确
     *
     * @param person
     * @param load
     * @return
     */
    private BlackListCard interface2Entity(IBlackListCard entity, boolean load) {
        if (entity instanceof BlackListCard) {
            return (BlackListCard) entity;
        }
        return null;
    }

    @Override
    public IBlackListCard findByCardNo(String cardNo) {
        IFilter filter = new Filter();
        filter.eq("cardNo", cardNo);
      return dao.findUniqueByFilter(filter,BlackListCard.class);
        /*
        BlackListCard blackListCard = (BlackListCard) dao
        .getCriteria(BlackListCard.class)
        .add(Restrictions.eq("cardNo", cardNo));
        return blackListCard;*/
    }

    @Override
    public void update(IBlackListCard entity) {
        this.dao.update(entity);
    }

}
