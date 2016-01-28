package com.yihuacomputer.fish.web.bsadvert.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertGroupForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("bsadvert/advertgroup")
public class BsAdvertGroupController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(BsAdvertGroupController.class);

	@Autowired
	private MessageSource messageSourceEnum;
	@Autowired
	private IAdvertGroupService advertGroupService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap getAdvertGroup(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search bsAdvert group ");
		ModelMap result = new ModelMap();
		IFilter filter = getFilter(webRequest);
		IPageResult<Object> pageResult = advertGroupService.page(start, limit, filter);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}

	private String getEnumI18n(String enumText) {
		if (null == enumText) {
			return "";
		}
		return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
	}

	public List<BsAdvertGroupForm> convert(List<Object> groupList) {
		List<BsAdvertGroupForm> bsGroupList = new ArrayList<BsAdvertGroupForm>();
		for (Object object : groupList) {
			Object[] result = (Object[]) object;
			BsAdvertGroupForm bsAdvertGroupForm = new BsAdvertGroupForm();
			IAdvertGroup advertGroup = (IAdvertGroup) result[0];
			IOrganization org = (IOrganization) result[1];
			bsAdvertGroupForm.setGroupName(advertGroup.getGroupName());
			bsAdvertGroupForm.setOrgId(org.getId());
			if (null != org.getOrganizationLevel()) {
				bsAdvertGroupForm.setOrgLevel(getEnumI18n(org.getOrganizationLevel().getText()));
				bsAdvertGroupForm.setOrgLevelId(org.getOrganizationLevel().getId());
			}
			bsAdvertGroupForm.setOrgName(org.getName());
			bsAdvertGroupForm.setGroupType(getEnumI18n(advertGroup.getGroupType().getName()));
			bsAdvertGroupForm.setId(advertGroup.getId());
			bsGroupList.add(bsAdvertGroupForm);
		}

		return bsGroupList;
	}

	private IFilter getFilter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				String value = request.getParameter(name);
				if (org.apache.commons.lang.StringUtils.isEmpty(value)) {
					continue;
				}
				if (name.equals("orgId")) {
					filter.eq("orgId", Long.valueOf(value));
				}
				if (name.equals("orgLevel")) {
					filter.eq("orgLevel", OrganizationLevel.getById(Integer.parseInt(value)));
				}
				if (name.equals("groupName")) {
					filter.eq("groupName", value);
				}
			}
		}
		return filter;
	}
}
