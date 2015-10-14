package com.yihuacomputer.fish.web.version.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
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
import com.yihuacomputer.fish.web.util.BasicErrorTips;
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
public class VersionController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(VersionController.class);

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
	private IVersionTypeAtmTypeRelationService versionTypeAtmTypeRelationService;

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
		result.addAttribute(FishConstant.DATA, toForm(pageResult.list(), orgService.get(String.valueOf(userSession.getOrgCode()))));
		return result;
	}

	// 下载文件
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
	 *
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		String typeId = request.getParameter("versionTypeId");
		IVersionType type = versionTypeService.getById(Long.valueOf(typeId));
		String typeName = type.getTypeName();
		String versionNo = request.getParameter("versionNo");
		IVersion version = versionService.findVersion(typeName, versionNo);
		if (version != null) {
			return String.format("{'success':false,'msg':'0'}", versionNo);
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
			e.printStackTrace();
			zipFile.delete();
			return "{'success':false,'msg':'" + e.getMessage() + "'}";
		}
		return "{'success':true,'serverPath':'" + fileName + "'}";
	}

	// 增加,
	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.POST)
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
			v.setDownloadCounter(1);
			versionService.add(v);

			// 回填值到form中
			form.setId(v.getId());
			form.setCreatedTime(DateUtils.getTimestamp(v.getCreatedTime()));
			form.setVersionType(type != null ? type.getTypeName() : "");
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
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, BasicErrorTips.SERVER_HANDLE_ERROR);
		}
		return result;
	}

	// 修改版本信息
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody ModelMap update(@PathVariable long id, @RequestBody VersionForm form) {
		logger.info(" update version : version.id = " + id);
		ModelMap result = new ModelMap();
		IVersion v = versionService.getById(id);
		if (v == null) {
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, BasicErrorTips.UPDATE_ID_NOT_EXIST);
			return result;
		}
		// @since 2.0.0.0不在从更新页面编辑，直接从版本分类信息中获取
		// v.setVersionPath(form.getVersionPath());
		v.setAutoDown(form.isAutoDown());
		v.setUncompress(form.isUncompress());
		v.setEagerRestart(form.isEagerRestart());
		v.setDesc(form.getDesc());
		v.setExecBefore(form.getExecBefore());
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

	// 删除
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ModelMap delete(@PathVariable long id) {
		logger.info(" delete version: version.id = " + id);
		ModelMap result = new ModelMap();
		try {
			versionService.delete(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (DependException dependException) {
			logger.error(dependException.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, dependException.getMessage());
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.put(FishConstant.ERROR_MSG, BasicErrorTips.SERVER_HANDLE_ERROR);
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
			if (FishWebUtils.isIgnoreRequestName(name) || name.equals("sort")) {
				continue;
			} else {
				String value = request.getParameter(name);
				if (StringUtils.isEmpty(value)) {
					continue;
				} else {
					if (name.equals("versionTypeId")) {
						if (!value.equals("0")) {
							filter.eq("versionType.id", Long.valueOf(value));
						}
					} else if (name.equals("versionStatus")) {
						filter.eq("versionStatus", VersionStatus.valueOf(value));
					} else if (name.equals("autoUpdate")) {
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
		// IPageResult<IVersion> pageResult =
		// versionService.pageSelectableDependVersion(start, limit,
		// Long.valueOf(versionTypeId),versionNo);
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
	 * 获得某种版本类型下的下一个版本号
	 *
	 * @param id
	 *            版本类型ID
	 * @return 版本号
	 */
	@RequestMapping(value = "/getNextNo/{id}", method = RequestMethod.GET)
	public @ResponseBody String getNextVersionNo(@PathVariable int id) {
		logger.info(String.format("getNextVersionNo : versionType.id = %s", id));
		String num = versionService.getNextVersionNo(id);
		return num;
	}

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
			logger.warn(String.format("version %s not exsit", versionIdStr));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", list);
			return result;
		}
		filter.eq("version", version);
		if (null == version.getVersionType()) {
			logger.warn(String.format("version %s type not exsit", versionIdStr));
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", list);
			return result;
		}
		long versionTypeId = version.getVersionType().getId();
		String versionTypeName = version.getVersionType().getTypeName();
		filter.eq("versionTypeName", versionTypeName);
		List<Long> devTypeList = versionTypeAtmTypeRelationService.findAtmTypeIds(versionTypeId);
		if (null != devTypeList && devTypeList.size() != 0) {
			filter.in("devType", devTypeList);
		}
		// TODO 如果版本类型没有选取设备型号，可以下发所有设备型号，在此处添加
		UserSession userSession = (UserSession) req.getSession().getAttribute("SESSION_USER");
		String orgFlag = userSession.getOrgFlag();
		filter.eq("orgFlag", "%" + orgFlag);
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
		IFilter filter = new Filter();
		filter.eq("versionType", versionType);
		filter.descOrder("versionStr");
		List<IVersion> versionList = versionService.list(filter);
		
		IFilter filterVersionDistribute = new Filter();
		filterVersionDistribute.eq("versionType", versionTypeId);
		UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
		filterVersionDistribute.eq("orgFlag", userSession.getOrgFlag());
		Map<Long, VersionDistribute> map = versionService.getVersionDistribute(filterVersionDistribute);
		List<VersionDistribute> diplayList = new ArrayList<VersionDistribute>();
		VersionDistribute versionDistribute = new VersionDistribute();
		versionDistribute.setVersionTypeId(versionTypeId);
		for (int index = 0; index < versionList.size(); index++) {
			IVersion version = versionList.get(index);
			if (null != map.get(version.getId())) {
				diplayList.add(map.get(version.getId()));
			} else {
				VersionDistribute versionDistributeNew = new VersionDistribute();
				versionDistributeNew.setVersionId(version.getId());
				versionDistributeNew.setVersionNo(version.getVersionNo());
				versionDistributeNew.setVersionNoNumber(0);
				versionDistributeNew.setVersionTypeId(versionTypeId);
				diplayList.add(versionDistributeNew);
			}
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.TOTAL, diplayList.size());
		result.addAttribute(FishConstant.DATA, diplayList);
		return result;
	}

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
