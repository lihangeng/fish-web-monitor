package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoney;
import com.yihuacomputer.fish.api.monitor.business.ICounterFeitMoneyService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.monitor.entity.business.CounterFeitMoney;

@Service
@Transactional
public class CounterFeitMoneyService implements ICounterFeitMoneyService {
	
	 @Autowired
	 private IGenericDao dao;

	 @Autowired
	 private IOrganizationService orgService;


	@Override
	public ICounterFeitMoney make() {
		return new CounterFeitMoney();
	}

	@Override
	public void save(ICounterFeitMoney counterFeitMoney) {
		this.dao.save(counterFeitMoney);
		
	}

    @SuppressWarnings("unchecked")
    @Override
	@Deprecated
	@Transactional(readOnly = true)
    public IPageResult<ICounterFeitMoney> page(int offset, int limit, IFilter filter, long orgId) {
		StringBuffer hql = new StringBuffer();
		IOrganization org = orgService.get(String.valueOf(orgId));
		hql.append("select DISTINCT ${columns} from (select counterFeitMoney.* from ATMC_COUNTER_FEIT_MONEY counterFeitMoney,DEV_INFO device,SM_ORG org,DEV_TYPE devType,DEV_VENDOR vendor ");
		hql.append("where counterFeitMoney.TERMINAL_ID = device.TERMINAL_ID and device.ORG_ID = org.ID and device.DEV_TYPE_ID = devType.ID and devType.DEV_VENDOR_ID = vendor.ID ");
		hql.append("and org.ORG_FLAG like ? ${filter} ");
		hql.append(" order by counterFeitMoney.dateTime desc");
		IPageResult<ICounterFeitMoney> page = (IPageResult<ICounterFeitMoney>) dao.page(offset, limit, filter, hql.toString(),
			      "%" + org.getOrgFlag());
	        return page;
    }

	
    @Override
	public IPageResult<ICounterFeitMoney> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, CounterFeitMoney.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResult<Object> pageObj(int offset, int limit, IFilter filter) {

		String hql = " select counterFeitMoney, device from CounterFeitMoney counterFeitMoney, Device device where counterFeitMoney.terminalId = device.terminalId  order by counterFeitMoney.dateTime desc";

		return (PageResult<Object>) dao.page(offset, limit, filter, hql);
	}
}