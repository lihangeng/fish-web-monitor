package com.yihuacomputer.fish.web.machine.controller;

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
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IP;
import com.yihuacomputer.fish.api.atm.IAtmGroup;
import com.yihuacomputer.fish.api.atm.IAtmGroupService;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.NetType;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.relation.IDeviceGroupRelation;
import com.yihuacomputer.fish.web.machine.form.AtmGroupForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.machine.form.GroupDeviceForm;
import com.yihuacomputer.fish.web.machine.form.ItemForm;

@Controller
@RequestMapping("/machine/atmGroup")
@ClassNameDescrible(describle="userlog.atmGroupController")
public class AtmGroupController {
	private Logger logger = LoggerFactory.getLogger(AtmGroupController.class);

	@Autowired
	private IAtmGroupService atmGroupService;

	/**
	 * 设备接口
	 */
	@Autowired
	private IDeviceService deviceService;
	
	@Autowired
	protected MessageSource messageSource;

	/**
	 * 机构接口
	 */

	@Autowired
	private IDeviceGroupRelation deviceGroupRelation;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search atmGroup : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IAtmGroup> pageResult = atmGroupService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("catalog size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, AtmGroupForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@MethodNameDescrible(describle="userlog.atmGroupController.delete",hasArgs=false,urlArgs=true)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete atmGroup: atmGroup.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IAtmGroup group = null;
			group = atmGroupService.get(id);
			if (group != null) {
				atmGroupService.remove(id);
				result.addAttribute(FishConstant.SUCCESS, true);
			} else {
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delError", null, FishCfg.locale));
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.atmGroupController.add",hasReqBodyParam=true,reqBodyClass=AtmGroupForm.class,bodyProperties="name")
	public @ResponseBody ModelMap add(@RequestBody AtmGroupForm request) {
		logger.info("add atmGroup");
		ModelMap result = new ModelMap();
		try {
			boolean isExist = this.isExistCode(request.getId(), request.getName());
			if (isExist) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("atmGroup.groupDup", null, FishCfg.locale));
			} else {
				IAtmGroup atmGroup = atmGroupService.make();
				request.translate(atmGroup);
				atmGroupService.add(atmGroup);
				result.put(FishConstant.SUCCESS, true);
				result.addAttribute(FishConstant.DATA, new AtmGroupForm(atmGroup));
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.addFail", null, FishCfg.locale)+":"+messageSource.getMessage("commen.error", null, FishCfg.locale));
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.atmGroupController.update",hasReqBodyParam=true,reqBodyClass=AtmGroupForm.class,bodyProperties="name")
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody AtmGroupForm request) {
		logger.info("update atmGroup: atmGroup.id = " + id);
		request.setId(id);
		ModelMap result = new ModelMap();
		try {
			IAtmGroup group = null;
			group = atmGroupService.get(id);
			if (group == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));
			} else {
				boolean isExist = this.isExistCode(id, request.getName());
				if (isExist) {
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("atmGroup.groupDup", null, FishCfg.locale));
				} else {
					IAtmGroup atmGroup = atmGroupService.get(id);
					request.translate(atmGroup);
					atmGroup.setId(id);
					atmGroupService.update(atmGroup);
					result.addAttribute(FishConstant.SUCCESS, true);
					result.addAttribute(FishConstant.DATA, request);
				}
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.updateError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 添加设备至组：
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.atmGroupController.link",hasReqBodyParam=true,reqBodyClass=GroupDeviceForm.class,bodyProperties="deviceId")
	public @ResponseBody
	ModelMap link(@RequestBody GroupDeviceForm request) {
		logger.info(String.format("device %s linked  %s", request.getGroupId(), request.getDeviceId()));
		ModelMap result = new ModelMap();
		deviceGroupRelation.link(atmGroupService.get(request.getGroupId()), deviceService.get(request.getDeviceId()));
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.DATA, request);
		return result;
	}

	/**
	 * 解除关联关系：
	 *
	 * @param personId
	 * @param deviceId
	 * @return
	 */
	@RequestMapping(value = "/unlink", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.atmGroupController.unlink",hasArgs=true,argsContext="deviceId")
	public @ResponseBody
	ModelMap unlink(@RequestParam String groupId, @RequestParam String deviceId) {
		ModelMap result = new ModelMap();
		deviceGroupRelation.unlink(atmGroupService.get(Long.valueOf(groupId)),
				deviceService.get(Long.valueOf(deviceId)));
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}

	/**
	 *
	 * 根据条件得到组外的设备列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/deviceByGroupAdding", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchByGroupEx(@RequestParam int start, @RequestParam int limit, WebRequest request,
			HttpServletRequest req) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = devicefilter(request);
		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		IPageResult<IDevice> pageResult = null;
		if (request.getParameter("groupId") == null || request.getParameter("groupId").isEmpty()) {
			pageResult = deviceService.page(start, limit, filter, String.valueOf(userSession.getOrgId()));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
			result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
			return result;
		}
		Long groupId = Long.valueOf(request.getParameter("groupId"));
		// 获得机构下所有的设备信息
		if (OrganizationType.MAINTAINER.equals(userSession.getOrgType())) {
			pageResult = deviceGroupRelation.pageUnlinkDeviceByGroup(start, limit, atmGroupService.get(groupId),
					filter, String.valueOf(userSession.getOrgId()), false);
		} else {
			pageResult = deviceGroupRelation.pageUnlinkDeviceByGroup(start, limit, atmGroupService.get(groupId),
					filter, String.valueOf(userSession.getOrgId()), true);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}

	/**
	 *
	 * 根据条件得到组内的设备列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@MethodNameDescrible(describle="userlog.atmGroupController.groupDetail",hasArgs=false)
	@RequestMapping(value = "/deviceByGroup", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchByGroup(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
		IFilter filter = devicefilter(request);
		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		IPageResult<IDevice> pageResult = null;
		Long groupId = Long.valueOf(request.getParameter("groupId"));
		if (groupId == 0) {
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("total", 0);
			result.addAttribute(FishConstant.DATA, convert(new ArrayList<IDevice>()));
			return result;
		}
		// 获得机构下所有的设备信息
		if (OrganizationType.MAINTAINER.equals(userSession.getOrgType())) {
			pageResult = deviceGroupRelation.pageDeviceByTypeGroup(start, limit, atmGroupService.get(groupId), filter,
					String.valueOf(userSession.getOrgId()), false);
		} else {
			pageResult = deviceGroupRelation.pageDeviceByTypeGroup(start, limit, atmGroupService.get(groupId), filter,
					String.valueOf(userSession.getOrgId()), true);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}

	private boolean isExistCode(long id, String name) {
		try {
			logger.info("update.atmGroup.id is" + id);
			IAtmGroup atmGroup = atmGroupService.get(name);
			logger.info("get.atmGroup.id is " + atmGroup.getId());
			if (atmGroup.getId() == id) {
				// 找到的Id和传入的Id相等，说明是同一个分组
				return false;
			} else {
				// 说明存在
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

	private IFilter devicefilter(WebRequest request) {
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
			if (name.equals("sort")) {
				continue;
			} else if (name.equals("groupId")) {
				continue;
			} else if ("startCashboxLimit".equals(name)) {
				filter.eq("startCashboxLimit", Integer.valueOf(value));
			} else if ("endCashboxLimit".equals(name)) {
				filter.eq("endCashboxLimit", Integer.valueOf(value));
			} else if ("startInstallDate".equals(name)) {
				filter.eq("startInstallDate", DateUtils.getDate(value));
			} else if ("endInstallDate".equals(name)) {
				filter.eq("endInstallDate", DateUtils.getDate(value));
			} else if ("devType".equals(name)) {
				// 型号
				filter.eq(name, Long.valueOf(value));
			} else if ("organization".equals(name)) {
				// 所属机构
				filter.eq(name, Long.valueOf(value));
			} else if ("devService".equals(name)) {
				// 维护商
				filter.eq(name, Long.valueOf(value));
			} else if ("devCatalogId".equals(name)) {
				// 类型
				filter.eq("devCatalogId", Long.valueOf(value));
			} else if ("devVendorId".equals(name)) {
				// 品牌
				filter.eq("devVendorId", Long.valueOf(value));
			} else if ("ip".equals(name)) {
				ITypeIP ip = new IP(value);
				filter.eq(name, ip);
			} else if ("awayFlag".equals(name)) {
				filter.eq("awayFlag", AwayFlag.getById(Integer.valueOf(value)));
			} else {
				filter.like(name, value);
			}
		}
		return filter;
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
					} else if (name.equals("groupId")) {
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

	@RequestMapping(value = "/combo", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getDeviceGroup(WebRequest request) {
		ModelMap map = new ModelMap();
		Iterable<IAtmGroup> devGroups = atmGroupService.list();
		List<ItemForm> forms = new ArrayList<ItemForm>();
		forms.add(new ItemForm(messageSource.getMessage("commen.all", null, FishCfg.locale), "0"));
		for (IAtmGroup devGroup : devGroups) {
			forms.add(new ItemForm(devGroup.getName(), String.valueOf(devGroup.getId())));
		}

		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute(FishConstant.DATA, forms);
		return map;
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
	
	private String getI18N(String code){
		return messageSource.getMessage(code, null, FishCfg.locale);
	}

}
