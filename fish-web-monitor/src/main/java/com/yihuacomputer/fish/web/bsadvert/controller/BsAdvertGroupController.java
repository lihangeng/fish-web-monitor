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
import com.yihuacomputer.common.filter.FilterFactory;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.api.version.relation.IDeviceAdvertRelation;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertGroupDeviceForm;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertGroupForm;
import com.yihuacomputer.fish.web.machine.form.DeviceForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("bsadvert/advertgroup")
public class BsAdvertGroupController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(BsAdvertGroupController.class);

	@Autowired
	private MessageSource messageSourceEnum;
	@Autowired
	private IAdvertGroupService advertGroupService;
	
	@Autowired
	private IOrganizationService orgService;
	
	
	@Autowired
	private IParamService paramService;
	
	
    @Autowired
    private IDeviceAdvertRelation deviceAdvertRelation;
	
    @Autowired
    private IDeviceService deviceService;

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
	
	
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	public @ResponseBody ModelMap getAdvertGroupList(HttpServletRequest request, WebRequest webRequest) {
		logger.info("search bsAdvert group list");
		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
		long orgId = userSession.getOrgId();
		IFilter filter = new Filter();
		filter.eq("orgId", orgId);
		List<IAdvertGroup> groupList = advertGroupService.list(filter);
		result.addAttribute(FishConstant.TOTAL,groupList.size());
		result.addAttribute(FishConstant.DATA, groupList);
		return result;
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
			bsAdvertGroupForm.setGroupType(advertGroup.getGroupType().getId());
			bsAdvertGroupForm.setId(advertGroup.getId());
			bsAdvertGroupForm.setResourcePath(advertGroup.getPath());
			bsGroupList.add(bsAdvertGroupForm);
		}

		return bsGroupList;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody BsAdvertGroupForm request) {
		logger.info("add bsAdvertGroup");
		long orgId = request.getOrgId();
		
		IOrganization org = orgService.get(String.valueOf(request.getOrgId()));		
		ModelMap result = new ModelMap();
		
		if(org == null){
			result.put(FishConstant.SUCCESS, false);
			result.put("errorMsg", "该机构已经不存在，无法添加");
			return result;
		}
		
		IAdvertGroup advertGroup = advertGroupService.orgHasAG(orgId);
		if(advertGroup != null){
			
			result.put(FishConstant.SUCCESS, false);
			result.put("errorMsg", "该机构下已存在广告组，无法添加");
			return result;
		}
		advertGroup = advertGroupService.make();
		advertGroup.setGroupName(request.getGroupName());
		
		request.setResourcePath("");
		
		request.translate(advertGroup);
		
		advertGroupService.save(advertGroup);
		
		String bsAdvertIP = paramService.getParam("bsAdvertServerIP").getParamValue();
		String bsAdvertPort = paramService.getParam("bsAdvertServerPort").getParamValue();
		String bsAdvertPath = paramService.getParam("bsAdvertPath").getParamValue();
		
		advertGroup.setPath("http://"+bsAdvertIP+":"+bsAdvertPort+"/"+advertGroup.getId()+bsAdvertPath+"/advert.html");
		
		advertGroupService.update(advertGroup);
		result.put(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, advertGroup);

		return result;
	}
	
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap update(@PathVariable long id, @RequestBody BsAdvertGroupForm request) {
		logger.info("update bsAdvertGroup: bsAdvertGroup.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IAdvertGroup advertGroup = advertGroupService.getById(id);
			
			if (advertGroup == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, "修改的广告组已经不存在，请刷新后重试");
			} else {
					request.translate(advertGroup);
					advertGroup.setId(id);
					advertGroupService.update(advertGroup);
					result.addAttribute(FishConstant.SUCCESS, true);
					result.addAttribute(FishConstant.DATA, request);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "修改广告组信息异常");
		}
		return result;
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		logger.info(" delete bsAdvertGroup: bsAdvertGroup.id = " + id);
		
		List<IAdvertGroup> list = deviceAdvertRelation.listAdvertGroupByGroupId(id);
		
		ModelMap result = new ModelMap();
		
		if(list.size()!=0){
			
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "该广告组下已经存在关联关系，请解除后再删除");
			return result;
			
		}
		try {
			IAdvertGroup group = advertGroupService.getById(id);
			if (group != null) {
				advertGroupService.deleteById(id);
				result.addAttribute(FishConstant.SUCCESS, true);
			} else {
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "呵呵 删除失败");
		}
		return result;
	}

	
    /**
     * 建立关联关系：
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/link", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap link(@RequestBody BsAdvertGroupDeviceForm request) {
        logger.info(String.format("device %s linked  %s", request.getGroupId(), request.getDeviceId()));
        ModelMap result = new ModelMap();
        IAdvertGroup advertGroup = advertGroupService.getById(request.getGroupId());
        IDevice device = deviceService.get(request.getDeviceId());
        List<IDevice> list = deviceAdvertRelation.listDeviceByAdvertGroup(advertGroup);
        if (list.contains(device)) {
            result.put(FishConstant.SUCCESS, true);
            return result;
        }
        deviceAdvertRelation.link(advertGroup, device);
        result.put(FishConstant.SUCCESS, true);
        result.put("data", request);
        return result;
    }
	
	
	
    /**
     * 解除关联关系：
     *
     * @param groupId
     * @param deviceId
     * @return
     */
    @RequestMapping(value = "/unlink", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap unlink(@RequestParam String groupId, @RequestParam String deviceId) {
        ModelMap result = new ModelMap();
        String[] ids = deviceId.split(",");
        try {
            for (String id : ids) {
            	deviceAdvertRelation.unlink(advertGroupService.getById(Long.parseLong(groupId)), deviceService.get(Long.valueOf(id)));
            }
            result.addAttribute(FishConstant.SUCCESS, true);
        }
        catch (Exception ex) {
            logger.info(ex.getMessage());
            result.addAttribute(FishConstant.SUCCESS, false);
        }
        return result;
    }
	
    /**
    *
    * 根据广告组Id获得关联设备列表
    *
    * @param form
    * @return ModelMap<String, Object>
    */
   @RequestMapping(value = "/linkedDevice", method = RequestMethod.GET)
   public @ResponseBody
   ModelMap searchLinkedDevice(@RequestParam int start, @RequestParam int limit, @RequestParam int flag,
           @RequestParam String guid, @RequestParam String organizationId, WebRequest request, HttpServletRequest req) {
       logger.info(String.format("search device : start = %s ,limt = %s ", start, limit));
       ModelMap result = new ModelMap();
       IPageResult<IDevice> pageResult = null;
       if (flag == 0) {// 已关联的设备
           result.addAttribute(FishConstant.SUCCESS, true);
           Filter filter = new Filter();
           filter.addFilterEntry(FilterFactory.like("terminalId", request.getParameter("terminalId")));
           pageResult = deviceAdvertRelation.pageDeviceByAdvertGroup(start, limit, advertGroupService.getById(Long.parseLong(guid)), filter);
           result.addAttribute("total", pageResult.getTotal());
           result.addAttribute("data", DeviceForm.convert(pageResult.list()));
       } else {// 可以关联的设备
           IFilter filter = new Filter();
           filter.like("terminalId", request.getParameter("terminalId"));
           pageResult = deviceAdvertRelation.pageUnlinkDeviceByAdvertGroup(start, limit, advertGroupService.getById(Long.parseLong(guid)), filter,
                           organizationId, orgService.getRoot().get(0).getGuid());
           
           result.addAttribute(FishConstant.SUCCESS, true);
           result.addAttribute("total", pageResult.getTotal());
           result.addAttribute("data", DeviceForm.convert(pageResult.list()));
       }
       return result;
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
