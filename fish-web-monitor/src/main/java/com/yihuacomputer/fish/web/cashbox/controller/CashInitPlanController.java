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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanInfoService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.cashbox.form.CashInitPlanInfoForm;

/**
 * 加钞计划管理
 * @author GQ
 *
 */
@Controller
@RequestMapping("/cashInitPlan")
@ClassNameDescrible(describle="userlog.DeviceCashInitPlanController")
public class CashInitPlanController {
	private Logger logger = LoggerFactory.getLogger(DeviceCashBoxInfoController.class);

	@Autowired
	private ICashInitPlanInfoService cashInitPlanInfoService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap searchCashInitPlanList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search CashInit Plan Info List");
		ModelMap result = new ModelMap();
		IFilter filter = getCashInitPlanInfoFilter(webRequest);
		if(filter.getValue("org.id")==null){
			UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
			filter.like("org.orgFlag", userSession.getOrgFlag()+"%");
		}
		else{
			String orgId = String.valueOf( filter.getValue("org.id"));
			IOrganization org = orgService.get(orgId);
			filter.like("org.orgFlag", org.getOrgFlag()+"%");
		}
		filter.descOrder("cashInitCode");
		IPageResult<ICashInitPlanInfo> cashInitPlanPageResult = cashInitPlanInfoService.page(start, limit,filter);
		List<CashInitPlanInfoForm> dcbirList = convert(cashInitPlanPageResult.list());
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, cashInitPlanPageResult.getTotal());
		result.put(FishConstant.DATA, dcbirList);
		return result;
	}
	
	@RequestMapping(value="/nextCashInitPlanId",method = RequestMethod.POST)
	public @ResponseBody ModelMap nextCashInitPlanId(HttpServletRequest request, WebRequest webRequest) {
		logger.info("search nextCashInitPlanId");
		ModelMap result = new ModelMap();
		IFilter filter =  getCashInitPlanInfoFilter(webRequest);
		if(filter.getValue("org.id")==null){
			UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
			filter.like("org.orgFlag", userSession.getOrgFlag()+"%");
		}
		else{
			String orgId = String.valueOf( filter.getValue("org.id"));
			IOrganization org = orgService.get(orgId);
			filter.like("org.orgFlag", org.getOrgFlag()+"%");
		}
		long planId = Long.parseLong(request.getParameter("planId"));
		ICashInitPlanInfo cashInitPlanInfo =cashInitPlanInfoService.get(planId);
		filter.gt("cashInitCode", cashInitPlanInfo.getCashInitCode());
		filter.order("cashInitCode");
		List<ICashInitPlanInfo> list =  cashInitPlanInfoService.list(filter);
		if(!list.isEmpty()){
			result.put(FishConstant.SUCCESS, true);
			result.put(FishConstant.DATA, convert(list.get(0)));
		}
		else{
			result.put(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, getI18N("deviceCashInitPlanController.endRecord"));
		}
		return result;
	}
	
	@RequestMapping(value="/prefCashInitPlanId",method = RequestMethod.POST)
	public @ResponseBody ModelMap prefCashInitPlanId(HttpServletRequest request, WebRequest webRequest) {
		logger.info("search nextCashInitPlanId");
		ModelMap result = new ModelMap();
		IFilter filter =  getCashInitPlanInfoFilter(webRequest);
		if(filter.getValue("org.id")==null){
			UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
			filter.like("org.orgFlag", userSession.getOrgFlag()+"%");
		}
		else{
			String orgId = String.valueOf( filter.getValue("org.id"));
			IOrganization org = orgService.get(orgId);
			filter.like("org.orgFlag", org.getOrgFlag()+"%");
		}
		long planId = Long.parseLong(request.getParameter("planId"));
		ICashInitPlanInfo cashInitPlanInfo =cashInitPlanInfoService.get(planId);
		filter.lt("cashInitCode", cashInitPlanInfo.getCashInitCode());
		filter.descOrder("cashInitCode");
		List<ICashInitPlanInfo> list =  cashInitPlanInfoService.list(filter);
		if(!list.isEmpty()){
			result.put(FishConstant.SUCCESS, true);
			result.put(FishConstant.DATA, convert(list.get(0)));
		}
		else{
			result.put(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, getI18N("deviceCashInitPlanController.firstRecord"));
		}
		return result;
	}
	
	private List<CashInitPlanInfoForm> convert(List<ICashInitPlanInfo> list){
		List<CashInitPlanInfoForm> formList = new ArrayList<CashInitPlanInfoForm>();
		for(ICashInitPlanInfo cashInitPlanInfo:list){
			CashInitPlanInfoForm form = new CashInitPlanInfoForm();
			form.setAmt(cashInitPlanInfo.getAmt());
			form.setCashInitCode(cashInitPlanInfo.getCashInitCode());
			form.setDate(cashInitPlanInfo.getDate());
			form.setOrgName(cashInitPlanInfo.getOrg().getName());
			form.setId(cashInitPlanInfo.getId());
			formList.add(form);
		}
		return formList;
	}
	private CashInitPlanInfoForm convert(ICashInitPlanInfo cashInitPlanInfo){
		CashInitPlanInfoForm form = new CashInitPlanInfoForm();
		form.setAmt(cashInitPlanInfo.getAmt());
		form.setCashInitCode(cashInitPlanInfo.getCashInitCode());
		form.setDate(cashInitPlanInfo.getDate());
		form.setOrgName(cashInitPlanInfo.getOrg().getName());
		form.setId(cashInitPlanInfo.getId());
		return form;
	}
	
	private IFilter getCashInitPlanInfoFilter(WebRequest request){
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

			if ("orgId".equals(name)) {
				filter.eq("org.id", Long.parseLong(value));
			} else if ("date".equals(name)) {
				filter.eq("date", Integer.valueOf(value));
			}
		}

		return filter;
	}
	private boolean isNotFilterName(String name) {
		return "devServiceName".equals(name) || "organizationID".equals(name) || "orgName".equals(name)
				|| "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
				|| "sort".equals(name);
	}
	
	private String getI18N(String code){
		return messageSource.getMessage(code,null,FishCfg.locale);
	}
}
