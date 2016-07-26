package com.yihuacomputer.fish.analysis.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.domain.spring.DataSource;
import com.yihuacomputer.domain.spring.DataSources;
import com.yihuacomputer.fish.analysis.entity.device.DeviceCatalogSummaryMonth;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonth;
import com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.machine.entity.AtmCatalog;
import com.yihuacomputer.fish.machine.entity.Device;

@Service
@Transactional
public class DeviceCatalogSummaryMonthService implements IDeviceCatalogSummaryMonthService {
	
	@Autowired 
	private IPersonService personService;
//	
//	@Autowired
//	private IDeviceService deviceService;
	
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IDeviceCatalogSummaryMonth make() {
		return new DeviceCatalogSummaryMonth();
	}

	public void loadBaseDate(Date date){
		//yyyy-mm-dd
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());
		//将时间定位至要汇总日期的上个月
		calendar.add(Calendar.MONTH, -2);
		//获取上次汇总日期
		String lastDate = DateUtils.getDate(calendar.getTime()).substring(0,7);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		//此次汇总时间点
		date = calendar.getTime();
		String dateStr = DateUtils.getDate(date).substring(0,7);
		Map<String,IDeviceCatalogSummaryMonth> lastInfoMap = this.get(lastDate);
		StringBuffer hqlSb = new StringBuffer();
		hqlSb.append("select device.devType.devCatalog,count(device.id), ");
//		hqlSb.append("sum(case when device.installDate<? then 1 else 0 end)  ,");
		hqlSb.append("sum(case when device.installDate>=? then 1 else 0 end)  ");
		hqlSb.append("from ").append(Device.class.getSimpleName());
//		.append(" where device.installDate<=?");
		hqlSb.append(" device  group by device.devType.devCatalog.name ");
		List<Object> list = dao.findByHQL(hqlSb.toString(), new Object[]{date});
		for(Object objectResult:list){
			Object[] objects = (Object[])objectResult;
			AtmCatalog atmlog = (AtmCatalog)objects[0];
			int  all = Integer.parseInt(String.valueOf(objects[1]));
//			int  old = Integer.parseInt(String.valueOf(objects[2]));
			int  news = Integer.parseInt(String.valueOf(objects[2]));
			IDeviceCatalogSummaryMonth idcsm = lastInfoMap.get(atmlog.getName());
			IDeviceCatalogSummaryMonth idcsmNew = this.make();
			idcsmNew.setCatalog(atmlog.getName());
			idcsmNew.setAddDevNum(news);
			idcsmNew.setAllAddDevNum(idcsm==null?news:idcsm.getAllAddDevNum()+news);
			idcsmNew.setAllScrappedDevNum(0);
			idcsmNew.setDate(dateStr);
			idcsmNew.setNum(all);
			idcsmNew.setScrappedDevNum(0);
			this.save(idcsmNew);
		}
	}
	
	@Override
	public IDeviceCatalogSummaryMonth update(IDeviceCatalogSummaryMonth dcsm) {
		return null;
	}

	@Override
	@DataSource(value=DataSources.Analysis)
	public IDeviceCatalogSummaryMonth save(IDeviceCatalogSummaryMonth dcsm) {
		return dao.save(dcsm);
	}

	public List<IDeviceCatalogSummaryMonth> list(IFilter filter) {

		return null;
	}

	@Override
	public IDeviceCatalogSummaryMonth get(String catalogName, String date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.yihuacomputer.fish.api.analysis.device.IDeviceCatalogSummaryMonthService#get(java.util.Date)
	 * 获取指定日期分类统计信息
	 */
	@DataSource(value=DataSources.Analysis)
	public Map<String,IDeviceCatalogSummaryMonth> get(String date){
		IFilter filter = new Filter();
		filter.eq("date", date);
		List<IDeviceCatalogSummaryMonth> deviceCatalogList = dao.findByFilter(filter, IDeviceCatalogSummaryMonth.class);
		Map<String,IDeviceCatalogSummaryMonth> map = new HashMap<String,IDeviceCatalogSummaryMonth>();
		for(IDeviceCatalogSummaryMonth dcsm:deviceCatalogList){
			map.put(dcsm.getCatalog(),dcsm);
		}
		return map;
	}
	public static void main(String args[]){
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();//DateUtils.getDate("2015-01-07");
		long datelong = date.getTime()/(1000l*60*60*24)*(1000l*60*60*24);
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+1"));
		calendar.setTimeInMillis(datelong);
		System.out.println(calendar.getTime().toString());
	}

}
