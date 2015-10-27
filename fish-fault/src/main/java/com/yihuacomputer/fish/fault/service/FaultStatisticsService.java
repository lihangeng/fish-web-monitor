package com.yihuacomputer.fish.fault.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.fault.IFaultStatisticsService;
import com.yihuacomputer.fish.fault.entity.CaseFault;

/**
 * 
 * @author xuxiang
 *
 */
@Service	
@Transactional
public class FaultStatisticsService implements IFaultStatisticsService{

	@Autowired
	private IGenericDao dao;
	
	@Override
	public List<Object> statisticsFaultTrend(int days) {
		StringBuffer hql = new StringBuffer();
		hql.append("select caseFault.faultDate,count(*) as faultCount from ").append(CaseFault.class.getName());
		hql.append(" caseFault where faultDate > ?");
		hql.append(" group by caseFault.faultDate");
		hql.append(" order by caseFault.faultDate");
		Long date = Long.parseLong(DateUtils.get(new Date(), DateUtils.STANDARD_DATE_SHORT));
		return dao.findByHQL(hql.toString(), Long.valueOf(date.intValue() - days));
	}

}
