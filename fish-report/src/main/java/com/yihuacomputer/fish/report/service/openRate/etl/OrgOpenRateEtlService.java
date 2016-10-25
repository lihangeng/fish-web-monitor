package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.filter.Filter;
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
