package com.yihuacomputer.fish.web.report.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.monitor.business.IHostRet;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.api.report.engine.IReportExport;
import com.yihuacomputer.fish.api.report.trans.ITransResultCountRpt;
import com.yihuacomputer.fish.api.report.trans.ITransRptService;
import com.yihuacomputer.fish.report.engine.ReportParam;
import com.yihuacomputer.fish.web.report.form.ReportTitle;

/**
 * 交易结果统计报表
 *
 * @author huxiaobao
 *
 */

@Controller
@RequestMapping(value = "/report/transactionResultCount")
@ClassNameDescrible(describle="userlog.TransactionResultCountReportController")
public class TransactionResultCountReportController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(TransactionResultCountReportController.class);

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IExportReportService exportReportService;

	@Autowired
	private ITransRptService transRptService;

	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * 交易结果统计报表
	 *
	 * @param request
	 * @param rq
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.TransactionResultCountReportController.export",hasArgs=false)
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap transactionResultCount(WebRequest request, HttpServletRequest rq) {

		logger.info("transactionResultCount report.");

		IFilter filter = request2filter(request, rq);
		ModelMap result = new ModelMap();
		String resourcePath = rq.getSession().getServletContext()
				.getRealPath("/resources/report/w_trans_Result_count.jasper");
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("title", getEnumI18n(ReportTitle.TransactionResultCount.getText()));
		// parameters.put("reportDate", DateUtils.getTimestamp(new Date()));
		if (filter.getFilterEntry("startData") != null) {
			String startReportDateValue = DateUtils.getDate((Date) filter.getFilterEntry("startData").getValue());
			parameters.put("startReportDate", messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale) + " : " + startReportDateValue);

			// 交易是带时分秒的，而页面是不需要时分秒的
//			filter.entrySet().remove(filter.getFilterEntry("startData"));
		} else {
			parameters.put("startReportDate", "");
		}

		if (filter.getFilterEntry("endData") != null) {
			String endReportDateValue = DateUtils.getDate((Date) filter.getFilterEntry("endData").getValue());
			parameters.put("endReportDate", endReportDateValue);

			// 交易是带时分秒的，而页面是不需要时分秒的
//			filter.entrySet().remove(filter.getFilterEntry("endData"));
		} else {
			parameters.put("endReportDate", "");
		}

		List<ITransResultCountRpt> data = transRptService.listTransResultCount(filter);
		
		transHostRet(data);


		String path = this.getReport(parameters, resourcePath, request.getParameter("exportType"),
				data == null ? new ArrayList<ITransResultCountRpt>() : data);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("path", path);

		return result;
	}

	/**
	 * 生成报表文件，并返回报表文件的路径
	 *
	 * @param parameters
	 * @param resourcePath
	 * @param exportType
	 * @param data
	 * @return
	 */
	private String getReport(Map<String, Object> parameters, String resourcePath, String exportType, List<?> data) {
		ReportParam reportParam = new ReportParam();

		if (exportType.equals("pdf")) {
			reportParam.setPdf(true);
		}
		if (exportType.equals("html")) {
			reportParam.setHtml(true);
		}
		if (exportType.equals("xls")) {
			reportParam.setXls(true);
			reportParam.setSheetNames(new String[] { "" + parameters.get("title") });
		}
		reportParam.setParameters(parameters);
		reportParam.setDataList(data);
		reportParam.setReportModule(resourcePath);

		String reportFilepath = FishCfg.getTempDir() + System.getProperty("file.separator") + "report";
		reportParam.setReportFilepath(reportFilepath);
		IReportExport reportResult = exportReportService.genReport(reportParam);
		String path = null;
		if (reportResult.isSuccess()) {
			if (reportParam.isPdf()) {
				path = reportResult.getPdfFile();
			}
			if (reportParam.isXls()) {
				path = reportResult.getXlsFile();
			}
			if (reportParam.isHtml()) {
				path = reportResult.getHtmlFile();
			}
		}
		return path;
	}

	/**
	 * 查询条件
	 *
	 * @param request
	 * @return
	 */
	private IFilter request2filter(WebRequest request, HttpServletRequest rq) {
		IFilter filter = new Filter();
		UserSession userSession = (UserSession) rq.getSession().getAttribute("SESSION_USER");
		String orgFlag = userSession.getOrgFlag();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			}

			String value = request.getParameter(name);
			if (StringUtils.isEmpty(value)) {
				continue;
			}

			if (name.equals("endDataTime")) {
				filter.eq("endData", DateUtils.getTimestamp(value));

			} else if (name.equals("startDataTime")) {
				filter.eq("startData", DateUtils.getTimestamp(value));

			} else if (name.equals("endDate")) {
				filter.eq("endDate", value);

			} else if (name.equals("startDate")) {
				filter.eq("startDate", value);

			} else if (name.equals("endAmt")) {
				filter.eq("endAmt", Long.valueOf(value));

			} else if (name.equals("startAmt")) {
				filter.eq("startAmt", Long.valueOf(value));

			} else if (name.equals("devVendorId")) {
				filter.eq("devVendorId", Long.valueOf(value));

			} else if (name.equals("devTypeId")) {
				filter.eq("devTypeId", Long.valueOf(value));

			} else if (name.equals("orgId")) {
				orgFlag = orgService.get(value).getOrgFlag();

			} else if (name.equals("accountNo")) {
				filter.eq("accountNo", value);

			} else if (name.equals("inOut")) {
				filter.eq("awayFlag", AwayFlag.getById(Long.valueOf(value).intValue()));

			} else if (name.equals("terminalId")) {
				filter.eq("terminalId", value);

			} else if (name.equals("transCode")) {
				filter.eq("transCode", value);

			} else if ("orgLevel".equals(name)) {
				filter.eq("orgLevel", OrganizationLevel.getById(Integer.valueOf(value)));

			} else if (name.equals("endData")) {
				filter.eq("endData", DateUtils.getDate(value));

			} else if (name.equals("startData")) {
				filter.eq("startData", DateUtils.getDate(value));

			} else if (name.equals("isDevice")) {
				filter.eq("isDevice", value);

			} else if ("devType".equals(name)) {
				filter.eq("devTypeId", value);
			}
		}

		filter.eq("orgFlag", orgFlag);
		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
				|| "sort".equals(name);
	}
	
	
	private List<ITransResultCountRpt> transHostRet(List<ITransResultCountRpt> data){
		
		List<IHostRet> hostRet = transRptService.listHostRetCode();
		Map<String,String> map = new HashMap<String, String>();
		String hostRetCode = "" ;
		String hostExplain = "";
		for(int i = 0; i<hostRet.size(); i++){
			hostRetCode = hostRet.get(i).getCode();
			hostExplain = hostRet.get(i).getName();
			map.put(hostRetCode, hostExplain);
		}
		
		for(int i = 0; i< data.size(); i++){
			String resultCode = data.get(i).getResult();
			String exlpain = map.get(resultCode);
			
			if(exlpain!=null){
				data.get(i).setResult(exlpain);
			}
		}
		return data;
	}
}
