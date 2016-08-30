package com.yihuacomputer.fish.web.cashbox.controller;

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

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.monitor.box.BoxInitRuleType;
import com.yihuacomputer.fish.api.monitor.box.CashInitPlanDeviceInfoForm;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfoService;

@Controller
@RequestMapping("/cashInitPlanDevice")
@ClassNameDescrible(describle = "userlog.DeviceCashInitPlanDetailController")
public class DeviceCashInitPlanDetailController {
	private Logger logger = LoggerFactory.getLogger(DeviceCashInitPlanDetailController.class);

	@Autowired
	private ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService;

	@Autowired
	private ICashInitPlanInfoService cashInitPlanInfoService;

	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private IDeviceBoxInfoService deviceBoxInfoService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachCashInitPlanDeviceInfoList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search CashInit Plan detail Info List");
		ModelMap result = new ModelMap();
		IFilter filter = getCashInitPlanInfoFilter(webRequest);
		List<ICashInitPlanDeviceInfo> cashInitPlanPageResult = cashInitPlanDeviceInfoService.list(filter);
		ICashInitPlanInfo planInfo = cashInitPlanInfoService.get(Long.parseLong(request.getParameter("cashInitPlanInfoId")));
		Map<String, IDeviceBoxInfo> deviceBoxInfoMap = deviceBoxInfoService.getDeviceBoxInfo(planInfo.getOrg().getOrgFlag());
		List<CashInitPlanDeviceInfoForm> dcbirList = convert(cashInitPlanPageResult, deviceBoxInfoMap);
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, dcbirList.size());
		result.put(FishConstant.DATA, dcbirList);
		return result;
	}

	/**
	 * 获取当前可选的设备列表
	 * 
	 * @param limit
	 * @param start
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value = "/selectableDevice", method = RequestMethod.GET)
	public @ResponseBody ModelMap seachSelectableDeviceList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search selectable device Info List");
		ModelMap result = new ModelMap();
		IFilter filter = getCashInitPlanInfoFilter(webRequest);
		ICashInitPlanInfo planInfo = cashInitPlanInfoService.get(Long.parseLong(request.getParameter("cashInitPlanInfoId")));
		List<CashInitPlanDeviceInfoForm> list = null;
		try {
			list = cashInitPlanDeviceInfoService.listSelectAble(planInfo,filter);
		} catch (Exception e) {
			logger.error("load selectable initplandevice failer");
			result.put(FishConstant.SUCCESS, false);
			return result;
		}
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, list.size());
		result.put(FishConstant.DATA, list);
		return result;
	}

	/**
	 * 加钞计划删除加钞设备
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@MethodNameDescrible(describle="userlog.DeviceCashInitPlanDetailController.delete",hasArgs=false,urlArgs=true)
	public ModelMap delete(@PathVariable long id) {
		logger.info(" delete cashInitPlanDeviceInfo with id = " + id);
		ModelMap result = new ModelMap();
		try {
			ICashInitPlanDeviceInfo cashInitPlanDeviceInfo = cashInitPlanDeviceInfoService.get(id);
			if(null!=cashInitPlanDeviceInfo){
				ICashInitPlanInfo cashInitPlanInfo = cashInitPlanDeviceInfo.getCashInitPlanInfo();
				double amt = cashInitPlanInfo.getAmt();
				amt-=cashInitPlanDeviceInfo.getActualAmt();
				cashInitPlanDeviceInfoService.remove(cashInitPlanDeviceInfo);
				cashInitPlanInfo.setAmt(amt);
				cashInitPlanInfoService.update(cashInitPlanInfo);
			}
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			logger.error(ex.getMessage());
		}
		return result;
	}
	/**
	 * 加钞计划中确认添加新的设备
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value = "/addDevice", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.DeviceCashInitPlanDetailController.add",hasArgs=false)
	public @ResponseBody ModelMap addDevice(HttpServletRequest request, WebRequest webRequest) {
		logger.info("addDevice device Info List");
		ModelMap result = new ModelMap();
		String terminalIds = request.getParameter("terminalIds").substring(1);
		long cashInitPlanInfoId = Long.parseLong(request.getParameter("cashInitPlanInfoId"));
		ICashInitPlanInfo planInfo = cashInitPlanInfoService.get(cashInitPlanInfoId);
		List<CashInitPlanDeviceInfoForm> list = null;
		try {
			list = cashInitPlanDeviceInfoService.listSelectAble(planInfo,new Filter());
		} catch (Exception e) {
			logger.error("load selectable initplandevice failer");
			result.put(FishConstant.SUCCESS, false);
			return result;
		}
		String[] terminals = terminalIds.split(",");
		Map<String, String> terminalIdMap = new HashMap<String, String>();
		for (String terminal : terminals) {
			terminalIdMap.put(terminal, terminal);
		}
		double actualAmt = planInfo.getAmt();
		for (CashInitPlanDeviceInfoForm cashInitPlanDeviceInfoForm : list) {
			String terminalId = cashInitPlanDeviceInfoForm.getTerminalId();
			if (null != terminalIdMap.get(terminalId)) {
				ICashInitPlanDeviceInfo cashInfoPlanDeviceInfo = cashInitPlanDeviceInfoService.make();
				cashInfoPlanDeviceInfo.setActualAmt(cashInitPlanDeviceInfoForm.getActualAmt());
				actualAmt += cashInitPlanDeviceInfoForm.getActualAmt();
				cashInfoPlanDeviceInfo.setAddress(cashInitPlanDeviceInfoForm.getAddress());
				cashInfoPlanDeviceInfo.setAdviceAmt(cashInitPlanDeviceInfoForm.getAdviceAmt());
				cashInfoPlanDeviceInfo.setAwayFlag(cashInitPlanDeviceInfoForm.getAwayFlagType());
				cashInfoPlanDeviceInfo.setCashInitPlanInfo(planInfo);
				cashInfoPlanDeviceInfo.setDevType(cashInitPlanDeviceInfoForm.getDevType());
				cashInfoPlanDeviceInfo.setFlag(BoxInitRuleType.getBoxInitRuleType(cashInitPlanDeviceInfoForm.getFlag()));
				cashInfoPlanDeviceInfo.setLastAmt(cashInitPlanDeviceInfoForm.getLastAmt());
				cashInfoPlanDeviceInfo.setLastDate(cashInitPlanDeviceInfoForm.getLastDate());
				cashInfoPlanDeviceInfo.setOrgName(cashInitPlanDeviceInfoForm.getOrgName());
				cashInfoPlanDeviceInfo.setTerminalId(cashInitPlanDeviceInfoForm.getTerminalId());
				cashInfoPlanDeviceInfo = cashInitPlanDeviceInfoService.save(cashInfoPlanDeviceInfo);
			}
		}
		planInfo.setAmt(actualAmt);
		cashInitPlanInfoService.update(planInfo);
		result.put(FishConstant.SUCCESS, true);
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
	@MethodNameDescrible(describle = "userlog.DeviceCashInitPlanDetailController.update", hasArgs = false, urlArgs = true)
	public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody CashInitPlanDeviceInfoForm request) {
		logger.info("update CashBox Limit Info: CashBoxInfo.id = " + id);
		request.setId(id);
		ModelMap model = new ModelMap();
		ICashInitPlanDeviceInfo cashDeviceInfo = cashInitPlanDeviceInfoService.get(id);
		if (cashDeviceInfo == null) {
			model.put(FishConstant.SUCCESS, false);
			model.put(FishConstant.ERROR_MSG, messageSource.getMessage("deviceBoxInfo.updateNotExist", null, FishCfg.locale));

			// 验证失败时，需要把正确(数据库)的数据返回
			model.addAttribute(FishConstant.DATA, request);
			return model;
		}
		cashDeviceInfo.setActualAmt(request.getActualAmt());
		ICashInitPlanInfo initPlan = cashDeviceInfo.getCashInitPlanInfo();
		initPlan.setAmt(initPlan.getAmt() + request.getActualAmt());
		try {
			cashInitPlanDeviceInfoService.update(cashDeviceInfo);
			cashInitPlanInfoService.update(initPlan);
		} catch (Exception e) {
			logger.error(String.format("add error : %s", e.getMessage()));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
			return model;
		}
		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute(FishConstant.DATA, request);
		return model;
	}

	private List<CashInitPlanDeviceInfoForm> convert(List<ICashInitPlanDeviceInfo> list, Map<String, IDeviceBoxInfo> deviceBoxInfoMap) {
		List<CashInitPlanDeviceInfoForm> formList = new ArrayList<CashInitPlanDeviceInfoForm>();
		for (ICashInitPlanDeviceInfo cashInitPlanDevice : list) {
			CashInitPlanDeviceInfoForm form = new CashInitPlanDeviceInfoForm();
			form.setActualAmt(cashInitPlanDevice.getActualAmt());
			form.setAddress(cashInitPlanDevice.getAddress());
			form.setAdviceAmt(cashInitPlanDevice.getAdviceAmt());
			form.setAwayFlag(getI18N(cashInitPlanDevice.getAwayFlag().getText()));
			form.setDevType(cashInitPlanDevice.getDevType());
			form.setFlag(cashInitPlanDevice.getFlag().getNo());
			form.setId(cashInitPlanDevice.getId());
			form.setLastAmt(cashInitPlanDevice.getLastAmt());
			form.setLastDate(cashInitPlanDevice.getLastDate());
			form.setOrgName(cashInitPlanDevice.getOrgName());
			form.setTerminalId(cashInitPlanDevice.getTerminalId());
			IDeviceBoxInfo deviceBoxInfo = deviceBoxInfoMap.get(cashInitPlanDevice.getTerminalId());
			if (null != deviceBoxInfo) {
				form.setMaxAmt(deviceBoxInfo.getDefaultBill());
				form.setBillAmt(deviceBoxInfo.getBillValue());
				form.setCashInAmt(deviceBoxInfo.getCashInValue());
			} else {
				form.setMaxAmt(-1);
			}
			formList.add(form);
		}
		return formList;
	}

	private String getI18N(String code) {
		return messageSource.getMessage(code, null, FishCfg.locale);
	}

	private IFilter getCashInitPlanInfoFilter(WebRequest request) {
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
			if ("cashInitPlanInfoId".equals(name)) {
				filter.eq("planid", Long.parseLong(value));
			} else if ("terminalId".equals(name)) {
				filter.eq("terminalId", value);
			} else if ("devType".equals(name)) {
				filter.eq("devType", value);
			} else if ("orgId".equals(name)) {
				filter.eq("orgId", value);
			} else if ("awayFlag".equals(name)) {
				filter.eq("awayFlag", value);
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "devServiceName".equals(name) || "organizationID".equals(name) || "orgName".equals(name) || "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name) || "sort".equals(name);
	}
}
