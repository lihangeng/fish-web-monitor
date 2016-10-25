package com.yihuacomputer.fish.report.service.device.etl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardEtlService;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardMonth;
import com.yihuacomputer.fish.api.report.device.etl.IRetainCardWeek;

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
				values[0] = each[0]==null?0l:Long.parseLong(each[0].toString());
				values[1] = each[1]==null?0l:Long.parseLong(each[1].toString());
				values[2] = each[2]==null?0l:Long.parseLong(each[2].toString());
				return values;
			}
		}
		return new Long[]{0L,0L,0L};
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"devType","date"})
	@Override
	public IRetainCardWeek saveByWeek(IRetainCardWeek retainCardWeek) {
		return dao.save(retainCardWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"devType","date"})
	@Override
	public IRetainCardMonth saveByMonth(IRetainCardMonth retainCardMonth) {
		return dao.save(retainCardMonth);
	}

}
