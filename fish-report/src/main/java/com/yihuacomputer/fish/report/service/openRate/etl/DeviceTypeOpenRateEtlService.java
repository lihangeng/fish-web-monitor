package com.yihuacomputer.fish.report.service.openRate.etl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateEtlService;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateMonth;
import com.yihuacomputer.fish.api.report.openRate.etl.IDeviceTypeOpenRateWeek;

/**
 * 设备型号开机率统计服务的实现
 * @author xuxiang
 * @since 2.1.1.1
 */
@Service
@Transactional
public class DeviceTypeOpenRateEtlService implements IDeviceTypeOpenRateEtlService{

	@Autowired
	private IGenericDao dao;
	
	@Override
	public List<IDeviceTypeOpenRateWeek> getDeviceTypeWeek(long weekOfYear) {
		IFilter filter = new Filter();
		filter.eq("date", weekOfYear);
		filter.descOrder("openRate");
		return dao.findByFilter(filter, IDeviceTypeOpenRateWeek.class);
	}

	@Override
	public List<IDeviceTypeOpenRateMonth> getDeviceTypeMonth(long month) {
		IFilter filter = new Filter();
		filter.eq("date", month);
		filter.descOrder("openRate");
		return dao.findByFilter(filter, IDeviceTypeOpenRateMonth.class);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","devType"})
	@Override
	public IDeviceTypeOpenRateWeek saveByWeek(IDeviceTypeOpenRateWeek deviceTypeOpenRateWeek) {
		return dao.save(deviceTypeOpenRateWeek);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"date","devType"})
	@Override
	public IDeviceTypeOpenRateMonth saveByMonth(IDeviceTypeOpenRateMonth deviceTypeOpenRateMonth) {
		return dao.save(deviceTypeOpenRateMonth);
	}

}
