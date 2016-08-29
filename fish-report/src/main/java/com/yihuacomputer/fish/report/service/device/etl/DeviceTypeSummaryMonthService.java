package com.yihuacomputer.fish.report.service.device.etl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.machine.entity.Device;

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

	@Override
	public void loadBaseDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date toDate = calendar.getTime();
		// 将时间定位至要汇总日期的上个月
		calendar.add(Calendar.MONTH, -2);
		// 获取上次汇总日期
		String lastDate = DateUtils.getDate(calendar.getTime()).substring(0, 7);
		calendar.add(Calendar.MONTH, 1);
		// 此次汇总时间点
		Date fromDate = calendar.getTime();
		String dateStr = DateUtils.getDate(fromDate).substring(0, 7);
		Map<String, IDeviceTypeSummaryMonth> lastInfoMap = this.get(lastDate);
		StringBuffer hqlSb = new StringBuffer();
		hqlSb.append("select device.devType.name,count(device.id), ");
		hqlSb.append("sum(case when device.installDate>=? then 1 else 0 end)  ");
		hqlSb.append("from ").append(Device.class.getSimpleName());
		hqlSb.append(" device where device.installDate<? group by device.devType.name ");
		List<Object> list = dao.findByHQL(hqlSb.toString(), new Object[] { fromDate,toDate });
		for (Object objectResult : list) {
			Object[] objects = (Object[]) objectResult;
			String devType = String.valueOf(objects[0]);
			int all = Integer.parseInt(String.valueOf(objects[1]));
			int news = Integer.parseInt(String.valueOf(objects[2]));
			IDeviceTypeSummaryMonth idtsm = lastInfoMap.get(devType);
			IDeviceTypeSummaryMonth idtsmNew = this.make();
			idtsmNew.setDevType(devType);
			idtsmNew.setAddDevNum(news);
			idtsmNew.setAllAddDevNum(idtsm == null ? news : idtsm.getAllAddDevNum() + news);
			idtsmNew.setAllScrappedDevNum(0);
			idtsmNew.setDate(dateStr);
			idtsmNew.setNum(all);
			idtsmNew.setScrappedDevNum(0);
			this.save(idtsmNew);
		}
	}

}
