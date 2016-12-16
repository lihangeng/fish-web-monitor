package com.yihuacomputer.fish.web.parameter.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
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
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetailService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelationService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.parameter.form.ParamElementForm;
import com.yihuacomputer.fish.web.parameter.form.ParamTempDetailForm;
import com.yihuacomputer.fish.web.parameter.form.ParamTemplateDeviceform;
import com.yihuacomputer.fish.web.parameter.form.ParamTemplateForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * @author panxin
 *
 */
@Controller
@RequestMapping("/parameter/template")
@ClassNameDescrible(describle="userlog.ParamTemplateController")
public class ParamTemplateController {

	private Logger logger = LoggerFactory.getLogger(ParamTemplateController.class);

	@Autowired
	private IParamTemplateService templateService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IParamDeviceDetailService paramDeviceDetailService;

	@Autowired
	private IParamTemplateDeviceRelationService paramTemplateDeviceRelationService;

	@Autowired
	private IParamElementService paramElementService;

	@Autowired
	private IParamTemplateDetailService paramTemplateDetailService;

	@Autowired
	private IOrganizationService orgService;


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
	@MethodNameDescrible(describle="userlog.ParamTemplateController.add",hasReqBodyParam=true,reqBodyClass=ParamTemplateForm.class,bodyProperties="name")
	public @ResponseBody ModelMap add(@RequestBody ParamTemplateForm request) {
		logger.info("add template");
		ModelMap result = new ModelMap();
		if(templateService.duplicateTemplateName(request.getName())){
			result.put(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.template.addFailure", null, FishCfg.locale));
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
	@MethodNameDescrible(describle="userlog.ParamTemplateController.delete",hasLogKey=true)
	public @ResponseBody ModelMap delete(@PathVariable long id) {
		logger.info(" delete template: template.id = " + id);
		ModelMap result = new ModelMap();
		IParamTemplate paramTemplate = templateService.get(id);
		result.addAttribute(FishConstant.LOG_KEY, paramTemplate.getName());
		try {
			templateService.unlinkAllBeforeDelete(id);
			templateService.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		}catch(ObjectNotFoundException ex ){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.template.notExist", null, FishCfg.locale));
		} 
		catch (Exception ex) {
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
	@MethodNameDescrible(describle="userlog.ParamTemplateController.updateTemplateDetail",hasReqBodyParam=true,reqBodyClass=ParamTempDetailForm.class,bodyProperties="name")
	@ResponseBody
	public ModelMap updateTemplateDetail(@RequestBody ParamTempDetailForm form , HttpServletRequest request) {

		ModelMap result = new ModelMap();

		List<ParamTempDetailForm> listDetail = form.getParamTempDetailForm();

		long templateId = form.getTemplateId();

		List<IParamTemplateDetail> list = paramTemplateDetailService.listByTempateId(templateId);


		Map<Long, String> oldMap = new HashMap<Long, String>();
		Long id = 0L;
		String value = null;
		for(int i = 0; i < list.size() ; i++) {
			id = list.get(i).getParamElement().getId();
			value = list.get(i).getParamElement().getParamValue();

			oldMap.put(id, value);
		}

		templateService.unlinkAll(templateId);
		IParamTemplate template = templateService.get(templateId);
		boolean isExist = this.isExistTemplateName(form.getTemplateId(), form.getName());
		if(isExist){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.template.updateTemplateDetail", null, FishCfg.locale));
		} else {
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

			if(!oldMap.equals(newMap)==true){
				template.setApplyFlag("0");
			}

			if (templateService.updateTemplateDetail(templateId, newMap)) {

				return result.addAttribute(FishConstant.SUCCESS, true);

			}
		}

		return result;


	}

	private boolean isExistTemplateName(long templateId, String name) {
		try {

			IParamTemplate template = templateService.get(name.trim());
			if(template == null){
				return false;
			}
			if (template.getId() == templateId) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
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
					if ("sort".equals(name)) {
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
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		IPageResult<IDevice> pageResult = null;
		IParamTemplate paramTemplate = templateService.get(Long.parseLong(guid));
		if (paramTemplate == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.template.notExist", null, FishCfg.locale));
			return result;
		}
		result.addAttribute(FishConstant.LOG_KEY, paramTemplate.getName());
		if (flag == 0) {
			// 已关联的设备
			result.addAttribute(FishConstant.SUCCESS, true);
			Filter filter = getFilterDevice(request);
			filter.eq("t.templateId", Long.parseLong(guid));

			pageResult = templateService.pageLinkedDevice(start, limit,
					templateService.get(Long.parseLong(guid)),userSession.getOrgId(), filter);

			result.addAttribute("total", pageResult.getTotal());
			result.addAttribute("data", convertDevice(pageResult.list()));
		} else {
			// 可以关联的设备
			IFilter filter = getFilterDevice(request);

			pageResult = templateService.pageUnlinkedDevice(start, limit,
					templateService.get(Long.parseLong(guid)),userSession.getOrgId(), filter);

			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("total", pageResult.getTotal());
			result.addAttribute("data", convertDevice(pageResult.list()));
		}
		return result;
	}

	/**
	 * 建立关联关系：
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/link", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.ParamTemplateController.link",hasLogKey=true)
	public @ResponseBody ModelMap link(@RequestBody ParamTemplateDeviceform request) {

		logger.info(String.format("device %s linked  %s", request.getGroupId(),request.getDeviceId()));

		ModelMap result = new ModelMap();
		IParamTemplate template = templateService.get(request.getGroupId());
		IDevice device = deviceService.get(request.getDeviceId());
		if(template == null){
			result.put(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.template.notExist", null, FishCfg.locale));
			return result;
			
		}
		result.addAttribute(FishConstant.LOG_KEY, template.getName()+"->"+device.getTerminalId());
		List<IDevice> list = templateService.listDeviceByTemplate(template);
		if (list.contains(device)) {
			result.put(FishConstant.SUCCESS, true);
			return result;
		}
		Date date = new Date();
		long timeStamp = Long.parseLong(DateUtils.getTimestamp5(date));
		templateService.link(template, device,timeStamp);
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
	@MethodNameDescrible(describle="userlog.ParamTemplateController.unlink",hasLogKey=true)
	public @ResponseBody ModelMap unlink(@RequestParam String templateId,
			@RequestParam String deviceId) {
		ModelMap result = new ModelMap();
		String[] ids = deviceId.split(",");
		IParamTemplate paramTemplate = templateService.get(Long.parseLong(templateId));
		result.addAttribute(FishConstant.LOG_KEY, paramTemplate.getName());
		try {
			for (String idd : ids) {
				templateService.unlink(paramTemplate,deviceService.get(Long.valueOf(idd)));
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
	public @ResponseBody ModelMap getAddedParam(@RequestParam int start,@RequestParam int limit, @RequestParam long id,@RequestParam long flag,@RequestParam long appSystem) {

		ModelMap result = new ModelMap();
		if (templateService.get(id) == null && flag == 0) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.template.notExist", null, FishCfg.locale));
		} else if(flag == 0){
			result.addAttribute(FishConstant.SUCCESS, true);
			List<IParamElement> elements = templateService.listParam2(id,flag);
			for(int i=0;i<elements.size();i++){
				elements.get(i).setParamValue(templateService.getDetailByTemplateId(id, elements.get(i).getId()).getParamValue());
			}
			result.addAttribute(FishConstant.DATA,convert(elements));
		}else {
			List<IParamElement> list = null;
			list =  paramElementService.list();
			list.removeAll(templateService.listParam2(id,flag));
			List<IParamElement> list2 = new ArrayList<IParamElement>();
			int size = list.size();
			for(int i = 0 ; i< size ;i++){
				if(list.get(i).getParamBelongs().getId() == appSystem){
					list2.add(list.get(i));
				}
			}
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, convert(list2));
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
			@RequestParam int limit, @RequestParam long id,@RequestParam long flag,@RequestParam long appSystem,HttpServletRequest req) {

		ModelMap result = new ModelMap();
		if (templateService.get(id) == null&&flag == 0) {
			result.addAttribute(FishConstant.SUCCESS, false);
		} else if(flag == 0){
			List<IParamElement> list = null;
			IFilter filter  = new Filter();
			filter.eq("paramBelongs.id", appSystem);
			list =  paramElementService.list(filter);
//			List<IParamElement> element =templateService.listParam2(id,flag);
//			list.removeAll(element);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, convert(list));
		}else{

			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA,convert(templateService.listParam(id,flag,appSystem)));

		}
		return result;
	}

	@Autowired
	private IParamPublishService paramPushService;

	/**
	 * 将设备当前所有参数覆盖为模板的参数
	 */
	@MethodNameDescrible(describle="userlog.ParamTemplateController.issue",hasLogKey=true)
	@RequestMapping(value = "/issueParam", method = RequestMethod.POST)
	public @ResponseBody ModelMap issueParam(@RequestParam long templateId,HttpServletRequest request) {

		ModelMap result = new ModelMap();

		Date date = new Date();
		long timeStamp = Long.parseLong(DateUtils.getTimestamp5(date));

		try {
			List<IDevice> deviceList = paramTemplateDeviceRelationService.listDeviceByTemplate(templateId);
			if(templateService.get(templateId)==null){
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.template.notExist", null, FishCfg.locale));
				return result;
				
				
			}
			
			if(null!=deviceList&&deviceList.isEmpty()){
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, getI18N("parameter.template.deviceUnlinked"));
				return result;
			}

			List<IParamTemplate> elementList = paramTemplateDetailService.listParamByTemplate(templateId);
			if(null!=elementList&&elementList.isEmpty()){
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, getI18N("parameter.template.hasNoParams"));
				return result;
			}


			IParamTemplate template = templateService.get(templateId);
			result.addAttribute(FishConstant.LOG_KEY, template.getName());
			UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
			templateService.issueTemplate(template, timeStamp);
			long jobId = paramPushService.paramPublishByTemplate(templateId, Long.parseLong(userSession.getPersonId()));
			if(jobId!=Long.MIN_VALUE){
				template.setApplyFlag("1");
				templateService.update(template);
				result.put(FishConstant.SUCCESS, true);
				result.put(FishConstant.DATA, jobId);
			}
			else{
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.template.infoFailure", null, FishCfg.locale));
			}

		}catch(Exception ex){

			logger.info(ex.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("parameter.template.issueFailure", null, FishCfg.locale));

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
				if ("orgId".equals(name)) {
					IOrganization org = orgService.get(value);

					filter.like("d.organization.orgFlag", org.getOrgFlag());
				}
				if ("ip".equals(name)) {
					ITypeIP ip = new IP(value);
					filter.eq("d.ip", ip);
				}
				if ("terminalId".equals(name)) {
					filter.like("d.terminalId", value);
				}
				if ("devType".equals(name)) {
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

	private String getI18N(String code){
		if(null==code){
    		return "";
    	}
		return messageSource.getMessage(code, null, code, FishCfg.locale);
	}
	
	public List<DeviceForm> convertDevice(List<IDevice> list) {
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
	
}
