package com.yihuacomputer.fish.web.machine.controller;

import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.ITypeIP;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.exception.ServiceException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.common.util.PageResult;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.web.machine.form.AtmTypeForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.system.form.PersonForm;
import com.yihuacomputer.fish.web.util.ExcelViewUtils;

@Controller
@RequestMapping("/machine/device")
@ClassNameDescrible(describle="userlog.deviceController")
public class DeviceController {

	private Logger logger = LoggerFactory.getLogger(DeviceController.class);

	/**
	 * 设备接口
	 */
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	private IVersionTypeAtmTypeRelationService versionAtmTypeService;
	
	
	@Autowired
	protected MessageSource messageSource;

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
	private IDevicePersonRelation devicePersonRelation;

	@RequestMapping(method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.deviceController.add",hasReqBodyParam=true,reqBodyClass=DeviceForm.class,bodyProperties="terminalId")
	public @ResponseBody
	ModelMap add(@RequestBody DeviceForm request) {
		logger.info("add Device");
		ModelMap model = new ModelMap();	
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
		
		IDevice device = deviceService.make();
		device.setOrganization(org);
		device.setDevService(serviceOrg);
		device.setDevType(atmType);
		logger.info(request.getInstallDate());
		request.translate(device);
		/*if (request.getInstallDate() != null  && !"".equals(request.getInstallDate())&& !request.getInstallDate().equals(DateUtils.getDate(new Date()))) {
			device.setStatus(DevStatus.UNOPEN);
		}else{*/
			device.setStatus(DevStatus.OPEN);
		/*}*/
		Map<String, Object> result = validator(request, "add");
		if ((Boolean) result.get("validator")) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, result.get("errorMsg"));
			return model;
		}
		try {
			deviceService.add(device);
		} catch (Exception e) {
			logger.error(String.format(messageSource.getMessage("device.addError", null, FishCfg.locale), e));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
			return model;
		}

		model.addAttribute(FishConstant.DATA, toFrom(device));
		return model;
	}

	/**
	 *
	 * 根据ID删除设备
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@MethodNameDescrible(describle="userlog.deviceController.delete",urlArgs=true)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete device: device.id = " + id);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		try {
			deviceService.remove(id);
		} catch (ServiceException se) {

			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, se.getMessage());

		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 根据GUID更新用户
	 *
	 * @param guid
	 * @param request
	 * @return ModelMap<String, Object>
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.deviceController.update",hasReqBodyParam=true,reqBodyClass=DeviceForm.class,bodyProperties="terminalId" )
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody DeviceForm request) {
		logger.info("update Device: device.id = " + id);
		request.setId(Long.toString(id));
		ModelMap model = new ModelMap();

		IDevice device = deviceService.get(id);		
		if (device == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));

			// 验证失败时，需要把正确(数据库)的数据返回
			model.addAttribute(FishConstant.DATA, request);
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
		device.setDevService(serviceOrg);
		device.setOrganization(org);
		device.setDevType(atmType);
		request.translate(device);
		try {
			deviceService.update(device);
		} catch(Exception e) {
			logger.error(String.format("add error : %s",e.getMessage()));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
			return model;
		}
		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute(FishConstant.DATA, toFrom(device));
		return model;
	}
	
	@RequestMapping(value = "export", method = RequestMethod.GET)
	@MethodNameDescrible(describle="userlog.deviceController.export",hasArgs=false)
	public @ResponseBody
	ModelAndView export( WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search device : "));
		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization = String.valueOf(userSession.getOrgId());

		StringBuffer hql = new StringBuffer();

		List<Object> fixedFilters = request2filter(webRequest, hql, organization);

		// 获得机构下所有的设备信息
		List<IDevice> data = deviceService.list(hql.toString(), fixedFilters);

		Map<String,Object> map = new HashMap<String,Object>();
		String theme = String.format("%s_%s",userSession.getOrgName(),getI18N("device.devinfo"));
		map.put(ExcelViewUtils.SHEET_NAME, theme);//device.devinfo
		map.put(ExcelViewUtils.TITLE, theme);
		map.put(ExcelViewUtils.FILE_NAME, theme);
		// 获得机构下所有的设备信息
		List<DeviceForm> formList = convert(data);
		map.put(ExcelViewUtils.BODY_CONTEXTS, formList);
		ExcelViewUtils excelUtils = new ExcelViewUtils();
		return new ModelAndView(excelUtils,map);
	}

	private String getI18N(String code){
		return messageSource.getMessage(code, null, FishCfg.locale);
	}


	/**
	 *
	 * 根据条件得到设备列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(webRequest);

		ModelMap result = new ModelMap();

		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization = String.valueOf(userSession.getOrgId());

		// 获得机构下所有的设备信息
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter, organization);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/findMatch", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchMatch(@RequestParam int start, @RequestParam int limit, WebRequest webRequest, HttpServletRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = new Filter();
		if(null != request.getParameter("query")){
			filter.like("terminalId", request.getParameter("query").toString()+"%");
		}

		ModelMap result = new ModelMap();

		UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
		String organization = String.valueOf(userSession.getOrgId());

		// 获得机构下所有的设备信息
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter, organization);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL,  pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}


	@RequestMapping(value = "/findByOrg", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap findByOrg(@RequestParam int start, @RequestParam int limit, @RequestParam String organizationID,
			WebRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);

		ModelMap result = new ModelMap();
		IOrganization org = orgService.get(organizationID);
		if(null==org){
			result.addAttribute(FishConstant.SUCCESS, false);
			IPageResult<IDevice> pageResult = new PageResult<IDevice>();
			result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
			result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
			return result;
		}
		filter.like("organization.orgFlag", "%" + organizationID);

		// 获得机构下所有的设备信息organizationID
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/findByService", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap findByService(@RequestParam int start, @RequestParam int limit, @RequestParam String organizationID,
			WebRequest request) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);

		ModelMap result = new ModelMap();

		filter.like("devService.orgFlag",organizationID +  "%");

		// 获得机构下所有的设备信息organizationID
		IPageResult<IDevice> pageResult = deviceService.page(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "findByTerminalId", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam String terminalId) {
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, toFrom(deviceService.get(terminalId)));
		return result;
	}

	
	public List<DeviceForm> convert(List<IDevice> list) {
		List<DeviceForm> result = new ArrayList<DeviceForm>();
		for (IDevice item : list) {
			result.add(toFrom(item));
		}
		return result;
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
		deviceForm.setAwayFlagName(device.getAwayFlag() == null ? null : getI18N(device.getAwayFlag().getText()));
		deviceForm.setSetupType(device.getSetupType() == null ? null : String.valueOf(device.getSetupType().getId()));
		deviceForm.setSetupTypeName(device.getSetupType() == null ? null : getI18N(device.getSetupType().getText()));
		deviceForm.setWorkType(device.getWorkType() == null ? null : String.valueOf(device.getWorkType().getId()));
		deviceForm.setVirtual(device.getVirtual());
		deviceForm.setSerial(device.getSerial());
		deviceForm.setNetType(device.getNetType() == null ? String.valueOf(NetType.CABLE.getId()) : String.valueOf(device.getNetType().getId()));
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
		deviceForm.setStatusName(device.getStatus() == null ? null : getI18N(device.getStatus().getText()));
		deviceForm.setTerminalId(device.getTerminalId());
		deviceForm.setInstallDate(device.getInstallDate() != null ? DateUtils.getDate(device.getInstallDate()) : "");
		return deviceForm;
	}
	
	/**
	 * 获取所有品牌信息
	 *
	 * @param id
	 *            设备的ID
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.deviceController.devDetail",hasArgs=false)
	@RequestMapping(value = "/queryAtmType", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap queryAtmType(HttpServletRequest request) {
		logger.info(String.format("search device : queryAtmType"));
		//版本关联机型品牌信息
		String versionId = request.getParameter("versionId");
		List<Long> atmTypeIdList = null;
		if(null!=versionId){
			atmTypeIdList = versionAtmTypeService.getAtmTypeIdsByVersionId(Long.parseLong(versionId));
		}
		ModelMap model = new ModelMap();
		List<IAtmType> atmTypeList = null;
		if(null==atmTypeIdList){
			atmTypeList = typeService.list();
		}
		else if(atmTypeIdList.size()==0){
			model.put(FishConstant.SUCCESS, true);
			model.put(FishConstant.DATA, new AtmTypeForm());
			return model;
		}
		else{
			IFilter filter = new Filter();
			filter.in("id", atmTypeIdList);
			atmTypeList =typeService.list(filter);
		}
		model.put(FishConstant.SUCCESS, true);
		model.put(FishConstant.DATA, AtmTypeForm.convert(atmTypeList));
		

		return model;
	}

	private Map<String, Object> validator(DeviceForm form, String action) {
		Map<String, Object> result = new HashMap<String, Object>();

		boolean validatorTi = false;
		StringBuffer errorMsg = new StringBuffer();

		IDevice device = deviceService.get(form.getTerminalId());
		result.put("validator", false);
		if (device != null) {
			// 该设备号已经被使用
			validatorTi = true;
			errorMsg.append(messageSource.getMessage("device.termIdDup", null, FishCfg.locale)+".<BR>");
		}

		result.put("validator", validatorTi);
		result.put("errorMsg", errorMsg.toString());
		return result;
	}

	/**
	 * 查询设备对应的人员
	 *
	 * @param terminalId
	 *            设备号
	 * @param type
	 *            0管机员,1维护员
	 * @return
	 */
	@RequestMapping(value = "/queryPerson", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap queryPerson(@RequestParam String terminalId, @RequestParam int type) {
		logger.info(String.format("search device : validatorTerminalId"));
		ModelMap model = new ModelMap();
		List<IPerson> personList = devicePersonRelation.listPersonByDevice(terminalId);
		List<IPerson> newPersonList = new ArrayList<IPerson>();
		for (IPerson person : personList) {
			if (person.getType().getId() == type) {
				newPersonList.add(person);
			}
		}
		model.put(FishConstant.SUCCESS, true);
		model.put(FishConstant.DATA, PersonForm.convert(newPersonList));

		return model;
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

	private List<Object> request2filter(WebRequest request, StringBuffer hql, String organization) {
		List<Object> fixedFilters = new ArrayList<Object>();
		hql.append("from Device device where 1=1 and device.organization.orgFlag like ? ");
		IOrganization org = orgService.get(organization);
		fixedFilters.add(org.getOrgFlag() + "%");

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
				hql.append(" and device.cashboxLimit >= ?");
				fixedFilters.add(Integer.valueOf(value));
			} else if ("endCashboxLimit".equals(name)) {
				hql.append(" and device.cashboxLimit <= ?");
				fixedFilters.add(Integer.valueOf(value));
			} else if ("startInstallDate".equals(name)) {
				hql.append(" and device.deviceExtend.installDate >= ?");
				fixedFilters.add(DateUtils.getDate(value));
			} else if ("endInstallDate".equals(name)) {
				hql.append(" and device.deviceExtend.installDate <= ?");
				fixedFilters.add(DateUtils.getDate(value));
			} else if ("devType".equals(name)) {// 型号
				hql.append(" and device.devType.id = ?");
				fixedFilters.add(Long.valueOf(value));
			} else if ("devCatalogId".equals(name)) { // 类型
				hql.append(" and device.devType.devCatalog.id = ?");
				fixedFilters.add(Long.valueOf(value));
			} else if ("devVendorId".equals(name)) {// 品牌
				hql.append(" and device.devType.devVendor.id = ?");
				fixedFilters.add(Long.valueOf(value));
			} else if ("devService".equals(name)) {
				hql.append(" and device.devService.orgFlag like ?");
				fixedFilters.add(orgService.get(value).getOrgFlag() + "%");
			} else if ("organization".equals(name)) {
				hql.append(" and device.organization.orgFlag like ?");
				fixedFilters.add(orgService.get(value).getOrgFlag() + "%");
			} else if ("ip".equals(name)) {
				ITypeIP ip = new IP(value);
				hql.append(" and device.ip = ?");
				fixedFilters.add(ip);
			} else if ("awayFlag".equals(name)) {
				hql.append(" and device.awayFlag = ?");
				fixedFilters.add(AwayFlag.getById(Integer.valueOf(value)));
			} else {
				hql.append(" and device.").append(name).append(" like ?");
				fixedFilters.add("%" + value + "%");
			}
		}
		return fixedFilters;
	}

	private boolean isNotFilterName(String name) {
		return "devServiceName".equals(name) || "organizationID".equals(name) || "orgName".equals(name)
				|| "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
				|| "sort".equals(name)||name.startsWith("gridInfo");
	}

}
