package com.yihuacomputer.fish.web.atm;

import java.util.ArrayList;
import java.util.List;









import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.advert.bs.BsAdvertStatus;
import com.yihuacomputer.fish.api.advert.bs.GroupType;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelationService;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;

@Controller
@RequestMapping("/msg/advertUrl")
public class AdvertUrlController {

	@Autowired
	private IAdvertGroupService advertGroupService;
	@Autowired
	private IBsAdvertService bsAdvertService;
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IAdvertGroupDeviceRelationService advertGroupDeviceRelationService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String reciveMsg(HttpServletRequest request) {
		ModelMap result = new ModelMap();
		String callbackName = (String)request.getParameter("jsoncallback");
		String terminalId = request.getParameter("terminalId");
		String path = "1";
		if(terminalId !=null){
			IDevice device=deviceService.get(terminalId);
			if(device!=null){
				String orgFlag=device.getOrganization().getOrgFlag();
				String orgGuids[] = orgFlag.split("-");
				for(int index=orgGuids.length-1;index>=0;index--){
					IAdvertGroup group = getGroupPath(Long.parseLong(orgGuids[index]),device.getId());
					if(group!=null){
						path = group.getPath();
						break;
					}
				}
			}
		}
		result.put("ret", path);
		String renderStr = callbackName+"("+JsonUtils.toJson(result)+")";
		return renderStr;
	}
	/**
	 * 获取可用的广告组
	 * @param orgId
	 * @param terminalId
	 * @return
	 */
	private IAdvertGroup getGroupPath(long orgId,long deviceId){
		IAdvertGroup defaultGroup = null;
		List<IAdvertGroup> list=advertGroupService.list(orgId);
		if(list.isEmpty()){
			return null;
		}else{
			List<Long> groupIdList = new ArrayList<Long>();
			//如果存在默认组，则将默认组缓存；不存在关联设备时候，直接返回默认组,存在关联的广告组，直接返回广告组
			for(IAdvertGroup group:list){
				if(group.getGroupType().equals(GroupType.DEFAULT)){
					IFilter filter  = new Filter();
					filter.eq("groupId", group.getId());
					filter.ne("bsAdvertStatus", BsAdvertStatus.UNACTIVE);
					List<IBsAdvert> advertList = bsAdvertService.list(filter);
					if(advertList.size()>0){
						defaultGroup = group;
					}
				}
				else{
					groupIdList.add(group.getId());
				}
			}
			IAdvertGroupDeviceRelation advertGroupDeviceRelation=advertGroupDeviceRelationService.getGroup(deviceId,groupIdList);
			if(null!=advertGroupDeviceRelation){
				defaultGroup = advertGroupService.getById(advertGroupDeviceRelation.getGroupId());
			}
		}
		return defaultGroup;
	}
}
