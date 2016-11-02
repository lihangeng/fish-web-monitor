package com.yihuacomputer.fish.web.machine.controller;

import java.util.ArrayList;
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
import com.yihuacomputer.fish.api.device.WorkType;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.api.monitor.business.IRegistService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.monitor.entity.business.DeviceRegister;
import com.yihuacomputer.fish.monitor.entity.business.RunInfo;
import com.yihuacomputer.fish.monitor.entity.report.DeviceReport;
import com.yihuacomputer.fish.monitor.entity.report.StatusReport;
import com.yihuacomputer.fish.web.machine.form.DeviceDetailForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.system.form.PersonForm;
import com.yihuacomputer.fish.web.version.form.DeviceVersionHistory;
import com.yihuacomputer.fish.web.version.form.VersionForm;

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
    private IVersionService VersionService;  
    
    @Autowired
    private IVersionTypeService VersionTypeService;   
    
    
    
    @Autowired
    private  IDeviceBoxInfoService devcieBoxInfoService;
    /**
     * 
     * 根据条件得到设备列表
     * 
     * @param form
     * @return ModelMap<String, Object>
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(HttpServletRequest httpRequest, WebRequest request)
    {
        logger.info(String.format("search device detail "));
        String terminalId = request.getParameter("termianlId");
        String typeName="gump-professional";
        IDevice device = deviceService.get(terminalId);
        ModelMap result = new ModelMap();
        if(null==device){
        	result.addAttribute(FishConstant.SUCCESS,false);
        	result.addAttribute(FishConstant.ERROR_MSG,"设备:"+terminalId+"不存在");
        	return result;
        }
        String deviceOrgFlag = device.getOrganization().getOrgFlag();
        HttpSession session = httpRequest.getSession();
        UserSession userSession = (UserSession) session.getAttribute("SESSION_USER");
        String userOrgFlag = userSession.getOrgFlag();
        if(!deviceOrgFlag.startsWith(userOrgFlag)){
        	result.addAttribute(FishConstant.SUCCESS,false);
        	result.addAttribute(FishConstant.ERROR_MSG,"无权限查看设备"+terminalId);
        	return result;
        }
        IXfsStatus xfsStatus = xfsService.loadXfsStatus(terminalId);
        List<IPerson> personList = devicePersonRelation.listPersonByDevice(terminalId);
        IDeviceBoxInfo devcieBoxInfo = devcieBoxInfoService.findByDeviceId(device.getId());
        List<ITask> lists = taskService.findTasks(device.getId());   
        DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
        StatusReport statusReport = new StatusReport();
        DeviceReport deviceReport = new DeviceReport();
		deviceReport.setDeviceId(terminalId);
		deviceReport.setXfsStatus(xfsStatus);
		IRunInfo runInfo = new RunInfo();
		runInfo.setRunStatus(xfsStatus.getRunStatus());
		deviceReport.setRunInfo(runInfo);
		deviceReport.setDevice(device);
		deviceReport.setDeviceRegister((DeviceRegister) registService.load(terminalId));
		statusReport.setStatusReport(deviceReport, messageSourceEnum);
        deviceDetailForm.setStatusReport(statusReport);
        deviceDetailForm.setDeviceForm(toFrom(device));
        deviceDetailForm.setAppReleaseList(getVersionForm(typeName,terminalId));
        
        deviceDetailForm.setMaxAlarm(devcieBoxInfo==null?"未知":String.valueOf(devcieBoxInfo.getMaxAlarm()));
        deviceDetailForm.setMinAlarm(devcieBoxInfo==null?"未知":String.valueOf(devcieBoxInfo.getMinAlarm()));
        deviceDetailForm.setPersonList(PersonForm.convert(personList));
        deviceDetailForm.setVersionDeviceList(getHistoryForms(lists, device));
        result.addAttribute(FishConstant.DATA, deviceDetailForm);
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
	public @ResponseBody
	ModelMap searchBasicInfo(HttpServletRequest httpRequest, WebRequest request){
    	logger.info(String.format("search device basic information"));
    	ModelMap result = new ModelMap();
    	String terminalId = request.getParameter("termianlId");
    	result= isExistAndCharge(httpRequest,terminalId);
    	if(!(Boolean) result.get(FishConstant.SUCCESS)){
    		return result;
    	}
    	IDevice device = deviceService.get(terminalId);
        DeviceForm deviceForm = toFrom(device);
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
    @RequestMapping(value="/hardwareInfo",method = RequestMethod.GET)
   	public @ResponseBody
   	ModelMap searchHardwareInfo(HttpServletRequest httpRequest, WebRequest request){
    	logger.info(String.format("search device hardware status information"));
    	ModelMap result = new ModelMap();
        String terminalId = request.getParameter("termianlId");
        IDevice device = deviceService.get(terminalId);
        result= isExistAndCharge(httpRequest,terminalId);
    	if(!(Boolean) result.get(FishConstant.SUCCESS)){
    		return result;
    	}
        IXfsStatus xfsStatus = xfsService.loadXfsStatus(terminalId);
    	DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
    	StatusReport statusReport = new StatusReport();
        DeviceReport deviceReport = new DeviceReport();
        IRunInfo runInfo = new RunInfo();
		runInfo.setRunStatus(xfsStatus.getRunStatus());
		deviceReport.setDeviceId(terminalId);
		deviceReport.setXfsStatus(xfsStatus);
    	deviceReport.setRunInfo(runInfo);
		deviceReport.setDevice(device);
    	statusReport.setStatusReport(deviceReport, messageSourceEnum);
        deviceDetailForm.setStatusReport(statusReport);
        return result;
    }
    
    /**
     *查询设备人员信息 
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/personInfo",method = RequestMethod.GET)
   	public @ResponseBody
   	ModelMap searchPersonInfo(HttpServletRequest httpRequest, WebRequest request){
    	logger.info(String.format("search device person information"));
    	ModelMap result = new ModelMap();
    	String terminalId = request.getParameter("termianlId");
    	result= isExistAndCharge(httpRequest,terminalId);
    	if(!(Boolean) result.get(FishConstant.SUCCESS)){
    		return result;
    	}
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
   	public @ResponseBody
   	ModelMap searchVersionInfo(HttpServletRequest httpRequest, WebRequest request){
    	logger.info(String.format("search device person information"));
    	ModelMap result = new ModelMap();
    	String terminalId = request.getParameter("termianlId");
    	IDevice device = deviceService.get(terminalId);
    	String typeName="gump-professional";
    	result= isExistAndCharge(httpRequest,terminalId);
    	if(!(Boolean) result.get(FishConstant.SUCCESS)){
    		return result;
    	}
    	List<ITask> lists = taskService.findTasks(device.getId()); 
    	DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
    	deviceDetailForm.setVersionDeviceList(getHistoryForms(lists, device));
    	deviceDetailForm.setAppReleaseList(getVersionForm(typeName,terminalId));
    	result.addAttribute(FishConstant.DATA, deviceDetailForm);
        result.addAttribute(FishConstant.SUCCESS, true);
    	return result;
    }
    
    /**
     * 查询设备版本信息
     * @param httpRequest
     * @param request
     * @return
     */
    @RequestMapping(value="/otherInfo",method = RequestMethod.GET)
   	public @ResponseBody
   	ModelMap searchOtherInfo(HttpServletRequest httpRequest, WebRequest request){
    	logger.info(String.format("search device person information"));
    	ModelMap result = new ModelMap();
    	String terminalId = request.getParameter("termianlId");
    	IDevice device = deviceService.get(terminalId);
        result= isExistAndCharge(httpRequest,terminalId);
    	if(!(Boolean) result.get(FishConstant.SUCCESS)){
    		return result;
    	}
    	DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
    	IDeviceBoxInfo devcieBoxInfo = devcieBoxInfoService.findByDeviceId(device.getId());
    	
    	DeviceReport deviceReport = new DeviceReport();
    	StatusReport statusReport = new StatusReport();
    	deviceReport.setDeviceRegister((DeviceRegister) registService.load(terminalId));
    	DeviceForm deviceForm = toFrom(device);
    	deviceDetailForm.setMaxAlarm(devcieBoxInfo==null?"未知":String.valueOf(devcieBoxInfo.getMaxAlarm()));
        deviceDetailForm.setMinAlarm(devcieBoxInfo==null?"未知":String.valueOf(devcieBoxInfo.getMinAlarm()));
        deviceDetailForm.setDeviceForm(deviceForm);
        statusReport.setStatusReport(deviceReport, messageSourceEnum);
        deviceDetailForm.setStatusReport(statusReport);
    	return result;
    }
    
    /**
     * 判断用户是否有权限查看设备信息及设备是否存在
     * @return
     */
    private ModelMap isExistAndCharge(HttpServletRequest httpRequest,String terminalId){
    	IDevice device = deviceService.get(terminalId);
        ModelMap result = new ModelMap();
        if(null==device){
        	result.addAttribute(FishConstant.SUCCESS, false);
        	result.addAttribute(FishConstant.ERROR_MSG, "设备:"+terminalId+"不存在");
        	return result;
        }
        String deviceOrgFlag = device.getOrganization().getOrgFlag();
        HttpSession session = httpRequest.getSession();
        UserSession userSession = (UserSession) session.getAttribute("SESSION_USER");
        String userOrgFlag = userSession.getOrgFlag();
        if(!deviceOrgFlag.startsWith(userOrgFlag)){
        	result.addAttribute(FishConstant.ERROR_MSG, "无权限查看设备"+terminalId);
        	result.addAttribute(FishConstant.SUCCESS, false);
        	return result;
        }
        return result.addAttribute(FishConstant.SUCCESS, true);
    }
    
    private List<DeviceVersionHistory> getHistoryForms(List<ITask> lists , IDevice device) {
        List<DeviceVersionHistory> forms = new ArrayList<DeviceVersionHistory>();

        
        for(ITask task : lists){
        	 DeviceVersionHistory form = new DeviceVersionHistory();   
             IVersion ver = task.getVersion();   
        	 if(null==ver){
        		 continue;
        	 }
             form.setVersionId(ver.getId());
             form.setVersionNo(ver.getVersionNo());
             form.setFullName(ver.getFullName());
             form.setVersionType(ver.getVersionType().getTypeName());
             form.setId(task.getId());
             form.setDeviceId(task.getDeviceId());
//             IDevice device = task.getDevice();
             if(null==device){
            	 continue;
             }
             form.setTerminalId(device.getTerminalId());
             form.setIp(device.getIp().toString());
             form.setDownTime(task.getExcuteTime());
             form.setStatus(getEnumI18n(task.getStatus().getText()));
             form.setRemark(task.getReason());
//             form.setUserName(getTaskCreatedUserName(deviceId,ver.getId()));
             forms.add(form);
             //index++;
        }                
        return forms;
    }
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
    
    private List<VersionForm> getVersionForm(String typeName,String terminalId) {
        List<VersionForm> appReleaseList = new ArrayList<VersionForm>();
        String versionNo=registService.load(terminalId).getAtmcVersion();
        IVersion currentVersion=VersionService.findVersion(typeName, versionNo);
        IFilter filter=new Filter();
        filter.eq("typeName", typeName);
        long typeId=VersionTypeService.list(filter).get(0).getId();
        IFilter filter2=new Filter();
        filter2.descOrder("versionStr");
        filter2.eq("versionType.id", typeId);
        List<IVersion> maybeVersions=VersionService.list(filter2);
        List<IVersion> versions= VersionService.getUpdateVersion(maybeVersions,currentVersion);
        appReleaseList=toVersionForm(versions);
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
