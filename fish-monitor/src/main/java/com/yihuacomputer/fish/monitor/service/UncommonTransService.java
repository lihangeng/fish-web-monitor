package com.yihuacomputer.fish.monitor.service;


import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTrans;
import com.yihuacomputer.fish.api.monitor.business.IUncommonTransService;
import com.yihuacomputer.fish.monitor.entity.business.UncommonTrans;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class UncommonTransService implements IUncommonTransService {
	
	 @Autowired
	 private IGenericDao dao;
	 
	@Autowired
	private MessageSource messageSource;


	@Override
	public IUncommonTrans make() {
		return new UncommonTrans();
	}

	@Override
	public void save(IUncommonTrans uncommonTrans) {
		this.dao.save(uncommonTrans);
		
	}

	@Override
	public IPageResult<IUncommonTrans> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, UncommonTrans.class);
	}

	@Override
	public IPageResult<IUncommonTrans> page(int offset, int limit,
			IFilter filter, long orgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IUncommonTrans get(long id) {
		return dao.get(id, UncommonTrans.class);
	}

	@Override
	public void update(IUncommonTrans trans) {
		dao.update(interface2Entity(trans,true));
		
	}
	
	private UncommonTrans interface2Entity(IUncommonTrans trans, boolean load) {
		if (trans instanceof UncommonTrans) {
			return (UncommonTrans) trans;
		}
		return null;
	}

	@Override
	public IUncommonTrans get(String transId) {
		UncommonTrans trans = (UncommonTrans) dao.getCriteria(UncommonTrans.class)
                .add(Restrictions.eq("transId", transId)).uniqueResult();
        if (trans == null) {
            throw new NotFoundException(String.format(messageSource.getMessage("exception.uncommonTrans.noSerialno", null, FishCfg.locale), transId));
        }
		return trans;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult<Object> pageObj(int offset, int limit, IFilter filter) {

		String hql = " select uncommonTrans, device from UncommonTrans uncommonTrans, Device device where uncommonTrans.terminalId = device.terminalId  order by uncommonTrans.dateTime desc";

		return (PageResult<Object>) dao.page(offset, limit, filter, hql);
	}

}
