package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.ICashInitRule;
import com.yihuacomputer.fish.api.monitor.box.ICashInitRuleService;
import com.yihuacomputer.fish.monitor.entity.cashplan.CashInitRule;

@Service
@Transactional
public class CashInitRuleService implements ICashInitRuleService {

	@Autowired
	private IGenericDao dao;
	@Override
	public IPageResult<ICashInitRule> page(int start, int limit, IFilter filter) {
		return dao.page(start, limit, filter, CashInitRule.class);
	}
	@Override
	public ICashInitRule update(ICashInitRule deviceBoxInitRule) {
		return dao.update(deviceBoxInitRule);
	}
	@Override
	public ICashInitRule get(long id) {
		return dao.get(id, CashInitRule.class);
	}

	@Override
	public ICashInitRule get(BoxInitRuleType ruleType){
		IFilter filter  = new Filter();
		filter.eq("ruleType", ruleType);
		return dao.findUniqueByFilter(filter, ICashInitRule.class);
	}
}
