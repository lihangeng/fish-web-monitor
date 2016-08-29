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
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfo;
import com.yihuacomputer.fish.api.monitor.box.ICashInitPlanDeviceInfoService;
import com.yihuacomputer.fish.api.monitor.box.IDeviceBoxInfo;
import com.yihuacomputer.fish.web.cashbox.form.CashInitPlanDeviceInfoForm;

@Controller
@RequestMapping("/cashInitPlanDevice")
@ClassNameDescrible(describle="userlog.DeviceCashInitPlanDetailController")
public class DeviceCashInitPlanDetailController {
	private Logger logger = LoggerFactory.getLogger(DeviceCashInitPlanDetailController.class);


	@Autowired
	private ICashInitPlanDeviceInfoService cashInitPlanDeviceInfoService;
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachCashBoxInitRuleList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search CashInit Plan detail Info List");
		ModelMap result = new ModelMap();
		IFilter filter = getCashInitPlanInfoFilter(webRequest);
		List<Object> cashInitPlanPageResult = cashInitPlanDeviceInfoService.listPage(filter);
		List<CashInitPlanDeviceInfoForm> dcbirList = convert(cashInitPlanPageResult);
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, dcbirList.size());
		result.put(FishConstant.DATA, dcbirList);
		return result;
	}
	private List<CashInitPlanDeviceInfoForm> convert(List<Object> list){
		List<CashInitPlanDeviceInfoForm> formList = new ArrayList<CashInitPlanDeviceInfoForm>();
		for(Object obj:list){
			Object[] objs = (Object[]) obj;
			ICashInitPlanDeviceInfo cashInitPlanDevice = (ICashInitPlanDeviceInfo)objs[0];
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
			IDeviceBoxInfo deviceBoxInfo = null;
			if(null!=objs[1]){
				deviceBoxInfo = (IDeviceBoxInfo)objs[1];
				form.setMaxAmt(deviceBoxInfo.getDefaultBill());
			}
			else{
				form.setMaxAmt(-1);
			}
			formList.add(form);
		}
		return formList;
	}
	
	private String getI18N(String code){
		return messageSource.getMessage(code, null, FishCfg.locale);
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

			if ("cashInitPlanInfoId".equals(name)) {
				filter.eq("cashInitPlanInfo.id", Long.parseLong(value));
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
