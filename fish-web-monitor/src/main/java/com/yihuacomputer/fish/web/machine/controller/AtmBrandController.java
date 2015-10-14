package com.yihuacomputer.fish.web.machine.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.atm.VendorStatus;
import com.yihuacomputer.fish.web.machine.form.AtmBrandForm;
import com.yihuacomputer.fish.web.machine.form.AtmTypeForm;
import com.yihuacomputer.fish.web.machine.form.ItemForm;

@Controller
@RequestMapping("/machine/atmBrand")
public class AtmBrandController {
	private Logger logger = LoggerFactory.getLogger(AtmBrandController.class);

	@Autowired
	private IAtmBrandService atmBrandService;
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IAtmTypeService atmTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search brand : start = %s ,limt = %s ", start, limit));
		logger.info("locale : " +request.getLocale().getDisplayName());
		IFilter filter = request2filter(request);
		ModelMap result = new ModelMap();
		IPageResult<IAtmVendor> pageResult = atmBrandService.page(start, limit, filter);
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		logger.info("brand size:" + pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, AtmBrandForm.convert(pageResult.list()));
		return result;
	}

	@RequestMapping(value = "/getTypeItem", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getTypeItem(WebRequest request) {
		ModelMap map = new ModelMap();
		String brand = request.getParameter("brand");
		List<IAtmType> list = new ArrayList<IAtmType>();
		List<ItemForm> forms = new ArrayList<ItemForm>();
		Iterable<IAtmType> atmTypes = new ArrayList<IAtmType>();
		boolean flag = false;
		if (brand == null || brand.trim().isEmpty() || brand.trim().equals("0")) {
			atmTypes = atmTypeService.list();
			flag = true;
		} else {
			IFilter filter = new Filter();
			filter.eq("devVendor.id", Long.parseLong(brand));

			atmTypes = atmTypeService.list(filter);
		}
		for (IAtmType atmType : atmTypes) {
			list.add(atmType);
		}
		forms = ItemForm.toTypeForms(list);
		if (flag) {
			forms.add(new ItemForm(messageSource.getMessage("c.brand.combox.all", null, request.getLocale()), "0"));
		}
		Collections.reverse(forms);
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute(FishConstant.DATA, forms);
		return map;
	}

	@RequestMapping(value = "/getTypeItem2", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getTypeItem2(WebRequest request) {
		ModelMap map = new ModelMap();
		String brand = request.getParameter("brand");
		List<IAtmType> list = new ArrayList<IAtmType>();
		List<ItemForm> forms = new ArrayList<ItemForm>();
		Iterable<IAtmType> atmTypes = new ArrayList<IAtmType>();
		if (brand == null || brand.trim().isEmpty() || brand.trim().equals("0")) {
			atmTypes = atmTypeService.list();
		} else {
			IFilter filter = new Filter();
			filter.eq("devVendor.id", Long.parseLong(brand));

			atmTypes = atmTypeService.list(filter);
		}
		for (IAtmType atmType : atmTypes) {
			list.add(atmType);
		}
		forms = ItemForm.toTypeForms(list);
		forms.add(new ItemForm(messageSource.getMessage("c.brand.combox.all", null, request.getLocale()), "0"));
		Collections.reverse(forms);
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute(FishConstant.DATA, forms);
		return map;
	}

	@RequestMapping(value = "/getBrandItem", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap getBrandItem(WebRequest request) {
		ModelMap map = new ModelMap();
		Iterable<IAtmVendor> atmVendors = atmBrandService.list();
		List<IAtmVendor> list = new ArrayList<IAtmVendor>();
		for (IAtmVendor vendor : atmVendors) {
			list.add(vendor);
		}

		List<ItemForm> forms = new ArrayList<ItemForm>();
		forms = ItemForm.toForms(list);
		forms.add(new ItemForm(messageSource.getMessage("c.brand.combox.all", null, request.getLocale()), "0"));
		Collections.reverse(forms);
		map.addAttribute(FishConstant.SUCCESS, true);
		map.addAttribute(FishConstant.DATA, forms);
		return map;
	}

	@RequestMapping(value = "/deleteQuery", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap deleteQuery(@RequestParam long id) {
		logger.info("query atmBrand : atmBrand.id" + id);
		ModelMap result = new ModelMap();
		Iterable<IAtmType> types = atmTypeService.list();
		IAtmVendor vendor = atmBrandService.get(id);
		for (IAtmType item : types) {

			result.addAttribute(FishConstant.SUCCESS, item.getDevVendor().getId() == vendor.getId() ? false : true);

		}
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete AtmBrand: atmBrand.id = " + id);
		ModelMap result = new ModelMap();
		if (atmBrandService.get(id) != null) {
			IAtmVendor vendor = atmBrandService.get(id);
			List<IAtmType> list = atmTypeService.listByBrand(vendor);
			if (list.size() > 0) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("atmBrand.bindAlready", null, FishCfg.locale));
			} else {
				atmBrandService.remove(id);
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} else {
			result.addAttribute(FishConstant.SUCCESS, true);
		}
		return result;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody AtmBrandForm request) {
		logger.info("add AtmBrand");
		ModelMap result = new ModelMap();
		if (this.isExistCode(String.valueOf(request.getId()), request.getName())) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("atmBrand.brandDup", null, FishCfg.locale));
		} else {
			IAtmVendor brand = atmBrandService.make();
			request.translate(brand);
			atmBrandService.add(brand);
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, new AtmBrandForm(brand));
		}

		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody AtmBrandForm request) {
		logger.info("update AtmBrand: atmBrand.id = " + id);
		ModelMap result = new ModelMap();
		IAtmVendor brand = atmBrandService.get(id);
		if (brand == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));
		} else {
			if (this.isExistCode(String.valueOf(id), request.getName())) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("atmBrand.brandDup", null, FishCfg.locale));
				result.addAttribute(FishConstant.DATA, new AtmBrandForm(brand));
			} else {
				request.translate(brand);
				atmBrandService.update(brand);
				result.addAttribute(FishConstant.SUCCESS, true);
				result.addAttribute(FishConstant.DATA, request);
			}
		}
		return result;
	}

	private boolean isExistCode(String id, String name) {
		try {
			logger.info("check.brand.id is" + id);
			IAtmVendor vendor = atmBrandService.get(name.trim().replace(" ", ""));
			logger.info("get.brand.id is " + vendor.getId());
			if (Long.toString(vendor.getId()).equals(id)) {
				// 找到的Id和传入的Id相等，说明是同一个品牌
				return false;
			} else {
				// 说明存在
				return true;
			}
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
				} else if (name.equals("sort")) {
					continue;
				} else if (name.equals("status")) {
					String value = request.getParameter(name);
					filter.eq(name, VendorStatus.getById(Long.valueOf(value).intValue()));
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

	/**
	 * 查找ATM品牌信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryAtmVendor", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap queryAtmVendor() {
		logger.info(String.format("search AtmVendor : queryAtmVendor"));
		ModelMap model = new ModelMap();
		List<IAtmVendor> atmVendorList = EntityUtils.convert(atmBrandService.list());
		model.put(FishConstant.DATA, AtmBrandForm.convert(atmVendorList));
		model.put(FishConstant.SUCCESS, true);
		return model;
	}

	/**
	 * 下拉框的联动 根据设备品牌查找设备型号
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryTypeByAtmVendor", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap queryAtmTypeByAtmBrand(@RequestParam long devVendorId) {
		logger.info(String.format("search AtmType By AtmBrand : queryTypeByAtmVendor"));
		ModelMap model = new ModelMap();
		Iterable<IAtmType> typeLists = atmTypeService.list();
		List<IAtmType> entities = new ArrayList<IAtmType>();
		for (IAtmType item : typeLists) {
			if (item.getDevVendor().getId() == devVendorId) {
				entities.add(item);
			}
		}
		model.put(FishConstant.DATA, AtmTypeForm.convert(entities));
		model.put(FishConstant.SUCCESS, true);
		return model;
	}

}
