package com.yihuacomputer.fish.version.service.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    
    /**
     * 根据版本类型获取所有设备上对应版本类型的版本
     * @param versionType 版本类型
     * @return 所有设备对应的版本Map集合
     */
    private Map<String,IVersion> getDeviceVersionOfVersionType(String versionType){
    	Map<String,IVersion> deviceVersionMap = new HashMap<String,IVersion>();
    	StringBuffer hqlSb = new StringBuffer("select deviceSoftVersion.terminalId,version from ");
    	hqlSb.append(Version.class.getSimpleName()).append(" version ,").
    	append(DeviceSoftVersion.class.getSimpleName()).append(" deviceSoftVersion ").
    	append(" where version.versionType.typeName=deviceSoftVersion.typeName ").
    	append(" and version.versionNo =deviceSoftVersion.versionNo and version.versionType.typeName=?");
    	Object [] argObj = {versionType};
    	List<Object> list = dao.findByHQL(hqlSb.toString(), argObj);
    	for(Object obj:list){
    		Object[] result = (Object[])obj;
    		deviceVersionMap.put(String.valueOf(result[0]), (Version)result[1]);
    	}
    	return deviceVersionMap;
    }
    
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
    private IPageResult<VersionChartsDetailForm> getMatchConditionDeviceTotal(long versionId,String orgFlag,int start,int limit){
    	StringBuffer hqlsb = new StringBuffer();
    	hqlsb.append("select device from ").append(Device.class.getSimpleName()).append(" device ,").
    	append(Version.class.getSimpleName()).append(" version ,").
    	append(VersionTypeAtmTypeRelation.class.getSimpleName()).
    	append(" as versionatmType where device.devType.id=versionatmType.atmTypeId ").
    	append(" and versionatmType.versionTypeId=version.versionType.id ").
    	append(" and version.id=? and device.organization.orgFlag like ? and device.status=?");
    	Object[] obj = {versionId,orgFlag,Status.OPENING};
		List<Object> totalList =  dao.findByHQL(hqlsb.toString(), obj);
    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(start,limit,totalList,versionId,false);
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
    private IPageResult<VersionChartsDetailForm> convertResult(int start, int limit,List<Object> result,long versionId,boolean isMatchVersion){
    	IVersion version = versionService.getById(versionId);
    	IPageResult<VersionChartsDetailForm> pageResult = null;
    	List<VersionChartsDetailForm> formList = new ArrayList<VersionChartsDetailForm>();
    	if(null==version){
    		logger.warn(String.format("version %d don't exist,refresh please!", versionId));
    		return pageResult;
    	}
//    	if(limit==0){
//    		return new  PageResult<VersionChartsDetailForm>(formList,start,limit);
//    	}
    	for(Object object :result){
//    		Object[] objects = (Object[])object;
    		IDevice device = (IDevice)object;
    		IDeviceSoftVersion deviceSoftVersion = deviceSoftVersionService.get(device.getTerminalId(), version.getVersionType().getTypeName());
    		VersionChartsDetailForm versionChartsDetailForm = new VersionChartsDetailForm();
    		versionChartsDetailForm.setTerminalId(device.getTerminalId());
    		versionChartsDetailForm.setDevType(device.getDevType().getName());
    		versionChartsDetailForm.setIp(device.getIp().toString());
    		versionChartsDetailForm.setOrgName(device.getOrganization().getName());
    		versionChartsDetailForm.setVersionId(versionId);
    		versionChartsDetailForm.setVersionNo(deviceSoftVersion==null?"":deviceSoftVersion.getVersionNo());
    		if(isMatchVersion){
    			if(deviceSoftVersion==null){
    				formList.add(versionChartsDetailForm);
    				continue;
    			}
    			IVersion deviceNowVersion = versionService.findVersion(deviceSoftVersion.getTypeName(), deviceSoftVersion.getVersionNo());
    			if(version.isAfter(deviceNowVersion)){
    				formList.add(versionChartsDetailForm);
    				continue;
    			}
    		}
    		else{
    			formList.add(versionChartsDetailForm);
    		}
    	}
    	return new  PageResult<VersionChartsDetailForm>(formList,start,limit);
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
		hql.append("select device from  ").
		append(DeviceVersion.class.getSimpleName()).append( " deviceVersion , ").
		append(Device.class.getSimpleName()).append(" device ,").
		append(Version.class.getSimpleName()).append(" version ,").
		append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType ").
		append(" where  deviceVersion.deviceId=device.id ").
		append(" and deviceVersion.versionId=version.id and ").
		append(" version.id=? and deviceVersion.taskStatus=? and device.status=?").
		append(" and device.devType.id=versionatmType.atmTypeId ").
    	append(" and versionatmType.versionTypeId=version.versionType.id ").
		append(" and device.organization.orgFlag like ? ");
		Object[] obj = {versionId,TaskStatus.CHECKED,Status.OPENING,orgFlag};
    	List<Object> successList = dao.findByHQL(hql.toString(), obj);
    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(start,limit,successList,versionId,false);
    	return pageResult;
    }
    /**
     * 根据机构和版本ID获取当前机构机型符合下发失败的设备数量
     * @param versionId
     * @param orgFlag
     * @return
     * TODO 当前所有设备型号符合条件，并且版本依赖符合条件(如果没有依赖，则要求设备对应的版本低于当前下发的版本)，并且没有下发成功的设备数量
     */
    private IPageResult<VersionChartsDetailForm> getMatchConditionDevicePush(long versionId,String orgFlag,int start,int limit){
    	StringBuffer hqlsb = new StringBuffer();
    	hqlsb.append("select device from ").append(Device.class.getSimpleName()).append(" device ,").
    	append(Version.class.getSimpleName()).append(" version ,").
    	append(VersionTypeAtmTypeRelation.class.getSimpleName()).
    	append(" versionatmType where device.devType.id=versionatmType.atmTypeId ").
    	append(" and versionatmType.versionTypeId=version.versionType.id ").
    	append(" and version.id=? and device.organization.orgFlag like ? and device.status=?");
    
    	Object[] obj = {versionId,orgFlag,Status.OPENING};
		List<Object> totalList =  dao.findByHQL(hqlsb.toString(), obj);
    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(start,limit,totalList,versionId,true);
    	return pageResult;
    }
    /**
     * 根据机构和版本ID获取当前机构机型符合并且设备版本的依赖关系符合条件的设备数量
     * @param versionId
     * @param orgFlag
     * @return
     */
    private IPageResult<VersionChartsDetailForm> getMatchConditionDeviceFatal(long versionId,String orgFlag,int start,int limit){
    	StringBuffer hql = new StringBuffer();
		//设备下发失败的台数
		hql.append("select device  from ").
		append(DeviceVersion.class.getSimpleName()).append( " deviceVersion , ").
		append(Device.class.getSimpleName()).append(" device, ").
		append(Version.class.getSimpleName()).append(" version ,").
    	append(VersionTypeAtmTypeRelation.class.getSimpleName()).append(" versionatmType ").
		append(" where  deviceVersion.deviceId=device.id and deviceVersion.versionId=version.id and ").
		append("deviceVersion.versionId=?  and device.status=? and device.organization.orgFlag like ? ").
		append(" and deviceVersion.taskStatus in('DEPLOYED_FAIL','NOTICED_FAIL','DOWNLOADED_FAIL','NOTICE_APP_FAIL')").
		append(" and versionatmType.versionTypeId=version.versionType.id and device.devType.id=versionatmType.atmTypeId ");
		Object[] obj = {versionId,Status.OPENING,orgFlag};
    	List<Object> fatalList = dao.findByHQL(hql.toString(), obj);
    	IPageResult<VersionChartsDetailForm> pageResult= convertResult(start,limit,fatalList,versionId,true);
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
