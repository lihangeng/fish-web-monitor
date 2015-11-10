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
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.report.base.IDayOpenRate;
import com.yihuacomputer.fish.api.report.base.IDayOpenRateService;
import com.yihuacomputer.fish.report.base.entity.DayOpenRate;

@Service
@Transactional
public class DayOpenRateService implements IDayOpenRateService {

    private String DEV_DEVICE_HQL = "select rate.terminalId, rate.openTimes, rate.healthyTimeReal, rate.unknownTimeReal, rate.maintainTimeReal, "
    		+"rate.faultTimeReal, rate.atmpTimeReal, rate.stopTimeReal, info.devType.devCatalog.name, info.organization.name,info.organization.code "
            + " from Device info, DayOpenRate rate, Organization org "
            + "where info.terminalId = rate.terminalId and info.organization = org.id ";

    private String DEV_DEVICE_HQL_GROUP = "select rate.terminalId, sum(rate.openTimes), sum(rate.healthyTimeReal), sum(rate.unknownTimeReal), sum(rate.maintainTimeReal),"
    		+" sum(rate.faultTimeReal), sum(rate.atmpTimeReal), sum(rate.stopTimeReal) "
            + " from Device info, DayOpenRate rate, Organization org "
            + "where info.terminalId = rate.terminalId and info.organization = org.id ";

    private String DEV_TYPE_STAT_HQL = "select sum(rate.openTimes), sum(rate.healthyTimeReal), "
            + "sum(rate.unknownTimeReal), sum(rate.maintainTimeReal), sum(rate.faultTimeReal), sum(rate.atmpTimeReal), sum(rate.stopTimeReal) "
            + "from Device info, AtmType type, DayOpenRate rate, Organization org "
            + "where info.devType=type.id and info.terminalId = rate.terminalId and info.organization = org.id ";


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

    @Autowired
	private IDeviceService deviceService;

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
                values.add(entry.getValue() + "%");
            } else if (entry.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(entry.getValue());
            }
        }

        entry = filter.getFilterEntry("rate.statDate");
        if (entry != null) {
            hql.append(" and ").append(entry.getKey());
            if (entry.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add(entry.getValue() + "%");
            } else if (entry.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(entry.getValue());
            }
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
            int openTimes = valueToInteger(status[0]);
            int healthyTimeReal = valueToInteger(status[1]);
            int unknownTimeReal = valueToInteger(status[2]);
            int maintainTimeReal = valueToInteger(status[3]);
            int faultTimeReal = valueToInteger(status[4]);
            int atmpTimeReal = valueToInteger(status[5]);
            int stopTimeReal = valueToInteger(status[6]);

            openRate = make();
            openRate.setId(id++);
            openRate.setStatDate(entry.getValue().toString());
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
            values.add(orgService.get(String.valueOf(typeValue)).getOrgFlag() + "%");
        }
        IFilterEntry entry = filter.getFilterEntry("rate.statDate");
        if (entry != null) {
            hql.append(" and ").append(entry.getKey());
            if (entry.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add(entry.getValue() + "%");
            } else if (entry.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(entry.getValue());
            }
        }

        List<Object> result = dao.findByHQL(hql.toString(), values.toArray());

        List<IDayOpenRate> dayOpenRateList = new ArrayList<IDayOpenRate>();
        IDayOpenRate openRate = null;
        int id = 0;
        for (Object obj : result) {
            Object[] status = (Object[]) obj;
            int openTimes = valueToInteger(status[0]);
            int healthyTimeReal = valueToInteger(status[1]);
            int unknownTimeReal = valueToInteger(status[2]);
            int maintainTimeReal = valueToInteger(status[3]);
            int faultTimeReal = valueToInteger(status[4]);
            int atmpTimeReal = valueToInteger(status[5]);
            int stopTimeReal = valueToInteger(status[6]);
            openRate = make();
            openRate.setId(id++);
            openRate.setStatDate(entry.getValue().toString());
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
        if ("1".equals(statType) || "2".equals(statType)) {
            hql.append(DEV_DEVICE_HQL_GROUP);
        } else {
            hql.append(DEV_DEVICE_HQL);
        }

        List<Object> values = new ArrayList<Object>();
        IFilterEntry entry = filter.getFilterEntry("org.orgFlag");
        if (entry != null) {
            hql.append(" and ").append(entry.getKey());
            if (entry.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add(entry.getValue() + "%");
            } else if (entry.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(entry.getValue());
            }
        }

        entry = filter.getFilterEntry("info.devType.devCatalog.id");
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

        entry = filter.getFilterEntry("info.organization.orgFlag");
        if (entry != null) {
            hql.append(" and ").append(entry.getKey());
            if (entry.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add(orgService.get(entry.getValue().toString()).getOrgFlag() + "%");
            } else if (entry.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(entry.getValue());
            }
        }

        entry = filter.getFilterEntry("rate.terminalId");
        if (entry != null) {
            hql.append(" and ").append(entry.getKey());
            if (entry.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add(entry.getValue());
            }
        }

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

        if ("1".equals(statType) || "2".equals(statType)) {
            hql.append(" group by rate.terminalId ");
        }

        List<Object> result = dao.findByHQL(hql.toString(), values.toArray());

        List<IDayOpenRate> dayOpenRateList = new ArrayList<IDayOpenRate>();

        DayOpenRate dayOpenRate = null;
        int id = 1;
        for (Object obj : result) {
            Object[] status = (Object[]) obj;
            String terminalId = status[0] == null ? "" : String.valueOf(status[0]);
            int openTimes = valueToInteger(status[1]);
            int healthyTimeReal = valueToInteger(status[2]);
            int unknownTimeReal = valueToInteger(status[3]);
            int maintainTimeReal = valueToInteger(status[4]);
            int faultTimeReal = valueToInteger(status[5]);
            int atmpTimeReal = valueToInteger(status[6]);
            int stopTimeReal = valueToInteger(status[7]);
            String orgName = "";
            String catalogName ="";
            if(status.length > 8)
            {
                catalogName = (String)status[8];
      			String srcbOrgName = (String)status[9];
      			String srcbOrgCode = (String)status[10];
      			orgName = srcbOrgName+"("+srcbOrgCode+")";
            }
            else
            {
            	IDevice device = deviceService.get(terminalId);
                catalogName = device.getDevType().getDevCatalog().getName();
            	String srcbOrgName = device.getOrganization().getName();
            	String srcbOrgCode = device.getOrganization().getCode();
            	orgName = srcbOrgName+"("+srcbOrgCode+")";
            }
            dayOpenRate = new DayOpenRate();
            dayOpenRate.setId(id++);
            dayOpenRate.setTerminalId(terminalId);
            dayOpenRate.setStatDate(date == null ? "" : String.valueOf(date));
            dayOpenRate.setOpenTimes(openTimes);
            dayOpenRate.setHealthyTimeReal(healthyTimeReal);
            dayOpenRate.setUnknownTimeReal(unknownTimeReal);
            dayOpenRate.setMaintainTimeReal(maintainTimeReal);
            dayOpenRate.setFaultTimeReal(faultTimeReal);
            dayOpenRate.setAtmpTimeReal(atmpTimeReal);
            dayOpenRate.setStopTimeReal(stopTimeReal);
            dayOpenRate.setOrgName(orgName);
            dayOpenRate.setDevCatalogName(catalogName);

            dayOpenRateList.add(dayOpenRate);
        }
        return dayOpenRateList;
    }


    @Override
    public IPageResult<IDayOpenRate> pageDev(int offset, int limit, IFilter filter) {
        IPageResult<IDayOpenRate> pageResult = new PageResult<IDayOpenRate>(listDev(filter), offset, limit);
        return pageResult;
    }


    private int valueToInteger(Object obj) {
        long result = obj == null ? 0L : Long.valueOf(String.valueOf(obj));
        return (int) result;
    }

	@Override
	public IDayOpenRate make() {
		return new DayOpenRate();
	}
}
