package com.yihuacomputer.fish.web.system.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.person.IUserLog;
import com.yihuacomputer.fish.api.person.IUserLogService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.system.form.UserLogForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 *
 * 用户日志管理
 *
 * @project fishbone-web
 * @file UserLogController.java
 * @author shixiaolong
 * @date 2012-5-3
 */
@Controller
@RequestMapping("/person/userLog")
public class UserLogController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(UserLogController.class);

	@Autowired
	private IUserLogService service;
	
	@Autowired
	protected MessageSource messageSource;

	public UserLogController() {
	}

	/**
	 * 根据条件得到用户日志列表
	 * @param start
	 * @param limit
	 * @param request
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search systemOperatorLog : start = %s ,limt = %s ", start, limit));
		IFilter filter = new Filter();
		IPageResult<IUserLog> pageResult = null;
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
						continue;
					}
					else if("operTimeStart".equals(name)) {
						filter.eq(name, request.getParameter(name)+" 00:00:00");
					}
					else if("operTimeEnd".equals(name)) {
						filter.eq(name, request.getParameter(name)+" 23:59:59");
					}
					else {
						filter.eq(name, request.getParameter(name));
					}
				}
			}
		}
		pageResult = service.page(start, limit, filter, String.valueOf(userSession.getOrgId()));
		List<IUserLog> list = pageResult.list();
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, UserLogForm.convert(list));
		return result;
	}
}
