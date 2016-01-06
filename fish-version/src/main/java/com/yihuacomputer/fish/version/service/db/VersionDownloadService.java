package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.Task;
import com.yihuacomputer.fish.version.entity.Version;
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
    private IOrganizationService organizationService;

    @Autowired
    private IVersionService versionService;

    @Autowired
    private IComplexDeviceService complexDeviceService;

    /**
     * 对应增加作业页面可选择的设备列表
     * 还可以下发的设备列表
     * 对应的逻辑是
     * 1.目标版本大于当前版本
     * 2.目标版本的依赖版本小于等于当前版本
     * 
     * 3.软件分类适用的设备型号 @sincie 2.0增加
     */
//	@Override
    public IPageResult<LinkedDeviceForm> pageDevices(int start, int limit, IVersion selectedVersion, IFilter outerFilter,long currentLoginUserId) {

    	return null;
    }

	/* (non-Javadoc)
	 * @see com.yihuacomputer.fish.api.version.IVersionDownloadService#pageCanPushDevices(int, int, com.yihuacomputer.fish.api.version.IVersion, com.yihuacomputer.common.IFilter)
	 */
	public IPageResult<LinkedDeviceForm> pageDownLoadDevices(int start, int limit, IVersion version, IFilter outerFilter) {
		List<LinkedDeviceForm> linkDeviceList = new ArrayList<LinkedDeviceForm>();
		IPageResult<Object> pushResult = this.getCanPushDevicePagesInfo(start, limit, version, outerFilter);
		for(Object record:pushResult.list()){
			Object[] objects = (Object[])record;
			IDevice device=(IDevice)objects[0];
			String versionNo=objects[1]==null?"":String.valueOf(objects[1]);
			LinkedDeviceForm linkDevice = new LinkedDeviceForm(device);
			linkDevice.setDeviceVersion(versionNo);
			linkDevice.setTargetVersion(version.getVersionNo());
			linkDeviceList.add(linkDevice);
		}
        IPageResult<LinkedDeviceForm> page = new PageResult<LinkedDeviceForm>(pushResult.getTotal(),linkDeviceList);
        return page;
	}
	
	
	public IPageResult<Object> getCanPushDevicePagesInfo(int start, int limit, IVersion version, IFilter outerFilter){
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
	    	hqlDevice.append("select device,deviceSoftVersion.versionNo from  ").
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
	    	hqlDevice.append(" and deviceSoftVersion.typeName=version.versionType.typeName and deviceSoftVersion.deviceId=device.id  and version.versionStr>deviceSoftVersion.versionStr ");
	    	
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
        	hqlDevice.append("select device,deviceSoftVersion.versionNo from  ").
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
    		append(" and device.id=deviceSoftVersion.deviceId ");
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
        StringBuffer hql = new StringBuffer();
        hql.append("select count(*) from Device device where device.organization.orgFlag like ? and device.status = ?");
        List<Object> filters = new ArrayList<Object>();
        filters.add(org.getOrgFlag()+"%");
        filters.add(DevStatus.OPEN);
        Object object =  dao.findUniqueByHql(hql.toString(),filters.toArray());
        return Long.parseLong(object.toString());
	}

}
