package com.yihuacomputer.fish.web.machine.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.device.RegStatus;
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.api.monitor.business.IDeviceRegister;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.VersionCatalog;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.monitor.entity.business.RunInfo;
import com.yihuacomputer.fish.monitor.entity.report.DeviceReport;
import com.yihuacomputer.fish.monitor.entity.report.StatusReport;
import com.yihuacomputer.fish.version.entity.Version;
import com.yihuacomputer.fish.web.machine.form.BoxAndRetainCardForm;
import com.yihuacomputer.fish.web.machine.form.DeviceDetailForm;
import com.yihuacomputer.fish.web.machine.form.DeviceDetailVersionForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.system.form.PersonForm;
import com.yihuacomputer.fish.web.version.form.VersionForm;

/**
 * 单机模式设备基础信息处理
 * @author GQ
 *
 */
@Controller
@RequestMapping("/machine/devicedetail")
public class DeviceDetailController
{
    private Logger logger = LoggerFactory.getLogger(DeviceDetailController.class);

	@Autowired
	private MessageSource messageSourceEnum;
	
    @Autowired
    private IDeviceService deviceService;

    @Autowired
    private IDevicePersonRelation devicePersonRelation;

    @Autowired
    private  IXfsService xfsService;

    @Autowired
    private ITaskService taskService;
    @Autowired
    private IRegistService registService;
    
    @Autowired
    private IVersionService versionService;  

    @Autowired
    private IVersionTypeService versionTypeService;  
    @Autowired
    private IRegistService registerService;  
    
    @Autowired
    private IDeviceSoftVersionService deviceSoftVersionService; 
    
    @Autowired
    private  IDeviceBoxInfoService devcieBoxInfoService;
    
    /**
     * 获取所有可升级版本列表
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value = "updateVersion", method = RequestMethod.GET)
    @ResponseBody
    public ModelMap getUpdateVersion( HttpServletRequest httpRequest, WebRequest request) {
    	String terminalId = request.getParameter("termianlId");
    	String versionNo = request.getParameter("veisionNo");
    	versionNo = versionNo==null?"0":versionNo;
    	List<VersionForm> appReleaseList=new ArrayList<VersionForm>();
    	appReleaseList=getVersionForm(terminalId,VersionCatalog.APP,versionNo);
    	ModelMap result = new ModelMap();
    	result.addAttribute(FishConstant.DATA, appReleaseList);
        result.addAttribute(FishConstant.SUCCESS, true);
    	return result;
    }
    
    /**
     * 查询设备基础信息
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/basicInfo",method = RequestMethod.GET)
    @ResponseBody
	public ModelMap searchBasicInfo(HttpServletRequest httpRequest, WebRequest request){
    	ModelMap result = new ModelMap();
    	String terminalId = request.getParameter("termianlId");
    	logger.info(String.format("search device %s basic information",terminalId));
    	IDevice device = deviceService.get(terminalId);
        DeviceForm deviceForm = toFrom(device);
        IDeviceRegister deviceRegister = registerService.load(terminalId);
        RegStatus reg = RegStatus.UNKNOWN;
        if(deviceRegister!=null&&deviceRegister.getRegStatus()!=null){
        	reg = deviceRegister.getRegStatus();
        }
        deviceForm.setRegisterStatus(getEnumI18n(reg.getText()));
        result.addAttribute(FishConstant.DATA,deviceForm);
        result.addAttribute(FishConstant.SUCCESS, true);
    	return result;
    }
    
    /**
     * 查看设备硬件状态
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/statusInfo",method = RequestMethod.GET)
    @ResponseBody
   	public ModelMap searchStatusInfo(HttpServletRequest httpRequest, WebRequest request){
    	ModelMap result = new ModelMap();
    	DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
        String terminalId = request.getParameter("termianlId");
    	logger.info(String.format("search device %s status information",terminalId));
        IXfsStatus xfsStatus = xfsService.loadXfsStatus(terminalId);
        if(xfsStatus != null){
        	StatusReport statusReport = new StatusReport();
            DeviceReport deviceReport = new DeviceReport();
            IRunInfo runInfo = new RunInfo();
    		runInfo.setRunStatus(xfsStatus.getRunStatus());
    		deviceReport.setXfsStatus(xfsStatus);
        	deviceReport.setRunInfo(runInfo);
        	statusReport.setStatusReport(deviceReport, messageSourceEnum);
            deviceDetailForm.setStatusReport(statusReport);
            result.addAttribute(FishConstant.DATA,deviceDetailForm);
            result.addAttribute(FishConstant.SUCCESS, true);
            return result;
        }
        result.addAttribute(FishConstant.SUCCESS, false);
        return result;
    }
    
    /**
     *查询设备人员信息 
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/personInfo",method = RequestMethod.GET)
    @ResponseBody
   	public ModelMap searchPersonInfo(HttpServletRequest httpRequest, WebRequest request){
    	ModelMap result = new ModelMap();
    	String terminalId = request.getParameter("termianlId");
    	logger.info(String.format("search device %s person information",terminalId));
    	List<IPerson> personList = devicePersonRelation.listPersonByDevice(terminalId);
    	if(personList.size() != 0){
    		List<PersonForm> data = PersonForm.convert(personList);
        	result.addAttribute(FishConstant.SUCCESS, true);
        	result.addAttribute(FishConstant.DATA, data);
    	}else{
    		result.addAttribute(FishConstant.SUCCESS, false);
    	}
    	return result;
    }
    
    /**
     * 查询设备版本信息
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/versionInfo",method = RequestMethod.GET)
    @ResponseBody
   	public ModelMap searchVersionInfo(HttpServletRequest httpRequest, WebRequest request){
    	ModelMap result = new ModelMap();
    	String terminalId = request.getParameter("termianlId");
    	logger.info(String.format("search device %s version information",terminalId));
    	DeviceDetailVersionForm data = new DeviceDetailVersionForm();
    	//获取设备最新版本
    	IDeviceRegister deviceRegister = registService.load(terminalId);
    	IDeviceSoftVersion deviceSoftVersion=deviceSoftVersionService.findVersionByCatlog(terminalId, VersionCatalog.APP);
    	if(deviceSoftVersion != null && deviceRegister != null){
    		Date lastUpdateTime = deviceSoftVersion.getLastUpdatedTime();
    		String versionNo = deviceRegister.getAtmcVersion()==null?"0":deviceRegister.getAtmcVersion();
        	String versionStr = versionService.getVersionStrByVersionNo(versionNo);
        	IFilter filter = new Filter();
        	filter.gt("versionStr", versionStr);
        	List<VersionForm> versionFromList = getVersionForm(terminalId, VersionCatalog.APP, versionNo);
        	if(versionFromList.size()>0){
        		data.setMaxVersion(versionFromList.get(0));
        	}
        	data.setCurrentVersion(versionNo);
        	data.setLastUpdateTime(DateUtils.get(lastUpdateTime, DateUtils.STANDARD_TIMESTAMP));
        	result.addAttribute(FishConstant.DATA, data);
            result.addAttribute(FishConstant.SUCCESS, true);
            return result;
    	}
    	result.addAttribute(FishConstant.SUCCESS, false);
    	return result;
    }
    
    /**
     * 查询设备钞箱和吞卡信息
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/boxAndRetainInfo",method = RequestMethod.GET)
    @ResponseBody
   	public ModelMap searchBoxAndRetainInfo(HttpServletRequest httpRequest, WebRequest request){
    	ModelMap result = new ModelMap();
    	BoxAndRetainCardForm boxAndRetainCardForm = new BoxAndRetainCardForm();
    	String terminalId = request.getParameter("termianlId");
    	logger.info(String.format("search device %s BoxAndRetain information",terminalId));
    	IDevice device = deviceService.get(terminalId);
    	IDeviceBoxInfo devcieBoxInfo = devcieBoxInfoService.findByDeviceId(device.getId());
    	if(devcieBoxInfo != null){
    		boxAndRetainCardForm.setMaxAlarm(String.valueOf(devcieBoxInfo.getMaxAlarm()));
            boxAndRetainCardForm.setMinAlarm(String.valueOf(devcieBoxInfo.getMinAlarm()));
    	}
    	else{
    		boxAndRetainCardForm.setMaxAlarm("未知");
    		boxAndRetainCardForm.setMinAlarm("未知");
    	}
    	StatusReport statusReport = new StatusReport();
    	DeviceReport deviceReport = new DeviceReport();
    	IXfsStatus xfsStatus = xfsService.loadXfsStatus(terminalId);
    	if(xfsStatus != null){
    		deviceReport.setXfsStatus(xfsStatus);
    		statusReport.setStatusReport(deviceReport, messageSourceEnum);
            boxAndRetainCardForm.setBoxCurrentCount("-1".equals(statusReport.getBoxCurrentCount())?"0":statusReport.getBoxCurrentCount());
            boxAndRetainCardForm.setBoxInitCount("-1".equals(statusReport.getBoxInitCount())?"0":statusReport.getBoxInitCount());
            boxAndRetainCardForm.setRetainCardCount("-1".equals(statusReport.getRetainCardCount())?"0":statusReport.getRetainCardCount());
//            boxAndRetainCardForm.setRegisterStatus(statusReport.getRegisterStatus());
            
    	}
    	result.addAttribute(FishConstant.DATA, boxAndRetainCardForm);
        result.addAttribute(FishConstant.SUCCESS, true);
    	return result;
    }
    
    /**
     * 判断用户是否有权限查看设备信息及设备是否存在
     * @return
     */
    @RequestMapping(value="/querydevice",method = RequestMethod.GET)
    @ResponseBody
    public ModelMap isExistAndCharge(HttpServletRequest httpRequest, WebRequest request,@RequestParam String terminalId ){
    	IDevice device = deviceService.get(terminalId);
        ModelMap result = new ModelMap();
        if(null==device){
        	result.addAttribute(FishConstant.SUCCESS, false);
        	result.addAttribute(FishConstant.ERROR_MSG, getEnumI18n("device.detail.query.notExist",terminalId));
        	return result;
        }
        String deviceOrgFlag = device.getOrganization().getOrgFlag();
        HttpSession session = httpRequest.getSession();
        UserSession userSession = (UserSession) session.getAttribute("SESSION_USER");
        String userOrgFlag = userSession.getOrgFlag();
        if(!deviceOrgFlag.startsWith(userOrgFlag)){
        	result.addAttribute(FishConstant.ERROR_MSG, getEnumI18n("device.detail.query.notPermission",terminalId));
        	result.addAttribute(FishConstant.SUCCESS, false);
        	return result;
        }
        return result.addAttribute(FishConstant.SUCCESS, true);
    }
    
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
    private String getEnumI18n(String code,String ...arg){
    	if(null==code){
    		return "";
    	}
    	return messageSourceEnum.getMessage(code, arg, FishCfg.locale);
    }
    
    private List<VersionForm> getVersionForm(String terminalId,VersionCatalog versionCatalog,String versionNo) {
        List<VersionForm> appReleaseList = new ArrayList<VersionForm>();
        IDeviceSoftVersion deviceSoftVersion=deviceSoftVersionService.findVersionByCatlog(terminalId, versionCatalog);
        if(deviceSoftVersion !=null){
        	 String typeName=deviceSoftVersion.getTypeName();
             IVersion currentVersion=versionService.findVersion(typeName, versionNo);
             if(currentVersion==null){
             	currentVersion = new Version();
             	currentVersion.setVersionNo(versionNo);
             	currentVersion.setVersionType(versionTypeService.getByName(typeName));
             	currentVersion.setVersionStr(versionService.getVersionStrByVersionNo(versionNo));
             }
             long typeId=currentVersion.getVersionType().getId();
             IFilter filter=new Filter();
 	         filter.descOrder("versionStr");
 	         filter.eq("versionType.id", typeId);
 	         List<IVersion> maybeVersions=versionService.list(filter);
 	         List<IVersion> versions= versionService.getUpdateVersion(maybeVersions,currentVersion);
 	         appReleaseList=toVersionForm(versions);
        }
        return appReleaseList;
    }
    
    private List<VersionForm> toVersionForm(List<IVersion> versions) {
		List<VersionForm> forms = new ArrayList<VersionForm>();
		for (IVersion v : versions) {
			VersionForm form = new VersionForm(v);
			if (v.getCreateUser() != null) {
				form.setUserName(v.getCreateUser().getName());
			}
			forms.add(form);
		}
		return forms;
	}

	/**
	 * 将接口数据保存至本地
	 *
	 * @param device
	 *            接口
	 * @param isDate
	 *            是否需要转换日期
	 */
	public DeviceForm toFrom(IDevice device) {
		DeviceForm deviceForm = new DeviceForm();
		deviceForm.setAddress(device.getAddress());
		deviceForm.setCashboxLimit(device.getCashboxLimit());
		deviceForm.setAwayFlag(device.getAwayFlag() == null ? null : String.valueOf(device.getAwayFlag().getId()));
		deviceForm.setAwayFlagName(device.getAwayFlag() == null ? null : getEnumI18n(device.getAwayFlag().getText()));
		deviceForm.setSetupType(device.getSetupType() == null ? null : String.valueOf(device.getSetupType().getId()));
		deviceForm.setSetupTypeName(device.getSetupType() == null ? null : getEnumI18n(device.getSetupType().getText()));
		deviceForm.setWorkType(device.getWorkType() == null ? null : String.valueOf(device.getWorkType().getId()));
		deviceForm.setWorkTypeName(device.getWorkType() == null ? getEnumI18n(WorkType.FROM_OPERATING.getText()) :getEnumI18n(device.getWorkType().getText()));
		deviceForm.setVirtual(device.getVirtual());
		deviceForm.setSerial(device.getSerial());
		deviceForm.setNetType(device.getNetType() == null ? String.valueOf(NetType.CABLE.getId()) : String.valueOf(device.getNetType().getId()));
		deviceForm.setNetTypeName(device.getNetType() == null ? getEnumI18n(NetType.CABLE.getText()) : getEnumI18n(device.getNetType().getText()));
		if (device.getDevService() != null) {
			deviceForm.setDevServiceName(device.getDevService().getName());
			deviceForm.setDevServiceId(device.getDevService().getGuid());
		}

		if (device.getDevType() != null) {
			deviceForm.setDevTypeId(device.getDevType().getId());
			deviceForm.setDevTypeName(device.getDevType().getName());
			deviceForm.setDevCatalogName(device.getDevType().getDevCatalog().getName());
			deviceForm.setDevVendorName(device.getDevType().getDevVendor().getName());
		}
		deviceForm.setId(String.valueOf(device.getId()));
		deviceForm.setIp(device.getIp().toString());
		if (device.getOrganization() != null) {
			deviceForm.setOrgId(device.getOrganization().getGuid());
			deviceForm.setOrgName(device.getOrganization().getName());
		}
		deviceForm.setStatus(device.getStatus() == null ? null : String.valueOf(device.getStatus().getId()));
		deviceForm.setStatusName(device.getStatus() == null ? null : getEnumI18n(device.getStatus().getText()));
		deviceForm.setTerminalId(device.getTerminalId());
		deviceForm.setInstallDate(device.getInstallDate() != null ? DateUtils.getDate(device.getInstallDate()) : "");
		return deviceForm;
	}
}
