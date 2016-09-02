package com.yihuacomputer.fish.report.service.device.etl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardMonth;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardWeek;
import com.yihuacomputer.fish.report.entity.etl.RetainCardMonth;
import com.yihuacomputer.fish.report.entity.etl.RetainCardWeek;

/**
 * 
 * @author xuxiang
 *
 */
@Service
@Transactional
public class RetainCardEtlService implements IRetainCardEtlService {

	@Autowired
	private IGenericDao dao;

	@Override
	public void extractByWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(cal);
		Date endDate = DateUtils.getLastDayOfWeek(cal);
		long start = Long.parseLong(DateUtils.getDateShort(startDate));
		long end = Long.parseLong(DateUtils.getDateShort(endDate));
		
		StringBuilder sql = new StringBuilder();
		sql.append("select B.typeName,A.retainCount,B.deviceCount from  ");
		sql.append("(select dev.DEV_TYPE_ID devTypeId,count(rc.id) retainCount ");
		sql.append("from atmc_retain_card rc,dev_info dev ");
		sql.append("where rc.TERMINAL_ID = dev.TERMINAL_ID AND rc.retain_date >= ? and rc.retain_date <= ? ");
		sql.append("GROUP BY dev.DEV_TYPE_ID) A ");
		sql.append("LEFT JOIN  ");
		sql.append("(select dev.DEV_TYPE_ID devTypeId,devType.NAME typeName,count(dev.ID) deviceCount ");
		sql.append("from dev_info dev,dev_type devType ");
		sql.append("where devType.id = dev.DEV_TYPE_ID ");
		sql.append("group by dev.DEV_TYPE_ID) B ");
		sql.append("on A.devTypeId = B.devTypeId ");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, start);
		query.setLong(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IRetainCardWeek day = new RetainCardWeek();
			day.setDate(Long.parseLong(DateUtils.get(date, "yyyyww")));
			day.setDevType(each[0].toString());
			day.setRetainCount(Long.parseLong(each[1].toString()));
			day.setDeviceCount(Long.parseLong(each[2].toString()));
			day.setLastRetainCount(this.getLastWeekRetainCard(date,each[0].toString()));
			dao.save(day);
		}
	}

	
	private long getLastWeekRetainCard(Date date,String typeName) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		long sDate = Long.parseLong(DateUtils.get(cal.getTime(), "yyyyww"));
		RetainCardWeek week = dao.findUniqueByHql("from RetainCardWeek where date = ? and devType = ?", sDate,typeName);
		if(week != null){
			return week.getRetainCount();
		}else{
			return 0;
		}
	}


	@Override
	public void extractByMonth(Date date) {
		String sDate = DateUtils.getYM(date);
		long start = Long.parseLong(sDate+"01");
		long end = Long.parseLong(sDate+"31");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select B.typeName,A.retainCount,B.deviceCount from  ");
		sql.append("(select dev.DEV_TYPE_ID devTypeId,count(rc.id) retainCount ");
		sql.append("from atmc_retain_card rc,dev_info dev ");
		sql.append("where rc.TERMINAL_ID = dev.TERMINAL_ID AND rc.retain_date >= ? and rc.retain_date <= ? ");
		sql.append("GROUP BY dev.DEV_TYPE_ID) A ");
		sql.append("LEFT JOIN  ");
		sql.append("(select dev.DEV_TYPE_ID devTypeId,devType.NAME typeName,count(dev.ID) deviceCount ");
		sql.append("from dev_info dev,dev_type devType ");
		sql.append("where devType.id = dev.DEV_TYPE_ID ");
		sql.append("group by dev.DEV_TYPE_ID) B ");
		sql.append("on A.devTypeId = B.devTypeId ");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, start);
		query.setLong(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IRetainCardMonth day = new RetainCardMonth();
			day.setDate(Long.parseLong(sDate));
			day.setDevType(each[0].toString());
			day.setRetainCount(Long.parseLong(each[1].toString()));
			day.setDeviceCount(Long.parseLong(each[2].toString()));
			day.setLastRetainCount(this.getLastMonthRetainCard(date,each[0].toString()));
			dao.save(day);
		}
	}
	
	private long getLastMonthRetainCard(Date date,String typeName) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		long sDate = Long.parseLong(DateUtils.getYM(cal.getTime()));
		RetainCardWeek week = dao.findUniqueByHql("from RetainCardWeek where date = ? and devType = ?", sDate,typeName);
		if(week != null){
			return week.getRetainCount();
		}else{
			return 0;
		}
	}


	@Override
	public List<IRetainCardWeek> getWeek(long weekOfYear) {
		return dao.findByHQL("from RetainCardWeek where date = ?", weekOfYear);
	}


	@Override
	public Long[] getWeekTotal(long weekOfYear) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(rc.DEVICE_COUNT),sum(rc.RETAIN_COUNT),sum(rc.LAST_RETAIN_COUNT)");
		sql.append(" from etl_retain_card_week rc");
		sql.append(" where rc.STAT_DATE = ?");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, weekOfYear);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){
				Long [] values = new Long[3];
				values[0] = Long.parseLong(each[0].toString());
				values[1] = Long.parseLong(each[1].toString());
				values[2] = Long.parseLong(each[2].toString());
				return values;
			}
		}
		return new Long[]{0L,0L,0L};
	}


	@Override
	public List<IRetainCardMonth> getMonth(long month) {
		return dao.findByHQL("from RetainCardMonth where date = ?", month);
	}

	@Override
	public Long[] getMonthTotal(long month) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(rc.DEVICE_COUNT),sum(rc.RETAIN_COUNT),sum(rc.LAST_RETAIN_COUNT)");
		sql.append(" from etl_retain_card_month rc");
		sql.append(" where rc.STAT_DATE = ?");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, month);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each != null){
				Long [] values = new Long[3];
				values[0] = Long.parseLong(each[0].toString());
				values[1] = Long.parseLong(each[1].toString());
				values[2] = Long.parseLong(each[2].toString());
				return values;
			}
		}
		return new Long[]{0L,0L,0L};
	}

}
