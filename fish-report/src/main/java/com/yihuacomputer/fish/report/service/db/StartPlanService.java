package com.yihuacomputer.fish.report.service.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.IStartPlan;
import com.yihuacomputer.fish.report.base.entity.StartPlan;
import com.yihuacomputer.fish.report.service.base.DomainStartPlanService;

@Service
@Transactional
public class StartPlanService extends DomainStartPlanService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IStartPlan get(long id) {
		return dao.get(id, StartPlan.class);
	}

	@Override
	public IStartPlan get(String name) {
		IStartPlan startPlan = (IStartPlan) dao.getCriteria(StartPlan.class)
				.add(Restrictions.eq("name", name)).uniqueResult();
		return startPlan;
	}

	@Override
	public IStartPlan add(IStartPlan startPlan) {
		return dao.save(startPlan);
	}

	@Override
	public void remove(long id) {
		dao.delete(id, StartPlan.class);
	}

	@Override
	public void update(IStartPlan startPlan) {
		dao.update(interface2Entity(startPlan, true));
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<IStartPlan> list() {
		return dao.loadAll(IStartPlan.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<IStartPlan> list(IFilter filter) {
		Filter db = new Filter();
		Filter mem = new Filter();
		for (IFilterEntry entry : filter.entrySet()) {
			if (entry.getKey().indexOf(".") > 0) {
				mem.addFilterEntry(entry);
			} else {
				db.addFilterEntry(entry);
			}
		}
		List<IStartPlan> plans = dao.findByFilter(db, IStartPlan.class);
		return mem.filter(plans);
	}

	private StartPlan interface2Entity(IStartPlan startPlan, boolean load) {
		if (startPlan instanceof IStartPlan) {
			return (StartPlan) startPlan;
		}
		return null;
	}
	
	@Override
	public IPageResult<IStartPlan> page(int offset, int limit, IFilter filter) {
		List<IStartPlan> lists = new ArrayList<IStartPlan>();
		EntityUtils.convert(list(filter), lists);
		return new PageResult<IStartPlan>(lists,offset,limit);
	}

}
