package com.yihuacomputer.fish.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.monitor.entity.cashplan.CashInitPlanInfo;

@Service
@Transactional
public class CashInitPlanInfoService implements ICashInitPlanInfoService {

	@Autowired
	private IGenericDao dao;

	@Override
	public ICashInitPlanInfo make() {
		return new CashInitPlanInfo();
	}

	@SaveMethodDescrible(isUpdate=false)
	@Override
	public ICashInitPlanInfo save(ICashInitPlanInfo cashInitPlanInfo) {
		return dao.save(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanInfo update(ICashInitPlanInfo cashInitPlanInfo) {
		return dao.update(cashInitPlanInfo);
	}

	@Override
	public void remove(ICashInitPlanInfo cashInitPlanInfo) {
		dao.delete(cashInitPlanInfo);
	}

	@Override
	public ICashInitPlanInfo get(long id) {
		return dao.get(id, CashInitPlanInfo.class);
	}

	@Override
	public IPageResult<ICashInitPlanInfo> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, CashInitPlanInfo.class);
	}

	public List<ICashInitPlanInfo> list(IFilter filter) {
		return dao.findByFilter(filter, ICashInitPlanInfo.class);
	}
}
