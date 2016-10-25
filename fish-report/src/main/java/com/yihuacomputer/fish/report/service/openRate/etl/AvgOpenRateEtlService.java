package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgMonthOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgWeekOpenRate;

/**
 * 
 * @author xuxiang
 *
 */
@Service
@Transactional
public class AvgOpenRateEtlService implements IAvgOpenRateEtlService{

	@Autowired
	private IGenericDao dao;
	
	

	@Override
	public Object[] getWeekTotal(long weekOfYear) {
		IAvgWeekOpenRate avgWeekRate = dao.findUniqueByHql("from AvgWeekOpenRate where date = ?", weekOfYear);
		if(avgWeekRate != null){
			List<Object> obejcts = new ArrayList<Object>();
			obejcts.add(avgWeekRate.getOpenTimes());
			obejcts.add(avgWeekRate.getHealthyTimeReal());
			obejcts.add(avgWeekRate.getOpenRate());
			//TODO 低于平局值的台数计算
			return obejcts.toArray();
		}
		return new Object[]{0l,0l,0.0};
	}

	@Override
	public Object[] getMonthTotal(long month) {
		IAvgMonthOpenRate avgWeekRate = dao.findUniqueByHql("from AvgMonthOpenRate where date = ?", month);
		if(avgWeekRate != null){
			List<Object> obejcts = new ArrayList<Object>();
			obejcts.add(avgWeekRate.getOpenTimes());
			obejcts.add(avgWeekRate.getHealthyTimeReal());
			obejcts.add(avgWeekRate.getOpenRate());
			//TODO 低于平局值的台数计算
			return obejcts.toArray();
		}
		return new Object[]{0l,0l,0.0};
	}

	@Override
	public List<IAvgDayOpenRate> getAvgDays(long start, long end) {
		return dao.findByHQL("from AvgDayOpenRate where date >= ?  and date <= ? ORDER BY date ASC", start,end);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date"})
	@Override
	public IAvgDayOpenRate saveByDay(IAvgDayOpenRate avgDayOpenRate) {
		return dao.save(avgDayOpenRate);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date"})
	@Override
	public IAvgWeekOpenRate saveByWeek(IAvgWeekOpenRate avgWeekOpenRatee) {
		return dao.save(avgWeekOpenRatee);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date"})
	@Override
	public IAvgMonthOpenRate saveByMonth(IAvgMonthOpenRate avgMonthOpenRate) {
		return dao.save(avgMonthOpenRate);
	}
}
