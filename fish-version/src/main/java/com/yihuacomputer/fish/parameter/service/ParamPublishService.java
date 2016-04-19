package com.yihuacomputer.fish.parameter.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.file.INIFileWriter;
import com.yihuacomputer.common.file.PropertiesFileWriter;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.domain.dao.IGenericDao;
import com.yihuacomputer.fish.api.device.IDevice;
import com.yihuacomputer.fish.api.device.IDeviceService;
import com.yihuacomputer.fish.api.parameter.FileFormat;
import com.yihuacomputer.fish.api.parameter.IAppSystem;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetail;
import com.yihuacomputer.fish.api.parameter.IParamDeviceDetailService;
import com.yihuacomputer.fish.api.parameter.IParamElement;
import com.yihuacomputer.fish.api.parameter.IParamElementService;
import com.yihuacomputer.fish.api.parameter.IParamPublish;
import com.yihuacomputer.fish.api.parameter.IParamPublishResult;
import com.yihuacomputer.fish.api.parameter.IParamPublishResultService;
import com.yihuacomputer.fish.api.parameter.IParamPublishService;
import com.yihuacomputer.fish.api.parameter.IParamTemplate;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDetail;
import com.yihuacomputer.fish.api.parameter.IParamTemplateDeviceRelationService;
import com.yihuacomputer.fish.api.parameter.IParamTemplateService;
import com.yihuacomputer.fish.api.parameter.ParamInfo;
import com.yihuacomputer.fish.parameter.entity.ParamDeviceDetail;
import com.yihuacomputer.fish.parameter.entity.ParamElement;
import com.yihuacomputer.fish.parameter.entity.ParamPublish;
import com.yihuacomputer.fish.parameter.entity.ParamPublishResult;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDetail;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateDeviceRelation;
import com.yihuacomputer.fish.parameter.entity.ParamTemplateElementRelation;
import com.yihuacomputer.fish.parameter.publishjob.PublishJobManager;

/**
 * @author GQ
 *
 */
@Service
@Transactional
public class ParamPublishService implements IParamPublishService {

	private Logger logger = LoggerFactory.getLogger(ParamPublishService.class);

	@Autowired
	private IParamTemplateService templateService;
	
	@Autowired
	private IDeviceService deviceService;

	@Autowired
	private IGenericDao dao;

	@Autowired
	private IParamTemplateDeviceRelationService templateDeviceRelationService;
	
	@Autowired
	private IParamDeviceDetailService paramDeviceDetailService;

	@Autowired
	private IParamElementService elementService;

	@Autowired
	private PublishJobManager publishJobManager;
	
	@Autowired
	private IParamPublishResultService paramPulishResultService;

	public PublishJobManager getPublishJobManager() {
		return publishJobManager;
	}

	public IParamPublishResultService getParamPulishResultService() {
		return paramPulishResultService;
	}
	/**
	 * 最大版本号标识(放置MAP中和每种应用进行比较，并存入MAP，所以标识尽量长一些，避免和现有的应用名称重复)
	 */
	private final static String MAX_VERSION_TIMESTAMP = "MAX_VERSION_TIMESTAMP_MAX_VERSION_TIMESTAMP";

	@Override
	public long generateParamFileByTemplate(long templateId) {
		IParamTemplate template = templateService.get(templateId);
		if (null == template) {
			logger.error("The template don't exsit");
			return 0;
		}
		List<IDevice> deviceList = templateService.listDeviceByTemplate(template);
		if (deviceList == null || deviceList.size() == 0) {
			logger.error("The template don't link device,can't generate file");
			return 0;
		}
		IDevice device = deviceList.get(0);
		// 根据设备号，获取设备相关应用的最大版本号<应用名称，应用当前最大的版本号>
		Map<String, Long> appVersionMap = getMaxVersionNoInfoByDeviceId(device.getId());
		// 获取模板详情所有的信息
		List<IParamTemplateDetail> list = templateService.listTemplateDetail(templateId);
		// 应用类型，参数类型，参数详情
		Map<String, Map<String, List<IParamTemplateDetail>>> map = new HashMap<String, Map<String, List<IParamTemplateDetail>>>();
		for (IParamTemplateDetail detail : list) {
			IAppSystem appSystem = detail.getParamElement().getParamBelongs();
			String appSystemName = appSystem.getName();
			String paramClassifyName = detail.getParamElement().getParamClassify().getName();
			// 获取应用类型下的参数分类Map信息
			Map<String, List<IParamTemplateDetail>> paramTypeMap = map.get(appSystemName);
			// 如果应用类型下的参数分类Map不存在，new一个
			if (paramTypeMap == null) {
				paramTypeMap = new HashMap<String, List<IParamTemplateDetail>>();
			}
			// 获取参数分类下参数列表
			List<IParamTemplateDetail> detailList = paramTypeMap.get(paramClassifyName);
			if (detailList == null) {
				detailList = new ArrayList<IParamTemplateDetail>();
			}
			detailList.add(detail);
			paramTypeMap.put(paramClassifyName, detailList);
			map.put(appSystemName, paramTypeMap);
		}
		// 生成参数文件
		if (!generateParamFile(map, appVersionMap)) {
			return 0;
		}
		String sourceFile = FishCfg.getFishHome() + FishCfg.fileSep + "param" + FishCfg.fileSep + appVersionMap.get(MAX_VERSION_TIMESTAMP) + FishCfg.fileSep;
		ZipUtils.zip(sourceFile, sourceFile + "param.zip", "utf-8");
		return appVersionMap.get(MAX_VERSION_TIMESTAMP);
	}

	@Override
	public long generateParamFileByDevice(long deviceId) {
		Map<String, Long> appVersionMap = getMaxVersionNoInfoByDeviceId(deviceId);
		// 和设备关联的模板中参数
		List<IParamTemplateDetail> tempDeviceRelationList = templateService.getParamTemplateDetailListByDeviceId(deviceId);
		IFilter filter = new Filter();
		filter.eq("device.id", deviceId);
		// 设备参数表中数据
		List<IParamDeviceDetail> paramDeviceDetailList = paramDeviceDetailService.list(filter);
		Map<String, Map<String, Map<String, String>>> paramDeviceDetailMap = new HashMap<String, Map<String, Map<String, String>>>();
		for (IParamDeviceDetail paramDeviceDetail : paramDeviceDetailList) {
			String value = paramDeviceDetail.getParamValue();
			String appName = paramDeviceDetail.getElement().getParamBelongs().getName();
			String typeName = paramDeviceDetail.getElement().getParamClassify().getName();
			String paramName = paramDeviceDetail.getElement().getParamName();
			Map<String, Map<String, String>> appMap = paramDeviceDetailMap.get(appName);
			if (null == appMap) {
				appMap = new HashMap<String, Map<String, String>>();
			}
			Map<String, String> typeMap = appMap.get(appName);
			if (null == typeMap) {
				typeMap = new HashMap<String, String>();
			}
			typeMap.put(paramName, value);
			appMap.put(typeName, typeMap);
			paramDeviceDetailMap.put(appName, appMap);
		}
		Map<String, Map<String, List<IParamTemplateDetail>>> descriptionMap = new HashMap<String, Map<String, List<IParamTemplateDetail>>>();
		// 如果设备未关联模板，则直接获取元数据
		if (tempDeviceRelationList.size() == 0) {
			// 元数据中参数
			List<IParamElement> elementList = elementService.list();
			// 将元数据转为IParamTemplateDetail内容并存入descriptionMap
			for (IParamElement paramElement : elementList) {
				String appName = paramElement.getParamBelongs().getName();
				String paramTypeName = paramElement.getParamClassify().getName();
				Map<String, List<IParamTemplateDetail>> appMap = descriptionMap.get(appName);
				if (appMap == null) {
					appMap = new HashMap<String, List<IParamTemplateDetail>>();
				}
				List<IParamTemplateDetail> detailList = appMap.get(paramTypeName);
				if (detailList == null || detailList.size() == 0) {
					detailList = new ArrayList<IParamTemplateDetail>();
				}
				IParamTemplateDetail paramDetail = new ParamTemplateDetail();
				paramDetail.setParamElement(paramElement);
				String paramValue = null;
				if (null == paramDeviceDetailMap.get(appName) || null == paramDeviceDetailMap.get(appName).get(paramTypeName) || null == paramDeviceDetailMap.get(appName).get(paramTypeName).get(paramElement.getParamName())) {
					paramValue = paramElement.getParamValue();
				} else {
					paramValue = paramDeviceDetailMap.get(appName).get(paramTypeName).get(paramElement.getParamName());
				}
				paramDetail.setParamValue(paramValue);
				detailList.add(paramDetail);
				appMap.put(paramTypeName, detailList);
				descriptionMap.put(appName, appMap);
			}
		} else {
			// 参数模板得到不重复的元数据信息
			Map<Long, Integer> map = new HashMap<Long, Integer>();
			List<IParamTemplateDetail> noRepeatTempDeviceRelationList = new ArrayList<IParamTemplateDetail>();
			int index = 0;
			for (IParamTemplateDetail tempDetail : tempDeviceRelationList) {
				long elementId = tempDetail.getParamElement().getId();
				if (null == map.get(elementId)) {
					noRepeatTempDeviceRelationList.add(tempDetail);
					map.put(elementId, index++);
				} else {
					// 获取重复的模板详情index
					int detailIndex = map.get(elementId);
					tempDetail = noRepeatTempDeviceRelationList.get(detailIndex);
					tempDetail.setParamValue(tempDetail.getParamElement().getParamValue());
					noRepeatTempDeviceRelationList.remove(detailIndex);
					noRepeatTempDeviceRelationList.add(detailIndex, tempDetail);
				}
			}
			for (IParamTemplateDetail tempDetail : noRepeatTempDeviceRelationList) {
				String appName = tempDetail.getParamElement().getParamBelongs().getName();
				String paramTypeName = tempDetail.getParamElement().getParamClassify().getName();
				Map<String, List<IParamTemplateDetail>> appMap = descriptionMap.get(appName);
				if (appMap == null) {
					appMap = new HashMap<String, List<IParamTemplateDetail>>();
				}
				List<IParamTemplateDetail> detailList = appMap.get(paramTypeName);
				if (detailList == null || detailList.size() == 0) {
					detailList = new ArrayList<IParamTemplateDetail>();
				}
				if (null != paramDeviceDetailMap.get(appName) && null != paramDeviceDetailMap.get(appName).get(paramTypeName) && null != paramDeviceDetailMap.get(appName).get(paramTypeName).get(tempDetail.getParamElement().getParamName())) {
					tempDetail.setParamValue(paramDeviceDetailMap.get(appName).get(paramTypeName).get(tempDetail.getParamElement().getParamName()));
				}
				detailList.add(tempDetail);
				appMap.put(paramTypeName, detailList);
				descriptionMap.put(appName, appMap);
			}

		}
		if (!generateParamFile(descriptionMap, appVersionMap)) {
			return 0;
		}
		String sourceFile = FishCfg.getFishHome() + FishCfg.fileSep + "param" + FishCfg.fileSep + appVersionMap.get(MAX_VERSION_TIMESTAMP) + FishCfg.fileSep;
		ZipUtils.zip(sourceFile, sourceFile + "param.zip", "utf-8");
		return appVersionMap.get(MAX_VERSION_TIMESTAMP);
	}

	@Override
	public boolean noticeDeviceDownloadParamFileByTemplate(long templateId,long versionNo,long personId) {
		List<IDevice> templateDeviceRelationList = templateDeviceRelationService.listDeviceByTemplate(templateId);
		String file = FishCfg.getFishHome() + FishCfg.fileSep + "param" + FishCfg.fileSep + versionNo + FishCfg.fileSep;
		ParamInfo paramInfo = new ParamInfo();
		paramInfo.setVersionNo(versionNo);
		paramInfo.setServerPath(file);
		Thread thread = new Thread(new NoticeThread(templateDeviceRelationList,paramInfo,sessionFactory,publishJobManager,personId));
		thread.start();
		return false;
	}

	public boolean noticeDeviceDownloadParamFileByDevice(List<Long> deviceIdList,List<Long>  versionNoList,long personId) {
		IFilter filter = new Filter();
		filter.in("id", deviceIdList);
		List<IDevice> deviceList = deviceService.list(filter);
		List<ParamInfo> list = new ArrayList<ParamInfo>();
		for(long versionNo:versionNoList){
			String file = FishCfg.getFishHome() + FishCfg.fileSep + "param" + FishCfg.fileSep + versionNo + FishCfg.fileSep;
			ParamInfo paramInfo = new ParamInfo();
			paramInfo.setVersionNo(versionNo);
			paramInfo.setServerPath(file);
			list.add(paramInfo);
		}
		Thread thread = new Thread(new NoticeThread(deviceList,list,sessionFactory,publishJobManager,personId));
		thread.start();
		return true;
	}

	/**
	 * 通过设备号获取参数最大的版本号
	 * 
	 * @param deviceId
	 * @return
	 */
	private Map<String, Long> getMaxVersionNoInfoByDeviceId(long deviceId) {
		Map<String, Long> map = new HashMap<String, Long>();
		StringBuffer hql = new StringBuffer();
		// 根据应用类型分组查找设备参数详情中版本最大的值
		hql.append("select paramDeviceDetail.element.paramBelongs.name,max(paramDeviceDetail.versionTimeStamp) from ").append(ParamDeviceDetail.class.getSimpleName()).append(" paramDeviceDetail ").append(" where paramDeviceDetail.device.id=? ").append(" group by paramDeviceDetail.element.paramBelongs.id");
		List<Object> objectList = dao.findByHQL(hql.toString(), new Object[] { deviceId });

		long maxVersionNo = 0l;
		for (Object objects : objectList) {
			Object[] object = (Object[]) objects;
			long versionNo = Long.parseLong(String.valueOf(object[1]));
			map.put(String.valueOf(object[0]), versionNo);
			maxVersionNo = maxVersionNo < versionNo ? versionNo : maxVersionNo;
		}
		// 判断是否存在模板关联，如果存在模板关联则查看元数据时候，则带上模板中的元数据的关联
		StringBuffer templateHql = new StringBuffer();
		templateHql.append("select distinct templateElement.elementId from ").append(ParamTemplateDeviceRelation.class.getSimpleName()).append(" templateDevice ,").append(ParamTemplateElementRelation.class.getSimpleName()).append(" templateElement ").append(" where templateDevice.deviceId=? and templateDevice.templateId=templateElement.templateId ");
		List<Object> elementIdList = dao.findByHQL(templateHql.toString(), new Object[] { deviceId });
		List<Object> args = new ArrayList<Object>();
		StringBuffer elementHql = new StringBuffer();
		// 查找元数据中的参数名称和版本号
		elementHql.append("select element.paramBelongs.name,max(element.paramTimestamp) from ").append(ParamElement.class.getSimpleName()).append(" element ");
		if (elementIdList.size() > 0) {
			elementHql.append(" where element.id in ( ");
			int index = 0;
			for (Object obj : elementIdList) {
				elementHql.append(Long.parseLong(String.valueOf(obj)));
				index++;
				if (index != elementIdList.size()) {
					elementHql.append(",");
				}
			}
			elementHql.append(")");
		}

		elementHql.append(" group by element.paramBelongs.id");
		List<Object> objectList1 = dao.findByHQL(elementHql.toString(), args.toArray());
		for (Object objects : objectList1) {
			Object[] object = (Object[]) objects;
			String key = String.valueOf(object[0]);
			long versionNo = Long.parseLong(String.valueOf(object[1]));
			if (null == map.get(key) || map.get(key) < versionNo) {
				map.put(key, versionNo);
				maxVersionNo = maxVersionNo < versionNo ? versionNo : maxVersionNo;
			}
		}
		map.put(MAX_VERSION_TIMESTAMP, maxVersionNo);
		return map;
	}

	/**
	 * 根据集合和版本号生成参数文件
	 * 
	 * @param map
	 * @param appVersionMap
	 * @return
	 */
	private boolean generateParamFile(Map<String, Map<String, List<IParamTemplateDetail>>> map, Map<String, Long> appVersionMap) {
		// 获取应用类型集合
		Set<String> appNameSet = map.keySet();
		Iterator<String> appNameIterator = appNameSet.iterator();
		Map<String, Map<String, String>> descriptionMap = new HashMap<String, Map<String, String>>();
		long maxVersionNo = appVersionMap.get(MAX_VERSION_TIMESTAMP);

		while (appNameIterator.hasNext()) {
			String appName = appNameIterator.next();
			Map<String, String> descSectionMap = new HashMap<String, String>();
			// 获取一个详情查找所需要的信息
			List<IParamTemplateDetail> detaiList = null;
			// 具体参数的Map文件
			Map<String, Map<String, String>> paramMap = new HashMap<String, Map<String, String>>();
			// 获取参数分类Map
			Map<String, List<IParamTemplateDetail>> paramTypeMap = map.get(appName);
			Set<String> paramTypeNameSet = paramTypeMap.keySet();
			Iterator<String> paramTypeNameIterator = paramTypeNameSet.iterator();
			while (paramTypeNameIterator.hasNext()) {
				String paramTypeName = paramTypeNameIterator.next();
				detaiList = paramTypeMap.get(paramTypeName);
				Map<String, String> paramSectionMap = new HashMap<String, String>();
				for (IParamTemplateDetail templateDetail : detaiList) {
					paramSectionMap.put(templateDetail.getParamElement().getParamName(), templateDetail.getParamValue());
				}
				paramMap.put(paramTypeName, paramSectionMap);
			}
			IAppSystem appSystem = detaiList.get(0).getParamElement().getParamBelongs();
			descSectionMap.put("Name", appSystem.getName());
			descSectionMap.put("VersionNo", String.valueOf(appVersionMap.get(appSystem.getName())));
			descSectionMap.put("Path", appSystem.getConfigPath());
			// TODO 可扩展字段，是否重启
			descSectionMap.put("Restart", "false");
			descriptionMap.put(appSystem.getConfigName(), descSectionMap);
			wirteFile(paramMap, appSystem.getConfigForm(), maxVersionNo, appSystem.getConfigName());
		}
		return wirteFile(descriptionMap, FileFormat.INI, maxVersionNo, "description.ini");
	}

	/**
	 * 生成相应格式的文件
	 * 
	 * @param mapInfo
	 *            参数配置文件内容
	 * @param fileFormat
	 *            文件格式
	 * @param maxVersion
	 *            最大版本号
	 * @param fileName
	 *            文件名称
	 * @return
	 */
	private boolean wirteFile(Map<String, Map<String, String>> mapInfo, FileFormat fileFormat, long maxVersion, String fileName) {
		String fileStr = FishCfg.getFishHome() + FishCfg.fileSep + "param" + FishCfg.fileSep + maxVersion + FishCfg.fileSep + fileName;
		File file = new File(fileStr);
		FileWriter fw = null;
		try {
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			fw = new FileWriter(file);

			switch (fileFormat) {
			case JSON:
				fw.write(JsonUtils.toJson(mapInfo));
				break;
			case XML:
				StringBuffer sb = new StringBuffer();
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				sb.append("<argTypes>\n");
				Set<String> paramTypeNameSet = mapInfo.keySet();
				Iterator<String> paramTypeNameIterator = paramTypeNameSet.iterator();
				while (paramTypeNameIterator.hasNext()) {
					String paramTypeName = paramTypeNameIterator.next();
					sb.append("<argType>\n").append("<name>").append(paramTypeName).append("</name>\n").append("<args>\n");
					Map<String, String> paramSectionMap = mapInfo.get(paramTypeName);
					Set<String> paramSet = paramSectionMap.keySet();
					Iterator<String> paramIterator = paramSet.iterator();
					while (paramIterator.hasNext()) {
						String paramName = paramIterator.next();
						String value = paramSectionMap.get(paramName);
						sb.append("<arg>\n").append("<key>").append(paramName).append("</key>\n");
						sb.append("<value>").append(value).append("</value>\n").append("</arg>\n");
					}
					sb.append("</args>\n</argType>\n");
				}
				sb.append("</argTypes>\n");
				fw.write(sb.toString());
				break;
			case PROPERTIES:
				new PropertiesFileWriter(fileStr, mapInfo);
				break;
			case INI:
				new INIFileWriter(fileStr, mapInfo,maxVersion);
				break;
			default:
				break;
			}
		} catch (IOException e1) {
			logger.error(String.format("file %s writer failer,reason is %s", fileStr, e1.getMessage()));
			e1.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					logger.error(String.format("fileWriter stream close failer,reason is %s", e.getMessage()));
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	@Override
	public IParamPublish make() {
		return new ParamPublish();
	}

	@Override
	public IParamPublish update(IParamPublish publish) {
		return dao.update(publish);
	}
	@Override
	public IParamPublish get(long id) {
		return dao.get(id, IParamPublish.class);
	}
	@Override
	public IParamPublish update(long id, String ret) {
		IParamPublish paramPulish = this.get(id);
		paramPulish.setRet(ret);
		dao.update(paramPulish);
		return paramPulish;
	}

	@Override
	public boolean paramPublishByTemplate(long templateId,long personId) {
		return noticeDeviceDownloadParamFileByTemplate(templateId,generateParamFileByTemplate(templateId), personId);
	}
	@Override
	public boolean paramPublishByDeviceIds(List<Long> deviceIds,long personId) {
		List<Long> versionList = new ArrayList<Long>();
		for(long deviceId:deviceIds){
			versionList.add(generateParamFileByDevice(deviceId));
		}
		noticeDeviceDownloadParamFileByDevice(deviceIds,versionList,personId);
		return true;
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public IParamPublish save(IParamPublish publish) {
		return dao.save(publish);
	}


}

class NoticeThread implements Runnable {

	private Logger logger = LoggerFactory.getLogger(NoticeThread.class);
	private List<IDevice> deviceList;
	private ParamInfo paramInfo;
	private List<ParamInfo> paramInfoList;
//	private IParamPublishResultService paramPulishResultService;
//	private IParamPublishService paramPulishService;

	private SessionFactory sessionFactory;
	
	private PublishJobManager publishJobManager;
	private boolean isTemplate = true;
	private long personId;

	/**
	 * 模板下发
	 * @param deviceList
	 * @param paramInfo
	 * @param publishService
	 * @param publishJobManager
	 */
	public NoticeThread(List<IDevice> deviceList, ParamInfo paramInfo,SessionFactory sessionFactory, PublishJobManager publishJobManager,long personId) {
		this.deviceList = deviceList;
		this.paramInfo = paramInfo;
		this.publishJobManager = publishJobManager;
		this.sessionFactory = sessionFactory;
//		this.paramPulishResultService = publishService.getParamPulishResultService();
		this.personId = personId;
	}	
	/**
	 * 设备下发通知
	 * @param deviceList
	 * @param paramInfoList
	 * @param publishService
	 * @param publishJobManager
	 */
	public NoticeThread(List<IDevice> deviceList, List<ParamInfo> paramInfoList,SessionFactory sessionFactory, PublishJobManager publishJobManager,long personId) {
		this.deviceList = deviceList;
		this.paramInfoList = paramInfoList;
		this.publishJobManager = publishJobManager;
		this.sessionFactory = sessionFactory;
//		this.paramPulishResultService = publishService.getParamPulishResultService();
		this.isTemplate = false;
		this.personId = personId;
	}

	@Override
	public synchronized void run() {
		IParamPublish paramPublish = new ParamPublish();//paramPulishService.make();
		String date = DateUtils.getTimestamp(new Date());
		logger.info("paramPublish date is "+date+",person is "+personId);
		paramPublish.setDate(date);
		paramPublish.setPublisher(personId);
		Session session = sessionFactory.openSession();
		session.save(paramPublish);
//		paramPulishService.save(paramPublish);
//		TODO 设置状态
//		paramPublish.setRet(ret);
		List<IParamPublishResult> paramPublishResultList = new ArrayList<IParamPublishResult>();
		//模板发布
		int index=0;
		ParamInfo paramInfoDetail;
		for(IDevice device:deviceList){
			if(isTemplate){
				paramInfoDetail = paramInfo;
			}else{
				paramInfoDetail = paramInfoList.get(index++);
			}
			IParamPublishResult paramPublishResult = new ParamPublishResult();
			//TODO terminalId
			paramPublishResult.setDeviceId(device.getId());
			paramPublishResult.setParamPublish(paramPublish);
			paramPublishResult.setVersionNo(paramInfoDetail.getVersionNo());
			paramPublishResult.setDevice(device);
			session.save(paramPublishResult);
			paramPublishResultList.add(paramPublishResult);
			publishJobManager.addTask(paramPublishResult);
		}
		paramPublish.setParamPublishs(paramPublishResultList);
		sessionFactory.close();
	}

}
