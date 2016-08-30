package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateWeek;
import com.yihuacomputer.fish.report.entity.etl.OrgOpenRateMonth;
import com.yihuacomputer.fish.report.entity.etl.OrgOpenRateWeek;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Service
@Transactional
public class OrgOpenRateEtlService implements IOrgOpenRateEtlService{

	@Autowired
	private IGenericDao dao;
	
	@Override
	public void extractByWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(cal);
		Date endDate = DateUtils.getLastDayOfWeek(cal);
		String start =DateUtils.getDateShort(startDate);
		String end = DateUtils.getDateShort(endDate);
		
		StringBuilder sql = new StringBuilder();
		sql.append("select org_code,org_name, sum(dor.OPENTIMES) OPENTIMES,sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL ");
		sql.append("from etl_device_open_rate_week dor");
		sql.append("where dor.STAT_DATE > ?  and dor.STAT_DATE < ? ");
		sql.append("group by org_code");
		

		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setString(0, start);
		query.setString(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IOrgOpenRateWeek week = new OrgOpenRateWeek();
			week.setDate(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
			week.setStartDate(start);
			week.setEndDate(end);
			week.setOrgCode(each[0].toString());
			week.setOrgName(each[1].toString());
			week.setOpenTimes(Long.parseLong(each[2].toString()));
			week.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			dao.save(week);
		}
	}

	@Override
	public void extractByMonth(Date date) {
		String month = DateUtils.getYM(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select org_code,org_name, sum(dor.OPENTIMES) OPENTIMES,sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL ");
		sql.append("from etl_device_open_rate_week dor");
		sql.append("where dor.STAT_DATE > ?  and dor.STAT_DATE < ? ");
		sql.append("group by org_code");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setString(0, month + "%");
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IOrgOpenRateMonth dorMonth = new OrgOpenRateMonth();
			dorMonth.setDate(month);
			dorMonth.setOrgCode(each[0].toString());
			dorMonth.setOrgName(each[1].toString());
			dorMonth.setOpenTimes(Long.parseLong(each[2].toString()));
			dorMonth.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			dao.save(dorMonth);
		}
	}

}
