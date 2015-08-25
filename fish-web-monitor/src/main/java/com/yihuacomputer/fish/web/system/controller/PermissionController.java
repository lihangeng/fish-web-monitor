package com.yihuacomputer.fish.web.system.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
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
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.web.system.form.PermissionCheckedTreeForm;
import com.yihuacomputer.fish.web.system.form.PermissionForm;
import com.yihuacomputer.fish.web.system.form.PermissionRequest;
import com.yihuacomputer.fish.web.system.form.PermissionTreeForm;

/**
 * @author YiHua
 * @version
 * @date 2011-8-28
 */
@Controller
@RequestMapping("/relation/permission")
public class PermissionController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(PermissionController.class);

	@Autowired
	private IPermissionService service;
	@Autowired
	private IRolePermissionRelation rolePermissionRelation;
	@Autowired
	private IRoleService roleService;

	public PermissionController() {
	}

	/**
	 *
	 * 方法描述 : 增加权限
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody PermissionRequest request) {
		logger.info("add Permission");
		ModelMap result = new ModelMap();
		PermissionForm form = request.getData();
		form.add(service);
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, form);
		return result;
	}

	/**
	 *
	 * 根据ID删除权限
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap deleteById(@PathVariable String id) {
		logger.info(" delete Permission: Permission.id = " + id);
		ModelMap result = new ModelMap();
		try {
			service.removeById(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 *
	 * 根据CODE删除权限
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable String code) {
		logger.info(" delete Permission: permission.code = " + code);
		ModelMap result = new ModelMap();
		try {
			service.remove(code);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 根据ID更新权限
	 *
	 * @param id
	 * @param request
	 * @return ModelMap<String, Object>
	 */

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody PermissionRequest request) {
		logger.info("update Permission: PermissionRequest.id = " + id);
		ModelMap result = new ModelMap();
		PermissionForm form = request.getData();
		form.update(service);
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}

	/**
	 *
	 * 根据条件得到权限
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search permissionRequest : start = %s ,limt = %s ", start, limit));

		IFilter filter = new Filter();

		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if ("start".equals(name) || "limit".equals(name) || name.startsWith("_dc")) {
				continue;
			} else if ("parentId".equals(name)) {
				IPermission p = service.get(request.getParameter(name));
				filter.eq("parent", p);
			} else {
				filter.like(name, request.getParameter(name));
			}
		}

		IPageResult<IPermission> pageResult = service.page(start, limit, filter);

		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, PermissionForm.convert(pageResult.list()));
		return result;
	}

	static {

	}

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public @ResponseBody
	List<PermissionTreeForm> tree(@RequestParam String node) {
		Iterable<IPermission> data = service.get(node).listChildren();
		return PermissionTreeForm.convert(data);
	}

	@RequestMapping(value = "/checkedTree", method = RequestMethod.GET)
	public @ResponseBody
	List<PermissionCheckedTreeForm> checkedTree(WebRequest request) {
		logger.info("checkedTree...");
		String node = request.getParameter("node");
		String roleId = request.getParameter("roleId");
		Iterable<IPermission> data = service.get(node).listChildren();
		List<IPermission> datalist = EntityUtils.convert(data);
		// 对权限进行排序
		Collections.sort(datalist, new Comparator<IPermission>() {
			public int compare(IPermission o1, IPermission o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		List<IPermission> permissions = new ArrayList<IPermission>();
		if (StringUtils.isNotEmpty(roleId) && !roleId.equalsIgnoreCase("0")) {
			permissions = rolePermissionRelation.listPermissionByRole(roleService.get(new Integer(roleId)));
		}
		return PermissionCheckedTreeForm.convert(datalist, permissions);
	}

}