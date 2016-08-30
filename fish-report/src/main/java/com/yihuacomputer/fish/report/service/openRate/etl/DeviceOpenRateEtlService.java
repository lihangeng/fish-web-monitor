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
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceOpenRateWeek;
import com.yihuacomputer.fish.report.entity.etl.DeviceOpenRateMonth;
import com.yihuacomputer.fish.report.entity.etl.DeviceOpenRateWeek;

/**
 * 
 * @author xuxiang
 *
 */
@Service
@Transactional
public class DeviceOpenRateEtlService implements IDeviceOpenRateEtlService{

	@Autowired
	private IGenericDao dao;
	
	@Override
	public void extractByWeeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(cal);
		Date endDate = DateUtils.getLastDayOfWeek(cal);
		String start =DateUtils.getDateShort(startDate);
		String end = DateUtils.getDateShort(endDate);

		StringBuilder sql = new StringBuilder();
		sql.append("select dor.TERMINAL_ID tid,sum(dor.OPENTIMES) OPENTIMES, ");
		sql.append("sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL,org.CODE,org.NAME,type.ID,type.NAME ");
		sql.append("from dev_open_rate dor , dev_info dev,dev_type type ,sm_org org ");
		sql.append("where dor.STAT_DATE > ?  and dor.STAT_DATE < ? and dor.TERMINAL_ID = dev.TERMINAL_ID ");
		sql.append("and dev.DEV_TYPE_ID = type.ID and dev.ORG_ID = org.ID ");
		sql.append("GROUP BY dor.TERMINAL_ID");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setString(0, start);
		query.setString(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceOpenRateWeek week = new DeviceOpenRateWeek();
			week.setDate(String.valueOf(cal.get(Calendar.WEEK_OF_YEAR)));
			week.setStartDate(start);
			week.setEndDate(end);
			week.setTerminalId(each[0].toString());
			week.setOpenTimes(Long.parseLong(each[1].toString()));
			week.setHealthyTimeReal(Long.parseLong(each[2].toString()));
			week.setOrgCode(each[3].toString());
			week.setOrgName(each[4].toString());
			week.setTypeId(Long.parseLong(each[5].toString()));
			week.setDevType(each[6].toString());
			dao.save(week);
		}
	}
	
	@Override
	public void extractByMonth(Date date) {
		String month = DateUtils.getYM(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select dor.TERMINAL_ID tid,sum(dor.OPENTIMES) OPENTIMES, ");
		sql.append("sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL,org.CODE,org.NAME,type.ID,type.NAME ");
		sql.append("from dev_open_rate dor , dev_info dev,dev_type type ,sm_org org ");
		sql.append("where dor.STAT_DATE like ? and dor.TERMINAL_ID = dev.TERMINAL_ID ");
		sql.append("and dev.DEV_TYPE_ID = type.ID and dev.ORG_ID = org.ID ");
		sql.append("GROUP BY dor.TERMINAL_ID");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setString(0, month + "%");
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceOpenRateMonth dorMonth = new DeviceOpenRateMonth();
			dorMonth.setDate(month);
			dorMonth.setTerminalId(each[0].toString());
			dorMonth.setOpenTimes(Long.parseLong(each[1].toString()));
			dorMonth.setHealthyTimeReal(Long.parseLong(each[2].toString()));
			dorMonth.setOrgCode(each[3].toString());
			dorMonth.setOrgName(each[4].toString());
			dorMonth.setTypeId(Long.parseLong(each[5].toString()));
			dorMonth.setDevType(each[6].toString());
			dao.save(dorMonth);
		}
	}

}
