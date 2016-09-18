package com.yihuacomputer.fish.web.report.controller;

import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceHardwareRptService;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.api.report.engine.IReportExport;
import com.yihuacomputer.fish.report.engine.ReportParam;

@Controller
@RequestMapping(value = "/report")
public class DeviceHardwareReportController {

    private Logger logger = LoggerFactory.getLogger(DeviceHardwareReportController.class);

    @Autowired
    private IDeviceHardwareRptService deviceHardwareRptService;

    @Autowired
    private IExportReportService exportReportService;

    @Autowired
    private IOrganizationService orgService;
    
    
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
    @RequestMapping(value = "/deviceHardware", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchDeviceHardware(WebRequest request, HttpServletRequest rq, HttpServletResponse response) {
        logger.info("searchDeviceHardware start");

        ModelMap result = new ModelMap();

        IFilter filter = request2filter(request, rq);

        String resoutcePath = rq.getSession().getServletContext()
                .getRealPath("/resources/report/w_deviceHardware.jasper");

        String reportFilePath = FishCfg.getTempDir() + System.getProperty("file.separator") + "report";

        ReportParam reportParam = new ReportParam();

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("title", getEnumI18n(ReportTitle.DeviceHardware.getText()));
        parameters.put("reportDate", DateUtils.getTimestamp(new Date()));
        
        parameters.put("orgName", messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
		parameters.put("terminalId", messageSource.getMessage("device.terminalId", null, FishCfg.locale));
		parameters.put("typeName", messageSource.getMessage("device.devType", null, FishCfg.locale));
		parameters.put("memory", messageSource.getMessage("report.devHard.memory", null, FishCfg.locale));
		parameters.put("cpu", messageSource.getMessage("report.devHard.cpu", null, FishCfg.locale));
		parameters.put("hardDisk", messageSource.getMessage("report.devHard.hardDisk", null, FishCfg.locale));
        
        reportParam.setParameters(parameters);

        if ("pdf".equals(request.getParameter("exportType"))) {
            reportParam.setPdf(true);
        }
        if (request.getParameter("exportType").equals("html")) {
            reportParam.setHtml(true);
        }
        if (request.getParameter("exportType").equals("xls")) {
            reportParam.setSheetNames(new String[]{"" + parameters.get("title")});
            reportParam.setXls(true);
        }

        List<IDeviceHardwareRpt> data = deviceHardwareRptService.listDeviceHardwareInfo(filter);

        reportParam.setDataList(data == null ? new ArrayList<IDeviceHardwareRpt>() : data);

        reportParam.setReportModule(resoutcePath);
        reportParam.setReportFilepath(reportFilePath);
        IReportExport reportResult = exportReportService.genReport(reportParam);

        result.addAttribute(FishConstant.SUCCESS, true);
        if (reportParam.isPdf()) {
            result.addAttribute("path", reportResult.getPdfFile());
        }
        if (reportParam.isHtml()) {
            result.addAttribute("path", reportResult.getHtmlFile());
        }
        if (reportParam.isXls()) {
            result.addAttribute("path", reportResult.getXlsFile());
        }
        return result;
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
                } else if (name.equals("sort")) {
                    continue;
                } else if (name.equals("orgId")) {
                    orgFlag = orgService.get(value).getOrgFlag();
                } else if (name.equals("terminalId")) {
                    filter.eq("terminalId", value);
                }
            }
        }
        filter.eq("orgFlag", orgFlag);
        return filter;
    }

    /**
     * 下载文件到浏览器端：
     */
    @RequestMapping(value = "/deviceHardware/downloadFile", method = RequestMethod.GET)
    public void download(@RequestParam String path, @RequestParam ReportTitle reportTitle, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        File file = new File(path);

        String type = path.substring(path.lastIndexOf("."));
        String fileName = getEnumI18n(reportTitle.getText()) + type;

        response.setHeader("Content-Disposition", "attachment; filename=\"" + getFileName(request, fileName) + "\"");
        response.setContentType("application/x-msdownload;charset=UTF-8");
        OutputStream out = null;
        RandomAccessFile randomFile = new RandomAccessFile(file, "r");
        try {
            out = response.getOutputStream();
            int len = 0;
            long contentLength = 0;
            contentLength = contentLength + randomFile.length();
            randomFile.seek(0);
            byte[] cache = new byte[1024];
            while ((len = randomFile.read(cache)) != -1) {
                out.write(cache, 0, len);
                contentLength += len;
            }
        }
        catch (Exception ex) {
        	logger.error(ex.getMessage());
        }
        finally {
            if (out != null) {
                out.close();
            }
            if (randomFile != null) {
                randomFile.close();
            }
        }
    }

    private String getFileName(HttpServletRequest request, String name) throws Exception {
    	if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0||request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") > 0) {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		} else {
			// IE浏览器
			return URLEncoder.encode(name, "UTF-8");
		}
    }

    private boolean isNotFilterName(String name) {
        return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
    }
}
