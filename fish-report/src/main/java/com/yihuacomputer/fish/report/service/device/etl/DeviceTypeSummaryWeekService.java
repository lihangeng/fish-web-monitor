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
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.report.entity.etl.DeviceTypeSummaryWeek;

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

	@Override
	public void loadBaseData(Date date) {
		// yyyy-mm-dd
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		// 将时间定位至 当前周的周一
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date toDate = calendar.getTime();
		// 当前周的两周前
		calendar.add(Calendar.DAY_OF_MONTH, -14);
		// 获取上次汇总日期
		String lastDate = DateUtils.getDate(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		// 此次汇总时间点
		Date fromDate = calendar.getTime();
		String dateStr = DateUtils.getDate(fromDate);
		Map<String, IDeviceTypeSummaryWeek> lastInfoMap = this.get(lastDate);
		StringBuffer hqlSb = new StringBuffer();
		hqlSb.append("select device.devType.name,count(device.id), ");
		hqlSb.append("sum(case when device.installDate>=? then 1 else 0 end)  ");
		hqlSb.append("from ").append(Device.class.getSimpleName());
		hqlSb.append(" device  where device.installDate<?  group by device.devType.name ");
		List<Object> list = dao.findByHQL(hqlSb.toString(), new Object[] { fromDate,toDate });
		for (Object objectResult : list) {
			Object[] objects = (Object[]) objectResult;
			String devType = String.valueOf(objects[0]);
			int all = Integer.parseInt(String.valueOf(objects[1]));
			int news = Integer.parseInt(String.valueOf(objects[2]));
			IDeviceTypeSummaryWeek idtsw = lastInfoMap.get(devType);
			IDeviceTypeSummaryWeek idtswNew = this.make();
			idtswNew.setDevType(devType);
			idtswNew.setAddDevNum(news);
			idtswNew.setAllAddDevNum(idtsw == null ? news : idtsw.getAllAddDevNum() + news);
			idtswNew.setAllScrappedDevNum(0);
			idtswNew.setDate(dateStr);
			idtswNew.setNum(all);
			idtswNew.setScrappedDevNum(0);
			this.save(idtswNew);
		}
	}

}
