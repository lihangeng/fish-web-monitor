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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.charts.ChartsInfo;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.Status;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.VersionChartsDetailForm;
import com.yihuacomputer.fish.api.version.VersionStaticsStatus;
import com.yihuacomputer.fish.machine.entity.device.Device;
import com.yihuacomputer.fish.version.entity.DeviceVersion;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.version.entity.VersionTypeAtmTypeRelation;

/**
 * //TODO 逻辑要进行更改
 * @author GQ
 * 获取图形信息
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
    
    
	@Override
	public List<ChartsInfo> getVersionDetailsInfo(long versionId,String orgFlag) {
		List<ChartsInfo> list = new ArrayList<ChartsInfo>();
		orgFlag = "%"+orgFlag;
		List<Object> result = getVersionDetailInfo(versionId,orgFlag);
		Object[] resultObj = (Object[])result.get(0);
		ChartsInfo chartsT = new ChartsInfo();
		chartsT.setTitle(VersionStaticsStatus.TOTALDEVICE.getText());
		chartsT.setFlag(VersionStaticsStatus.TOTALDEVICE.getId());
		chartsT.setVersionId(versionId);
		chartsT.setValue(getOpeningDeviceTotal(orgFlag,versionId));
		ChartsInfo chartsS = new ChartsInfo();
		chartsS.setTitle(VersionStaticsStatus.SUCCESSDEVICE.getText());
		if(null==resultObj[1]){
			chartsS.setValue(0);
		}
		else{
			chartsS.setValue((Long)resultObj[1]);
		}
		chartsS.setFlag(VersionStaticsStatus.SUCCESSDEVICE.getId());
		chartsS.setVersionId(versionId);
		ChartsInfo chartsF = new ChartsInfo();
		chartsF.setTitle(VersionStaticsStatus.FAILDEVICE.getText());
		if(null==resultObj[2]){
			chartsF.setValue(0);
		}
		else{
			chartsF.setValue((Long)resultObj[2]);
		}
		chartsF.setFlag(VersionStaticsStatus.FAILDEVICE.getId());
		chartsF.setVersionId(versionId);
		ChartsInfo chartsP = new ChartsInfo();
		chartsP.setTitle(VersionStaticsStatus.PASHDEVICE.getText());
		chartsP.setValue(chartsT.getValue()-chartsS.getValue());
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
	
    /* (non-Javadoc)
     * @see com.yihuacomputer.fish.api.version.IVersionStaticsStautsService#getOpeningDeviceTotal(java.lang.String, long)
     */
    public long getOpeningDeviceTotal(String orgFlag,long versionId) {
    	List<Long> atmTypeList = this.getAtmTypeIdsByVersionId(versionId);
    	if(atmTypeList==null){
    		
    	}
    	List<Object> list = new ArrayList<Object>();
    	list.add(Status.OPENING);
    	list.add(orgFlag);
    	StringBuffer hql= new StringBuffer("select count(t.id) from Device t where t.status = ? and t.organization.orgFlag like ?");
    	StringBuffer atmtype = new StringBuffer(" and t.devType.id in (" );
    	for(int listIndex=0;listIndex<atmTypeList.size();listIndex++){
    		atmtype.append("?");
    		list.add(atmTypeList.get(listIndex));
    		if(listIndex<atmTypeList.size()-1){
    			atmtype.append(",");
    		}
    	}
    	atmtype.append(")");
    	if(list.size()>2){
    		hql.append(atmtype);
    	}
        Object object =  dao.findUniqueByHql(hql.toString(), list.toArray());
        return Long.parseLong(object.toString());
    }
	
	
	private List<Object> getVersionDetailInfo(long versionId,String orgFlag){
		StringBuffer hql = new StringBuffer();
		hql.append("select count(dev.id),").append("sum( case vdv.taskStatus when 'CHECKED' then 1 else 0 end) as success , ").
		append("sum( case vdv.taskStatus when 'NEW' then 1 when 'RUN' then 1 ").
		append("when 'CANCELED' then 1  when 'CANCEL_FAIL' then 1 ").append(" when 'NOTICED' then 1 when 'NOTICED_FAIL' then 1 ").
		append("when 'DOWNLOADED' then 1 when 'DOWNLOADED_FAIL' then 1 ").append("when 'DEPLOYED' then 1 when 'DEPLOYED_WAIT' then 1 ").
		append(" when 'DEPLOYED_FAIL' then 1 when 'OTHER' then 1 ").
		append(" when 'NOTICE_APP_OK' then 1 when 'NOTICE_APP_FAIL' then 1 else 0 end) as fail from ").
		append(Device.class.getSimpleName()).append(" dev").
		append(" , ").append(DeviceVersion.class.getSimpleName()).
		append(" vdv where  vdv.deviceId=dev.id and vdv.versionId=? and dev.organization.orgFlag like ?");
		List<Object> list = new ArrayList<Object>();
		list.add(versionId);
		list.add(orgFlag);
		List<Object> obj = dao.findByHQL(hql.toString(), list.toArray());
		return obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IPageResult<VersionChartsDetailForm> getVersionChartsDetailForm(int start,int limit,IFilter filter) {
		String orgFlagStr ="",  versionTypeName="";
		IPageResult<VersionChartsDetailForm> pageResult = null;
		List<Long> devTypeList = null;
		//机构号
		Object orgFlag = filter.getValue("orgFlag");
		if(null!=orgFlag){
			orgFlagStr = String.valueOf(orgFlag);
		}
		//可支持的设备型号
		Object devType = filter.getValue("devType");
		if(null!=devType){
			devTypeList = (List<Long>)devType;
		}
		Object versionO = filter.getValue("version");
		IVersion version = (Version)versionO;
		//要查询的标识
		Object detailNO =  filter.getValue("flag");
		//版本归属版本类型
		versionTypeName = String.valueOf(filter.getValue("versionTypeName"));
		VersionStaticsStatus status = VersionStaticsStatus.getById(Integer.parseInt(detailNO.toString()));
		if(status.equals(VersionStaticsStatus.TOTALDEVICE)){
			pageResult = getVersionAllDevice(start,limit,orgFlagStr,devTypeList,versionTypeName,version);
		}
		else if(status.equals(VersionStaticsStatus.FAILDEVICE)){
			pageResult = new PageResult<VersionChartsDetailForm>();
			//TODO 计算下发失败的设备明细
		}
		else if(status.equals(VersionStaticsStatus.SUCCESSDEVICE)){
			//TODO 计算下发成功的设备明细
			pageResult = new PageResult<VersionChartsDetailForm>();
		}
		else if(status.equals(VersionStaticsStatus.PASHDEVICE)){
			//TODO 计算可进行下发的设备明细
			pageResult = new PageResult<VersionChartsDetailForm>();
		}
		return pageResult;
	}
	
	/**
	 * 获取当前版本可下发的所有设备信息
	 * @param orgFlag
	 * @param devType
	 * @param versionTypeName
	 * @return
	 */
	private IPageResult<VersionChartsDetailForm> getVersionAllDevice(int start,int limit,String orgFlag,List<Long> devType,String versionTypeName,IVersion version){
		IFilter filter = new Filter();
		filter.llike("organization.orgFlag",orgFlag);
		filter.in("device.devType.id",devType);
		filter.eq("device.status",Status.OPENING);
		//获取所有符合条件的设备
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter, null);
		//版本依赖关系不达标计数器
		int counter = 0;
		List<VersionChartsDetailForm> formList = new ArrayList<VersionChartsDetailForm>();
		
		//获取依赖版本
		String depVersionNo = null;
		IVersion dependVersion = version.getDependVersion();
		if(dependVersion!=null){
			depVersionNo = dependVersion.getVersionNo();
		}
		
		for(IDevice device:pageResult.list()){
			IDeviceSoftVersion dsv = deviceSoftVersionService.get(device.getTerminalId(), versionTypeName);
			if(depVersionNo!=null&&!depVersionNo.equals(dsv==null?"":dsv.getVersionNo())){
				counter++;
				continue;
			}
			VersionChartsDetailForm vcdf = new VersionChartsDetailForm();
			vcdf.setIp(device.getIp().toString());
			vcdf.setOrgName(device.getOrganization().getName());
			vcdf.setTerminalId(device.getTerminalId());
			vcdf.setDevType(device.getDevType().getName());
			if(null!=dsv){
				vcdf.setVersionNo(dsv.getVersionNo());
			}else{
				vcdf.setVersionNo("");
			}
			formList.add(vcdf);
		}
		return new PageResult<VersionChartsDetailForm>(pageResult.getTotal()-counter,formList);
	}
	
	
	
}
