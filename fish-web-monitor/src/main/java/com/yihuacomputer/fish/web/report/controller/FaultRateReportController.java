package com.yihuacomputer.fish.web.report.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.report.base.IEveryMonthFaultCount;
import com.yihuacomputer.fish.api.report.base.IFaultRateReportService;
import com.yihuacomputer.fish.api.report.base.ITransactionMonths;
import com.yihuacomputer.fish.report.base.FaultRateReportService;
import com.yihuacomputer.fish.web.report.form.FaultRateReportForm;
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

	/*@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap searchByBrand(HttpServletRequest req, WebRequest webRequest) {
		logger.info("search trades'fault rate of brand");
		ModelMap result = new ModelMap();
		List<FaultRateReportForm> fault = faultRateReportService.list();
		DecimalFormat df = new DecimalFormat("#");
		for (FaultRateReportForm rate : fault) {
			rate.setRate(df.format((double) rate.getFaultCount() / rate.getTradeCount() * 100));
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, fault.size());
		result.addAttribute(FishConstant.DATA, fault);
		return result;
	}*/

	@RequestMapping(value = "/faultByType", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryFaultByType() {
		logger.info(String.format("search faultByType : queryFaultByType"));
		ModelMap result = new ModelMap();
		List<ITransactionMonths> trans = EntityUtils.convert(faultRateReportService.typeTrans());
		List<IEveryMonthFaultCount> fault = EntityUtils.convert(faultRateReportService.typeFault());
		List<IAtmType> type = atmTypeService.list();
		List<FaultRateReportForm> list=new ArrayList<FaultRateReportForm>();
		DecimalFormat df = new DecimalFormat("#");
		for(ITransactionMonths t:trans){
			FaultRateReportForm form=new FaultRateReportForm();
			form.setName(t.getDevType());
			form.setTransCount(t.getTransCount());
			form.setFaultCount(0);
			list.add(form);
		}
		for(IEveryMonthFaultCount f:fault){
			for(FaultRateReportForm fr:list){
				if(f.getDevType().equals(fr.getName())){
					fr.setFaultCount(f.getFaultCount());
					if(fr.getFaultCount()==0){
						fr.setRate("00");
					}else if(fr.getTransCount()==0){
						fr.setRate("100");
					}else{
						fr.setRate(df.format((double)fr.getFaultCount()/fr.getTransCount()*100));
					}
				}else{
					FaultRateReportForm form=new FaultRateReportForm();
					form.setName(f.getDevType());
					form.setTransCount(0);
					form.setFaultCount(f.getFaultCount());
					form.setRate("100");
					list.add(form);
				}
			}
		}
		for(IAtmType t:type){
			for(FaultRateReportForm fr:list){
				if(!t.getName().equals(fr.getName())){
					FaultRateReportForm form=new FaultRateReportForm();
					form.setName(t.getName());
					form.setTransCount(0);
					form.setFaultCount(0);
					form.setRate("00");
					list.add(form);
				}
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
