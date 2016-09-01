package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
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
			avgDay.setDate(DateUtils.getDateShort(date));
			avgDay.setOpenTimes(Long.valueOf(each[0].toString()));
			avgDay.setHealthyTimeReal(Long.valueOf(each[1].toString()));
			dao.save(avgDay);
		}
	}

	@Override
	public void extractByWeek(Date date) {
		Long [] values = DateUtils.getFirstAndLastDayofWeek(date);
		String start = String.valueOf(values[0]);
		String end = String.valueOf(values[1]);
		List<Object> lists = dao.findByHQL(getSqlByAvgOpenRate(),start,end);
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){
				IAvgWeekOpenRate avgWeek = new AvgWeekOpenRate();
				avgWeek.setDate(String.valueOf(DateUtils.getWeek(date)));
				avgWeek.setStartDate(start);
				avgWeek.setEndDate(end);
				avgWeek.setOpenTimes(Long.valueOf(each[0].toString()));
				avgWeek.setHealthyTimeReal(Long.valueOf(each[1].toString()));
				dao.save(avgWeek);
			}
		}
	}

	@Override
	public void extractByMonth(Date date) {
		Long [] values = DateUtils.getFirstAndLastDayofMonth(date);
		String start = String.valueOf(values[0]);
		String end = String.valueOf(values[1]);
		List<Object> lists = dao.findByHQL(getSqlByAvgOpenRate(), start,end);
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){
				IAvgMonthOpenRate avgMonth = new AvgMonthOpenRate();
				avgMonth.setDate(DateUtils.getYM(date));
				avgMonth.setOpenTimes(Long.valueOf(each[0].toString()));
				avgMonth.setHealthyTimeReal(Long.valueOf(each[1].toString()));
				dao.save(avgMonth);
			}
		}
	}
	
	private String getSqlByAvgOpenRate(){
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(dor.openTimes),sum(dor.healthyTimeReal) ");
		hql.append("from AvgDayOpenRate dor ");
		hql.append("where dor.date > ?  and dor.date < ? ");
		return hql.toString();
	}

	@Override
	public Object[] getWeekTotal(int weekOfYear) {
		IAvgWeekOpenRate avgWeekRate = dao.findUniqueByHql("from AvgWeekOpenRate where dor.date = ?", weekOfYear);
		if(avgWeekRate != null){
			Object [] values = new Object []{};
			values[0] = avgWeekRate.getOpenTimes();
			values[1] = avgWeekRate.getHealthyTimeReal();
			values[2] = avgWeekRate.getRate();
			//TODO 低于平局值的台数计算
			return values;
		}
		return new Object[]{0l,0l,0.0};
	}

	@Override
	public Object[] getMonthTotal(int month) {
		IAvgWeekOpenRate avgWeekRate = dao.findUniqueByHql("from AvgMonthOpenRate where dor.date = ?", month);
		if(avgWeekRate != null){
			Object [] values = new Object []{};
			values[0] = avgWeekRate.getOpenTimes();
			values[1] = avgWeekRate.getHealthyTimeReal();
			values[2] = avgWeekRate.getRate();
			//TODO 低于平局值的台数计算
			return values;
		}
		return new Object[]{0l,0l,0.0};
	}

	@Override
	public List<IAvgDayOpenRate> getAvgDays(long start, long end) {
		return dao.findByHQL("from AvgDayOpenRate where dor.date > ?  and dor.date < ?", start,end);
	}
}
