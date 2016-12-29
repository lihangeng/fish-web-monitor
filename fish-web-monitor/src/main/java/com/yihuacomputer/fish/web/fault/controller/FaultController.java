package com.yihuacomputer.fish.web.fault.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.fault.FaultCloseType;
import com.yihuacomputer.fish.api.fault.FaultStatus;
import com.yihuacomputer.fish.api.fault.ICaseFault;
import com.yihuacomputer.fish.api.fault.ICaseFaultService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.fault.form.CaseFaultForm;
import com.yihuacomputer.fish.web.util.ExcelViewUtils;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/case/caseFault")
@ClassNameDescrible(describle="userlog.faultController")
public class FaultController
{

    private final Logger logger = LoggerFactory.getLogger(FaultController.class);

    @Autowired
    private ICaseFaultService service;

    @Autowired
	protected MessageSource messageSource;

    /**
     * 根据条件得到故障列表
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req)
    {
        logger.info(String.format("search caseFault : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        long orgId = userSession.getOrgId();
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext())
        {
            String name = iterator.next();
            if (FishWebUtils.isIgnoreRequestName(name))
            {
                continue;
            }
            else
            {
                if (request.getParameter(name).isEmpty())
                {
                    continue;
                }
                else
                {
                    if ("sort".equals(name))
                    { // 去掉前端页面传来的sort排序字段
                        continue;
                    }
                    else
                    {
                        filter.eq(name, request.getParameter(name));
                    }
                }
            }
        }
        long time1 = System.currentTimeMillis() ;
        IPageResult<ICaseFault> pageResult = service.page(start, limit, filter, orgId);
        long time2 = System.currentTimeMillis() ;
        logger.info(String.format("query fault cost time [%s]ms", (time2-time1)));
        List<ICaseFault> list = pageResult.list();
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", CaseFaultForm.convert(list));
        long time3 = System.currentTimeMillis() ;
        logger.info(String.format("convert fault cost time [%s]ms", (time3-time2)));
        return result;
    }

    /**
     * 根据条件得到故障列表
     *
     * @param faultId
     * @param req
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap searchById(@RequestParam long faultId, HttpServletRequest req)
    {
        logger.info(String.format("search caseFault by Id %s",faultId));
        ModelMap result = new ModelMap();
        ICaseFault caseFault = service.getFault(faultId);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", new CaseFaultForm(caseFault));
        return result;
    }

    /**
     * @param list
     * @return
     */
    public List<CaseFaultForm> convert(List<ICaseFault> list) {
		List<CaseFaultForm> result = new ArrayList<CaseFaultForm>();
		for (ICaseFault item : list) {
			CaseFaultForm cff = new CaseFaultForm();
			cff.setOrg(item.getOrg().getName());
			cff.setTerminalId(item.getTerminalId());
			if (null == item.getDevMod())
            {
                cff.setDevModName("");
            }else{
                cff.setDevModName(getEnumI18n(item.getDevMod().getText()));;
            }
			if (null == item.getFaultClassify())
            {
                cff.setFaultClassify("");;
            }else{
            	cff.setFaultClassify(item.getFaultClassify().getClassifyName());
            }
			cff.setVendorHwCode(item.getVendorHwCode());
			cff.setFaultTime(item.getFaultTime()==null?"":DateUtils.getTimestamp(item.getFaultTime()));
			cff.setClosedTime(item.getClosedTime()==null?"":DateUtils.getTimestamp(item.getClosedTime()));
			cff.setDuration(item.getDuration());
			if (item.getFaultStatus().equals(FaultStatus.OPEN))
            {
                cff.setFaultStatusName(messageSource.getMessage("fault.unClosed", null, FishCfg.locale));
            }else if (item.getFaultStatus().equals(FaultStatus.CLOSED)){
                cff.setFaultStatusName(messageSource.getMessage("fault.closed", null, FishCfg.locale));
            }
			cff.setUpgrade(item.getUpgrade());
			if (FaultCloseType.FORCE.equals(item.getCloseType()))
            {
                cff.setCloseTypeName(messageSource.getMessage("fault.force", null, FishCfg.locale));
            }
            else if (FaultCloseType.NORMAL.equals(item.getCloseType()))
            {
                cff.setCloseTypeName(messageSource.getMessage("fault.normal", null, FishCfg.locale));
            }
            else
            {
            	cff.setCloseTypeName("");;
            }
			cff.setBankPer(listPersonName(item.getBankPerson()));
			cff.setSerPer(listPersonName(item.getServicePerson()));
			result.add(cff);
		}
		return result;
	}
    /**
     * @param request
     * @param req
     * @param response
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
	@MethodNameDescrible(describle="userlog.faultController.export",hasArgs=false)
    public @ResponseBody
    ModelAndView poi(WebRequest request, HttpServletRequest req, HttpServletResponse response)
    {
        logger.info("export caseFault");
        Map<String,Object> map = new HashMap<String,Object>();
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        long orgId = userSession.getOrgId();
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext())
        {
            String name = iterator.next();
            if (FishWebUtils.isIgnoreRequestName(name))
            {
                continue;
            }else{
                if (request.getParameter(name).isEmpty())
                {
                    continue;
                }else{
                    if ("sort".equals(name))
                    { // 去掉前端页面传来的sort排序字段
                        continue;
                    }else{
                        String value = req.getParameter(name);
                        if ("undefined".equals(value))
                        {
                            continue;
                        }else{
                            filter.eq(name, request.getParameter(name));
                        }
                    }
                }
            }
        }
        List<ICaseFault> list = service.list(orgId, filter);
		String theme = String.format("%s",messageSource.getMessage("fault.info", null, FishCfg.locale));
		map.put(ExcelViewUtils.SHEET_NAME, theme);//device.devinfo
		map.put(ExcelViewUtils.TITLE, theme);
		map.put(ExcelViewUtils.FILE_NAME, theme);
		// 获得机构下所有的设备信息
		List<CaseFaultForm> formList = convert(list);
		map.put(ExcelViewUtils.BODY_CONTEXTS, formList);
		ExcelViewUtils excelUtils = new ExcelViewUtils();
		return new ModelAndView(excelUtils,map);
       
    }

    @Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }

    /**
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.faultController.update",hasReqBodyParam=true,reqBodyClass=CaseFaultForm.class,bodyProperties="terminalId")
    public @ResponseBody
    ModelMap update(@PathVariable String id,
            @RequestBody CaseFaultForm request)
    {
        logger.info("close fault: fault.id = " + id);

        ModelMap result = new ModelMap();
        request.setId(Long.parseLong(id));
        try
        {
        	ICaseFault fault = service.getFault(Long.parseLong(id)) ;
        	fault.setCloseType(FaultCloseType.FORCE);
        	service.closeCaseFault(fault);
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute("data", request);
        }
        catch (Exception e)
        {
        	logger.error(String.format("Exception is [%s]", e));
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.updateError", null, FishCfg.locale));
        }
        return result;
    }
    
    
    /**
     * @param list
     * @return
     */
    public String listPersonName(List<IPerson> list){
    	StringBuffer sb = new StringBuffer();
    	for(int i=0;i<list.size();i++){
    		sb.append(list.get(i).getName());
    		if(i!=list.size()-1){
    			sb.append(";");
    		}
    	}
    	return sb.toString();
    }
}
