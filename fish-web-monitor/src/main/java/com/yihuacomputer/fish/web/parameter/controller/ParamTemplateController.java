package com.yihuacomputer.fish.web.parameter.controller;

import java.util.Iterator;
import java.util.List;

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
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertGroupDeviceForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.parameter.form.ParamTemplateForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/parameter/template")
public class ParamTemplateController {

	private Logger logger = LoggerFactory.getLogger(ParamTemplateController.class);

	@Autowired
	private IParamTemplateService templateService;
	
    @Autowired
    private IDeviceService deviceService;
	
	
	@Autowired
	private IOrganizationService orgService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request){
		logger.info(String.format("search template : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IParamTemplate> pageResult = templateService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("template size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, ParamTemplateForm.convert(pageResult.list()));
		return result;
	}

	
	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody ParamTemplateForm request){
		logger.info("add template");
		ModelMap result=new ModelMap();
		IParamTemplate template = templateService.make();
		request.translate(template);
		templateService.add(template);
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, new ParamTemplateForm(template));

		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete template: template.id = " + id);
		ModelMap result = new ModelMap();
		try {
			templateService.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody ParamTemplateForm request) {
		logger.info("update elemet: elemet.id = " + id);
		ModelMap result = new ModelMap();
		IParamTemplate template = templateService.get(id);
		request.translate(template);
		template.setId(id);
		templateService.update(template);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, request);
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
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}
	
	
	
    /**
    *
    * @param form
    * @return ModelMap<String, Object>
    */
   @RequestMapping(value = "/linkedDevice", method = RequestMethod.GET)
   public @ResponseBody
   ModelMap searchLinkedDevice(@RequestParam int start, @RequestParam int limit, @RequestParam int flag,
           @RequestParam String guid, @RequestParam String organizationId, WebRequest request, HttpServletRequest req) {
       logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
       ModelMap result = new ModelMap();
       IPageResult<IDevice> pageResult = null;
       if (flag == 0) {
    	   // 已关联的设备
           result.addAttribute(FishConstant.SUCCESS, true);
           Filter filter = getFilterDevice(request);
           
           pageResult = templateService.pageLinkedDevice(start, limit, templateService.get(Long.parseLong(guid)), filter);
           result.addAttribute("total", pageResult.getTotal());
           result.addAttribute("data", DeviceForm.convert(pageResult.list()));
       } else {
    	   // 可以关联的设备
           IFilter filter = getFilterDevice(request);
           pageResult = templateService.pageUnlinkedDevice(start, limit, templateService.get(Long.parseLong(guid)), filter);
           
           result.addAttribute(FishConstant.SUCCESS, true);
           result.addAttribute("total", pageResult.getTotal());
           result.addAttribute("data", DeviceForm.convert(pageResult.list()));
       }
       return result;
   }
   
   
   
   
   /**
    * 建立关联关系：
    *
    * @param request
    * @return
    */
   @RequestMapping(value = "/link", method = RequestMethod.POST)
   public @ResponseBody
   ModelMap link(@RequestBody BsAdvertGroupDeviceForm request) {
       logger.info(String.format("device %s linked  %s", request.getGroupId(), request.getDeviceId()));
       ModelMap result = new ModelMap();
       IParamTemplate advertGroup = templateService.get(request.getGroupId());
       IDevice device = deviceService.get(request.getDeviceId());
       List<IDevice> list = templateService.listDeviceByTemplate(advertGroup);
       if (list.contains(device)) {
           result.put(FishConstant.SUCCESS, true);
           return result;
       }
       templateService.link(advertGroup, device);
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
   public @ResponseBody
   ModelMap unlink(@RequestParam String id, @RequestParam String deviceId) {
       ModelMap result = new ModelMap();
       String[] ids = deviceId.split(",");
       try {
           for (String idd : ids) {
        	   templateService.unlink(templateService.get(Long.parseLong(id)), deviceService.get(Long.valueOf(idd)));
           }
           result.addAttribute(FishConstant.SUCCESS, true);
       }
       catch (Exception ex) {
           logger.info(ex.getMessage());
           result.addAttribute(FishConstant.SUCCESS, false);
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

					filter.like("organization.orgFlag", org.getOrgFlag());
				}
				if (name.equals("ip")) {
					ITypeIP ip = new IP(value);
					filter.eq("ip", ip);
				}
				if (name.equals("terminalId")) {
					filter.like("terminalId", value);
				}
				if (name.equals("devType")) {
					filter.eq("devType.id", Long.parseLong(value));
				}
			}
		}
		return filter;
	}

}
