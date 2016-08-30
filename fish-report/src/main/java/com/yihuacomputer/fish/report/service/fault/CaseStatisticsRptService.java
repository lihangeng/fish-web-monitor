package com.yihuacomputer.fish.report.service.fault;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.report.fault.ICaseStatisticsRptService;

@Service
@Transactional(readOnly = true)
public class CaseStatisticsRptService implements ICaseStatisticsRptService {

	@Autowired
	private IGenericDao dao;

	@Override
	public List<Object> caseStatisticsRank(IFilter filter) {
		IFilterEntry startDate = filter.getFilterEntry("startDate");
		IFilterEntry endDate = filter.getFilterEntry("endDate");
		IFilterEntry angle = filter.getFilterEntry("angle");
		IFilterEntry orgFlag = filter.getFilterEntry("orgFlag");
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		hql.append("select ");
		if (angle != null) {
			int angleType = Integer.parseInt(angle.getValue().toString());
			switch (angleType) {
			// 1.设备 2.品牌3.型号4.模块
			case 1: {
				hql.append(" device.terminalId,count(c.id) ");
				break;
			}
			case 2: {
				hql.append(" device.devType.devVendor.name,count(c.id) ");
				break;
			}
			case 3: {
				hql.append(" device.devType.name,count(c.id) ");
				break;
			}
			case 4: {
				hql.append(" c.devMod,count(c.id) ");
				break;
			}
			}
		}
		hql.append(" from CaseFault c,Device device,Organization org where device.terminalId=c.terminalId and device.organization.id=org.id ");
		if (orgFlag != null) {
			hql.append(" and org.orgFlag like ? ");
			paramList.add(orgFlag.getValue().toString() + "%");
		}
		if (startDate != null) {
			hql.append(" and c.faultDate>=?");
			paramList.add(Long.parseLong(startDate.getValue().toString()));
		}
		if (endDate != null) {
			hql.append(" and c.faultDate<=?");
			paramList.add(Long.parseLong(endDate.getValue().toString()));
		}
		if (angle != null) {
			int angleType = Integer.parseInt(angle.getValue().toString());
			switch (angleType) {
			// 1.设备 2.品牌3.型号4.模块
			case 1: {
				hql.append(" group by device.terminalId ");
				break;
			}
			case 2: {
				hql.append(" group by device.devType.devVendor.name ");
				break;
			}
			case 3: {
				hql.append(" group by device.devType.name ");
				break;
			}
			case 4: {
				hql.append(" group by c.devMod ");
				break;
			}
			}
		}
		hql.append(" order by count(c.id) desc ");
		return dao.findByHQL(hql.toString(), paramList.toArray());
	}

}
