package com.yihuacomputer.fish.report.service.device.etl;

import java.util.ArrayList;
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
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeek;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryWeekService;
import com.yihuacomputer.fish.machine.entity.AtmCatalog;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.report.entity.etl.DeviceCatalogSummaryWeek;

@Service
@Transactional
public class DeviceCatalogSummaryWeekService implements IDeviceCatalogSummaryWeekService {

	@Autowired
	private IGenericDao dao;

	@Override
	public IDeviceCatalogSummaryWeek make() {
		return new DeviceCatalogSummaryWeek();
	}

	@Override
	public IDeviceCatalogSummaryWeek update(IDeviceCatalogSummaryWeek dcsw) {
		return dao.update(dcsw);
	}

	@Override
	public IDeviceCatalogSummaryWeek save(IDeviceCatalogSummaryWeek dcsw) {
		return dao.save(dcsw);
	}

	@Override
	public List<IDeviceCatalogSummaryWeek> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceCatalogSummaryWeek.class);
	}

	@Override
	public IDeviceCatalogSummaryWeek get(String catalogName, String date) {
		IFilter filter = new Filter();
		filter.eq("catalog", catalogName);
		filter.eq("date", date);
		return dao.findUniqueByFilter(filter, IDeviceCatalogSummaryWeek.class);
	}

	@Override
	public Map<String, IDeviceCatalogSummaryWeek> get(String date) {
		IFilter filter = new Filter();
		filter.eq("date", date);
		List<IDeviceCatalogSummaryWeek> deviceCatalogList = dao.findByFilter(filter, IDeviceCatalogSummaryWeek.class);
		Map<String, IDeviceCatalogSummaryWeek> map = new HashMap<String, IDeviceCatalogSummaryWeek>();
		for (IDeviceCatalogSummaryWeek dcsm : deviceCatalogList) {
			map.put(dcsm.getCatalog(), dcsm);
		}
		return map;
	}

	@Override
	public void loadBaseData(Date date) {
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
		
		
		Map<String, IDeviceCatalogSummaryWeek> lastInfoMap = this.get(lastlastWeek);
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
			IDeviceCatalogSummaryWeek idcswNew = this.make();
			idcswNew.setCatalog(atmlog.getName());
			idcswNew.setAddDevNum(news);
			idcswNew.setAllAddDevNum(idcsw == null ? news : idcsw.getAllAddDevNum() + news);
			idcswNew.setAllScrappedDevNum(0);
			idcswNew.setDate(week);
			idcswNew.setNum(all);
			idcswNew.setScrappedDevNum(0);
			this.save(idcswNew);
		}
	}

	@Override
	public Map<String, List<IDeviceCatalogSummaryWeek>> getWeek(int weekOfYear, int days) {
		IFilter filter = new Filter();
		filter.ge("date", String.valueOf(weekOfYear - days));
		filter.le("date", String.valueOf(weekOfYear));
		List<IDeviceCatalogSummaryWeek> deviceCatalogList = dao.findByFilter(filter, IDeviceCatalogSummaryWeek.class);
		Map<String,List<IDeviceCatalogSummaryWeek>> maps = new HashMap<String,List<IDeviceCatalogSummaryWeek>>();
		for(IDeviceCatalogSummaryWeek each : deviceCatalogList){
			String catalog = each.getCatalog();
			if(!maps.containsKey(catalog)){
				maps.put(catalog, new ArrayList<IDeviceCatalogSummaryWeek>());
			}
			maps.get(catalog).add(each);
		}
		return maps;
	}

}
