package com.yihuacomputer.fish.web.version.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.multipart.MultipartFile;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.exception.DependException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.fish.api.atm.IAtmType;
import com.yihuacomputer.fish.api.atm.IAtmTypeService;
import com.yihuacomputer.fish.api.charts.ChartsInfo;
import com.yihuacomputer.fish.api.person.IOrganization;
import com.yihuacomputer.fish.api.person.IOrganizationService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.IVersionService;
import com.yihuacomputer.fish.api.version.IVersionStaticsService;
import com.yihuacomputer.fish.api.version.IVersionStaticsStautsService;
import com.yihuacomputer.fish.api.version.IVersionType;
import com.yihuacomputer.fish.api.version.IVersionTypeAtmTypeRelationService;
import com.yihuacomputer.fish.api.version.IVersionTypeService;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.api.version.VersionChartsDetailForm;
import com.yihuacomputer.fish.api.version.VersionDistribute;
import com.yihuacomputer.fish.api.version.VersionDistributeDetail;
import com.yihuacomputer.fish.api.version.VersionNo;
import com.yihuacomputer.fish.api.version.VersionStatus;
import com.yihuacomputer.fish.api.version.VersionStatusDistribute;
import com.yihuacomputer.fish.web.util.DownFromWebUtils;
import com.yihuacomputer.fish.web.util.FishWebUtils;
import com.yihuacomputer.fish.web.version.form.BaseForm;
import com.yihuacomputer.fish.web.version.form.VersionForm;

/**
 * @author xuxigang
 *
 */
@Controller
@RequestMapping(value = "/version/version")
@ClassNameDescrible(describle="userlog.VersionController")
public class VersionController {

	private Logger logger = LoggerFactory.getLogger(VersionController.class);

	@Autowired
	private IVersionTypeService versionTypeService;

	@Autowired
	private IVersionService versionService;

	@Autowired
	private IAtmTypeService atmTypeService;

	@Autowired
	private IVersionStaticsService vsService;

	@Autowired
	private IOrganizationService orgService;

	@Autowired
	private IVersionStaticsStautsService versionStaticsStatusService;
	
	@Autowired
	private MessageSource messageSource;

	@Autowired
	private IVersionTypeAtmTypeRelationService versionTypeAtmTypeRelationService;

	/**
	 * Bar3d 成功，失败，当前可下发设备[真的可以执行下发操作的设备], 总共符合下发条件的设备[条件为机型符合] 图表BAR
	 * @param wReq
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "versionDetails", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap searchVersionDetails(WebRequest wReq, HttpServletRequest req) {

		String versionIdStr = req.getParameter("versionId");
		logger.info(String.format("search %s  versionDetails ", versionIdStr));
		long versionId = 0;
		if (null != versionIdStr && !"".equals(versionIdStr.trim())) {
			versionId = Long.parseLong(req.getParameter("versionId"));
		}
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		String orgFlag = userSession.getOrgFlag();
		List<ChartsInfo> chartsList = versionStaticsStatusService.getVersionSummaryInfo(versionId, orgFlag, 0, 0);

		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", chartsList);
		return result;
	}

	/**
	 * @param start
	 * @param limit
	 * @param wReq
	 * @param req
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelMap search(@RequestParam int start, @RequestParam int limit, WebRequest wReq, HttpServletRequest req) {
		logger.info(String.format("search version : start = %s ,limt = %s ", start, limit));
		IFilter filter = getFilter(wReq);
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		filter.eq("versionType.display", true);
		filter.descOrder("createdTime");
		IPageResult<IVersion> pageResult = versionService.page(start, limit, filter);

		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, toForm(pageResult.list(), orgService.get(String.valueOf(userSession.getOrgId()))));
		return result;
	}

	/**
	 * 下载文件
	 * @param typeName
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void download(@RequestParam String typeName, @RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info(String.format("down version.file is [%s_%s]", typeName, fileName));
		File file = new File(VersionCfg.getVersionDir() + System.getProperty("file.separator") + typeName + File.separator + fileName);
		if (!file.exists()) {
			logger.error(String.format("down version.file is [%s_%s] is not exist", typeName, fileName));
		}
		DownFromWebUtils.download(file, request, response);
	}

	/**
	 * 上传版本文件
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.VersionController.upload",hasFaceParam=true,faceParam="fileName")
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String typeId = request.getParameter("versionTypeId");
		IVersionType type = versionTypeService.getById(Long.valueOf(typeId));
		String typeName = type.getTypeName();
		String versionNo = request.getParameter("versionNo");
		IVersion version = versionService.findVersion(typeName, versionNo);
		if (version != null) {
			return String.format("{'success':false,'msg':'0'}");
		}

		/*
		 * 为了适应生产环境中多个版本并行升级的问题，放开此限制，江苏农行的需求
		 * if(!isAfterAll(typeName,versionNo)){ return
		 * "{'success':false,'msg':'新建的版本号必须为["+typeName+"]软件分类下的最高版本'}"; }
		 */

		String workHome = VersionCfg.getVersionDir();
		long fileSize = file.getSize();
		if (fileSize > 314572800) {
			return "{'success':false,'msg':'1'}";
		}

		File targetDir = new File(workHome + System.getProperty("file.separator") + typeName);
		if (!targetDir.exists()) {
			targetDir.mkdirs();
		}

		String originalFileName = file.getOriginalFilename();

		String fileName = typeName + "_" + versionNo + originalFileName.substring(originalFileName.lastIndexOf(".")).toLowerCase();
		File zipFile = null;
		// 保存
		try {
			zipFile = new File(targetDir.getAbsolutePath(), fileName);
			file.transferTo(zipFile);
			boolean isZip = ZipUtils.isZipFile(zipFile);
			if (!isZip) {
				zipFile.delete();
				return "{'success':false,'msg':'2'}";
			}
		} catch (Exception e) {
			logger.error(String.format("[%s]", e));
			if(zipFile!=null){
				
				zipFile.delete();
				
			}
			return "{'success':false,'msg':'" + e.getMessage() + "'}";
		}
		return "{'success':true,'serverPath':'" + fileName + "'}";
	}

	/**
	 *增加
	 * @param form
	 * @param request
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.POST)
	@MethodNameDescrible(describle="userlog.VersionController.add",hasReqBodyParam=true,reqBodyClass=VersionForm.class,bodyProperties="versionNo")
	public @ResponseBody ModelMap add(@RequestBody VersionForm form, HttpServletRequest request) {
		logger.info(" add version...");
		ModelMap result = new ModelMap();
		try {
			IVersion v = versionService.make();
			v.setVersionNo(form.getVersionNo());
			IVersionType type = null;
			if (form.getVersionTypeId() > 0) {
				type = versionTypeService.getById(form.getVersionTypeId());
				v.setVersionType(type);
				// @since 2.0直接从版本分类信息中获取，不在增加页面中编辑
				v.setVersionPath(type.getDefaultInstallPath());
			}
			v.setAutoDown(form.isAutoDown());
			v.setUncompress(form.isUncompress());
			v.setEagerRestart(form.isEagerRestart());
			v.setDesc(form.getDesc());
			if (form.getDependVersionId() > 0) {
				v.setDependVersion(versionService.getById(form.getDependVersionId()));
			}
			v.setServerPath(form.getServerPath());
			UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
			v.setCreateUserId(userSession.getUserId());
			v.setExecBefore(form.getExecBefore());
			v.setExecAfter(form.getExecAfter());
			v.setDownloadCounter(0);
			versionService.add(v);

			// 回填值到form中
			form.setId(v.getId());
			form.setCreatedTime(DateUtils.getTimestamp(v.getCreatedTime()));
			form.setVersionType(type != null ? type.getTypeName() : "");
			form.setVersionTypeDesc(type != null ? type.getDesc() : "");
			if (v.getDependVersion() != null) {
				form.setDependVersionId(v.getDependVersion().getId());
				form.setDependVersion(v.getDependVersion().getFullName());
				form.setDvDisplayName(v.getDependVersion().getFullName() + " [" + v.getDependVersion().getServerPath() + "]");
			}
			form.setVersionNo(v.getVersionNo());
			form.setUserName(userSession.getUserName());

			// form.setVersionStatics(vsService.getVersionStatics(v,orgService.getByCode(userSession.getOrgCode())));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", form);
		} catch (Exception ex) {
			logger.error(String.format("[%s]", ex));
			result.addAttribute(FishConstant.SUCCESS, false);
			
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("error.handleError", null, FishCfg.locale));
		}
		return result;
	}

	/**
	 * 修改版本信息
	 * @param id
	 * @param form
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@MethodNameDescrible(describle="userlog.VersionController.update",hasReqBodyParam=true,reqBodyClass=VersionForm.class,bodyProperties="versionNo")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody VersionForm form) {
		logger.info(" update version : version.id = " + id);
		form.setId(id);
		ModelMap result = new ModelMap();
		IVersion v = versionService.getById(id);
		if (v == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, messageSource.getMessage("error.update.noData", null, FishCfg.locale));
			return result;
		}
		// @since 2.0.0.0不在从更新页面编辑，直接从版本分类信息中获取
		// v.setVersionPath(form.getVersionPath());
		v.setAutoDown(form.isAutoDown());
		v.setUncompress(form.isUncompress());
		v.setEagerRestart(form.isEagerRestart());
		v.setDesc(form.getDesc());
		v.setExecBefore(form.getExecBefore());
		v.setExecAfter(form.getExecAfter());
		if (form.getDependVersionId() > 0) {
			v.setDependVersion(versionService.getById(form.getDependVersionId()));
		} else {
			v.setDependVersion(null);
		}

		versionService.update(v);
		// 回填值
		if (v.getDependVersion() != null) {
			form.setDependVersionId(v.getDependVersion().getId());
			form.setDependVersion(v.getDependVersion().getFullName());
			form.setDvDisplayName(v.getDependVersion().getFullName() + " [" + v.getDependVersion().getServerPath() + "]");
		} else {
			form.setDependVersionId(0);
			form.setDependVersion(null);
			form.setDvDisplayName(null);
		}
		result.addAttribute("data", form);
		return result;
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@MethodNameDescrible(describle="userlog.VersionController.delete",hasLogKey=true)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ModelMap delete(@PathVariable long id) {
		logger.info(" delete version: version.id = " + id);
		IVersion version = versionService.getById(id);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.LOG_KEY, version.getFullName());
		try {
			versionService.delete(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (DependException dependException) {
			logger.error(String.format("[%s]", dependException));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, dependException.getMessage());
		} catch (Exception ex) {
			logger.error(String.format("[%s]", ex));
			result.addAttribute(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, messageSource.getMessage("error.handleError", null, FishCfg.locale));
		}
		return result;
	}

	// 转换数据格式
	private List<VersionForm> toForm(List<IVersion> versions, IOrganization org) {
		List<VersionForm> forms = new ArrayList<VersionForm>();
		for (IVersion v : versions) {
			VersionForm form = new VersionForm(v);
			if (v.getCreateUser() != null) {
				form.setUserName(v.getCreateUser().getName());
			}
			forms.add(form);
		}
		return forms;
	}

	// 获得查询条件
	private IFilter getFilter(WebRequest request) {
		IFilter filter = new Filter();
		Iterator<String> iterator = request.getParameterNames();
		while (iterator.hasNext()) {
			String name = iterator.next();
			if (FishWebUtils.isIgnoreRequestName(name) || "sort".equals(name)) {
				continue;
			} else {
				String value = request.getParameter(name);
				if (StringUtils.isEmpty(value)) {
					continue;
				} else {
					if ("versionTypeId".equals(name)) {
						if (!"0".equals(value)) {
							filter.eq("versionType.id", Long.valueOf(value));
						}
					} else if ("versionStatus".equals(name)) {
						filter.eq("versionStatus", VersionStatus.valueOf(value));
					} else if ("autoUpdate".equals(name)) {
						filter.eq("autoDown", Boolean.valueOf(value));
					} else {
						filter.like(name, request.getParameter(name));
					}
				}
			}
		}
		return filter;
	}

	/**
	 * 获得依赖版本信息
	 * 
	 * @param start
	 * @param limit
	 * @param versionTypeId
	 *            软件分类ID
	 * @param versionNo
	 *            软件版本号
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/combo")
	public @ResponseBody ModelMap combo(@RequestParam int start, @RequestParam int limit, @RequestParam long versionTypeId, @RequestParam String versionNo, HttpServletRequest request) {
		logger.info(String.format("get version's depend versions by versionId = %s and currentVersionNo = %s", versionTypeId, versionNo));
		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		IFilter filter = new Filter();
		filter.eq("versionType.id", versionTypeId);
		filter.descOrder("createdTime");
		List<IVersion> versions = versionService.list(filter);
		List<IVersion> target = new ArrayList<IVersion>();
		for (IVersion version : versions) {
			VersionNo currentNo = new VersionNo(versionNo);
			if (currentNo.isBiggerThan(new VersionNo(version.getVersionNo()))) {
				target.add(version);
			}
		}
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", toForm(target, orgService.get(String.valueOf(userSession.getOrgId()))));
		return result;
	}

	/**
	 * 获得设备型号
	 *
	 * @param start
	 * @param limit
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/combo/atmType")
	@ResponseBody
	public ModelMap getAtmType(WebRequest request) {
		Iterable<IAtmType> types = atmTypeService.list();
		ModelMap result = new ModelMap();
		result.addAttribute("data", toAtmType(types));
		return result;
	}

	private List<BaseForm> toAtmType(Iterable<IAtmType> types) {
		List<BaseForm> forms = new ArrayList<BaseForm>();
		for (IAtmType type : types) {
			forms.add(new BaseForm(type.getId(), type.getName()));
		}
		return forms;
	}


	/**
	 * 柱状图成功，失败，当前可下发设备[真的可以执行下发操作的设备], 总共符合下发条件的设备[条件为机型符合] 明细信息
	 * @param start
	 * @param limit
	 * @param wReq
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "versionChartsDetails", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap versionChartsDetails(@RequestParam int start, @RequestParam int limit, WebRequest wReq, HttpServletRequest req) {

		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		String versionIdStr = req.getParameter("versionId");
		logger.info(String.format("search %s  versionChartsDetails ", versionIdStr));
		long versionId = 0;
		if (null != versionIdStr && !"".equals(versionIdStr.trim())) {
			versionId = Long.parseLong(req.getParameter("versionId"));
		}
		IVersion version = versionService.getById(versionId);
		List<VersionChartsDetailForm> list = null;
		if (null == version) {
			logger.warn(String.format("version %s not exist", versionIdStr));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", list);
			return result;
		}
		filter.eq("version", version);
		if (null == version.getVersionType()) {
			logger.warn(String.format("version %s type not exist", versionIdStr));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", list);
			return result;
		}
		long versionTypeId = version.getVersionType().getId();
		String versionTypeName = version.getVersionType().getTypeName();
		filter.eq("versionTypeName", versionTypeName);
		List<Long> devTypeList = versionTypeAtmTypeRelationService.findAtmTypeIds(versionTypeId);
		if (null != devTypeList && !devTypeList.isEmpty()) {
			filter.in("devType", devTypeList);
		}
		// TODO 如果版本类型没有选取设备型号，可以下发所有设备型号，在此处添加
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		String orgFlag = userSession.getOrgFlag();
		filter.eq("orgFlag",orgFlag +  "%");
		// 获取要查看版本内容标识(下发成功、失败....)
		String flag = req.getParameter("flag");
		if (null != flag && !"".equals(flag)) {
			filter.eq("flag", Integer.parseInt(flag));
		}
		IPageResult<VersionChartsDetailForm> pageResult = versionStaticsStatusService.getVersionChartsDetailForm(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, pageResult.list());
		return result;
	}

	/**
	 * @param displayNumber
	 * @param versionTypeId
	 * @param webRequest
	 * @param request
	 * @return 获取每个版本所占设备的数量(图表显示)如果当前版本生产上不存在，则不显示.
	 */
	@RequestMapping(value = "distribute", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getVersionDistribute(@RequestParam int displayNumber, @RequestParam long versionTypeId, WebRequest webRequest, HttpServletRequest request) {
		IVersionType versionType = versionTypeService.getById(versionTypeId);
		ModelMap result = new ModelMap();
		if (null == versionType) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.TOTAL, 0);
			result.addAttribute(FishConstant.DATA, null);
			return result;
		}
		
		IFilter filterVersionDistribute = new Filter();
		filterVersionDistribute.eq("versionType", versionTypeId);
		UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
		filterVersionDistribute.eq("orgFlag", userSession.getOrgFlag());
		List<VersionDistribute> diplayList = versionService.getVersionDistribute(filterVersionDistribute);
		List<VersionDistribute> diplayList1 = new ArrayList<VersionDistribute>();
		//如果当前版本生产上不存在，则不显示.
		for (VersionDistribute vd:diplayList) {
			if ("".equals(vd.getVersionNo())) {
				vd.setVersionNo(messageSource.getMessage("version.versionno.unknown", null, FishCfg.locale));
			} 
			diplayList1.add(vd);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, diplayList1.size());
		result.addAttribute(FishConstant.DATA, diplayList1);
		return result;
	}

	/**
	 * @param versionId
	 * @param webRequest
	 * @param request
	 * @return 每个版本在设备上下发的状态信息(taskStatus) 图表PIE
	 */
	@RequestMapping(value = "distributeStatus", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getVersionDistributeStatus(@RequestParam long versionId, WebRequest webRequest, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
		IFilter filter = new Filter();
		filter.eq("versionId", versionId);
		filter.eq("orgFlag", userSession.getOrgFlag());
		List<VersionStatusDistribute> list = versionService.getVersionStatusDistribute(filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, list.size());
		result.addAttribute(FishConstant.DATA, list);
		return result;
	}
	
	/**
	 * 某个版本设备各种状态的明细信息(列表)
	 * @param versionId
	 * @param start
	 * @param limit
	 * @param webRequest
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "distributeStatusDetail", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap getVersionDistributeStatusDetail(@RequestParam long versionId,@RequestParam int start,@RequestParam int limit, WebRequest webRequest, HttpServletRequest request) {
		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);

		IFilter filter = new Filter();
		filter.eq("versionId", versionId);
		filter.eq("orgFlag", userSession.getOrgFlag());
		filter.eq("taskStatus", request.getParameter("taskStatus"));
		IPageResult<VersionDistributeDetail> pageResult =  versionService.getVersionStatusDistributeDetail(start, limit, filter);

		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, pageResult.list());
		return result;
	}
}
