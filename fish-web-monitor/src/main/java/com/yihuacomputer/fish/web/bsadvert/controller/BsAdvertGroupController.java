package com.yihuacomputer.fish.web.bsadvert.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.fish.api.advert.bs.GroupType;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResource;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.OrganizationLevel;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.system.config.IParamService;
import com.yihuacomputer.fish.api.version.VersionCfg;
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
	private IBsAdvertService bsAdvertService;
	
	@Autowired
	private IParamService paramService;
	
	
    @Autowired
    private IDeviceAdvertRelation deviceAdvertRelation;
	
    @Autowired
    private IDeviceService deviceService;
    
	@Autowired
	private MessageSource messageSourceVersion;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap getAdvertGroup(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search bsAdvert group ");
		HttpSession session = request.getSession();
		UserSession user = (UserSession) session.getAttribute(FishWebUtils.USER);
		ModelMap result = new ModelMap();
		IFilter filter = getFilter(webRequest);
		if(filter.getValue("orgId")==null){
			filter.like("orgId", String.valueOf(user.getOrgId()));
		}
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
			Object[] objs = (Object[]) object;
			BsAdvertGroupForm bsAdvertGroupForm = new BsAdvertGroupForm();
			bsAdvertGroupForm.setGroupName(String.valueOf(objs[2]));
			bsAdvertGroupForm.setOrgId((Long)(objs[4]));
			if (null != objs[6]) {
				bsAdvertGroupForm.setOrgLevel(getEnumI18n(OrganizationLevel.getById((Integer)(objs[6])).getText()));
				bsAdvertGroupForm.setOrgLevelId((Integer)(objs[6]));
			}
			bsAdvertGroupForm.setOrgName(String.valueOf(objs[7]));
			bsAdvertGroupForm.setGroupType((Integer)objs[3]);
			bsAdvertGroupForm.setId((Long)objs[0]);
			bsAdvertGroupForm.setResourcePath(String.valueOf(objs[5]));
			String activedAdv = String.valueOf(objs[1]);
			bsAdvertGroupForm.setActivedAdv(activedAdv);
			bsGroupList.add(bsAdvertGroupForm);
		}

		return bsGroupList;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	ModelMap add(@RequestBody BsAdvertGroupForm request,HttpServletRequest Httprequest) {
		logger.info("add bsAdvertGroup");
		UserSession userSession = (UserSession)Httprequest.getSession().getAttribute(FishWebUtils.USER);
		long orgId = userSession.getOrgId();
		
		IOrganization org = orgService.get(String.valueOf(orgId));		
		ModelMap result = new ModelMap();
		
		String  groupName = request.getGroupName();
		
		boolean nameDupFlag = advertGroupService.dupGroupName(orgId,groupName);
		
		if(nameDupFlag){
			result.put(FishConstant.SUCCESS, false);
			result.put("errorMsg", messageSourceVersion.getMessage("bsadvertGroup.add.groupNameDup", null, FishCfg.locale));
			return result;
		}
		
		if(org == null){
			result.put(FishConstant.SUCCESS, false);
			result.put("errorMsg", messageSourceVersion.getMessage("bsadvertGroup.org.notExists", null, FishCfg.locale));
			return result;
		}
		
		IAdvertGroup advertGroup = advertGroupService.orgHasAG(orgId);
		if(advertGroup != null && request.getGroupType()==1){
			
			result.put(FishConstant.SUCCESS, false);
			result.put("errorMsg", messageSourceVersion.getMessage("bsadvertGroup.org.groupDup", null, FishCfg.locale));
			return result;
		}
		advertGroup = advertGroupService.make();
		advertGroup.setGroupName(request.getGroupName());
		
		request.setResourcePath("");
		request.setOrgId(orgId);
		request.translate(advertGroup);
		advertGroupService.save(advertGroup);
		
		advertGroup.setPath(advertGroup.getOrgId() + " " + advertGroup.getGuid());
		
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
		
		long orgId = request.getOrgId();
		String groupName = request.getGroupName();
		
		boolean nameDupFlag = advertGroupService.dupGroupName(orgId,groupName);
		
		if(nameDupFlag){
			result.put(FishConstant.SUCCESS, false);
			result.put("errorMsg", messageSourceVersion.getMessage("bsadvertGroup.add.groupNameDup", null, FishCfg.locale));
			return result;
		}
		
		
		try {
			IAdvertGroup advertGroup = advertGroupService.getById(id);
			
			if (advertGroup == null) {
				result.addAttribute(FishConstant.SUCCESS, false);
				result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("bsadvertGroup.update.groupNotExists", null, FishCfg.locale));
			} else {
					advertGroup.setGroupName(request.getGroupName());
					advertGroupService.update(advertGroup);
					result.addAttribute(FishConstant.SUCCESS, true);
					result.addAttribute(FishConstant.DATA, request);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("bsadvertGroup.update.updateError", null, FishCfg.locale));
		}
		return result;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	ModelMap delete(@PathVariable long id) {
		
		logger.info(" delete bsAdvertGroup: bsAdvertGroup.id = " + id);
		ModelMap result = new ModelMap();
		
		IFilter filter = new Filter();
		filter.eq("groupId", id);
		List<IBsAdvert> listBsAdvert = bsAdvertService.list(filter);
		
		if(listBsAdvert.size()!=0){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("bsadvertGroup.delete.hasAdvert", null, FishCfg.locale));
			return result;
			
		}
		
		List<IAdvertGroup> listAdvertGroup = deviceAdvertRelation.listAdvertGroupByGroupId(id);
		
		if(listAdvertGroup.size()!=0){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("bsadvertGroup.delete.hasLinkedDevice", null, FishCfg.locale));
			return result;
		}
		try {
			IAdvertGroup group = advertGroupService.getById(id);
			if (group != null) {
				//如果是根节点默认广告组，则不允许删除
				if(group.getOrgId()==1&&(group.getGroupType().getId()==1)){
					result.addAttribute(FishConstant.SUCCESS, false);
					result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("bsadvertGroup.delete.rootDelete", null, FishCfg.locale));
					return result;
				}
				advertGroupService.deleteById(id);
				result.addAttribute(FishConstant.SUCCESS, true);
			} else {
				result.addAttribute(FishConstant.SUCCESS, true);
			}
		} catch (Exception e) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("bsadvertGroup.delete.deleteFailed", null, FishCfg.locale));
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
           Filter filter = getFilterDevice(request);
           
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
				if (name.equals("groupType")) {
					filter.eq("groupType", GroupType.getGroupTypeById(Integer.parseInt(value)));
				}
			}
		}
		return filter;
	}
	
	private Filter getFilterDevice(WebRequest request) {
		Filter filter = new Filter();
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
				if (name.equals("ip")) {
					filter.like("ip", value);
				}
				if (name.equals("terminalId")) {
					filter.like("terminalId", value);
				}
				if (name.equals("devType")) {
					filter.eq("devType", value);
				}
			}
		}
		return filter;
	}
	
	@RequestMapping(value="/actived",method = RequestMethod.POST)
	public @ResponseBody ModelMap activedBsAdvert(@RequestParam long advertGroupId,HttpServletRequest request, WebRequest webRequest) {
		
		IBsAdvert advert = advertGroupService.getBsAdvertByGroupId(advertGroupId);
		ModelMap result = new ModelMap();
		if(advert==null){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSourceVersion.getMessage("bsadvertGroup.actived.hasNoActivedAdvert", null, FishCfg.locale));
			return result;
		}else{
			result.addAttribute(FishConstant.SUCCESS, true);
			return result;
		}
	}
	
	/**
	 * 进行预览
	 * @param id
	 * @param screen
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/preview2", method = RequestMethod.POST)
	@ResponseBody
	public String preview2(@RequestParam long id, @RequestParam String screen, HttpServletRequest request) {
		// 1.根据广告编号把媒体文件放到临时目录，并把临时目录保存到request中
		// 2.获取媒体资源的文件名并保存到到request中
		IBsAdvert advert = advertGroupService.getBsAdvertByGroupId(id);
		String workHome = VersionCfg.getBsAdvertDir();
		String contextPath = this.getRealPath(request);
		File targetDir = new File(contextPath + File.separator + advert.getId() + File.separator + screen);
		if (!targetDir.exists()&&!targetDir.mkdirs()){
			throw new AppException(getVersionI18n("advert.createDir.fail", new Object[]{targetDir.getName()}));
		}
		String zipFilePath = VersionCfg.getBsAdvertDir()+File.separator+advert.getId()+".zip";
		ZipUtils.unZip(zipFilePath, VersionCfg.getBsAdvertDir()+File.separator+advert.getId(), "UTF-8");
		StringBuffer result = new StringBuffer("[");
		for (IBsAdvertResource resource : advert.getAdvertResources()) {
			if (getEnumI18n(resource.getScreen().getText()).equals(screen)) {
				String sourceFilePath = workHome + File.separator + advert.getId() + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType()) + File.separator + getEnumI18n(resource.getScreen().getText())
						+ File.separator + resource.getContent();
				IOUtils.copyFileToDirectory(sourceFilePath, targetDir.getAbsolutePath());
				String image = "tmp/bsAdvert/" + advert.getId() + "/" + screen + "/" + resource.getContent();
				result.append("{'picName':'").append(image).append("','playTime':'").append(resource.getPlayTime()).append("'}").append(",");
			}
		}

		if (!result.toString().equals("[")) {
			String r = result.toString().substring(0, result.toString().length() - 1);
			return r + "]";
		}
		return result.append("]").toString();
	}
	
	private String getRealPath(HttpServletRequest request) {
		return FishWebUtils.getRealPathByTmp(request) + "/bsAdvert";
	}
	
	private String getVersionI18n(String key,Object[] value){
		return messageSourceVersion.getMessage(key,value, FishCfg.locale);
	}
}
