package com.yihuacomputer.fish.web.machine.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.ITempDevInfo;
import com.yihuacomputer.fish.api.device.ITempDevInfoService;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;

@Controller
@RequestMapping("/machine/tempdevice")
public class TempDeviceController {
	private Logger logger = LoggerFactory.getLogger(DeviceController.class);


	/**
	 * 机构接口
	 */
	@Autowired
	private IOrganizationService orgService;

	/**
	 * 型号接口
	 */
	@Autowired
	private IAtmTypeService typeService;

	@Autowired
	private ITempDevInfoService tempDevInfoService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired(required = false)
	private ICollectService collectService;
	
	
	@Autowired
	protected MessageSource messageSource;


	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(webRequest);
		ModelMap result = new ModelMap();

		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization = String.valueOf(userSession.getOrgId());
		// 获得机构下所有的设备信息
		IPageResult<ITempDevInfo> pageResult = tempDevInfoService.page(start, limit, filter, organization);

		
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, DeviceForm.convertTempDev(pageResult.list()));
		return result;
		
		
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

			if ("startCashboxLimit".equals(name)) {
				filter.ge("device.cashboxLimit", Integer.valueOf(value));
			} else if ("endCashboxLimit".equals(name)) {
				filter.le("device.cashboxLimit", Integer.valueOf(value));
			} else if ("startInstallDate".equals(name)) {

				filter.ge("device.deviceExtend.installDate", DateUtils.getDate(value));

			} else if ("endInstallDate".equals(name)) {

				filter.le("device.deviceExtend.installDate", DateUtils.getDate(value));

			} else if ("devType".equals(name)) {// 型号

				filter.eq("device.devType.id", Long.valueOf(value));

			} else if ("devCatalogId".equals(name)) { // 类型

				filter.eq("device.devType.devCatalog.id", Long.valueOf(value));

			} else if ("devVendorId".equals(name)) {// 品牌

				filter.eq("device.devType.devVendor.id", Long.valueOf(value));

			} else if ("devService".equals(name)) {
				IOrganization org = orgService.get(value);

				filter.like("device.devService.orgFlag", org.getOrgFlag());

			} else if ("organization".equals(name)) {
				IOrganization org = orgService.get(value);

				filter.like("device.organization.orgFlag", org.getOrgFlag());

			} else if ("ip".equals(name)) {
				ITypeIP ip = new IP(value);
				filter.eq("device.ip", ip);
			} else if ("awayFlag".equals(name)) {
				filter.eq("device.awayFlag", AwayFlag.getById(Integer.valueOf(value)));
			} else if ("devTypeId".equals(name)) {
				filter.eq("device.devType.id", Long.parseLong(value));
			} 
//			
			else {
				filter.like("device." + name, value);
			}
		}

		return filter;
	}

	/**
	 *
	 * 方法描述 : 根据GUID更新未生效设备信息
	 *
	 * @param guid
	 * @param request
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody DeviceForm request) {
		logger.info("update Device: device.id = " + id);
		ModelMap model = new ModelMap();
		ITempDevInfo tempDevice = tempDevInfoService.get(id);
		if (tempDevice == null) {
			model.put("success", true);
			model.put("errorMsg", "该设备不存在，请刷新后再操作!");

			// 验证失败时，需要把正确(数据库)的数据返回
			model.addAttribute("data", request);
			return model;
		}	
	 	IOrganization org = orgService.get(request.getOrgId());		
		if (org == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.orgNotExist", null, FishCfg.locale));
			return model;
		}

		IOrganization serviceOrg = orgService.get(request.getDevServiceId());
		if (serviceOrg == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.serNotExist", null, FishCfg.locale));
			return model;
		}

		IAtmType atmType = typeService.get(request.getDevTypeId());
		if (atmType == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("device.typeNotExist", null, FishCfg.locale));
			return model;
		}
	    Date effectDate = DateUtils.getDate(request.getEffectiveDate());
       if (DateUtils.getDate(effectDate).equals(DateUtils.getDate(new Date()))) {
    		ITempDevInfo tempdev = tempDevInfoService.get(request.getTerminalId());
    		IDevice exitDevice = deviceService.get(request.getTerminalId());
        	if(tempdev != null)
        	{
        		tempDevInfoService.remove(request.getTerminalId());
        	}
       	
        	if(exitDevice == null)
        	{        		
        		IDevice device = deviceService.make();
    			device.setOrganization(org);
    			device.setDevService(serviceOrg);
    			device.setDevType(atmType);
    			request.translate(device);
    			if(tempDevice.getDeviceId() != 0)
    			{
    				device.setId(tempDevice.getDeviceId());
    			}
    			deviceService.add(device);
    		/*	// 增加设备后，初始化设备
    			if (collectService != null) {
    				collectService.initDeviceCollect(device);
    			}*/
        	}
        	else
        	{
        		 exitDevice.setOrganization(org);
        		 exitDevice.setDevService(serviceOrg);
        		 exitDevice.setDevType(atmType);
         	     request.translate(exitDevice);
                 deviceService.update(exitDevice);
        	}

			model.put("success", true);
			model.put("updateMsg", "更改成功,设备信息已生效!");
			return model;
		}else {
			ITempDevInfo dev = tempDevInfoService.get(id);
			System.out.println(id);
			if(dev != null)
			{
				
				dev.setOrganization(org);
				dev.setDevService(serviceOrg);
				dev.setDevType(atmType);
				request.translate(dev);
				dev.setDeviceId(tempDevice.getDeviceId());
				tempDevInfoService.update(dev);
			}
			model.put("success", true);
			model.put("updateMsg", "设备信息更新成功!");
			return model;
		}
	}


	/**
	 *
	 * 根据ID删除未生效设备信息表
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete device: tempDevice.id = " + id);
		ModelMap result = new ModelMap();
		result.put("success", true);
        System.out.println(id);
		ITempDevInfo tempDevice = null;
		try {
			tempDevice = tempDevInfoService.get(id);
			System.out.println(tempDevice);
			if (tempDevice == null) {
				return result;
			}
			tempDevInfoService.remove(id);
		} catch (Exception ex) {
			result.put("errorMsg", "删除失败");
		}
		return result;
	}

	private boolean isNotFilterName(String name) {
		return "devServiceName".equals(name) || "organizationID".equals(name) || "orgName".equals(name) || "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
				|| "sort".equals(name);
	}


}
