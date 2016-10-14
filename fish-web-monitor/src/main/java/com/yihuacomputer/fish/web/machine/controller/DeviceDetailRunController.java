package com.yihuacomputer.fish.web.machine.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;

@Controller
@RequestMapping("/machine/devicedetailrun")
public class DeviceDetailRunController {

	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private ITransTypeService transTypeService;
	
	public @ResponseBody ModelMap getInfo(HttpServletRequest httpRequest,WebRequest request){
		IFilter filter  = new Filter();
		filter.eq("terminalId", httpRequest.getParameter("terminalId"));
		filter.eq("startDate",DateUtils.getTodayDate());
		filter.eq("endDate",DateUtils.getDateShort(DateUtils.getLastMonth()));
		List<Object> list = transactionService.statisticsTransCountForDevice(filter);
		transTypeService.list(new Filter());
		return null;
	}
}
