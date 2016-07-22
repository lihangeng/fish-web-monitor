package com.yihuacomputer.fish.web.report.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmModuleService;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.report.base.FaultRateReport;
import com.yihuacomputer.fish.api.report.base.IFaultRateReportService;

@Controller
@RequestMapping(value = "/report/faultRate")
@ClassNameDescrible(describle="userlog.FaultRateReportController")
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

	/**
	 * 查询品牌故障率
	 * @param req
	 * @param webRequest
	 * @return
	 */
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

	/**
	 * 查询相应品牌的型号故障率
	 * @param req
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/faultByType", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryFaultByType(HttpServletRequest req, WebRequest request) {
		logger.info(String.format("search faultByType : queryFaultByType"));
		ModelMap result = new ModelMap();
		String vendor = req.getParameter("vendorId");
		Long vendorId = 1l;
		if(vendor != null){
			vendorId = Long.parseLong(vendor);
			String time = req.getParameter("dateTime");
			List<FaultRateReport> list = faultRateReportService.listByVendorHql(time, vendorId);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.TOTAL, list.size());
			result.addAttribute(FishConstant.DATA, list);
		}
		return result;
	}

	/**
	 * 查询相应型号的模块故障率
	 * @param req
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value = "/faultByModule", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryFaultByModule(HttpServletRequest req, WebRequest webRequest) {
		logger.info(String.format("search faultByModule : queryFaultByModule"));
		ModelMap result = new ModelMap();
		String vendor = req.getParameter("vendorId");
		Long vendorId = 1l;
		if(vendor != null){
			vendorId = Long.parseLong(vendor);
		}
		String devType = req.getParameter("devTypeId");
		long devTypeId = 1l;
		if(devType != null){
			devTypeId = Long.parseLong(devType);
		}
		String time = req.getParameter("dateTime");
		List<FaultRateReport> list = faultRateReportService.listByDevTypeHql(time, vendorId, devTypeId);
		long transCount = 0l;
		FaultRateReport itrade = faultRateReportService.getTradeCount(time,vendorId, devTypeId);
		if(itrade != null){
			transCount = itrade.getTradeCount();
		}
		List<IAtmModule> module = faultRateReportService.getModule(devTypeId);
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		for(FaultRateReport f:list){
			set1.add(f.getName());
		}
		for(IAtmModule m:module){
			set2.add(m.getName());
		}
		set2.removeAll(set1);
		if(set2 != null){
			Iterator<String> it=  set2.iterator();
			while(it.hasNext()){
				FaultRateReport f = new FaultRateReport();
				f.setVendorId(vendorId);
				f.setDevTypeId(devTypeId);
				f.setName(it.next());
				f.setTradeCount(transCount);
				f.setFaultCount(0);
				f.setRate("00.00");
				list.add(f);
			}
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, list.size());
		result.addAttribute(FishConstant.DATA, list);
		return result;
	}
	
}
