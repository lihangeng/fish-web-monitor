/**
 *
 */
package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import antlr.Version;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IDeviceVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionDownloadService;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.LinkedDeviceForm;
import com.yihuacomputer.fish.api.version.job.IJob;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.DeviceVersion;
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

    /**
     * @param start
     * @param limit
     * @param jobId
     * @param filter
     * @return
     */
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

	   //-------------------------增加
    @Override
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
		   linkedDeviceForm.setTargetVersion(version.getVersionNo());
		   linkedDeviceForm.setDeviceVersion(deviceSoftVersion.getVersionNo());
		   formPage.add(linkedDeviceForm);
	   }
	   return new PageResult<LinkedDeviceForm>(resultObject.getTotal(),formPage);
   }

    @Override
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
			append(DeviceVersion.class.getSimpleName()).append( " deviceVersion , ").
			append(Device.class.getSimpleName()).append(" device1 ,");

			if(version.getVersionType().isDisplay()){
				hqlDevice.append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType1 ,");
			}
	    	hqlDevice.append(Version.class.getSimpleName()).append(" version1 ").
			append(" where  deviceVersion.deviceId=device1.id ").
			append(" and deviceVersion.versionId=version1.id and ").
			append(" version1.id=? and deviceVersion.taskStatus in('NEW','RUN','NOTICED','NOTICE_APP_OK','DOWNLOADED','DEPLOYED','DEPLOYED_WAIT') and device1.status=?");
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
			append(DeviceVersion.class.getSimpleName()).append( " deviceVersion , ").
    		append(Device.class.getSimpleName()).append(" device1 ,").
    		append(Version.class.getSimpleName()).append(" version1 ,").
    		append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType1 ").
    		append(" where  deviceVersion.deviceId=device1.id ").
    		append(" and deviceVersion.versionId=version1.id and ").
    		append(" version1.id=? and deviceVersion.taskStatus in('NEW','RUN','NOTICED','NOTICE_APP_OK','DOWNLOADED','DEPLOYED','DEPLOYED_WAIT') and device1.status=?").
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
   	public boolean selectAllDeviceToTask(IJob job,IFilter outerFilter){
		List<Object> argList=new ArrayList<Object>();
		Object orgFlag = outerFilter.getValue("orgFlag");
		String orgFlagStr =orgFlag+ "%";
		Object terminalId = outerFilter.getValue("terminalId");
		Object atmTypeId = outerFilter.getValue("atmTypeId");
		Object ip = outerFilter.getValue("ip");
		Object eagerRestart = outerFilter.getValue("eagerRestart");
		Object deviceIds = outerFilter.getValue("deviceIds");
		//如果有没有依赖版本则版本号要小于当前要下发的版本号;并且下发的设备如果存在下发的任务，任务不可以为没有失败的任务
//		StringBuffer hqlDevice = new StringBuffer();
		
		StringBuffer sb = new StringBuffer();
     	sb.append("INSERT INTO VER_TASK(DEVICE_ID,VERSION_BEFORE_UPDATE,VERSION_ID,EAGER_RESTART,JOB_ID,TASK_STATUS,TASK_TYPE,EXCEPT_VERSION) ");
     	sb.append("select device0_.ID ,concat(concat(devicesoft3_.TYPE_NAME,'_'),devicesoft3_.VERSION_NO),?,?,?,'NEW','MANUAL',version1_.VERSION_NO ");
     	sb.append("from DEV_INFO device0_ ,VER_VERSION version1_ , ");
     	if(job.getVersion().getVersionType().isDisplay()){
     		sb.append("VER_VERSIONTYPE_ATMTYPE versiontyp2_ ,");
     	}
     	sb.append("VER_DEVICE_SOFT_VERSION devicesoft3_,VER_VERSION_TYPE versiontyp4_ ,SM_ORG organizati5_ ");
     	sb.append("where version1_.VERSION_TYPE_ID=versiontyp4_.ID  ");
     	if(job.getVersion().getVersionType().isDisplay()){
     		sb.append(" and device0_.DEV_TYPE_ID=versiontyp2_.ATM_TYPE_ID and versiontyp2_.VERSION_TYPE_ID=version1_.VERSION_TYPE_ID ");
     	}
     	sb.append(" and devicesoft3_.TYPE_NAME=versiontyp4_.TYPE_NAME ");
     	sb.append("and devicesoft3_.TERMINAL_ID=device0_.TERMINAL_ID  and version1_.VERSION_STR>devicesoft3_.VERSION_STR  ");
     	sb.append("and device0_.ORG_ID=organizati5_.ID  and version1_.ID=?  and device0_.STATUS=? ");

     	sb.append("and ( organizati5_.ORG_FLAG like ?) and ( device0_.ID not in  ( ");
     	sb.append("select device7_.ID   from VER_DEVICE_VERSION task6_ ,  DEV_INFO device7_ , VER_VERSION version9_ , SM_ORG organizati10_ ");
     	if(job.getVersion().getVersionType().isDisplay()){
     		sb.append(",VER_VERSIONTYPE_ATMTYPE versiontyp8_  ");
     	}
     	sb.append("where device7_.ORG_ID=organizati10_.ID and task6_.DEVICE_ID=device7_.ID and task6_.VERSION_ID=version9_.ID ");
     	if(job.getVersion().getVersionType().isDisplay()){
     		sb.append("and device7_.DEV_TYPE_ID=versiontyp8_.ATM_TYPE_ID  and versiontyp8_.VERSION_TYPE_ID=version9_.VERSION_TYPE_ID ");
     	}
     	sb.append("and version9_.ID=? and (task6_.TASK_STATUS in ('NEW' , 'RUN' , 'NOTICED' ,'NOTICE_APP_OK' , 'DOWNLOADED' , 'DEPLOYED' , 'DEPLOYED_WAIT','DOWN_BEFORE_WAIT','DOWNLOADING') ");
     	sb.append(") and device7_.STATUS=? and (organizati10_.ORG_FLAG like ?)))");
     	
     	if(terminalId!=null){
     		sb.append(" and device0_.TERMINAL_ID like ? ");
    		argList.add("%"+String.valueOf(terminalId)+"%");
    	}
    	if(atmTypeId!=null){
    		sb.append(" and versiontyp2_.ATM_TYPE_ID =? ");
    		argList.add(Long.parseLong(String.valueOf(atmTypeId)));
    	}
    	if(ip!=null){
    		sb.append(" and device0_.IP =? ");
    		argList.add(new IP(String.valueOf(ip)));
    	}
		StringBuffer deviceSb = new StringBuffer();
    	if(deviceIds!=null){
    		sb.append("and device0_.ID in ");
    		@SuppressWarnings("unchecked")
			List<Long> deviceIdList = (List<Long>)deviceIds;
    		deviceSb.append(" ( ");
    		for(int index=0;index<deviceIdList.size();index++){
    			deviceSb.append(deviceIdList.get(index));
    			if(index!=deviceIdList.size()-1)
    				deviceSb.append(",");
    		}
    		deviceSb.append(")");
    		sb.append(deviceSb);
    	}

    	IVersion version = job.getVersion();
    	StringBuffer updateDvSb = new StringBuffer();
    	updateDvSb.append("update VER_DEVICE_VERSION  set TASK_STATUS='NEW',LAST_UPDATED_TIME=?,REMARK='' where VERSION_ID = ?");
    	updateDvSb.append(" and TASK_STATUS not in('NEW' , 'RUN' , 'NOTICED' ,'NOTICE_APP_OK' , 'DOWNLOADED' , 'DEPLOYED' , 'DEPLOYED_WAIT','DOWN_BEFORE_WAIT','DOWNLOADING') ");
    	if(!deviceSb.toString().isEmpty()){
    		updateDvSb.append(" and DEVICE_ID in ").append(deviceSb);
    	}
    	
     	Query query = dao.getSQLQuery(sb.toString());
     	query.setLong(0,version.getId());
     	query.setCharacter(1, ((Boolean)eagerRestart?'1':'0'));
     	query.setLong(2, job.getJobId());
     	query.setLong(3, version.getId());
     	query.setInteger(4, DevStatus.OPEN.ordinal());
     	query.setString(5, orgFlagStr);
     	query.setLong(6, version.getId());
     	query.setInteger(7, DevStatus.OPEN.ordinal());
     	query.setString(8, orgFlagStr);
     	int insertCount = query.executeUpdate();
     	//DEVICE_VERSION状态重置
     	Query queryDv = dao.getSQLQuery(updateDvSb.toString());
     	queryDv.setDate(0,new Date());
     	queryDv.setLong(1, version.getId());
     	queryDv.executeUpdate();
     	return insertCount!=0;
   	}

   	
}
