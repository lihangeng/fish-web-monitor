package com.yihuacomputer.fish.web.cashbox.controller;

import java.util.ArrayList;
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
import com.yihuacomputer.fish.api.monitor.box.ICashInitRule;
import com.yihuacomputer.fish.api.monitor.box.ICashInitRuleService;
import com.yihuacomputer.fish.web.cashbox.form.CashInitRuleFrom;

/**
 * 加钞规则管理
 * @author GQ
 *
 */
@Controller
@RequestMapping("/cashboxInitRule")
@ClassNameDescrible(describle = "userlog.CashBoxInitRuleController")
public class CashInitRuleController {
	private Logger logger = LoggerFactory.getLogger(DeviceCashBoxInfoController.class);

	@Autowired
	private ICashInitRuleService devBoxInitRuleService;
	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachCashBoxInitRuleList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search CashBox Info List");
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		String ruleName = request.getParameter("name");
		if(null!=ruleName&&!"".equals(ruleName)){
			filter.like("name", "%"+ruleName+"%");
		}
		String startUsingstr = request.getParameter("startUsing");
		if(null!=startUsingstr&&!"".equals(startUsingstr)){
			filter.eq("startUsing", Boolean.parseBoolean(startUsingstr));
		}
		IPageResult<ICashInitRule> deviceBoxInitRulePageResult = devBoxInitRuleService.page(start, limit,filter);
		List<CashInitRuleFrom> dcbirList = convert(deviceBoxInitRulePageResult.list());
		result.put(FishConstant.SUCCESS, true);
		result.put(FishConstant.TOTAL, deviceBoxInitRulePageResult.getTotal());
		result.put(FishConstant.DATA, dcbirList);
		return result;
	}

	/**
	 *
	 * 方法描述 : 根据ID更新设备钞箱信息
	 *
	 * @param guid
	 * @param request
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle = "userlog.CashBoxInitRuleController.update",hasReqBodyParam=true,reqBodyClass=CashInitRuleFrom.class,bodyProperties="name")
	public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody CashInitRuleFrom request) {
		logger.info("update InitRule Info: InitRule.id = " + id);
		request.setId(id);
		ModelMap model = new ModelMap();

		ICashInitRule deviceBoxInitRule = devBoxInitRuleService.get(id);
		deviceBoxInitRule.setStartUsing(request.isStartUsing());
		try {
			devBoxInitRuleService.update(deviceBoxInitRule);
		} catch (Exception e) {
			logger.error(String.format("update error : %s", e.getMessage()));
			model.put(FishConstant.SUCCESS, false);
			model.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
			return model;
		}
		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute(FishConstant.DATA, convert(deviceBoxInitRule));
		return model;
	}

	private List<CashInitRuleFrom> convert(List<ICashInitRule> list) {
		List<CashInitRuleFrom> ruleList = new ArrayList<CashInitRuleFrom>();
		for (ICashInitRule rule : list) {
			CashInitRuleFrom form = new CashInitRuleFrom();
			form.setId(rule.getId());
			form.setName(rule.getName());
			form.setRuleDesc(rule.getRuleDesc());
			form.setStartUsing(rule.isStartUsing() );
			ruleList.add(form);
		}
		return ruleList;
	}

	private CashInitRuleFrom convert(ICashInitRule rule) {
		CashInitRuleFrom form = new CashInitRuleFrom();
		form.setId(rule.getId());
		form.setName(rule.getName());
		form.setRuleDesc(rule.getRuleDesc());
		form.setStartUsing(rule.isStartUsing() );
		return form;
	}
}
