package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.charts.ChartsInfo;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.VersionChartsDetailForm;
import com.yihuacomputer.fish.api.version.VersionStaticsStatus;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.machine.entity.device.Device;
import com.yihuacomputer.fish.version.entity.DeviceSoftVersion;
import com.yihuacomputer.fish.version.entity.DeviceVersion;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.version.entity.VersionTypeAtmTypeRelation;

/**
 * //TODO 逻辑要进行更改
 * @author GQ
 * 获取图形信息
 */
/**
 * @author GQ
 *
 */
@Service
@Transactional
public class VersionStaticsStatusService implements IVersionStaticsStautsService {

	private Logger logger = LoggerFactory.getLogger(VersionStaticsStatusService.class);
	
    @Autowired
    private IGenericDao dao;

    
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IDeviceSoftVersionService deviceSoftVersionService;
    @Autowired
    private IVersionService versionService;
    
	@Override
	public List<ChartsInfo> getVersionSummaryInfo(long versionId,String orgFlag,int start,int limit) {
		logger.info(String.format("get orgFlag %s Version %d detail charts info", orgFlag,versionId));
		List<ChartsInfo> list = new ArrayList<ChartsInfo>();
		orgFlag = "%"+orgFlag;

		ChartsInfo chartsT = new ChartsInfo();
		chartsT.setTitle(VersionStaticsStatus.TOTALDEVICE.getText());
		chartsT.setFlag(VersionStaticsStatus.TOTALDEVICE.getId());
		chartsT.setVersionId(versionId);
		chartsT.setValue(getMatchConditionDeviceTotal(versionId, orgFlag, start, limit).getTotal());
		ChartsInfo chartsS = new ChartsInfo();
		chartsS.setTitle(VersionStaticsStatus.SUCCESSDEVICE.getText());
		chartsS.setValue(getMatchConditionDeviceSuccess(versionId, orgFlag, start, limit).getTotal());

		chartsS.setFlag(VersionStaticsStatus.SUCCESSDEVICE.getId());
		chartsS.setVersionId(versionId);
		ChartsInfo chartsF = new ChartsInfo();
		chartsF.setTitle(VersionStaticsStatus.FAILDEVICE.getText());
		chartsF.setValue(getMatchConditionDeviceFatal(versionId, orgFlag, start, limit).getTotal());

		chartsF.setFlag(VersionStaticsStatus.FAILDEVICE.getId());
		chartsF.setVersionId(versionId);
		ChartsInfo chartsP = new ChartsInfo();
		chartsP.setTitle(VersionStaticsStatus.PASHDEVICE.getText());

		chartsP.setValue(getMatchConditionDevicePush(versionId, orgFlag, start, limit).getTotal());
		chartsP.setFlag(VersionStaticsStatus.PASHDEVICE.getId());
		chartsP.setVersionId(versionId);
		list.add(chartsT);
		list.add(chartsS);
		list.add(chartsF);
		list.add(chartsP);
		return list;
	}
	
	
	public List<Long> getAtmTypeIdsByVersionId(long versionId){
		StringBuffer hql = new StringBuffer();
		hql.append("select vtatr.atmTypeId from ").append(Version.class.getSimpleName()).append(" as version,")
		.append(VersionTypeAtmTypeRelation.class.getSimpleName()).
		append(" as vtatr where version.versionType.id=vtatr.versionTypeId and version.id=?");
		Object[] a = {versionId};
		List<Object> list =  dao.findByHQL(hql.toString(), a);
		List<Long> atmTypeList = new ArrayList<Long>();
		for(Object obj:list){
			atmTypeList.add((Long)obj);
		}
		return atmTypeList;
	}
	
    /**
     * 根据机构和版本ID获取当前机构机型符合的设备数量
     * @param versionId
     * @param orgFlag
     * @return
     */
    public IPageResult<VersionChartsDetailForm> getMatchConditionDeviceTotal(long versionId,String orgFlag,int start,int limit){
    	StringBuffer hqlsb = new StringBuffer();
    	hqlsb.append("select device from ").append(Device.class.getSimpleName()).append(" device ,").
    	append(Version.class.getSimpleName()).append(" version ,").
    	append(VersionTypeAtmTypeRelation.class.getSimpleName()).
    	append(" as versionatmType where device.devType.id=versionatmType.atmTypeId ").
    	append(" and versionatmType.versionTypeId=version.versionType.id ").
    	append(" and version.id=? and device.organization.orgFlag like ? and device.status=?");
    	Object[] obj = {versionId,orgFlag,Status.OPENING};
    	IVersion version = versionService.getById(versionId);
    	if(version==null){
    		return null;
    	}
    	@SuppressWarnings("unchecked")
		IPageResult<Object> result = (IPageResult<Object>) dao.page(start, limit, hqlsb.toString(), obj);
    	List<Object> list = result.list();
    	List<VersionChartsDetailForm> resultList = new ArrayList<VersionChartsDetailForm>();
    	for(Object objDevice:list){
    		IDevice device = (IDevice)objDevice;
    		VersionChartsDetailForm versionChartsDetailForm = new VersionChartsDetailForm();
    		IDeviceSoftVersion deviceSoftVersion = deviceSoftVersionService.get(device.getTerminalId(), version.getVersionType().getTypeName());
    		versionChartsDetailForm.setTerminalId(device.getTerminalId());
    		versionChartsDetailForm.setDevType(device.getDevType().getName());
    		versionChartsDetailForm.setIp(device.getIp().toString());
    		versionChartsDetailForm.setOrgName(device.getOrganization().getName());
    		versionChartsDetailForm.setVersionId(versionId);
    		versionChartsDetailForm.setVersionNo(deviceSoftVersion.getVersionNo());
    		resultList.add(versionChartsDetailForm);
    	}
    	
    	IPageResult<VersionChartsDetailForm> pageResult = new PageResult<VersionChartsDetailForm>(result.getTotal(),resultList);
    	return pageResult;
    }
    
  
    /**
     * 查询的设备结果进行转换并通过条件进行过滤 
     * @param result 查询的设备结果
     * @param versionId 要比对的版本号
     * @param isMatchVersion 是否进行版本号比对,true 则比对
     * @return
     *  TODO 此处效率较低，需要优化(versionNo无法比对，考虑将versionNo加入数据库；再数据库内部进行比对)
     */
    private IPageResult<VersionChartsDetailForm> convertResult(IPageResult<Object> result){
    	List<VersionChartsDetailForm> formList = new ArrayList<VersionChartsDetailForm>();
    	for(Object object :result.list()){
    		Object[] infos = (Object[])object;
    		IDevice device = (IDevice)infos[0];
    		String currentVersionNo = String.valueOf(infos[1]);
    		VersionChartsDetailForm versionChartsDetailForm = new VersionChartsDetailForm();
    		versionChartsDetailForm.setTerminalId(device.getTerminalId());
    		versionChartsDetailForm.setDevType(device.getDevType().getName());
    		versionChartsDetailForm.setIp(device.getIp().toString());
    		versionChartsDetailForm.setOrgName(device.getOrganization().getName());
    		versionChartsDetailForm.setVersionNo(currentVersionNo);
    		formList.add(versionChartsDetailForm);
    	}
    	return new  PageResult<VersionChartsDetailForm>(result.getTotal(),formList);
    }
    
    

    /**
     * 根据机构和版本ID获取当前机构机型符合并且下发成功的设备数量
     * 此处只能和task进行关联，因为当前查看的版本下发记录只能在此表中查到历史记录
     * @param versionId
     * @param orgFlag
     * @return
     *  TODO 如果设备型号变更可能导致成功数量大于设备总数量
     */
    public IPageResult<VersionChartsDetailForm> getMatchConditionDeviceSuccess(long versionId,String orgFlag,int start,int limit){
    	StringBuffer hql = new StringBuffer();
		//设备下发成功的台数
		hql.append("select device,deviceSoftVersion.versionNo from  ").
		append(DeviceVersion.class.getSimpleName()).append( " deviceVersion , ").
		append(Device.class.getSimpleName()).append(" device ,").
		append(Version.class.getSimpleName()).append(" version ,").
    	append(DeviceSoftVersion.class.getSimpleName()).append(" deviceSoftVersion ,").
		append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType ").
		append(" where  deviceVersion.deviceId=device.id ").
		append(" and deviceVersion.versionId=version.id ").
		append(" and version.id=? and deviceVersion.taskStatus=? and device.status=?").
		append(" and device.devType.id=versionatmType.atmTypeId ").
    	append(" and versionatmType.versionTypeId=version.versionType.id ").
		append(" and device.organization.orgFlag like ? ").
		append(" and version.versionType.typeName=deviceSoftVersion.typeName ").
		append(" and device.terminalId=deviceSoftVersion.terminalId ");
		Object[] obj = {versionId,TaskStatus.CHECKED,Status.OPENING,orgFlag};
    	@SuppressWarnings("unchecked")
		IPageResult<Object> successResult = (IPageResult<Object>) dao.page(start, limit, hql.toString(), obj);
    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(successResult);
    	return pageResult;
    }
//  private Map<String,IVersion> getDeviceVersionOfVersionType(String versionType){
//	Map<String,IVersion> deviceVersionMap = new HashMap<String,IVersion>();
//	StringBuffer hqlSb = new StringBuffer("select deviceSoftVersion.terminalId,version from ");
//	hqlSb.append(Version.class.getSimpleName()).append(" version ,").
//	append(DeviceSoftVersion.class.getSimpleName()).append(" deviceSoftVersion ").
//	append(" where version.versionType.typeName=deviceSoftVersion.typeName ").
//	append(" and version.versionNo =deviceSoftVersion.versionNo and version.versionType.typeName=?");
//	Object [] argObj = {versionType};

//}
    /**
     * 根据机构和版本ID获取当前机构机型符合下发失败的设备数量
     * @param versionId
     * @param orgFlag
     * @return
     * TODO 当前所有设备型号符合条件，并且版本依赖符合条件(如果没有依赖，则要求设备对应的版本低于当前下发的版本)，并且没有下发成功的设备数量
     */
    public IPageResult<VersionChartsDetailForm> getMatchConditionDevicePush(long versionId,String orgFlag,int start,int limit){
    	
    	StringBuffer hqlDevice = new StringBuffer();
    	
    	//由于版本的依赖版本是版本自依赖关系，所有在差巡之前进行判断是否有依赖关系
    	IVersion version = versionService.getById(versionId);
    	if(version==null){
    		logger.error(String.format("version %d is not exist", versionId));
    		return null;
    	}
		//设备可下发成功的台数
    	hqlDevice.append("select device,deviceSoftVersion.versionNo from  ").
		append(Device.class.getSimpleName()).append(" device ,").
		append(Version.class.getSimpleName()).append(" version ,").
		append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType ,").
		append(DeviceSoftVersion.class.getSimpleName()).append(" deviceSoftVersion ").
		append(" where version.id=?  and device.status=?").
		append(" and device.devType.id=versionatmType.atmTypeId ").
    	append(" and versionatmType.versionTypeId=version.versionType.id ").
		
		append(" and version.versionType.typeName=deviceSoftVersion.typeName ").
		append(" and device.terminalId=deviceSoftVersion.terminalId ");
    	if(null==version.getDependVersion()){
    		hqlDevice.append(" and version.versionNo>deviceSoftVersion.versionNo ");
    	}
    	else{
    		hqlDevice.append(" and version.dependVersion.versionNo=deviceSoftVersion.versionNo");
    	}
    	hqlDevice.append(" and device.organization.orgFlag like ? and device.id not in").
		//下发成功的设备
    	append("(select device1.id from  ").
		append(DeviceVersion.class.getSimpleName()).append( " deviceVersion1 , ").
		append(Device.class.getSimpleName()).append(" device1 ,").
		append(Version.class.getSimpleName()).append(" version1 ,").
		append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType1 ").
		append(" where  deviceVersion1.deviceId=device1.id ").
		append(" and deviceVersion1.versionId=version1.id and ").
		append(" version1.id=? and deviceVersion1.taskStatus=? and device1.status=?").
		append(" and device1.devType.id=versionatmType1.atmTypeId ").
    	append(" and versionatmType1.versionTypeId=version1.versionType.id ").
		append(" and device1.organization.orgFlag like ?) ");
		Object[] objDevice = {versionId,Status.OPENING,orgFlag,versionId,TaskStatus.CHECKED,Status.OPENING,orgFlag};
		@SuppressWarnings("unchecked")
		IPageResult<Object> pushResult = (IPageResult<Object>) dao.page(start, limit, hqlDevice.toString(), objDevice);
    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(pushResult);
//		List<Object> totalList =  dao.findByHQL(hqlDevice.toString(), objDevice);
//    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(start,limit,totalList,versionId,true);
    	return pageResult;
    }
    /**
     * 根据机构和版本ID获取当前机构机型符合并且设备版本的依赖关系符合条件的设备数量
     * @param versionId
     * @param orgFlag
     * @return
     */
    public IPageResult<VersionChartsDetailForm> getMatchConditionDeviceFatal(long versionId,String orgFlag,int start,int limit){
    	StringBuffer hql = new StringBuffer();
		//设备下发失败的台数
		hql.append("select device,deviceSoftVersion.versionNo  from ").
		append(DeviceVersion.class.getSimpleName()).append( " deviceVersion , ").
		append(Device.class.getSimpleName()).append(" device, ").
		append(Version.class.getSimpleName()).append(" version ,").
    	append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType ,").
		append(DeviceSoftVersion.class.getSimpleName()).append(" deviceSoftVersion ").
		append(" where  deviceVersion.deviceId=device.id and deviceVersion.versionId=version.id and ").
		append("deviceVersion.versionId=?  and device.status=? and device.organization.orgFlag like ? ").
		append(" and deviceVersion.taskStatus in('DEPLOYED_FAIL','NOTICED_FAIL','DOWNLOADED_FAIL','NOTICE_APP_FAIL')").
		append(" and versionatmType.versionTypeId=version.versionType.id and device.devType.id=versionatmType.atmTypeId ").
		append(" and version.versionType.typeName=deviceSoftVersion.typeName ").
		append(" and device.terminalId=deviceSoftVersion.terminalId ");
		Object[] obj = {versionId,Status.OPENING,orgFlag};
		
		

    	@SuppressWarnings("unchecked")
		IPageResult<Object> fatalResult = (IPageResult<Object>) dao.page(start, limit, hql.toString(), obj);
    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(fatalResult);
		
//    	List<Object> fatalList = dao.findByHQL(hql.toString(), obj);
//    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(start,limit,fatalList,versionId,true);
    	return pageResult;
    }
	

	@Override
	public IPageResult<VersionChartsDetailForm> getVersionChartsDetailForm(int start,int limit,IFilter filter) {
		String orgFlagStr ="";
		IPageResult<VersionChartsDetailForm> pageResult = null;
		//机构号
		Object orgFlag = filter.getValue("orgFlag");
		if(null!=orgFlag){
			orgFlagStr = String.valueOf(orgFlag);
		}

		Object versionO = filter.getValue("version");
		IVersion version = (Version)versionO;
		//要查询的标识
		Object detailNO =  filter.getValue("flag");
		VersionStaticsStatus status = VersionStaticsStatus.getById(Integer.parseInt(detailNO.toString()));
		if(status.equals(VersionStaticsStatus.TOTALDEVICE)){
			pageResult = getMatchConditionDeviceTotal(version.getId(),orgFlagStr,start,limit);
		}
		else if(status.equals(VersionStaticsStatus.FAILDEVICE)){
			pageResult = getMatchConditionDeviceFatal(version.getId(),orgFlagStr,start,limit);
		}
		else if(status.equals(VersionStaticsStatus.SUCCESSDEVICE)){
			pageResult = getMatchConditionDeviceSuccess(version.getId(),orgFlagStr,start,limit);
		}
		else if(status.equals(VersionStaticsStatus.PASHDEVICE)){
			pageResult = getMatchConditionDevicePush(version.getId(),orgFlagStr,start,limit);
		}
		return pageResult;
	}
	
	
	
	
	
}
