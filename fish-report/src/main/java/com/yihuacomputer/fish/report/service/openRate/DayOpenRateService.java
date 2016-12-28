package com.yihuacomputer.fish.report.service.openRate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.Operator;
import com.yihuacomputer.common.annotation.SaveMethodDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.report.entity.DayOpenRate;

/**
 * @author YiHua
 *
 */
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


  

    @Autowired
    private IGenericDao dao;


    @Autowired
    private IOrganizationService orgService;

    @Autowired
	private IDeviceService deviceService;

    @SaveMethodDescrible(isUpdate=true,keyName={"terminalId","statDate"})
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

        IFilterEntry orgFlag = filter.getFilterEntry("org.orgFlag");
        if (orgFlag != null) {
            hql.append(" and ").append(orgFlag.getKey());
            if (orgFlag.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add(orgFlag.getValue() + "%");
            } else if (orgFlag.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(orgFlag.getValue());
            }
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

        IFilterEntry organization = filter.getFilterEntry("info.organization.orgFlag");
        if (organization != null) {
            hql.append(" and ").append(organization.getKey());
            if (organization.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add(orgService.get(organization.getValue().toString()).getOrgFlag() + "%");
            } else if (organization.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(organization.getValue());
            }
        }

        IFilterEntry awayFlags = filter.getFilterEntry("info.awayFlag");
        if (awayFlags != null) {
            hql.append(" and ").append(awayFlags.getKey());
            if (awayFlags.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add("%" + awayFlags.getValue());
            } else if (awayFlags.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(awayFlags.getValue());
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
            String orgName="";
            String awayFlag = "";
            if(status.length > 7)
            {
      			String srcbOrgName = (String)status[7];
      			String srcbOrgCode = (String)status[8];
      			awayFlag = (String)status[9];
      			orgName = srcbOrgName+"("+srcbOrgCode+")";
            }
            else
            {
            	if(awayFlags != null){
            		awayFlag = awayFlags.getValue().toString();
            	}
            	if(organization !=null){
                String orgId = organization.getValue().toString();
            	String srcbOrgName = orgService.get(orgId).getName();
            	String srcbOrgCode = orgService.get(orgId).getCode();
            	orgName = srcbOrgName+"("+srcbOrgCode+")";
            	}
            }
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
            openRate.setOrgName(orgName);
            openRate.setAwayFlag(awayFlag);
            dayOpenRateList.add(openRate);
        }
        return dayOpenRateList;
    }
    private String DEV_ORG_STAT_HQL = "select  sum(rate.openTimes), sum(rate.healthyTimeReal), "
            + "sum(rate.unknownTimeReal), sum(rate.maintainTimeReal), sum(rate.faultTimeReal), sum(rate.atmpTimeReal), sum(rate.stopTimeReal) "
            + "from Device info, Organization org, DayOpenRate rate "
            + "where info.organization.id=org.id and info.terminalId = rate.terminalId ";
    @Override
    public List<IDayOpenRate> listOrg(IFilter filter) {
        StringBuffer hql = new StringBuffer(DEV_ORG_STAT_HQL);

        List<Object> values = new ArrayList<Object>();

        IFilterEntry orgFlag = filter.getFilterEntry("org.orgFlag");
        if (orgFlag != null) {
            hql.append(" and ").append(orgFlag.getKey());
            hql.append(" like ?");
            values.add(orgFlag.getValue() + "%");
        }

        IFilterEntry entry = filter.getFilterEntry("rate.statDate");
        if (entry != null) {
            hql.append(" and ").append(entry.getKey());
            hql.append(" like ?");
            values.add(entry.getValue() + "%");
        }


        IFilterEntry awayFlags = filter.getFilterEntry("info.awayFlag");
        if (awayFlags != null) {
            hql.append(" and ").append(awayFlags.getKey());
            hql.append(" = ?");
            values.add(awayFlags.getValue());
        }
        
        List<Object> result = dao.findByHQL(hql.toString(), values.toArray());

        List<IDayOpenRate> dayOpenRateList = new ArrayList<IDayOpenRate>();
        IDayOpenRate openRate = null;
        int id = 0;
        String statDateValue = null;
        if(entry!=null){
        	statDateValue =entry.getValue().toString();
        }
        for (Object obj : result) {
            Object[] status = (Object[]) obj;
            int openTimes = valueToInteger(status[0]);
            int healthyTimeReal = valueToInteger(status[1]);
            int unknownTimeReal = valueToInteger(status[2]);
            int maintainTimeReal = valueToInteger(status[3]);
            int faultTimeReal = valueToInteger(status[4]);
            int atmpTimeReal = valueToInteger(status[5]);
            int stopTimeReal = valueToInteger(status[6]);
            String orgName="";
            String awayFlag="";
        	if(awayFlags != null){
        		awayFlag = awayFlags.getValue().toString();
        	}
        	 IFilterEntry  orgIdFilterEntry = filter.getFilterEntry("orgId");
        	if(orgIdFilterEntry !=null){
	            String orgId = orgIdFilterEntry.getValue().toString();
	        	String srcbOrgName = orgService.get(orgId).getName();
	        	String srcbOrgCode = orgService.get(orgId).getCode();
	        	orgName = srcbOrgName+"("+srcbOrgCode+")";
        	}
            openRate = make();
            openRate.setId(id++);
            openRate.setStatDate(statDateValue);
            openRate.setHealthyTimeReal(healthyTimeReal);
            openRate.setOpenTimes(openTimes);
            openRate.setUnknownTimeReal(unknownTimeReal);
            openRate.setMaintainTimeReal(maintainTimeReal);
            openRate.setFaultTimeReal(faultTimeReal);
            openRate.setAtmpTimeReal(atmpTimeReal);
            openRate.setStopTimeReal(stopTimeReal);
            openRate.setOrgName(orgName);
            openRate.setAwayFlag(awayFlag);
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
    	
    	StringBuffer sql=new StringBuffer();
    	sql.append("select sum(HEALTHY_TIMEREAL)/sum(OPENTIMES)*100 from DEV_OPEN_RATE,DEV_INFO,SM_ORG ").append(
    			" where DEV_INFO.TERMINAL_ID=DEV_OPEN_RATE.TERMINAL_ID and DEV_INFO.ORG_ID=SM_ORG.ID");

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
        
        entry = filter.getFilterEntry("info.devType.devVendor.id");
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
        
        entry = filter.getFilterEntry("info.devType.id");
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

        IFilterEntry awayFlags = filter.getFilterEntry("info.awayFlag");
        if (awayFlags != null) {
            hql.append(" and ").append(awayFlags.getKey());
            if (awayFlags.getOperator() == Operator.LIKE) {
                hql.append(" like ?");
                values.add("%" + awayFlags.getValue());
            } else if (awayFlags.getOperator() == Operator.EQ) {
                hql.append(" = ?");
                values.add(awayFlags.getValue());
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
            
//            sql.append(" and ").append(" sm_org.ID like '").append(entry.getValue()).append("%' ");
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
            
//            sql.append(" and").append(" dev_open_rate.STAT_DATE like '").append(date).append("%'");
        }

        if ("1".equals(statType) || "2".equals(statType)) {
            hql.append(" group by rate.terminalId ");
        }

        List<Object> result = dao.findByHQL(hql.toString(), values.toArray());
        
               
        SQLQuery query=dao.getSQLQuery(sql.toString());
        @SuppressWarnings("unchecked")
		List<Object> avg = query.list();
        
        entry = filter.getFilterEntry("(cast(rate.healthyTimeReal as int)*1.00)/(cast(rate.openTimes as int)*1.00)*100");
        //是否进行比较
        boolean compare = false;
        //是否大于
        boolean gl = true;
        double rateValue = 0.0;
        if (entry != null) {
        	compare = true;
            if(entry.getOperator() == Operator.GT){
            	gl = true;
            	rateValue=Double.parseDouble(entry.getValue().toString());
            }else if(entry.getOperator()==Operator.LE){
            	gl=false;
            	rateValue=Double.parseDouble(entry.getValue().toString());
            }
        }
        
        double avgOpenRate=1;
        if(avg.get(0)!=null){
        avgOpenRate=Double.parseDouble(avg.get(0).toString());
        }
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
            if(compare){
            	if(gl){
            		if(healthyTimeReal*1.0/openTimes*100<=rateValue){
            			continue;
            		}
            	}
            	else{

            		if(healthyTimeReal*1.0/openTimes*100>rateValue){
            			continue;
            		}
            	}
            }
            String orgName = "";
            String catalogName ="";
            String typeName="";
            String vendorName="";
            String awayFlag="";
            if(status.length > 11)
            {
            	IDevice device = deviceService.get(terminalId);
                catalogName = (String)status[11];
      			String srcbOrgName = (String)status[12];
      			String srcbOrgCode = (String)status[13];
      			typeName=device.getDevType().getName();
      			vendorName=device.getDevType().getDevVendor().getName();
      			awayFlag=(String)status[14];
      			orgName = srcbOrgName+"("+srcbOrgCode+")";
            }
            else
            {
            	IDevice device = deviceService.get(terminalId);
                catalogName = device.getDevType().getDevCatalog().getName();
                typeName=device.getDevType().getName();
                vendorName=device.getDevType().getDevVendor().getName();
                if(awayFlags != null){
                	awayFlag = awayFlags.getValue().toString();
                }
                //int i=device.getAwayFlag().getId();
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
            dayOpenRate.setDevTypeName(typeName);
            dayOpenRate.setDevVendorName(vendorName);
            dayOpenRate.setAwayFlag(awayFlag);
            dayOpenRate.setAvgOpenRate(avgOpenRate);

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

	@Override
	public List<IDayOpenRate> listByDev(String terminalId) {
		StringBuilder sb = new StringBuilder();
		sb.append("from ").append(DayOpenRate.class.getSimpleName()).append(" as dayopenrate where")
		.append(" dayopenrate.statDate>? and dayopenrate.terminalId=?");
		return dao.findByHQL(sb.toString(), DateUtils.getDate(DateUtils.getLastWeek()),terminalId);
	}
}
