package com.yihuacomputer.fish.web.parameter.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.DeviceParam;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamClassify;
import com.yihuacomputer.fish.api.parameter.IParamClassifyService;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.parameter.form.AppSystemForm;
import com.yihuacomputer.fish.web.parameter.form.DevParameterForm;
import com.yihuacomputer.fish.web.parameter.form.ParamClassifyForm;


@Controller
@RequestMapping("/parameter/devParameter")
public class DevParameterController {
private Logger logger=LoggerFactory.getLogger(AppSystemController.class);
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IAppSystemService appSystemService;
	
	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	private IParamClassifyService classifyService;
	
	@Autowired
	private IParamDeviceDetailService paramDeviceDetailService;
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start,@RequestParam int limit,HttpServletRequest request,WebRequest webRequest){
		logger.info("search device information");
		ModelMap result=new ModelMap();
		UserSession userSession=(UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization=String.valueOf(userSession.getOrgId());
		IFilter filter = request2filter(webRequest);
		IPageResult<IDevice> pageResult=deviceService.page(start, limit, filter,organization);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA,this.convert(pageResult.list()));
		return result;
		
	}
	
	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else if (name.equals("sort")) {
					continue;
				} else if (name.equals("terminalId")) {
					String value = request.getParameter(name);
					filter.eq(name, value.trim());
				} else {
					filter.like(name, request.getParameter(name));
				}
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}
	
	public List<DevParameterForm> convert(List<IDevice> list) {
		List<DevParameterForm> result = new ArrayList<DevParameterForm>();
		for (IDevice item : list) {
			result.add(new DevParameterForm(item));
		}
		return result;
	}
	
	@RequestMapping(value="/getAppData",method=RequestMethod.GET)
	public @ResponseBody ModelMap getAppData(HttpServletRequest request,WebRequest webRequest){
		logger.info("search appSystem information");
		ModelMap result=new ModelMap();
		List<IAppSystem> appList=EntityUtils.convert(appSystemService.list());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, appList.size());
		result.addAttribute(FishConstant.DATA,toForm(appList));
		return result;
		
	}
	
	
	/**
	 * 数据格式转换
	 */
	private List<AppSystemForm> toForm(List<IAppSystem> appSystem){
			List<AppSystemForm> lists=new ArrayList<AppSystemForm>();
				for(IAppSystem app:appSystem){
					lists.add(new AppSystemForm(app));
				}

			return lists;
	}
	
	/**
	 * 进入界面默认加载第一条设备参数
	 */
	@RequestMapping(value="/paramInfo",method=RequestMethod.GET)
	public @ResponseBody ModelMap searchParam(HttpServletRequest request,WebRequest webRequest){
		logger.info("search the relative device's parameter information");
		ModelMap result=new ModelMap();
		IFilter filter = paramfilter(webRequest);
		long deviceId=1l;
		if(null!=request.getParameter("deviceId")&&!request.getParameter("deviceId").isEmpty()){
			deviceId=Long.parseLong(request.getParameter("deviceId"));
		}else{
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		long tabId=1l;
		if(null!=request.getParameter("tabId")&&!request.getParameter("tabId").isEmpty()){
			tabId=Long.parseLong(request.getParameter("tabId"));
		}else{
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		List<DeviceParam> pageResult=paramDeviceDetailService.list(filter, tabId, deviceId);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.size());
		result.addAttribute(FishConstant.DATA,pageResult);
		return result;
	}
	
	/**
	 * 查询参数过滤条件
	 */
	private IFilter paramfilter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else if (name.equals("sort")) {
					continue;
				}/* else if (name.equals("paramName")) {
					filter.eq("pe."+name, request.getParameter("paramName"));
				} else if(name.equals("paramClassify")) {
					filter.eq("pe."+name, request.getParameter("paramClassify"));
				}else{
					filter.like(name, request.getParameter(name));
				}*/
				else{
					logger.info(name  + " " + request.getParameter(name));
				}
			}
		}

		return filter;
	}
	
	
	/**
	 * 参数分类查询
	 */
	@RequestMapping(value="/devParamClassify",method=RequestMethod.GET)
	public @ResponseBody ModelMap searchClassfigy(int start,int limit,HttpServletRequest request,WebRequest webRequest){
		logger.info("search device parameter classify information");
		ModelMap result=new ModelMap();
		List<IParamClassify> classifyList=EntityUtils.convert(classifyService.list());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, classifyList.size());
		result.addAttribute(FishConstant.DATA,classifyConvert(classifyList));
		return result;
	}
	
	private List<ParamClassifyForm> classifyConvert(List<IParamClassify> paramClassify){
		List<ParamClassifyForm> lists=new ArrayList<ParamClassifyForm>();
		for(IParamClassify classify:paramClassify){
			lists.add(new ParamClassifyForm(classify));
		}

	return lists;
	}
	
}
