package com.yihuacomputer.fish.web.cashbox.controller;

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
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.cashbox.form.DeviceCashBoxInfoForm;

@Controller
@RequestMapping("/cashbox")
@ClassNameDescrible(describle="userlog.CashBoxController")
public class DeviceCashBoxInfoController {

	private Logger logger = LoggerFactory.getLogger(DeviceCashBoxInfoController.class);

	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IOrganizationService orgService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachCashBoxInfoList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search CashBox Info List");
		ModelMap result = new ModelMap();
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String orgFlag = String.valueOf(userSession.getOrgFlag());
		IFilter filter  = request2filter(webRequest);
		if(null==filter.getFilterEntry("deviceId.organization.orgFlag")){
			filter.like("deviceId.organization.orgFlag", orgFlag+"%");
		}
		filter.order("boxChange");
		IPageResult<IDeviceBoxInfo> deviceBoxInfoPageResult = deviceBoxInfoService.list(start, limit, filter);
		List<DeviceCashBoxInfoForm> dcbifList = convert(deviceBoxInfoPageResult.list());
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, deviceBoxInfoPageResult.getTotal());
		result.put(FishConstant.DATA, dcbifList);
		return result;
	}
	/**
	 *
	 * 方法描述 : 根据ID更新设备钞箱信息
	 *
	 * @param guid
	 * @param request
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.CashBoxController.update",hasArgs=false,urlArgs=true )
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody DeviceCashBoxInfoForm request) {
		logger.info("update CashBox Limit Info: CashBoxInfo.id = " + id);
		request.setId(id);
		ModelMap model = new ModelMap();

		IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoService.get(id);		
		if (deviceBoxInfo == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, messageSource.getMessage("deviceBoxInfo.updateNotExist", null, FishCfg.locale));

			// 验证失败时，需要把正确(数据库)的数据返回
			model.addAttribute(FishConstant.DATA, request);
			return model;
		}
		deviceBoxInfo.setMaxAlarm(request.getMaxAlarm());
		deviceBoxInfo.setMinAlarm(request.getMinAlarm());
		deviceBoxInfo.setBoxChange(false);
		try {
			deviceBoxInfoService.update(deviceBoxInfo);
		} catch(Exception e) {
			logger.error(String.format("add error : %s",e.getMessage()));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
			return model;
		}
		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute(FishConstant.DATA,convert(deviceBoxInfo));
		return model;
	}
	@RequestMapping(value = "/synchronizedBoxLimit", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.CashBoxController.synchronizedBoxLimit",hasArgs=false,urlArgs=true )
	public @ResponseBody 
	ModelMap synchronizedUpdate(HttpServletRequest request,WebRequest webRequest) {
		ModelMap model = new ModelMap();
		long deviceCashBoxId = Long.parseLong(request.getParameter("id"));
		logger.info("synchronized CashBox Limit Info: CashBoxInfo.id = " + deviceCashBoxId);
		IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoService.get(deviceCashBoxId);
		
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String orgFlag = String.valueOf(userSession.getOrgFlag());
		IFilter filter  = request2filter(webRequest);
		if(null==filter.getFilterEntry("deviceId.organization.orgFlag")){
			filter.like("deviceId.organization.orgFlag", orgFlag+"%");
		}
		boolean updateResult = false;
		try{
			updateResult = deviceBoxInfoService.synchronizedUpdate(deviceBoxInfo,filter);
		}catch(Exception e){
			model.addAttribute(FishConstant.SUCCESS, false);
		}
		model.addAttribute(FishConstant.SUCCESS, updateResult);
		return model;
	}
	private DeviceCashBoxInfoForm convert(IDeviceBoxInfo deviceBoxInfo){
		DeviceCashBoxInfoForm dcbif = new DeviceCashBoxInfoForm();
		IDevice device = deviceBoxInfo.getDeviceId();
		dcbif.setId(deviceBoxInfo.getId());
		dcbif.setAwayFlagName(i18n(device.getAwayFlag().getText()));
		dcbif.setDevCatalogName(device.getDevType().getDevCatalog().getName());
		dcbif.setDevServiceName(device.getDevService().getName());
		dcbif.setDevTypeName(device.getDevType().getName());
		dcbif.setIp(device.getIp().toString());
		dcbif.setMaxAlarm(deviceBoxInfo.getMaxAlarm());
		dcbif.setMinAlarm(deviceBoxInfo.getMinAlarm());
		dcbif.setOrganizationName(device.getOrganization().getName());
		dcbif.setTerminalId(device.getTerminalId());
		return dcbif;
	}
	
	private List<DeviceCashBoxInfoForm> convert(List<IDeviceBoxInfo> list){
		List<DeviceCashBoxInfoForm> dcbifList = new ArrayList<DeviceCashBoxInfoForm>();
		for(IDeviceBoxInfo idbi :list){
			DeviceCashBoxInfoForm dcbif = new DeviceCashBoxInfoForm();
			IDevice device = idbi.getDeviceId();
			dcbif.setId(idbi.getId());
			dcbif.setAwayFlagName(i18n(device.getAwayFlag().getText()));
			dcbif.setDevCatalogName(device.getDevType().getDevCatalog().getName());
			dcbif.setDevServiceName(device.getDevService().getName());
			dcbif.setDevTypeName(device.getDevType().getName());
			dcbif.setIp(device.getIp().toString());
			dcbif.setMaxAlarm(idbi.getMaxAlarm());
			dcbif.setMinAlarm(idbi.getMinAlarm());
			dcbif.setOrganizationName(device.getOrganization().getName());
			dcbif.setTerminalId(device.getTerminalId());
			dcbifList.add(dcbif);
		}
		return dcbifList;
	}
	
	private String i18n(String code){
		return messageSource.getMessage(code, null, FishCfg.locale);
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

			if ("devTypeId".equals(name)) {// 型号
				filter.eq("deviceId.devType.id", Long.valueOf(value));
			} else if ("devVendorId".equals(name)) {// 品牌
				filter.eq("deviceId.devType.devVendor.id", Long.valueOf(value));
			} else if ("organization".equals(name)) {
				IOrganization org = orgService.get(value);
				filter.like("deviceId.organization.orgFlag", org.getOrgFlag()+"%");
			} else if ("ip".equals(name)) {
				ITypeIP ip = new IP(value);
				filter.eq("deviceId.ip", ip);
			} else if ("awayFlag".equals(name)) {
				filter.eq("deviceId.awayFlag", AwayFlag.getById(Integer.valueOf(value)));
			} else if ("terminalId".equals(name)) {
				filter.eq("deviceId.terminalId", value);
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
