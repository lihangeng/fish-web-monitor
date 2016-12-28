package com.yihuacomputer.fish.report.service.device.etl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataWeekService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryWeekService;
import com.yihuacomputer.fish.machine.entity.AtmCatalog;
import com.yihuacomputer.fish.machine.entity.Device;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class DeviceExtractDataWeekService implements IDeviceExtractDataWeekService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IDeviceCatalogSummaryWeekService deviceCatalogSummaryWeekService;
	
	@Autowired
	private IDeviceTypeSummaryWeekService deviceTypeSummaryWeekService;
	
	@Override
	public void loadCatalogBaseData(Date date) {
		// yyyy-mm-dd
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		//当前周的两周前
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		// 获取上次汇总日期
		String lastlastWeek=DateUtils.get(calendar.getTime(), "yyyyww");
		// 此次汇总时间点
		String week=DateUtils.get(date, "yyyyww");

		calendar.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(calendar);
		Date endDate = DateUtils.getLastDayOfWeek(calendar);
		
		
		Map<String, IDeviceCatalogSummaryWeek> lastInfoMap = deviceCatalogSummaryWeekService.get(lastlastWeek);
		StringBuffer hqlSb = new StringBuffer();
		hqlSb.append("select device.devType.devCatalog,count(device.id), ");
		hqlSb.append("sum(case when device.installDate>=? then 1 else 0 end)  ");
		hqlSb.append("from ").append(Device.class.getSimpleName());
		hqlSb.append(" device where device.installDate<?  group by device.devType.devCatalog.name ");
		List<Object> list = dao.findByHQL(hqlSb.toString(), new Object[] { startDate,endDate });
		for (Object objectResult : list) {
			Object[] objects = (Object[]) objectResult;
			AtmCatalog atmlog = (AtmCatalog) objects[0];
			int all = Integer.parseInt(String.valueOf(objects[1]));
			int news = Integer.parseInt(String.valueOf(objects[2]));
			IDeviceCatalogSummaryWeek idcsw = lastInfoMap.get(atmlog.getName());
			IDeviceCatalogSummaryWeek idcswNew = deviceCatalogSummaryWeekService.make();
			idcswNew.setCatalog(atmlog.getName());
			idcswNew.setAddDevNum(news);
			idcswNew.setAllAddDevNum(idcsw == null ? news : idcsw.getAllAddDevNum() + news);
			idcswNew.setAllScrappedDevNum(0);
			idcswNew.setDate(week);
			idcswNew.setNum(all);
			idcswNew.setScrappedDevNum(0);
			deviceCatalogSummaryWeekService.save(idcswNew);
		}
	}

	@Override
	public void loadTypeBaseData(Date date) {
		// yyyy-mm-dd
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		//当前周的两周前
		calendar.add(Calendar.DAY_OF_MONTH, -7);
		// 获取上次汇总日期
		String lastlastWeek=DateUtils.get(calendar.getTime(), "yyyyww");
		// 此次汇总时间点
		String week=DateUtils.get(date, "yyyyww");
		calendar.setTime(date);
		Date startDate = DateUtils.getFirstDayOfWeek(calendar);
		Date endDate = DateUtils.getLastDayOfWeek(calendar);
		Map<String, IDeviceTypeSummaryWeek> lastInfoMap = deviceTypeSummaryWeekService.get(lastlastWeek);
		StringBuffer hqlSb = new StringBuffer();
		hqlSb.append("select device.devType.name,count(device.id), ");
		hqlSb.append("sum(case when device.installDate>=? then 1 else 0 end)  ");
		hqlSb.append("from ").append(Device.class.getSimpleName());
		hqlSb.append(" device  where device.installDate<?  group by device.devType.name ");
		List<Object> list = dao.findByHQL(hqlSb.toString(), new Object[] { startDate,endDate });
		for (Object objectResult : list) {
			Object[] objects = (Object[]) objectResult;
			String devType = String.valueOf(objects[0]);
			int all = Integer.parseInt(String.valueOf(objects[1]));
			int news = Integer.parseInt(String.valueOf(objects[2]));
			IDeviceTypeSummaryWeek idtsw = lastInfoMap.get(devType);
			IDeviceTypeSummaryWeek idtswNew = deviceTypeSummaryWeekService.make();
			idtswNew.setDevType(devType);
			idtswNew.setAddDevNum(news);
			idtswNew.setAllAddDevNum(idtsw == null ? news : idtsw.getAllAddDevNum() + news);
			idtswNew.setAllScrappedDevNum(0);
			idtswNew.setDate(week);
			idtswNew.setNum(all);
			idtswNew.setScrappedDevNum(0);
			deviceTypeSummaryWeekService.save(idtswNew);
		}
	}

}
