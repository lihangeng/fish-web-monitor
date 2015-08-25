package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IDeviceVersion;
import com.yihuacomputer.fish.api.version.IDeviceVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeRestriction;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
import com.yihuacomputer.fish.api.version.RestrictionColumn;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.version.entity.VersionType;

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

    /**
     * 对应增加作业页面可选择的设备列表
     * 还可以下发的设备列表
     * 对应的逻辑是
     * 1.目标版本大于当前版本
     * 2.目标版本的依赖版本小于等于当前版本
     * 
     * 3.软件分类适用的设备型号 @sincie 2.0增加
     */
	@Override
    public IPageResult<LinkedDeviceForm> pageDevices(int start, int limit, IVersion selectedVersion, IFilter outerFilter,long currentLoginUserId) {
        IFilter filter = null;
        if (outerFilter == null) {
            filter = new Filter();
        }
        else {
            filter = outerFilter;
        }
        IVersionType vType = selectedVersion.getVersionType();
//        List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
//        for(IVersionTypeRestriction vTypeRestriction : vTypeRestrictions){
//            if(vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)){
//                filter.addFilterEntry(FilterFactory.eq("device.cashType",CashType.valueOf(vTypeRestriction.getRestrictionValue())));
//            }else if(vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)){
//                filter.addFilterEntry(FilterFactory.eq("device.awayFlag",AwayFlag.valueOf(vTypeRestriction.getRestrictionValue())));
//            }
//        }
        //是否显示网络不通的设备这个条件放到页面中由用户自行选择，默认不显示
        //TODO 补充
        //获取当前登陆用户能够查看的设备信息列表
//        StringBuffer hql = new StringBuffer();
//        hql.append("from Device device ,VersionTypeAtmTypeRelation va where versionTypeId = ?");
        IPageResult<IDevice> devices =  complexDeviceService.page(start, limit, filter,currentLoginUserId);
        List<LinkedDeviceForm> forms = new ArrayList<LinkedDeviceForm>();
        for(IDevice device : devices.list()){
            LinkedDeviceForm form = new LinkedDeviceForm(device);
            IDeviceSoftVersion deviceSoftVersion = deviceSoftVersionService.get(device.getTerminalId(), selectedVersion.getVersionType().getTypeName());
            if(deviceSoftVersion != null){
                form.setDeviceVersion(deviceSoftVersion.getVersionNo());
                IVersion dv = deviceSoftVersion.getVersion();
                if(dv == null){
                    dv = versionService.make();
                    dv.setVersionNo(deviceSoftVersion.getVersionNo());
                    VersionType temp = new VersionType();
                    temp.setTypeName(selectedVersion.getVersionType().getTypeName());
                    dv.setVersionType(temp);
                }
                if(!selectedVersion.isAfter(dv) && selectedVersion.getVersionNo() != dv.getVersionNo() ){
                    form.setSelectable(false);
                }
            }else{
                form.setSelectable(selectedVersion.isAfter(null));
            }
            //@since 2.0
            //只有可下发的设备才放入form中
            if(form.isSelectable()){
	            IDeviceVersion deviceVersion = dvService.findDeviceVersion(device.getId(), selectedVersion.getId());
	            if(deviceVersion != null){
	                form.setTargetVersion(selectedVersion.getVersionNo());
	                form.setTaskStatus(deviceVersion.getTaskStatus().getText());
	                form.setReason(deviceVersion.getDesc());
	            }
	            //下发状态的判断
            	forms.add(form);
            }
        }
        IPageResult<LinkedDeviceForm> page = new PageResult<LinkedDeviceForm>(forms.size(),forms);
        return page;
    }

    @Override
    public IPageResult<IDevice> pageLinkedDevices(int start, int limit, IFilter filter) {
        List<IDevice> devices = new ArrayList<IDevice>();
        /*if (job != null) {
            List<ITask> tasks = job.getTasks();
            for (ITask task : tasks) {
                devices.add(task.getDevice());
            }
        }*/
        return new PageResult<IDevice>(devices, start, limit);
    }

    @SuppressWarnings("deprecation")
	public IPageResult<ITask> pageTasks(int start, int limit, int jobId, IFilter filter) {
        filter.addFilterEntry(FilterFactory.eq("job.jobId", jobId));
        return taskService.page(start, limit, filter);
    }

    @Override
    public IPageResult<?> pageDownDevices(int start, int limit, IVersion version, IFilter filter) {
        StringBuffer hql = new StringBuffer();
        hql.append("select d.id,d.terminalId,d.ip,d.organization.name,d.devType.name,dsv.version.versionNo,v.versionNo,dv.taskStatus,dv.desc ");
        hql.append("from Device d ");
        hql.append("left join DeviceSoftVersion dsv join Version as v join DeviceVersion dv ");
        hql.append("where d.id = dsv.deviceId and dsv.version.versionType.id = ? and v.id = dv.versionId and d.id = dv.deviceId");
        return dao.page(start, limit, hql.toString(), version.getVersionType().getId());

    }

	@Override
	public void rebootATM(String... ips) {

	}

	@SuppressWarnings("unused")
	private void rebootOne(String ip){
		 String url = MonitorCfg.getHttpUrl(ip) + "/ctr/normalReboot";
	}

	public long getMayBeDownDevice(IVersion version,IOrganization org){
//	    IFilter filter =  new Filter();
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from Device device where device.organization.orgFlag like ? and device.status = ?");
        IVersionType vType = version.getVersionType();
        List<IVersionTypeRestriction> vTypeRestrictions = vType.listVersionTypeRestrictions();
        List<Object> filters = new ArrayList<Object>();
        filters.add("%"+org.getOrgFlag());
        filters.add(Status.OPENING);
        for(IVersionTypeRestriction vTypeRestriction : vTypeRestrictions){
            if(vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.CASH_TYPE)){
//                filter.addFilterEntry(FilterFactory.eq("device.cashType",CashType.valueOf(vTypeRestriction.getRestrictionValue())));
                   hql.append(" and device.cashType = ?");
                   filters.add(CashType.valueOf(vTypeRestriction.getRestrictionValue()));
            }else if(vTypeRestriction.getRestrictionColumn().equals(RestrictionColumn.AWAY_FLAG)){
//                filter.addFilterEntry(FilterFactory.eq("device.awayFlag",AwayFlag.valueOf(vTypeRestriction.getRestrictionValue())));
                hql.append(" and device.awayFlag = ?");
                filters.add(AwayFlag.valueOf(vTypeRestriction.getRestrictionValue()));
            }
        }

        Object object =  dao.findUniqueByHql(hql.toString(),filters.toArray());
        return Long.parseLong(object.toString());
	}

}
