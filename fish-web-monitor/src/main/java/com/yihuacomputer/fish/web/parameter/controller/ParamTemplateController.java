package com.yihuacomputer.fish.web.parameter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertGroupDeviceForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.parameter.form.ParamElementForm;
import com.yihuacomputer.fish.web.parameter.form.ParamTempDetailForm;
import com.yihuacomputer.fish.web.parameter.form.ParamTemplateForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * @author panxin
 *
 */
@Controller
@RequestMapping("/parameter/template")
public class ParamTemplateController {

	private Logger logger = LoggerFactory.getLogger(ParamTemplateController.class);
			
	@Autowired
	private IParamTemplateService templateService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IParamElementService paramElementService;

	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	private IAppSystemService appSystemService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start,
			@RequestParam int limit, WebRequest request) {
		logger.info(String.format("search template : start = %s ,limt = %s ",start, limit));
				
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IParamTemplate> pageResult = templateService.page(start,limit, filter);
				
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("template size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA,ParamTemplateForm.convert(pageResult.list()));
				
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap add(@RequestBody ParamTemplateForm request) {
		logger.info("add template");
		ModelMap result = new ModelMap();
		if(templateService.duplicateTemplateName(request.getName())){
			result.put(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "模板名称重复,请修改后重试");
			return result;
		}
		request.setApplyFlag("0");
		IParamTemplate template = templateService.add(translate(request));
	
		List<ParamTemplateForm> listElement =  request.getParamTemplateForm();
		 
		for(int i=0;i<listElement.size();i++){
			 
			IParamElement emlement = paramElementService.get(Long.parseLong(listElement.get(i).getElementId()));
			templateService.linkTempParam(template, emlement,listElement.get(i).getParamValue());
			
		}
		
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, new ParamTemplateForm(template));

		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ModelMap delete(@PathVariable long id) {
		logger.info(" delete template: template.id = " + id);
		ModelMap result = new ModelMap();
		try {
			templateService.unlinkAllBeforeDelete(id);
			templateService.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 * 更新模板详情
	 * @param form
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{templateDetail}", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap updateTemplateDetail(@RequestBody ParamTempDetailForm form , HttpServletRequest request) {
		
		ModelMap result = new ModelMap();
		if(templateService.duplicateTemplateName(form.getName())){
			result.put(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "模板名称重复,请修改后重试");
			return result;
		}

		List<ParamTempDetailForm> listDetail = form.getParamTempDetailForm();

		long templateId = form.getTemplateId();
		
		templateService.unlinkAll(templateId);
		IParamTemplate template = templateService.get(templateId);
        
		template.setName(form.getName());
		template.setRemark(form.getRemark());

		templateService.update(template);
		
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, form);
		
		
		IParamElement emlement = null;
		
		for(int i = 0; i<listDetail.size(); i++){
			
			emlement = paramElementService.get(listDetail.get(i).getElementId());
			templateService.linkTempParam(template, emlement, listDetail.get(i).getParamValue());
			
		}

		Map<Long, String> newMap = new HashMap<Long, String>();

		String paramValue = null;
		Long elementId = 0L;

		for (int i = 0; i < listDetail.size(); i++) {
			elementId = listDetail.get(i).getElementId();
			paramValue = listDetail.get(i).getParamValue();

			newMap.put(elementId, paramValue);
		}

		if (templateService.updateTemplateDetail(templateId, newMap)) {
			
			return result.addAttribute(FishConstant.SUCCESS, true);
			
		}

        
		
		return result.addAttribute(FishConstant.SUCCESS, false);

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
				} else {
					if (name.equals("sort")) {
						continue;
					} else {
						filter.like(name, request.getParameter(name));
					}
				}
			}
		}

		return filter;

	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name)
				|| "limit".equals(name) || "_dc".equals(name);
	}

	/**
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/linkedDevice", method = RequestMethod.GET)
	public @ResponseBody ModelMap searchLinkedDevice(@RequestParam int start,
			@RequestParam int limit, @RequestParam int flag,
			@RequestParam String guid, @RequestParam String organizationId,
			WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search device : start = %s ,limt = %s ",start, limit));
				
		ModelMap result = new ModelMap();
		IPageResult<IDevice> pageResult = null;
		if (flag == 0) {
			// 已关联的设备
			result.addAttribute(FishConstant.SUCCESS, true);
			Filter filter = getFilterDevice(request);
			filter.eq("t.templateId", Long.parseLong(guid));

			pageResult = templateService.pageLinkedDevice(start, limit,
					templateService.get(Long.parseLong(guid)), filter);
			result.addAttribute("total", pageResult.getTotal());
			result.addAttribute("data", DeviceForm.convert(pageResult.list()));
		} else {
			// 可以关联的设备
			IFilter filter = getFilterDevice(request);
			pageResult = templateService.pageUnlinkedDevice(start, limit,
					templateService.get(Long.parseLong(guid)), filter);

			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("total", pageResult.getTotal());
			result.addAttribute("data", DeviceForm.convert(pageResult.list()));
		}
		return result;
	}

	/**
	 * 建立关联关系：
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/link", method = RequestMethod.POST)
	public @ResponseBody ModelMap link(@RequestBody BsAdvertGroupDeviceForm request) {
			
		logger.info(String.format("device %s linked  %s", request.getGroupId(),request.getDeviceId()));
				
		ModelMap result = new ModelMap();
		IParamTemplate advertGroup = templateService.get(request.getGroupId());
		IDevice device = deviceService.get(request.getDeviceId());
		List<IDevice> list = templateService.listDeviceByTemplate(advertGroup);
		if (list.contains(device)) {
			result.put(FishConstant.SUCCESS, true);
			return result;
		}
		Date date = new Date();
		long timeStamp = Long.parseLong(DateUtils.getTimestamp5(date));
		templateService.link(advertGroup, device,timeStamp);
		result.put(FishConstant.SUCCESS, true);
		result.put("data", request);
		return result;
	}

	/**
	 * 解除关联关系：
	 *
	 * @param groupId
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/unlink", method = RequestMethod.POST)
	public @ResponseBody ModelMap unlink(@RequestParam String templateId,
			@RequestParam String deviceId) {
		ModelMap result = new ModelMap();
		String[] ids = deviceId.split(",");
		try {
			for (String idd : ids) {
				templateService.unlink(templateService.get(Long.parseLong(templateId)),deviceService.get(Long.valueOf(idd)));
				
			}
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			logger.info(ex.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 * 获得该模板下的已关联参数
	 *
	 * @return
	 */
	@RequestMapping(value = "/addedParam", method = RequestMethod.GET)
	public @ResponseBody ModelMap getAddedParam(@RequestParam int start,@RequestParam int limit, @RequestParam long id,@RequestParam long flag) {

		ModelMap result = new ModelMap();
		if (templateService.get(id) == null && flag == 0) {
			result.addAttribute(FishConstant.SUCCESS, false);
		} else if(flag == 0){
			result.addAttribute(FishConstant.SUCCESS, true);
			List<IParamElement> elements = templateService.listParam(id,flag);
			for(int i=0;i<elements.size();i++){
				elements.get(i).setParamValue(templateService.getDetailByTemplateId(id, elements.get(i).getId()).getParamValue());
			}
			result.addAttribute(FishConstant.DATA,convert(elements));
		}else {
			List<IParamElement> list = null;
			list =  paramElementService.list();
			list.removeAll(templateService.listParam(id,flag));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, convert(list));
		}
		return result;
	}
	
	
	/**
	 * 获得该模板下的可关联参数
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addingParam", method = RequestMethod.GET)
	public @ResponseBody ModelMap getAddingParam(@RequestParam int start,
			@RequestParam int limit, @RequestParam long id,@RequestParam long flag) {

		ModelMap result = new ModelMap();
		if (templateService.get(id) == null&&flag == 0) {
			result.addAttribute(FishConstant.SUCCESS, false);
		} else if(flag == 0){
			List<IParamElement> list = null;
			list =  paramElementService.list();
			list.removeAll(templateService.listParam(id,flag));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, convert(list));
		}else{
			
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA,convert(templateService.listParam(id,flag)));
			
		}
		return result;
	}

	@Autowired
	private IParamPublishService paramPushService;
	
	/**
	 * 将设备当前所有参数覆盖为模板的参数
	 */
	@RequestMapping(value = "/issueParam", method = RequestMethod.POST)
	public @ResponseBody ModelMap issueParam(@RequestParam long templateId,HttpServletRequest request) {
		
		ModelMap result = new ModelMap();
		
		Date date = new Date();
		long timeStamp = Long.parseLong(DateUtils.getTimestamp5(date));
	
		try {
			
			IParamTemplate template = templateService.get(templateId);
			UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
			templateService.issueTemplate(template, timeStamp);
			boolean noticeResult = paramPushService.paramPublishByTemplate(templateId, Long.parseLong(userSession.getPersonId()));
			if(noticeResult){
				template.setApplyFlag("1");
				templateService.update(template);
				result.put(FishConstant.SUCCESS, true);
			}
			else{
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, "通知失败!");
			}
		
		}catch(Exception ex){
			
			logger.info(ex.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "下发模板失败");
			
		}

		return result;
			
	}


	/**
	 * 获得该模板下的详细信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/templateDetail", method = RequestMethod.GET)
	public @ResponseBody ModelMap getTempDetail(@RequestParam int start,
			@RequestParam int limit, @RequestParam long id) {

		ModelMap result = new ModelMap();
		if (templateService.get(id) == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
		} else {
			List<IParamTemplateDetail> list = templateService
					.listTemplateDetail(id);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, convertDetail(list));
		}
		return result;
	}

	private Filter getFilterDevice(WebRequest request) {
		Filter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				String value = request.getParameter(name);
				if (org.apache.commons.lang.StringUtils.isEmpty(value)) {
					continue;
				}
				if (name.equals("orgId")) {
					IOrganization org = orgService.get(value);

					filter.like("d.organization.orgFlag", org.getOrgFlag());
				}
				if (name.equals("ip")) {
					ITypeIP ip = new IP(value);
					filter.eq("d.ip", ip);
				}
				if (name.equals("terminalId")) {
					filter.like("d.terminalId", value);
				}
				if (name.equals("devType")) {
					filter.eq("d.devType.id", Long.parseLong(value));
				}
			}
		}
		return filter;
	}

	public List<ParamElementForm> convert(List<IParamElement> resources) {
		List<ParamElementForm> result = new ArrayList<ParamElementForm>();
		for (IParamElement resource : resources) {
			result.add(new ParamElementForm(resource));
		}
		return result;
	}

	public List<ParamTempDetailForm> convertDetail(
			List<IParamTemplateDetail> resources) {
		List<ParamTempDetailForm> result = new ArrayList<ParamTempDetailForm>();
		for (IParamTemplateDetail resource : resources) {
			result.add(new ParamTempDetailForm(resource));
		}
		return result;
	}
	private IParamTemplate translate(ParamTemplateForm templateForm) {
		IParamTemplate template  =  templateService.make();
		template.setId(templateForm.getId());
		template.setName(templateForm.getName());
		template.setRemark(templateForm.getRemark());
		template.setApplyFlag(templateForm.getApplyFlag());
		return template;
		
	}
}
