package com.yihuacomputer.fish.web.monitor.controller;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.monitor.business.ICashInit;
import com.yihuacomputer.fish.api.monitor.business.ICashInitService;
import com.yihuacomputer.fish.web.atm.controller.CashInitalController;
import com.yihuacomputer.fish.web.atm.format.CashInitalMsg;

/**
 * 加钞信息
 *
 * @author pengwenchao
 *
 */
@Controller
@ClassNameDescrible(describle="userlog.CashInitController")
@RequestMapping("/monitor/cashinit")
public class CashInitController {
	private Logger logger = LoggerFactory.getLogger(CashInitalController.class);

	@Autowired
	private ICashInitService cashInitService;

	/**
	 * 根据条件得到加钞信息
	 *
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search cashinit : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);

		ModelMap result = new ModelMap();

		IPageResult<ICashInit> pageResult = cashInitService.page(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", CashInitalMsg.convert(pageResult.list()));
		return result;
	}

	private IFilter request2filter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			}

			String value = request.getParameter(name);
			if ("startAmt".equals(name)) {
				filter.ge("amt", Long.valueOf(value));
			} else if ("endAmt".equals(name)) {
				filter.le("amt", Long.valueOf(value));
			} else if ("startDate".equals(name)) {
				filter.ge("date", value + " 00:00:00");
			} else if ("endDate".equals(name)) {
				filter.le("date", value + " 23:59:59");
			} else {
				filter.like(name, value);
			}

		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name)
				|| "sort".equals(name);
	}
}
