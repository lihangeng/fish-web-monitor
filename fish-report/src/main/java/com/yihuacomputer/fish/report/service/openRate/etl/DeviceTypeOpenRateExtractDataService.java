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
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateExtractDataService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateWeek;
import com.yihuacomputer.fish.report.entity.etl.DeviceTypeOpenRateMonth;
import com.yihuacomputer.fish.report.entity.etl.DeviceTypeOpenRateWeek;

@Service
@Transactional
public class DeviceTypeOpenRateExtractDataService implements IDeviceTypeOpenRateExtractDataService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IDeviceTypeOpenRateEtlService deviceTypeOpenRateEtlService;
	
	@Override
	public void extractByWeek(Date date) {
		Long weekOfYear = DateUtils.getWeek(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select dor.TYPE_ID,dor.DEV_TYPE_NAME, sum(dor.OPENTIMES) OPENTIMES,");
		sql.append("sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL,dor.START_DATE,dor.END_DATE ");
		sql.append("from ETL_DEVICE_OPEN_RATE_WEEK dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by dor.TYPE_ID");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0,weekOfYear);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceTypeOpenRateWeek week = new DeviceTypeOpenRateWeek();
			week.setDate(weekOfYear);
			week.setTypeId(Long.parseLong(each[0].toString()));
			week.setDevType(each[1].toString());
			week.setOpenTimes(Long.parseLong(each[2].toString()));
			week.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			week.setStartDate(each[4].toString());
			week.setEndDate(each[5].toString());
			week.setOpenRate(NumUtils.getPercent(week.getHealthyTimeReal(),week.getOpenTimes()));
			deviceTypeOpenRateEtlService.saveByWeek(week);
		}
	}

	@Override
	public void extractByMonth(Date date) {
		Long ym = DateUtils.getLongYM(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select dor.TYPE_ID,dor.DEV_TYPE_NAME, sum(dor.OPENTIMES) OPENTIMES,sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL ");
		sql.append("from ETL_DEVICE_OPEN_RATE_MONTH dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by dor.TYPE_ID");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, ym);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceTypeOpenRateMonth dorMonth = new DeviceTypeOpenRateMonth();
			dorMonth.setDate(ym);
			dorMonth.setTypeId(Long.parseLong(each[0].toString()));
			dorMonth.setDevType(each[1].toString());
			dorMonth.setOpenTimes(Long.parseLong(each[2].toString()));
			dorMonth.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			dorMonth.setOpenRate(NumUtils.getPercent(dorMonth.getHealthyTimeReal(),dorMonth.getOpenTimes()));
			deviceTypeOpenRateEtlService.saveByMonth(dorMonth);
		}
	}

}
