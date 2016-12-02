package com.yihuacomputer.fish.report.service.trans.etl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeDay;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeEtlService;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeMonth;
import com.yihuacomputer.fish.api.report.trans.etl.ITransTypeWeek;

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
	public List<ITransTypeWeek> getWeek(long weekOfYear) {
		return dao.findByHQL("from TransTypeWeek where date = ?", weekOfYear);
	}

	@Override
	public Long[] getWeekTotal(long weekOfYear) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(rc.TRANS_COUNT),sum(rc.TRANS_AMOUNT)");
		sql.append(" from ETL_TRANS_TYPE_WEEK rc");
		sql.append(" where rc.STAT_DATE = ?");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, weekOfYear);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each[0] != null){
				Long [] values = new Long[2];
				values[0] = Long.parseLong(each[0].toString());
				values[1] =  new Double(Double.parseDouble(each[1].toString())).longValue();
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
		sql.append(" from ETL_TRANS_TYPE_MONTH rc");
		sql.append(" where rc.STAT_DATE = ?");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, month);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			if(each != null){
				Long [] values = new Long[2];
				values[0] = each[0]==null?0l:(long)Double.parseDouble(each[0].toString());
				values[1] = each[1]==null?0l:(long)Double.parseDouble(each[1].toString());
				return values;
			}
		}
		return new Long[]{0L,0L};
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","transCode"})
	@Override
	public ITransTypeDay saveByDay(ITransTypeDay transTypeDay) {
		return dao.save(transTypeDay);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","transCode"})
	@Override
	public ITransTypeWeek saveByWeek(ITransTypeWeek transTypeWeek) {
		return dao.save(transTypeWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","transCode"})
	@Override
	public ITransTypeMonth saveByMonth(ITransTypeMonth transTypeMonth) {
		return dao.save(transTypeMonth);
	}
	

}
