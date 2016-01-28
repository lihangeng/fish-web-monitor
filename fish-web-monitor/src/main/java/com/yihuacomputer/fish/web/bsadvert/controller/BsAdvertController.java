package com.yihuacomputer.fish.web.bsadvert.controller;

import java.util.ArrayList;
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

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertForm;

@Controller
@RequestMapping("bsadvert/advert")
public class BsAdvertController {
	
	private Logger logger = org.slf4j.LoggerFactory.getLogger(BsAdvertController.class);

	@Autowired
	private MessageSource messageSourceEnum;
	@Autowired
	private IBsAdvertService bsAdvertService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap getBsAdvert(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search bsAdvert group ");
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		IPageResult<IBsAdvert> pageResult = bsAdvertService.page(start, limit, filter);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}
	
	private List<BsAdvertForm> convert(List<IBsAdvert> advertList){
		List<BsAdvertForm> formList = new ArrayList<BsAdvertForm>();
		for(IBsAdvert bsAdvert:advertList){
			BsAdvertForm form = new BsAdvertForm();
			form.setActived(bsAdvert.getActived());
			form.setActivePersonId(bsAdvert.getActivePersonId());
//			form.setActivePersonName(activePersonName);
			form.setAdvertName(bsAdvert.getAdvertName());
			form.setGroupId(bsAdvert.getGroupId());
//			form.setGroupName(bsAdvert.get);
			form.setId(bsAdvert.getId());
			form.setLastTime(DateUtils.getDate(bsAdvert.getLastTime()));
			form.setPersonId(bsAdvert.getPersonId());
//			form.setPersonName();
			formList.add(form);
		}
		return formList;
	}
}
