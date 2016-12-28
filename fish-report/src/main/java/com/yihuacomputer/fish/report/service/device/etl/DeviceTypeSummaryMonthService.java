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
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.report.entity.etl.DeviceTypeSummaryMonth;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class DeviceTypeSummaryMonthService implements IDeviceTypeSummaryMonthService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IDeviceTypeSummaryMonth make() {
		return new DeviceTypeSummaryMonth();
	}

	@Override
	public IDeviceTypeSummaryMonth update(IDeviceTypeSummaryMonth dtsm) {
		return dao.update(dtsm);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"devType","date"})
	@Override
	public IDeviceTypeSummaryMonth save(IDeviceTypeSummaryMonth dtsm) {
		return dao.save(dtsm);
	}

	@Override
	public List<IDeviceTypeSummaryMonth> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceTypeSummaryMonth.class);
	}

	@Override
	public IDeviceTypeSummaryMonth get(String devtype, String date) {
		IFilter filter = new Filter();
		filter.eq("devType", devtype);
		filter.eq("date", date);
		return dao.findUniqueByFilter(filter, IDeviceTypeSummaryMonth.class);
	}

	@Override
	public Map<String, IDeviceTypeSummaryMonth> get(String date) {
		IFilter filter = new Filter();
		filter.eq("date", date);
		List<IDeviceTypeSummaryMonth> deviceTypeList = dao.findByFilter(filter, IDeviceTypeSummaryMonth.class);
		Map<String, IDeviceTypeSummaryMonth> map = new HashMap<String, IDeviceTypeSummaryMonth>();
		for (IDeviceTypeSummaryMonth dtsm : deviceTypeList) {
			map.put(dtsm.getDevType(), dtsm);
		}
		return map;
	}
}
