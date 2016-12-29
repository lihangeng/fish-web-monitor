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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
import com.yihuacomputer.fish.api.parameter.FileFormat;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IAppSystemService;
import com.yihuacomputer.fish.web.parameter.form.AppSystemForm;


/**
 * @author YiHua
 *
 */
@Controller
@RequestMapping("/parameter/appSystem")
@ClassNameDescrible(describle="userlog.AppSystemController")
public class AppSystemController {
	private Logger logger=LoggerFactory.getLogger(AppSystemController.class);

	@Autowired
	private IAppSystemService appSystemService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * @param start
	 * @param limit
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start,@RequestParam int limit,HttpServletRequest request,WebRequest webRequest){
		logger.info("search appSystem");
		ModelMap result=new ModelMap();
		IFilter filter = request2filter(webRequest);
		IPageResult<IAppSystem> pageResult=appSystemService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, toForm(pageResult.list()));
		return result;
	}

	/**
	 * 数据格式转换
	 * @param appSystem
	 * @return
	 */
	private List<AppSystemForm> toForm(List<IAppSystem> appSystem){
			List<AppSystemForm> lists=new ArrayList<AppSystemForm>();
				for(IAppSystem app:appSystem){
					lists.add(new AppSystemForm(app));
				}

			return lists;
	}

	/**
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.AppSystemController.update",hasReqBodyParam=true,reqBodyClass=AppSystemForm.class,bodyProperties="name")
	public @ResponseBody
	ModelMap update(@PathVariable long id,@RequestBody AppSystemForm request){
		logger.info("update appSystem:appSystem.id="+id);
		request.setId(id);
		ModelMap result=new ModelMap();
		IAppSystem appSystem=appSystemService.get(id);
		if(appSystem==null){
			result.addAttribute(FishConstant.SUCCESS,false);
			result.addAttribute(FishConstant.ERROR_MSG,messageSource.getMessage("parameter.updateNotExist", null, FishCfg.locale));
		}else{
				request.translate(appSystem);
				appSystemService.update(appSystem);
				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute(FishConstant.DATA, request);
			}
		return result;
	}


	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else if ("sort".equals(name)) {
					continue;
				} else if ("name".equals(name)) {
					String value = request.getParameter(name);
					filter.like(name, value.trim());
				} else if ("configName".equals(name)) {
					String value = request.getParameter(name);
					filter.like(name, value.trim());
				}else if ("configForm".equals(name)) {
					String value = request.getParameter(name);
					filter.eq(name, FileFormat.getById(Integer.parseInt(value)));
				}else {
					filter.like(name, request.getParameter(name));
				}
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}



}
