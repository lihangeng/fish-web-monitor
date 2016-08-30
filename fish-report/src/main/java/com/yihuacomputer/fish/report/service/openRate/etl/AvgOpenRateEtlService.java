package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.Calendar;
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
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(cal);
		Date endDate = DateUtils.getLastDayOfWeek(cal);
		String start =DateUtils.getDateShort(startDate);
		String end = DateUtils.getDateShort(endDate);
		
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(dor.openTimes),sum(dor.healthyTimeReal) ");
		hql.append("from AvgDayOpenRate dor ");
		hql.append("where dor.date > ?  and dor.date < ?");
		List<Object> lists = dao.findByHQL(hql.toString(),start,end);
		for(Object object : lists){
			Object [] each = (Object[])object;
			IAvgWeekOpenRate avgWeek = new AvgWeekOpenRate();
			avgWeek.setDate(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
			avgWeek.setStartDate(start);
			avgWeek.setEndDate(end);
			avgWeek.setOpenTimes(Long.valueOf(each[0].toString()));
			avgWeek.setHealthyTimeReal(Long.valueOf(each[1].toString()));
			dao.save(avgWeek);
		}
	}

	@Override
	public void extractByMonth(Date date) {
		String month = DateUtils.getYM(date);
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(dor.openTimes),sum(dor.healthyTimeReal) ");
		hql.append("from AvgDayOpenRate dor ");
		hql.append("where dor.date like ? ");
		List<Object> lists = dao.findByHQL(hql.toString(), month + "%");
		for(Object object : lists){
			Object [] each = (Object[])object;
			IAvgMonthOpenRate avgMonth = new AvgMonthOpenRate();
			avgMonth.setDate(month);
			avgMonth.setOpenTimes(Long.valueOf(each[0].toString()));
			avgMonth.setHealthyTimeReal(Long.valueOf(each[1].toString()));
			dao.save(avgMonth);
		}
	}
}
