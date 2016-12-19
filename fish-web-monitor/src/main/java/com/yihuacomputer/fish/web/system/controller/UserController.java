package com.yihuacomputer.fish.web.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
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
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.permission.IRoleService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserLogService;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.web.system.form.RoleForm;
import com.yihuacomputer.fish.web.system.form.UserForm;
import com.yihuacomputer.fish.web.system.form.UserRoleForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 账户控制：
 *
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping("/person/user")
@ClassNameDescrible(describle="userlog.UserController")
public class UserController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@Autowired
	private IPersonService personService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserRoleRelation userRoleRelation;

	@Autowired
	private IUserLogService logService;
	
	@Autowired
	protected MessageSource messageSource;

	public UserController() {
	}

	/**
	 * 给账户添加角色：
	 *
	 * @param request
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.UserController.addRole",hasLogKey=true)
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap addRole(@RequestBody UserRoleForm request) {
		logger.info(String.format("device %s linked  %s", request.getUserId(), request.getRoleId()));
		ModelMap result = new ModelMap();
		IRole role = roleService.get(request.getRoleId());
		//角色不存在
		if(role == null){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.processError", null, FishCfg.locale));
			return result;
		}
		try {
			IUser user = userService.get(request.getUserId());
			if (user == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.notExist", null, FishCfg.locale));
				return result;
			}
			result.addAttribute(FishConstant.LOG_KEY,user.getCode() + "->" + role.getName());
			userRoleRelation.link(user, role);
			result.put(FishConstant.SUCCESS, true);
			result.put(FishConstant.DATA, request);
		} catch (Exception ex) {
			logger.info(String.format("[%s]", ex));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.processError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 删除账户角色
	 */
	@MethodNameDescrible(describle="userlog.UserController.deleteRole",hasLogKey=true)
	@RequestMapping(value = "/removeRole", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap deleteRole(@RequestParam long userId, @RequestParam long roleId) {
		ModelMap result = new ModelMap();
		IRole role = roleService.get(roleId);
		if (role == null) {
			result.addAttribute(FishConstant.SUCCESS, true);
			return result;
		}
		try {
			IUser user = userService.get(userId);
			userRoleRelation.unlink(user,role);
			result.addAttribute(FishConstant.LOG_KEY,user.getCode() + "->" + role.getName());
			result.addAttribute(FishConstant.SUCCESS, true);
		}catch (Exception ex) {
			logger.info(String.format("[%s]", ex));
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 增加账号
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@MethodNameDescrible(describle="userlog.UserController.add",hasReqBodyParam=true,reqBodyClass=UserForm.class,bodyProperties="code")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody UserForm form) {
		logger.info("add User");
		ModelMap result = new ModelMap();
		boolean flag = userService.isExist(form.getUserGuid());
		if (!flag) {
			if (!isExistCode(String.valueOf(form.getId()), form.getCode())) {
				try {
					IPerson person = personService.get(form.getUserGuid());
					if(person==null){
						result.put(FishConstant.ERROR_MSG, messageSource.getMessage("user.addExist", null, FishCfg.locale));
						result.put(FishConstant.SUCCESS, false);
						return result;
					}
					List<IRole> roleList = new ArrayList<IRole>();
					if (StringUtils.isNotEmpty(form.getRoles())) {
						String[] permissions = form.getRoles().split(",");
						for (String roleId : permissions) {
							IRole role =roleService.get(Long.valueOf(roleId));
							if(role==null){
								result.put(FishConstant.ERROR_MSG, messageSource.getMessage("user.addSuccessRoleNotExist", null, FishCfg.locale));
								result.put(FishConstant.SUCCESS, false);
								return result;
							}
							roleList.add(role);
						}
					}
					IUser user = userService.add(form.getCode(), person, roleList);
					form.setId(user.getId());
					form.setName(user.getPerson().getName());
					form.setMobile(user.getPerson().getMobile());
					form.setPhone(user.getPerson().getPhone());
					form.setEmail(user.getPerson().getEmail());
					if (user.getPerson().getOrganization() != null) {
						form.setOrganizationName(user.getPerson().getOrganization().getName());
					}
					result.put(FishConstant.SUCCESS, true);
					result.addAttribute(FishConstant.DATA, form);
				} catch (Exception ex) {
					result.put(FishConstant.ERROR_MSG, messageSource.getMessage("user.addFail", null, FishCfg.locale));
					result.put(FishConstant.SUCCESS, false);
				}
			} else {
				result.put(FishConstant.ERROR_MSG, messageSource.getMessage("user.addFailDup", null, FishCfg.locale));
				result.put(FishConstant.SUCCESS, false);
			}
		} else {
			result.put(FishConstant.ERROR_MSG, messageSource.getMessage("user.addFailAcc", null, FishCfg.locale));
			result.put(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 *
	 * 根据ID删除账号
	 *
	 * @param id
	 * @return ModelMap<String, Object>
	 */
	@MethodNameDescrible(describle="userlog.UserController.delete",hasLogKey=true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete Master: Master.id = " + id);
		ModelMap result = new ModelMap();
		IUser user = userService.get(id);
		if (user == null) {
			result.addAttribute(FishConstant.SUCCESS, true);
			return result;
		}
		result.addAttribute(FishConstant.LOG_KEY, user.getCode());
		try {
			String code = user.getCode();
			System.out.println(code);
			userService.remove(id);
			logService.remove(code);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			logger.info(String.format("[%s]", ex));
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delError", null, FishCfg.locale));
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 根据ID更新账号
	 *
	 * @param guid
	 * @param request
	 * @return ModelMap<String, Object>
	 */
	@MethodNameDescrible(describle="userlog.UserController.update",hasReqBodyParam=true,reqBodyClass=UserForm.class,bodyProperties="code")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody UserForm form) {
		logger.info("update Master: Master.id = " + id);
		form.setId(id);
		ModelMap result = new ModelMap();
		if (!isExistCode(String.valueOf(form.getId()), form.getCode())) {
			IUser user = userService.get(id);
			if (user == null) {
				result.put(FishConstant.ERROR_MSG, "");
				result.put(FishConstant.SUCCESS, false);
			} else {
				if (form.getUserState() != null) {
					if (user.getState() == UserState.LOCK) {
						user.setAccessTime(null);
					}
					user.setState(UserState.getById(Integer.valueOf(form.getUserState())));
				} else {
					form.setUserState(String.valueOf((user.getState().getId())));
				}
				userService.update(user);
				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute(FishConstant.DATA, form);
			}
		} else {
			result.put(FishConstant.ERROR_MSG, "");
			result.put(FishConstant.SUCCESS, false);
		}
		return result;
	}

	/**
	 * 验证后修改密码：
	 *
	 * @param username
	 * @param request
	 * @param webrequest
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.UserController.resetPassword",hasArgs=true,argsContext="username")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap resetPassword(@RequestParam String username, @RequestParam String newPassword, HttpServletRequest request,
			WebRequest webrequest) {
		ModelMap result = new ModelMap();
		try {
			IUser user = userService.get(username);

			user.setPlainText(newPassword);
			if (user.getState().equals(UserState.NORMAL)) {

				user.setState(UserState.NEW);
			}
			userService.update(user);

			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("message", messageSource.getMessage("user.resetPwdSuccess", null, FishCfg.locale));

		} catch (Exception e) {

			logger.error(e.toString());

			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.resetPwdFail", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 用户锁定和解锁：
	 *
	 * @param username
	 * @param password
	 * @param session
	 * @param request
	 * @param webrequest
	 * @return
	 */
	@RequestMapping(value = "/lockUser", method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.UserController.unLock",hasArgs=true,argsContext="username")
	public @ResponseBody
	ModelMap lockUser(@RequestParam String username, HttpServletRequest request, WebRequest webrequest) {
		ModelMap result = new ModelMap();
		IUser user = userService.get(username);
		if (user.getState().equals(UserState.LOCK)) {
			user.setState(UserState.NORMAL);
		}
		user.setAccessTime(new Date());
		userService.update(user);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("message", messageSource.getMessage("user.unlockSuccess", null, FishCfg.locale));
		return result;
	}

	/**
	 *
	 * 根据条件得到账号列表
	 *
	 * @param form
	 * @return ModelMap<String, Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search user : start = %s ,limt = %s ", start, limit));
		ModelMap result = new ModelMap();
		IFilter filter1 = new Filter();
		IFilter filter2 = new Filter();
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		filter1.ne("user.id", userSession.getUserId());
		filter2.ne("id", userSession.getUserId());
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					// 去掉前端页面传来的sort排序字段
					if ("sort".equals(name)) {
						continue;
					} else if ("name".equals(name)) {
						filter1.like("person.name", request.getParameter(name));
						filter2.like("person.name", request.getParameter(name));
					} else if ("userState".equals(name)) {
						filter1.eq("user.userState",
								UserState.getById(Long.valueOf(request.getParameter(name)).intValue()));
						filter2.eq("userState", UserState.getById(Long.valueOf(request.getParameter(name)).intValue()));
					} else {
						filter1.like("user." + name, request.getParameter(name));
						filter2.like(name, request.getParameter(name));
					}
				}
			}
		}

		IPageResult<IUser> pageResult = null;
		pageResult = userService.page(start, limit, filter1, String.valueOf(userSession.getOrgId()));

		List<IUser> users = pageResult.list();
		for (IUser user : users) {
			user.setRoles(userRoleRelation.listRoleByUser(user));
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, UserForm.convert(users));
		return result;
	}

	/**
	 * 获得该用户下的角色
	 *
	 * @param id
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addedRole", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getAddedRole(@RequestParam int start, @RequestParam int limit, @RequestParam long id) {

		ModelMap result = new ModelMap();
		if (userService.get(id) == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
		} else {
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA,
					RoleForm.convert(userRoleRelation.listRoleByUser(userService.get(id))));
		}
		return result;
	}

	/**
	 * 获得该用户所能添加的角色
	 *
	 * @param start
	 * @param limit
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addingRole", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getAddingRole(@RequestParam int start, @RequestParam int limit, @RequestParam long id) {

		ModelMap result = new ModelMap();
		if (userService.get(id) == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
		} else {
			List<IRole> list = roleService.list();
			list.removeAll(userRoleRelation.listRoleByUser(userService.get(id)));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, RoleForm.convert(list));
		}
		return result;
	}

	/**
	 *
	 * 方法描述 : 验证code的唯一性
	 *
	 * @param name
	 * @param form
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/unique", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap unique(@RequestParam String id, @RequestParam String code) {
		logger.info("org code unique checking...");
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, isExistCode(id, code));
		if (isExistCode(id, code)) {
			result.addAttribute("errios", messageSource.getMessage("user.accDup", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 是否存在code
	 *
	 * @param id
	 * @param code
	 * @return true 存在，false不存在
	 */
	private boolean isExistCode(String id, String code) {
		try {
			logger.info("update.user.id is" + id);
			IUser user = userService.get(code);
			logger.info("get.user.id is " + user.getId());
			if (id.equals(String.valueOf(user.getId()))) {
				// 找到的Id和传入的Id相等，说明是同一个组织
				return false;
			} else {
				// 说明存在
				return true;
			}
		} catch (Exception e) {
			return false;
		}
	}

}
