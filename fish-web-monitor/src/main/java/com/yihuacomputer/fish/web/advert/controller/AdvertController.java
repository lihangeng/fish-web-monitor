package com.yihuacomputer.fish.web.advert.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.IFilter;
import com.yihuacomputer.common.IPageResult;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.fish.api.advert.AdvertDownMethod;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.AdvertValidity;
import com.yihuacomputer.fish.api.advert.IAdvert;
import com.yihuacomputer.fish.api.advert.IAdvertResource;
import com.yihuacomputer.fish.api.advert.IAdvertResourceService;
import com.yihuacomputer.fish.api.advert.IAdvertService;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.web.advert.form.AdvertForm;
import com.yihuacomputer.fish.web.advert.form.AdvertResourceForm;
import com.yihuacomputer.fish.web.advert.form.AdvertVersionForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 广告服务的控制器
 *
 * @author xuxigang
 *
 */
@Controller
@RequestMapping(value = "/advert")
public class AdvertController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(AdvertController.class);

	@Autowired
	private IAdvertService advertService;

	@Autowired
	private IAdvertResourceService advertResourceService;

	@Autowired
	private MessageSource messageSourceVersion;
	@Autowired
	private MessageSource messageSourceEnum;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap searchAdvert(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search advert : start = %s ,limit = %s ", start, limit));
		IFilter filter = getFilter(request);
		filter.descOrder("createdTime");
		IPageResult<IAdvert> pageResult = advertService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toAdvertForm(pageResult.list()));

		return result;
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	public @ResponseBody ModelMap searchAdvertResource(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search advert resource: start = %s ,limit = %s ", start, limit));
		IFilter filter = getFilter(request);

		IPageResult<IAdvertResource> pageResult = advertResourceService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute("data", toAdvertResourceForm(pageResult.list()));
		return result;
	}

	/**
	 * 上传广告资源
	 */
	@RequestMapping(value = "/uploadRes", method = RequestMethod.POST)
	public @ResponseBody String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String oFileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		if (fileSize > 31457280) {
			return "{'success':false,'errors':'0'}";
		}

		String saveFileName = IOUtils.addTimeStampInFileName(oFileName);
		File targetFile = IOUtils.createFile(getTempDir(request) + File.separator + saveFileName);

		// 保存
		try {
			file.transferTo(targetFile);
			return "{'success':true,'oFileName':'" + saveFileName + "'}";
		} catch (Exception e) {
			e.printStackTrace();
			return "{'success':false,'errors':'" + e.getMessage() + "'}";
		}
	}

	private String getTempDir(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		UserSession user = (UserSession) session.getAttribute(FishWebUtils.USER);
		String dir = FishCfg.getTempDir() + File.separator + user.getUserCode() + "_" + sessionId;
		return dir;
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
				if (name.equals("advertId")) {
					filter.eq("advert.id", Long.valueOf(value));
				}
				if (name.equals("advertType")) {
					filter.eq("advertType", AdvertType.valueOf(value));
				}
				if (name.equals("advertDownMethod")) {
					filter.eq("advertDownMethod", AdvertDownMethod.valueOf(value));
				}
				if (name.equals("createdTimeStart")) {
					filter.gt("createdTime", DateUtils.getTimestamp(value));
				}
				if (name.equals("createdTimeEnd")) {
					filter.le("createdTime", DateUtils.getTimestamp(value));
				}
			}
		}
		return filter;
	}

	private List<AdvertForm> toAdvertForm(List<IAdvert> adverts) {
		List<AdvertForm> forms = new ArrayList<AdvertForm>();
		for (IAdvert advert : adverts) {
			AdvertForm form = convertToAdvertForm(advert);
			form.setUserName(advert.getCreateUser() == null ? "" : advert.getCreateUser().getName());
			forms.add(form);
		}
		return forms;
	}

	private List<AdvertResourceForm> toAdvertResourceForm(List<IAdvertResource> adverts) {
		List<AdvertResourceForm> forms = new ArrayList<AdvertResourceForm>();
		for (IAdvertResource advert : adverts) {
			AdvertResourceForm form = new AdvertResourceForm(advert);
			forms.add(form);
		}
		return forms;
	}

	/**
	 * 二代应用广告的目录结构 advert_1.zip --META-INF --AD_IDLE ----0.jpg ----1.jpg
	 * ----..... ----config.xml
	 * 
	 * @param form
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap add(@RequestBody AdvertForm form, HttpServletRequest request) {
		logger.info(" add advert...");

		ModelMap result = new ModelMap();
		IAdvert advert = advertService.make(AdvertType.valueOf(form.getAdvertType()));
		advert.setAdvertDownMethod(AdvertDownMethod.valueOf(form.getAdvertDownMethod()));
		advert.setAdvertValidity(AdvertValidity.valueOf(form.getAdvertValidity()));
		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		advert.setCreateUserId(userSession.getUserId());
		int num = 0;
		String tempDir = getTempDir(request);
		List<String> fileNames = new ArrayList<String>();
		for (AdvertResourceForm resForm : form.getAdvertResources()) {
			IAdvertResource res = advertResourceService.make();
			res.setPlayTime(resForm.getPlayTime());
			res.setBeginTime(resForm.getBeginTime());
			res.setEndTime(resForm.getEndTime());
			if (resForm.getBeginDate() != null) {
				res.setBeginDate(DateUtils.getDate(resForm.getBeginDate()));
			}
			if (resForm.getEndDate() != null) {
				res.setEndDate(DateUtils.getDate(resForm.getEndDate()));
			}
			if (AdvertType.isWords(advert.getAdvertType())) {
				res.setContent(resForm.getContent());
			} else {
				String oFileName = resForm.getContent();
				File file = new File(tempDir + File.separator + oFileName);
				String newFileName = num + IOUtils.getFileSuffix(oFileName).toLowerCase();
				file.renameTo(new File(tempDir + File.separator + newFileName));
				res.setContent(newFileName);
				fileNames.add(newFileName);
				num++;
			}
			res.setAdvert(advert);
			advert.addAdvertResource(res);
		}
		advertService.add(advert);

		// 拷贝临时目录中的文件到advert目录中
		for (String fileName : fileNames) {
			IOUtils.copyFileToDirectory(tempDir + File.separator + fileName, VersionCfg.getAdvertDir() + File.separator + advert.getId() + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType()));
		}

		// 删除临时目录
		IOUtils.deleteDir(tempDir);

		// 生成广告版本文件
		IVersion version = advert.toVersion();
		advert.setVersionId(version.getId());
		advertService.update(advert);

		// 回填值到form中
		form.setId(advert.getId());
		form.setCreatedTime(DateUtils.getTimestamp(advert.getCreatedTime()));
		form = setVersionToForm(version,form);
		form.setUserName(userSession.getUserName());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("data", form);
		return result;
	}

	// 删除广告
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody ModelMap delete(@PathVariable long id) {
		logger.info(" delete advert with cascade: advert.id = " + id);
		ModelMap result = new ModelMap();
		try {
			// 如果广告已经被下发或者被配置在作业中
			advertService.delete(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (NotFoundException iae) {
			logger.warn(iae.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			String tips = messageSourceVersion.getMessage("advert.deleteFailNoFound", new Object[] { iae.getMessage() }, FishCfg.locale);
			result.put(FishConstant.ERROR_MSG, tips);
			// result.put(FishConstant.ERROR_MSG,
			// "删除失败:"+iae.getMessage()+",请刷新列表.");
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			logger.error(ex.getMessage());
			String tips = messageSourceVersion.getMessage("versionType.deleteFail", new Object[] { ex.getMessage() }, FishCfg.locale);
			result.put(FishConstant.ERROR_MSG, tips);
			// result.put(FishConstant.ERROR_MSG, "删除失败:" + ex.getMessage());
		}
		return result;
	}

	@RequestMapping(value = "/{id}/generateVersion", method = RequestMethod.GET)
	public @ResponseBody ModelMap generateVersion(@PathVariable long id) {
		logger.info(" generateVersion: advert.id = " + id);
		ModelMap result = new ModelMap();
		try {
			advertService.getById(id).toVersion();
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			ex.printStackTrace();
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	@RequestMapping(value = "/version", method = RequestMethod.POST)
	public @ResponseBody ModelMap getVersion(@RequestParam long id) {
		logger.info(" getVersion: advert.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IVersion version = advertService.getById(id).getVersion();
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute("data", toAdvertVersion(version));
		} catch (Exception ex) {
			ex.printStackTrace();
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	private AdvertVersionForm toAdvertVersion(IVersion version) {
		return new AdvertVersionForm(version);
	}

	/**
	 * 广告预览
	 * 
	 * @param id
	 *            广告编号
	 * @return
	 */
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public String preview(@RequestParam long id, HttpServletRequest request) {
		// 1.根据广告编号把媒体文件放到临时目录，并把临时目录保存到request中
		// 2.获取媒体资源的文件名并保存到到request中
		IAdvert advert = advertService.getById(id);
		String workHome = VersionCfg.getAdvertDir();
		String contextPath = this.getRealPath(request);
		File targetDir = new File(contextPath + File.separator + id);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		List<String> images = new ArrayList<String>();
		for (IAdvertResource resource : advert.getAdvertResources()) {
			String sourceFilePath = workHome + File.separator + resource.getContent();
			IOUtils.copyFileToDirectory(sourceFilePath, targetDir.getAbsolutePath());
			images.add(resource.getContent());
		}
		if (images.size() > 0) {
			request.setAttribute("tempPath", "/advert/" + id);
			request.setAttribute("images", images);
			return "preview";
		} else {
			return "noPreview";
		}
	}

	@RequestMapping(value = "/preview2", method = RequestMethod.POST)
	public @ResponseBody String preview2(@RequestParam long id, HttpServletRequest request) {
		// 1.根据广告编号把媒体文件放到临时目录，并把临时目录保存到request中
		// 2.获取媒体资源的文件名并保存到到request中
		IAdvert advert = advertService.getById(id);
		String workHome = VersionCfg.getAdvertDir();
		String contextPath = this.getRealPath(request);
		File targetDir = new File(contextPath + File.separator + id);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}
		StringBuffer result = new StringBuffer("[");
		for (IAdvertResource resource : advert.getAdvertResources()) {
			String sourceFilePath = workHome + File.separator + id + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType()) + File.separator + resource.getContent();
			IOUtils.copyFileToDirectory(sourceFilePath, targetDir.getAbsolutePath());
			String image = "tmp/advert/" + id + "/" + resource.getContent();
			result.append("{'picName':'").append(image).append("','playTime':'").append(resource.getPlayTime()).append("'}").append(",");
		}

		if (!result.toString().equals("[")) {
			String r = result.toString().substring(0, result.toString().length() - 1);
			return r + "]";
		}
		return result.append("]").toString();
	}

	private String getRealPath(HttpServletRequest request) {
		return FishWebUtils.getRealPathByTmp(request) + "/advert";
	}

	public AdvertForm convertToAdvertForm(IAdvert advert) {
		AdvertForm advertForm = new AdvertForm();
		advertForm.setId(advert.getId());
		advertForm.setAdvertType(advert.getAdvertType().name());
		advertForm.setAdvertDownMethod(advert.getAdvertDownMethod().name());
		advertForm.setAdvertValidity(advert.getAdvertValidity().name());
		advertForm.setCreatedTime(DateUtils.getTimestamp(advert.getCreatedTime()));
		IVersion version = advert.getVersion();
		if (version != null) {
			advertForm = setVersionToForm(version,advertForm);
		}
		return advertForm;
	}
	public AdvertForm setVersionToForm(IVersion version,AdvertForm advertForm) {
		advertForm.setVersionId(version.getId());
		advertForm.setVersionType(version.getVersionType().getTypeName());
		advertForm.setVersionFile(getVersionFile(version.getVersionType().getTypeName(), version.getServerPath()));
		advertForm.setVersionStatus(getEnumI18n(version.getVersionStatus().getText()));
		advertForm.setVersionNo(version.getVersionNo());
		return advertForm;
    }
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
	private String getVersionFile(String typeName, String fileName) {
		File file = new File(VersionCfg.getVersionDir() + File.separator + typeName + File.separator + fileName);
		return file.exists() ? fileName : null;
	}
}
