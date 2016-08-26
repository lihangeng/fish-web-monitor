package com.yihuacomputer.fish.web.cashbox.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

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

@Controller
@RequestMapping("/cashInitPlan")
@ClassNameDescrible(describle="userlog.DeviceCashInitPlanController")
public class DeviceCashInitPlanController {
	private Logger logger = LoggerFactory.getLogger(DeviceCashBoxInfoController.class);

	@Autowired
	private ICashInitPlanInfoService cashInitPlanInfoService;
	

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachCashBoxInitRuleList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
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
			filter.eq("org.orgFlag", org.getOrgFlag());
		}
		filter.descOrder("cashInitCode");
		IPageResult<ICashInitPlanInfo> cashInitPlanPageResult = cashInitPlanInfoService.page(start, limit,filter);
		List<CashInitPlanInfoForm> dcbirList = convert(cashInitPlanPageResult.list());
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, cashInitPlanPageResult.getTotal());
		result.put(FishConstant.DATA, dcbirList);
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
}
