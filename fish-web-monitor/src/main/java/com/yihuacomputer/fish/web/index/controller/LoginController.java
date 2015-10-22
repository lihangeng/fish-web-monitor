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
import com.yihuacomputer.fish.web.index.form.LoginBackForm;
import com.yihuacomputer.fish.web.index.form.Menu;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/login")
@ClassNameDescrible(describle="user.login")
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

	/**
	 * 登录并验证用户
	 */
	@MethodNameDescrible(describle="user.login.login",isNumberArgs=false,argsContext="username")
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap login(@RequestParam String username, @RequestParam String password, HttpSession session, HttpServletRequest request, WebRequest webrequest) {
		ModelMap result = new ModelMap();

		 //验证没有注册就进入注册页面。
		 if (!new DBType(sf.getHibernateProperties()).isMemDB() &&
		 FishCfg.isFishExpiry()) {
			 result.addAttribute(FishConstant.SUCCESS, false);
			 result.addAttribute("isRegister", false);
//			 result.addAttribute("message", "请进行系统注册.");
			 return result;
		 }

		try {
			IUser user = userService.login(username, password);
			IOrganization org = user.getOrganization();

			//@since 2.0.0.0 删除此逻辑
//			if (!user.getCode().equals("admin") && user.getAccessTime() != null && (new Date().getTime() - user.getAccessTime().getTime() > 1000l * 60 * 60 * 24 * 50)) {
//				result.addAttribute("isMessage", true);
//				result.addAttribute("message", "您已经超过50天没有修改密码了,请尽快修改密码,超过60天未修改密码,用户将被锁定.");
//			} else {
//				result.addAttribute("isMessage", false);
//			}
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("id", session.getId());
			result.addAttribute("userState", user.getState().getId());

			// 保存会话信息
			UserSession userSession = new UserSession();
			userSession.setUserId(user.getId());
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
			session.setAttribute(FishWebUtils.USER, userSession);
		} catch (AppException app) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute("message", app.getMessage());
		} catch (Exception e) {
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
			result.addAttribute(FishConstant.ERROR_MSG,  messageSource.getMessage("login.pwderror", null, FishCfg.locale));//"您输入的密码错误,请重新输入密码."
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
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.POST)
	public @ResponseBody ModelMap getMenu(@RequestParam long userId) {
		logger.info("get menus...");
		ModelMap result = new ModelMap();
		result.put("data", getMenuConfig(getMenuFormPermission(userId)));
		return result;
	}

	@RequestMapping(value = "/mymenu/{userId}", method = RequestMethod.GET)
	public @ResponseBody List<TreeMenu> tree(@RequestParam String node, @PathVariable long userId) {
		List<IPermission> permissions = userRoleRelation.listUserPermission(userId);
		List<TreeMenu> forms = new ArrayList<TreeMenu>();
		for (IPermission permission : permissions) {
			IPermission parent = permission.getParent();
			if (parent != null && parent.getCode().equals(node)) {
				TreeMenu menu = new TreeMenu(permission,hasChildMenu(permission,permissions));
				menu.setDesc(messageSource.getMessage("user.login.remark", null, FishCfg.locale));
				forms.add(menu);
			}
		}
		return forms;
	}

	@Autowired
	private IPermissionService service;

	private boolean hasChildMenu(IPermission self,List<IPermission> permissions){
		for(IPermission permission : permissions){
			if(permission.getParent() != null && permission.getParent().getCode().equals(self.getCode())){
				return true;
			}
		}
		return false;
	}

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

	private List<Menu> getMenuFormPermission(long userId) {
		List<IPermission> permissions = userRoleRelation.listUserPermission(userId);
		List<List<SimplePermission>> tempPermissions = getTempPermissions(permissions);

		List<SimplePermission> firsts = tempPermissions.get(0);
		List<SimplePermission> others = tempPermissions.get(1);

		List<Menu> menus = new ArrayList<Menu>();
		getSubMenu(menus, null, firsts, others);
		return menus;
	}

	/**
	 * 计算子菜单
	 *
	 * @param menus
	 * @param parent
	 * @param lists
	 * @param all
	 */
	private void getSubMenu(List<Menu> menus, Menu parent, List<SimplePermission> lists, List<SimplePermission> all) {
		for (SimplePermission p : lists) {
			createMenu(menus, parent, p, all);
		}
	}
	private void createMenu(List<Menu> menus, Menu parent, SimplePermission p, List<SimplePermission> all) {
		List<SimplePermission> child = getChild(p, all);
		Menu menu = null;
		if (child.size() > 0) {
			menu = new Menu(p.getText(), p.getAction());
		} else {
			menu = new Menu(p.getText(), p.getAction(), p.getAction());
		}

		if (parent == null) {
			menus.add(menu);
		}

		if (parent != null) {
			parent.addSubMenu(menu);
		}
		if (child.size() > 0) {
			getSubMenu(menus, menu, child, all);
		}
	}

	private List<SimplePermission> getChild(SimplePermission p, List<SimplePermission> all) {
		List<SimplePermission> child = new ArrayList<SimplePermission>();
		for (SimplePermission sp : all) {
			if (sp.getParentId().equalsIgnoreCase(p.getId())) {
				child.add(sp);
			}
		}
		return child;
	}

	private String getMenuConfig(List<Menu> menus) {
		StringBuffer config = new StringBuffer("");
		for (Menu menu : menus) {
			config.append(",").append(menu.toConfig());
		}
		if (config.length() != 0) {
			config.deleteCharAt(0);
			config.insert(0, "[");
			config.append("]");
		}
		return config.toString();
	}

	private List<List<SimplePermission>> getTempPermissions(List<IPermission> permissions) {
		List<List<SimplePermission>> result = new ArrayList<List<SimplePermission>>();
		List<SimplePermission> firsts = new ArrayList<SimplePermission>();
		List<SimplePermission> others = new ArrayList<SimplePermission>();
		for (IPermission p : permissions) {
			if (p.getParent() == null) {
				continue;
			}
			if (p.getParent().getCode().equals("root")) {
				firsts.add(new SimplePermission(p.getId(), p.getDescription(), p.getCode(), p.getCode(), p.getParent().getId()));
			} else {
				others.add(new SimplePermission(p.getId(), p.getDescription(), p.getCode(), p.getCode(), p.getParent().getId()));
			}
		}
		result.add(firsts);
		result.add(others);
		return result;
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
	private String text;
	private String desc;
	private boolean leaf = true;
	

	public TreeMenu(IPermission permission,boolean hasChild) {
		this.id = permission.getCode();
		this.text = permission.getDescription();
		this.leaf = !hasChild;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}
	protected void setDesc(String desc){
		
	}
	public String getDesc() {
		return desc;
	}

	public boolean isLeaf() {
		return leaf;
	}

}

class SimplePermission {
	private String id;
	private String text;
	private String cls;
	private String action;
	private String parentId;

	public SimplePermission(String id, String text, String cls, String action, String parentId) {
		this.id = id;
		this.text = text;
		this.cls = cls;
		this.action = action;
		this.parentId = parentId;
	}

	public String getText() {
		return text;
	}

	public String getCls() {
		return cls;
	}

	public String getAction() {
		return action;
	}

	public String getId() {
		return id;
	}

	public String getParentId() {
		return parentId;
	}

}
