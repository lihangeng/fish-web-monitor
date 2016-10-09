package com.yihuacomputer.fish.web.report.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
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
import com.yihuacomputer.fish.web.util.ExcelViewUtils;

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
//        String avgType=request.getParameter("avgType");
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
        //机构类型
        String type = request.getParameter("type");
        //获取要展示开机率的子机构树
        if (type == null || type.isEmpty()) {
        	//查询子节点
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
        //获取开机率的时间条件
        IFilter filter = request2filter(webRequest, "rate.statDate");

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
    ModelAndView deviceImportStat(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {

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

        List<OpenRateForm> data = OpenRateForm.convert(dayOpenRateService.listDev(filter));
        Map<String,Object> map = new HashMap<String,Object>();
		String theme = messageSource.getMessage("openRateReport.device", null, FishCfg.locale);
		map.put(ExcelViewUtils.SHEET_NAME, theme);//device.devinfo
		map.put(ExcelViewUtils.TITLE, theme);
		map.put(ExcelViewUtils.FILE_NAME, theme);
		// 获得机构下所有的设备信息
		map.put(ExcelViewUtils.BODY_CONTEXTS, data);
		ExcelViewUtils excelUtils = new ExcelViewUtils();
		return new ModelAndView(excelUtils,map);
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
    ModelAndView typeImportStat(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {

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
        List<OpenRateForm> data = listType2Form(EntityUtils.<IAtmType> convert(iterable), filter);

        Map<String,Object> map = new HashMap<String,Object>();
		String theme = messageSource.getMessage("openRateReport.devType", null, FishCfg.locale);
		map.put(ExcelViewUtils.SHEET_NAME, theme);//device.devicetype
		map.put(ExcelViewUtils.TITLE, theme);
		map.put(ExcelViewUtils.FILE_NAME, theme);
		// 获得机构下所有的设备信息
		map.put(ExcelViewUtils.BODY_CONTEXTS, data);
		ExcelViewUtils excelUtils = new ExcelViewUtils();
		return new ModelAndView(excelUtils,map);
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
    ModelAndView orgImportStat(WebRequest webRequest, HttpServletRequest request, HttpServletResponse response) {

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
        List<OpenRateForm> data = listOrg2Form(treeFormList, filter);

        Map<String,Object> map = new HashMap<String,Object>();
		String theme = messageSource.getMessage("openRateReport.org", null, FishCfg.locale);
		map.put(ExcelViewUtils.SHEET_NAME, theme);//device.devicetype
		map.put(ExcelViewUtils.TITLE, theme);
		map.put(ExcelViewUtils.FILE_NAME, theme);
		// 获得机构下所有的设备信息
		map.put(ExcelViewUtils.BODY_CONTEXTS, data);
		ExcelViewUtils excelUtils = new ExcelViewUtils();
		return new ModelAndView(excelUtils,map);
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
	private List<OpenRateForm> listOrg2Form(List<OpenRateTreeForm> result, IFilter filter1) {
        List<OpenRateForm> openRateFormList = new ArrayList<OpenRateForm>();
        Set<IFilterEntry> set = filter1.entrySet();
        
        for (OpenRateTreeForm form : result) {
        	IFilter filter = new Filter();
        	if(null!=set.iterator()){
        		Iterator<IFilterEntry> iterator= set.iterator();
            	while(iterator.hasNext()){
            		filter.addFilterEntry(iterator.next());
            	}
        	}
            filter.eq("orgId", form.getId());

        	IOrganization org = orgService.get(form.getId());
        	filter.like("org.orgFlag",org.getOrgFlag());
            List<IDayOpenRate> iDayList = dayOpenRateService.listOrg(filter);

            OpenRateForm openRateForm  = new OpenRateForm();
            if (iDayList == null || iDayList.isEmpty()) {
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
