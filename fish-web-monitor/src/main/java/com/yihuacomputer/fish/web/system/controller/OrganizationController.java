package com.yihuacomputer.fish.web.system.controller;

import java.util.ArrayList;
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
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.OrganizationState;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.web.system.form.OrganizationForm;
import com.yihuacomputer.fish.web.system.form.OrganizationTreeForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 *
 * 机构管理
 *
 * @project fishbone-web
 * @file organizationController.java
 * @author wfy
 * @date 2010-6-17
 */
@Controller
@RequestMapping("/person/organization")
public class OrganizationController {

	private final Logger logger = org.slf4j.LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	private IOrganizationService service;

	@Autowired
	private IPersonService personService;

	@Autowired
	protected MessageSource messageSource;

	public OrganizationController() {
	}

	/**
	 *
	 * 增加机构信息
	 *
	 * @param form
	 * @return ModelMap
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap add(@RequestBody OrganizationForm form) {
		logger.info(" add org...");

		ModelMap result = new ModelMap();
		boolean isExist = this.isExistCode(form.getGuid(), form.getCode(), form.getOrganizationType());
		if (isExist) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.addDup", null, FishCfg.locale));
			return result;

		} else {
			IOrganization org = service.make();
			org.setCode(form.getCode());
			org.setName(form.getName());
			org.setAddress(form.getAddress());
			org.setZip(form.getZip());

			org.setOrganizationLevel(OrganizationLevel.getById(form.getOrgLevel()));

			if (form.getServiceObjectId() != 0) {
				org.setServiceObject(service.get(String.valueOf(form.getServiceObjectId())));
			}
			org.setDescription(form.getDescription());
			if (!form.getOrganizationType().isEmpty()) {
				org.setOrganizationType(OrganizationType.getById(Integer.parseInt(form.getOrganizationType())));
			}
			if (!form.getOrganizationState().isEmpty()) {
				org.setOrganizationState(OrganizationState.getById(Integer.parseInt(form.getOrganizationState())));
			}
			IOrganization parent = null;
			if (StringUtils.isNotEmpty(form.getParentId())) {
				parent = service.get(form.getParentId());
				if (parent == null) {
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.addNoParent", null, FishCfg.locale));
					return result;
				}
				org.setParent(parent);
			}
			service.add(org);

			// 回填值到form中
			form.setGuid(org.getGuid());

			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", form);
		}
		return result;
	}

	/**
	 *
	 * 根据ID删除机构
	 *
	 * @param id
	 * @return Map<String, Object>
	 */
	@SuppressWarnings("incomplete-switch")
	@RequestMapping(value = "/{guid}", method = RequestMethod.DELETE)
	public @ResponseBody ModelMap delete(@PathVariable String guid) {
		logger.info(" delete org: org.guid = " + guid);
		ModelMap result = new ModelMap();
		IOrganization organization = null;
		organization = service.get(guid);
		if (organization == null) {
			result.addAttribute(FishConstant.SUCCESS, true);
			return result;
		}
		List<Long> organizationIds = service.listSubOrgId(guid);
		if (organizationIds.size() > 1) {
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.delHasChild", null, FishCfg.locale));
			result.addAttribute("flag", false);
			result.addAttribute(FishConstant.SUCCESS, false);
			return result;
		}
		try {
			service.remove(guid);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			switch (organization.getOrganizationType()) {
			case BANK:
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.delHasRelation", null, FishCfg.locale));
				result.addAttribute("flag", true);
				result.addAttribute(FishConstant.SUCCESS, false);
				break;
			case MAINTAINER:
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.delSerHasRelation", null, FishCfg.locale));
				result.addAttribute("flag", true);
				result.addAttribute(FishConstant.SUCCESS, false);
				break;
			}
		}
		return result;
	}

	/**
	 *
	 * 根据ID更新机构信息
	 *
	 * @param id
	 * @param form
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/{guid}", method = RequestMethod.PUT)
	public @ResponseBody ModelMap update(@PathVariable String guid, @RequestBody OrganizationForm form, HttpServletRequest req) {
		logger.info(" update org : org.id = " + guid);
		ModelMap result = new ModelMap();

		boolean isExist = this.isExistCode(guid, form.getCode(), form.getOrganizationType());
		if (isExist) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.updateDup", null, FishCfg.locale));
			return result;
		}
		IOrganization organization = service.get(guid);
		if (organization == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));
			return result;
		}
		List<Long> subids = service.listSubOrgId(guid);
		for (Long id : subids) {
			if (form.getParentId().equals(String.valueOf(id))) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("organization.update.faileRule", null, FishCfg.locale));
				return result;
			}
		}
		organization.setCode(form.getCode());
		organization.setName(form.getName());
		organization.setAddress(form.getAddress());
		organization.setZip(form.getZip());
		organization.setDescription(form.getDescription());
		if (StringUtils.isNotEmpty(form.getParentId())) {
			IOrganization parent = service.get(form.getParentId());
			if (parent == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("organization.update.noParent", null, FishCfg.locale));
				return result;
			}
			organization.setParent(parent);
		}
		organization.setOrganizationLevel(OrganizationLevel.getById(form.getOrgLevel()));
		if (form.getServiceObjectId() != 0) {
			organization.setServiceObject(service.get(form.getServiceObjectId() + ""));
		}
		if (form.getUserGuid() != null) {
			if (Integer.valueOf(form.getUserGuid()) > 0) {
				organization.setManager(personService.get(form.getUserGuid()));
				form.setManager(personService.get(form.getUserGuid()).getName());
			} else {
				organization.setManager(null);
				form.setManager(null);
			}
		}
		organization.setOrganizationType(OrganizationType.getById(Integer.parseInt(form.getOrganizationType())));
		organization.setOrganizationState(OrganizationState.getById(Integer.parseInt(form.getOrganizationState())));
		service.update(organization);

		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		if (form.getCode().equals(userSession.getOrgCode())) {
			userSession.setOrgName(form.getName());
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", form);
		return result;
	}

	/**
	 *
	 * 组织迁移
	 *
	 * @param id
	 * @param form
	 * @return Map<String, Object>
	 */
	@RequestMapping(value = "/move", method = RequestMethod.POST)
	public @ResponseBody ModelMap move(@RequestParam String guid, @RequestParam String parentId, HttpServletRequest request, WebRequest webrequest) {
		logger.info(" move org : org.guid = " + guid);
		ModelMap result = new ModelMap();
		try {
			IOrganization org = service.get(guid);
			IOrganization parentOrg = service.get(parentId);
			if (org.getGuid().equals(parentId)) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("organization.move.failSelf", null, FishCfg.locale));
			} else if (isChild(org.getGuid(), parentId) == true) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("organization.move.failSelfDown", null, FishCfg.locale));
			} else {
				org.setParent(parentOrg);
				service.update(org);
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (NotFoundException nfe) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, String.format("%s,"+messageSource.getMessage("organization.move.refresh", null, FishCfg.locale), nfe.getMessage()));
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("user.processError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 根据Id获得组织机构：
	 *
	 * @param guid
	 * @return
	 */
	@RequestMapping(value = "/{guid}", method = RequestMethod.GET)
	public @ResponseBody ModelMap load(@PathVariable String guid, HttpServletRequest req) {
		ModelMap map = new ModelMap();
		OrganizationForm form = new OrganizationForm(service.get(guid));
		OrganizationForm min = null;
		if (null != req.getParameter("nowOrgCode") && !req.getParameter("nowOrgCode").isEmpty()) {
			min = new OrganizationForm(service.getMinOrgLevel(req.getParameter("nowOrgCode")));
		}
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute("data", form);
		map.addAttribute("min", min);
		return map;
	}

	/**
	 *
	 * 根据条件得到机构列表
	 *
	 * @param form
	 * @return Map<String,Object>
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search organization : start = %s ,limt = %s ", start, limit));

		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		OrganizationType orgType = userSession.getOrgType();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
						continue;
					} else if ("selectedNode".equals(name)) {
						filter.eq("parent.id", Long.valueOf(request.getParameter(name)));
					} else if ("type".equals(name)) {
						filter.eq("organizationType", OrganizationType.getById(Integer.valueOf(request.getParameter("type"))));
					} else if ("orgLevel".equals(name)) {
						filter.eq("organizationLevel", OrganizationLevel.getById(Integer.parseInt(request.getParameter(name))));
					} else if ("orgId".equals(name)) {
						filter.like("orgFlag",service.get(request.getParameter(name)).getOrgFlag()+  "%" );

					} else {
						filter.like(name, request.getParameter(name));
					}
				}

			}
		}
		IPageResult<IOrganization> pageResult = null;
		if (orgType != null && orgType.equals(OrganizationType.getById(Integer.valueOf(request.getParameter("type"))))) {
			pageResult = service.page(start, limit, filter, String.valueOf(userSession.getOrgId()));
		} else {
			pageResult = service.page(start, limit, filter);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", OrganizationForm.convert(pageResult.list()));
		return result;
	}

	/**
	 * 获得机构树：
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	public @ResponseBody List<OrganizationTreeForm> treeByType(@RequestParam String node, WebRequest request, HttpServletRequest httpRequest) {
		Iterable<IOrganization> childs = null;
		List<OrganizationTreeForm> result = new ArrayList<OrganizationTreeForm>();
		if (request.getParameter("type") == null || request.getParameter("type").isEmpty()) {
			childs = service.listSubOrg(node);
			for (IOrganization item : childs) {
				result.add(new OrganizationTreeForm(item));
			}
		} else if (!request.getParameter("type").equals("null")) {
			OrganizationType orgType = OrganizationType.getById(Integer.valueOf(request.getParameter("type")));
			String org = request.getParameter("orgId");
			String bcOrg = request.getParameter("bcOrg");
			if (null == org || "".equals(org))
				childs = service.listChildren(node, orgType);
			else if (null != bcOrg && !"".equals(bcOrg)) {
				IFilter filter = new Filter();
				filter.eq("parent.id", Long.parseLong(org));
				filter.eq("organizationType", OrganizationType.BANK);
				result.add(new OrganizationTreeForm(service.get(org)));
			} else {
				Long pId = service.get(org).getParent().getId();
				childs = service.get(String.valueOf(pId)).listChildren(orgType);
			}
			if (childs != null)
				for (IOrganization item : childs) {
					result.add(new OrganizationTreeForm(item));
				}
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
	private boolean isExistCode(String id, String code, String type) {
		try {
			logger.info("update.org.id is" + id);
			String orgType = "";
			IFilter filter = new Filter();
			filter.eq("code", code);
			List<IOrganization> list = (List<IOrganization>) service.list(filter);
			if (list.size() == 0) {
				return false;
			} else if (id == null || id.isEmpty()) {
				if (list.size() == 2) {
					return true;
				} else {
					orgType = String.valueOf(list.get(0).getOrganizationType().getId());
					if (type.equals(orgType)) {
						return true;
					}
					return false;
				}
			} else {
				IOrganization organization = service.get(id);
				if (code.equals(organization.getCode())) {
					return false;
				} else {
					for (IOrganization org : list) {
						orgType = String.valueOf(org.getOrganizationType().getId());
						if (type.equals(orgType)) {
							return true;
						}
					}
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取地市级银行信息
	 *
	 *
	 */
	@RequestMapping(value = "/queryServiceObject", method = RequestMethod.GET)
	public @ResponseBody ModelMap queryServiceObject() {
		logger.info(String.format("search organization : queryServiceObject"));
		ModelMap model = new ModelMap();
		List<IOrganization> organizationList = EntityUtils.convert(service.list());

		List<IOrganization> listTemp = new ArrayList<IOrganization>();
		for (IOrganization organization : organizationList) {
			String orgFlag = organization.getOrgFlag();
			String str[] = orgFlag.split("-");

			if (organization.getOrganizationType() != null && organization.getOrganizationType().getId() == 0) {
				if (str.length == 4) {
					listTemp.add(organization);
				} else if (str.length == 3) {
					List<Long> list = service.listSubOrgId(organization.getGuid());
					if (list == null || list.size() == 1) {
						listTemp.add(organization);
					}
				}
			}
		}
		model.put("data", OrganizationForm.convert(listTemp));
		model.put(FishConstant.SUCCESS, true);
		return model;
	}

	private boolean isChild(String orgId, String newParentId) {
		IOrganization newParentOrg = service.get(newParentId);
		IOrganization childOrg = service.get(orgId);
		String childOrgFlag = childOrg.getParent().getOrgFlag();
		if(childOrgFlag.equals(newParentOrg.getOrgFlag())){
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/queryMatching", method = RequestMethod.GET)
	@MethodNameDescrible(describle = "模糊匹配", argsContext = "name", isNumberArgs = false)
	public @ResponseBody ModelMap queryMatching(WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search organization : queryMatching"));
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();

		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
						continue;
					} else if ("type".equals(name)) {
						filter.eq("organizationType", OrganizationType.getById(Integer.valueOf(request.getParameter("type"))));
					} else if ("name".equals(name)) {
						filter.like(name, request.getParameter(name));
					}
				}
			}
		}
		if (null == filter.getValue("name") || "".equals(filter.getValue("name"))) {
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("total", 0);
			result.addAttribute("data", new OrganizationForm());
			return result;
		}

		List<IOrganization> list = service.listMatching(filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", list.size());
		result.addAttribute("data", OrganizationForm.convert(list));
		return result;
	}
}
