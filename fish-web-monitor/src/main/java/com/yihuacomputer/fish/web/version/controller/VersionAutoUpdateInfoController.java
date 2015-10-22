package com.yihuacomputer.fish.web.version.controller;

import java.util.Iterator;

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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.version.job.task.AutoUpdateTaskForm;
import com.yihuacomputer.fish.api.version.job.task.ITaskService;
import com.yihuacomputer.fish.api.version.job.task.TaskStatus;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping(value="/version/versionAutoUpdate")
public class VersionAutoUpdateInfoController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(VersionAutoUpdateInfoController.class);
	
	@Autowired
	private ITaskService taskService;
	
	@Autowired
	private IOrganizationService orgService;
	
	@RequestMapping(method=RequestMethod.GET)
    @ResponseBody
	public ModelMap getAutoUpdateVersionInfo(@RequestParam int start,@RequestParam int limit,WebRequest webRequest,HttpServletRequest request){
		logger.debug(String.format("get autoupdate info from %d limit %d",start,limit));
		ModelMap result = new ModelMap();
		IFilter filter = getFilterByRequest(webRequest);
		if(null==filter.getValue("orgFlag")){
			UserSession usersession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
			filter.eq("orgFlag", "%"+usersession.getOrgFlag());
		}
		IPageResult<AutoUpdateTaskForm> pageResult = taskService.pageAutoUpdateTask(start,limit,filter);
		result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, pageResult.list());
		return result;
	}
	
	private IFilter getFilterByRequest(WebRequest request){
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while(iterator.hasNext()){
			String name = iterator.next();
			if (isNotFilterName(name)) {
	                continue;
            }
            String value = request.getParameter(name);
            if (value == null || value.isEmpty()) {
                continue;
            }
            if("orgId".equals(name)){
            	IOrganization org = orgService.get(value);
            	if(null!=org){
            		filter.like("orgFlag", "%"+org.getOrgFlag());
            	}
            }
            else if("versionType".equals(name)){
            	filter.eq("versionType",Long.parseLong(value));
            }
            else if("versionNo".equals(name)){
            	filter.eq("versionNo",value);
            }else if("taskStatus".equals(name)){
            	TaskStatus status = TaskStatus.valueOf(TaskStatus.class, value);
            	filter.eq("taskStatus",status);
            }else if("terminalId".equals(name)){
            	filter.eq("terminalId","%"+value+"%");
            }else if("atmTypeId".equals(name)){
            	filter.eq("atmTypeId",Long.parseLong(value));
            }else if("deviceIp".equals(name)){
            	filter.eq("deviceIp",new IP(value));
            }
		}
		return filter;
	}
	 private boolean isNotFilterName(String name) {
	        return   "orgName".equals(name)
	                || "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
	                || "sort".equals(name);
	  }
}
