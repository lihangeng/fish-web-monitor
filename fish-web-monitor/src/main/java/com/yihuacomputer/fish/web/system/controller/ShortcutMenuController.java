package com.yihuacomputer.fish.web.system.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.permission.IPermission;
import com.yihuacomputer.fish.api.permission.IPermissionService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.relation.IUserPermissionRelation;
import com.yihuacomputer.fish.web.system.form.MenuTreeForm;
import com.yihuacomputer.fish.web.system.form.ShortcutMenuCheckedTreeForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("/shortcutMenu")
public class ShortcutMenuController {

	@Autowired
	private IPermissionService service;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserPermissionRelation userPermissionRelation;

	private Logger logger = org.slf4j.LoggerFactory.getLogger(PermissionController.class);

	@RequestMapping(value = "/checkedTree", method = RequestMethod.GET)
	public @ResponseBody
	List<ShortcutMenuCheckedTreeForm> checkedTree(WebRequest request, HttpSession session) {
		logger.info("checkedTree...");
		String node = request.getParameter("node");
		String userId = request.getParameter("userId");
		long id = Long.parseLong(userId);

		IUser user = userService.get(id);
		Iterable<IPermission> data = service.get(node).listChildren();
		//转换成list
		List<IPermission> datalist = EntityUtils.convert(data);
		//对权限进行排序
		Collections.sort(datalist, new Comparator<IPermission>() {
			public int compare(IPermission o1, IPermission o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
//		List<IPermission> permissions = this.getPermissionsByUser(user);

		List<IPermission> permissions = userPermissionRelation.listPermissionsByUser(id);
		List<IPermission> list = new ArrayList<IPermission>();
		for (IPermission item : datalist) {
			if (permissions.contains(item)) {
				list.add(item);
			}
		}
		List<IPermission> permissionList = new ArrayList<IPermission>();
		if (StringUtils.isNotEmpty(userId) && !userId.equalsIgnoreCase("0")) {

			permissionList = userPermissionRelation.listShortMenuByUser(user);
		}
		return ShortcutMenuCheckedTreeForm.convert(list, permissionList);
	}

	@RequestMapping(value = "/editShortcutMenu", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap edit(@RequestParam String permissions, HttpSession session) {
		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) session.getAttribute(FishWebUtils.USER);
		long UserId = userSession.getUserId();
		IUser user = userService.get(UserId);

		List<IPermission> list = userPermissionRelation.listShortMenuByUser(user);
		List<String> codesInDB = new ArrayList<String>();
		for (IPermission each : list) {
			codesInDB.add(each.getCode());
		}
		List<String> commons = new ArrayList<String>();
		List<String> codesInPage = toList(permissions);

		// 找出数据库和页面的共同元素
		for (String code : codesInPage) {
			if (codesInDB.contains(code)) {
				commons.add(code);
			}
		}
		// 删除共同的元素
		if (commons.size() > 0) {
			codesInDB.removeAll(commons);
			codesInPage.removeAll(commons);
		}

		// 删除关系
		for (String code : codesInDB) {
			IPermission p = service.get(code);
			userPermissionRelation.unlink(user, p);
		}

		// 增加关系
		for (String code : codesInPage) {
			IPermission p = service.get(code);
			userPermissionRelation.link(user, p);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}

	private List<String> toList(String permissions) {
		List<String> lists = new ArrayList<String>();
		if (StringUtils.isNotEmpty(permissions)) {
			String[] str = permissions.split(",");
			for (String item : str) {
				lists.add(item);
			}
		}
		return lists;
	}

	/**
	 * 列出user下面的权限
	 */
	@RequestMapping(value = "/listShortcutMenu", method = RequestMethod.GET)
	public @ResponseBody
	List<MenuTreeForm> listShortcutMenu(WebRequest request, @RequestParam String node, HttpSession session) {
		logger.info("listShortcutMenu...");
		UserSession userSession = (UserSession) session.getAttribute(FishWebUtils.USER);
		long UserId = userSession.getUserId();
		IUser user = userService.get(UserId);
		List<IPermission> permissions = userPermissionRelation.listShortMenuByUser(user);
		//对权限进行排序
		Collections.sort(permissions, new Comparator<IPermission>() {
			public int compare(IPermission o1, IPermission o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});
		List<MenuTreeForm> forms = new ArrayList<MenuTreeForm>();
		for (IPermission item : permissions) {
			if(!hasSubMenu(item)){
				forms.add(new MenuTreeForm(item));
			}
		}


		return forms;
	}

	/**
	 * 检查下面是否有子菜单
	 *
	 * @param permissions
	 * @return
	 */
	private boolean hasSubMenu(IPermission permission) {
		for (IPermission item : permission.listChildren()) {
			if(!item.isButton()){
				return true;
			}
		}
		return false;
	}

}
