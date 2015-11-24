package com.yihuacomputer.fish.web.index.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.fault.IFaultStatisticsService;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.web.index.form.ChartForm;

@Controller
@RequestMapping("/index")
public class IndexController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(IndexController.class);

	@Autowired
	private IFaultStatisticsService faultStatisticsService;

	@Autowired
	private IRetaincardService retainCardService;

	@RequestMapping(value = "faultTrendByDay", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap faultTrendByDay(WebRequest wReq, HttpServletRequest req) {
		logger.info("faultTrendByDay...");
		String startDateTime = req.getParameter("startDateTime");
		Date start = null;
		if(startDateTime != null && !"".equals(startDateTime)){
			start = DateUtils.get(startDateTime,DateUtils.STANDARD_DATE);
		}else{
			start = DateUtils.getDate(-7);
		}
		
		String endDateTime = req.getParameter("endDateTime");
		Date end = null;
		if(endDateTime != null && !"".equals(endDateTime)){
			end = DateUtils.get(endDateTime,DateUtils.STANDARD_DATE);
		}else{
			end = new Date();
		}
		List<Object> objects = faultStatisticsService.statisticsFaultTrend(start,end);
		List<ChartForm> forms = new ArrayList<ChartForm>();
		for(Object object : objects){
			Object[]objs = (Object[])object;
			ChartForm form = new ChartForm();
			String month = objs[0].toString().trim();
			Date date = DateUtils.get(month, DateUtils.STANDARD_DATE_SHORT);
			form.setMonth(DateUtils.get(date, DateUtils.STANDARD_DATE));
			form.setData1(objs[1].toString());
			forms.add(form);
		}
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", forms);
		return result;
	}

	@RequestMapping(value = "retainCardByDay", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap retainCardByDay(WebRequest wReq, HttpServletRequest req) {
		logger.info("retainCardByDay...");
		List<Object> objects = retainCardService.statisticsReatainCardTrend(-7);
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
