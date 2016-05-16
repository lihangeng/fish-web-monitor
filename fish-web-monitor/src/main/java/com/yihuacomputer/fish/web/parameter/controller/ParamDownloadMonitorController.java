package com.yihuacomputer.fish.web.parameter.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishAppResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishSearchService;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.api.parameter.ParamDownloadResultForm;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.web.parameter.form.ParamDownloadMonitorForm;
import com.yihuacomputer.fish.web.parameter.form.ParamPublishAppResultForm;

@Controller
@RequestMapping("/parameter/downloadMonior")
public class ParamDownloadMonitorController {
	private Logger logger=LoggerFactory.getLogger(ParamDownloadMonitorController.class);
	
	@Autowired 
	private IParamPublishSearchService paramPublishSearchService;
	
	@Autowired
	private IPersonService personService;
	
	@Autowired
	private IParamPublishResultService paramPublishResultService;
	
	
	@Autowired
	private IParamTemplateService paramtemplateService;
	
	@Autowired
	private MessageSource messageSourceEnum;
	/**
	 * 查询参数下发Job信息
	 * @param start
	 * @param limit
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start,@RequestParam int limit,HttpServletRequest request,WebRequest webRequest){
		logger.info("search parameter download information");
		ModelMap result=new ModelMap();
		IFilter filter=JobFilter(webRequest);
		filter.descOrder("id");
		IPageResult<IParamPublish> pageResult= paramPublishSearchService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, JobToForm(pageResult.list()));
		return result;
	}
	
	private IFilter JobFilter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else if (name.equals("sort")) {
					continue;
				} else if (name.equals("id")) {
					String value = request.getParameter(name);
					filter.eq(name, Long.valueOf(value.trim()));
				} else {
					filter.eq(name, request.getParameter(name));
				}
			}
		}

		return filter;
	}
	
	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}
	
	private List <ParamDownloadMonitorForm> JobToForm(List<IParamPublish> list){
		List<ParamDownloadMonitorForm> result=new ArrayList<ParamDownloadMonitorForm>();
		for(IParamPublish paramPublish :list){
			ParamDownloadMonitorForm pdmf=new ParamDownloadMonitorForm(paramPublish);
			IPerson person = personService.get(String.valueOf(paramPublish.getPublisher()));
			if(null!=person){
				pdmf.setPublisherName(person.getName());
			}
			IParamTemplate template = paramtemplateService.get(paramPublish.getTemplateId());
			if(null!=template){
				pdmf.setTemplateName(template.getName());
			}
			result.add(pdmf);
		}
		return result;
	}
	
	/**
	 * 查询参数下发task信息
	 */
	
	@RequestMapping(value="/task",method=RequestMethod.GET)
	public @ResponseBody ModelMap TaskSearch(@RequestParam int start,@RequestParam int limit,HttpServletRequest request,WebRequest webRequest){
		logger.info("search parameter download task information");
		ModelMap result=new ModelMap();
		String publishId=null;
		if(request.getParameter("publishId") !=null && !request.getParameter("publishId").isEmpty()){
			publishId=request.getParameter("publishId");
		}
		IFilter filter=TaskFilter(webRequest);
		IPageResult<ParamDownloadResultForm> pageResult=paramPublishResultService.getByPublishId(start, limit, filter,publishId);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, pageResult.list());
		return result;
	}

	private IFilter TaskFilter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else if (name.equals("sort")) {
					continue;
				} else if (name.equals("id")) {
					String value = request.getParameter(name);
					filter.eq("ppr.id", Long.valueOf(value.trim()));
				}else if (name.equals("terminalId")) {
					String value = request.getParameter(name).trim();
					filter.like("device.terminalId", value);
				}
			}
		}

		return filter;
	}
	
	@RequestMapping(value="/task/status",method=RequestMethod.GET)
	public @ResponseBody ModelMap searchStatus(HttpServletRequest req,WebRequest webRequest){
		logger.info("search publish result status");
		ModelMap result=new ModelMap();
		if(req.getParameter("publishResultId")!=null){
			String pubId=req.getParameter("publishResultId");
			List<IParamPublishAppResult> list=paramPublishResultService.getStatus(Long.valueOf(pubId));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.TOTAL, list.size());
			result.addAttribute(FishConstant.DATA, statusConvert(list));
		}
		return result;
	}
	
	private List<ParamPublishAppResultForm> statusConvert(List<IParamPublishAppResult> list){
		List<ParamPublishAppResultForm> ppar=new ArrayList<ParamPublishAppResultForm>();
		for(IParamPublishAppResult result:list){
			ParamPublishAppResultForm pparf=new ParamPublishAppResultForm();
			pparf.setAppSystem(result.getAppSystem().getName());
			pparf.setReason(result.getReason());
			if(result.getStatus()!=null){
				pparf.setStatus(getEnumI18n(result.getStatus().getText()));
			}
			ppar.add(pparf);
		}
		return ppar;
	}
	private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText,null,FishCfg.locale);
    }
	
}
