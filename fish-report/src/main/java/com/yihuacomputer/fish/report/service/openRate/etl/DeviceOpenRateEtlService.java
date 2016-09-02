package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
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
	public void extractByWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(cal);
		Date endDate = DateUtils.getLastDayOfWeek(cal);
		String start =DateUtils.getDate(startDate);
		String end = DateUtils.getDate(endDate);
		SQLQuery query = dao.getSQLQuery(getDeviceOpenRateExtractSql());
		query.setString(0, start);
		query.setString(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceOpenRateWeek week = new DeviceOpenRateWeek();
			week.setDate(DateUtils.getWeek(endDate));
			week.setStartDate(start);
			week.setEndDate(end);
			week.setTerminalId(each[0].toString());
			week.setOpenTimes(Long.parseLong(each[1].toString()));
			week.setHealthyTimeReal(Long.parseLong(each[2].toString()));
			week.setOrgCode(each[3].toString());
			week.setOrgName(each[4].toString());
			week.setTypeId(Long.parseLong(each[5].toString()));
			week.setDevType(each[6].toString());
			week.setOpenRate(week.getHealthyTimeReal()/week.getOpenTimes());
			dao.save(week);
		}
	}
	
	private String getDeviceOpenRateExtractSql(){
		StringBuilder sql = new StringBuilder();
		sql.append("select dor.TERMINAL_ID tid,sum(dor.OPENTIMES) OPENTIMES,sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL,");
		sql.append("org.CODE orgCode,org.NAME orgName,type.ID typeId,type.NAME typeName ");
		sql.append("from dev_open_rate dor , dev_info dev,dev_type type ,sm_org org ");
		sql.append("where dor.STAT_DATE > ?  and dor.STAT_DATE < ? and dor.TERMINAL_ID = dev.TERMINAL_ID ");
		sql.append("and dev.DEV_TYPE_ID = type.ID and dev.ORG_ID = org.ID ");
		sql.append("GROUP BY dor.TERMINAL_ID");
		return sql.toString();
	}
	
	@Override
	public void extractByMonth(Date date) {
		String month = DateUtils.get(date, DateUtils.STANDARD_MONTH_FULL);
		String start = month + "-01";
		String end = month + "-31";
		SQLQuery query = dao.getSQLQuery(getDeviceOpenRateExtractSql());
		query.setString(0, start);
		query.setString(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceOpenRateMonth dorMonth = new DeviceOpenRateMonth();
			dorMonth.setDate(DateUtils.getLongYM(date));
			dorMonth.setTerminalId(each[0].toString());
			dorMonth.setOpenTimes(Long.parseLong(each[1].toString()));
			dorMonth.setHealthyTimeReal(Long.parseLong(each[2].toString()));
			dorMonth.setOrgCode(each[3].toString());
			dorMonth.setOrgName(each[4].toString());
			dorMonth.setTypeId(Long.parseLong(each[5].toString()));
			dorMonth.setDevType(each[6].toString());
			dorMonth.setOpenRate(dorMonth.getHealthyTimeReal()/dorMonth.getOpenTimes());
			dao.save(dorMonth);
		}
	}

	@Override
	public List<IDeviceOpenRateWeek> getTopDeviceWeek(int weekOfYear, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", weekOfYear);
		filter.descOrder("openRate");
		IPageResult<IDeviceOpenRateWeek> page = dao.page(0, limit, filter, DeviceOpenRateWeek.class);
		return page.list();
	}

	@Override
	public List<IDeviceOpenRateWeek> getLastDeviceWeek(int weekOfYear, int limit) {
		IFilter filter = new Filter();
		filter.eq("ddate", weekOfYear);
		filter.order("openRate");
		IPageResult<IDeviceOpenRateWeek> page = dao.page(0, limit, filter, DeviceOpenRateWeek.class);
		return page.list();
	}
	
	@Override
	public List<IDeviceOpenRateMonth> getTopDeviceMonth(int month, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", month);
		filter.descOrder("openRate");
		IPageResult<IDeviceOpenRateMonth> page = dao.page(0, limit, filter, DeviceOpenRateMonth.class);
		return page.list();
	}


	@Override
	public List<IDeviceOpenRateMonth> getLastDeviceMonth(int month, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", month);
		filter.order("openRate");
		IPageResult<IDeviceOpenRateMonth> page = dao.page(0, limit, filter, DeviceOpenRateMonth.class);
		return page.list();
	}

}
