package com.yihuacomputer.fish.web.machine.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.ThreeTuple;
import com.yihuacomputer.fish.api.monitor.business.ITransType;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;

@Controller
@RequestMapping("/machine/devicedetailrun")
public class DeviceDetailRunController {

	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private ITransTypeService transTypeService;
	
	@RequestMapping(value="transType")
	public @ResponseBody ModelMap getTransInfo(HttpServletRequest httpRequest,WebRequest request){
		IFilter filter  = new Filter();
		filter.eq("terminalId", httpRequest.getParameter("terminalId"));
		filter.eq("startDate",DateUtils.getDateShort(DateUtils.getLastMonth()));
		filter.eq("endDate",DateUtils.getTodayDate());
		
		List<ThreeTuple<String, Integer,Double>> list = transactionService.statisticsTransCountForDevice(filter);
		Map<String,ITransType> transTypeMap = transTypeService.getTransTypeMap();
		List<TransInfoForm> resultList = new ArrayList<TransInfoForm>();
		for(ThreeTuple<String, Integer,Double> threeTuple:list){
			String transCode = threeTuple.frist;
			ITransType transType = transTypeMap.get(threeTuple.frist);
			TransInfoForm transInfoFrom = new TransInfoForm();
			transInfoFrom.setTransName(transType==null?transCode:transType.getTransCode());
			transInfoFrom.setTransCount(threeTuple.second);
			transInfoFrom.setTransAmt(threeTuple.third);
			transInfoFrom.setTransCode(transCode);
			resultList.add(transInfoFrom);
		}
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.DATA, resultList);
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}
}
class TransInfoForm{
	private String transName;
	private int transCount;
	private double transAmt;
	private String transCode;
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	public int getTransCount() {
		return transCount;
	}
	public void setTransCount(int transCount) {
		this.transCount = transCount;
	}
	public double getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(double transAmt) {
		this.transAmt = transAmt;
	}
	public String getTransCode() {
		return transCode;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
}
