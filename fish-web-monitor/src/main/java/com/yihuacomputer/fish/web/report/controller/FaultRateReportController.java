package com.yihuacomputer.fish.web.report.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.parameter.IParamClassify;
import com.yihuacomputer.fish.web.parameter.form.ParamClassifyForm;
import com.yihuacomputer.fish.web.report.form.BrandForm;
import com.yihuacomputer.fish.web.report.form.ModuleForm;
import com.yihuacomputer.fish.web.report.form.TypeForm;

@Controller
@RequestMapping(value = "/report")
public class FaultRateReportController {

	 private Logger logger = LoggerFactory.getLogger(FaultRateReportController.class);

	 @Autowired
	 private MessageSource messageSource;

	 @RequestMapping(value="/faultByBrand",method=RequestMethod.GET)
	 public @ResponseBody ModelMap queryFaultByBrand(){

		 logger.info(String.format("search faultByBrand : queryFaultByBrand"));
		 ModelMap model=new ModelMap();
		 List<BrandForm> data =new ArrayList<BrandForm>();
		 data.add(new BrandForm("YH",100,10000,0.01));
		 data.add(new BrandForm("HW",200,20000,0.01));
		 data.add(new BrandForm("ZX",300,30000,0.01));
		 data.add(new BrandForm("TX",400,40000,0.01));
	        model.put(FishConstant.DATA, data);
	        model.put(FishConstant.SUCCESS, true);
		return model;

	 }

	 @RequestMapping(value="/faultByType",method=RequestMethod.GET)
	 public @ResponseBody ModelMap queryFaultByType(){

		 logger.info(String.format("search faultByType : queryFaultByType"));
		 ModelMap model=new ModelMap();
		 List<TypeForm> data =new ArrayList<TypeForm>();
		 data.add(new TypeForm("cds6040w",400,40000,0.01));
		 data.add(new TypeForm("cds6040t",300,30000,0.01));
		 data.add(new TypeForm("cds6040wt",200,20000,0.01));
		 data.add(new TypeForm("cds6040tw",100,10000,0.01));
	        model.put(FishConstant.DATA, data);
	        model.put(FishConstant.SUCCESS, true);
		return model;

	 }

	 @RequestMapping(value="/faultByModule",method=RequestMethod.GET)
	 public @ResponseBody ModelMap queryFaultByModule(){

		 logger.info(String.format("search faultByModule : queryFaultByModule"));
		 ModelMap model=new ModelMap();
		 List<ModuleForm> data =new ArrayList<ModuleForm>();
		 data.add(new ModuleForm("IDC",300,30000,0.01));
		 data.add(new ModuleForm("SIU",200,20000,0.01));
		 data.add(new ModuleForm("PIN",300,30000,0.01));
		 data.add(new ModuleForm("CIM",200,20000,0.01));
	        model.put(FishConstant.DATA, data);
	        model.put(FishConstant.SUCCESS, true);
		return model;

	 }
//	 public List<ParamClassifyForm> convert(List<IParamClassify> list)
//	private List<FaultByBrandForm> convert(List<FaultByBrandForm> data) {
//       List<FaultByBrandForm> result=new ArrayList<FaultByBrandForm>();
//       result.add(data);
//		return result;
//	}

}
