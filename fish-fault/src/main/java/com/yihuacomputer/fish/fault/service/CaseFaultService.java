package com.yihuacomputer.fish.fault.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.fault.FaultCloseType;
import com.yihuacomputer.fish.api.fault.FaultStatus;
import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseFaultService;
import com.yihuacomputer.fish.api.fault.IFaultClassify;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.fault.entity.CaseFault;

@Service
@Transactional
public class CaseFaultService implements ICaseFaultService {

	private final String OPEN_CASE_HQL = "from CaseFault c where c.terminalId = ? and c.faultStatus='OPEN' ";


	@Autowired
	private IGenericDao dao;

	@Autowired
	private IOrganizationService orgService;

	@Override
	public ICaseFault make() {
		return new CaseFault();
	}

	@Override
	public ICaseFault getFault(long id) {
		return dao.get(id, CaseFault.class);
	}


	@Override
	public void save(ICaseFault caseFault) {
		dao.save(caseFault);
	}

	@Override
	public void update(ICaseFault caseFault) {
		dao.update(caseFault);
	}

	@Override
	public List<ICaseFault> listOpenCaseFault(String terminalId) {
		List<ICaseFault> result = dao.findByHQL(OPEN_CASE_HQL,terminalId);
		return result;
	}

	@Override
	public void closeCaseFault(ICaseFault caseFault) {
		caseFault.setClosedTime(new Date());
		caseFault.setFaultStatus(FaultStatus.CLOSED);
		caseFault.setDuration(calculateDuration(caseFault.getFaultTime()));
		if(caseFault.getCloseType()!=null){
			caseFault.setCloseType(FaultCloseType.FORCE);
		}else{
			caseFault.setCloseType(FaultCloseType.NORMAL);
		}
		this.update(caseFault);
	}

	/**
	 * 计算故障持续时间
	 *
	 * @param faultTime
	 * @return
	 */
	private double calculateDuration(Date faultTime){
		if(faultTime==null){
			return 0.0;
		}
		Date now = new Date();
		double fault = 1000*60*60;
		java.text.DecimalFormat   df=new   java.text.DecimalFormat("#.##");
		double date=  (now.getTime()-faultTime.getTime())/fault;
		return Double.parseDouble(df.format(date));
	}

	@Override
	public void createCaseFault(ICaseFault caseFault) {
		caseFault.setFaultTime(new Date());
		caseFault.setFaultStatus(FaultStatus.OPEN);
		this.save(caseFault);
	}

	@Override
	public List<ICaseFault> list(IFilter filter) {
		return dao.findByFilter(filter, ICaseFault.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<ICaseFault> page(int offset, int limit, IFilter filter,long orgId) {
		StringBuffer hql = new StringBuffer();
//		IOrganization org = orgService.get(String.valueOf(orgId));
		List<Object> valueObj = new ArrayList<Object>();
    	IFilterEntry terminalId = filter.getFilterEntry("terminalId");
    	IFilterEntry faultClassify = filter.getFilterEntry("faultClassify");
    	IFilterEntry faultCode = filter.getFilterEntry("faultCode");
    	IFilterEntry devMod = filter.getFilterEntry("devMod");
    	IFilterEntry vendorHwCode = filter.getFilterEntry("vendorHwCode");
    	IFilterEntry faultTime = filter.getFilterEntry("faultTime");
    	IFilterEntry orgName = filter.getFilterEntry("orgName");
		IFilterEntry devType = filter.getFilterEntry("devType");
    	IFilterEntry closedTime = filter.getFilterEntry("closedTime");
    	IFilterEntry duration = filter.getFilterEntry("duration");
    	IFilterEntry faultStatus = filter.getFilterEntry("faultStatus");
    	IFilterEntry upgrade = filter.getFilterEntry("upgrade");
    	hql.append("select caseFault from CaseFault caseFault ,Device device ");
    	hql.append("where caseFault.terminalId = device.terminalId and device.organization.orgFlag like ? ");
//		valueObj.add("%" + org.getOrgFlag());
    	valueObj.add("%-1" );
		if(terminalId!=null){
			hql.append(" and caseFault.terminalId like ?");
			valueObj.add("%"+terminalId.getValue()+"%");
		}
		if(faultTime!=null){
			hql.append(" and caseFault.faultTime>=?");
			valueObj.add(DateUtils.getTimestamp(faultTime.getValue().toString()+" 00:00:00"));
			hql.append(" and caseFault.faultTime<=?");
			valueObj.add(DateUtils.getTimestamp(faultTime.getValue().toString()+" 23:59:59"));
		}
		if(closedTime!=null){
			hql.append(" and caseFault.closedTime>=?");
			valueObj.add(DateUtils.getTimestamp(closedTime.getValue().toString()+" 00:00:00"));
			hql.append(" and caseFault.closedTime<=?");
			valueObj.add(DateUtils.getTimestamp(closedTime.getValue().toString()+" 23:59:59"));
		}
		if(faultClassify!=null){
			hql.append(" and caseFault.faultClassify.id = ?");
			valueObj.add(faultClassify.getValue());
		}
		if(faultCode!=null){
			hql.append(" and caseFault.faultCode = ?");
			valueObj.add(faultCode.getValue());
		}
		if(devMod!=null){
			hql.append(" and caseFault.devMod = ?");
			valueObj.add(DeviceMod.valueOf(devMod.getValue().toString()));
		}
		if(vendorHwCode!=null){
			hql.append(" and caseFault.vendorHwCode = ?");
			valueObj.add(vendorHwCode.getValue());
		}
		if(duration!=null){
			hql.append(" and caseFault.duration = ?");
			valueObj.add(Double.parseDouble(duration.getValue().toString()));
		}
		if(faultStatus!=null){
			hql.append(" and caseFault.faultStatus = ?");
			valueObj.add(FaultStatus.valueOf(faultStatus.getValue().toString()));
		}
		if(upgrade!=null){
			hql.append(" and caseFault.upgrade = ?");
			valueObj.add(Integer.parseInt(upgrade.getValue().toString()));
		}
		if (orgName != null) {
			hql.append(" and device.organization.name = ?");
			valueObj.add(orgName.getValue());
		}
		if (devType != null) {
			hql.append(" and device.devType.id = ?");
			valueObj.add(Long.parseLong(devType.getValue().toString()));
		}
		hql.append(" order by caseFault.faultTime desc");
		IPageResult<ICaseFault> result = (IPageResult<ICaseFault>)this.dao.page(offset,limit,hql.toString(), valueObj.toArray());
    	return result;
	}

	/**
	 * 判断模块当前是否已经有对应的故障创建
	 *
	 * @param openCaseList
	 * @param devMod
	 * @param faultClassify
	 * @return
	 */
	public boolean hasModCaseFault(List<ICaseFault> openCaseList,DeviceMod devMod,IFaultClassify faultClassify){
		if(openCaseList==null||openCaseList.isEmpty()){
			return false;
		}
		for(ICaseFault caseFault:openCaseList){
			if(caseFault.getDevMod() == null){
				continue;
			}
			if(caseFault.getDevMod().equals(devMod)&&caseFault.getFaultClassify().getId().equals(faultClassify.getId())){
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断模块当前是否已经有对应的故障创建
	 *
	 * @param openCaseList
	 * @param faultClassify
	 * @return
	 */
	public ICaseFault getModCaseFault(List<ICaseFault> openCaseList,IFaultClassify faultClassify){
		if(openCaseList==null||openCaseList.isEmpty()){
			return null;
		}
		for(ICaseFault caseFault:openCaseList){
			if(caseFault.getFaultClassify().getId().equals(faultClassify.getId())){
				return caseFault;
			}
		}
		return null;
	}

	/**
	 * 关闭正常模块故障
	 * @param openCaseList
	 * @param devMod
	 */
	public void closeHealthyModCase(List<ICaseFault> openCaseList,DeviceMod devMod){
		if(openCaseList==null||openCaseList.isEmpty()){
			return;
		}
		for(ICaseFault caseFault:openCaseList){
			if(caseFault.getDevMod().equals(devMod)){
				this.closeCaseFault(caseFault);
			}
		}
	}

    @Override
    public List<ICaseFault> list(long orgId, IFilter filter) {
        StringBuffer hql = new StringBuffer();
        IOrganization org = orgService.get(String.valueOf(orgId));
        List<Object> valueObj = new ArrayList<Object>();
        IFilterEntry terminalId = filter.getFilterEntry("terminalId");
        IFilterEntry faultClassify = filter.getFilterEntry("faultClassify");
        IFilterEntry faultCode = filter.getFilterEntry("faultCode");
        IFilterEntry devMod = filter.getFilterEntry("devMod");
        IFilterEntry vendorHwCode = filter.getFilterEntry("vendorHwCode");
        IFilterEntry faultTime = filter.getFilterEntry("faultTime");
    	IFilterEntry orgName = filter.getFilterEntry("orgName");
		IFilterEntry devType = filter.getFilterEntry("devType");
        IFilterEntry closedTime = filter.getFilterEntry("closedTime");
        IFilterEntry duration = filter.getFilterEntry("duration");
        IFilterEntry faultStatus = filter.getFilterEntry("faultStatus");
        IFilterEntry upgrade = filter.getFilterEntry("upgrade");
        hql.append("select caseFault from CaseFault caseFault ,Device device ");
        hql.append("where caseFault.terminalId = device.terminalId and device.organization.orgFlag like ? ");
        valueObj.add("%" + org.getOrgFlag());
        if(terminalId!=null){
            hql.append(" and caseFault.terminalId like ?");
            valueObj.add("%"+terminalId.getValue()+"%");
        }
        if(faultTime!=null){
            hql.append(" and caseFault.faultTime>=?");
            valueObj.add(DateUtils.getTimestamp(faultTime.getValue().toString()+" 00:00:00"));
            hql.append(" and caseFault.faultTime<=?");
            valueObj.add(DateUtils.getTimestamp(faultTime.getValue().toString()+" 23:59:59"));
        }
        if(closedTime!=null){
            hql.append(" and caseFault.closedTime>=?");
            valueObj.add(DateUtils.getTimestamp(closedTime.getValue().toString()+" 00:00:00"));
            hql.append(" and caseFault.closedTime<=?");
            valueObj.add(DateUtils.getTimestamp(closedTime.getValue().toString()+" 23:59:59"));
        }
        if(faultClassify!=null){
            hql.append(" and caseFault.faultClassify.id = ?");
            valueObj.add(faultClassify.getValue());
        }
        if(faultCode!=null){
            hql.append(" and caseFault.faultCode = ?");
            valueObj.add(faultCode.getValue());
        }
        if(devMod!=null){
            hql.append(" and caseFault.devMod = ?");
            valueObj.add(DeviceMod.valueOf(devMod.getValue().toString()));
        }
        if(vendorHwCode!=null){
            hql.append(" and caseFault.vendorHwCode = ?");
            valueObj.add(vendorHwCode.getValue());
        }
        if(duration!=null){
            hql.append(" and caseFault.duration = ?");
            valueObj.add(Double.parseDouble(duration.getValue().toString()));
        }
        if(faultStatus!=null){
            hql.append(" and caseFault.faultStatus = ?");
            valueObj.add(FaultStatus.valueOf(faultStatus.getValue().toString()));
        }
        if(upgrade!=null){
            hql.append(" and caseFault.upgrade = ?");
            valueObj.add(Integer.parseInt(upgrade.getValue().toString()));
        }
        if (orgName != null) {
			hql.append(" and device.organization.name = ?");
			valueObj.add(orgName.getValue());
		}
		if (devType != null) {
			hql.append(" and device.devType.id = ?");
			valueObj.add(Long.parseLong(devType.getValue().toString()));
		}
        hql.append(" order by caseFault.faultTime desc");
        List<ICaseFault> list = dao.findByHQL(hql.toString(), valueObj.toArray());
        return list ;
    }

	@Override
	public void closeHealthyModCase(List<ICaseFault> openCaseList,
			DeviceMod devMod, String modType) {
		if(openCaseList==null||openCaseList.isEmpty()){
			return;
		}
		for(ICaseFault caseFault:openCaseList){
			if(caseFault.getDevMod().equals(devMod) && caseFault.getFaultClassify().getId().equals(modType)){
				this.closeCaseFault(caseFault);
			}
		}
	}
}
