package com.yihuacomputer.fish.monitor.service.db;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.monitor.filter.IStatusFilter;
import com.yihuacomputer.fish.api.monitor.report.IStatusMonitorMapOrg;
import com.yihuacomputer.fish.api.monitor.report.IStatusReport;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.propertise.IXfsPropertise;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.monitor.entity.business.DeviceRegister;
import com.yihuacomputer.fish.monitor.entity.report.StatusMonitorMapOrg;
import com.yihuacomputer.fish.monitor.entity.report.StatusReport;
import com.yihuacomputer.fish.monitor.entity.xfs.prop.XfsPropertise;
import com.yihuacomputer.fish.monitor.entity.xfs.status.XfsStatus;
import com.yihuacomputer.fish.person.service.api.IDomainOrganizationService;
import com.yihuacomputer.fish.system.entity.Organization;

@Service
@Transactional
public class XfsService implements IXfsService {

    @Autowired
    private IGenericDao dao;

    @Autowired
    private IDomainOrganizationService orgService;

    private final String OFFLINE_STATUS_HQL = "select xfs.terminalId from XfsStatus xfs where xfs.dateTime<? and xfs.netStatus='Healthy' and xfs.runStatus<>'StopManmade'";

    private final String DEVICE_STATUS_HQL = "select d,x,r from Device d,XfsStatus x,DeviceRegister r "
            + "where x.terminalId = d.terminalId and r.terminalId = d.terminalId ";

    private final String DEVICE_STATUS_MAP_HQL = "select d,x,r,m from Device d,XfsStatus x,DeviceRegister r,Marker m "
            + "where x.terminalId = d.terminalId and r.terminalId = d.terminalId and d.terminalId = m.markerId and m.markerType = 'DEVICE' ";

    private final String DEVICE_SATATUS_MAP_ORG_HQL = "select o, m from Organization o, Marker m "
            + "where o.id = m.markerId and m.markerType = 'ORG'";

    @Override
    public IXfsStatus makeXfsStatus() {
        return new XfsStatus();
    }

    @Override
    public IXfsPropertise makeXfsPropertise() {
        return new XfsPropertise();
    }

    @Override
    public void saveXfsStatus(IXfsStatus status) {
        this.dao.save(status);
    }

    public void deleteXfsStatus(IXfsStatus status) {
        this.dao.delete(status);
    }

    @Override
    public void saveXfsProp(IXfsPropertise prop) {
        this.dao.save(prop);
    }

    public void deleteXfsProp(IXfsPropertise prop) {
        this.dao.delete(prop);
    }

    @Override
    @Transactional(readOnly=true)
    public IXfsStatus loadXfsStatus(String terminalId) {
        return this.dao.get(terminalId, XfsStatus.class);
    }

    @Override
    @Transactional(readOnly=true)
    public IXfsPropertise loadXfsProp(String terminalId) {
        return this.dao.get(terminalId, XfsPropertise.class);
    }

    @Override
    public void updateXfsProp(IXfsPropertise prop) {
        this.dao.update(prop);
    }

    @Override
    public void updateXfsStatus(IXfsStatus status) {
        this.dao.update(status);

    }

    @Override
    public IXfsStatus initXfsStatus(String terminalId) {
        IXfsStatus status = this.makeXfsStatus();
        status.setTerminalId(terminalId);
        this.saveXfsStatus(status);
        return status;
    }

    @Override
    public void initXfsProp(String terminalId) {
        IXfsPropertise prop = this.makeXfsPropertise();
        prop.setTerminalId(terminalId);
        this.saveXfsProp(prop);
    }

    @Override
    public String getStatusCode(String terminalId, DeviceMod mod) {
        IXfsStatus status = this.loadXfsStatus(terminalId);
        switch (mod) {
            case IDC: {
                return status.getStatusIdc().getCode();
            }
            case NFC: {
            	return status.getStatusNfc().getCode();
            }
            case CDM: {
                return status.getStatusCdm().getCode();
            }
            case CIM: {
                return status.getStatusCim().getCode();
            }
            case RPR: {
                return status.getStatusRpr().getCode();
            }
            case JPR: {
                return status.getStatusJpr().getCode();
            }
            case PBK: {
            	return status.getStatusPbk().getCode();
            }
            case TTU: {
                return status.getStatusTtu().getCode();
            }
            case PIN: {
                return status.getStatusPin().getCode();
            }
            case SIU: {
                return status.getStatusSiu().getCode();
            }
            case ICC: {
                return status.getStatusIcc().getCode();
            }
            case FGP: {
                return status.getStatusFgp().getCode();
            }
            case ISC: {
                return status.getStatusIsc().getCode();
            }
            default: {
                return null;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true)
    public IPageResult<IStatusReport> pageStatus(int offset, int limit, IStatusFilter statusFilter, boolean isMap) {
        List<Object> valueObj = new ArrayList<Object>();
        StringBuffer devStatusHql = new StringBuffer();

        if (isMap) {
            devStatusHql.append(this.DEVICE_STATUS_MAP_HQL);
        } else {
            devStatusHql.append(this.DEVICE_STATUS_HQL);
        }

        IOrganization org = orgService.get(statusFilter.getOrgId());
        devStatusHql.append("and d.organization.orgFlag like ? ");
        valueObj.add("%" + org.getOrgFlag());

        // 设备状态
        devStatusHql.append(" and d.status=? ");
        valueObj.add(statusFilter.getDeviceStatus());


        if (statusFilter.getTerminalId() != null && !statusFilter.getTerminalId().isEmpty()) {
            devStatusHql.append("and d.terminalId like ? ");
            valueObj.add("%" +statusFilter.getTerminalId() +"%");
        } else {
            if (statusFilter.getWorkType() != 0) {
                devStatusHql.append("and d.workType=? ");
                valueObj.add(WorkType.getById(statusFilter.getWorkType()));
            }

            if (statusFilter.getAwayFlag() != 0) {
                devStatusHql.append("and d.awayFlag=? ");
                valueObj.add(AwayFlag.getById(statusFilter.getAwayFlag()));
            }

            if (statusFilter.getDevType() != 0) {
                devStatusHql.append("and d.devType.id=? ");
                valueObj.add(statusFilter.getDevType());
            }
            if (statusFilter.getDevVendor() != 0) {
                devStatusHql.append("and d.devType.devVendor.id=? ");
                valueObj.add(statusFilter.getDevVendor());
            }
            if(statusFilter.getAtmGroup()!=0){
                devStatusHql.append("and (d.id in (select devGrop.deviceId from DeviceGroupObj devGrop where devGrop.groupId=?)) ");
                valueObj.add(statusFilter.getAtmGroup());
            }

            devStatusHql.append(getModHql(statusFilter));
        }

        Object[] values = valueObj.toArray();

        PageResult<Object> result = (PageResult<Object>) this.dao.page(offset, limit, devStatusHql.toString(), values);

        List<IStatusReport> statusMonitorList = new ArrayList<IStatusReport>();

        for (Object obj : result.list()) {
            StatusReport statusReport = new StatusReport();

            Object[] status = (Object[]) obj;
            Device device = (Device) status[0];
            XfsStatus xfs = (XfsStatus) status[1];
            DeviceRegister reg = (DeviceRegister) status[2];

            statusReport.setId(device.getId());

            statusReport.setCode(device.getTerminalId());
            statusReport.setAddress(device.getAddress());
            statusReport.setIp(device.getIp().toString());

            statusReport.setType(device.getDevType().getName());
            statusReport.setInsideOutside(String.valueOf(device.getAwayFlag().getText()));
            statusReport.setSeviceMode(String.valueOf(device.getWorkType().getText()));
            statusReport.setOrg(device.getOrganization().getName());

            statusReport.setRegisterStatus(reg.getRegStatus().getText());
            statusReport.setAppRelease(reg.getAtmcVersion());

            statusReport.setRunStatus(xfs.getRunStatus().getText());
            statusReport.setModStatus(xfs.getModStatus().getText());
            statusReport.setNetStatus(xfs.getNetStatus().getText());
            statusReport.setBoxStatus(xfs.getBoxStatus().getText());

            statusReport.setRun(xfs.getRunStatus());
            statusReport.setMod(xfs.getModStatus());
            statusReport.setNet(xfs.getNetStatus());
            statusReport.setBox(xfs.getBoxStatus());

            statusReport.setRprStatus(xfs.getStatusRpr()==null?DeviceStatus.Unknown:xfs.getStatusRpr().getStatus());
            statusReport.setIdcStatus(xfs.getStatusIdc()==null?DeviceStatus.Unknown:xfs.getStatusIdc().getStatus());
            statusReport.setRetainCardCount(String.valueOf(xfs.getStatusIdc().getCards()));
            statusReport.setCimStatus(xfs.getStatusCim()==null?DeviceStatus.Unknown:xfs.getStatusCim().getStatus());
            statusReport.setCdmStatus(xfs.getStatusCdm()==null?DeviceStatus.Unknown:xfs.getStatusCdm().getStatus());
            statusReport.setBoxCurrentCount(String.valueOf(xfs.getBoxCurrentCount()));
            statusReport.setBoxInitCount(String.valueOf(xfs.getBoxInitCount()));
            statusReport.setCashboxLimit(String.valueOf(device.getCashboxLimit()));
            statusReport.setSiuStatus(xfs.getStatusSiu()==null?DeviceStatus.Unknown:xfs.getStatusSiu().getStatus());
            statusReport.setTtuStatus(xfs.getStatusTtu()==null?DeviceStatus.Unknown:xfs.getStatusTtu().getStatus());
            statusReport.setPinStatus(xfs.getStatusPin()==null?DeviceStatus.Unknown:xfs.getStatusPin().getStatus());
            statusReport.setJprStatus(xfs.getStatusJpr()==null?DeviceStatus.Unknown:xfs.getStatusJpr().getStatus());
            statusReport.setNfcStauts(xfs.getStatusNfc()==null?DeviceStatus.Unknown:xfs.getStatusNfc().getStatus());
            statusReport.setPbkStatus(xfs.getStatusPbk()==null?DeviceStatus.Unknown:xfs.getStatusPbk().getStatus());

            statusReport.setIccStatus(xfs.getStatusIcc()==null?DeviceStatus.Unknown:xfs.getStatusIcc().getStatus()) ;
            statusReport.setFgpStatus(xfs.getStatusFgp()==null?DeviceStatus.Unknown:xfs.getStatusFgp().getStatus()) ;
            statusReport.setIscStatus(xfs.getStatusIsc()==null?DeviceStatus.Unknown:xfs.getStatusIsc().getStatus()) ;

            statusMonitorList.add(statusReport);
        }
        IPageResult<IStatusReport> pageResult = new PageResult<IStatusReport>(result.getTotal(), statusMonitorList);

        return pageResult;
    }

    @Override
    @Transactional(readOnly=true)
    public IPageResult<IStatusReport> pageStatus(int offset, int limit, IStatusFilter statusFilter) {
        return pageStatus(offset, limit, statusFilter, false);
    }

    /**
     * 组织状态监控条件的HQL语句
     *
     * @param statusFilter
     * @return
     */
    private String getModHql(IStatusFilter statusFilter) {
        StringBuffer statusHql = new StringBuffer();

        if (statusFilter.getRunStatusFilter().isInitial() || statusFilter.getRunStatusFilter().isUnknow()
                || statusFilter.getRunStatusFilter().isHealth() || statusFilter.getRunStatusFilter().isHalf()
                || statusFilter.getRunStatusFilter().isCustomer() || statusFilter.getRunStatusFilter().isMaintain()
                || statusFilter.getRunStatusFilter().isVdm() || statusFilter.getRunStatusFilter().isShutdown()
                || statusFilter.getRunStatusFilter().isReBoot() || statusFilter.getRunStatusFilter().isAtmpStop()
                || statusFilter.getRunStatusFilter().isStopManMade() || statusFilter.getRunStatusFilter().isStopMod()
                || statusFilter.getRunStatusFilter().isStopUnCashIn() || statusFilter.getRunStatusFilter().isStop()) {
            statusHql.append("and x.runStatus in (");

            if (statusFilter.getRunStatusFilter().isInitial()) {
                statusHql.append("'Initial',");
            }
            if (statusFilter.getRunStatusFilter().isUnknow()) {
                statusHql.append("'Unknown',");
            }
            if (statusFilter.getRunStatusFilter().isHealth()) {
                statusHql.append("'Healthy',");
            }
            if (statusFilter.getRunStatusFilter().isHalf()) {
                statusHql.append("'SubHealth',");
            }
            if (statusFilter.getRunStatusFilter().isCustomer()) {
                statusHql.append("'Customer',");
            }
            if (statusFilter.getRunStatusFilter().isMaintain()) {
                statusHql.append("'Maintain',");
            }
            if (statusFilter.getRunStatusFilter().isVdm()) {
                statusHql.append("'Vdm',");
            }
            if (statusFilter.getRunStatusFilter().isShutdown()) {
                statusHql.append("'Halt',");
            }
            if (statusFilter.getRunStatusFilter().isReBoot()) {
                statusHql.append("'ReBoot',");
            }
            if (statusFilter.getRunStatusFilter().isAtmpStop()) {
                statusHql.append("'StopAtmp',");
            }
            if (statusFilter.getRunStatusFilter().isStopManMade()) {
                statusHql.append("'StopManmade',");
            }
            if (statusFilter.getRunStatusFilter().isStopMod()) {
                statusHql.append("'StopMod',");
            }

            if (statusFilter.getRunStatusFilter().isStopUnCashIn()) {
                statusHql.append("'StopUnCashIn',");
            }

            if (statusFilter.getRunStatusFilter().isStop()) {
                statusHql.append("'StopUnKnown',");
            }

            statusHql.append("'') ");
        }
        if (statusFilter.getModStatusFilter().isFatal() || statusFilter.getModStatusFilter().isHealth()
                || statusFilter.getModStatusFilter().isUnknown() || statusFilter.getModStatusFilter().isWarning()
                || statusFilter.getModStatusFilter().isNodevice()) {
            statusHql.append("and x.modStatus in (");
            if (statusFilter.getModStatusFilter().isFatal()) {
                statusHql.append("'Fatal',");
            }
            if (statusFilter.getModStatusFilter().isHealth()) {
                statusHql.append("'Healthy',");
            }
            if (statusFilter.getModStatusFilter().isUnknown()) {
                statusHql.append("'Unknown',");
            }
            if (statusFilter.getModStatusFilter().isWarning()) {
                statusHql.append("'Warning',");
            }
            if (statusFilter.getModStatusFilter().isNodevice()) {
                statusHql.append("'NoDevice',");
            }
            statusHql.append("'') ");
        }
        if (statusFilter.getBoxStatusFilter().isHealthy() || statusFilter.getBoxStatusFilter().isFatal()
                || statusFilter.getBoxStatusFilter().isEmpty() || statusFilter.getBoxStatusFilter().isFull()
                || statusFilter.getBoxStatusFilter().isHigh() || statusFilter.getBoxStatusFilter().isLow()
                || statusFilter.getBoxStatusFilter().isUnknown()) {
            statusHql.append("and x.boxStatus in (");
            if (statusFilter.getBoxStatusFilter().isFatal()) {
                statusHql.append("'Fatal',");
            }
            if (statusFilter.getBoxStatusFilter().isHealthy()) {
                statusHql.append("'Healthy',");
            }
            if (statusFilter.getBoxStatusFilter().isEmpty()) {
                statusHql.append("'Empty',");
            }
            if (statusFilter.getBoxStatusFilter().isFull()) {
                statusHql.append("'Full',");
            }
            if (statusFilter.getBoxStatusFilter().isHigh()) {
                statusHql.append("'High',");
            }
            if (statusFilter.getBoxStatusFilter().isLow()) {
                statusHql.append("'Low',");
            }
            if (statusFilter.getBoxStatusFilter().isUnknown()) {
                statusHql.append("'Unknown',");
            }
            statusHql.append("'') ");
        }
        if (statusFilter.getNetStatusFilter().isHealth() || statusFilter.getNetStatusFilter().isWarning()
                || statusFilter.getNetStatusFilter().isFatal() || statusFilter.getNetStatusFilter().isUnknown()) {
            statusHql.append("and x.netStatus in (");
            if (statusFilter.getNetStatusFilter().isHealth()) {
                statusHql.append("'Healthy',");
            }
            if (statusFilter.getNetStatusFilter().isFatal()) {
                statusHql.append("'Fatal',");
            }
            if (statusFilter.getNetStatusFilter().isUnknown()) {
                statusHql.append("'Unknown',");
            }
            if (statusFilter.getNetStatusFilter().isWarning()) {
                statusHql.append("'Warning',");
            }
            statusHql.append("'') ");
        }
        return statusHql.toString();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true)
    public List<? extends Object> loadOfflineStatus(int offset, int limit, String dateTime) {
        Query query = dao.getHibernateSession().createQuery(OFFLINE_STATUS_HQL);
        query.setString(0, dateTime);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    @Transactional(readOnly=true)
    public IPageResult<IStatusMonitorMapOrg> pageStatusMapOrg(int offset, int limit, String orgId) {

        StringBuffer hql = new StringBuffer();
        List<Object> valueObj = new ArrayList<Object>();
        hql.append(DEVICE_SATATUS_MAP_ORG_HQL);
        if (orgId != null && !orgId.isEmpty()) {
            IOrganization org = orgService.get(orgId);
            hql.append(" and o.orgFlag like ? ");
            valueObj.add("%" + org.getOrgFlag());
        }

        List<IStatusMonitorMapOrg> statusMonitorList = new ArrayList<IStatusMonitorMapOrg>();
        PageResult<Object> result = (PageResult<Object>) this.dao.page(offset, limit, hql.toString(),
                valueObj.toArray());
        IStatusMonitorMapOrg statusMonitorMapOrg = null;
        for (Object obj : result.list()) {
            statusMonitorMapOrg = new StatusMonitorMapOrg();

            Object[] status = (Object[]) obj;
            Organization org = (Organization) status[0];
           
            statusMonitorMapOrg.setAddress(org.getAddress());
            statusMonitorMapOrg.setCode(org.getCode());
            statusMonitorMapOrg.setDescription(org.getDescription());
            statusMonitorMapOrg.setId(org.getId());
            statusMonitorMapOrg.setName(org.getName());
            statusMonitorMapOrg.setOrgFlag(org.getOrgFlag());

            if (org.getOrganizationType() != null) {
                statusMonitorMapOrg.setOrgType("" + org.getOrganizationType().getText());
            }

            if (org.getOrganizationState() != null) {
                statusMonitorMapOrg.setState("" + org.getOrganizationState().getText());
            }

            statusMonitorList.add(statusMonitorMapOrg);
        }

        IPageResult<IStatusMonitorMapOrg> pageResult = new PageResult<IStatusMonitorMapOrg>(result.getTotal(),
                statusMonitorList);

        return pageResult;
    }

	@Override
	public IXfsStatus makeOfflineXfsStatus() {
		IXfsStatus xfsStatus = new XfsStatus();
		xfsStatus.setNetStatus(NetStatus.Fatal);
		return xfsStatus;
	}
}
