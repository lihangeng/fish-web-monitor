package com.yihuacomputer.fish.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.monitor.filter.IClassifyModStatusFilter;
import com.yihuacomputer.fish.api.monitor.filter.ReportMedthod;
import com.yihuacomputer.fish.api.monitor.report.IClassifyReport;
import com.yihuacomputer.fish.api.monitor.xfs.IClassifyMonitorService;
import com.yihuacomputer.fish.api.monitor.xfs.status.BoxStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceStatus;
import com.yihuacomputer.fish.api.monitor.xfs.status.NetStatus;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.monitor.entity.business.DeviceRegister;
import com.yihuacomputer.fish.monitor.entity.report.ClassifyReport;
import com.yihuacomputer.fish.monitor.entity.xfs.status.XfsStatus;


@Service
@Transactional
public class ClassifyMonitorService implements IClassifyMonitorService {

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private IGenericDao dao;

	private final String DEVICE_STATUS_HQL = "select d,x,r from Device d,XfsStatus x,DeviceRegister r "
	            + "where d.status=0 and x.terminalId = d.terminalId and r.terminalId = d.terminalId ";

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<IClassifyReport> pageStatus(int offset, int limit,
			IClassifyModStatusFilter statusFilter) {
		List<Object> valueObj = new ArrayList<Object>();
        StringBuffer devStatusHql = new StringBuffer();

        devStatusHql.append(this.DEVICE_STATUS_HQL);
        IOrganization org = organizationService.get(statusFilter.getOrgId());
        devStatusHql.append("and d.organization.orgFlag like ? ");
        valueObj.add(org.getOrgFlag() + "%");

        if (statusFilter.getTerminalId() != null && !statusFilter.getTerminalId().isEmpty()) {
            devStatusHql.append("and d.terminalId like ? ");
            valueObj.add("%" +statusFilter.getTerminalId() +"%");
        }

        devStatusHql.append(getModHql());
        Object[] values = valueObj.toArray();
        IPageResult<Object> result = null;
        List<Object> list = null;
        //如果后期分页
        if(limit!=0){
        	result = (IPageResult<Object>) this.dao.page(offset, limit, devStatusHql.toString(), values);
        	list = result.list();
        }
        else{
        	list = (List<Object>) this.dao.findByHQL(devStatusHql.toString(), values);
        }
        List<IClassifyReport> statusMonitorList = new ArrayList<IClassifyReport>();

        for (Object obj : list) {
            ClassifyReport statusReport = new ClassifyReport();

            Object[] status = (Object[]) obj;
            Device device = (Device) status[0];
            XfsStatus xfs = (XfsStatus) status[1];
            DeviceRegister reg = (DeviceRegister) status[2];

            statusReport.setId(device.getId());

            statusReport.setCode(device.getTerminalId());
            statusReport.setAddress(device.getAddress());
            statusReport.setIp(device.getIp().toString());

            statusReport.setType(device.getDevType().getName());
            statusReport.setInsideOutside(getEnumI18n(device.getAwayFlag().getText()));
            statusReport.setSeviceMode(getEnumI18n(device.getWorkType().getText()));
            statusReport.setOrg(device.getOrganization().getName());

            statusReport.setRegisterStatus(getEnumI18n(reg.getRegStatus().getText()));
            statusReport.setAppRelease(reg.getAtmcVersion());

            statusReport.setRunStatus(getEnumI18n(xfs.getRunStatus().getText()));
            statusReport.setModStatus(getEnumI18n(xfs.getModStatus().getText()));
            statusReport.setNetStatus(getEnumI18n(xfs.getNetStatus().getText()));
            statusReport.setBoxStatus(getEnumI18n(xfs.getBoxStatus().getText()));

            statusReport.setRun(xfs.getRunStatus());
            statusReport.setMod(xfs.getModStatus());
            statusReport.setNet(xfs.getNetStatus());
            statusReport.setBox(xfs.getBoxStatus());
            if(!xfs.getModStatus().equals(DeviceStatus.Healthy)){
            	statusReport.setMethod(ReportMedthod.ADD);
            }
            if(!xfs.getBoxStatus().equals(BoxStatus.Healthy)&&!xfs.getBoxStatus().equals(BoxStatus.Unknown)){
            	statusReport.setBoxMethod(ReportMedthod.ADD);
            }
            if(!xfs.getNetStatus().equals(NetStatus.Healthy)){
            	statusReport.setNetMethod(ReportMedthod.ADD);
            }

            statusReport.setRprStatus(xfs.getStatusRpr()==null?DeviceStatus.NoDevice:xfs.getStatusRpr().getStatus());
            statusReport.setIdcStatus(xfs.getStatusIdc()==null?DeviceStatus.NoDevice:xfs.getStatusIdc().getStatus());
            statusReport.setRetainCardCount(String.valueOf(xfs.getStatusIdc().getCards()));
            statusReport.setCimStatus(xfs.getStatusCim()==null?DeviceStatus.NoDevice:xfs.getStatusCim().getStatus());
            statusReport.setCdmStatus(xfs.getStatusCdm()==null?DeviceStatus.NoDevice:xfs.getStatusCdm().getStatus());
            statusReport.setBoxCurrentCount(String.valueOf(xfs.getBoxCurrentCount()));
            statusReport.setBoxInitCount(String.valueOf(xfs.getBoxInitCount()));
            statusReport.setCashboxLimit(String.valueOf(device.getCashboxLimit()));
            statusReport.setSiuStatus(xfs.getStatusSiu()==null?DeviceStatus.NoDevice:xfs.getStatusSiu().getStatus());
            statusReport.setTtuStatus(xfs.getStatusTtu()==null?DeviceStatus.NoDevice:xfs.getStatusTtu().getStatus());
            statusReport.setPinStatus(xfs.getStatusPin()==null?DeviceStatus.NoDevice:xfs.getStatusPin().getStatus());
            statusReport.setJprStatus(xfs.getStatusJpr()==null?DeviceStatus.NoDevice:xfs.getStatusJpr().getStatus());
            statusReport.setNfcStauts(xfs.getStatusNfc()==null?DeviceStatus.NoDevice:xfs.getStatusNfc().getStatus());
            statusReport.setPbkStatus(xfs.getStatusPbk()==null?DeviceStatus.NoDevice:xfs.getStatusPbk().getStatus());
            statusReport.setFgpStatus(xfs.getStatusFgp()==null?DeviceStatus.NoDevice:xfs.getStatusFgp().getStatus());
            statusReport.setIccStatus(xfs.getStatusIcc()==null?DeviceStatus.NoDevice:xfs.getStatusIcc().getStatus());
            statusReport.setIscStatus(xfs.getStatusIsc()==null?DeviceStatus.NoDevice:xfs.getStatusIsc().getStatus());


            statusMonitorList.add(statusReport);
        }
        IPageResult<IClassifyReport> pageResult = new PageResult<IClassifyReport>(list.size(), statusMonitorList);

        return pageResult;
	}
	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	 private String getModHql() {
	        StringBuffer statusHql = new StringBuffer();

	            statusHql.append("and (x.modStatus in (");
	                statusHql.append("'Fatal',");
	                statusHql.append("'Unknown',");
	                statusHql.append("'Warning',");
	                statusHql.append("'NoDevice',");
	            statusHql.append("'') ");

	            statusHql.append("or x.boxStatus in (");
	                statusHql.append("'Fatal',");
	                statusHql.append("'Empty',");
	                statusHql.append("'Full',");
	                statusHql.append("'High',");
	                statusHql.append("'Low',");
	                statusHql.append("'Unknown',");
	            statusHql.append("'') ");

	            statusHql.append("or x.netStatus in (");
	                statusHql.append("'Fatal',");
	                statusHql.append("'Unknown',");
	                statusHql.append("'Warning',");
	            statusHql.append("'')) ");
	        return statusHql.toString();
	    }

}
