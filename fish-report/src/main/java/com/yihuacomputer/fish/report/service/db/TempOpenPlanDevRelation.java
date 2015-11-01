package com.yihuacomputer.fish.report.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.openplan.IDeviceOpenPlan;
import com.yihuacomputer.fish.api.openplan.ITempDevRelation;
import com.yihuacomputer.fish.api.report.IStartPlan;
import com.yihuacomputer.fish.api.report.ITempOpenPlanDevRelation;
import com.yihuacomputer.fish.report.base.entity.TempDevOpenPlanRlation;


@Service
@Transactional
public class TempOpenPlanDevRelation implements ITempOpenPlanDevRelation{

	@Autowired
	private IGenericDao dao;

	@Override
	public void link(IDeviceOpenPlan openPlan, Long DeviceId) {
		TempDevOpenPlanRlation tempDevOpenPlanRel = TempDevOpenPlanRlation.make(openPlan.getId(), DeviceId);
		dao.save(tempDevOpenPlanRel);
	}

	@Override
	public void unlink(IDeviceOpenPlan openPlan,Long DeviceId) {
		Filter filter = new Filter();
		filter.addFilterEntry(FilterFactory.eq("tempPlanId", openPlan.getId()));
		filter.addFilterEntry(FilterFactory.eq("tempDeviceId",DeviceId));
		TempDevOpenPlanRlation topdr = dao.findUniqueByFilter(filter, TempDevOpenPlanRlation.class);
		if(topdr != null)
		{
			dao.delete(topdr);
		}
	}

	@Override
	public IPageResult<IStartPlan> page(int offset, int limit, IFilter filter) {

		return null;
	}

	@Override
	public List<IDeviceOpenPlan> listPlanByDevice(Long deviceId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from DeviceOpenPlan t ,TempDevOpenPlanRlation t1 ");
		hql.append("where t.id = t1.tempPlanId and t1.tempDeviceId = ?");
		List<IDeviceOpenPlan> startPlans = dao.findByHQL(hql.toString(), deviceId);
		return startPlans;
	}

	@Override
	public List<ITempDevRelation> listRelationByDevice(Long deviceId) {
		StringBuffer hql = new StringBuffer();
		hql.append("select t from TempDevOpenPlanRlation t where t.tempDeviceId = ?");
		List<ITempDevRelation> tempRelation = dao.findByHQL(hql.toString(), deviceId);
		return tempRelation;
	}

}
