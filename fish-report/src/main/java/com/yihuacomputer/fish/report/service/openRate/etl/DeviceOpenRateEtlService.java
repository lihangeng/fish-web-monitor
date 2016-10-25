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
	public List<IDeviceOpenRateWeek> getTopDeviceWeek(long weekOfYear, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", weekOfYear);
		filter.descOrder("openRate");
		IPageResult<IDeviceOpenRateWeek> page = dao.page(0, limit, filter, DeviceOpenRateWeek.class);
		return page.list();
	}

	@Override
	public List<IDeviceOpenRateWeek> getLastDeviceWeek(long weekOfYear, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", weekOfYear);
		filter.order("openRate");
		IPageResult<IDeviceOpenRateWeek> page = dao.page(0, limit, filter, DeviceOpenRateWeek.class);
		return page.list();
	}
	
	@Override
	public List<IDeviceOpenRateMonth> getTopDeviceMonth(long month, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", month);
		filter.descOrder("openRate");
		IPageResult<IDeviceOpenRateMonth> page = dao.page(0, limit, filter, DeviceOpenRateMonth.class);
		return page.list();
	}


	@Override
	public List<IDeviceOpenRateMonth> getLastDeviceMonth(long month, int limit) {
		IFilter filter = new Filter();
		filter.eq("date", month);
		filter.order("openRate");
		IPageResult<IDeviceOpenRateMonth> page = dao.page(0, limit, filter, DeviceOpenRateMonth.class);
		return page.list();
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"terminalId","date"})
	@Override
	public IDeviceOpenRateWeek saveByWeek(IDeviceOpenRateWeek deviceOpenRateWeek) {
		return dao.save(deviceOpenRateWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"terminalId","date"})
	@Override
	public IDeviceOpenRateMonth saveByMonth(IDeviceOpenRateMonth deviceOpenRateMonth) {
		return dao.save(deviceOpenRateMonth);
	}

}
