package com.yihuacomputer.fish.web.parameter.controller;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.parameter.IClassify;
import com.yihuacomputer.fish.api.parameter.IClassifyService;
import com.yihuacomputer.fish.web.parameter.form.ClassifyForm;

@Controller
@RequestMapping("/parameter/classify")
public class ClassifyController {

	private Logger logger = LoggerFactory.getLogger(ClassifyController.class);

	@Autowired
	private IClassifyService classifyService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start,@RequestParam int limit, WebRequest request) {
		logger.info(String.format("search template : start = %s ,limt = %s ",start, limit));
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IClassify> pageResult = classifyService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("classify size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA,ClassifyForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody ClassifyForm request){
		logger.info("add classify");
		ModelMap result=new ModelMap();
		boolean isExist = this.isExistClassifyName(request.getId(), request.getName());
		if(isExist){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "增加失败：分类名称已存在。");
		} else {
			IClassify classify =classifyService.make();
			request.translate(classify);
			classifyService.add(classify);
			result.put(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, new ClassifyForm(classify));
		}
		return result;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete classify: classify.id = " + id);
		ModelMap result = new ModelMap();
		try {
			if(id == 1){
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, "删除失败：默认分类无法删除。");
				return result;
			}
			classifyService.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody ClassifyForm request) {
		logger.info("update classify: classify.id = " + id);
		ModelMap result = new ModelMap();
		IClassify classify = classifyService.get(id);
		request.translate(classify);
		classify.setId(id);
		classifyService.update(classify);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, request);
		return result;
	}

	/**
	 * 判断分类名称是否重复
	 *
	 * @param id
	 *            分类Id
	 * @param name
	 *            分类名称
	 * @return
	 */
	private boolean isExistClassifyName(long id, String name) {
		try {
			logger.info("checkclassifyName" + name);
			IClassify classify = classifyService.get(name.trim());
			if (classify.getId() == id) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
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
				} else {
					if (name.equals("sort")) {
						continue;
					} else {
						filter.like(name, request.getParameter(name));
					}
				}
			}
		}
		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name)|| "limit".equals(name) || "_dc".equals(name);
	}

}
