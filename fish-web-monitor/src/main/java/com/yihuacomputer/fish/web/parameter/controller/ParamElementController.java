package com.yihuacomputer.fish.web.parameter.controller;

import java.util.Iterator;
import java.util.List;

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

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.parameter.IParamClassify;
import com.yihuacomputer.fish.api.parameter.IParamClassifyService;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.web.parameter.form.ParamClassifyForm;
import com.yihuacomputer.fish.web.parameter.form.ParamElementForm;

@Controller
@RequestMapping("/parameter/element")
public class ParamElementController {

	private Logger logger = LoggerFactory.getLogger(ParamElementController.class);

	@Autowired
	private IParamElementService elementService;

	@Autowired
	private IParamClassifyService classifyService;

    @Autowired
    protected MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request){
		logger.info(String.format("search element : start = %s ,limt = %s ", start, limit));
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IParamElement> pageResult = elementService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("element size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, ParamElementForm.convert(pageResult.list()));
		return result;

	}

	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody ParamElementForm request){
		logger.info("add elementelement");
		ModelMap result=new ModelMap();
		IParamElement element =elementService.make();
		IParamClassify classify=classifyService.get(request.getClassifyId());
		element.setParamName(request.getParamName());
		element.setParamValue(request.getParamValue());
		element.setParamType(request.getParamType());
		element.setParamClassify(classify);
		element.setVersionNo(request.getVersionNo());
		element.setParamRights(request.getParamRights());
		element.setParamBelongs(request.getParamBelongs());
		element.setRemark(request.getRemark());
		element.setCreateTime(request.nullDate(request.getCreateTime()));
		element.setLastModifyTime(request.nullDate(request.getLastModifyTime()));

		elementService.add(element);
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, new ParamElementForm(element));

		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete element: element.id = " + id);
		ModelMap result = new ModelMap();
		try {
			elementService.remove(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody ParamElementForm request) {
		logger.info("update elemet: elemet.id = " + id);
		ModelMap result = new ModelMap();
		request.setCreateTime(convert(request.getCreateTime()));
		request.setLastModifyTime(convert(request.getLastModifyTime()));
		IParamElement element = elementService.get(id);

		IParamClassify classify=classifyService.get(request.getClassifyId());
		element.setParamName(request.getParamName());
		element.setParamValue(request.getParamValue());
		element.setParamType(request.getParamType());
		element.setParamClassify(classify);
		element.setVersionNo(request.getVersionNo());
		element.setParamRights(request.getParamRights());
		element.setParamBelongs(request.getParamBelongs());
		element.setRemark(request.getRemark());
		element.setCreateTime(request.nullDate(request.getCreateTime()));
		element.setLastModifyTime(request.nullDate(request.getLastModifyTime()));

		elementService.update(element);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, request);
		return result;
	}

	@RequestMapping(value = "/elementClassify", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryAtmVendor() {
        logger.info(String.format("search element : queryClassify"));
        ModelMap model = new ModelMap();
        List<IParamClassify> cassifyList = EntityUtils.convert(classifyService.list());
        model.put(FishConstant.DATA, ParamClassifyForm.convert(cassifyList));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }


	private String convert(String string) {
		if (string == null || "".equals(string)) {
			return null;
		}else{
			if(string.contains("T")){
				String str=string.replace("T", " ");
				return str;
			}else{
				return string;
			}

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
				} else {
					if (name.equals("sort")) {
						continue;
					} else {
						filter.like(name, request.getParameter(name));
					}
				}
			}
		}

		return filter;

	}
	private boolean isNotFilterName(String name) {
		return "page".equals(name) || "start".equals(name) || "limit".equals(name) || "_dc".equals(name);
	}


}
