package com.yihuacomputer.fish.report.service.trans.etl;

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
		Long [] dates = DateUtils.getFirstAndLastDayofWeek(date);
		SQLQuery query = dao.getSQLQuery(getTransTypeSql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			ITransTypeWeek day = new TransTypeWeek();
			day.setDate(DateUtils.getWeek(date));
			day.setTransCode(each[0].toString());
			day.setTransAmount(Double.parseDouble(each[1].toString()));
			day.setTransCount(Long.parseLong(each[2].toString()));
			dao.save(day);
		}
		
	}
	private String getTransTypeSql(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tr.TRANS_CODE,SUM(tr.TRANS_AMOUNT),SUM(tr.TRANS_COUNT) ");
		sql.append("FROM etl_trans_type_day tr ");
		sql.append("WHERE tr.STAT_DATE >= ?  and tr.STAT_DATE <= ? ");
		sql.append("GROUP BY tr.TRANS_CODE");
		return sql.toString();
	}

	@Override
	public void extractByMonth(Date date) {
		Long [] dates = DateUtils.getFirstAndLastDayofMonth(date);
		SQLQuery query = dao.getSQLQuery(getTransTypeSql());
		query.setLong(0, dates[0]);
		query.setLong(1, dates[1]);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			ITransTypeMonth day = new TransTypeMonth();
			day.setDate(Long.parseLong(DateUtils.getYM(date)));
			day.setTransCode(each[0].toString());
			day.setTransAmount(Double.parseDouble(each[1].toString()));
			day.setTransCount(Long.parseLong(each[2].toString()));
			dao.save(day);
		}
	}

	@Override
	public List<ITransTypeWeek> getWeek(long weekOfYear) {
		return dao.findByHQL("from TransTypeWeek where date = ?", weekOfYear);
	}

	@Override
	public Long[] getWeekTotal(long weekOfYear) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(rc.TRANS_COUNT),sum(rc.TRANS_AMOUNT)");
		sql.append(" from etl_trans_type_week rc");
		sql.append(" where rc.STAT_DATE = ?");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, weekOfYear);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each != null){
				Long [] values = new Long[]{};
				values[0] = Long.parseLong(each[0].toString());
				values[1] = Long.parseLong(each[1].toString());
				return values;
			}
		}
		return new Long[]{0L,0L};
	}

	@Override
	public List<ITransTypeMonth> getMonth(long month) {
		return dao.findByHQL("from TransTypeMonth where date = ?", month);
	}

	@Override
	public Long[] getMonthTotal(long month) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(rc.TRANS_COUNT),sum(rc.TRANS_AMOUNT)");
		sql.append(" from etl_trans_type_month rc");
		sql.append(" where rc.STAT_DATE = ?");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, month);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each != null){
				Long [] values = new Long[]{};
				values[0] = Long.parseLong(each[0].toString());
				values[1] = Long.parseLong(each[1].toString());
				return values;
			}
		}
		return new Long[]{0L,0L};
	}
	

}
