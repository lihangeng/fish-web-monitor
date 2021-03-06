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
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardExtractDataService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardMonth;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardWeek;
import com.yihuacomputer.fish.report.entity.etl.RetainCardMonth;
import com.yihuacomputer.fish.report.entity.etl.RetainCardWeek;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class RetainCardExtractDataService implements IRetainCardExtractDataService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IRetainCardEtlService retainCardEtlService;
	
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
		sql.append("(select dev.DEV_TYPE_ID devTypeId,count(rc.ID) retainCount ");
		sql.append("from ATMC_RETAIN_CARD rc,DEV_INFO dev ");
		sql.append("where rc.TERMINAL_ID = dev.TERMINAL_ID AND rc.RETAIN_DATE >= ? and rc.RETAIN_DATE <= ? ");
		sql.append("GROUP BY dev.DEV_TYPE_ID) A ");
		sql.append("LEFT JOIN  ");
		sql.append("(select dev.DEV_TYPE_ID devTypeId,devType.NAME typeName,count(dev.ID) deviceCount ");
		sql.append("from DEV_INFO dev,DEV_TYPE devType ");
		sql.append("where devType.ID = dev.DEV_TYPE_ID ");
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
			retainCardEtlService.saveByWeek(day);
		}
	}

	@Override
	public void extractByMonth(Date date) {
		String sDate = DateUtils.getYM(date);
		long start = Long.parseLong(sDate+"01");
		long end = Long.parseLong(sDate+"31");
		
		StringBuilder sql = new StringBuilder();
		sql.append("select B.typeName,A.retainCount,B.deviceCount from  ");
		sql.append("(select dev.DEV_TYPE_ID devTypeId,count(rc.ID) retainCount ");
		sql.append("from ATMC_RETAIN_CARD rc,DEV_INFO dev ");
		sql.append("where rc.TERMINAL_ID = dev.TERMINAL_ID AND rc.RETAIN_DATE >= ? and rc.RETAIN_DATE <= ? ");
		sql.append("GROUP BY dev.DEV_TYPE_ID) A ");
		sql.append("LEFT JOIN  ");
		sql.append("(select dev.DEV_TYPE_ID devTypeId,devType.NAME typeName,count(dev.ID) deviceCount ");
		sql.append("from DEV_INFO dev,DEV_TYPE devType ");
		sql.append("where devType.ID = dev.DEV_TYPE_ID ");
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
			retainCardEtlService.saveByMonth(day);
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

}
