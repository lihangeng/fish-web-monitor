package com.yihuacomputer.fish.web.machine.controller;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.ThreeTuple;
import com.yihuacomputer.fish.api.fault.IFaultStatisticsService;
import com.yihuacomputer.fish.api.monitor.business.CashSettleInit;
import com.yihuacomputer.fish.api.monitor.business.ICashInitService;
import com.yihuacomputer.fish.api.monitor.business.IRetaincardService;
import com.yihuacomputer.fish.api.monitor.business.ISettlementService;
import com.yihuacomputer.fish.api.monitor.business.ITransType;
import com.yihuacomputer.fish.api.monitor.business.ITransTypeService;
import com.yihuacomputer.fish.api.monitor.business.ITransactionService;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.web.index.form.ChartForm;

@Controller
@RequestMapping("/machine/devicedetailrun")
public class DeviceDetailRunController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(DeviceDetailRunController.class);
	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private ITransTypeService transTypeService;
	@Autowired
	private IRetaincardService retainCardService;
	@Autowired
	private IDayOpenRateService dayOpenRateService;
	@Autowired
	private IFaultStatisticsService faultStatisticsService;
	@Autowired
	private ICashInitService cashInitService;
	@Autowired
	private ISettlementService settlementService;
	
	
	
	/**
	 * 获取设备开机率信息
	 * @param wReq
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "openrate", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap openRateByDev(WebRequest wReq, HttpServletRequest req) {
		logger.info("openRateByDev...");
		List<IDayOpenRate> openRateList = dayOpenRateService.listByDev(req.getParameter("terminalId"));
		List<ChartForm> forms = new ArrayList<ChartForm>();
		for(IDayOpenRate openRate : openRateList){
			ChartForm form = new ChartForm();
			form.setMonth(openRate.getStatDate());
			double healthy=(double)openRate.getHealthyTimeReal();
			int all = openRate.getOpenTimes();
	        DecimalFormat df = new DecimalFormat("#.0");
			String openrate = df.format((all==0?0:healthy/all)*100);
			form.setData1(openrate);
			forms.add(form);
		}
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", forms);
		return result;
	}
	
	/**
	 * 获取设备吞卡信息
	 * @param wReq
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "retainCard", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap retainCardByDev(WebRequest wReq, HttpServletRequest req) {
		logger.info("retainCardByDev...");
		List<Object> objects = retainCardService.statisticsReatainCardTrendByTerminalId(req.getParameter("terminalId"));
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
	
	/**
	 * 获取设备交易信息
	 * @param httpRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value="transType")
	public @ResponseBody ModelMap getTransInfo(HttpServletRequest httpRequest,WebRequest request){
		IFilter filter  = new Filter();
		filter.eq("terminalId", httpRequest.getParameter("terminalId"));
		filter.eq("startDate",DateUtils.getDateShort(DateUtils.getLastWeek()));
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
	
	/**
	 * 获取设备故障信息
	 * @param wReq
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "faultTrend", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap faultTrendByDev(WebRequest wReq, HttpServletRequest req) {
		logger.info("faultTrendByDev...");
		Date start = DateUtils.getLastWeek();
		Date end = new Date();
		List<Object> objects = faultStatisticsService.statisticsFaultTrendByTerminalId(start,end,req.getParameter("terminalId"));
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
	
	
	/**
	 * 获取设备清机加钞信息
	 * @param wReq
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "cashSettleInit", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap cashSettleInit(WebRequest wReq, HttpServletRequest req) {
		logger.info("cashSettleInit...");
		List <CashSettleInit> settleList = settlementService.getCashSettleInitListByDev( req.getParameter("terminalId"));
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", settleList);
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

