package com.yihuacomputer.fish.report.service.device.etl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.domain.spring.DataSource;
import com.yihuacomputer.domain.spring.DataSources;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonth;
import com.yihuacomputer.fish.api.report.device.etl.IDeviceCatalogSummaryMonthService;
import com.yihuacomputer.fish.report.entity.etl.DeviceCatalogSummaryMonth;

@Service
@Transactional
public class DeviceCatalogSummaryMonthService implements IDeviceCatalogSummaryMonthService {
	
	
	@Autowired
	private IGenericDao dao;
	
	@Override
	public IDeviceCatalogSummaryMonth make() {
		return new DeviceCatalogSummaryMonth();
	}

	@Override
	public IDeviceCatalogSummaryMonth update(IDeviceCatalogSummaryMonth dcsm) {
		return dao.update(dcsm);
	}

	@SaveMethodDescrible(isUpdate=true,keyName={"catalog","date"})
	@Override
	public IDeviceCatalogSummaryMonth save(IDeviceCatalogSummaryMonth dcsm) {
		return dao.save(dcsm);
	}

	public List<IDeviceCatalogSummaryMonth> list(IFilter filter) {
		return dao.findByFilter(filter, IDeviceCatalogSummaryMonth.class);
	}

	@Override
	public IDeviceCatalogSummaryMonth get(String catalogName, String date) {
		IFilter filter = new Filter();
		filter.eq("catalog", catalogName);
		filter.eq("date", date);
		return dao.findUniqueByFilter(filter, IDeviceCatalogSummaryMonth.class);
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

	@Override
	public Map<String, List<IDeviceCatalogSummaryMonth>> getMonth(int month, int months) {
		IFilter filter = new Filter();
		filter.ge("date", String.valueOf(month - months));
		filter.le("date", String.valueOf(month));
		List<IDeviceCatalogSummaryMonth> deviceCatalogList = dao.findByFilter(filter, IDeviceCatalogSummaryMonth.class);
		Map<String,List<IDeviceCatalogSummaryMonth>> maps = new HashMap<String,List<IDeviceCatalogSummaryMonth>>();
		for(IDeviceCatalogSummaryMonth each : deviceCatalogList){
			String catalog = each.getCatalog();
			if(!maps.containsKey(catalog)){
				maps.put(catalog, new ArrayList<IDeviceCatalogSummaryMonth>());
			}
			maps.get(catalog).add(each);
		}
		return maps;

	}

	@Override
	public List<IDeviceCatalogSummaryMonth> getAddAndScrp(int month, int lastMonth) {
		StringBuilder sql = new StringBuilder();
		sql.append("select dev.SUMMARY_DATE,sum(dev.ADD_NUM),sum(dev.SCRAPPED_NUM)");
		sql.append(" FROM dev_catalog_summary_month dev");
		sql.append(" WHERE dev.SUMMARY_DATE <=").append(String.valueOf(month));
		sql.append(" AND dev.SUMMARY_DATE >").append(String.valueOf(lastMonth)).append(" GROUP BY dev.SUMMARY_DATE ASC");
		SQLQuery query = dao.getSQLQuery(sql.toString());
		List<?> lists = query.list();
		List<IDeviceCatalogSummaryMonth> result = new ArrayList<IDeviceCatalogSummaryMonth>();
		for(Object object : lists){
			Object [] each = (Object[])object;
			IDeviceCatalogSummaryMonth deviceCatalogSummaryMonth = new DeviceCatalogSummaryMonth();
			deviceCatalogSummaryMonth.setDate(each[0]==null? "0":each[0].toString());
			deviceCatalogSummaryMonth.setAddDevNum(each[1]==null?0:Integer.valueOf(each[1].toString()));
			deviceCatalogSummaryMonth.setScrappedDevNum(each[2]==null?0:Integer.valueOf(each[2].toString()));
			result.add(deviceCatalogSummaryMonth);
		}
		return result;
	}

}
