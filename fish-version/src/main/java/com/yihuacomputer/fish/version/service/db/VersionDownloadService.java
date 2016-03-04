/**
 *
 */
package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.Version;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IDeviceVersion;
import com.yihuacomputer.fish.api.version.IDeviceVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
import com.yihuacomputer.fish.api.version.VersionCatalog;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.Task;
import com.yihuacomputer.fish.version.entity.VersionType;
import com.yihuacomputer.fish.version.entity.VersionTypeAtmTypeRelation;

/**
 * @author xuxigang
 *
 */
@Service
@Transactional
public class VersionDownloadService implements IVersionDownloadService {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IDeviceSoftVersionService deviceSoftVersionService;

    @Autowired
    private IVersionService versionService;

    @Autowired
    private IComplexDeviceService complexDeviceService;

    @Autowired
    private IDeviceVersionService dvService;

    @Autowired
    private IDeviceService deviceService ;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrganizationService orgService;

    private Logger logger = org.slf4j.LoggerFactory.getLogger(VersionDownloadService.class);

    /**
     * 可以下发的设备列表逻辑 1.目标版本大于当前版本 2.目标版本的依赖版本小于等于当前版本
     */
    @Override
    public IPageResult<LinkedDeviceForm> pageDevices(int start, int limit, IVersion selectedVersion, IJob job,
            IFilter outerFilter, long currentLoginUserId) {

//        IFilter filter = outerFilter == null ? new Filter() : outerFilter;
//
//        IVersionType vType = selectedVersion.getVersionType();
//        List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//
//            if (RestrictionColumn.CASH_TYPE.equals(vTypeRestriction.getRestrictionColumn())) {
//                filter.eq("device.cashType", CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//
//            } else if (RestrictionColumn.AWAY_FLAG.equals(vTypeRestriction.getRestrictionColumn())) {
//
//                filter.eq("device.awayFlag", AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//            }
//        }
//
//
//
//        IPageResult<IDevice> devices = complexDeviceService.page(start, limit, filter, currentLoginUserId);
//
////        IPageResult<IDevice> devices = pageCanDownDevice(start, limit, filter, currentLoginUserId);
//        List<LinkedDeviceForm> forms = new ArrayList<LinkedDeviceForm>();
//
//        if(devices.list()!=null){
//        	logger.info(String.format("devices list size is [%s]", devices.list().size()));
//        }
//        for (IDevice device : devices.list()) {
//
//            LinkedDeviceForm form = new LinkedDeviceForm(device);
//            IDeviceSoftVersion deviceSoftVersion = deviceSoftVersionService.get(device.getTerminalId(), selectedVersion
//                    .getVersionType().getTypeName());
//            if (deviceSoftVersion != null) {
//                form.setDeviceVersion(deviceSoftVersion.getVersionNo());
//                IVersion dv = deviceSoftVersion.getVersion();
//                if (dv == null) {
//                    dv = versionService.make();
//                    dv.setVersionNo(deviceSoftVersion.getVersionNo());
//                    VersionType temp = new VersionType();
//                    temp.setTypeName(selectedVersion.getVersionType().getTypeName());
//                    dv.setVersionType(temp);
//                }
//                if (!selectedVersion.isAfter(dv)) {
//                    form.setSelectable(false);
//                }
//            } else {
//                form.setSelectable(selectedVersion.isAfter(null));
//            }
//            IDeviceVersion deviceVersion = dvService.findDeviceVersion(device.getId(), selectedVersion.getId());
//
//            if (deviceVersion != null) {
//                form.setTargetVersion(selectedVersion.getVersionNo());
//                form.setTaskStatus(deviceVersion.getTaskStatus().getText());
//                form.setReason(deviceVersion.getDesc());
//            }
//
//            // 下发状态的判断
//            if(form.isSelectable()){
//            	forms.add(form);
//            }
//        }
//
//        IPageResult<LinkedDeviceForm> page = new PageResult<LinkedDeviceForm>(devices.getTotal(), forms);
    	IPageResult<LinkedDeviceForm> page = new PageResult<LinkedDeviceForm>();

        return page;
    }

    @Override
    public IPageResult<IDevice> pageLinkedDevices(int start, int limit, IJob job, IFilter filter) {
        List<IDevice> devices = new ArrayList<IDevice>();
        if (job != null) {
            List<ITask> tasks = job.getTasks();
            for (ITask task : tasks) {
                devices.add(task.getDevice());
            }
        }
        return new PageResult<IDevice>(devices, start, limit);
    }

    public IPageResult<ITask> pageTasks(int start, int limit, int jobId, IFilter filter) {
        filter.eq("job.jobId", jobId);
        return taskService.page(start, limit, filter);
    }

    @Override
    public IPageResult<?> pageDownDevices(int start, int limit, IVersion version, IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("select d.id,d.terminalId,d.ip,d.organization.name,d.devType.name,dsv.version.versionNo,v.versionNo,dv.taskStatus,dv.desc ");
        hql.append("from Device d ");
        hql.append("left join DeviceSoftVersion dsv join Version as v join DeviceVersion dv ");
        hql.append("where d.terminalId = dsv.terminalId and dsv.version.versionType.id = ? and v.id = dv.versionId and d.id = dv.deviceId");
        return dao.page(start, limit, hql.toString(), version.getVersionType().getId());

    }

    @Override
    public void rebootATM(String... ips) {

    }

    public long getMayBeDownDevice(IVersion version, IOrganization org) {

    	//org为当前登录用户所属机构
    	StringBuffer hql = new StringBuffer();
    	List<Object> filters = new ArrayList<Object>();
    	filters.add("%"+org.getOrgFlag());
    	filters.add(DevStatus.OPEN);
    	//根据版本是否有依赖版本来组织不同的hql
    	long deviceTotal = 0 ;
    	hql.append("select count(d.id) from Device d,DeviceSoftVersion dv,Version v where d.organization.orgFlag like ? and d.status = ?") ;
		hql.append(" and d.terminalId=dv.terminalId and v.versionNo=dv.versionNo and dv.typeName=v.versionType.typeName  and v.versionType.id=? ") ;
		filters.add(version.getVersionType().getId()) ;

		StringBuffer deviceSql = new StringBuffer() ;
		List<Object> deviceFilter = new ArrayList<Object>();
		deviceSql.append("select count(d.id) from Device d where d.status = ? and d.organization.orgFlag like ?") ;
		deviceFilter.add(DevStatus.OPEN) ;
		deviceFilter.add("%"+org.getOrgFlag()) ;

//		IVersionType vType = version.getVersionType();
//    	List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                hql.append(" and d.cashType = ?");
//                filters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//
//                deviceSql.append(" and d.cashType = ?") ;
//                deviceFilter.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                hql.append(" and d.awayFlag = ?");
//                filters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//
//                deviceSql.append(" and d.awayFlag = ?");
//                deviceFilter.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//            }
//        }

    	if(version.getDependVersion()!=null){
    		hql.append(" and v.id < ? and v.id >= ? ") ;
    		filters.add(version.getId()) ;
    		filters.add(version.getDependVersion().getId()) ;
    	}else{
    		hql.append(" and v.id >= ? ") ;
    		filters.add(version.getId()) ;
    		List<Object> deviceTotalList = dao.findByHQL(deviceSql.toString(),deviceFilter.toArray());
    		if(deviceTotalList!=null && !deviceTotalList.isEmpty()){
    			deviceTotal = (Long)deviceTotalList.get(0) ;
    		}
    	}

    	List<Object> deviceList = dao.findByHQL(hql.toString(),filters.toArray());
    	long count = 0;
    	if(deviceList!=null && !deviceList.isEmpty()){
    		count = (Long)deviceList.get(0) ;
    		if(deviceTotal>0){
    			count = deviceTotal-count ;
    		}
    	}
    	if(count == 0){
    		//当前用户机构及下属机构下无设备
    		return count;
    	}
        return count;

    }

    @Override
    public List<Object> getSelectAllForList(IVersion version, IOrganization org){
    	//org为当前登录用户所属机构
		StringBuffer hql = new StringBuffer();
    	List<Object> filters = new ArrayList<Object>();
    	//根据版本是否有依赖版本来组织不同的hql
    	hql.append("select d.id,dv.type_name,dv.version_no from dev_info d,ver_device_soft_version dv,ver_version v,ver_version_type ddt,dev_xfs_status xfsStatus,sm_org o ") ;
    	hql.append(" where d.org_id=o.id and o.org_flag like ? and d.status = ? and d.terminal_id=xfsStatus.terminal_id ") ;
    	filters.add("%" + org.getOrgFlag()) ;
    	filters.add(3) ;
    	hql.append(" and xfsStatus.net_status = ? and d.terminal_id=dv.terminal_id and v.version_no=dv.version_no ") ;
    	filters.add(NetStatus.Healthy.name()) ;
    	hql.append(" and dv.type_name=ddt.type_name and ddt.id = ?  ") ;
    	IVersionType vType = version.getVersionType();
//    	List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//    	 for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//             if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                 hql.append(" and d.cash_type = ?");
//                 filters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//             } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                 hql.append(" and d.away_flag = ?");
//                 filters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//             }
//         }
    	if(version.getDependVersion()!=null){
    		hql.append(" and v.id < ? and v.id >= ? ") ;
    		filters.add(version.getId()) ;
    		filters.add(version.getDependVersion().getId()) ;
    	}else{
    		hql.append(" and v.id < ? ") ;
    		filters.add(version.getId()) ;
    	}
    	SQLQuery query = dao.getSQLQuery(hql.toString()) ;
    	query.setString(0, "%" + org.getOrgFlag()) ;
    	query.setInteger(1, 3) ;
    	query.setString(2, NetStatus.Healthy.name()) ;
    	query.setLong(3, version.getVersionType().getId()) ;
    	int indexCount = 3;
//    	for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//            	query.setInteger(++indexCount, (CashType.valueOf(vTypeRestriction.getRestrictionValue())).getId()) ;
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//            	query.setInteger(++indexCount, (AwayFlag.valueOf(vTypeRestriction.getRestrictionValue())).getId()) ;
//            }
//        }

    	if(version.getDependVersion()!=null){
    		query.setLong(++indexCount, version.getId()) ;
    		query.setLong(++indexCount, version.getDependVersion().getId()) ;
    	}else{
    		query.setLong(++indexCount, version.getId()) ;
    	}
    	return query.list();
    }

	@Override
	public List<IDevice> getSelectAll(IVersion version, IOrganization org) {
    	//org为当前登录用户所属机构
		StringBuffer hql = new StringBuffer();
    	List<Object> filters = new ArrayList<Object>();
    	IFilter filter = new Filter() ;
    	filters.add("%"+org.getOrgFlag());
    	filters.add(DevStatus.OPEN);

    	//根据版本是否有依赖版本来组织不同的hql
    	List<IDevice> deviceAllList = null ;
    	hql.append("select d from Device d,DeviceSoftVersion dv,Version v,XfsStatus xfsStatus where d.organization.orgFlag like ? and d.status = ?") ;
    	hql.append(" and d.terminalId=xfsStatus.terminalId and xfsStatus.netStatus=? ") ;
    	filters.add(NetStatus.Healthy);
		hql.append(" and d.terminalId=dv.terminalId and v.versionNo=dv.versionNo and dv.typeName=v.versionType.typeName  and v.versionType.id=? ") ;
		filters.add(version.getVersionType().getId()) ;

		IVersionType vType = version.getVersionType();
//    	List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                hql.append(" and d.cashType = ?");
//                filters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//
//                filter.eq("cashType", CashType.valueOf(vTypeRestriction.getRestrictionValue())) ;
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                hql.append(" and d.awayFlag = ?");
//                filters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//
//                filter.eq("awayFlag", AwayFlag.valueOf(vTypeRestriction.getRestrictionValue())) ;
//            }
//        }

    	if(version.getDependVersion()!=null){
    		hql.append(" and v.id < ? and v.id >= ? ") ;
    		filters.add(version.getId()) ;
    		filters.add(version.getDependVersion().getId()) ;
    	}else{
    		hql.append(" and v.id < ? ") ;
    		filters.add(version.getId()) ;

    		filter.like("organization.orgFlag", "%"+org.getOrgFlag()) ;
    		filter.eq("status", DevStatus.OPEN) ;
//    		deviceAllList = deviceService.list(filter) ;
    	}

    	List<IDevice> deviceList = dao.findByHQL(hql.toString(),filters.toArray());
//    	List<IDevice> selectDeviceList = new ArrayList<IDevice>() ;
//    	if(deviceList!=null){
//    		selectDeviceList = deviceList ;
//    		if(deviceAllList!=null && deviceAllList.size()>0){
//    			deviceAllList.removeAll(deviceList) ;
//    			selectDeviceList = deviceAllList ;
//    		}
//    	}
    	return deviceList ;
	}

	   /**
     * 可以下发的设备列表逻辑 1.目标版本大于当前版本 2.目标版本的依赖版本小于等于当前版本
     */
    @Override
    public IPageResult<LinkedDeviceForm> pageDevices(int start, int limit, IVersion selectedVersion, IJob job,
            IFilter outerFilter, long currentLoginUserId, IVersion devVersion) {

        IFilter filter = outerFilter == null ? new Filter() : outerFilter;


//        IPageResult<IDevice> devices = complexDeviceService.page(start, limit, filter, currentLoginUserId);

        IPageResult<LinkedDeviceForm> page = null ;

        List<LinkedDeviceForm> forms = new ArrayList<LinkedDeviceForm>();
        if(selectedVersion.getVersionType().getVersionCatalog()==VersionCatalog.ADVERT){
        	IPageResult<Object> devices = pageCanDownDeviceForAdvert(start, limit, filter, selectedVersion,devVersion);
        	if(devices.list()!=null){
            	logger.info(String.format("devices list size is [%s]", devices.list().size()));
            }

        	Object[] tmpObjs = null ;
            for (Object obj : devices.list()) {
            	tmpObjs = (Object[])obj ;
                String terminalId = (String)tmpObjs[0] ;
                IDevice device = deviceService.get(terminalId) ;
                LinkedDeviceForm form = new LinkedDeviceForm(device);
                IDeviceSoftVersion deviceSoftVersion = deviceSoftVersionService.get(terminalId, selectedVersion
                      .getVersionType().getTypeName());
                if (deviceSoftVersion != null) {
                    form.setDeviceVersion(deviceSoftVersion.getVersionNo());
                    IVersion dv = deviceSoftVersion.getVersion();
                    if (dv == null) {
                        dv = versionService.make();
                        dv.setVersionNo(deviceSoftVersion.getVersionNo());
                        VersionType temp = new VersionType();
                        temp.setTypeName(selectedVersion.getVersionType().getTypeName());
                        dv.setVersionType(temp);
                    }
//                    if (!selectedVersion.isAfter(dv)) {
//                        form.(false);
//                    }
                } 
//                else {
//                    form.setSelectable(selectedVersion.isAfter(null));
//                }
                IDeviceVersion deviceVersion = dvService.findDeviceVersion(device.getId(), selectedVersion.getId());

                if (deviceVersion != null) {
                    form.setTargetVersion(selectedVersion.getVersionNo());
                    form.setTaskStatus(deviceVersion.getTaskStatus().getText());
                    form.setReason(deviceVersion.getDesc());
                }

                // 下发状态的判断
                forms.add(form);
            }
            page = new PageResult<LinkedDeviceForm>(devices.getTotal(), forms);

        }else{
        	IPageResult<Object> devices = pageCanDownDevice(start, limit, filter, selectedVersion,devVersion);
            if(devices.list()!=null){
            	logger.info(String.format("devices list size is [%s]", devices.list().size()));
            }
        	Object[] tmpObjs = null ;
            IDevice device = null ;
            LinkedDeviceForm form = null ;
            IDeviceSoftVersion deviceSoftVersion = null ;
            IXfsStatus xfsStatus = null ;
            for (Object obj : devices.list()) {
            	tmpObjs = (Object[])obj ;
                device = (IDevice)tmpObjs[0] ;
                deviceSoftVersion = (IDeviceSoftVersion)tmpObjs[1] ;
                xfsStatus = (IXfsStatus)tmpObjs[2] ;
                form = new LinkedDeviceForm(device);
                if (deviceSoftVersion != null) {
                    form.setDeviceVersion(deviceSoftVersion.getVersionNo());
                    IVersion dv = deviceSoftVersion.getVersion();
                    if (dv == null) {
                        dv = versionService.make();
                        dv.setVersionNo(deviceSoftVersion.getVersionNo());
                        VersionType temp = new VersionType();
                        temp.setTypeName(selectedVersion.getVersionType().getTypeName());
                        dv.setVersionType(temp);
                    }
//                	if(!selectedVersion.isAfter(dv)||!xfsStatus.getNetStatus().equals(NetStatus.Healthy)){
//                		form.setSelectable(false);
//                	}
                } 
//                else {
//                	form.setSelectable(xfsStatus.getNetStatus().equals(NetStatus.Healthy)&&selectedVersion.isAfter(null));
//                }
                IDeviceVersion deviceVersion = dvService.findDeviceVersion(device.getId(), selectedVersion.getId());

                if (deviceVersion != null) {
                    form.setTargetVersion(selectedVersion.getVersionNo());
                    form.setTaskStatus(deviceVersion.getTaskStatus().getText());
                    form.setReason(deviceVersion.getDesc());
                }

                // 下发状态的判断
                forms.add(form);
            }
            page = new PageResult<LinkedDeviceForm>(devices.getTotal(), forms);
        }
        return page;
    }

	private IPageResult<Object> pageCanDownDevice(int start, int limit,
			IFilter filter, IVersion version,IVersion deviceVersion) {
		StringBuffer hql = new StringBuffer();
		List<Object> fixedFilters = new ArrayList<Object>();
		IFilterEntry orgFlag = filter.getFilterEntry("orgFlag") ;
		IFilterEntry ip = filter.getFilterEntry("ip") ;
		IFilterEntry atmTypeId = filter.getFilterEntry("atmTypeId") ;
		IFilterEntry terminalId = filter.getFilterEntry("terminalId") ;

		IFilterEntry atmGroupId = filter.getFilterEntry("atmGroup") ;

    	//根据版本是否有依赖版本来组织不同的hql
    	hql.append("select d,dv,xfsStatus,case xfsStatus.netStatus when 'Healthy' then 0 else 1 end as ns from Device d,DeviceSoftVersion dv,Version v,XfsStatus xfsStatus ") ;
    	if(atmGroupId!=null){
    		hql.append(",AtmGroup atmGroup,DeviceGroupObj deviceGroupObj ") ;
    	}
    	hql.append(" where d.organization.orgFlag like ? and d.status = ?") ;
    	fixedFilters.add("%" + orgFlag.getValue()) ;
    	fixedFilters.add(DevStatus.OPEN);
    	hql.append(" and d.terminalId=xfsStatus.terminalId ") ;
//    	and xfsStatus.netStatus=? ") ;
//    	fixedFilters.add(NetStatus.Healthy) ;

    	if(atmGroupId!=null){
	    	hql.append(" and atmGroup.id = deviceGroupObj.groupId and atmGroup.id = ? and deviceGroupObj.deviceId=d.id ") ;
	    	fixedFilters.add(atmGroupId.getValue()) ;
    	}
		hql.append(" and d.terminalId=dv.terminalId and v.versionNo=dv.versionNo and dv.typeName=v.versionType.typeName  and v.versionType.id=? ") ;
		fixedFilters.add(version.getVersionType().getId()) ;

		IVersionType vType = version.getVersionType();
//    	List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                hql.append(" and d.cashType = ?");
//                fixedFilters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                hql.append(" and d.awayFlag = ?");
//                fixedFilters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//            }
//        }

    	if(version.getDependVersion()!=null){
    		hql.append(" and v.id < ? and v.id >= ? ") ;
    		fixedFilters.add(version.getId()) ;
    		fixedFilters.add(version.getDependVersion().getId()) ;
    	}else{
    		hql.append(" and v.id < ? ") ;
    		fixedFilters.add(version.getId()) ;
    	}

    	if(ip!=null){
    		hql.append(" and d.ip = ? ") ;
    		fixedFilters.add(ip.getValue()) ;
    	}
    	if(atmTypeId!=null){
    		hql.append(" and d.devType.id = ? ") ;
    		fixedFilters.add(Long.parseLong(atmTypeId.getValue().toString())) ;
    	}
    	if(terminalId != null){
    		hql.append(" and d.terminalId like ? ") ;
    		fixedFilters.add(terminalId.getValue()+"%") ;
    	}

    	if(deviceVersion!=null){
    		hql.append(" and dv.versionNo = ? ") ;
    	    fixedFilters.add(deviceVersion.getVersionNo()) ;
    	}

    	hql.append(" order by  ns,dv.versionNo,d.terminalId") ;

        return (IPageResult<Object>) dao.page(start, limit, hql.toString(),fixedFilters.toArray());
	}


	private IPageResult<Object> pageCanDownDeviceForAdvert(int start, int limit,
			IFilter filter, IVersion version,IVersion deviceVersion) {
		StringBuffer hql = new StringBuffer();
		List<Object> fixedFilters = new ArrayList<Object>();
		IFilterEntry orgFlag = filter.getFilterEntry("orgFlag") ;
		IFilterEntry ip = filter.getFilterEntry("ip") ;
		IFilterEntry atmTypeId = filter.getFilterEntry("atmTypeId") ;
		IFilterEntry terminalId = filter.getFilterEntry("terminalId") ;

    	//根据版本是否有依赖版本来组织不同的hql
//    	hql.append("select d from Device d,XfsStatus xfsStatus where d.organization.orgFlag like ? and d.status = ?") ;
//    	fixedFilters.add("%" + orgFlag.getValue()) ;
//    	fixedFilters.add(Status.OPEN);
//    	hql.append(" and d.terminalId=xfsStatus.terminalId and xfsStatus.netStatus=? ") ;
//    	fixedFilters.add(NetStatus.Healthy) ;
//		IVersionType vType = version.getVersionType();
//    	List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                hql.append(" and d.cashType = ?");
//                fixedFilters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                hql.append(" and d.awayFlag = ?");
//                fixedFilters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//            }
//        }
//
//    	if(ip!=null){
//    		hql.append(" and d.ip = ? ") ;
//    		fixedFilters.add(ip.getValue()) ;
//    	}
//    	if(atmTypeId!=null){
//    		hql.append(" and d.devType.id = ? ") ;
//    		fixedFilters.add(Long.parseLong(atmTypeId.getValue().toString())) ;
//    	}
//    	if(terminalId != null){
//    		hql.append(" and d.terminalId like ? ") ;
//    		fixedFilters.add(terminalId.getValue()+"%") ;
//    	}
//
//    	hql.append(" order by d.terminalId") ;


		hql.append("select t3.ttid,t3.tvn ") ;
		hql.append(" from (select t1.tid ttid,t2.vn tvn ") ;
		hql.append(" from (select d.TERMINAL_ID tid ") ;
		hql.append(" from dev_info d,dev_xfs_status dxs,sm_org org where d.org_id=org.id and org.org_flag like ? and d.TERMINAL_ID=dxs.TERMINAL_ID and dxs.NET_STATUS=? and d.status=? ") ;
		fixedFilters.add("%" + orgFlag.getValue()) ;
		fixedFilters.add(NetStatus.Healthy.name()) ;
		fixedFilters.add(3) ;
		IVersionType vType = version.getVersionType();
//    	List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                hql.append(" and d.cash_type = ?");
//                fixedFilters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                hql.append(" and d.away_flag = ?");
//                fixedFilters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//            }
//        }
    	if(ip!=null){
			hql.append(" and d.ip = ? ") ;
			fixedFilters.add(ip.getValue()) ;
		}
		if(atmTypeId!=null){
			hql.append(" and d.dev_type_id = ? ") ;
			fixedFilters.add(Long.parseLong(atmTypeId.getValue().toString())) ;
		}
		if(terminalId != null){
			hql.append(" and d.terminal_id like ? ") ;
			fixedFilters.add(terminalId.getValue()+"%") ;
		}
        hql.append(") t1 left join ") ;
		hql.append(" (select vsv.TERMINAL_ID tid,vv.VERSION_NO vn ") ;
		hql.append(" from VER_DEVICE_SOFT_VERSION vsv,VER_VERSION vv,VER_VERSION_TYPE vvt,dev_info d,dev_xfs_status dxs,sm_org org ") ;
		hql.append(" where d.TERMINAL_ID=vsv.TERMINAL_ID and d.TERMINAL_ID=dxs.TERMINAL_ID and d.org_id=org.id and org.org_flag like ? and dxs.NET_STATUS=? and d.status=? and vsv.VERSION_NO = vv.VERSION_NO and vsv.TYPE_NAME= vvt.TYPE_NAME and vv.VERSION_TYPE_ID=?  and vv.VERSION_TYPE_ID=vvt.ID ") ;
		fixedFilters.add("%" + orgFlag.getValue()) ;
		fixedFilters.add(NetStatus.Healthy.name()) ;
		fixedFilters.add(3) ;
		fixedFilters.add(version.getVersionType().getId()) ;
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                hql.append(" and d.cash_type = ?");
//                fixedFilters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                hql.append(" and d.away_flag = ?");
//                fixedFilters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//            }
//        }
    	if(ip!=null){
			hql.append(" and d.ip = ? ") ;
			fixedFilters.add(ip.getValue()) ;
		}
		if(atmTypeId!=null){
			hql.append(" and d.dev_type_id = ? ") ;
			fixedFilters.add(Long.parseLong(atmTypeId.getValue().toString())) ;
		}
		if(terminalId != null){
			hql.append(" and d.terminal_id like ? ") ;
			fixedFilters.add(terminalId.getValue()+"%") ;
		}
		hql.append(") t2 ") ;
		hql.append(" on t1.tid = t2.tid ) t3 ") ;
		hql.append(" where t3.ttid not in (select vsv.TERMINAL_ID tid ") ;
		hql.append(" from VER_DEVICE_SOFT_VERSION vsv,VER_VERSION vv,VER_VERSION_TYPE vvt,dev_info d,dev_xfs_status dxs,VER_VERSION v1,sm_org org ") ;
		hql.append(" where d.TERMINAL_ID=vsv.TERMINAL_ID and d.TERMINAL_ID=dxs.TERMINAL_ID and d.org_id=org.id and org.org_flag like ? and dxs.NET_STATUS=? and d.status=? and vsv.VERSION_NO = vv.VERSION_NO and vsv.TYPE_NAME= vvt.TYPE_NAME and vv.VERSION_TYPE_ID=?  and vv.VERSION_TYPE_ID=vvt.ID and vv.id>v1.id and v1.id=? ") ;
		fixedFilters.add("%" + orgFlag.getValue()) ;
		fixedFilters.add(NetStatus.Healthy.name()) ;
		fixedFilters.add(3) ;
		fixedFilters.add(version.getVersionType().getId()) ;
		fixedFilters.add(version.getId()) ;
//        for (IVersionTypeRestriction vTypeRestriction : vTypeRestrictions) {
//            if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)) {
//                hql.append(" and d.cash_type = ?");
//                fixedFilters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
//            } else if (vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)) {
//                hql.append(" and d.away_flag = ?");
//                fixedFilters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
//            }
//        }
    	if(ip!=null){
			hql.append(" and d.ip = ? ") ;
			fixedFilters.add(ip.getValue()) ;
		}
		if(atmTypeId!=null){
			hql.append(" and d.dev_type_id = ? ") ;
			fixedFilters.add(Long.parseLong(atmTypeId.getValue().toString())) ;
		}
		if(terminalId != null){
			hql.append(" and d.terminal_id like ? ") ;
			fixedFilters.add(terminalId.getValue()+"%") ;
		}
		hql.append(") ") ;
		hql.append(" order by t3.ttid ") ;
		return (IPageResult<Object>) dao.pageForSQL(start, limit, hql.toString(),fixedFilters.toArray());
//        return (IPageResult<IDevice>) dao.page(start, limit, hql.toString(),fixedFilters.toArray());
	}


	   private IOrganization getBelongsOrg(IFilter outerFilter) {
	        IFilter filter = null;
	        if (outerFilter == null) {
	            filter = new Filter();
	        }
	        else {
	            filter = outerFilter;
	        }
	        String orgId = null;
	        for (IFilterEntry c : filter.entrySet()) {
	            if (c.getKey().equals("device.organization")) {
	                orgId = c.getValue().toString();
	                filter.entrySet().remove(c);
	                break;
	            }
	        }

	        logger.info(String.format("orgId is [%s]", orgId));
	        if (orgId != null && !"".equals(orgId)) {
	            return orgService.get(orgId);
	        }

	        return null;
	    }
	   
	   //-------------------------增加
	   public IPageResult<LinkedDeviceForm> pageDevices(int start, int limit,IVersion version,IFilter outerFilter){
		   IPageResult<Object> resultObject = getCanPushDevicePagesInfo(start, limit,version,outerFilter);
		   List<LinkedDeviceForm> formPage = new ArrayList<LinkedDeviceForm>();
		   for(Object objects:resultObject.list()){
			   LinkedDeviceForm linkedDeviceForm = new LinkedDeviceForm();
			   Object []object =(Object[])objects;
			   IDevice device = (IDevice)object[0];
			   IDeviceSoftVersion deviceSoftVersion = (IDeviceSoftVersion)object[1];
			   linkedDeviceForm.setAddress(device.getAddress());
			   linkedDeviceForm.setCode(device.getTerminalId());
			   linkedDeviceForm.setDeviceType(device.getDevType().getName());
			   linkedDeviceForm.setDeviceVersion(deviceSoftVersion.getVersionNo());
			   linkedDeviceForm.setInstallDate(DateUtils.getDate(device.getInstallDate()));
			   linkedDeviceForm.setIp(device.getIp().toString());
			   linkedDeviceForm.setOrgName(device.getOrganization().getName());
			   linkedDeviceForm.setPort(1);
			   linkedDeviceForm.setId(device.getId());
			   linkedDeviceForm.setReason(deviceSoftVersion.getDesc());
			   linkedDeviceForm.setTargetVersion(version.getVersionNo());
//			   linkedDeviceForm.setTaskStatus(deviceSoftVersion.get);
			   formPage.add(linkedDeviceForm);
//			   linkedDeviceForm.set
		   }
		   return new PageResult<LinkedDeviceForm>(resultObject.getTotal(),formPage);
	   }

   	public IPageResult<Object> getCanPushDevicePagesInfo(int start, int limit,IVersion version,IFilter outerFilter){

		List<Object> argList=new ArrayList<Object>();
		Object orgFlag = outerFilter.getValue("orgFlag");
		String orgFlagStr =orgFlag+ "%";
		Object terminalId = outerFilter.getValue("terminalId");
		Object atmTypeId = outerFilter.getValue("atmTypeId");
		Object ip = outerFilter.getValue("ip");
		//如果有没有依赖版本则版本号要小于当前要下发的版本号;并且下发的设备如果存在下发的任务，任务不可以为没有失败的任务
		StringBuffer hqlDevice = new StringBuffer();
		if(null==version.getDependVersion()){
			//设备可下发成功的台数
	    	hqlDevice.append("select device,deviceSoftVersion from  ").
			append(Device.class.getSimpleName()).append(" device ,").
			append(Version.class.getSimpleName()).append(" version ,");
	    	//如果是广告等不显示的版本不用进行设备型号比对
			if(version.getVersionType().isDisplay()){
				hqlDevice.append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType, ");
			}
			hqlDevice.append(DeviceSoftVersion.class.getSimpleName()).append(" deviceSoftVersion ").
	    	append(" where version.id=?  and device.status=?");
			argList.add(version.getId());
			argList.add(DevStatus.OPEN);
			if(version.getVersionType().isDisplay()){
				hqlDevice.append(" and device.devType.id=versionatmType.atmTypeId ").append(" and versionatmType.versionTypeId=version.versionType.id ");
			}
	    	hqlDevice.append(" and deviceSoftVersion.typeName=version.versionType.typeName and deviceSoftVersion.terminalId=device.terminalId  and version.versionStr>deviceSoftVersion.versionStr ");
	    	
	    	if(terminalId!=null){
	    		hqlDevice.append(" and device.terminalId like ? ");
	    		argList.add("%"+String.valueOf(terminalId)+"%");
	    	}
	    	if(atmTypeId!=null){
	    		hqlDevice.append(" and device.devType.id =? ");
	    		argList.add(Long.parseLong(String.valueOf(atmTypeId)));
	    	}
	    	if(ip!=null){
	    		hqlDevice.append(" and device.ip =? ");
	    		argList.add(new IP(String.valueOf(ip)));
	    	}
	    	hqlDevice.append(" and device.organization.orgFlag like ? and device.id not in");
	    	argList.add(orgFlagStr);
			//下发成功的设备
	    	hqlDevice.append("(select device1.id from  ").
			append(Task.class.getSimpleName()).append( " task1 , ").
			append(Device.class.getSimpleName()).append(" device1 ,");

			if(version.getVersionType().isDisplay()){
				hqlDevice.append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType1 ,");
			}
	    	hqlDevice.append(Version.class.getSimpleName()).append(" version1 ").
			append(" where  task1.deviceId=device1.id ").
			append(" and task1.version.id=version1.id and ").
			append(" version1.id=? and task1.status in('NEW','RUN','NOTICED','NOTICE_APP_OK','DOWNLOADED','DEPLOYED','DEPLOYED_WAIT') and device1.status=?");
	    	if(version.getVersionType().isDisplay()){
		    	hqlDevice.append(" and device1.devType.id=versionatmType1.atmTypeId ").
		    	append(" and versionatmType1.versionTypeId=version1.versionType.id ");
	    	}
			argList.add(version.getId());
			argList.add(DevStatus.OPEN);
	    	if(terminalId!=null){
	    		hqlDevice.append(" and device1.terminalId like ? ");
	    		argList.add("%"+String.valueOf(terminalId)+"%");
	    	}
	    	if(atmTypeId!=null){
	    		hqlDevice.append(" and device1.devType.id =? ");
	    		argList.add(Long.parseLong(String.valueOf(atmTypeId)));
	    	}
	    	if(ip!=null){
	    		hqlDevice.append(" and device1.ip =? ");
	    		argList.add(new IP(String.valueOf(ip)));
	    	}
	    	argList.add(orgFlagStr);
			hqlDevice.append(" and device1.organization.orgFlag like ?) ");
    	}
//    	存在版本依赖关系
    	else{
    		//设备可下发成功的台数
        	hqlDevice.append("select device,deviceSoftVersion from  ").
    		append(Device.class.getSimpleName()).append(" device ,").
    		append(Version.class.getSimpleName()).append(" version ,").
    		append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType, ").
    		append(DeviceSoftVersion.class.getSimpleName()).append(" deviceSoftVersion ").
    		append(" where version.id=?  and device.status=?");
    		argList.add(version.getId());
			argList.add(DevStatus.OPEN);
			hqlDevice.append(" and device.devType.id=versionatmType.atmTypeId ").
        	append(" and versionatmType.versionTypeId=version.versionType.id ").
    		append(" and version.dependVersion.versionStr>=deviceSoftVersion.versionStr").
    		//依赖版本大于等于当前设备上版本，但是如果当前版本处于初始化即31个0;还是不可以下发
    		append(" and deviceSoftVersion.versionStr<>'0000000000000000000000000000000'").
    		append(" and version.versionType.typeName=deviceSoftVersion.typeName ").
    		append(" and device.terminalId=deviceSoftVersion.terminalId ");
			if(terminalId!=null){
	    		hqlDevice.append(" and device.terminalId like ? ");
	    		argList.add("%"+String.valueOf(terminalId)+"%");
	    	}
	    	if(atmTypeId!=null){
	    		hqlDevice.append(" and device.devType.id =? ");
	    		argList.add(Long.parseLong(String.valueOf(atmTypeId)));
	    	}
	    	if(ip!=null){
	    		hqlDevice.append(" and device.ip =? ");
	    		argList.add(new IP(String.valueOf(ip)));
	    	}
        	hqlDevice.append(" and device.organization.orgFlag like ? and device.id not in");

	    	argList.add(orgFlagStr);
    		//下发成功的设备
        	hqlDevice.append("(select device1.id from  ").
    		append(Task.class.getSimpleName()).append( " task1 , ").
    		append(Device.class.getSimpleName()).append(" device1 ,").
    		append(Version.class.getSimpleName()).append(" version1 ,").
    		append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType1 ").
    		append(" where  task1.deviceId=device1.id ").
    		append(" and task1.version.id=version1.id and ").
    		append(" version1.id=? and task1.status in('NEW','RUN','NOTICED','NOTICE_APP_OK','DOWNLOADED','DEPLOYED','DEPLOYED_WAIT') and device1.status=?").
    		append(" and device1.devType.id=versionatmType1.atmTypeId ");
			argList.add(version.getId());
			argList.add(DevStatus.OPEN);
        	if(terminalId!=null){
	    		hqlDevice.append(" and device1.terminalId like ? ");
	    		argList.add("%"+String.valueOf(terminalId)+"%");
	    	}
	    	if(atmTypeId!=null){
	    		hqlDevice.append(" and device1.devType.id =? ");
	    		argList.add(Long.parseLong(String.valueOf(atmTypeId)));
	    	}
	    	if(ip!=null){
	    		hqlDevice.append(" and device1.ip =? ");
	    		argList.add(new IP(String.valueOf(ip)));
	    	}
    		hqlDevice.append(" and versionatmType1.versionTypeId=version1.versionType.id ").
    		append(" and device1.organization.orgFlag like ?) ");
	    	argList.add(orgFlagStr);
    	}
		@SuppressWarnings("unchecked")
		IPageResult<Object> pushResult = (IPageResult<Object>) dao.page(start, limit, hqlDevice.toString(), argList.toArray());
		return pushResult;
   	}
   	
   	
}
