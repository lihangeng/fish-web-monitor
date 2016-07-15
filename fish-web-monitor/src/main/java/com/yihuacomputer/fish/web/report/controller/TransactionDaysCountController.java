package com.yihuacomputer.fish.web.report.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.index.form.ChartForm;

@Controller
@RequestMapping("/report/transactionDaysCount")
@ClassNameDescrible(describle="userlog.TransactionDaysCountController")
public class TransactionDaysCountController {
	private Logger logger = LoggerFactory.getLogger(TransactionDaysCountController.class);

	@Autowired
	private ITransactionService transactionService;

	@Autowired
	private IOrganizationService orgService;

	@MethodNameDescrible(describle="userlog.TransactionDaysCountController.search",hasArgs=false)
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelMap search(WebRequest wReq, HttpServletRequest req) {
		logger.debug("TransactionDaysCount search");
		IFilter filter = new Filter() ;
		String month = req.getParameter("month") ;
		filter.eq("startDate", Integer.parseInt((month + "-01").replaceAll("-", "")));
        int days = 31 ;
        try {
			days = DateUtils.daysOfMonth(month) ;
		} catch (ParseException e) {
			logger.error(e.getMessage());
		}
        filter.eq("endDate", Integer.parseInt((month +"-" + days).replaceAll("-", "")));
        String orgId = req.getParameter("orgId") ;
        if(orgId==null || "".equals(orgId)){
        	UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        	filter.eq("orgFlag", userSession.getOrgFlag()) ;
        }else{
        	IOrganization org = orgService.get(orgId) ;
        	filter.eq("orgFlag", org.getOrgFlag()) ;
        }
		List<Object> objects = transactionService.statisticsTransTrend(filter);
		List<ChartForm> forms = new ArrayList<ChartForm>();
		for(Object object : objects){
			Object[]objs = (Object[])object;
			ChartForm form = new ChartForm();
			form.setMonth(objs[0].toString());
			form.setData1(objs[1].toString());
			forms.add(form);
		}
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", forms);
		return result;
	}
	
	
	@MethodNameDescrible(describle="userlog.TransactionDaysCountController.searchHour",hasArgs=false)
	@RequestMapping(value="/hour",method = RequestMethod.GET)
	@ResponseBody
	public ModelMap searchHour(WebRequest wReq, HttpServletRequest req) {
		IFilter filter = new Filter() ;
		String day = req.getParameter("day") ;
		filter.eq("startDate", Integer.parseInt(day.replaceAll("-", "")));
        filter.eq("endDate", Integer.parseInt(day.replaceAll("-", "")));
        String orgId = req.getParameter("orgId") ;
        if(orgId==null || "".equals(orgId)){
        	UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        	filter.eq("orgFlag", userSession.getOrgFlag()) ;
        }else{
        	IOrganization org = orgService.get(orgId) ;
        	filter.eq("orgFlag", org.getOrgFlag()) ;
        }
		List<Object> objects = transactionService.statisticsTransHourTrend(filter);
		List<ChartForm> forms = new ArrayList<ChartForm>();
		for(Object object : objects){
			Object[]objs = (Object[])object;
			ChartForm form = new ChartForm();
			form.setMonth(objs[0].toString());
			form.setData1(objs[1].toString());
			forms.add(form);
		}
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", forms);
		return result;
	}

}
