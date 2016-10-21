package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.Date;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.NumUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IOrgOpenRateWeek;
import com.yihuacomputer.fish.report.entity.etl.OrgOpenRateMonth;
import com.yihuacomputer.fish.report.entity.etl.OrgOpenRateWeek;

/**
 * 
 * @author xuxiang
 * @since 2.1.1.1
 */
@Service
@Transactional
public class OrgOpenRateEtlService implements IOrgOpenRateEtlService{

	@Autowired
	private IGenericDao dao;
	
	@Override
	public void extractByWeek(Date date) {
		Long weekOfYear = DateUtils.getWeek(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select org_code,org_name, sum(dor.OPENTIMES) OPENTIMES,");
		sql.append("sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL,dor.start_date,dor.end_date ");
		sql.append("from etl_device_open_rate_week dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by org_code");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, weekOfYear);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IOrgOpenRateWeek week = new OrgOpenRateWeek();
			week.setDate(weekOfYear);
			week.setOrgCode(each[0].toString());
			week.setOrgName(each[1].toString());
			week.setOpenTimes(Long.parseLong(each[2].toString()));
			week.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			week.setStartDate(each[4].toString());
			week.setEndDate(each[5].toString());
			week.setOpenRate(NumUtils.getPercent(week.getHealthyTimeReal(),week.getOpenTimes()));
			this.saveByWeek(week);
		}
	}

	@Override
	public void extractByMonth(Date date) {
		Long ym = DateUtils.getLongYM(date);
		StringBuilder sql = new StringBuilder();
		sql.append("select org_code,org_name, sum(dor.OPENTIMES) OPENTIMES,sum(dor.HEALTHY_TIMEREAL) HEALTHY_TIMEREAL ");
		sql.append("from etl_device_open_rate_month dor ");
		sql.append("where dor.STAT_DATE = ? ");
		sql.append("group by org_code");
		
		SQLQuery query = dao.getSQLQuery(sql.toString());
		query.setLong(0, ym);
		List<?> lists = query.list();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IOrgOpenRateMonth dorMonth = new OrgOpenRateMonth();
			dorMonth.setDate(ym);
			dorMonth.setOrgCode(each[0].toString());
			dorMonth.setOrgName(each[1].toString());
			dorMonth.setOpenTimes(Long.parseLong(each[2].toString()));
			dorMonth.setHealthyTimeReal(Long.parseLong(each[3].toString()));
			dorMonth.setOpenRate(NumUtils.getPercent(dorMonth.getHealthyTimeReal(),dorMonth.getOpenTimes()));
			this.saveByMonth(dorMonth);
		}
	}

	@Override
	public List<IOrgOpenRateWeek> getTopOrgWeek(long weekOfYear, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", weekOfYear);
		filter.descOrder("openRate");
		IPageResult<IOrgOpenRateWeek> page = dao.page(0, limit, filter, OrgOpenRateWeek.class);
		return page.list();
	}

	@Override
	public List<IOrgOpenRateMonth> getTopOrgMonth(long month, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", month);
		filter.descOrder("openRate");
		IPageResult<IOrgOpenRateMonth> page = dao.page(0, limit, filter, OrgOpenRateMonth.class);
		return page.list();
	}

	@Override
	public List<IOrgOpenRateWeek> getLastOrgWeek(long weekOfYear, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", weekOfYear);
		filter.order("openRate");
		IPageResult<IOrgOpenRateWeek> page = dao.page(0, limit, filter, OrgOpenRateWeek.class);
		return page.list();
	}

	@Override
	public List<IOrgOpenRateMonth> getLastOrgMonth(long month, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", month);
		filter.order("openRate");
		IPageResult<IOrgOpenRateMonth> page = dao.page(0, limit, filter, OrgOpenRateMonth.class);
		return page.list();
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"orgCode","date"})
	@Override
	public IOrgOpenRateWeek saveByWeek(IOrgOpenRateWeek orgOpenRateWeek) {
		return dao.save(orgOpenRateWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"orgCode","date"})
	@Override
	public IOrgOpenRateMonth saveByMonth(IOrgOpenRateMonth orgOpenRateMonth) {
		return dao.save(orgOpenRateMonth);
	}

}
