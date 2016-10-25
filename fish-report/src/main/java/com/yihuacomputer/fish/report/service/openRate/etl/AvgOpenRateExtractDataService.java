package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.NumUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgMonthOpenRate;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IAvgWeekOpenRate;
import com.yihuacomputer.fish.report.entity.etl.AvgDayOpenRate;
import com.yihuacomputer.fish.report.entity.etl.AvgMonthOpenRate;
import com.yihuacomputer.fish.report.entity.etl.AvgWeekOpenRate;

@Service
@Transactional
public class AvgOpenRateExtractDataService implements IAvgOpenRateExtractDataService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IAvgOpenRateEtlService avgOpenRateEtlService;
	
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
			avgOpenRateEtlService.saveByDay(avgDay);
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
				avgOpenRateEtlService.saveByWeek(avgWeek);
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
				avgOpenRateEtlService.saveByMonth(avgMonth);
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

}
