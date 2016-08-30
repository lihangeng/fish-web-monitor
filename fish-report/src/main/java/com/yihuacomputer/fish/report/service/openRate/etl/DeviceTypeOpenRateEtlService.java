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
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateWeek;
import com.yihuacomputer.fish.report.entity.etl.DeviceTypeOpenRateMonth;
import com.yihuacomputer.fish.report.entity.etl.DeviceTypeOpenRateWeek;

/**
 * 设备型号开机率统计服务的实现
 * @author xuxiang
 * @since 2.1.1.1
 */
@Service
@Transactional
public class DeviceTypeOpenRateEtlService implements IDeviceTypeOpenRateEtlService{

	@Autowired
	private IGenericDao dao;
	
	@Override
	public void extractByWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String weekOfYear = String.valueOf(cal.get(Calendar.WEEK_OF_YEAR));
		
		StringBuilder sql = new StringBuilder();
		sql.append("select dor.type_id,dor.dev_type_name, sum(dor.OPENTIMES) OPENTIMES,");
		sql.append("sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL,dor.start_date,dor.end_date ");
		sql.append("from etl_device_open_rate_week dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by dor.type_id");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setString(0,weekOfYear);
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
			dao.save(week);
		}
	}

	@Override
	public void extractByMonth(Date date) {
		String month = DateUtils.get(date, "yyyy-MM");
		StringBuilder sql = new StringBuilder();
		sql.append("select dor.type_id,dor.dev_type_name, sum(dor.OPENTIMES) OPENTIMES,sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL ");
		sql.append("from etl_device_open_rate_month dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by dor.type_id");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setString(0, month);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceTypeOpenRateMonth dorMonth = new DeviceTypeOpenRateMonth();
			dorMonth.setDate(month);
			dorMonth.setTypeId(Long.parseLong(each[0].toString()));
			dorMonth.setDevType(each[1].toString());
			dorMonth.setOpenTimes(Long.parseLong(each[2].toString()));
			dorMonth.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			dao.save(dorMonth);
		}
	}

}
