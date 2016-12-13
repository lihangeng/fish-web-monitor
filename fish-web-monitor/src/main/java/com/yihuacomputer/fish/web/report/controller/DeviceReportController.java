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
import java.util.Map;

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
import com.yihuacomputer.fish.api.report.device.IDeviceRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceRptService;
import com.yihuacomputer.fish.api.report.engine.IExportReportService;
import com.yihuacomputer.fish.api.report.engine.IReportExport;
import com.yihuacomputer.fish.report.engine.ReportParam;
import com.yihuacomputer.fish.web.report.form.ReportTitle;

@Controller
@RequestMapping(value = "/report")
public class DeviceReportController {

    private Logger logger = LoggerFactory.getLogger(DeviceReportController.class);

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private IDeviceRptService deviceRptService;

    @Autowired
    private IExportReportService exportReportService;

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

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap deviceTypeCount(WebRequest request, HttpServletRequest rq) {

        logger.info("deviceTypeCount");

        IFilter filter = request2filter(request, rq);
        ModelMap result = new ModelMap();
        String resourcePath = rq.getSession().getServletContext().getRealPath("/resources/report/w_device.jasper");
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("title", getEnumI18n(ReportTitle.Device.getText()));
        parameters.put("reportDate", DateUtils.getTimestamp(new Date()));
        
        
        parameters.put("serial", messageSource.getMessage("report.device.serial", null, FishCfg.locale));
        parameters.put("orgNo", messageSource.getMessage("report.device.orgNo", null, FishCfg.locale));
        parameters.put("orgName", messageSource.getMessage("runtimeInfo.orgName", null, FishCfg.locale));
        parameters.put("terminalId", messageSource.getMessage("device.terminalId", null, FishCfg.locale));
		parameters.put("vendorName", messageSource.getMessage("report.devTypeCount.vendorName", null, FishCfg.locale));
		parameters.put("typeName", messageSource.getMessage("device.devType", null, FishCfg.locale));
		
		
		parameters.put("IP", messageSource.getMessage("report.device.IP", null, FishCfg.locale));
		parameters.put("mantainOrg", messageSource.getMessage("device.devSer", null, FishCfg.locale));
		parameters.put("awayFlag", messageSource.getMessage("device.devInsideOutside", null, FishCfg.locale));
		parameters.put("setupType", messageSource.getMessage("report.devBoxDetail.setupType", null, FishCfg.locale));
		parameters.put("address", messageSource.getMessage("report.device.address", null, FishCfg.locale));

        List<IDeviceRpt> data = deviceRptService.listDevice(filter);

        String path = getReport(parameters, resourcePath, request.getParameter("exportType"),
                data == null ? new ArrayList<IDeviceRpt>() : data);

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
    private String getReport(Map<String, Object> parameters, String resourcePath, String exportType, List<IDeviceRpt> data) {
        ReportParam reportParam = new ReportParam();
        if (exportType.equals("pdf")) {
            reportParam.setPdf(true);
        }
        if (exportType.equals("html")) {
            reportParam.setHtml(true);
        }
        if (exportType.equals("xls")) {
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

    /**
     * 下载文件到浏览器端：
     */
    @RequestMapping(value = "/device/downloadFile", method = RequestMethod.GET)
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
            out.close();
            randomFile.close();
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
                } else if (name.equals("orgId")) {
                    orgFlag = orgService.get(value).getOrgFlag();
                }
            }
        }
        filter.eq("orgFlag", orgFlag);
        return filter;
    }

    private boolean isNotFilterName(String name) {
        return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
    }

    public static void main(String[] args) {
        System.out.println("10".concat("%"));
    }
}
