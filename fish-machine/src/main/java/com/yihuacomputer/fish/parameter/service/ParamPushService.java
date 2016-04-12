package com.yihuacomputer.fish.parameter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.api.parameter.IParamPushService;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelationService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.parameter.entity.ParamDeviceDetail;
import com.yihuacomputer.fish.parameter.entity.ParamElement;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDeviceRelation;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateElementRelation;

@Service
@Transactional
public class ParamPushService implements IParamPushService {

	private Logger logger = LoggerFactory.getLogger(ParamPushService.class);
	
	@Autowired
	private IParamTemplateService templateService;
	

	@Autowired
	private IGenericDao dao;

	@Autowired
	private IParamTemplateDeviceRelationService templateDeviceRelationService;
	@Autowired
	private IParamDeviceDetailService paramDeviceDetailService;
	
	@Autowired
	private IParamElementService elementService;
	
	@Override
	public boolean generateParamFileByTemplate(long templateId) {
		IParamTemplate template = templateService.get(templateId);
		List<IDevice> deviceList= templateService.listDeviceByTemplate(template);
		if(deviceList==null||deviceList.size()==0){
			logger.error("The template don't link device,can't generate file");
			return false;
		}
		IDevice device = deviceList.get(0);
		//根据设备号，获取设备相关应用的最大版本号<应用名称，应用当前最大的版本号>
		Map<String,Long> appVersionMap = getMaxVersionInfo(device.getId());
		//获取模板详情所有的信息
		List<IParamTemplateDetail> list = templateService.listTemplateDetail(templateId);
		//应用类型，参数类型，参数详情
		Map<String,Map<String,List<IParamTemplateDetail>>> map = new HashMap<String,Map<String,List<IParamTemplateDetail>>>();
		for(IParamTemplateDetail detail:list){
			IAppSystem appSystem = detail.getParamElement().getParamBelongs();
			String appSystemName = appSystem.getName();
			String paramClassifyName = detail.getParamElement().getParamClassify().getName();
			//获取应用类型下的参数分类Map信息
			Map<String,List<IParamTemplateDetail>> paramTypeMap= map.get(appSystemName);
			//如果应用类型下的参数分类Map不存在，new一个
			if(paramTypeMap==null){
				paramTypeMap = new HashMap<String,List<IParamTemplateDetail>>();
				map.put(appSystemName, paramTypeMap);
			}
			//获取参数分类下参数列表
			List<IParamTemplateDetail> detailList = paramTypeMap.get(paramClassifyName);
			if(detailList==null){
				detailList = new ArrayList<IParamTemplateDetail>();
				paramTypeMap.put(paramClassifyName, detailList);
			}
			detailList.add(detail);
		}
		//生成参数文件
		generateParamFile(map,appVersionMap);
		//TODO 在制定目录生成压缩文件
		return false;
	}
	
	/**
	 * 根据集合和版本号生成参数文件
	 * @param map
	 * @param appVersionMap
	 * @return
	 */
	private boolean generateParamFile(Map<String,Map<String,List<IParamTemplateDetail>>> map,Map<String,Long> appVersionMap){
		//获取应用类型集合
		Set<String> appNameSet = map.keySet();
		Iterator<String> appNameIterator = appNameSet.iterator();
		while(appNameIterator.hasNext()){
			String appName = appNameIterator.next();
			//获取参数分类Map
			Map<String,List<IParamTemplateDetail>> paramTypeMap = map.get(appName);
			Set<String> paramTypeNameSet = paramTypeMap.keySet();
			Iterator<String> paramTypeNameIterator = paramTypeNameSet.iterator();
			while(paramTypeNameIterator.hasNext()){
				String paramTypeName = paramTypeNameIterator.next();
				paramTypeMap.get(paramTypeName);
				//TODO 转化成参数文件
			}
			//TODO 转化成描述文件
		}
		
		return false;
	}
	
//	private boolean wirteFile(){
//		
//	}

	@Override
	public boolean generateParamFileByDevice(long deviceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean noticeDeviceDownloadParamFileByTemplate(long templateId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean noticeDeviceDownloadParamFileByDevice(long deviceId) {
		getMaxVersionInfo(deviceId);
		return false;
	}
	
	
	
	private Map<String,Long> getMaxVersionInfo(long deviceId){
		Map<String,Long> map = new HashMap<String,Long>();
		StringBuffer hql = new StringBuffer();
		hql.append("select paramDeviceDetail.element.paramBelongs.name,max(paramDeviceDetail.versionTimeStamp) from ")
		.append(ParamDeviceDetail.class.getSimpleName()).append(" paramDeviceDetail ")
		.append(" where paramDeviceDetail.device.id=? ")
		.append(" group by paramDeviceDetail.element.paramBelongs.id");
		List<Object> objectList = dao.findByHQL(hql.toString(), new Object[]{deviceId});
		for(Object objects: objectList){
			Object[] object = (Object[])objects;
			map.put(String.valueOf(object[0]), Long.parseLong(String.valueOf(object[1])));
		}
		//判断是否存在模板关联，如果存在模板关联则查看元数据时候，则带上模板中的元数据的关联
		StringBuffer templateHql = new StringBuffer();
		templateHql.append("select distinct templateElement.elementId from ")
		.append(ParamTemplateDeviceRelation.class.getSimpleName()).append(" templateDevice ,")
		.append(ParamTemplateElementRelation.class.getSimpleName()).append(" templateElement ")
		.append(" where templateDevice.deviceId=? and templateDevice.templateId=templateElement.templateId ");
		List<Object> elementIdList = dao.findByHQL(templateHql.toString(), new Object[]{deviceId});
		List<Object> args = new ArrayList<Object>();
		StringBuffer elementHql = new StringBuffer();
		//查找元数据中的参数名称和版本号
		elementHql.append("select element.paramBelongs.name,max(element.paramTimestamp) from ")
		.append(ParamElement.class.getSimpleName()).append(" element ");
		if(elementIdList.size()>0){
			elementHql.append(" where element.id in ( ");
			int index = 0;
			for(Object obj:elementIdList){
				elementHql.append(Long.parseLong(String.valueOf(obj)));
				index++;
				if(index!=elementIdList.size()){
					elementHql.append(",");
				}
			}
			elementHql.append(")");
		}
		
		elementHql.append(" group by element.paramBelongs.id");
		List<Object> objectList1 = dao.findByHQL(elementHql.toString(), args.toArray());
		for(Object objects: objectList1){
			Object[] object = (Object[])objects;
			String key = String.valueOf(object[0]);
			long versionNo = Long.parseLong(String.valueOf(object[1]));
			if(map.get(key)<versionNo){
				map.put(key, versionNo);
			}
		}
		return map;
	}

}
