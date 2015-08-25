package com.yihuacomputer.fish.report.base;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.report.base.IDayOpenRate;
import com.yihuacomputer.fish.api.report.base.IDayOpenRateService;
import com.yihuacomputer.fish.report.base.entity.DayOpenRate;

@Service
@Transactional
public class DayOpenRateService implements IDayOpenRateService {

	private String DEV_DEVICE_HQL = "select rate.terminalId, rate.openTimes, rate.healthyTimeReal, rate.unknownTimeReal, rate.maintainTimeReal, rate.faultTimeReal, rate.atmpTimeReal, rate.stopTimeReal, rate.noScreenReal "
			+ " from Device info, DayOpenRate rate, Organization org "
			+ "where info.terminalId = rate.terminalId and info.organization.id = org.id ";

	private String DEV_DEVICE_HQL_GROUP = "select rate.terminalId, sum(rate.openTimes), sum(rate.healthyTimeReal), sum(rate.unknownTimeReal), sum(rate.maintainTimeReal), sum(rate.faultTimeReal), sum(rate.atmpTimeReal), sum(rate.stopTimeReal),sum(rate.noScreenReal) "
			+ " from Device info, DayOpenRate rate, Organization org "
			+ "where info.terminalId = rate.terminalId and info.organization.id = org.id ";

	private String DEV_TYPE_STAT_HQL = "select sum(rate.openTimes), sum(rate.healthyTimeReal), "
			+ "sum(rate.unknownTimeReal), sum(rate.maintainTimeReal), sum(rate.faultTimeReal), sum(rate.atmpTimeReal), sum(rate.stopTimeReal) "
			+ "from Device info, AtmType type, DayOpenRate rate, Organization org "
			+ "where info.devType=type.id and info.terminalId = rate.terminalId and info.organization.id = org.id ";

	// private String DEV_DEVICE_STAT_HQL = "select info, rate "
	// +
	// "from dev_info info left join dev_open_rate rate on info.terminal_id=rate.terminal_id "
	// + " where rate.statDate like ? order by rate.opentimereal desc";

	private String DEV_ORG_STAT_HQL = "select  sum(rate.openTimes), sum(rate.healthyTimeReal), "
			+ "sum(rate.unknownTimeReal), sum(rate.maintainTimeReal), sum(rate.faultTimeReal), sum(rate.atmpTimeReal), sum(rate.stopTimeReal) "
			+ "from Device info, Organization org, DayOpenRate rate "
			+ "where info.organization=org.id and info.terminalId = rate.terminalId ";

	@Autowired
	private IGenericDao dao;

	@Autowired
	private IOrganizationService orgService;

	@Override
	public IDayOpenRate make() {
		return new DayOpenRate();
	}

	@Override
	public void save(IDayOpenRate dayOpenRate) {
		dao.save(dayOpenRate);
	}

	@Override
	public List<IDayOpenRate> list() {
		return list(new Filter());
	}

	@Override
	public List<IDayOpenRate> list(IFilter filter) {
		return dao.findByFilter(filter, IDayOpenRate.class);
	}

	@Override
	public IPageResult<IDayOpenRate> page(int offset, int limit, IFilter filter) {
		return dao.page(offset, limit, filter, DayOpenRate.class);
	}

	@Override
	public IPageResult<IDayOpenRate> pageType(int offset, int limit, IFilter filter) {
		IPageResult<IDayOpenRate> pageResult = new PageResult<IDayOpenRate>(listType(filter), offset, limit);
		return pageResult;
	}

	@Override
	public List<IDayOpenRate> listType(IFilter filter) {

		StringBuffer hql = new StringBuffer(DEV_TYPE_STAT_HQL);
		List<Object> values = new ArrayList<Object>();

		Object typeValue = filter.getValue("typeId");
		if (typeValue != null) {
			hql.append(" and type.id = ?");
			values.add(Long.valueOf(typeValue.toString()));
		}

		IFilterEntry entry = filter.getFilterEntry("org.orgFlag");
		if (entry != null) {
			hql.append(" and ").append(entry.getKey());
			if (entry.getOperator() == Operator.LIKE) {
				hql.append(" like ?");
				values.add("%" + entry.getValue());
			} else if (entry.getOperator() == Operator.EQ) {
				hql.append(" = ?");
				values.add(entry.getValue());
			}
		}

		String statType = (String) filter.getFilterEntry("startType").getValue();
		String statDate = null;

		// 按季度统计
		if ("4".equals(statType)) {

			hql.append(" and ").append("rate.statDate <= ? ");
			hql.append(" and ").append("rate.statDate >= ? ");

			values.add(filter.getFilterEntry("endDate").getValue());
			values.add(filter.getFilterEntry("startDate").getValue());

			statDate = filter.getFilterEntry("quarterText").getValue().toString();

		} else {

			// 年/月/日统计
			entry = filter.getFilterEntry("rate.statDate");
			Object date = null;
			if (entry != null) {
				hql.append(" and ").append(entry.getKey());
				date = entry.getValue();
				if (entry.getOperator() == Operator.LIKE) {
					hql.append(" like ?");
					values.add(date + "%");
				} else if (entry.getOperator() == Operator.EQ) {
					hql.append(" = ?");
					values.add(date);
				}
			}
			statDate = date == null ? "" : String.valueOf(date);
		}


		List<Object> result = dao.findByHQL(hql.toString(), values.toArray());
		List<IDayOpenRate> dayOpenRateList = new ArrayList<IDayOpenRate>();
		IDayOpenRate openRate = null;
		int id = 0;
		for (Object obj : result) {
			Object[] status = (Object[]) obj;

			// "select sum(rate.openTimes), sum(rate.healthyTimeReal), "
			// +
			// "sum(rate.unknownTimeReal), sum(rate.maintainTimeReal), sum(rate.faultTimeReal), sum(rate.atmpTimeReal), sum(rate.stopTimeReal) "
			long openTimes = valueToLong(status[0]);
			long healthyTimeReal = valueToLong(status[1]);
			long unknownTimeReal = valueToLong(status[2]);
			long maintainTimeReal = valueToLong(status[3]);
			long faultTimeReal = valueToLong(status[4]);
			long atmpTimeReal = valueToLong(status[5]);
			long stopTimeReal = valueToLong(status[6]);

			openRate = make();
			openRate.setId(id++);
			openRate.setStatDate(statDate);
			openRate.setHealthyTimeReal(healthyTimeReal);
			openRate.setOpenTimes(openTimes);
			openRate.setUnknownTimeReal(unknownTimeReal);
			openRate.setMaintainTimeReal(maintainTimeReal);
			openRate.setFaultTimeReal(faultTimeReal);
			openRate.setAtmpTimeReal(atmpTimeReal);
			openRate.setStopTimeReal(stopTimeReal);
			dayOpenRateList.add(openRate);
		}
		return dayOpenRateList;
	}

	@Override
	public List<IDayOpenRate> listOrg(IFilter filter) {
		StringBuffer hql = new StringBuffer(DEV_ORG_STAT_HQL);

		List<Object> values = new ArrayList<Object>();
		Object typeValue = filter.getValue("orgId");
		if (typeValue != null) {
			hql.append(" and org.orgFlag like ?");
			values.add("%" + orgService.get(String.valueOf(typeValue)).getOrgFlag());
		}


		IFilterEntry entry = null;
		String statType = (String) filter.getFilterEntry("startType").getValue();
		String statDate = null;

		// 按季度统计
		if ("4".equals(statType)) {

			hql.append(" and ").append("rate.statDate <= ? ");
			hql.append(" and ").append("rate.statDate >= ? ");

			values.add(filter.getFilterEntry("endDate").getValue());
			values.add(filter.getFilterEntry("startDate").getValue());

			statDate = filter.getFilterEntry("quarterText").getValue().toString();

		} else {

			// 年/月/日统计
			entry = filter.getFilterEntry("rate.statDate");
			Object date = null;
			if (entry != null) {
				hql.append(" and ").append(entry.getKey());
				date = entry.getValue();
				if (entry.getOperator() == Operator.LIKE) {
					hql.append(" like ?");
					values.add(date + "%");
				} else if (entry.getOperator() == Operator.EQ) {
					hql.append(" = ?");
					values.add(date);
				}
			}
			statDate = date == null ? "" : String.valueOf(date);
		}

		List<Object> result = dao.findByHQL(hql.toString(), values.toArray());

		List<IDayOpenRate> dayOpenRateList = new ArrayList<IDayOpenRate>();
		IDayOpenRate openRate = null;
		int id = 0;
		for (Object obj : result) {
			Object[] status = (Object[]) obj;
			long openTimes = valueToLong(status[0]);
			long healthyTimeReal = valueToLong(status[1]);
			long unknownTimeReal = valueToLong(status[2]);
			long maintainTimeReal = valueToLong(status[3]);
			long faultTimeReal = valueToLong(status[4]);
			long atmpTimeReal = valueToLong(status[5]);
			long stopTimeReal = valueToLong(status[6]);

			openRate = make();
			openRate.setId(id++);
			openRate.setStatDate(statDate);
			openRate.setHealthyTimeReal(healthyTimeReal);
			openRate.setOpenTimes(openTimes);
			openRate.setUnknownTimeReal(unknownTimeReal);
			openRate.setMaintainTimeReal(maintainTimeReal);
			openRate.setFaultTimeReal(faultTimeReal);
			openRate.setAtmpTimeReal(atmpTimeReal);
			openRate.setStopTimeReal(stopTimeReal);

			dayOpenRateList.add(openRate);
		}
		return dayOpenRateList;
	}

	@Override
	public IPageResult<IDayOpenRate> pageOrg(int offset, int limit, IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IDayOpenRate> listDev(IFilter filter) {
		String statType = (String) filter.getFilterEntry("startType").getValue();

		StringBuffer hql = new StringBuffer();
		if ("1".equals(statType) || "2".equals(statType) || "4".equals(statType)) {
			hql.append(DEV_DEVICE_HQL_GROUP);
		} else {
			hql.append(DEV_DEVICE_HQL);
		}

		// 机构条件
		List<Object> values = new ArrayList<Object>();
		IFilterEntry entry = filter.getFilterEntry("org.orgFlag");
		if (entry != null) {
			hql.append(" and ").append(entry.getKey());
			if (entry.getOperator() == Operator.LIKE) {
				hql.append(" like ?");
				values.add("%" + entry.getValue());
			} else if (entry.getOperator() == Operator.EQ) {
				hql.append(" = ?");
				values.add(entry.getValue());
			}
		}


		String statDate = null;

		// 按季度统计
		if ("4".equals(statType)) {

			hql.append(" and ").append("rate.statDate <= ? ");
			hql.append(" and ").append("rate.statDate >= ? ");

			values.add(filter.getFilterEntry("endDate").getValue());
			values.add(filter.getFilterEntry("startDate").getValue());

			statDate = filter.getFilterEntry("quarterText").getValue().toString();

		} else {

			// 年/月/日统计
			entry = filter.getFilterEntry("rate.statDate");
			Object date = null;
			if (entry != null) {
				hql.append(" and ").append(entry.getKey());
				date = entry.getValue();
				if (entry.getOperator() == Operator.LIKE) {
					hql.append(" like ?");
					values.add(date + "%");
				} else if (entry.getOperator() == Operator.EQ) {
					hql.append(" = ?");
					values.add(date);
				}
			}

			statDate = date == null ? "" : String.valueOf(date);
		}

		// 设备号
		entry = filter.getFilterEntry("rate.terminalId");
		if (entry != null) {
			hql.append(" and ").append(" rate.terminalId like ? ");
			values.add("%" + entry.getValue() + "%");
		}


		if ("1".equals(statType) || "2".equals(statType) || "4".equals(statType)) {
			hql.append(" group by rate.terminalId ");
		}

		List<Object> result = dao.findByHQL(hql.toString(), values.toArray());

		List<IDayOpenRate> dayOpenRateList = new ArrayList<IDayOpenRate>();
		DayOpenRate dayOpenRate = null;
		int id = 1;
		for (Object obj : result) {
			Object[] status = (Object[]) obj;
			String terminalId = status[0] == null ? "" : String.valueOf(status[0]);
			long openTimes = valueToLong(status[1]);
			long healthyTimeReal = valueToLong(status[2]);
			long unknownTimeReal = valueToLong(status[3]);
			long maintainTimeReal = valueToLong(status[4]);
			long faultTimeReal = valueToLong(status[5]);
			long atmpTimeReal = valueToLong(status[6]);
			long stopTimeReal = valueToLong(status[7]);
			long noScreenReal  = valueToLong(status[8]);

			dayOpenRate = new DayOpenRate();
			dayOpenRate.setId(id++);
			dayOpenRate.setTerminalId(terminalId);
			dayOpenRate.setStatDate(statDate);
			dayOpenRate.setOpenTimes(openTimes);
			dayOpenRate.setHealthyTimeReal(healthyTimeReal);
			dayOpenRate.setUnknownTimeReal(unknownTimeReal);
			dayOpenRate.setMaintainTimeReal(maintainTimeReal);
			dayOpenRate.setFaultTimeReal(faultTimeReal);
			dayOpenRate.setAtmpTimeReal(atmpTimeReal);
			dayOpenRate.setStopTimeReal(stopTimeReal);
			dayOpenRate.setNoScreenReal(noScreenReal);

			dayOpenRateList.add(dayOpenRate);
		}
		return dayOpenRateList;
	}

	@Override
	public IPageResult<IDayOpenRate> pageDev(int offset, int limit, IFilter filter) {
		IPageResult<IDayOpenRate> pageResult = new PageResult<IDayOpenRate>(listDev(filter), offset, limit);
		return pageResult;
	}

	private long valueToLong(Object obj) {
		long result = obj == null ? 0L : Long.valueOf(String.valueOf(obj));
		return result;
	}
}
