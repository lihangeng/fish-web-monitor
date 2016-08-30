package com.yihuacomputer.fish.report.service.trans.etl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeDay;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeMonth;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeWeek;
import com.yihuacomputer.fish.report.entity.etl.TransTypeDay;
import com.yihuacomputer.fish.report.entity.etl.TransTypeMonth;
import com.yihuacomputer.fish.report.entity.etl.TransTypeWeek;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Service
@Transactional
public class TransTypeEtlService implements ITransTypeEtlService{
	
	@Autowired
	private IGenericDao dao;

	@Override
	public void extractByDay(Date date) {
		long lDate = Long.parseLong(DateUtils.getDateShort(date)); 
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.TRANS_DATE,tr.TRANS_CODE,SUM(tr.AMT),COUNT(tr.TRANS_DATE) ");
		sql.append("FROM atmc_transaction tr ");
		sql.append("WHERE tr.TRANS_DATE = ? ");
		sql.append("GROUP BY tr.TRANS_CODE ");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, lDate);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			ITransTypeDay day = new TransTypeDay();
			day.setDate(lDate);
			day.setTransCode(each[1].toString());
			day.setTransAmount(Double.parseDouble(each[2].toString()));
			day.setTransCount(Long.parseLong(each[3].toString()));
			dao.save(day);
		}
	}

	@Override
	public void extractByWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(cal);
		Date endDate = DateUtils.getLastDayOfWeek(cal);
		long start = Long.parseLong(DateUtils.getDateShort(startDate));
		long end = Long.parseLong(DateUtils.getDateShort(endDate));
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.TRANS_CODE,SUM(tr.TRANS_AMOUNT),SUM(tr.TRANS_COUNT) ");
		sql.append("FROM etl_trans_type_day tr ");
		sql.append("WHERE tr.STAT_DATE >= ?  and tr.STAT_DATE <= ? ");
		sql.append("GROUP BY tr.TRANS_CODE");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, start);
		query.setLong(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			ITransTypeWeek day = new TransTypeWeek();
			day.setDate(Long.parseLong(DateUtils.get(date, "yyyyww")));
			day.setTransCode(each[0].toString());
			day.setTransAmount(Double.parseDouble(each[1].toString()));
			day.setTransCount(Long.parseLong(each[2].toString()));
			dao.save(day);
		}
		
	}

	@Override
	public void extractByMonth(Date date) {
		String sDate = DateUtils.getYM(date);//yyyyMM
		long start = Long.parseLong(sDate + "01");
		long end = Long.parseLong(sDate + "31");
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.TRANS_CODE,SUM(tr.TRANS_AMOUNT),SUM(tr.TRANS_COUNT) ");
		sql.append("FROM etl_trans_type_day tr ");
		sql.append("WHERE tr.STAT_DATE >= ?  and tr.STAT_DATE <= ? ");
		sql.append("GROUP BY tr.TRANS_CODE");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, start);
		query.setLong(1, end);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			ITransTypeMonth day = new TransTypeMonth();
			day.setDate(Long.parseLong(sDate));
			day.setTransCode(each[0].toString());
			day.setTransAmount(Double.parseDouble(each[1].toString()));
			day.setTransCount(Long.parseLong(each[2].toString()));
			dao.save(day);
		}
	}
	

}
