package com.yihuacomputer.fish.web.system.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.yihuacomputer.fish.api.permission.IGroup;
import com.yihuacomputer.fish.api.permission.IGroupService;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.web.system.form.GroupForm;
import com.yihuacomputer.fish.web.system.form.GroupRequest;

/**
 * @author 樊晓雨
 * @version
 * @date 2010-8-19
 */

public class GroupController {
	private Logger logger = LoggerFactory.getLogger(GroupController.class);

	private IGroupService service;

	private IOrganizationService organizationService;

	/**
	 *
	 * 方法描述 : 增加组
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody GroupRequest request) {
		logger.info("add Group");
		ModelMap result = new ModelMap();
		GroupForm form = request.getData();
		form.add(service, organizationService);
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, form);
		return result;
	}

	/**
	 *
	 * 根据ID删除数据库
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable Long id) {
		logger.info(" delete group: group.id = " + id);
		ModelMap result = new ModelMap();
		try {
			service.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 根据ID更新组
	 *
	 * @param id
	 * @param request
	 * @return ModelMap<String, Object>
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody GroupRequest request) {
		logger.info("update Group: Group.id = " + id);
		ModelMap result = new ModelMap();
		GroupForm form = request.getData();
		form.update(service);
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}

	/**
	 *
	 * 根据条件得到数据库列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search group : start = %s ,limt = %s ", start, limit));

		IFilter filter = new Filter();

		String parentId = request.getParameter("parentId");
		if (null != parentId) {
			filter.eq("organizationCode", parentId);
			logger.info("parentId : " + parentId);
		}

		String name = request.getParameter("name");
		if (null != name) {
			filter.like("name", name);
			logger.info("name : " + name);
		}
		IPageResult<IGroup> pageResult = service.page(start, limit, filter);

		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, GroupForm.convert(pageResult.list()));
		return result;
	}

	/**
	 *
	 * 方法描述 : 验证name的唯一性
	 *
	 * @param name
	 * @param form
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/unique", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap ifExist(@RequestParam String name, @RequestParam long id, @RequestParam String organizationCode) {
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		filter.eq("name", name);
		filter.eq("organizationCode", organizationCode);
		List<IGroup> listGroup = service.list(filter);

		if (listGroup.size() > 0) {
			if (listGroup.size() == 1) {
				if (listGroup.get(0).getId() == id) {
					result.addAttribute(FishConstant.SUCCESS, true);
				} else {
					result.addAttribute(FishConstant.SUCCESS, false);
				}
			} else {
				result.addAttribute(FishConstant.SUCCESS, false);
			}
		} else {
			result.addAttribute(FishConstant.SUCCESS, true);
		}
		return result;
	}

}
