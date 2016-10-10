package com.yihuacomputer.fish.web.machine.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.yihuacomputer.common.util.EntityUtils;
import com.yihuacomputer.fish.api.atm.IAtmBrandService;
import com.yihuacomputer.fish.api.atm.IAtmCatalog;
import com.yihuacomputer.fish.api.atm.IAtmCatalogService;
import com.yihuacomputer.fish.api.atm.IAtmModule;
import com.yihuacomputer.fish.api.atm.IAtmModuleService;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeAtmModuleRelationService;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.atm.IAtmVendor;
import com.yihuacomputer.fish.api.device.CashType;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.web.machine.form.AtmBrandForm;
import com.yihuacomputer.fish.web.machine.form.AtmCatalogForm;
import com.yihuacomputer.fish.web.machine.form.AtmTypeForm;
import com.yihuacomputer.fish.web.machine.form.AtmTypeLinkModuleForm;

@Controller
@RequestMapping("/machine/atmType")
@ClassNameDescrible(describle="userlog.atmTypeController")
public class AtmTypeController {
    private Logger logger = LoggerFactory.getLogger(AtmTypeController.class);

    @Autowired
    private IAtmTypeService atmTypeService;

    @Autowired
    private IAtmBrandService atmBrandService;

    @Autowired
    private IAtmCatalogService atmCatalogService;

    @Autowired
    private IAtmTypeAtmModuleRelationService relationService;

    @Autowired
    private IAtmModuleService moduleService;

    @Autowired
    private IDeviceService deviceService;

    @Autowired
    protected MessageSource messageSource;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request) {
        logger.info(String.format("search type : start = %s ,limt = %s ", start, limit));
        IFilter filter = request2filter(request);
        ModelMap result = new ModelMap();

        IPageResult<IAtmType> pageResult = atmTypeService.page(start, limit, filter);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
        logger.info("type size:" + pageResult.getTotal());
        result.addAttribute(FishConstant.DATA, AtmTypeForm.convert(pageResult.list()));
        return result;
    }

    /**
     * 查找设备型号
     *
     * @return
     */

    @RequestMapping(value = "/queryAtmType", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryAtmType() {
        logger.info(String.format("search AtmType : queryAtmType"));
        ModelMap model = new ModelMap();
        List<IAtmType> atmTypeList = atmTypeService.list();
        model.put(FishConstant.DATA, AtmTypeForm.convert(atmTypeList));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@MethodNameDescrible(describle="userlog.atmTypeController.delete",hasArgs=false,urlArgs=true)
    public @ResponseBody ModelMap delete(@PathVariable long id) {
        logger.info(" delete AtmType: atmType.id = " + id);
        ModelMap result = new ModelMap();
        try {
            IAtmType type = atmTypeService.get(id);
            if (type != null) {
                List<IDevice> deviceList = deviceService.listDeviceByType(type);
                if (deviceList.size() > 0) {
                    result.addAttribute(FishConstant.SUCCESS, false);
                    result.addAttribute(FishConstant.ERROR_MSG,
                            messageSource.getMessage("atmType.bindAlready", null, FishCfg.locale));
                } else {
                    List<Long> relationedAtmModuleIdsInDB = relationService.findAtmModuleIds(id);
                    List<Long> relationedAtmModuleIdsInDBRemoved = new ArrayList<Long>(relationedAtmModuleIdsInDB);
                    for (Long removedId : relationedAtmModuleIdsInDBRemoved) {
                        relationService.unlink(id, removedId);
                    }

                    atmTypeService.remove(id);
                    result.addAttribute(FishConstant.SUCCESS, true);
                }
            } else {
                result.addAttribute(FishConstant.SUCCESS, true);
            }
        }
        catch (Exception e) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
            logger.error(String.format(messageSource.getMessage("commen.error", null, FishCfg.locale) + ","
                    + messageSource.getMessage("atmType.errorCode", null, FishCfg.locale) + "[%s]", e));
        }
        return result;
    }

    @RequestMapping(method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.atmTypeController.add",hasReqBodyParam=true,reqBodyClass=AtmTypeForm.class,bodyProperties="name")
    public @ResponseBody ModelMap add(@RequestBody AtmTypeForm form) {
        logger.info("add atmType");
        ModelMap result = new ModelMap();
        try {
            if (this.isExistCode(String.valueOf(form.getId()), form.getName())) {
                result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG,
                        messageSource.getMessage("atmType.typeDup", null, FishCfg.locale));
            } else {
                IAtmType type = atmTypeService.make();
                IAtmVendor vendor = atmBrandService.get(form.getDevVendorId());
                IAtmCatalog catalog = atmCatalogService.get(form.getDevCatalogId());
                if(vendor ==null||catalog==null){
                	result.addAttribute(FishConstant.SUCCESS, false);
                	result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("atmType.vendorCatalogNoExist", null, FishCfg.locale));
                	return result;
                }
                type.setDevCatalog(catalog);
                type.setDevVendor(vendor);
                type.setCashtype("1".equals(form.getCashtype()) ? CashType.CASH : CashType.NOT_CASH);
                type.setName(form.getName().trim());
                atmTypeService.add(type);
                relationService.link(type.getId(), form.getAtmModules());
                form.setId(type.getId());
                result.put(FishConstant.SUCCESS, true);
                result.addAttribute(FishConstant.DATA, new AtmTypeForm(type));
            }
        }
        catch (Exception e) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
            logger.error(String.format(messageSource.getMessage("commen.error", null, FishCfg.locale) + ","
                    + messageSource.getMessage("atmType.errorCode", null, FishCfg.locale) + "[%s]", e));
        }

        return result;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@MethodNameDescrible(describle="userlog.atmTypeController.update",hasReqBodyParam=true,reqBodyClass=AtmTypeForm.class,bodyProperties="name")
    public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody AtmTypeForm form) {
        logger.info("update AtmType: atmType.id = " + id);
        form.setId(id);
        ModelMap result = new ModelMap();
        try {
            IAtmType type = atmTypeService.get(id);
            if (type == null) {
                result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG,
                        messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));
            } else {
                if (this.isExistCode(String.valueOf(id), form.getName())) {
                    result.addAttribute(FishConstant.SUCCESS, false);
                    result.addAttribute(FishConstant.ERROR_MSG,
                            messageSource.getMessage("atmType.typeDup", null, FishCfg.locale));
                    result.addAttribute(FishConstant.DATA, new AtmTypeForm(type));
                } else {
                    type.setCashtype("1".equals(form.getCashtype()) ? CashType.CASH : CashType.NOT_CASH);
                    type.setName(form.getName().trim());
                    IAtmVendor atmVendor = atmBrandService.get(form.getDevVendorId());
                    type.setDevVendor(atmVendor);
                    IAtmCatalog atmCatalog = atmCatalogService.get(form.getDevCatalogId());
                    type.setDevCatalog(atmCatalog);
                    atmTypeService.update(type);
                    List<Long> atmModules = form.getAtmModules();
                    List<Long> relationedAtmModuleIdsInDB = relationService.findAtmModuleIds(id);
                    List<Long> atmModulesNew = new ArrayList<Long>(atmModules);
                    List<Long> relationedAtmModuleIdsInDBRemoved = new ArrayList<Long>(relationedAtmModuleIdsInDB);
                    atmModulesNew.removeAll(relationedAtmModuleIdsInDB);
                    relationService.link(id, atmModulesNew);
                    relationedAtmModuleIdsInDBRemoved.removeAll(atmModules);
                    for (Long removedId : relationedAtmModuleIdsInDBRemoved) {
                        relationService.unlink(id, removedId);
                    }

                    form.setDevVendorName(atmVendor.getName());
                    form.setDevCatalogName(atmCatalog.getName());
                    result.addAttribute(FishConstant.SUCCESS, true);
                    result.addAttribute(FishConstant.DATA, form);
                }
            }
        }
        catch (Exception e) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("commen.error", null, FishCfg.locale));
            logger.error(String.format(messageSource.getMessage("commen.error", null, FishCfg.locale) + ","
                    + messageSource.getMessage("atmType.errorCode", null, FishCfg.locale) + "[%s]", e));
        }
        return result;
    }

    @RequestMapping(value = "/queryAtmVendor", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryAtmVendor() {
        logger.info(String.format("search AtmType : queryAtmVendor"));
        ModelMap model = new ModelMap();
        List<IAtmVendor> atmVendorList = EntityUtils.convert(atmBrandService.list());
        model.put(FishConstant.DATA, AtmBrandForm.convert(atmVendorList));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    @RequestMapping(value = "/queryAtmCatalog", method = RequestMethod.GET)
    public @ResponseBody ModelMap queryAtmCatalog() {
        logger.info(String.format("search AtmType : queryAtmCatalog"));
        ModelMap model = new ModelMap();
        List<IAtmCatalog> atmVendorList = EntityUtils.convert(atmCatalogService.list());
        model.put(FishConstant.DATA, AtmCatalogForm.convert(atmVendorList));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/unique")
    public @ResponseBody ModelMap unique(@RequestParam String id, @RequestParam String no) {
        logger.info("type no unique checking...");
        ModelMap result = new ModelMap();
        result.addAttribute(FishConstant.SUCCESS, isExistCode(id, no));
        return result;
    }

    /**
     * 加载atm模块信息
     * 
     * @param atmModule
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/atmModule")
    public @ResponseBody ModelMap findModules(@RequestParam int atmTypeId) {
        logger.info(String.format("findModules : atmTypeId = %s", atmTypeId));
        ModelMap result = new ModelMap();
        String data = "";
        List<IAtmModule> atmModules = moduleService.list();
        if (atmTypeId == 0) {
            data = toModuleForm(atmModules, null);
        } else {
            data = toModuleForm(atmModules, relationService.findAtmModules(atmTypeId));
        }
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", data);
        return result;

    }

    public String toModuleForm(List<IAtmModule> atmModules, List<IAtmModule> checkeds) {
        StringBuffer result = new StringBuffer("");
        int length = atmModules.size();
        Map<Long, IAtmModule> atmModuleMaps = new HashMap<Long, IAtmModule>();
        if (checkeds != null) {
            for (IAtmModule atmModule : checkeds) {
                atmModuleMaps.put(atmModule.getId(), atmModule);
            }
        }
        for (int i = 0; i < length; i++) {
            IAtmModule atmModule = atmModules.get(i);
            result.append(",");
            result.append("{boxLabel:'").append(atmModule.getNote()).append("',");
            result.append("name:'atmModules',");
            if (checkeds != null) {
                IAtmModule checked = atmModuleMaps.get(atmModule.getId());
                if (checked != null) {
                    result.append("checked:true,");
                }
            }
            result.append("inputValue:").append(atmModule.getId()).append("}");
        }
        if (result.length() != 0) {
            result.deleteCharAt(0);
            result.insert(0, "[");
            result.append("]");
        } else {
            result.append("[]");
        }
        return result.toString();

    }

    /**
     * 检查编号唯一性
     *
     * @param id
     * @param no
     * @return
     */
    private boolean isExistCode(String id, String name) {
        try {
            logger.info("update.type.id is" + id);
            IAtmType type = atmTypeService.get(name.trim());
            logger.info("get.type.id is " + type.getId());
            if (Long.toString(type.getId()).equals(id)) {
                // 找到的Id和传入的Id相等，说明是同一个类型
                return false;
            } else {
                // 说明存在
                return true;
            }
        }
        catch (Exception e) {
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
                } else if (name.equals("cashtype")) {
                    String value = request.getParameter(name);
                    filter.eq(name, CashType.getById(Long.valueOf(value).intValue()));
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
     * 加载所有型号与模块的关联
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/atmLinkModule")
    public @ResponseBody ModelMap atmLinkModule() {
        logger.info(String.format("atmLinkModule"));
        ModelMap result = new ModelMap();

        AtmTypeLinkModuleForm resultForm = new AtmTypeLinkModuleForm();

        List<Object> list = relationService.list();

        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (Object obj : list) {
            Object[] objs = (Object[]) obj;

            IAtmType at = (IAtmType) objs[0];
            IAtmModule am = (IAtmModule) objs[1];
            // IAtmTypeAtmModuleRelation ar = (IAtmTypeAtmModuleRelation)
            // objs[2];

            // 已经存在,直接添加
            if (map.containsKey(at.getName())) {
                List<String> li = map.get(at.getName());
                li.add(am.getName());
                continue;
            }

            // 创建新的
            List<String> li = new ArrayList<String>();
            li.add(am.getName());
            map.put(at.getName(), li);

        }

        resultForm.setTypeLink(map);

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", resultForm);
        return result;

    }

}
