package com.yihuacomputer.fish.web.advert.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import com.yihuacomputer.common.annotation.ClassNameDescrible;
import com.yihuacomputer.common.annotation.MethodNameDescrible;
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.exception.NotFoundException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.fish.api.advert.AbstractAdvertZipGenerator;
import com.yihuacomputer.fish.api.advert.AdvertDownMethod;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.AdvertValidity;
import com.yihuacomputer.fish.api.advert.IAdvert;
import com.yihuacomputer.fish.api.advert.IAdvertResource;
import com.yihuacomputer.fish.api.advert.IAdvertResourceService;
import com.yihuacomputer.fish.api.advert.IAdvertService;
import com.yihuacomputer.fish.api.advert.Screen;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.version.IVersion;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.web.advert.form.AdvertForm;
import com.yihuacomputer.fish.web.advert.form.AdvertResourceForm;
import com.yihuacomputer.fish.web.advert.form.AdvertVersionForm;
import com.yihuacomputer.fish.web.advert.form.UploadResourceForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

/**
 * 广告服务的控制器
 *
 * @author xuxigang
 *
 */
@Controller
@RequestMapping(value = "/advert")
@ClassNameDescrible(describle="userlog.AdvertController")
public class AdvertController {
	private Logger logger = org.slf4j.LoggerFactory.getLogger(AdvertController.class);

	@Autowired
	private MessageSource messageSourceEnum;
	
	@Autowired
	private MessageSource messageSourceVersion;
	
	@Autowired
	private IAdvertService advertService;

	@Autowired
	private IAdvertResourceService advertResourceService;

	private final static long ADVERTBYTESIZE=5242880;
	private final static long MILLO=1024*1024l;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelMap searchAdvert(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search advert : start = %s ,limit = %s ", start, limit));
		IFilter filter = getFilter(request);
		filter.descOrder("createdTime");
		IPageResult<IAdvert> pageResult = advertService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, toAdvertForm(pageResult.list()));

		return result;
	}

	@RequestMapping(value = "/resource", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap searchAdvertResource(@RequestParam int start, @RequestParam int limit, WebRequest request) {
		logger.info(String.format("search advert resource: start = %s ,limit = %s ", start, limit));
		IFilter filter = getFilter(request);

		IPageResult<IAdvertResource> pageResult = advertResourceService.page(start, limit, filter);
		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute("total", pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, toAdvertResourceForm(pageResult.list()));
		return result;
	}

	/**
	 * 上传广告资源
	 */
	@RequestMapping(value = "/uploadRes", method = RequestMethod.POST)
	@ResponseBody
	@MethodNameDescrible(describle="userlog.AdvertController.upload",hasArgs=false)
	public String upload(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String oFileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		
		if (fileSize > ADVERTBYTESIZE) {
			
			return "{'success':false,'errors':'"+messageSourceVersion.getMessage("advert.fileSize", new Object[]{ADVERTBYTESIZE/MILLO}, FishCfg.locale)+"'}";
		}

		String saveFileName = IOUtils.addTimeStampInFileName(oFileName);
		File targetFile = IOUtils.createFile(getTempRealDir(request) + File.separator + saveFileName);

		// 保存
		try {
			file.transferTo(targetFile);
			return "{'success':true,'oFileName':'" + saveFileName + "'}";
		} catch (Exception e) {
			logger.error("upload file exception:" + e.getMessage());
			return "{'success':false,'errors':'"+messageSourceVersion.getMessage("advert.upload.exception", new Object[]{saveFileName, e.getMessage()}, FishCfg.locale)+"'}";
		}
	}

	@RequestMapping(value = "/uploadRes/screen", method = RequestMethod.POST)
	@ResponseBody
	@MethodNameDescrible(describle="userlog.AdvertController.uploadByScreen",hasArgs=false)
	public String uploadByScreen(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String oFileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		if (fileSize > ADVERTBYTESIZE) {
			return "{'success':false,'errors':'"+messageSourceVersion.getMessage("advert.fileSize", new Object[]{ADVERTBYTESIZE/MILLO}, FishCfg.locale)+"'}";
		}

		String handledOFileName = oFileName;
		String suffixs[] = oFileName.split("\\.");
		String suffixName = suffixs[suffixs.length - 1];
		logger.info("suffixName name is" + suffixName);
		if (this.containsChinese(oFileName)) {
			handledOFileName = "zh." + suffixName;
		}
		logger.info("uploadfile name is" + handledOFileName);
		String screen = request.getParameter("screen");

		String saveFileName = IOUtils.addTimeStampInFileName(handledOFileName);
		File targetFile = IOUtils.createFile(getTempRealDir(request) + File.separator + screen + File.separator + saveFileName);

		// 保存
		try {
			file.transferTo(targetFile);
			UploadResourceForm form = new UploadResourceForm(oFileName, saveFileName, getTempWebDir(request, screen, saveFileName), screen);
			return JsonUtils.toJson(form);
		} catch (Exception e) {
			logger.error("upload file exception:" + e.getMessage());
			return "{'success':false,'errors':'"+messageSourceVersion.getMessage("advert.upload.exception", new Object[]{saveFileName, e.getMessage()}, FishCfg.locale)+"'}";
		}
	}

	public boolean containsChinese(String s) {
		String pattern = "[u4e00-u9fa5]+";
		Pattern p = Pattern.compile(pattern);
		Matcher result = p.matcher(s);
		return result.find();
	}

	private String getSessionDir(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		UserSession user = (UserSession) session.getAttribute(FishWebUtils.USER);
		return user.getUserCode() + "_" + sessionId;
	}

	private String getTempWebDir(HttpServletRequest request, String screen, String saveFileName) {
		return "tmp/advert/" + this.getSessionDir(request) + "/" + screen + "/" + saveFileName;
	}

	private String getTempRealDir(HttpServletRequest request) {
		return this.getRealPath(request) + File.separator + this.getSessionDir(request);
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
					filter.ge("createdTime", DateUtils.getTimestamp(value+" 00:00:00"));
				}
				if (name.equals("createdTimeEnd")) {
					filter.le("createdTime", DateUtils.getTimestamp(value+" 23:59:59"));
				}
			}
		}
		return filter;
	}

	private List<AdvertForm> toAdvertForm(List<IAdvert> adverts) {
		List<AdvertForm> forms = new ArrayList<AdvertForm>();
		for (IAdvert advert : adverts) {
			AdvertForm form = convertToFrom(advert);
			form.setUserName(advert.getCreateUser() == null ? "" : advert.getCreateUser().getName());
			forms.add(form);
		}
		return forms;
	}

    public AdvertForm convertToFrom(IAdvert advert) {
    	AdvertForm form = new AdvertForm();
    	form.setId(advert.getId());
    	form.setAdvertType(advert.getAdvertType().name());
    	form.setAdvertDownMethod(advert.getAdvertDownMethod().name());
    	form.setAdvertValidity(advert.getAdvertValidity().name());
    	form.setCreatedTime(DateUtils.getTimestamp(advert.getCreatedTime()));
        IVersion version = advert.getVersion();
        if (version != null) {
        	setVersion(form,version);
        	form.setVersionDesc(version.getVersionType().getTypeName()+"_"+advert.getId());
        }
        return form;
    }
    
    public AdvertForm setVersion(AdvertForm form,IVersion version) {
    	form.setVersionId(version.getId());
    	form.setVersionType(version.getVersionType().getTypeName());
    	form.setVersionFile(getVersionFile(version.getVersionType().getTypeName(), version.getServerPath()));
    	form.setVersionStatus(getEnumI18n(version.getVersionStatus().getText()));
    	form.setVersionNo(version.getVersionNo());
    	return form;
    }

    private String getVersionFile(String typeName, String fileName) {
        File file = new File(VersionCfg.getVersionDir() + File.separator + typeName + File.separator + fileName);
        return file.exists() ? fileName : null;
    }
	private List<AdvertResourceForm> toAdvertResourceForm(List<IAdvertResource> adverts) {
		List<AdvertResourceForm> forms = new ArrayList<AdvertResourceForm>();
		for (IAdvertResource advert : adverts) {
			AdvertResourceForm form =convert(advert);
			forms.add(form);
		}
		return forms;
	}
	public AdvertResourceForm convert(IAdvertResource advRes) {
		AdvertResourceForm form = new AdvertResourceForm();
		form.setId(advRes.getId());
		form.setAdvertId(advRes.getAdvert().getId());
		form.setPlayTime(advRes.getPlayTime());
		form.setBeginDate(advRes.getBeginDate() == null ? "" : DateUtils.getDate(advRes.getBeginDate()));
		form.setEndDate(advRes.getEndDate() == null ? "" : DateUtils.getDate(advRes.getEndDate()));
		form.setBeginTime(advRes.getBeginTime());
		form.setEndTime(advRes.getEndTime());
		form.setContent(advRes.getContent());
		form.setScreen(advRes.getScreen());
		form.setFileSize(getResourceFileSize(form,advRes.getAdvert().getAdvertType(), advRes.getAdvert().getId()));
		return form;
	}

	private String getResourceFileSize(AdvertResourceForm form,AdvertType type, long id) {
		if (AdvertType.isWords(type)) {
			return "0";
		} else {
			String fileName = VersionCfg.getAdvertDir() + File.separator + id + File.separator
					+ AdvertTypeConversionService.convert(type) + File.separator + getEnumI18n(form.getScreen().getText())+File.separator + form.getContent();
			File file = new File(fileName);
			long size = file.length();
			if (size < 1024) {
				return size + " Byte";
			} else {
				return size / 1024 + " KB";
			}
		}
	}
	/**
	 * 二代应用广告的目录结构 advert_1.zip --META-INF --AD_IDLE ----1024 ------0.jpg ------1.jpg ------..... ------config.ini ----800 ------0.jpg ------..... ------config.ini
	 *
	 * @param form
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@MethodNameDescrible(describle="userlog.AdvertController.add",hasLogKey=true)
	public ModelMap add(@RequestBody AdvertForm form, HttpServletRequest request) {
		logger.info(" add advert...");

		ModelMap result = new ModelMap();
		result.addAttribute(FishConstant.LOG_KEY, getEnumI18n("AdvertType."+form.getAdvertType()));
		IAdvert advert = advertService.make(AdvertType.valueOf(form.getAdvertType()));
		advert.setAdvertDownMethod(AdvertDownMethod.valueOf(form.getAdvertDownMethod()));
		advert.setAdvertValidity(AdvertValidity.valueOf(form.getAdvertValidity()));
		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		advert.setCreateUserId(userSession.getUserId());
		IVersion version = null;

		if (AdvertType.isWords(advert.getAdvertType())) {
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
				res.setContent(resForm.getContent());
				res.setAdvert(advert);
				advert.addAdvertResource(res);
			}

			advertService.add(advert);
			version = advert.toVersion(new AbstractAdvertZipGenerator() {

				protected void beforeZip(IAdvert advert) {
					// 二代应用需要
					String advertVersionFilePath = VersionCfg.getVersionDir() + File.separator + "advert" + File.separator;
			        File advertFile = new File(advertVersionFilePath);
			        if(!advertFile.exists()||!advertFile.isDirectory()){
			        	advertFile.mkdirs();
			        }
					genenateMetaFile(advert);
				}

				private String getAdvertSourcePath(IAdvert advert) {
					return VersionCfg.getAdvertDir() + File.separator + advert.getId();
				}

				private void genenateMetaFile(IAdvert advert) {
					File file = new File(getAdvertSourcePath(advert) + File.separator + "META-INF");
					if (!file.exists()&&!file.mkdirs()){
							throw new AppException(messageSourceVersion.getMessage("advert.createDir.fail", new Object[]{file.getName()}, FishCfg.locale));
					}

					File meta = new File(file.getAbsolutePath() + File.separator + "MANIFEST.MF");
					if (!meta.exists()) {
						try {
							meta.createNewFile();
						} catch (IOException e) {
							throw new AppException(messageSourceVersion.getMessage("advert.createMeta.fail",null,FishCfg.locale));
						}
					}

					FileWriter fw = null;
					BufferedWriter bw = null;
					try {
						fw = new FileWriter(meta);
						bw = new BufferedWriter(fw);
						bw.write("Manifest-Version: 1.0");
						bw.newLine();
						bw.write("Gump-Scheduler: IMMEDIATE");
						bw.newLine();
						bw.write("Gump-Type: ADVERTISE");
						bw.newLine();
						bw.write("Gump-AppName: YHATMC-AD_MESSAGE");
						bw.newLine();
						bw.write("Built-By: yihua");
						bw.newLine();
						bw.write("Build-Jdk: 1.6.0_25");
						bw.newLine();
						bw.write("Gump-Version: 0001");
						bw.newLine();
						bw.write("Created-By: monitor");
						bw.newLine();
						bw.write("Archiver-Version: Plexus Archiver");
						bw.newLine();
						bw.write("Gump-InstallDate: 2012-02-01 00:00:00");
						bw.newLine();
						bw.write("Gump-InstallEndDate: 2012-02-01 00:00:00");
						bw.flush();
						bw.close();
						fw.close();
					} catch (IOException e) {
						logger.error("inputStream execption:" + e.getMessage());
					} finally {
						try {
							if (bw != null) {
								bw.close();
							}
							if (fw != null) {
								fw.close();
							}
						} catch (IOException e) {
							logger.error("close ioStream exception:" + e.getMessage());
						}
					}
				}
			});
		} else {
			int num1024 = 0;
			int num800 = 0;
			int num600 = 0;
			String tempDir = getTempRealDir(request);
			List<ScreenFile> fileNames = new ArrayList<ScreenFile>();
			for (AdvertResourceForm resForm : form.getAdvertResources()) {
				IAdvertResource res = advertResourceService.make();
				res.setPlayTime(resForm.getPlayTime());
				res.setBeginTime(resForm.getBeginTime());
				res.setEndTime(resForm.getEndTime());
				if (resForm.getBeginDate() != null && !"".equals(resForm.getBeginDate())) {
					res.setBeginDate(DateUtils.getDate(resForm.getBeginDate()));
				}
				if (resForm.getEndDate() != null && resForm.getEndDate().length()>9) {
					res.setEndDate(DateUtils.getDate(resForm.getEndDate()));
				}
				if (AdvertType.isWords(advert.getAdvertType())) {
					res.setContent(resForm.getContent());
				} else {
					int num = 0;
					if (resForm.getScreen().equals(Screen.SCREEN_1024)) {
						num = num1024;
						num1024++;
					} else if (resForm.getScreen().equals(Screen.SCREEN_800)) {
						num = num800;
						num800++;
					} else {
						num = num600;
						num600++;
					}
					String oFileName = resForm.getContent();
					String absDir = tempDir + File.separator + getEnumI18n(resForm.getScreen().getText());
					File file = new File(absDir + File.separator + oFileName);
					String newFileName = num + IOUtils.getFileSuffix(oFileName).toLowerCase();
					if(!file.renameTo(new File(absDir + File.separator + newFileName))){
						throw new AppException(getVersionI18n("advert.rename.fail", new Object[]{newFileName}));
						
					}
					res.setContent(newFileName);
					fileNames.add(new ScreenFile(resForm.getScreen(), File.separator + newFileName));
					res.setScreen(resForm.getScreen());
					num++;
				}
				res.setAdvert(advert);
				advert.addAdvertResource(res);
			}
			advertService.add(advert);

			// 拷贝临时目录中的文件到advert目录中
			for (ScreenFile screenFile : fileNames) {
				IOUtils.copyFileToDirectory(tempDir + File.separator + getEnumI18n(screenFile.getScreen().getText()) + File.separator + screenFile.getFileName(), VersionCfg.getAdvertDir() + File.separator
						+ advert.getId() + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType()) + File.separator + getEnumI18n(screenFile.getScreen().getText()));
			}

			// 删除临时目录
			IOUtils.deleteDir(tempDir);

			// 生成广告版本文件
			version = advert.toVersion(new AbstractAdvertZipGenerator() {
				protected void getAdvertConfigInfoAndSaveToFile(IAdvert advert) {
					saveConfigInfoToFileByScreen(advert, Screen.SCREEN_1024);
					saveConfigInfoToFileByScreen(advert, Screen.SCREEN_800);
					saveConfigInfoToFileByScreen(advert, Screen.SCREEN_600);
				}

				private void saveConfigInfoToFileByScreen(IAdvert advert, Screen screen) {
					String configInfo = advert.getAdvertConfigByScreen(screen);
					IOUtils.writeStringToFile(getConfigFileBasePath(advert) + File.separator + getEnumI18n(screen.getText()) + File.separator + "config.ini", configInfo);
				}
			});

		}
		advert.setVersionId(version.getId());
		advertService.update(advert);

		// 回填值到form中
		form.setId(advert.getId());
		form.setCreatedTime(DateUtils.getTimestamp(advert.getCreatedTime()));
		setVersion(form,version);
		form.setUserName(userSession.getUserName());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, form);

		return result;
	}
	private String getVersionI18n(String key,Object[] value){
		return messageSourceVersion.getMessage(key,value, FishCfg.locale);
	}
	// 删除广告
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	@MethodNameDescrible(describle="userlog.AdvertController.delete",hasLogKey=true)
	public ModelMap delete(@PathVariable long id) {
		logger.info(" delete advert with cascade: advert.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IAdvert advert = advertService.getById(id);
			result.addAttribute(FishConstant.LOG_KEY, getEnumI18n(advert.getAdvertType().getText()));
			// 如果广告已经被下发或者被配置在作业中
			advertService.delete(id);
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (NotFoundException iae) {
			logger.warn(iae.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.put("errors", getVersionI18n("advert.deleteFailNoFound",new Object[]{iae.getMessage() }));
		} catch (Exception ex) {
			result.addAttribute(FishConstant.SUCCESS, false);
			logger.error(ex.getMessage());
			result.put("errors", getVersionI18n("versionType.deleteFail",new Object[]{ex.getMessage() }));
		}
		return result;
	}
	 private String getEnumI18n(String enumText){
	    	if(null==enumText){
	    		return "";
	    	}
	    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
	    }
	
	 
	@RequestMapping(value = "/{id}/generateVersion", method = RequestMethod.GET)
	@ResponseBody
	public ModelMap generateVersion(@PathVariable long id) {
		logger.info(" generateVersion: advert.id = " + id);
		ModelMap result = new ModelMap();
		try {
			advertService.getById(id).toVersion();
			result.addAttribute(FishConstant.SUCCESS, true);
		} catch (Exception ex) {
			logger.error("general version File execption:" + ex.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
		}
		return result;
	}

	@RequestMapping(value = "/version", method = RequestMethod.POST)
	@ResponseBody
	public ModelMap getVersion(@RequestParam long id) {
		logger.info(" getVersion: advert.id = " + id);
		ModelMap result = new ModelMap();
		try {
			IVersion version = advertService.getById(id).getVersion();
			result.addAttribute(FishConstant.SUCCESS, true);
			result.addAttribute(FishConstant.DATA, toAdvertVersion(version));
		} catch (Exception ex) {
			logger.error("get advert version error:" + ex.getMessage());
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
		if (!targetDir.exists()&&!targetDir.mkdir()) {
			throw new AppException(getVersionI18n("advert.createDir.fail", new Object[]{targetDir.getName()}));
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
	@ResponseBody
	public String preview2(@RequestParam long id, @RequestParam String screen, HttpServletRequest request) {
		// 1.根据广告编号把媒体文件放到临时目录，并把临时目录保存到request中
		// 2.获取媒体资源的文件名并保存到到request中
		IAdvert advert = advertService.getById(id);
		String workHome = VersionCfg.getAdvertDir();
		String contextPath = this.getRealPath(request);
		File targetDir = new File(contextPath + File.separator + id + File.separator + screen);
		if (!targetDir.exists()&&!targetDir.mkdirs()){
			throw new AppException(getVersionI18n("advert.createDir.fail", new Object[]{targetDir.getName()}));
		}
		StringBuffer result = new StringBuffer("[");
		for (IAdvertResource resource : advert.getAdvertResources()) {
			if (getEnumI18n(resource.getScreen().getText()).equals(screen)) {
				String sourceFilePath = workHome + File.separator + id + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType()) + File.separator + getEnumI18n(resource.getScreen().getText())
						+ File.separator + resource.getContent();
				IOUtils.copyFileToDirectory(sourceFilePath, targetDir.getAbsolutePath());
				String image = "tmp/advert/" + id + "/" + screen + "/" + resource.getContent();
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
		return FishWebUtils.getRealPathByTmp(request) + "/advert";
	}

	class ScreenFile {
		private Screen screen;

		private String fileName;

		public ScreenFile(Screen screen, String fileName) {
			this.screen = screen;
			this.fileName = fileName;
		}

		public Screen getScreen() {
			return screen;
		}

		public void setScreen(Screen screen) {
			this.screen = screen;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

	}

}
