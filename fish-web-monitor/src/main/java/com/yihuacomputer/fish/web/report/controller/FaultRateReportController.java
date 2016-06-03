package com.yihuacomputer.fish.web.report.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmModuleService;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.report.base.FaultRateReport;
import com.yihuacomputer.fish.api.report.base.IFaultRateReportService;

@Controller
@RequestMapping(value = "/report/faultRate")
public class FaultRateReportController {

	private Logger logger = LoggerFactory.getLogger(FaultRateReportController.class);

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IFaultRateReportService faultRateReportService;

	@Autowired
	private IAtmBrandService atmBrandService;

	@Autowired
	private IAtmTypeService atmTypeService;

	@Autowired
	private IAtmModuleService atmModuleService;

	@RequestMapping(value = "/faultByBrand", method = RequestMethod.GET)
	public @ResponseBody ModelMap searchByBrand(HttpServletRequest req, WebRequest webRequest) {
		logger.info(String.format("search faultByBrand : queryFaultByBrand"));
		ModelMap result = new ModelMap();
		String time = req.getParameter("dateTime");
		List<FaultRateReport> list = faultRateReportService.listAllHql(time);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, list.size());
		result.addAttribute(FishConstant.DATA, list);
		return result;
	}

	@RequestMapping(value = "/faultByType", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryFaultByType(HttpServletRequest req, WebRequest request) {
		logger.info(String.format("search faultByType : queryFaultByType"));
		ModelMap result = new ModelMap();
		Long vendorId = Long.parseLong(req.getParameter("vendorId"));
		String time = req.getParameter("dateTime");
		List<FaultRateReport> list = faultRateReportService.listByVendorHql(time, vendorId);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, list.size());
		result.addAttribute(FishConstant.DATA, list);
		return result;
	}

	@RequestMapping(value = "/faultByModule", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryFaultByModule(HttpServletRequest req, WebRequest webRequest) {
		logger.info(String.format("search faultByModule : queryFaultByModule"));
		ModelMap result = new ModelMap();
		long vendorId = Long.parseLong(req.getParameter("vendorId"));
		long devTypeId = Long.parseLong(req.getParameter("devTypeId"));
		String time = req.getParameter("dateTime");
		List<FaultRateReport> list = faultRateReportService.listByDevTypeHql(time, vendorId, devTypeId);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, list.size());
		result.addAttribute(FishConstant.DATA, list);
		return result;
	}

}
