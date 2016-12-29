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
import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRpt;
import com.yihuacomputer.fish.api.report.device.IDeviceBoxDetailRptService;
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
public class DeviceBoxDetailReportController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(DeviceBoxDetailReportController.class);

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private IDeviceBoxDetailRptService deviceBoxDetailRptService;

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

    /**
     * @param request
     * @param req
     * @return
     */
    @RequestMapping(value = "/deviceBox", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchDeviceBoxDetail(WebRequest request, HttpServletRequest req) {

        logger.info("searchDeviceBoxDetail");

        ModelMap result = new ModelMap();
        IFilter filter = request2filter(request, req);
        String resourcePath = req.getSession().getServletContext()
                .getRealPath("/resources/report/w_deviceBoxDetail.jasper");
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("title", getEnumI18n(ReportTitle.DeviceBox.getText()));
        parameters.put("reportDate", DateUtils.getTimestamp(new Date()));
        parameters.put("orgName",getEnumI18n("runtimeInfo.orgName"));
		parameters.put("terminalId", getEnumI18n("device.terminalId"));
		
		
		parameters.put("vendorName", getEnumI18n("report.devTypeCount.vendorName"));
		parameters.put("typeName", getEnumI18n("device.devType"));
		parameters.put("setupType", getEnumI18n("report.devBoxDetail.setupType"));
		parameters.put("termStatus", getEnumI18n("report.devBoxDetail.termStatus"));
		parameters.put("boxLeft", getEnumI18n("report.devBoxDetail.boxLeft"));
        

        List<IDeviceBoxDetailRpt> data = convertI18N(deviceBoxDetailRptService.listDeviceBoxDetail(filter));

        String path = getReport(parameters, resourcePath, request.getParameter("exportType"),
                data == null ? new ArrayList<IDeviceBoxDetailRpt>() : data);

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("path", path);

        return result;
    }
    
    private List<IDeviceBoxDetailRpt> convertI18N(List<IDeviceBoxDetailRpt> datas){
    	List<IDeviceBoxDetailRpt> list = new ArrayList<IDeviceBoxDetailRpt>();
    	for(IDeviceBoxDetailRpt deviceBoxDetail:datas){
    		String setupType = deviceBoxDetail.getSetupType();
			String status = deviceBoxDetail.getStatus();
	    	 if ("WEAR_WALL".equals(setupType)) {
	             deviceBoxDetail.setSetupType(getEnumI18n("report.deviceBoxDetail.wareWall"));
	         }
	         if ("LOBBY".equals(setupType)) {
	             deviceBoxDetail.setSetupType(getEnumI18n("report.deviceBoxDetail.lobby"));
	         }
	         if ("OPEN".equals(status)) {
	             deviceBoxDetail.setStatus(getEnumI18n("report.deviceBoxDetail.opening"));
	         }
	         if ("DISABLED".equals(status)) {
	             deviceBoxDetail.setStatus(getEnumI18n("report.deviceBoxDetail.disabled"));
	         }
	         if ("UNOPEN".equals(status)) {
	             deviceBoxDetail.setStatus(getEnumI18n("report.deviceBoxDetail.unopen"));
	         }
	         if ("SCRAPPED".equals(status)) {
	             deviceBoxDetail.setStatus(getEnumI18n("report.deviceBoxDetail.scrapped"));
	         }
	         list.add(deviceBoxDetail);
    	}
    	return list;
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
            List<IDeviceBoxDetailRpt> data) {
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

    /**
     * 下载文件到浏览器端：
     * @param path
     * @param reportTitle
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/deviceBox/downloadFile", method = RequestMethod.GET)
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
        	logger.error(String.format("[%s]", ex));
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

    private IFilter request2filter(WebRequest request, HttpServletRequest req) {
        IFilter filter = new Filter();
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
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
                } else if ("orgId".equals(name)) {
                    orgFlag = orgService.get(value).getOrgFlag();
                } else if ("terminalId".equals(name)) {
                    filter.eq("terminalId", value);
                } else if ("devVendorId".equals(name)) {
                    filter.eq("devVendorId", Long.valueOf(value));
                } else if ("devTypeId".equals(name)) {
                    filter.eq("devTypeId", Long.valueOf(value));
                } else if ("deviceStatusId".equals(name)) {
                    filter.eq("deviceStatusId", Long.valueOf((value)));
                } else if ("setupId".equals(name)) {
                    filter.eq("setupId", Long.valueOf(value));
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
