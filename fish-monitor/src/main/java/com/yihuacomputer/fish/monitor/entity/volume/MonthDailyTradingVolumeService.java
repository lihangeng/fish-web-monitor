package com.yihuacomputer.fish.monitor.entity.volume;

import java.util.ArrayList;
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
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolume;
import com.yihuacomputer.fish.api.monitor.volume.IMonthDailyTradingVolumeService;
@Service
@Transactional
public class MonthDailyTradingVolumeService implements IMonthDailyTradingVolumeService {

	@Autowired
	private IGenericDao dao;
	
	@Override
	public void generalMonthDailyTradingVolume() {
		String date = DateUtils.getTodayDate();
		generalMonthDailyTradingVolumeByDate(date);
	}

	@Override
	public void generalMonthDailyTradingVolumeByDate(String dateStr) {
		//将传过来的字符串转化为日期
		Date date = DateUtils.getDateShort(dateStr);
		//获取上个月的第一天
		Date lastMonthStartDate = DateUtils.getPreviousMonthFirst(date);
		//获取当月月的第一天
		Date monthStartDate = DateUtils.getMonthFirst(date);
		//获取上个月的天数
		int lastMonthDays = DateUtils.getLastMonthDays(monthStartDate);
		int  lastMonth = Integer.parseInt(DateUtils.getYM(lastMonthStartDate));
		int lastYearMonth =Integer.parseInt(DateUtils.getLastYearMonth(date));
		IFilter filter  = new Filter();
		filter.eq("transMonth", lastYearMonth);
		//获取同比数据集合
		Map<String,IMonthDailyTradingVolume> monthDailyMap  = getMonthDailyMap(filter);
		StringBuffer sb = new StringBuffer();
		sb.append("select  dayTV.terminalId,sum(dayTV.amtIn),sum(dayTV.amtOut) from ").append(DayTradingVolume.class.getSimpleName())
		.append(" dayTV where dayTV.transDate>=? and dayTV.transDate<? group by  dayTV.terminalId");
		//获取环比数据集合
		List<Object> monthDailyList = dao.findByHQL(sb.toString(), new Object[]{Integer.parseInt(DateUtils.get(lastMonthStartDate, DateUtils.STANDARD_DATE_SHORT)),Integer.parseInt(DateUtils.get(monthStartDate, DateUtils.STANDARD_DATE_SHORT))});
		List<IMonthDailyTradingVolume> list = new ArrayList<IMonthDailyTradingVolume>();
		for(Object obj:monthDailyList){
			Object[] objs = (Object[])obj;
			IMonthDailyTradingVolume monthDailyTradingVolume = this.make();
			String terminalId = String.valueOf(objs[0]);
			monthDailyTradingVolume.setTerminalId(terminalId);
			monthDailyTradingVolume.setTransMonth(lastMonth);
			monthDailyTradingVolume.setMonthAmtInAvg(Double.parseDouble(String.valueOf(objs[1]))/lastMonthDays);
			monthDailyTradingVolume.setMonthAmtOutAvg(Double.parseDouble(String.valueOf(objs[2]))/lastMonthDays);
			IMonthDailyTradingVolume lastYearmonthDailyTradingVolume = monthDailyMap.get(terminalId);
			if(null!=lastYearmonthDailyTradingVolume){
				monthDailyTradingVolume.setLastYearAmtInAvg(lastYearmonthDailyTradingVolume.getMonthAmtInAvg());
				monthDailyTradingVolume.setLastYearAmtOutAvg(lastYearmonthDailyTradingVolume.getMonthAmtOutAvg());
			}
			list.add(monthDailyTradingVolume);
		}
		dao.batchSave(list);
	}

	@Override
	public IMonthDailyTradingVolume make() {
		return new MonthDailyTradingVolume();
	}

	@Override
	public IMonthDailyTradingVolume save(IMonthDailyTradingVolume monthDailyTradingVolume) {
		return dao.save(monthDailyTradingVolume);
	}

	@Override
	public IMonthDailyTradingVolume update(IMonthDailyTradingVolume monthDailyTradingVolume) {
		return dao.update(monthDailyTradingVolume);
	}

	@Override
	public void remove(IMonthDailyTradingVolume monthDailyTradingVolume) {
		dao.delete(monthDailyTradingVolume);
	}

	public List<IMonthDailyTradingVolume> list(IFilter filter){
		return dao.findByFilter(filter, IMonthDailyTradingVolume.class);
	}
	
	public Map<String,IMonthDailyTradingVolume> getMonthDailyMap(IFilter filter){
		Map<String,IMonthDailyTradingVolume> monthDailyMap = new HashMap<String,IMonthDailyTradingVolume>();
		List<IMonthDailyTradingVolume> monthDaliyList = this.list(filter);
		for(IMonthDailyTradingVolume monthDaliy:monthDaliyList){
			monthDailyMap.put(monthDaliy.getTerminalId(), monthDaliy);
		}
		return monthDailyMap;
	}
}
