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
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceExtractDataMonthService;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceTypeSummaryMonthService;
import com.yihuacomputer.fish.machine.entity.AtmCatalog;
import com.yihuacomputer.fish.machine.entity.Device;

/**
 * @author YiHua
 *
 */
@Service
@Transactional
public class DeviceExtractDataMonthService implements IDeviceExtractDataMonthService {

	@Autowired
	private IGenericDao dao;
	
	@Autowired
	private IDeviceCatalogSummaryMonthService deviceCatalogSummaryMonthService;
	
	@Autowired
	private IDeviceTypeSummaryMonthService deviceTypeSummaryMonthService;
	
	@Override
	public void loadCatalogBaseData(Date date) {
		//yyyy-mm-dd
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(date.getTime());

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		Date toDate = calendar.getTime();
		//将时间定位至要汇总日期的上个月
		calendar.add(Calendar.MONTH, -2);
		//获取上次汇总日期
		String lastDate = DateUtils.getYM(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		//此次汇总时间点
		Date fromDate = calendar.getTime();
		String dateStr = DateUtils.getYM(fromDate);
//			String dateStr = DateUtils.getDate(fromDate).substring(0,7);
		Map<String,IDeviceCatalogSummaryMonth> lastInfoMap = deviceCatalogSummaryMonthService.get(lastDate);
		StringBuffer hqlSb = new StringBuffer();
		hqlSb.append("select device.devType.devCatalog,count(device.id), ");
		hqlSb.append("sum(case when device.installDate>=? then 1 else 0 end)  ");
		hqlSb.append("from ").append(Device.class.getSimpleName());
		hqlSb.append(" device  where device.installDate<? group by device.devType.devCatalog.name ");
		List<Object> list = dao.findByHQL(hqlSb.toString(), new Object[]{fromDate,toDate});
		for(Object objectResult:list){
			Object[] objects = (Object[])objectResult;
			AtmCatalog atmlog = (AtmCatalog)objects[0];
			int  all = Integer.parseInt(String.valueOf(objects[1]));
			int  news = Integer.parseInt(String.valueOf(objects[2]));
			IDeviceCatalogSummaryMonth idcsm = lastInfoMap.get(atmlog.getName());
			IDeviceCatalogSummaryMonth idcsmNew = deviceCatalogSummaryMonthService.make();
			idcsmNew.setCatalog(atmlog.getName());
			idcsmNew.setAddDevNum(news);
			idcsmNew.setAllAddDevNum(idcsm==null?news:idcsm.getAllAddDevNum()+news);
			idcsmNew.setAllScrappedDevNum(0);
			idcsmNew.setDate(dateStr);
			idcsmNew.setNum(all);
			idcsmNew.setScrappedDevNum(0);
			deviceCatalogSummaryMonthService.save(idcsmNew);
		}
		
	}

	@Override
	public void loadTypeBaseData(Date date) {
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
		String lastDate = DateUtils.getYM(calendar.getTime());
		calendar.add(Calendar.MONTH, 1);
		// 此次汇总时间点
		Date fromDate = calendar.getTime();
		String dateStr = DateUtils.getYM(fromDate);
		Map<String, IDeviceTypeSummaryMonth> lastInfoMap = deviceTypeSummaryMonthService.get(lastDate);
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
			IDeviceTypeSummaryMonth idtsmNew = deviceTypeSummaryMonthService.make();
			idtsmNew.setDevType(devType);
			idtsmNew.setAddDevNum(news);
			idtsmNew.setAllAddDevNum(idtsm == null ? news : idtsm.getAllAddDevNum() + news);
			idtsmNew.setAllScrappedDevNum(0);
			idtsmNew.setDate(dateStr);
			idtsmNew.setNum(all);
			idtsmNew.setScrappedDevNum(0);
			deviceTypeSummaryMonthService.save(idtsmNew);
		}
	}

}
