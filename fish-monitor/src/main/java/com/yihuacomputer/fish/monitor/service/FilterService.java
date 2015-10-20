package com.yihuacomputer.fish.monitor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.IFilterService;
import com.yihuacomputer.fish.api.monitor.filter.IProcessFilter;
import com.yihuacomputer.fish.api.monitor.filter.IRetaincardFilter;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.ITransationFilter;
import com.yihuacomputer.fish.monitor.entity.filter.BoxStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ClassifyModStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ModStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.NetStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.ProcessFilter;
import com.yihuacomputer.fish.monitor.entity.filter.RetaincardFilter;
import com.yihuacomputer.fish.monitor.entity.filter.RunStatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.StatusFilter;
import com.yihuacomputer.fish.monitor.entity.filter.TransationFilter;

@Service
@Transactional
public class FilterService implements IFilterService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public void updateStatusFilter(IStatusFilter filter) {
		this.dao.update(filter);	
	}

	@Override
	public IStatusFilter loadStatusFilter(String userId) {		
		return this.dao.get(userId, StatusFilter.class);
	}

	@Override
	public IStatusFilter makeStatusFilter() {
		IStatusFilter filter = new StatusFilter();
		filter.setBoxStatusFilter(new BoxStatusFilter());
		filter.setRunStattusFilter(new RunStatusFilter());
		filter.setNetStatusFilter(new NetStatusFilter());
		filter.setModStatusFilter(new ModStatusFilter());
		return filter;
	}

	@Override
	public IStatusFilter makeAndSaveStatusFilter(String userId) {
		IStatusFilter filter = new StatusFilter();
		filter.setUserId(userId);
		filter.setBoxStatusFilter(new BoxStatusFilter());
		filter.setRunStattusFilter(new RunStatusFilter());
		filter.setNetStatusFilter(new NetStatusFilter());
		filter.setModStatusFilter(new ModStatusFilter());
		this.dao.save(filter);
		return filter;
	}

	@Override
	public ITransationFilter makeTransactionFilter() {
		TransationFilter transationFilter = new TransationFilter();
		return transationFilter;
	}

	@Override
	public IProcessFilter makeProcessFilter() {
		ProcessFilter processFilter = new ProcessFilter();
		return processFilter;
	}

	@Override
	public IRetaincardFilter makeRetaincardFilter() {
		return new RetaincardFilter();
	}

	public IClassifyModStatusFilter makeClassifyModStatusFilter() {
		return new ClassifyModStatusFilter();
	}

//	public IClassifyBoxStatusFilter makeClassifyBoxStatusFilter() {
//		return new ClassifyBoxStatusFilter();
//	}

//	public IClassifyNetStatusFilter makeClassifyNetStatusFilter() {
//		return new ClassifyNetStatusFilter();
//	}

}
