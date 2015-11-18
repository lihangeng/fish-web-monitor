package com.yihuacomputer.fish.web.machine.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.system.config.IParam;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.web.machine.form.ParamForm;

@Controller
@RequestMapping("/machine/param")
public class ParamController {
	private Logger logger = LoggerFactory.getLogger(ParamController.class);

	@Autowired
	private IParamService paramService;
	@Autowired
	protected MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit,
			WebRequest request) {
		logger.info(String.format("search param : start = %s ,limt = %s ",
				start, limit));
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IParam> pageResult = paramService
				.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		logger.info("param size:" + pageResult.getTotal());
		result.addAttribute("data", convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody ParamForm request) {
		logger.info("update Param: param.id = " + id);
		ModelMap result = new ModelMap();
		IParam param = paramService.get(id);
		try {
			request.translate(param);
			paramService.update(param);
			request.setId(id);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", request);
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("param.updateError", null, FishCfg.locale));
		}

		return result;
	}

	/**
	 * 验证参数是否可以被修改
	 *
	 * @param paramKey
	 * @return
	 */

	@RequestMapping(method = RequestMethod.GET, value = "/unique")
	public @ResponseBody
	ModelMap unique(@RequestParam String paramKey) {
		ModelMap result = new ModelMap();
		IParam param = paramService.getParam(paramKey);
		if (param.getClassify() == 0) {
			result.addAttribute(FishConstant.SUCCESS, false);
		} else {
			result.addAttribute(FishConstant.SUCCESS, true);
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
				} else if (name.equals("sort")) {
					continue;
				}
				
				String value = request.getParameter(name);
				if(value.indexOf("\\")>=0){
					value = value.replace("\\", "\\\\");
				}
				filter.like(name, value);
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name)
				|| "limit".equals(name) || "_dc".equals(name);
	}
	
	
	private  List<ParamForm> convert(List<IParam> list) {
		List<ParamForm> result = new ArrayList<ParamForm>();
		for (IParam item : list) {
			result.add(new ParamForm(item));
		}
		return result;
	}
	
	
	
}
