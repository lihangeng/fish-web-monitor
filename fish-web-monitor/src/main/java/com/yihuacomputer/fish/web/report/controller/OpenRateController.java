package com.yihuacomputer.fish.web.report.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
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
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.filter.FilterEntry;
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.device.AwayFlag;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRate;
import com.yihuacomputer.fish.api.report.openRate.IDayOpenRateService;
import com.yihuacomputer.fish.machine.service.AtmTypeService;
import com.yihuacomputer.fish.web.report.form.OpenRateForm;
import com.yihuacomputer.fish.web.report.form.OpenRateTreeForm;

/**
 * 开机率
 *
 * @author pengwenchao
 *
 */
@Controller
@RequestMapping("/report/openrate")
@ClassNameDescrible(describle="userlog.OpenRateController")
public class OpenRateController {

    @Autowired
    private IDayOpenRateService dayOpenRateService;

    @Autowired
    private IOrganizationService orgService;

    @Autowired
    private AtmTypeService typeService;
    
    @Autowired
	protected MessageSource messageSource;

    // @Autowired
    // private IDeviceOpenRateService deviceOpenRateService;

    /**
     * 导出excel文件名称 ,response/file目录下
     */
    private String importFileName;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(OpenRateController.class);

    @RequestMapping(value = "deviceOpenRate", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchDevice(@RequestParam int start, @RequestParam int limit, WebRequest webRequest,
            HttpServletRequest request) {
        logger.info(String.format("search search : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();
        String terminate = request.getParameter("terminalId");
        String org = request.getParameter("organization");
        String devCatalogId = request.getParameter("devCatalogId");
        String devVendor=request.getParameter("devVendorId");
        String devType=request.getParameter("devTypeId");
        String awayFlag=request.getParameter("awayFlag");
        String compare=request.getParameter("compare");
        String openRate=request.getParameter("openrate");
        String avgType=request.getParameter("avgType");
        IFilter filter = request2filter(webRequest, "rate.statDate");
        if(null!=terminate&&!terminate.isEmpty()){
        	filter.like("rate.terminalId", terminate+"%");
        }
        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        String organization = String.valueOf(userSession.getOrgId());
        filter.like("org.orgFlag", orgService.get(organization).getOrgFlag());
        if(devCatalogId != null)
        {
        	  filter.eq("info.devType.devCatalog.id", Long.valueOf(devCatalogId));
        }
        if(org != null)
        {
        	  filter.like("info.organization.orgFlag",org);
        }
        if(devVendor != null){
        	filter.eq("info.devType.devVendor.id", Long.valueOf(devVendor));
        }
        if(devType != null){
        	filter.eq("info.devType.id", Long.valueOf(devType));
        }
        if(awayFlag != null&&awayFlag !=""){
            filter.eq("info.awayFlag", AwayFlag.getById(Integer.valueOf(awayFlag)));
            }
        if(openRate != null){
        if(Integer.parseInt(compare) ==1 ){
        filter.gt("(cast(rate.healthyTimeReal as int)*1.00)/(cast(rate.openTimes as int)*1.00)*100", Double.parseDouble(openRate));
        }else if(Integer.parseInt(compare) ==0){
        filter.le("(cast(rate.healthyTimeReal as int)*1.00)/(cast(rate.openTimes as int)*1.00)*100", Double.parseDouble(openRate));	
        }
        }
//        if(Integer.parseInt(avgType) == 0){
//        	String value="(select avg(cast(rate.healthyTimeReal as int)/cast(rate.openTimes as int)*100) form DayOpenRate)";
//        	filter.lt("cast(rate.healthyTimeReal as int)/cast(rate.openTimes as int)*100",value);
//        	
//        }else{
//        	
//        }
        IPageResult<IDayOpenRate> pageResult = dayOpenRateService.pageDev(start, limit, filter);


        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", OpenRateForm.convert(pageResult.list()));
        return result;
    }

    @RequestMapping(value = "typeOpenRate", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchType(@RequestParam int start, @RequestParam int limit, WebRequest webRequest,
            HttpServletRequest request) {
        logger.info(String.format("search search : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();

        IPageResult<IAtmType> pageResult = typeService.page(start, limit, new Filter());

        IFilter filter = request2filter(webRequest, "rate.statDate");
        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        String organization = String.valueOf(userSession.getOrgId());
        filter.like("org.orgFlag", orgService.get(organization).getOrgFlag());
        String org = request.getParameter("orgId");
        if(org != null)
        {
        	  filter.like("info.organization.orgFlag",org);
        }
        
        String awayFlag=request.getParameter("awayFlag");
        if(awayFlag != null&&awayFlag !=""){
        filter.eq("info.awayFlag", AwayFlag.getById(Integer.valueOf(awayFlag)));
        }
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", listType2Form(pageResult.list(), filter));
        return result;
    }

    
    @RequestMapping(value = "orgOpenRate", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchOrg(@RequestParam String node, WebRequest webRequest, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Iterable<IOrganization> childs = null;
        List<OpenRateTreeForm> result = new ArrayList<OpenRateTreeForm>();
        String type = request.getParameter("type");
        if (type == null || type.isEmpty()) {
            childs = orgService.get(node).listChildren();
            for (IOrganization item : childs) {
            	if (item.getOrganizationType().equals(OrganizationType.BANK)) {
                    result.add(new OpenRateTreeForm(item));
				}
            }
        } else if (!"null".equals(type)) {
            OrganizationType orgType = OrganizationType.getById(Integer.valueOf(request.getParameter("type")));
            childs = orgService.get(node).listChildren(orgType);
            for (IOrganization item : childs) {
                result.add(new OpenRateTreeForm(item));
            }
        }
        IFilter filter = request2filter(webRequest, "rate.statDate");
        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        String organization = String.valueOf(userSession.getOrgId());
        filter.like("org.orgFlag", orgService.get(organization).getOrgFlag());
        String org = request.getParameter("orgId");
        if(org != null&&org !="")
        {
        	  filter.like("info.organization.orgFlag",org);
        }
        
        String awayFlag=request.getParameter("awayFlag");
        if(awayFlag != null&&awayFlag !=""){
        filter.eq("info.awayFlag", AwayFlag.getById(Integer.valueOf(awayFlag)));
        }
        model.addAttribute(FishConstant.SUCCESS, true);
        model.addAttribute("data", listOrg2Form(result, filter));
        return model;
    }

    /**
     * 导出设备开机率报表
     * @param webRequest
     * @param request
     * @param response
     */
	@MethodNameDescrible(describle="userlog.OpenRateController.exportDevice",hasArgs=false)
    @RequestMapping(value = "device/importStat", method = RequestMethod.GET)
    public @ResponseBody
    void deviceImportStat(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {

        IFilter filter = request2filter(webRequest, "rate.statDate");

        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        String organization = String.valueOf(userSession.getOrgId());
        filter.like("org.orgFlag", orgService.get(organization).getOrgFlag());

        String terminate = request.getParameter("terminalId");
        String devVendor=request.getParameter("devVendorId");
        String devType=request.getParameter("devTypeId");
        String awayFlag=request.getParameter("awayFlag");
        String compare=request.getParameter("compare");
        String org=request.getParameter("org");
        String openRate=request.getParameter("openRate");
        
        
        if(devVendor != null&&!devVendor.isEmpty()){
        	filter.eq("info.devType.devVendor.id", Long.valueOf(devVendor));
        }

        if(devType != null&&!devType.isEmpty()){
        	filter.eq("info.devType.id", Long.valueOf(devType));
        }
        

        if(awayFlag != null&&awayFlag !=""){
        filter.eq("info.awayFlag", AwayFlag.getById(Integer.valueOf(awayFlag)));
        }
        if(org != null&&!org.isEmpty())
        {
        	  filter.like("info.organization.orgFlag",org);
        }
        
        if(openRate != null&&!openRate.isEmpty() ){
            if(Integer.parseInt(compare) ==1 ){
            filter.gt("(cast(rate.healthyTimeReal as int)*1.00)/(cast(rate.openTimes as int)*1.00)*100", Double.parseDouble(openRate));
            }else if(Integer.parseInt(compare) ==0){
            filter.le("(cast(rate.healthyTimeReal as int)*1.00)/(cast(rate.openTimes as int)*1.00)*100", Double.parseDouble(openRate));	
            }
            }
        
      
        
        if(null!=terminate&&!terminate.isEmpty()){
        	filter.like("rate.terminalId", terminate+"%");
        }

        String devCatalogId = request.getParameter("devCatalogId");

        UserSession srcbUserSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        String srcbOrganization = String.valueOf(srcbUserSession.getOrgId());
        filter.like("org.orgFlag", orgService.get(srcbOrganization).getOrgFlag());

        if(devCatalogId != null&&!devCatalogId.isEmpty())
        {
        	  filter.eq("info.devType.devCatalog.id", Long.valueOf(devCatalogId));
        }

        List<OpenRateForm> date = OpenRateForm.convert(dayOpenRateService.listDev(filter));

        String path = createExls(date, messageSource.getMessage("device.terminalId", null, FishCfg.locale), true);
        try {
            download(path, webRequest, response);
        }
        catch (Exception e) {
        }
    }

	/**
	 * 型号开机率报表导出
	 * @param webRequest
	 * @param request
	 * @param response
	 */
	@MethodNameDescrible(describle="userlog.OpenRateController.exportType",hasArgs=false)
    @RequestMapping(value = "type/importStat", method = RequestMethod.GET)
    public @ResponseBody
    void typeImportStat(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {

        Iterable<IAtmType> iterable = typeService.list();

        IFilter filter = request2filter(webRequest, "rate.statDate");
        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        String organization = String.valueOf(userSession.getOrgId());
        filter.like("org.orgFlag", orgService.get(organization).getOrgFlag());
        String org = request.getParameter("orgId");
        if(org != null&&org !="")
        {
        	  filter.like("info.organization.orgFlag",org);
        }
        
        String awayFlag=request.getParameter("awayFlag");
        if(awayFlag != null&&awayFlag !=""){
        filter.eq("info.awayFlag", AwayFlag.getById(Integer.valueOf(awayFlag)));
        }
        List<OpenRateForm> date = listType2Form(EntityUtils.<IAtmType> convert(iterable), filter);

        String path = createExls(date, messageSource.getMessage("openRateReport.devType", null, FishCfg.locale), false);

        try {
            download(path, webRequest, response);
        }
        catch (Exception e) {
        }
    }

    /**
     * 机构开机率报表导出
     * @param webRequest
     * @param request
     * @param response
     */
    @MethodNameDescrible(describle="userlog.OpenRateController.exportOrg",hasArgs=false)
    @RequestMapping(value = "org/importStat", method = RequestMethod.GET)
    public @ResponseBody
    void orgImportStat(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {

        IFilter orgFilter = new Filter();

        UserSession userSession = (UserSession) request.getSession().getAttribute("SESSION_USER");
        String organization = String.valueOf(userSession.getOrgId());
        orgFilter.like("orgFlag", orgService.get(organization).getOrgFlag());

        orgFilter.eq("organizationType", OrganizationType.BANK);
        Iterable<IOrganization> iterable = orgService.list(orgFilter);

        List<OpenRateTreeForm> treeFormList = OpenRateTreeForm.convert(EntityUtils.<IOrganization> convert(iterable));

        IFilter filter = request2filter(webRequest, "rate.statDate");
        String org = request.getParameter("orgId");
        if(org != null&&org !="")
        {
        	  filter.like("info.organization.orgFlag",org);
        }
        String awayFlag=request.getParameter("awayFlag");
        if(awayFlag != null&&awayFlag !=""){
        filter.eq("info.awayFlag", AwayFlag.getById(Integer.valueOf(awayFlag)));
        }
        List<OpenRateForm> date = listOrg2Form(treeFormList, filter);

        String path = createExls(date, messageSource.getMessage("openRateReport.org", null, FishCfg.locale), false);

        try {
            download(path, webRequest, response);
        }
        catch (Exception e) {
        }
    }

    private void download(String path, WebRequest request, HttpServletResponse response) throws Exception {

        File file = new File(path);

        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + getFileName(request, path.substring(path.lastIndexOf(File.separator)+1))
                        + "\"");
        response.addHeader("Content-Length", "" + file.length());
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

    private String getFileName(WebRequest request, String name) throws Exception {
    	if (request.getHeader("User-Agent").toUpperCase().indexOf("CHROME") > 0||request.getHeader("User-Agent").toUpperCase().indexOf("FIREFOX") > 0) {
			return new String(name.getBytes("UTF-8"), "ISO8859-1");
		} else {
			// IE浏览器
			return URLEncoder.encode(name, "UTF-8");
		}
    }

    private String createExls(List<OpenRateForm> data, String sheetName, boolean isProg) {

    	importFileName = sheetName.substring(0, 2) + messageSource.getMessage("report.openRate.title", null, FishCfg.locale);
        String pathname = FishCfg.getTempDir() + File.separator + importFileName;

        HSSFWorkbook workBook = new HSSFWorkbook();

        HSSFCellStyle bodyCellStyle = workBook.createCellStyle();
        bodyCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        bodyCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyCellStyle.setBottomBorderColor(HSSFColor.BLACK.index);

        bodyCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyCellStyle.setLeftBorderColor(HSSFColor.BLACK.index);

        bodyCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyCellStyle.setRightBorderColor(HSSFColor.BLACK.index);

        bodyCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyCellStyle.setTopBorderColor(HSSFColor.BLACK.index);
        
        bodyCellStyle.setFillForegroundColor(HSSFColor.RED.index);
        
        
        HSSFCellStyle bodyCellStyleRed = workBook.createCellStyle();
        bodyCellStyleRed.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        bodyCellStyleRed.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        bodyCellStyleRed.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        bodyCellStyleRed.setBottomBorderColor(HSSFColor.BLACK.index);

        bodyCellStyleRed.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        bodyCellStyleRed.setLeftBorderColor(HSSFColor.BLACK.index);

        bodyCellStyleRed.setBorderRight(HSSFCellStyle.BORDER_THIN);
        bodyCellStyleRed.setRightBorderColor(HSSFColor.BLACK.index);

        bodyCellStyleRed.setBorderTop(HSSFCellStyle.BORDER_THIN);
        bodyCellStyleRed.setTopBorderColor(HSSFColor.BLACK.index);
        
        bodyCellStyleRed.setFillForegroundColor(HSSFColor.RED.index);
        
        HSSFFont font=workBook.createFont();
    	font.setColor(HSSFColor.RED.index);
    	bodyCellStyleRed.setFont(font);

        HSSFCellStyle titleCellStyle = workBook.createCellStyle();
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleCellStyle.setFillForegroundColor(HSSFColor.AQUA.index);

        titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBottomBorderColor(HSSFColor.BLACK.index);

        titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setLeftBorderColor(HSSFColor.BLACK.index);

        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setRightBorderColor(HSSFColor.BLACK.index);

        titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setTopBorderColor(HSSFColor.BLACK.index);

        HSSFFont titleFont = workBook.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        titleFont.setFontHeight((short) 300);
        titleCellStyle.setFont(titleFont);

        HSSFSheet sheet = workBook.createSheet(sheetName);

        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 700);

        int columnIndex = 0;

        sheet.setColumnWidth(columnIndex++, 5500);
        sheet.setColumnWidth(columnIndex++, 6500);

        if (isProg) {
            sheet.setColumnWidth(columnIndex++, 4500);
            sheet.setColumnWidth(columnIndex++, 4500);
        }
        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 8000);

        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 8000);
        sheet.setColumnWidth(columnIndex++, 3500);

        if (isProg) {
            sheet.setColumnWidth(columnIndex++, 3500);
        }

        columnIndex = 0;

        HSSFCell cell = row.createCell(columnIndex++);
        cell.setCellValue(sheetName);
        cell.setCellStyle(titleCellStyle);
        if(isProg)
        {
        	 cell = row.createCell(columnIndex++);
             cell.setCellValue(messageSource.getMessage("device.devOrg", null, FishCfg.locale));
             cell.setCellStyle(titleCellStyle);

             cell = row.createCell(columnIndex++);
             cell.setCellValue(messageSource.getMessage("openRateReport.devCatalog", null, FishCfg.locale));
             cell.setCellStyle(titleCellStyle);

        }
        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.date", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        /*
         * if (isProg) { cell = row.createCell(columnIndex++);
         * cell.setCellValue("方案开机时间"); cell.setCellStyle(titleCellStyle);
         *
         * cell = row.createCell(columnIndex++); cell.setCellValue("方案关机时间");
         * cell.setCellStyle(titleCellStyle);
         *
         * cell = row.createCell(columnIndex++);
         * cell.setCellValue("方案应开机时长(分钟)"); cell.setCellStyle(titleCellStyle);
         *
         * cell = row.createCell(columnIndex++);
         * cell.setCellValue("方案有效开机时长(分钟)"); cell.setCellStyle(titleCellStyle);
         * }
         */

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.totalTime", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.normalTime", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.managerTime", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.netErrorTime", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.hardErrorTime", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.pErrorTime", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.otherTime", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

        cell = row.createCell(columnIndex++);
        cell.setCellValue(messageSource.getMessage("openRateReport.openRate", null, FishCfg.locale));
        cell.setCellStyle(titleCellStyle);

       /* if (isProg) {
            cell = row.createCell(columnIndex++);
            cell.setCellValue("方案开机率");
            cell.setCellStyle(titleCellStyle);
        }*/

        for (int i = 0; i < data.size(); i++) {
             
            OpenRateForm form = data.get(i);
            
            double openRate=form.getOpenRate();
            double avgOpenRate=form.getAvgOpenRate();


            row = sheet.createRow(i + 1);
            row.setHeight((short) 350);

            columnIndex = 0;

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getTerminalId());
            cell.setCellStyle(bodyCellStyle);

			if (isProg) {
				cell = row.createCell(columnIndex++);
				cell.setCellValue(form.getOrgName());
				cell.setCellStyle(bodyCellStyle);

				cell = row.createCell(columnIndex++);
				cell.setCellValue(form.getDevCatalogName());
				cell.setCellStyle(bodyCellStyle);
			}
            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getStatDate());
            cell.setCellStyle(bodyCellStyle);

            /*
             * if (isProg) { cell = row.createCell(columnIndex++);
             * cell.setCellValue(form.getProgramOpenTime());
             * cell.setCellStyle(bodyCellStyle);
             *
             * cell = row.createCell(columnIndex++);
             * cell.setCellValue(form.getProgramCloseTime());
             * cell.setCellStyle(bodyCellStyle);
             *
             * cell = row.createCell(columnIndex++);
             * cell.setCellValue(form.getProgramTimes());
             * cell.setCellStyle(bodyCellStyle);
             *
             * cell = row.createCell(columnIndex++);
             * cell.setCellValue(form.getProgramTimeReal());
             * cell.setCellStyle(bodyCellStyle); }
             */

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getOpenTimes());
            cell.setCellStyle(bodyCellStyle);

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getHealthyTimeReal());
            cell.setCellStyle(bodyCellStyle);

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getMaintainTimeReal());
            cell.setCellStyle(bodyCellStyle);

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getUnknownTimeReal());
            cell.setCellStyle(bodyCellStyle);

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getFaultTimeReal());
            cell.setCellStyle(bodyCellStyle);

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getAtmpTimeReal());
            cell.setCellStyle(bodyCellStyle);

            cell = row.createCell(columnIndex++);
            cell.setCellValue(form.getStopTimeReal());
            cell.setCellStyle(bodyCellStyle);
            
            if(openRate<avgOpenRate){
            	cell = row.createCell(columnIndex++);
                cell.setCellValue(form.getOpenRate() + "%");
                cell.setCellStyle(bodyCellStyleRed);
            }else{
            	cell = row.createCell(columnIndex++);
                cell.setCellValue(form.getOpenRate() + "%");
                cell.setCellStyle(bodyCellStyle);
            }
            
            

            /*if (isProg) {
                cell = row.createCell(columnIndex++);
                cell.setCellValue(form.getProgramOpenRate() + "%");
                cell.setCellStyle(bodyCellStyle);
            }*/

        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathname);
            workBook.write(fos);
        }
        catch (FileNotFoundException e) {
        }
        catch (IOException e) {
        }
        finally {
            if (fos != null) {
                try {
                    fos.close();
                }
                catch (IOException e) {
                }
            }
        }
        return pathname;
    }

    private IFilter request2filter(WebRequest request, String key) {
        IFilter filter = new Filter();
        String statType = request.getParameter("statType");
        if (statType == null || statType.isEmpty()) {

            filter.eq(key, DateUtils.getDate(new Date()));
        } else if ("1".equals(statType)) {

            filter.like(key, request.getParameter("year"));
        } else if ("2".equals(statType)) {

            filter.like(key, request.getParameter("month"));
        } else if ("3".equals(statType)) {

            filter.eq(key, request.getParameter("day"));
        }
        filter.eq("startType", statType);

        return filter;
    }

    @SuppressWarnings("deprecation")
	private List<OpenRateForm> listType2Form(List<IAtmType> typeList, IFilter filter) {
        List<OpenRateForm> openRateFormList = new ArrayList<OpenRateForm>();
        OpenRateForm openRateForm = null;
        FilterEntry entry = null;
        for (IAtmType form : typeList) {
        	if(entry!=null){
        		filter.entrySet().remove(entry);
        	}
            entry = FilterFactory.eq("typeId", form.getId());
            filter.addFilterEntry(entry);

            List<IDayOpenRate> iDayList = dayOpenRateService.listType(filter);

            if (iDayList == null || iDayList.isEmpty()) {
                openRateForm = new OpenRateForm();
                openRateForm.setOpenTimes("0");
                openRateForm.setHealthyTimeReal("0");
                openRateForm.setStatDate(DateUtils.getDate(new Date()));
                openRateForm.setOpenRate(0.00);
                openRateForm.setTerminalId(form.getName());
                openRateForm.setId(Long.valueOf(form.getId()));
            } else {
                openRateForm = new OpenRateForm(iDayList.get(0));
                openRateForm.setTerminalId(form.getName());
                openRateForm.setId(Long.valueOf(form.getId()));
            }
            openRateFormList.add(openRateForm);
        }
        return openRateFormList;
    }

    @SuppressWarnings("deprecation")
	private List<OpenRateForm> listOrg2Form(List<OpenRateTreeForm> result, IFilter filter) {
        List<OpenRateForm> openRateFormList = new ArrayList<OpenRateForm>();
        OpenRateForm openRateForm = null;
        FilterEntry entry = null;
        for (OpenRateTreeForm form : result) {
        	if(entry!=null){
        		filter.entrySet().remove(entry);
        	}
            entry = FilterFactory.eq("orgId", form.getId());
            filter.addFilterEntry(entry);

            List<IDayOpenRate> iDayList = dayOpenRateService.listOrg(filter);

            if (iDayList == null || iDayList.isEmpty()) {
                openRateForm = new OpenRateForm();
                openRateForm.setOpenTimes("0");
                openRateForm.setHealthyTimeReal("0");
                openRateForm.setStatDate(DateUtils.getDate(new Date()));
                openRateForm.setOpenRate(0.00);
                openRateForm.setTerminalId(form.getText());
                openRateForm.setId(Long.valueOf(form.getId()));
            } else {
                openRateForm = new OpenRateForm(iDayList.get(0));
                openRateForm.setTerminalId(form.getText());
                openRateForm.setId(Long.valueOf(form.getId()));
            }
            openRateFormList.add(openRateForm);
        }
        return openRateFormList;
    }

}
