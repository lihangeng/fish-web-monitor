/**
 *
 */
package com.yihuacomputer.fish.web.version.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IComplexDeviceService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersion;
import com.yihuacomputer.fish.api.version.IDeviceSoftVersionService;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.job.task.ITask;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.web.version.form.DeviceVersionHistory;
import com.yihuacomputer.fish.web.version.form.VersionDeviceForm;

/**
 * @author xuxigang
 *
 */
@Controller
@RequestMapping(value = "/version/device")
public class DeviceVersionController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(DeviceVersionController.class);
    @Autowired
    private IDeviceService deviceService;
    @Autowired
    private IComplexDeviceService complexDeviceService;
    @Autowired
    private ITaskService taskService;
    @Autowired
    private IVersionService versionService;
    @Autowired
    private IDeviceSoftVersionService dsvService;
 

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest webRequest,HttpServletRequest request) {
        logger.info(String.format("search device in DeviceVersion: start = %s ,limt = %s ", start, limit));
        IFilter filter = request2filter(webRequest);

        ModelMap result = new ModelMap();

        UserSession userSession = (UserSession)request.getSession().getAttribute("SESSION_USER");
        IPageResult<IDevice> pageResult = complexDeviceService.page(start, limit, filter,userSession.getUserId());

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", this.getForms(pageResult.list()));
        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/history")
    public @ResponseBody
    ModelMap history(@RequestParam int start, @RequestParam int limit, @RequestParam int deviceId) {
        logger.info(String.format("search Device Version history: start = %s ,limt = %s ", start, limit));

        ModelMap result = new ModelMap();

        List<ITask> lists = taskService.findTasks(deviceId);     
             
        List<DeviceVersionHistory> forms = getHistoryForms(lists , deviceId);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", forms.size());
        result.addAttribute("data",forms );
        return result;
    }

    private List<DeviceVersionHistory> getHistoryForms(List<ITask> lists , long deviceId) {
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
             IDevice device = task.getDevice();
             if(null==device){
            	 continue;
             }
             form.setTerminalId(device.getTerminalId());
             form.setIp(device.getIp().toString());
             form.setDownTime(task.getExcuteTime());
             form.setStatus(task.getStatus().getText());
             form.setRemark(task.getReason());
//             form.setUserName(getTaskCreatedUserName(deviceId,ver.getId()));
             forms.add(form);
             //index++;
        }                
        return forms;
    }
    
    private String getTaskCreatedUserName(long deviceId,long versionId){
       List<ITask>  tasks = taskService.findTaskByDeviceIdAndVersionId(deviceId,versionId); 
       if(tasks.size() > 0){
    	   return "";
//    	   TODO  : 
//    	   return tasks.get(0).getJob().getCreateUser().getName();
       }else{
    	   return "";
       }
    }

    private List<VersionDeviceForm> getForms(List<IDevice> devices){
        List<VersionDeviceForm> forms = new ArrayList<VersionDeviceForm>();
        for(IDevice device : devices){
            VersionDeviceForm form = new VersionDeviceForm(device);
            String appVersion = getAppVersion(device);
            if(appVersion != null){
                form.setAppVersion(appVersion);
            }
            forms.add(form);
        }
        return forms;
    }

    private String getAppVersion(IDevice device){
        IFilter filter = new Filter();
        filter.eq("terminalId", device.getTerminalId());
        List<IDeviceSoftVersion> versions = dsvService.list(filter);
        for(IDeviceSoftVersion dsv : versions){
            if(dsv.getTypeName().equalsIgnoreCase("gump-professional")){
                return dsv.getTypeName() + "_" + dsv.getVersionNo();
            }
            if(dsv.getTypeName().equalsIgnoreCase("Cols_SRCB_ShangHai")){
                return dsv.getTypeName() + "_" + dsv.getVersionNo();
            }
            if(dsv.getTypeName().equalsIgnoreCase("Cols_SRCB_RURAL")){
                return dsv.getTypeName() + "_" + dsv.getVersionNo();
            }
            if(dsv.getTypeName().equalsIgnoreCase("ASM_SRCB_ShangHai")){
                return dsv.getTypeName() + "_" + dsv.getVersionNo();
            }
        }
        return null;
    }

    private IFilter request2filter(WebRequest request) {
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (isNotFilterName(name)) {
                continue;
            }
            String value = request.getParameter(name);
            if (value == null || value.isEmpty()) {
                continue;
            }

           if ("devTypeId".equals(name)) {
                filter.eq("device.devType.id", Long.valueOf(value));
           } else if ("devBrandId".equals(name)) {
                filter.eq("device.devType.devVendor.id", Long.valueOf(value));
            } else if ("organization".equals(name)) {// value == orgId
                filter.eq("device.organization", value);
            } else if ("ip".equals(name)) {
                ITypeIP ip = new IP(value);
                filter.eq("device." + name, ip);
            } else {
                filter.like("device." + name, value);
            }
        }

        return filter;
    }

    private boolean isNotFilterName(String name) {
        return "devServiceName".equals(name) || "organizationID".equals(name) || "orgName".equals(name)
                || "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
                || "sort".equals(name);
    }
}
