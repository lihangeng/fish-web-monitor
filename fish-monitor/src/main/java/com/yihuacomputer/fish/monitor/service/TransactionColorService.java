package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.ITransactionColor;
import com.yihuacomputer.fish.api.monitor.business.ITransactionColorService;
import com.yihuacomputer.fish.monitor.entity.business.TransactionColor;

@Service
@Transactional
public class TransactionColorService implements ITransactionColorService {
	@Autowired
	private IGenericDao dao;

	@Override
	public ITransactionColor make() {
		return new TransactionColor();
	}

	@Override
	public ITransactionColor get(long id) {
		return dao.get(id, TransactionColor.class);
	}

	@Override
	public ITransactionColor get(String hostRet) {
		ITransactionColor color = (ITransactionColor) dao.getCriteria(TransactionColor.class)
				.add(Restrictions.eq("hostRet", hostRet)).uniqueResult();
		return color;
	}

	@Override
	public void add(ITransactionColor transactionColor) {
		dao.save(transactionColor);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, TransactionColor.class);
	}

	@Override
	public void remove(String hostRet) {
		remove(get(hostRet).getId());
	}

	@Override
	public void update(ITransactionColor transactionColor) {
		dao.update(transactionColor);
	}

	@Override
	public List<ITransactionColor> list() {
		return list(new Filter());
	}

	@Override
	public List<ITransactionColor> list(IFilter filter) {
		return EntityUtils.<ITransactionColor> convert(dao.findByFilter(filter, TransactionColor.class));
	}

	@Override
	public IPageResult<ITransactionColor> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, TransactionColor.class);
	}

    @Override
    public void batchRemove(String userName) {
        
        String hql = "DELETE FROM TransactionColor WHERE userName = ? ";
        
        dao.batchUpdate(hql, userName);
    }

}
