package com.yihuacomputer.fish.web.report.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IFilterEntry;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.fish.api.monitor.xfs.status.DeviceMod;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.report.fault.ICaseStatisticsRptService;
import com.yihuacomputer.fish.web.report.form.CaseStatisticsForm;

@Controller
@RequestMapping("/report/caseStatisticsReport")
@ClassNameDescrible(describle="userlog.CaseStatisticsReportController")
public class CaseStatisticsReportController {

	@Autowired
	private ICaseStatisticsRptService caseStatisticsRptService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelMap search(WebRequest wReq, HttpServletRequest req) {
		IFilter filter = request2filter(wReq, req) ;
		IFilterEntry rank = filter.getFilterEntry("rank");
		List<Object> objects = caseStatisticsRptService.caseStatisticsRank(filter);
		List<CaseStatisticsForm> forms = new ArrayList<CaseStatisticsForm>();
		int rankCount = Integer.parseInt(rank.getValue().toString()) ;
		for(int i=0;i<objects.size();i++){
			if(i>=rankCount){
				break ;
			}
			Object object = objects.get(i) ;
			Object[]objs = (Object[])object;
			CaseStatisticsForm form = new CaseStatisticsForm();
			if(objs[0] instanceof DeviceMod){
				form.setAngle(messageSource.getMessage(((DeviceMod)objs[0]).getText(), null, FishCfg.locale));
			}else{
				form.setAngle(objs[0].toString());
			}
			form.setTotal(objs[1].toString());
			forms.add(form);
		}
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", forms);
		return result;
	}

	/**
	 * 查询条件
	 *
	 * @param request
	 * @return
	 */
	private IFilter request2filter(WebRequest request, HttpServletRequest rq) {
		IFilter filter = new Filter();
		UserSession userSession = (UserSession) rq.getSession().getAttribute("SESSION_USER");
		String orgFlag = userSession.getOrgFlag();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			String value = request.getParameter(name);
			if (StringUtils.isEmpty(value)) {
				continue;
			}

			if (name.equals("startDateTime")) {
				filter.eq("startDate", Integer.parseInt((request.getParameter("startDateTime")).replaceAll("-", ""))) ;

			}else if (name.equals("endDateTime")) {
				filter.eq("endDate", Integer.parseInt((request.getParameter("endDateTime")).replaceAll("-", ""))) ;

			}else if (name.equals("angle")) {
				filter.eq("angle", value);

			} else if (name.equals("rank")) {
				filter.eq("rank", value);

			}
		}

		filter.eq("orgFlag", orgFlag);
		return filter;
	}

}
