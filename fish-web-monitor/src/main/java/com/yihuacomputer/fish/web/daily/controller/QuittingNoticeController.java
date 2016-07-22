package com.yihuacomputer.fish.web.daily.controller;

import java.util.Date;
import java.util.Iterator;

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
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.DevStatus;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.device.StopType;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.monitor.xfs.IXfsService;
import com.yihuacomputer.fish.api.monitor.xfs.status.IXfsStatus;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNotice;
import com.yihuacomputer.fish.api.quittingNotice.IQuittingNoticeService;
import com.yihuacomputer.fish.web.daily.form.QuittingNoticeForm;

@Controller
@RequestMapping("/machine/quittingNotice")
@ClassNameDescrible(describle="userlog.quittingNoticeController")
public class QuittingNoticeController {
	private Logger logger = LoggerFactory.getLogger(QuittingNoticeController.class);

	@Autowired
	private IQuittingNoticeService quittingNoticeService;

	/**
	 * 设备接口
	 */
	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IXfsService xfsService;

	@Autowired
	private ICollectService collectService;

	@Autowired
	private IRunInfoService runInfoService;

	@Autowired
	protected MessageSource messageSource;

	/**
	 * 增加一条设备报停记录：
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.quittingNoticeController.add",hasArgs=false)
	public @ResponseBody
	ModelMap add(@RequestBody QuittingNoticeForm form) {
		logger.info("add QuittingNotice");
		ModelMap result = new ModelMap();
		try {
			IDevice device = deviceService.get(form.getDeviceCode());

			if (device == null) {
				result.put(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.addFail.noDevice", null, FishCfg.locale));
				return result;
			}
			if(device.getStatus().equals(DevStatus.UNOPEN))
			{
				result.put(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.addFail.noOPenDevice", null, FishCfg.locale));
				return result;
			}
			if(device.getStatus().equals(DevStatus.SCRAPPED))
			{
				result.put(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.addFail.deviceScrappend", null, FishCfg.locale));
				return result;
			}
			IQuittingNotice exit_quittingNotice = quittingNoticeService
					.get(form.getDeviceCode(),new Date());
			if (exit_quittingNotice != null && exit_quittingNotice.getDevStatus().equals(DevStatus.DISABLED))
			{
				result.put(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.addFail.deviceHavedQuitted", null, FishCfg.locale));
				return result;
			}
			if (exit_quittingNotice != null && exit_quittingNotice.getDevStatus().equals(DevStatus.OPEN))
			{
				result.put(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.addFail.exitOneQuittingNotice", null, FishCfg.locale));
				return result;
			}			
			IQuittingNotice quittingNotice = quittingNoticeService.make();
			Date quittingNoticeTime = form.getStopTime();
			Date todayTime = new Date();
			if (quittingNoticeTime.getTime() < todayTime.getTime()) {
				quittingNotice.setDevStatus(DevStatus.DISABLED);
			} else {
				quittingNotice.setDevStatus(DevStatus.OPEN);
			}
			
			quittingNotice.setDeviceCode(form.getDeviceCode());
			quittingNotice.setId(form.getId());
			quittingNotice.setOpenTime(form.getOpenTime());
			quittingNotice.setStopTime(form.getStopTime());
			quittingNotice.setSetTime(new Date());
			quittingNotice.setStopType(StopType.getById(Integer.parseInt(form.getStopType())));
			quittingNotice.setStopReason(form.getStopReason());
			quittingNotice.setResponsiblilityName(form.getResponsibilityName());
			IQuittingNotice notice = quittingNoticeService.add(quittingNotice);

			form.setSetTime(notice.getSetTime());
			form.setId(notice.getId());

			if (quittingNotice.getDevStatus() != null) {

				// 当设备报停时,更新设备表状态
				if (!device.getStatus().equals(quittingNotice.getDevStatus())) {
					if(device.getStatus().equals(DevStatus.DISABLED) || device.getStatus().equals(DevStatus.OPEN))
					{
						device.setStatus(quittingNotice.getDevStatus());
						deviceService.update(device);
					}
				
				}

				/* 停机开始 */
				if (quittingNotice.getDevStatus().equals(DevStatus.DISABLED)) {
					IRunInfo runInfo = runInfoService.make();
					runInfo.setTerminalId(quittingNotice.getDeviceCode());
					runInfo.setRunStatus(RunStatus.StopManmade);
					collectService.collectATMCRunInfo(quittingNotice.getDeviceCode(), runInfo);
				}
			}

			result.put(FishConstant.SUCCESS, true);
			result.addAttribute("data", form);
		} catch (Exception e) {
			result.put(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.addFail", null, FishCfg.locale)+messageSource.getMessage("commen.error", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 *
	 * 根据ID删除一条设备报停记录:
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@MethodNameDescrible(describle="userlog.quittingNoticeController.delete",hasArgs=false,urlArgs=true)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete QuittingNotice: quittingNotice.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IQuittingNotice quittingNotice = quittingNoticeService.get(id);
			if (quittingNotice == null) {
				result.addAttribute(FishConstant.SUCCESS, true);
				return result;
			}
			quittingNoticeService.remove(id);

			/* 停机结束 */
			if (quittingNotice.getDevStatus() != null && quittingNotice.getDevStatus().equals(DevStatus.DISABLED)) {

				IDevice device = deviceService.get(quittingNotice.getDeviceCode());
				if(device != null)
				{
					device.setStatus(DevStatus.OPEN);
					deviceService.update(device);
					IXfsStatus status = xfsService.loadXfsStatus(quittingNotice.getDeviceCode());
					status.setRunStatus(RunStatus.Unknown);
					xfsService.updateXfsStatus(status);
				}				
			}
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 更具ID对一条设备报停记录更新：
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.quittingNoticeController.update",hasArgs=false,urlArgs=true)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody QuittingNoticeForm form) {
		logger.info("update QuittingNotice: quittingNotice.id = " + id);
		ModelMap result = new ModelMap();
		form.setId(id);
		try {
			IDevice device = deviceService.get(form.getDeviceCode());
			if (device == null) {
				result.put(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.updateFailDev", null, FishCfg.locale));
				return result;
			}
			IQuittingNotice quittingNotice = quittingNoticeService.get(id);
			if (quittingNotice == null) {
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.updateNotExist", null, FishCfg.locale));
				result.addAttribute(FishConstant.SUCCESS, false);
			} else {		
				Date quittingNoticeTime = form.getStopTime();
				Date todayTime = new Date();
				if (quittingNoticeTime.getTime() < todayTime.getTime()) {
					quittingNotice.setDevStatus(DevStatus.DISABLED);
				} else {
					quittingNotice.setDevStatus(DevStatus.OPEN);
				}
				String strDay = DateUtils.getDate(new Date());
				Date todayStart = DateUtils.getTimestamp(strDay + " 00:00:00");
				if( form.getOpenTime() != null)
				{
					Date openTime = form.getOpenTime();
					if( openTime.getTime() == todayStart.getTime())
					{
						quittingNotice.setDevStatus(DevStatus.OPEN);
					}
				}				
				quittingNotice.setDeviceCode(quittingNotice.getDeviceCode());
				quittingNotice.setOpenTime(form.getOpenTime());
				quittingNotice.setStopTime(form.getStopTime());
				quittingNotice.setStopType(StopType.getById(Integer.parseInt(form.getStopType())));
				quittingNotice.setStopReason(form.getStopReason());
				quittingNotice.setResponsiblilityName(form.getResponsibilityName());
				quittingNoticeService.update(quittingNotice);

				if (quittingNotice.getDevStatus() != null && !quittingNotice.getDevStatus().equals(device.getStatus())) {
					if(device.getStatus().equals(DevStatus.DISABLED) || device.getStatus().equals(DevStatus.OPEN))
					{
						device.setStatus(quittingNotice.getDevStatus());
						deviceService.update(device);
						if(device.getStatus().equals(DevStatus.OPEN))
						{
							IXfsStatus status = xfsService.loadXfsStatus(quittingNotice
									.getDeviceCode());
							status.setRunStatus(RunStatus.Unknown);
							xfsService.updateXfsStatus(status);
						}
					}
					
				}

				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute("data", form);
			}
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("quittingNotice.updateError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 更具条件得到设备报停记录列表：
	 *
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search quittingNotice : start = %s ,limt = %s ", start, limit));

		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		String orgId = String.valueOf(userSession.getOrgId());
		IPageResult<IQuittingNotice> pageResult = null;
		if (OrganizationType.BANK.equals(userSession.getOrgType())) {
			IFilter filter = request2filter(request, "quittingNotice.");
			pageResult = quittingNoticeService.page(start, limit, filter, orgId, true);
		} else if (OrganizationType.MAINTAINER.equals(userSession.getOrgType())) {
			IFilter filter = request2filter(request, "quittingNotice.");
			pageResult = quittingNoticeService.page(start, limit, filter, orgId, false);
		} else {
			IFilter filter = request2filter(request, "");
			pageResult = quittingNoticeService.page(start, limit, filter);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", QuittingNoticeForm.convert(pageResult.list()));
		return result;
	}

	private IFilter request2filter(WebRequest request, String prefix) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (isNotFilterName(name)) {
				continue;
			}

			else {
				String value = request.getParameter(name);
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
					continue;
				}
				if ("stopType".equals(name)) {
					filter.eq(prefix + "stopType", StopType.getById(Integer.valueOf(value)));
				}
				if ("startDate".equals(name)) {
					String newValue = value + " 00:00:00";
					filter.ge(prefix + "stopTime", DateUtils.getTimestamp(newValue));
				}
				if ("endDate".equals(name)) {
					String newValue = value + " 23:59:59";

					filter.le(prefix + "stopTime", DateUtils.getTimestamp(newValue));
				}
				if ("deviceCode".equals(name)) {
					filter.like(prefix + "deviceCode", value);
				}
				if ("responsibilityName".equals(name)) {
					filter.like(prefix + "responsibilityName", value);
				}
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}

}
