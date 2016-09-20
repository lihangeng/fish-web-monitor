package com.yihuacomputer.fish.web.system.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.permission.IRole;
import com.yihuacomputer.fish.api.person.Gender;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.IPerson;
import com.yihuacomputer.fish.api.person.IPersonJob;
import com.yihuacomputer.fish.api.person.IPersonJobService;
import com.yihuacomputer.fish.api.person.IPersonService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.OrganizationType;
import com.yihuacomputer.fish.api.person.PersonState;
import com.yihuacomputer.fish.api.person.PersonType;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.relation.IDevicePersonRelation;
import com.yihuacomputer.fish.api.relation.IUserRoleRelation;
import com.yihuacomputer.fish.machine.entity.Device;
import com.yihuacomputer.fish.person.service.db.SrcbDevicePersonRelation;
import com.yihuacomputer.fish.system.entity.Person;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.system.form.PersonDeviceForm;
import com.yihuacomputer.fish.web.system.form.PersonForm;
import com.yihuacomputer.fish.web.system.form.PersonJobForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 人员基本信息控制：
 *
 * @author huxiaobao
 *
 */
@Controller
@RequestMapping("/person/person")
@ClassNameDescrible(describle="userlog.PersonController")
public class PersonController {
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService service;

    @Autowired
    private IOrganizationService organizationService;
   
    @Autowired
    private SrcbDevicePersonRelation srcbDevicePersonRelation;
    

    @Autowired
    private IUserService userService;

    @Autowired
    private IDevicePersonRelation devicePersonRelation;

    @Autowired
    private IUserRoleRelation userRoleRelation;

    /**
     * 设备接口
     */
    @Autowired
    private IDeviceService deviceService;
    
    
    @Autowired
	protected MessageSource messageSource;

    @Autowired
    private IPersonJobService personJobService;

    public PersonController() {
    }

    /**
     *
     * 根据人员Id获得关联设备列表
     *
     * @param form
     * @return ModelMap<String, Object>
     */
    @RequestMapping(value = "/linkdeDevice", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap searchLinkedDevice(@RequestParam int start, @RequestParam int limit, @RequestParam int flag,
            @RequestParam String guid, @RequestParam String organizationId, WebRequest request, HttpServletRequest req) {
        logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        OrganizationType type = organizationService.get(organizationId).getOrganizationType();
        IPageResult<IDevice> pageResult = null;
        if (flag == 0) {// 已关联的设备
            result.addAttribute(FishConstant.SUCCESS, true);
            Filter filter = new Filter();
            filter.addFilterEntry(FilterFactory.like("terminalId", request.getParameter("terminalId")));
            if (OrganizationType.BANK.equals(userSession.getOrgType())) {
                pageResult = devicePersonRelation.pageDeviceByTypePerson(start, limit, service.get(guid), filter,
                        String.valueOf(userSession.getOrgId()), true);
                if(pageResult.list().size()==0){
                	pageResult = devicePersonRelation.pageDeviceByTypePerson(0, limit, service.get(guid), filter,
                            String.valueOf(userSession.getOrgId()), true);
                }
            } else if (OrganizationType.MAINTAINER.equals(userSession.getOrgType())) {
                pageResult = devicePersonRelation.pageDeviceByTypePerson(start, limit, service.get(guid), filter,
                        String.valueOf(userSession.getOrgId()), false);
                if(pageResult.list().size()==0){
                	pageResult = devicePersonRelation.pageDeviceByTypePerson(0, limit, service.get(guid), filter,
                            String.valueOf(userSession.getOrgId()), false);
                }
            } else {
                pageResult = devicePersonRelation.pageDeviceByPerson(start, limit, service.get(guid), filter);
                if(pageResult.list().size()==0){
                	 pageResult = devicePersonRelation.pageDeviceByPerson(0, limit, service.get(guid), filter);
                }
            }
            result.addAttribute("total", pageResult.getTotal());
            result.addAttribute("data", DeviceForm.convert(pageResult.list()));
        } else {// 可以关联的设备
            IFilter filter = new Filter();
            filter.like("terminalId", request.getParameter("terminalId"));
            if (OrganizationType.BANK.equals(userSession.getOrgType())) {
                if (OrganizationType.BANK.equals(type)) {
                    pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(start, limit, service.get(guid), filter,
                            organizationId, organizationService.getRoot().get(0).getGuid());
                    if(pageResult.list().size()==0){
                    	pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(0, limit, service.get(guid), filter,
                                organizationId, organizationService.getRoot().get(0).getGuid());
                    }
                } else {
                    pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(start, limit, service.get(guid), filter,
                            String.valueOf(userSession.getOrgId()), organizationId);
                    if(pageResult.list().size()==0){
                    	pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(0, limit, service.get(guid), filter,
                                String.valueOf(userSession.getOrgId()), organizationId);
                    }
                }
            } else if (OrganizationType.MAINTAINER.equals(userSession.getOrgType())) {
                if (OrganizationType.BANK.equals(type)) {
                    pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(start, limit, service.get(guid), filter,
                            organizationId, String.valueOf(userSession.getOrgId()));
                    if(pageResult.list().size()==0){
                    	pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(0, limit, service.get(guid), filter,
                                organizationId, String.valueOf(userSession.getOrgId()));
                    }
                } else {
                    pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(start, limit, service.get(guid), filter,
                            organizationService.getRoot().get(0).getGuid(), organizationId);
                    if(pageResult.list().size()==0){
                    	pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(0, limit, service.get(guid), filter,
                                organizationService.getRoot().get(0).getGuid(), organizationId);
                    }
                }
            } else {
                if (OrganizationType.BANK.equals(type)) {
                    pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(start, limit, service.get(guid), filter,
                            organizationId, organizationService.getRoot().get(0).getGuid());
                    if(pageResult.list().size()==0){
                    	pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(0, limit, service.get(guid), filter,
                                organizationId, organizationService.getRoot().get(0).getGuid());
                    }
                } else {
                    pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(start, limit, service.get(guid), filter,
                            organizationService.getRoot().get(0).getGuid(), organizationId);
                    if(pageResult.list().size()==0){
                    	pageResult = devicePersonRelation.pageUnlinkDeviceByPerson(0, limit, service.get(guid), filter,
                                organizationService.getRoot().get(0).getGuid(), organizationId);
                    }
                }
            }
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute("total", pageResult.getTotal());
            result.addAttribute("data", DeviceForm.convert(pageResult.list()));
        }
        return result;
    }

    /**
     * 解除关联关系：
     *
     * @param personId
     * @param deviceId
     * @return
     */
    @MethodNameDescrible(describle="userlog.PersonController.unlink",hasArgs=true,argsContext="personId")
    @RequestMapping(value = "/unlink", method = RequestMethod.POST)
    
    public @ResponseBody
    ModelMap unlink(@RequestParam String personId, @RequestParam String deviceId) {
        ModelMap result = new ModelMap();
        String[] ids = deviceId.split(",");
        try {
            for (String id : ids) {
                devicePersonRelation.unlink(service.get(personId), deviceService.get(Long.valueOf(id)));
            }
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception ex) {
            logger.info(ex.getMessage());
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }
    
    
    @RequestMapping(value = "/showOrgManger", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap showOrgManger(@RequestParam String terminalId) {
        ModelMap result = new ModelMap();
        try {
            IDevice device = deviceService.get(terminalId);
            
            IPerson person = device.getOrganization().getManager();
            
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute("data", new PersonForm(person));
        }
        catch (Exception ex) {
            logger.info(ex.getMessage());
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }

    /**
     * 建立关联关系：
     *
     * @param request
     * @return
     */
    @MethodNameDescrible(describle="userlog.PersonController.link",hasArgs=false)
    @RequestMapping(value = "/link", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap link(@RequestBody PersonDeviceForm request) {
        logger.info(String.format("device %s linked  %s", request.getPersonId(), request.getDeviceId()));
        ModelMap result = new ModelMap();
        IPerson person = service.get(String.valueOf(request.getPersonId()));
        IDevice device = deviceService.get(request.getDeviceId());
        List<IDevice> list = devicePersonRelation.listDeviceByPerson(person);
        if (list.contains(device)) {
            result.put(FishConstant.SUCCESS, true);
            return result;
        }
        devicePersonRelation.link(person, device);
        result.put(FishConstant.SUCCESS, true);
        result.put("data", request);
        return result;
    }

    /**
     *
     * 方法描述 : 根据Name增加Person
     *
     * @param form
     * @return ModelMap<String, Object>
     */
    @MethodNameDescrible(describle="userlog.PersonController.add",hasArgs=false)
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    ModelMap add(@RequestBody PersonForm form) {
        logger.info("add Person");
        ModelMap result = new ModelMap();
        IPerson person = service.make();
        person.setCode(form.getCode());
        person.setEmail(form.getEmail());
        person.setGender(form.getGender());
        person.setMobile(form.getMobile());
        person.setName(form.getName());
        person.setPhone(form.getPhone());
        person.setType(PersonType.getById(Integer.parseInt(form.getType())));
        person.setState(PersonState.getById(Integer.parseInt(form.getState())));
        person.setJobNum(form.getJobNum());
        person.setRemark(form.getRemark());
        if (form.getBirthday() != null && !form.getBirthday().isEmpty()) {
            try {
                person.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(form.getBirthday()));
                form.setBirthday(DateUtils.getDate(person.getBirthday()));
            }
            catch (ParseException e) {
                logger.info(e.getMessage());
            }
        }
        IOrganization organization = null;
        if (StringUtils.isNotEmpty(form.getOrganizationId())) {
            organization = organizationService.get(form.getOrganizationId());
            if(null==organization){
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.notExist", null, FishCfg.locale));
                return result;
            }
            person.setOrganization(organization);
        }

        // 岗位信息
        IPersonJob personJob = null;
        if (StringUtils.isNotEmpty(form.getPersonJobCode())) {
            personJob = personJobService.get(form.getPersonJobCode());
            person.setPersonJob(personJob);
        }

        // 预留字段
        person.setReserve1(form.getReserve1());
        person.setReserve2(form.getReserve2());
        person.setReserve3(form.getReserve3());

        service.add(person);
        form.setGuid(person.getGuid());
        if (person.getOrganization() != null) {
            form.setOrganizationId(person.getOrganization().getGuid());
            form.setOrganizationName(person.getOrganization().getName());
        }
        result.put(FishConstant.SUCCESS, true);
        result.addAttribute("data", form);
        return result;
    }

    /**
     *
     * 根据GUID删除用户
     *
     * @param guid
     * @return ModelMap<String, Object>
     */
    @MethodNameDescrible(describle="userlog.PersonController.delete",hasArgs=false,urlArgs=true)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ModelMap delete(@PathVariable String id) {
        logger.info(" delete person: person.guid = " + id);
        ModelMap result = new ModelMap();
        if (service.get(id) != null) {
            IFilter filter = new Filter();
            filter.eq("personId", Long.valueOf(id));
            if (organizationService.list(filter).iterator().hasNext()) {
                result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delManager", null, FishCfg.locale));
                return result;
            }
            IPerson person = service.get(id);
            List<IDevice> deviceList = devicePersonRelation.listDeviceByPerson(person);
            // 是否与人员有关联
            if (deviceList != null && !deviceList.isEmpty()) {
                result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delDevRela", null, FishCfg.locale));
                return result;
            }
            filter = new Filter();
            filter.eq("person.guid", id);
            if (!userService.list(filter).iterator().hasNext()) {
                try {
                    service.remove(id);
                    result.addAttribute(FishConstant.SUCCESS, true);
                }
                catch (Exception ex) {
                    result.addAttribute(FishConstant.SUCCESS, false);
                    result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delError", null, FishCfg.locale));
                }
            } else {
                result.addAttribute(FishConstant.SUCCESS, false);
                result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.delAcc", null, FishCfg.locale));
            }
        } else {
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        return result;
    }

    /**
     *
     * 方法描述 : 根据GUID更新用户
     *
     * @param guid
     * @param request
     * @return ModelMap<String, Object>
     */
    @MethodNameDescrible(describle="userlog.PersonController.update",hasArgs=false,urlArgs=true)
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public @ResponseBody
    ModelMap update(@PathVariable String id, @RequestBody PersonForm form) {
        logger.info("update Person: Person.guid = " + id);
        form.setGuid(id);
        ModelMap result = new ModelMap();
        IPerson person = service.get(id);
        if (person == null) {
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.updateNotExist", null, FishCfg.locale));
            return result;
        }
        try {
            if (form.getOrganizationId() != null) {
                IOrganization oldOrganization = person.getOrganization();
                IOrganization newOrganization = organizationService.get(form.getOrganizationId());
                if(null==newOrganization){
    				result.addAttribute(FishConstant.SUCCESS, false);
    				result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("org.notExist", null, FishCfg.locale));
                    return result;
                }
                if (oldOrganization.getId()!=newOrganization.getId()) {
                    IFilter filter = new Filter();
                    filter.eq("personId",  Long.valueOf(id));
                    if (organizationService.list(filter).iterator().hasNext()) {
                        result.addAttribute(FishConstant.SUCCESS, false);
                        result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("person.updateManager", null, FishCfg.locale));
                        return result;
                    }
                }
                person.setOrganization(newOrganization);
            }
        }
        catch (Exception e) {
            e.getStackTrace();
        }
        person.setCode(form.getCode());
        person.setName(form.getName());
        if (form.getBirthday() != null && !form.getBirthday().isEmpty()) {
            try {
                person.setBirthday(new SimpleDateFormat("yyyy-MM-dd").parse(form.getBirthday()));
                form.setBirthday(new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthday()));
            }
            catch (ParseException e) {
                logger.info(e.getMessage());
            }
        }
        person.setEmail(form.getEmail());
        person.setGender(form.getGender());
        if (person.getOrganization() != null) {
            form.setOrganizationName(person.getOrganization().getName());
        }
        person.setMobile(form.getMobile());
        person.setPhone(form.getPhone());
        person.setType(PersonType.getById(Integer.parseInt(form.getType())));
        person.setState(PersonState.getById(Integer.parseInt(form.getState())));
        person.setJobNum(form.getJobNum());
        person.setRemark(form.getRemark());


        if (!(form.getPersonJobCode() == null || "".equals(form.getPersonJobCode()))) {
            IPersonJob newPersonJob = personJobService.get(form.getPersonJobCode());
            person.setPersonJob(newPersonJob);
            form.setPersonJobName(newPersonJob.getName());
        }

        person.setReserve1(form.getReserve1());
        person.setReserve2(form.getReserve2());
        person.setReserve3(form.getReserve3());

        service.update(person);

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("data", form);
        return result;
    }

    /**
     * 根据条件得到人员列表
     *
     * @param start
     * @param limit
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody
    ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
        logger.info(String.format("search person : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();
        String atmCode = "";
        String role = "";
        UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
        // 登陆人员类型（银行为0，维护商为1）
        OrganizationType orgType = userSession.getOrgType();
        String perType = null;
        // 如果单击机构树节点，深度查询该机构树节点机构以及下属机构下的人员信息
//        if (request.getParameter("selectedNode") != null && !request.getParameter("selectedNode").isEmpty()) {
//            IPageResult<IPerson> pageResult = service.page(start, limit, request.getParameter("selectedNode"));
//            result.addAttribute(FishConstant.SUCCESS, true);
//            result.addAttribute("total", pageResult.getTotal());
//            result.addAttribute("data", PersonForm.convert(pageResult.list()));
//            return result;
//        }

        // 单击查询按钮，带入查询条件：
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();
        while (iterator.hasNext()) {
            String name = iterator.next();
            if (FishWebUtils.isIgnoreRequestName(name)||"org".equals(name)) {
                continue;
            } else {
                String value = request.getParameter(name);
                if (value.isEmpty()) {
                    continue;
                } else {
                    if ("sort".equals(name)) { // 去掉前端页面传来的sort排序字段
                        continue;
                    }
                    else if ("organizationId".equals(name)) {
                    	filter.eq("organization", organizationService.get(value));
                    } else if ("selectedNode".equals(name)) {
                    	filter.like("organization.orgFlag", organizationService.get(value).getOrgFlag()+'%');
                    } else if ("gender".equals(name)) {
                    	filter.eq("gender",  Gender.valueOf(value));
                    } else if ("jobNum".equals(name)) {
                    	filter.like("jobNum", "%" + value + "%");
                    } else if ("type".equals(name)) {
                        perType = value;
                        filter.eq("type", PersonType.getById(Integer.parseInt(value)));
                    } else if ("state".equals(name)) {
                    	filter.eq("state",  PersonState.getById(Integer.parseInt(value)));
                    } else if ("atmCode".equals(name)) {
                        atmCode = value;
                    } else if ("role".equals(name)) {
                        role = value;
                    } else if ("personJobCode".equals(name)) {
                    	filter.eq("personJob",  personJobService.get(value));
                    } else {
                    	filter.like(name, value);
                    }
                }
            }
        }

        IPageResult<IPerson> pageResult = null;
        List<IPerson> list = null;
        // 判断登录人员是哪个机构下的，过滤该机构或该机构下属以外的机构人员
        // 由于人员类型和机构类型一致（银行为0，维护商为1），故此处简单判断（如果相同，即有权限查询）
        if (orgType != null && perType != null && orgType.equals(OrganizationType.getById(Integer.valueOf(perType)))) {
            pageResult = service.page(start, limit, String.valueOf(userSession.getOrgId()), filter);
            list = pageResult.list();
        } else {
            pageResult = service.page(start, limit, filter);
            list = pageResult.list();
        }
        List<IPerson> listTemp = new ArrayList<IPerson>();
        List<IPerson> listTemp1 = new ArrayList<IPerson>();
        if (!atmCode.isEmpty()) {
            for (IPerson person : list) {
                IDevice device = deviceService.get(atmCode);
                if (device != null && device.getDevService().getGuid().equals(person.getOrganization().getGuid())) {
                    listTemp.add(person);
                }
            }
            list = listTemp;
        }
        // 根据角色进行二次过滤
        if (role != null && !role.isEmpty()) {
            for (IPerson person : listTemp) {
                IUser user = userService.get(Long.parseLong(person.getGuid()));
                List<IRole> roles = userRoleRelation.listRoleByUser(user);
                for (IRole r : roles) {
                    if (r.getDescription().equals(role)) {
                        listTemp1.add(person);
                        continue;
                    }
                }
            }
            list = listTemp1;
        }

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", PersonForm.convert(list));
        return result;
    }

    /**
     *
     * 根据条件得到用户列表
     *
     * @param form
     * @return ModelMap<String, Object>
     */
    @RequestMapping(value = "/lookup/unlink", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap unlink(@RequestParam int start, @RequestParam int limit, WebRequest request) {
        logger.info(String.format("search user : start = %s ,limt = %s ", start, limit));
        IFilter filter = new Filter();
        Iterator<String> iterator = request.getParameterNames();

        while (iterator.hasNext()) {
            String name = iterator.next();
            if ("start".equals(name) || "limit".equals(name) || name.startsWith("_dc")) {
                continue;
            } else if ("parentId".equals(name)) {
            	filter.eq("organization.id", request.getParameter(name));
            }
        }

        String name = request.getParameter("name");
        if (null != name) {
        	filter.like("name", name);
            logger.info("name : " + name);
        }

        ModelMap result = new ModelMap();
        IPageResult<IPerson> pageResult = service.pageUnlinkUser(start, limit, filter);

        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        if (pageResult.getTotal() > 0) {
            result.addAttribute("isPerson", "true");
        } else {
            result.addAttribute("isPerson", "false");
        }

        result.addAttribute("data", PersonForm.convert(pageResult.list()));
        return result;
    }

    /**
     *
     * 根据条件得到用户列表
     *
     * @param form
     * @return ModelMap<String, Object>
     */
    @RequestMapping(value = "/lookup/org", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap lookByorg(@RequestParam int start, @RequestParam int limit, @RequestParam String selectedNode) {
        logger.info(String.format("search user : start = %s ,limt = %s ", start, limit));
        ModelMap result = new ModelMap();
        IPageResult<IPerson> pageResult = service.page(start, limit, selectedNode);
        result.addAttribute(FishConstant.SUCCESS, true);
        result.addAttribute("total", pageResult.getTotal());
        result.addAttribute("data", PersonForm.convert(pageResult.list()));
        return result;
    }
    /**
	 * 根据条件得到人员列表
	 *
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/selectPerson", method = RequestMethod.GET)
	public @ResponseBody
	ModelMap selectPerson(@RequestParam int start, @RequestParam int limit, WebRequest request, HttpServletRequest req) {
		logger.info(String.format("search person : start = %s ,limt = %s ", start, limit));
		ModelMap result = new ModelMap();

		Map<String, String> map = new HashMap<String, String>();
		Iterator<String> iterator = request.getParameterNames();
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		map.put("orgFlag", userSession.getOrgFlag());
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name)) {
				continue;
			} else {
				if (request.getParameter(name).isEmpty()) {
					continue;
				} else {
					if ("deviceId".equals(name)) {
						map.put("deviceId", request.getParameter(name));
					} else if ("jobNum".equals(name)) {
						map.put("jobNum", "%" + request.getParameter(name) + "%");
					} else if ("state".equals(name)) {
						map.put("state", PersonState.getById(Integer.parseInt(request.getParameter(name))).ordinal() + "");
					} else if ("mobile".equals(name)) {
						map.put("mobile", "%" + request.getParameter(name) + "%");
					} else if ("name".equals(name)) {
						map.put("name", "%" + request.getParameter(name) + "%");
					} else if ("type".equals(name)) {
						map.put("type", request.getParameter(name));
					}
				}
			}
		}
		IPageResult<IPerson> page = srcbDevicePersonRelation.selectPage(start, limit, map);
		result.addAttribute("success", true);
		result.addAttribute("total", page.getTotal());
		result.addAttribute("data", PersonForm.convert(page.list()));
		return result;
	}
    /**
     * 获取人员岗位信息
     *
     * @return
     */
    @RequestMapping(value = "/queryPersonJob", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap queryPersonJobList() {
        logger.info(String.format("search personJob : queryPersonJobList"));
        ModelMap model = new ModelMap();
        List<IPersonJob> listPersonJob = personJobService.list();
        model.put("data", PersonJobForm.convert(listPersonJob));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }

    /**
     * 个人设置-个人信息页面的数据
     * @param id
     * @return
     * @since 2.0.0.0
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody
    ModelMap getPersonalInfo(@PathVariable String id) {
        logger.info(" getPersonalInfo : person.guid = " + id);
        ModelMap model = new ModelMap();
        IPerson person = service.get(id);
        model.put("data", new PersonForm(person));
        model.put(FishConstant.SUCCESS, true);
        return model;
    }
    
	@RequestMapping(value = "/devicePerson", method = RequestMethod.POST)
	public @ResponseBody
	ModelMap devicePerson(@RequestParam String terminalId, @RequestParam String personId, @RequestParam String type, @RequestParam String personType) {
		ModelMap result = new ModelMap();
		String[] ids = personId.split(",");
		int i = 0;
		for (String pId : ids) {
			IDevice device = deviceService.get(terminalId);
			IPerson person = service.get(pId);
			if (device == null) {
				i++;
				continue;
			}
			if (person == null) {
				i++;
				continue;
			}
			if (type.equals("add")) {
				devicePersonRelation.link(person, device);
			} else {
				IPerson p = new Person();
				IDevice d = new Device();
				p.setGuid(pId);
				d.setTerminalId(terminalId);
				devicePersonRelation.unlink(person, device);
			}
		}
		if (i > 0) {
			result.put("success", false);
			result.put("errors", i);
		} else {
			result.put("success", true);
		}
		return result;
	}

}
