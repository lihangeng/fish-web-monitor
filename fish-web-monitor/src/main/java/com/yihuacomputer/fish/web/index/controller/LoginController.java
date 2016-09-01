package com.yihuacomputer.fish.web.index.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.util.MsgDigestAlgorithm;
import com.yihuacomputer.domain.util.DBType;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.person.UserState;
import com.yihuacomputer.fish.api.relation.IRelationService;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.api.session.ISessionManage;
import com.yihuacomputer.fish.web.index.form.LoginBackForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/login")
@ClassNameDescrible(describle = "userlog.LoginController")
public class LoginController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(LoginController.class);

	@Autowired(required = false)
	private IUserService userService;

	@Autowired
	private IRelationService relationService;

	@Autowired
	private IUserRoleRelation userRoleRelation;

	@Autowired
	private LocalSessionFactoryBean sf;

	@Autowired
	protected MessageSource messageSource;

	@Autowired
	private ISessionManage sessionManage;

	/**
	 * 登录并验证用户
	 */
	@MethodNameDescrible(describle = "userlog.LoginController.login", hasArgs = true, argsContext = "username")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap login(@RequestParam String username, @RequestParam String password, HttpSession session, HttpServletRequest request, WebRequest webrequest) {

		ModelMap result = new ModelMap();
		// 验证没有注册就进入注册页面。
		if (!new DBType(sf.getHibernateProperties()).isMemDB() && FishCfg.isFishExpiry()) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("isRegister", false);
			result.addAttribute("message", "System is not Register");
			return result;
		}
		boolean hasLogin = sessionManage.hasLogin(username);
		if (hasLogin) {
			String forceLogin = (String) webrequest.getParameter("forceLogin");
			if (forceLogin != null && "true".equals(forceLogin)) {
				sessionManage.logout(username);
			} else {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute("message",  messageSource.getMessage("login.forceLogin", new Object[]{username}, FishCfg.locale));
				return result;
			}
		}
		try {
			IUser user = userService.login(username, password);
			IOrganization org = user.getOrganization();
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("id", session.getId());
			result.addAttribute("userState", user.getState().getId());
			if (user.getState().equals(UserState.NEW)) {
				return result;
			}
			// 保存会话信息
			UserSession userSession = new UserSession();
			userSession.setUserId(user.getId());
			userSession.setPersonId(user.getPerson().getGuid());
			userSession.setUserName(user.getName());
			userSession.setUserCode(user.getCode());
			userSession.setUserState(user.getState());
			userSession.setOrgId(org.getId());
			userSession.setOrgFlag(org.getOrgFlag());
			userSession.setOrgName(org.getName());
			userSession.setOrgCode(org.getCode());
			userSession.setOrgType(org.getOrganizationType());
			if (org.getServiceObject() != null) {
				userSession.setOrgServiceObjectId(org.getServiceObject().getId());
				userSession.setOrgServiceObjectName(org.getServiceObject().getName());
			}
			userSession.setMapUrl(getMapUrl());
			sessionManage.login(username, session);
			session.setAttribute(FishWebUtils.USER, userSession);
		} catch (AppException app) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("message", app.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(String.format("User login error![%s]", e));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("message", messageSource.getMessage("login.loginError", null, FishCfg.locale));

		}
		result.addAttribute("isRegister", true);
		return result;
	}

	/**
	 * 获取地图URL地址
	 *
	 * @return
	 */
	private static String getMapUrl() {
		String mapsUrl = FishCfg.getParamValue("maps_url");
		if (mapsUrl == null || mapsUrl.isEmpty()) {
			return "";
		} else {
			return mapsUrl;
		}
	}

	/**
	 * 验证用户和密码是否正确
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public @ResponseBody ModelMap check(@RequestParam String code, @RequestParam String password, HttpSession session, HttpServletRequest request, WebRequest webrequest) {
		ModelMap result = new ModelMap();
		IUser user = userService.get(code);
		// 验证密码
		String pwd = MsgDigestAlgorithm.getMD5Str(password);
		if (!user.getPassword().equals(pwd)) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("login.pwderror", null, FishCfg.locale));// "您输入的密码错误,请重新输入密码."
		} else {
			result.addAttribute(FishConstant.SUCCESS, true);
		}
		return result;
	}

	/**
	 * 验证后修改密码：
	 *
	 * @param username
	 * @param password
	 * @param session
	 * @param request
	 * @param webrequest
	 * @return
	 */
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public @ResponseBody ModelMap updatePassword(@RequestParam String username, @RequestParam String password, @RequestParam String newPassword, HttpSession session, HttpServletRequest request,
			WebRequest webrequest) {
		ModelMap result = new ModelMap();
		IUser user = userService.get(username);

		// 验证密码
		String pwd = MsgDigestAlgorithm.getMD5Str(password);
		if (!user.getPassword().equals(pwd)) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("login.initPwdError", null, FishCfg.locale));
		} else {
			user.setPlainText(newPassword);
			if (user.getState().equals(UserState.NEW)) {
				user.setState(UserState.NORMAL);
			}
			user.setAccessTime(new Date());
			userService.update(user);
			LoginBackForm form = new LoginBackForm();
			form.setUserCode(username);
			form.setUserId(String.valueOf(userService.get(username).getId()));
			result.addAttribute("user", form);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("message", messageSource.getMessage("login.updateSuccess", null, FishCfg.locale));
			result.addAttribute("sessionId", session.getId());
		}
		return result;
	}

	/**
	 * 登录用户获取菜单权限
	 * 
	 * @param node
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/mymenu/{userId}", method = RequestMethod.GET)
	public @ResponseBody List<TreeMenu> tree(@RequestParam String node, @PathVariable long userId) {
		List<IPermission> permissions = userRoleRelation.findDirectChildPermissionsByUser(userId, node);
		List<TreeMenu> forms = new ArrayList<TreeMenu>();
		for (IPermission permission : permissions) {
			TreeMenu menu = new TreeMenu(permission);
			// menu.setText(messageSource.getMessage("user.login.remark", null,
			// FishCfg.locale));
			forms.add(menu);
		}
		return forms;
	}

	@Autowired
	private IPermissionService service;

	/**
	 * 获取用户Button
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/button", method = RequestMethod.GET)
	public @ResponseBody ModelMap getButton(@RequestParam long userId) {
		logger.info("get buttons...");
		ModelMap result = new ModelMap();
		result.put("data", getButtonsConfig(userId));
		return result;
	}

	private List<String> getButtonsConfig(long userId) {
		return relationService.findButtonsByUser(userId);
	}
}

/**
 * 主页面菜单
 *
 * @author xuxiang
 *
 */
class TreeMenu {
	private String id;
	private String code;
	private String text;
	private String iconCls;
	private boolean leaf = true;

	public TreeMenu(IPermission permission) {
		this.id = permission.getId();
		this.code = permission.getCode();
		this.text = permission.getDescription();
		this.leaf = permission.isLeaf();
		this.iconCls = permission.getIconCls();
	}

	public TreeMenu() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
