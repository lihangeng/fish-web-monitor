package com.yihuacomputer.fish.report.service.device.etl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.report.entity.etl.DeviceTypeSummaryWeek;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class DeviceTypeSummaryWeekService implements IDeviceTypeSummaryWeekService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IDeviceTypeSummaryWeek make() {
		return new DeviceTypeSummaryWeek();
	}

	@Override
	public IDeviceTypeSummaryWeek update(IDeviceTypeSummaryWeek dtsw) {
		return dao.update(dtsw);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"devType","date"})
	@Override
	public IDeviceTypeSummaryWeek save(IDeviceTypeSummaryWeek dtsw) {
		return dao.save(dtsw);
	}

	@Override
	public List<IDeviceTypeSummaryWeek> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceTypeSummaryWeek.class);
	}

	@Override
	public IDeviceTypeSummaryWeek get(String devType, String date) {
		IFilter filter = new Filter();
		filter.eq("devType", devType);
		filter.eq("date", date);
		return dao.findUniqueByFilter(filter, IDeviceTypeSummaryWeek.class);
	}

	@Override
	public Map<String, IDeviceTypeSummaryWeek> get(String date) {
		IFilter filter = new Filter();
		filter.eq("date", date);
		List<IDeviceTypeSummaryWeek> deviceTypeList = dao.findByFilter(filter, IDeviceTypeSummaryWeek.class);
		Map<String, IDeviceTypeSummaryWeek> map = new HashMap<String, IDeviceTypeSummaryWeek>();
		for (IDeviceTypeSummaryWeek dtsw : deviceTypeList) {
			map.put(dtsw.getDevType(), dtsw);
		}
		return map;
	}

}
