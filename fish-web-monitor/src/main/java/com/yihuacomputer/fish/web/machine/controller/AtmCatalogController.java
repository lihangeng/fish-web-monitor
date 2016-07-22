package com.yihuacomputer.fish.web.machine.controller;

import java.util.Iterator;

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
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.web.machine.form.AtmCatalogForm;

@Controller
@RequestMapping("/machine/atmCatalog")
@ClassNameDescrible(describle="userlog.atmCatalogController")
public class AtmCatalogController {
	private Logger logger = LoggerFactory.getLogger(AtmCatalogController.class);

	@Autowired
	private IAtmCatalogService atmCatalogService;
	
	@Autowired
	protected MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search catalog : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IAtmCatalog> pageResult = atmCatalogService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("catalog size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, AtmCatalogForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@MethodNameDescrible(describle="userlog.atmCatalogController.delete",hasArgs=false,urlArgs=true)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete AtmCatalog: atmCatalog.id = " + id);
		ModelMap result = new ModelMap();
		try {
			atmCatalogService.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delError", null, FishCfg.locale));
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody AtmCatalogForm request) {
		logger.info("add atmCatalog");
		ModelMap result = new ModelMap();
		IAtmCatalog catalog = atmCatalogService.make();
		request.translate(catalog);
		atmCatalogService.add(catalog);
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, new AtmCatalogForm(catalog));

		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.atmCatalogController.update",hasArgs=false,urlArgs=true)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody AtmCatalogForm request) {
		logger.info("update AtmCatalog: atmCatalog.id = " + id);
		request.setId(id);
		ModelMap result = new ModelMap();
		IAtmCatalog catalog = atmCatalogService.get(id);
		request.translate(catalog);
		atmCatalogService.update(catalog);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, request);
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/unique")
	public @ResponseBody
	ModelMap unique(@RequestParam String id, @RequestParam String no) {
		logger.info("brand no unique checking...");
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, isExistCode(id, no));
		return result;
	}

	private boolean isExistCode(String id, String no) {
		try {
			logger.info("update.catalog.id is" + id);
			IAtmCatalog catalog = atmCatalogService.get(no);
			logger.info("get.brand.id is " + catalog.getId());
			if (Long.toString(catalog.getId()).equals(id)) {
				// 找到的Id和传入的Id相等，说明是同一个分类
				return false;
			} else {
				// 说明存在
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
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}
}
