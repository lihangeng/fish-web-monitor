package com.yihuacomputer.fish.web.system.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.permission.RoleType;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.relation.IRolePermissionRelation;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.web.system.form.RoleForm;

/**
 *
 *
 *
 */
@Controller
@RequestMapping("/relation/role")
@ClassNameDescrible(describle="userlog.RoleController")
public class RoleController {
	private Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private IRoleService service;

	@Autowired
	private IPermissionService permissionService;

	@Autowired
	private IRolePermissionRelation rolePermissionRelation;

	@Autowired
	private IUserRoleRelation userRoleRelation;
	
	@Autowired
	private MessageSource messageSource;

	/**
	 *
	 * 方法描述 : 增加角色
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@MethodNameDescrible(describle="userlog.RoleController.add",hasReqBodyParam=true,reqBodyClass=RoleForm.class,bodyProperties="name")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody RoleForm form) {
		logger.info("add Role");
		ModelMap result = new ModelMap();
		boolean isExist = this.isExistRoleName(form.getId(), form.getName());
		if (isExist) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("role.add.exist", null, FishCfg.locale));
		} else {
			IRole role = service.make(form.getName().trim());
			role.setDescription(form.getDescription());
			service.add(role);
			form.setId(role.getId());
			if (StringUtils.isNotEmpty(form.getPermissions())) {
				String[] permissions = form.getPermissions().split(",");
				for (String code : permissions) {
					IPermission p = permissionService.get(code);
					rolePermissionRelation.link(role, p);
				}
			}
			result.put(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, form);
		}
		return result;
	}

	/**
	 *
	 * 根据ID删除角色
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@MethodNameDescrible(describle="userlog.RoleController.delete",hasLogKey=true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete role: role.id = " + id);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		try {
			IRole role = service.get(id);
			if (role == null) {
				result.addAttribute(FishConstant.SUCCESS, true);
			} else {
				result.addAttribute(FishConstant.LOG_KEY, role.getName());
				List<IPermission> permissions = rolePermissionRelation.listPermissionByRole(role);
				boolean isSystem = this.isSystem(id);
				if (isSystem) {
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute("errorMsg", messageSource.getMessage("role.del.systemRole", null, FishCfg.locale));
				} else if (this.isLinkedByUser(id)) {
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute("errorMsg", messageSource.getMessage("role.del.bindRole", null, FishCfg.locale));

				} else {
					for (IPermission p : permissions) {
						rolePermissionRelation.unlink(role, p);
					}
					try {
						service.remove(id);
					} catch (IllegalArgumentException iae) {
						logger.warn(iae.getMessage());
						result.addAttribute(FishConstant.SUCCESS, false);
						result.put("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
					}
				}
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("commen.error", null, FishCfg.locale));
		}
		return result;
	}

	private boolean isLinkedByUser(long id) {
		logger.info("check User by Role");
		boolean flag = false;
		IRole role = service.get(id);
		List<IUser> userList = userRoleRelation.listUserByRole(role);
		if (!userList.isEmpty()) {
			flag = true;
		}
		return flag;
	}

	/**
	 *
	 * 方法描述 : 根据ID更新角色
	 *
	 * @param id
	 * @param request
	 * @return ModelMap<String, Object>
	 */
	@MethodNameDescrible(describle="userlog.RoleController.update",hasReqBodyParam=true,reqBodyClass=RoleForm.class,bodyProperties="name")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody RoleForm form) {
		logger.info("update Role: Role.id = " + id);
		form.setId(id);
		ModelMap result = new ModelMap();
		boolean isExist = this.isExistRoleName(id, form.getName());
		IRole role = service.get(id);
		if (role == null) {
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("updateMsg", messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));
			return result;
		} else {
			if (isExist) {
				List<IPermission> permissions = rolePermissionRelation.listPermissionByRole(role);
				StringBuffer s = new StringBuffer();
				for (IPermission item : permissions) {
					s.append(item.getCode()+",");
				}
				RoleForm oldRole = new RoleForm(role);
				oldRole.setPermissions(s.toString());
				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute("errorMsg", messageSource.getMessage("role.update.exist", null, FishCfg.locale));
				result.addAttribute(FishConstant.DATA, oldRole);
				return result;
			}
			if (this.isSystem(id)) {
				// IRole role = service.get(id);
				List<IPermission> permissions = rolePermissionRelation.listPermissionByRole(role);
				StringBuffer s = new StringBuffer();
				for (IPermission item : permissions) {
					s.append(item.getCode()+",");
				}
				RoleForm oldRole = new RoleForm(role);
				oldRole.setPermissions(s.toString());

				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute("errorMsg", messageSource.getMessage("role.update.systemRole", null, FishCfg.locale));
				result.addAttribute(FishConstant.DATA, oldRole);
				return result;
			}
			role.setName(form.getName());
			role.setDescription(form.getDescription());
			service.update(role);

			List<String> codesInDB = new ArrayList<String>();
			for (IPermission each : rolePermissionRelation.listPermissionByRole(role)) {
				codesInDB.add(each.getCode());
			}
			List<String> commons = new ArrayList<String>();
			List<String> codesInPage = toList(form.getPermissions());
			// 找出页面和数据库中的共同元素
			for (String code : codesInPage) {
				if (codesInDB.contains(code)) {
					commons.add(code);
				}
			}

			// 删除共同的元素
			if (!commons.isEmpty()) {
				codesInDB.removeAll(commons);
				codesInPage.removeAll(commons);
			}

			// 删除关系
			for (String code : codesInDB) {
				IPermission p = permissionService.get(code);
				rolePermissionRelation.unlink(role, p);
			}
			// 增加关系
			for (String code : codesInPage) {
				IPermission p = permissionService.get(code);
				rolePermissionRelation.link(role, p);
			}

			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, form);
		}

		return result;

	}

	private List<String> toList(String permissions) {
		List<String> lists = new ArrayList<String>();
		if (StringUtils.isNotEmpty(permissions)) {
			String[] ps = permissions.split(",");
			for (String value : ps) {
				lists.add(value);
			}
		}
		return lists;
	}

	/**
	 *
	 * @Title: 迁移角色到新的组织
	 * @Description:根据角色id和组织的code,迁移角色到新的组织
	 * @param id
	 *            角色id
	 * @param orgCode
	 *            组织code
	 * @return ModelMap
	 * @version 1.3
	 * @since 1.2
	 * @updateTime 2011-6-7 上午10:38:48
	 * @updateAuthor GaoBoTao
	 * @date 2011-6-7 上午10:38:48
	 */
	@RequestMapping(value = "/move", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap moveRole(@RequestParam long id, @RequestParam String orgCode) {
		logger.info("move Role: Role.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IRole role = service.get(id);
			service.update(role);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 *
	 * 角色列表页面
	 *
	 * @param
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search role : start = %s ,limit = %s", start, limit));
		ModelMap result = new ModelMap();
		IFilter filter = request2filter(request);
		IPageResult<IRole> page = service.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, page.getTotal());
		result.addAttribute(FishConstant.DATA, RoleForm.convert(page.list()));
		return result;
	}

	/*
	 * private IFilter getFilter(WebRequest request) { IFilter filter = new
	 * Filter(); String type = request.getParameter("type"); if
	 * (StringUtils.isNotEmpty(type)) {
	 * filter.addFilterEntry(FilterFactory.eq("type", RoleType.getById(new
	 * Integer(type)))); } return filter; }
	 */

	/**
	 *
	 * 角色--权限页面的角色显示
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/permission", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchPermissionRole(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search role in role-- permission : start = %s ,limt = %s ", start, limit));
		return doDisplayRole(start, limit, request, RoleType.PERMISSION);
	}

	private ModelMap doDisplayRole(int start, int limit, WebRequest request, RoleType roleType) {
		String parentId = request.getParameter("parentId");
		ModelMap result = new ModelMap();
		// 如果左侧没有选择任何节点,传入的值为0,此时不需要在右侧显示任何内容

		if (!"0".equals(parentId)) {
			IFilter filter = new Filter();
			filter.eq("type", roleType);
			filter.eq("organizationCode", parentId);

			String name = request.getParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				filter.like("name", name);
			}
			IPageResult<IRole> pageResult = service.page(start, limit, filter);
			result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
			result.addAttribute(FishConstant.DATA, RoleForm.convert(pageResult.list()));
		} else {
			result.addAttribute(FishConstant.TOTAL, 0);
			result.addAttribute(FishConstant.DATA, RoleForm.convert(new ArrayList<IRole>()));
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}

	/**
	 *
	 * 角色--资源页面的角色显示
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(value = "/access", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap searchAccessRole(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search role in role--reosource : start = %s ,limt = %s ", start, limit));

		return doDisplayRole(start, limit, request, RoleType.RESOURCE);
	}

	/**
	 *
	 * 方法描述 : 验证角色name的唯一性
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
		List<IRole> listRole = service.list(filter);
		if (!listRole.isEmpty()) {
			if (listRole.size() == 1) {
				if (listRole.get(0).getId() == id) {
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

	/**
	 * 验证此条记录是否存在
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/isExist", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap isRoleExist(@RequestParam long id) {
		ModelMap result = new ModelMap();
		try {
			IRole role = service.get(id);
			if (role == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("errorMsg", messageSource.getMessage("role.isRoleExist.notExist", null, FishCfg.locale));
			} else {
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("errorMsg", messageSource.getMessage("role.isRoleExist.notExist", null, FishCfg.locale));
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/isSystemRole")
	public @ResponseBody
	ModelMap isSystemRole(@RequestParam long id) {
		ModelMap result = new ModelMap();
		if (this.isSystem(id)) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}

	/**
	 * 判断角色名称是否重复
	 *
	 * @param id
	 *            角色Id
	 * @param name
	 *            角色名称
	 * @return
	 */
	private boolean isExistRoleName(long id, String name) {
		try {
			logger.info("chekRoelName" + name);
			IRole role = service.get(name.trim());
			if (role.getId() == id) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			return false;
		}

	}

	private boolean isSystem(long id) {
		try {
			IRole role = service.get(id);
			return role.isSystem();
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
				} else if ("sort".equals(name)) {
					continue;
				} else {
					filter.like(name, request.getParameter(name));
				}
			}
		}

		return filter;
	}

	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}

}
