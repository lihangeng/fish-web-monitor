package com.yihuacomputer.fish.web.report.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.monitor.business.CardStatus;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.report.device.IRetainCardRpt;
import com.yihuacomputer.fish.api.report.device.IRetainCardRptService;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.api.report.engine.IReportExport;
import com.yihuacomputer.fish.report.engine.ReportParam;
import com.yihuacomputer.fish.web.report.form.ReportTitle;

/**
 * 吞卡明细报表控制器
 *
 * @author huxiaobao
 *
 */

@Controller
@RequestMapping(value = "/report/retainCard")
@ClassNameDescrible(describle="userlog.RetainCardReportController")
public class RetainCardReportController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(RetainCardReportController.class);

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IRetainCardRptService retainCardRptService;

	@Autowired
	private IExportReportService exportReportService;
	
    @Autowired
	private MessageSource messageSource;

	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
    
    
	/**
	 * 吞卡明细报表
	 *
	 * @param request
	 * @param rq
	 * @param response
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchRetainCard(WebRequest request, HttpServletRequest rq, HttpServletResponse response) {
		logger.info("searchRetainCard start");
		IFilter filter = request2filter(request, rq);
		ModelMap result = new ModelMap();

		String resourcePath = rq.getSession().getServletContext().getRealPath("/resources/report/w_retaincard.jasper");
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("title", getEnumI18n(ReportTitle.RetainCard.getText()));

		if (filter.getFilterEntry("startData") != null) {
			parameters.put("startReportDate", DateUtils.getTimestamp((Date) filter.getFilterEntry("startData").getValue()));
		} else {
			parameters.put("startReportDate", "");
		}

		if (filter.getFilterEntry("endData") != null) {
			parameters.put("endReportDate", DateUtils.getTimestamp((Date) filter.getFilterEntry("endData").getValue()));
		} else {
			parameters.put("endReportDate", "");
		}
		
		parameters.put("orgNo", messageSource.getMessage("report.device.orgNo", null, FishCfg.locale));
		parameters.put("orgName", messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
		parameters.put("terminalId", messageSource.getMessage("device.terminalId", null, FishCfg.locale));
		parameters.put("address", messageSource.getMessage("device.devAddress", null, FishCfg.locale));
		parameters.put("dateTime", messageSource.getMessage("report.retainCard.dateTime", null, FishCfg.locale));
		parameters.put("cardNo", messageSource.getMessage("report.retainCard.cardNo", null, FishCfg.locale));
		parameters.put("reason", messageSource.getMessage("report.retainCard.reason", null, FishCfg.locale));

		List<IRetainCardRpt> data = retainCardRptService.listRetainCardDetail(filter);

		String path = this.getReport(parameters, resourcePath, request.getParameter("exportType"),
				data == null ? new ArrayList<IRetainCardRpt>() : data);

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

		if ("pdf".equals(exportType)) {
			reportParam.setPdf(true);
		}
		if ("html".equals(exportType)) {
			reportParam.setHtml(true);
		}
		if ("xls".equals(exportType)) {
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

			if ("endDataTime".equals(name)) {
				filter.eq("endData", DateUtils.getTimestamp(value));

			} else if ("startDataTime".equals(name)) {
				filter.eq("startData", DateUtils.getTimestamp(value));

			} else if ("endDate".equals(name)) {
				filter.eq("endDate", value);

			} else if ("startDate".equals(name)) {
				filter.eq("startDate", value);

			} else if ("endAmt".equals(name)) {
				filter.eq("endAmt", Long.valueOf(value));

			} else if ("startAmt".equals(name)) {
				filter.eq("startAmt", Long.valueOf(value));

			} else if ("devVendorId".equals(name)) {
				filter.eq("devVendorId", Long.valueOf(value));

			} else if ("devTypeId".equals(name)) {
				filter.eq("devTypeId", Long.valueOf(value));

			} else if ("orgId".equals(name)) {
				orgFlag = orgService.get(value).getOrgFlag();

			} else if ("accountNo".equals(name)) {
				filter.eq("accountNo", value);

			} else if ("inOut".equals(name)) {
				filter.eq("awayFlag", AwayFlag.getById(Long.valueOf(value).intValue()));

			} else if ("terminalId".equals(name)) {
				filter.eq("terminalId", value);

			} else if ("transCode".equals(name)) {
				filter.eq("transCode", value);

			} else if ("orgLevel".equals(name)) {
				filter.eq("orgLevel", OrganizationLevel.getById(Integer.valueOf(value)));

			} else if ("endData".equals(name)) {
				filter.eq("endData", DateUtils.getDate(value));

			} else if ("startData".equals(name)) {
				filter.eq("startData", DateUtils.getDate(value));

			} else if ("isDevice".equals(name)) {
				filter.eq("isDevice", value);

			} else if ("devType".equals(name)) {
				filter.eq("devTypeId", value);
			} else if ("status".equals(name)) {
				filter.eq("status", CardStatus.getById(Integer.valueOf(value)));
			}
		}

		filter.eq("orgFlag", orgFlag);
		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
				|| "sort".equals(name);
	}
}
