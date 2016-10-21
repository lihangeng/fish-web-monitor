package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.NumUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgMonthOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgWeekOpenRate;
import com.yihuacomputer.fish.report.entity.etl.AvgDayOpenRate;
import com.yihuacomputer.fish.report.entity.etl.AvgMonthOpenRate;
import com.yihuacomputer.fish.report.entity.etl.AvgWeekOpenRate;

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
	public void extractByDay(Date date) {
		String stdDate = DateUtils.getDate(date);
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(dor.openTimes),sum(dor.healthyTimeReal) ");
		hql.append("from DayOpenRate dor ");
		hql.append("where dor.statDate = ? ");
		List<Object> lists = dao.findByHQL(hql.toString(), stdDate);
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] == null){
				continue;
			}
			IAvgDayOpenRate avgDay = new AvgDayOpenRate();
			avgDay.setDate(Long.parseLong(DateUtils.getDateShort(date)));
			avgDay.setOpenTimes(Long.valueOf(each[0].toString()));
			avgDay.setHealthyTimeReal(Long.valueOf(each[1].toString()));
			avgDay.setOpenRate(NumUtils.getPercent(avgDay.getHealthyTimeReal(),avgDay.getOpenTimes()));
			this.saveByDay(avgDay);
		}
	}

	@Override
	public void extractByWeek(Date date) {
		Long [] values = DateUtils.getFirstAndLastDayofWeek(date);
		List<Object> lists = dao.findByHQL(getSqlByAvgOpenRate(),values[0],values[1]);
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){
				IAvgWeekOpenRate avgWeek = new AvgWeekOpenRate();
				avgWeek.setDate(DateUtils.getWeek(date));
				avgWeek.setStartDate(String.valueOf(values[0]));
				avgWeek.setEndDate(String.valueOf(values[1]));
				avgWeek.setOpenTimes(Long.valueOf(each[0].toString()));
				avgWeek.setHealthyTimeReal(Long.valueOf(each[1].toString()));
				avgWeek.setOpenRate(NumUtils.getPercent(avgWeek.getHealthyTimeReal(),avgWeek.getOpenTimes()));
				this.saveByWeek(avgWeek);
			}
		}
	}

	@Override
	public void extractByMonth(Date date) {
		Long [] values = DateUtils.getFirstAndLastDayofMonth(date);
		List<Object> lists = dao.findByHQL(getSqlByAvgOpenRate(), values[0],values[1]);
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){
				IAvgMonthOpenRate avgMonth = new AvgMonthOpenRate();
				avgMonth.setDate(DateUtils.getLongYM(date));
				avgMonth.setOpenTimes(Long.valueOf(each[0].toString()));
				avgMonth.setHealthyTimeReal(Long.valueOf(each[1].toString()));
				avgMonth.setOpenRate(NumUtils.getPercent(avgMonth.getHealthyTimeReal(),avgMonth.getOpenTimes()));
				this.saveByMonth(avgMonth);
			}
		}
	}
	
	private String getSqlByAvgOpenRate(){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(dor.openTimes),sum(dor.healthyTimeReal) ");
		hql.append("from AvgDayOpenRate dor ");
		hql.append("where dor.date >= ?  and dor.date <= ? ");
		return hql.toString();
	}

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
