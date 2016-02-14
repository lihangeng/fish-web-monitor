package com.yihuacomputer.fish.web.atm;

import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelation;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupDeviceRelationService;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;

@Controller
@RequestMapping("/msg/advertUrl")
public class AdvertUrlController {
	
	@Autowired
	private IAdvertGroupService advertGroupService;
	@Autowired
	private IDeviceService deviceService;
	@Autowired
	private IOrganizationService organizationService;
	@Autowired
	private IAdvertGroupDeviceRelationService advertGroupDeviceRelationService;
	String path=null;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	String reciveMsg(HttpServletRequest request) {
		ModelMap result = new ModelMap();
		String callbackName = (String)request.getParameter("jsoncallback");
		String terminalId = request.getParameter("terminalId");

		
		if(terminalId !=null){
			IDevice device=deviceService.get(terminalId);
			long orgId=device.getOrganization().getId();
			getGroupPath(orgId,terminalId);
		}
			result.put("ret", path);
		String renderStr = callbackName+"("+JsonUtils.toJson(result)+")";
		return renderStr;
	}
	/**
	 * 获取广告组的路径
	 * @param orgId
	 * @param terminalId
	 * @return
	 */
	public String getGroupPath(long orgId,String terminalId){
		List<IAdvertGroup> list=advertGroupService.list(orgId);
		if(list.isEmpty()){
			getParentId(orgId,terminalId);
		}else{
			IAdvertGroupDeviceRelation advertGroupDeviceRelation=advertGroupDeviceRelationService.getGroup(terminalId);
			if(advertGroupDeviceRelation==null){
				for(IAdvertGroup advertGroup:list){
					if(advertGroup.getGroupType().getName().toString()=="0"){
						path=advertGroup.getPath();
					}
				}
				if(path==null){
					getParentId(orgId,terminalId);
				}
			}else{
				long groupId=advertGroupDeviceRelation.getGroupId();
				path=advertGroupService.getById(groupId).getPath();
			}
		}
		return path;
	}
	/**
	 * 获取机构的父节点
	 * @param orgId
	 * @param terminalId
	 */
	long parentId=1;
	public void getParentId(long orgId,String terminalId){
		if(parentId>0){
			IOrganization organization=organizationService.get(String.valueOf(orgId));
			parentId=organization.getParent().getId();
			getGroupPath(parentId,terminalId);
		}
	}
}
