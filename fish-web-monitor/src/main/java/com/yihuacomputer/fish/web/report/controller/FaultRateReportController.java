package com.yihuacomputer.fish.web.report.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.report.base.FaultRateReport;
import com.yihuacomputer.fish.api.report.base.IFaultRateReportService;
import com.yihuacomputer.fish.web.report.form.ModuleForm;

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

	@RequestMapping(value = "/faultByBrand", method = RequestMethod.GET)
	public @ResponseBody ModelMap searchByBrand(HttpServletRequest req, WebRequest webRequest) {
		logger.info(String.format("search faultByBrand : queryFaultByBrand"));
		ModelMap result = new ModelMap();
		List<FaultRateReport> list = faultRateReportService.listByBrand();
		List<IAtmVendor> brand = EntityUtils.convert(atmBrandService.list());
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		DecimalFormat df = new DecimalFormat("#");
		for (FaultRateReport f : list) {
			if (f.getTradeCount() == 0) {
				f.setRate("100");
			} else if (f.getFaultCount() == 0) {
				f.setRate("00");
			} else {
				f.setRate(df.format((double) f.getFaultCount() / f.getTradeCount() * 100));
			}
		}
		for (IAtmVendor t : brand) {
			set1.add(t.getName());
		}
		for (FaultRateReport f : list) {
			set2.add(f.getName());
		}
		set1.removeAll(set2);
		if (set1 != null) {
			Iterator<String> it = set1.iterator();
			if (it.hasNext()) {
				FaultRateReport form = new FaultRateReport();
				form.setName(String.valueOf(it.next()));
				form.setTradeCount(0);
				form.setFaultCount(0);
				form.setRate("00");
				list.add(form);
			}

		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, list.size());
		result.addAttribute(FishConstant.DATA, list);
		return result;
	}

	@RequestMapping(value = "/faultByType", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryFaultByType() {
		logger.info(String.format("search faultByType : queryFaultByType"));
		ModelMap result = new ModelMap();
		List<FaultRateReport> list = faultRateReportService.listByType();
		List<IAtmType> type = atmTypeService.list();
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		DecimalFormat df = new DecimalFormat("#");
		for (FaultRateReport f : list) {
			if (f.getTradeCount() == 0) {
				f.setRate("100");
			} else if (f.getFaultCount() == 0) {
				f.setRate("00");
			} else {
				f.setRate(df.format((double) f.getFaultCount() / f.getTradeCount() * 100));
			}
		}
		for (IAtmType t : type) {
			set1.add(t.getName());
		}
		for (FaultRateReport f : list) {
			set2.add(f.getName());
		}
		set1.removeAll(set2);
		if (set1 != null) {
			Iterator<String> it = set1.iterator();
			if (it.hasNext()) {
				FaultRateReport form = new FaultRateReport();
				form.setName(String.valueOf(it.next()));
				form.setTradeCount(0);
				form.setFaultCount(0);
				form.setRate("00");
				list.add(form);
			}

		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, list.size());
		result.addAttribute(FishConstant.DATA, list);
		return result;
	}

	@RequestMapping(value = "/faultByModule", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryFaultByModule() {

		logger.info(String.format("search faultByModule : queryFaultByModule"));
		ModelMap model = new ModelMap();
		List<ModuleForm> data = new ArrayList<ModuleForm>();
		data.add(new ModuleForm("IDC", 300, 400, 45));
		data.add(new ModuleForm("SIU", 200, 200, 64));
		data.add(new ModuleForm("PIN", 300, 100, 74));
		data.add(new ModuleForm("CIM", 200, 200, 67));
		model.put(FishConstant.DATA, data);
		model.put(FishConstant.SUCCESS, true);
		return model;

	}
	// public List<ParamClassifyForm> convert(List<IParamClassify> list)
	// private List<FaultByBrandForm> convert(List<FaultByBrandForm> data) {
	// List<FaultByBrandForm> result=new ArrayList<FaultByBrandForm>();
	// result.add(data);
	// return result;
	// }

}
