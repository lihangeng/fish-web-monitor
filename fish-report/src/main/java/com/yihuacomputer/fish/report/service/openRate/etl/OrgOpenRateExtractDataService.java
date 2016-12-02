package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.NumUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateWeek;
import com.yihuacomputer.fish.report.entity.etl.OrgOpenRateMonth;
import com.yihuacomputer.fish.report.entity.etl.OrgOpenRateWeek;

@Service
@Transactional
public class OrgOpenRateExtractDataService implements IOrgOpenRateExtractDataService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IOrgOpenRateEtlService orgOpenRateEtlService;
	
	@Override
	public void extractByWeek(Date date) {
		Long weekOfYear = DateUtils.getWeek(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select ORG_CODE,ORG_NAME, sum(dor.OPENTIMES) OPENTIMES,");
		sql.append("sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL,dor.START_DATE,dor.END_DATE ");
		sql.append("from ETL_DEVICE_OPEN_RATE_WEEK dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by ORG_CODE");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, weekOfYear);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IOrgOpenRateWeek week = new OrgOpenRateWeek();
			week.setDate(weekOfYear);
			week.setOrgCode(each[0].toString());
			week.setOrgName(each[1].toString());
			week.setOpenTimes(Long.parseLong(each[2].toString()));
			week.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			week.setStartDate(each[4].toString());
			week.setEndDate(each[5].toString());
			week.setOpenRate(NumUtils.getPercent(week.getHealthyTimeReal(),week.getOpenTimes()));
			orgOpenRateEtlService.saveByWeek(week);
		}
	}

	@Override
	public void extractByMonth(Date date) {
		Long ym = DateUtils.getLongYM(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select ORG_CODE,ORG_NAME, sum(dor.OPENTIMES) OPENTIMES,sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL ");
		sql.append("from ETL_DEVICE_OPEN_RATE_MONTH dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by ORG_CODE");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, ym);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IOrgOpenRateMonth dorMonth = new OrgOpenRateMonth();
			dorMonth.setDate(ym);
			dorMonth.setOrgCode(each[0].toString());
			dorMonth.setOrgName(each[1].toString());
			dorMonth.setOpenTimes(Long.parseLong(each[2].toString()));
			dorMonth.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			dorMonth.setOpenRate(NumUtils.getPercent(dorMonth.getHealthyTimeReal(),dorMonth.getOpenTimes()));
			orgOpenRateEtlService.saveByMonth(dorMonth);
		}
	}

}
