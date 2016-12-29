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
import org.slf4j.LoggerFactory;
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
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountJRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceTypeCountRptService;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.api.report.engine.IReportExport;
import com.yihuacomputer.fish.report.engine.ReportParam;
import com.yihuacomputer.fish.web.report.form.ReportTitle;

/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping(value = "/report")
@ClassNameDescrible(describle="userlog.DeviceTypeCountReportController")
public class DeviceTypeCountReportController {

    private Logger logger = LoggerFactory.getLogger(DeviceTypeCountReportController.class);

    @Autowired
    private IExportReportService exportReportService;

    @Autowired
    private IDeviceTypeCountRptService deviceTypeCountService;

    @Autowired
    private IOrganizationService orgService;

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
     * @param request
     * @param rq
     * @return
     */
    @RequestMapping(value = "/deviceTypeCount", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap deviceTypeCount(WebRequest request, HttpServletRequest rq) {

        logger.info("deviceTypeCount");

        IFilter filter = request2filter(request, rq);
        ModelMap result = new ModelMap();

        List<IDeviceTypeCountRpt> data = deviceTypeCountService.listDeviceTypeCount(filter);

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute(FishConstant.DATA, data);

        return result;
    }
	
    /**
     * @param request
     * @param rq
     * @return
     */
    @RequestMapping(value = "/jdeviceTypeCount", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap deviceTypeCountJReport(WebRequest request, HttpServletRequest rq) {

        logger.info("deviceTypeCount");

        IFilter filter = request2filter(request, rq);
        ModelMap result = new ModelMap();
        String resourcePath = rq.getSession().getServletContext()
                .getRealPath("/resources/report/w_deviceType_count.jasper");
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("title", getEnumI18n(ReportTitle.DeviceTypeCount.getText()));
        parameters.put("reportDate", messageSource.getMessage("runtimeInfo.date", null, FishCfg.locale) + " : " + DateUtils.getDate(new Date()));
        parameters.put("orgName", messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
        parameters.put("vendorName", messageSource.getMessage("report.devTypeCount.vendorName", null, FishCfg.locale));
        parameters.put("type", messageSource.getMessage("report.devTypeCount.type", null, FishCfg.locale));
        parameters.put("countName", messageSource.getMessage("report.devTypeCount.countName", null, FishCfg.locale));
        parameters.put("total", messageSource.getMessage("report.devTypeCount.total", null, FishCfg.locale));
        parameters.put("subTotal", messageSource.getMessage("report.devTypeCount.subTotal", null, FishCfg.locale));

        List<IDeviceTypeCountJRpt> data = deviceTypeCountService.listDeviceTypeCountJReport(filter);

        String path = getReport(parameters, resourcePath, request.getParameter("exportType"),
                data == null ? new ArrayList<IDeviceTypeCountJRpt>() : data);

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute(FishConstant.DATA, path);

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
    private String getReport(Map<String, Object> parameters, String resourcePath, String exportType,
            List<IDeviceTypeCountJRpt> data) {
        ReportParam reportParam = new ReportParam();
        if ("pdf".equals(exportType)) {
            reportParam.setPdf(true);
        }
        if ("html".equals(exportType)) {
            reportParam.setHtml(true);
        }
        if ("xls".equals(exportType)) {
            reportParam.setXls(true);
            reportParam.setSheetNames(new String[]{"" + parameters.get("title")});
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


    private IFilter request2filter(WebRequest request, HttpServletRequest rq) {
        IFilter filter = new Filter();
        UserSession userSession = (UserSession) rq.getSession().getAttribute("SESSION_USER");
        String orgFlag = userSession.getOrgFlag();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (isNotFilterName(name)) {
                continue;
            } else {
                String value = request.getParameter(name);
                if (StringUtils.isEmpty(value)) {
                    continue;
                } else if ("sort".equals(name)) {
                    continue;
                } else if ("orgId".equals(name)) {
                    orgFlag = orgService.get(value).getOrgFlag();
                } else if ("devVendorId".equals(name)) {
                    filter.eq("devVendorId", Long.valueOf(value));
                } else if ("devTypeId".equals(name)) {
                    filter.eq("devTypeId", Long.valueOf(value));
                } else if ("orgLevel".equals(name)) {
                    filter.eq("orgLevel",
                            OrganizationLevel.getById(Integer.valueOf(value)));
                }
            }
        }
        filter.eq("orgFlag", orgFlag);
        return filter;
    }

    private boolean isNotFilterName(String name) {
        return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
    }

}
