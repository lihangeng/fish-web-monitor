package com.yihuacomputer.fish.web.parameter.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.DeviceParam;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamClassify;
import com.yihuacomputer.fish.api.parameter.IParamClassifyService;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetailService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelation;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelationService;
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
	
	@Autowired
	private IParamTemplateDeviceRelationService paramTemplateDeviceRelationService;
	
	@Autowired
	private IParamElementService paramElementService;
	
	@Autowired
	private IParamTemplateDetailService paramTemplateDetailService;
	
	@Autowired
	private IParamPublishService paramPushService;
	
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
					filter.like(name, value.trim());
				} else if(name.equals("devTypeId")) {
					filter.eq("device.devType.id", Long.parseLong(request.getParameter(name)));
				} else if(name.equals("ip")){
					ITypeIP ip = new IP(request.getParameter("ip"));
					filter.eq("device.ip", ip);
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
		List<IAppSystem> appList=appSystemService.list();
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
	 * 根据设备号查询设备参数
	 */
	@RequestMapping(value="/paramInfo",method=RequestMethod.GET)
	public @ResponseBody ModelMap searchParam(HttpServletRequest request,WebRequest webRequest){
		logger.info("search the relative device's parameter information");
		ModelMap result=new ModelMap();
		IFilter filter = paramfilter(webRequest);
		long deviceId=1l;
		List<DeviceParam> pageResult = null;
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
		List<IParamTemplateDeviceRelation> relationList=paramTemplateDeviceRelationService.getByDeviceId(deviceId);
		if(relationList.isEmpty()){
			pageResult=paramDeviceDetailService.list(filter, tabId, deviceId);
			for(DeviceParam deviceParam:pageResult){
				if(deviceParam.getParamValue().isEmpty() || deviceParam.getParamValue()==null){
					deviceParam.setParamValue(deviceParam.getElementParamValue());
				}else{
					String str=deviceParam.getDevParamModifyTime();
					String s=str.substring(0,4)+"-"+str.substring(4, 6)+"-"+str.substring(6, 8)+" "+str.substring(8, 10)+":"+str.substring(10, 12)+":"+str.substring(12, 14);
					deviceParam.setEleModifyTime(s);
				}
			}
		}else{
			pageResult= new ArrayList<DeviceParam>();
			List<DeviceParam> param=paramDeviceDetailService.list(filter, tabId, deviceId);
			Map<Long,String> template=new HashMap<Long,String>();
			List<IParamTemplateDetail> templateParam=paramTemplateDetailService.list(deviceId);
			for(IParamTemplateDetail detail:templateParam){
				template.put(detail.getParamElement().getId(), detail.getParamValue());
			}
			for(Entry<Long,String> entry:template.entrySet()){
				for(DeviceParam dp:param){
					if(entry.getKey().equals(dp.getId())){
						DeviceParam dParam=new DeviceParam();
						dParam.setId(entry.getKey());
						dParam.setParamClassifyId(dp.getParamClassifyId());
						dParam.setParamClassify(dp.getParamClassify());
						dParam.setParamName(dp.getParamName());
						if(dp.getParamValue() != null && dp.getParamValue() != ""){
							dParam.setParamValue(dp.getParamValue());
							String str=dp.getDevParamModifyTime();
							String s=str.substring(0,4)+"-"+str.substring(4, 6)+"-"+str.substring(6, 8)+" "+str.substring(8, 10)+":"+str.substring(10, 12)+":"+str.substring(12, 14);
							dParam.setEleModifyTime(s);
						}else{
							dParam.setParamValue(dp.getElementParamValue());
							dParam.setEleModifyTime(dp.getEleModifyTime());
						}
						dParam.setEleParamType(dp.getEleParamType());
						dParam.setEleParamRights(dp.getEleParamRights());
						pageResult.add(dParam);
					}
				}
			}
		}
		
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
				} else if (name.equals("paramName")) {
					filter.eq(name, request.getParameter("paramName").trim());
				} else if(name.equals("ClassifyId")) {
					filter.eq(name, request.getParameter("ClassifyId"));
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
	
	/**@PathVariable long id,
	 * 更改设备参数
	 */
	@RequestMapping(value="/paramInfo/{id}",method=RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id,@RequestBody DeviceParam paramForm,HttpServletRequest request){
		logger.info("update device's parameter deviceId="+id);
		ModelMap result=new ModelMap();
		List<DeviceParam> paramList=paramForm.getDeviceParam();
		if(paramList==null){
			result.addAttribute(FishConstant.SUCCESS,false);
			result.addAttribute(FishConstant.ERROR_MSG,messageSource.getMessage("parameter.updateNotExist", null, FishCfg.locale));
		}else{
				for(int i=0;i<paramList.size();i++){
					long elementId=paramList.get(i).getId();
					IParamDeviceDetail deviceDetail=paramDeviceDetailService.getById(elementId, id);
					String dateStr=new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
					if(deviceDetail != null){
						deviceDetail.setParamValue(paramList.get(i).getParamValue());
						deviceDetail.setVersionTimeStamp(Long.valueOf(dateStr));
						paramDeviceDetailService.update(deviceDetail);
					}else{
						IParamDeviceDetail pdd=paramDeviceDetailService.make();
						pdd.setDevice(deviceService.get(id));
						pdd.setElement(paramElementService.get(paramList.get(i).getId()));
						pdd.setParamValue(paramList.get(i).getParamValue());
						pdd.setVersionTimeStamp(Long.valueOf(dateStr));
						paramDeviceDetailService.add(pdd);
					}
				}
				List<Long> deviceIdList=new ArrayList<Long>();
				deviceIdList.add(id);
				UserSession userSession=(UserSession) request.getSession().getAttribute("SESSION_USER");
				long jobId=paramPushService.paramPublishByDeviceIds(deviceIdList, Long.valueOf(userSession.getPersonId()));
				if(jobId!=Long.MIN_VALUE){
					result.addAttribute(FishConstant.SUCCESS, true);
					result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.downloadSuccess", null, FishCfg.locale));
				}else{
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.downloadFailure", null, FishCfg.locale));
				}
			}
		return result;
	}
	
	/**
	 * 下发设备参数
	 */
	@RequestMapping(value="/paramInfo/release",method=RequestMethod.POST)
	public @ResponseBody 
	 ModelMap releaseParam(@RequestParam String arrayId,HttpServletRequest request){
		logger.info("release the parameters of devices");
		ModelMap result=new ModelMap();
		String[] str=arrayId.split("-");
		List<Long> deviceIdList=new ArrayList<Long>();
		for(int i=1;i<str.length;i++){
			deviceIdList.add(Long.valueOf(str[i]));
		}
		UserSession userSession=(UserSession) request.getSession().getAttribute("SESSION_USER");
		long jobId=paramPushService.paramPublishByDeviceIds(deviceIdList, Long.valueOf(userSession.getPersonId()));
		if(jobId!=Long.MIN_VALUE){
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.downloadSuccess", null, FishCfg.locale));
			result.addAttribute(FishConstant.DATA, jobId);
		}else{
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.downloadFailure", null, FishCfg.locale));
		}
		return result;
		
	}
	
}
