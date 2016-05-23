package com.yihuacomputer.fish.web.daily.controller;

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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.atmMove.IAtmMove;
import com.yihuacomputer.fish.api.atmMove.IAtmMoveService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.web.daily.form.AtmMoveForm;

@Controller
@RequestMapping("/machine/atmMove")
public class AtmMoveController {
	/**
	 * 日志
	 */
	private Logger logger = LoggerFactory.getLogger(AtmMoveController.class);

	@Autowired
	private IAtmMoveService atmMoveService;

	/**
	 * 设备接口
	 */
	@Autowired
	private IDeviceService deviceService;

	/**
	 * 机构接口
	 */
	@Autowired
	private IOrganizationService orgService;
	
	@Autowired
	protected MessageSource messageSource;

	/**
	 * 增加一条移机记录：
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody AtmMoveForm request) {
		logger.info("add tmMoveService");
		ModelMap result = new ModelMap();
		try {
			IAtmMove addAtmMove = atmMoveService.make();

			addAtmMove.setOrganization(orgService.make());
			addAtmMove.setTargetOrganization(orgService.make());

			request.translate(addAtmMove);
			IDevice device = deviceService.get(request.getTerminalId());
			if(request.getOrgId() == request.getTargetOrganizationId()){
				result.put(FishConstant.SUCCESS, false);
				result.put(FishConstant.ERROR_MSG, messageSource.getMessage("atmMove.failOrg", null, FishCfg.locale));
				return result;
			}
			if (device == null) {
				result.put(FishConstant.SUCCESS, false);
				result.put(FishConstant.ERROR_MSG, messageSource.getMessage("atmMove.failDev", null, FishCfg.locale));
				return result;
			} else {
				IAtmMove atmMove = atmMoveService.add(addAtmMove);
				device.setOrganization(orgService.get(request.getTargetOrganizationId() + ""));
				device.setAddress(request.getTargetAddress());
				deviceService.update(device);
				result.put(FishConstant.SUCCESS, true);
				result.addAttribute("data", new AtmMoveForm(atmMove));
			}
		} catch (Exception e) {
			result.put(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, messageSource.getMessage("atmMove.fail", null, FishCfg.locale)+messageSource.getMessage("commen.error", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 *
	 * 根据删除一条移机记录:
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete AtmMove: atmMove.id = " + id);
		ModelMap result = new ModelMap();
		if (atmMoveService.get(id) == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.delSucess", null, FishCfg.locale));
			return result;
		}
		try {
			atmMoveService.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 根据ID对一条移机记录更新：
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody AtmMoveForm request) {
		logger.info("update AtmMove: atmMove.id = " + id);
		ModelMap result = new ModelMap();
		request.setId(id);
		try {
			IAtmMove atmMove = atmMoveService.get(id);
			if (atmMove == null) {
				result.put(FishConstant.SUCCESS, false);
				result.put(FishConstant.ERROR_MSG, messageSource.getMessage("atmMove.failDev", null, FishCfg.locale));
				return result;
			}
			request.translate(atmMove);
			atmMoveService.update(atmMove);
			IDevice device = deviceService.get(request.getTerminalId());
			if (device == null) {
				result.put(FishConstant.SUCCESS, false);
				result.put(FishConstant.ERROR_MSG, messageSource.getMessage("atmMove.failDev", null, FishCfg.locale));
				return result;
			}
			device.setOrganization(orgService.get(request.getTargetOrganizationId() + ""));
			device.setAddress(request.getTargetAddress());
			deviceService.update(device);
			result.put(FishConstant.SUCCESS, true);
			result.addAttribute("data", new AtmMoveForm(atmMove));
			result.put(FishConstant.SUCCESS, true);
			result.addAttribute("data", new AtmMoveForm(atmMove));
		} catch (Exception e) {
			result.put(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, messageSource.getMessage("atmMove.fail", null, FishCfg.locale)+messageSource.getMessage("commen.error", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 根据条件得到移机记录列表：
	 *
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search atmMove : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);

		ModelMap result = new ModelMap();

		IPageResult<IAtmMove> pageResult = atmMoveService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", AtmMoveForm.convert(pageResult.list()));
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
				String value = request.getParameter(name);
				if (request.getParameter(name).isEmpty() || "sort".equals(name)) {
					continue;
				}

				if ("orgId".equals(name)) {
					IOrganization org = orgService.get(value);
					filter.like("organization.orgFlag", org.getOrgFlag());

				} else if ("targetOrganizationId".equals(name)) {
					IOrganization org = orgService.get(value);
					filter.like("targetOrganization.orgFlag", org.getOrgFlag());

				} else if ("startDate".equals(name)) {
					filter.ge("date", DateUtils.getDate(value));

				} else if ("endDate".equals(name)) {
					filter.le("date", DateUtils.getDate(value));

				} else if ("terminalId".equals(name)) {
					filter.like("terminalId", value);

				}
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}

}
