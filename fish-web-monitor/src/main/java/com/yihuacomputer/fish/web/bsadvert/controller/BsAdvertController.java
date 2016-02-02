package com.yihuacomputer.fish.web.bsadvert.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
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
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.Screen;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroup;
import com.yihuacomputer.fish.api.advert.bs.IAdvertGroupService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResource;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertResourceService;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.api.advert.util.AdvertTypeConversionService;
import com.yihuacomputer.fish.api.person.IUser;
import com.yihuacomputer.fish.api.person.IUserService;
import com.yihuacomputer.fish.api.person.UserSession;
import com.yihuacomputer.fish.api.version.VersionCfg;
import com.yihuacomputer.fish.web.advert.form.AdvertResourceForm;
import com.yihuacomputer.fish.web.advert.form.UploadResourceForm;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("bsadvert/advert")
public class BsAdvertController {

	private Logger logger = org.slf4j.LoggerFactory.getLogger(BsAdvertController.class);

	@Autowired
	private MessageSource messageSourceEnum;
	

	@Autowired
	private IUserService userService;

	@Autowired
	private IBsAdvertService bsAdvertService;
	
	@Autowired
	private IAdvertGroupService advertGroupService;

	@Autowired
	private IBsAdvertResourceService bsAdvertResourceService;

	@Autowired
	private MessageSource messageSourceVersion;

	private final static long ADVERTBYTESIZE = 5242880;
	private final static long MILLO = 1024 * 1024l;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachBsAdvertList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search bsAdvert group ");
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		String groupId = request.getParameter("groupId");
		String advertName = request.getParameter("advertName");
		String actived = request.getParameter("actived");
		String updateTimeEnd = request.getParameter("updateTimeEnd");
		String updateTimeStart = request.getParameter("updateTimeStart");
		if(null!=groupId&&!"".equals(groupId.trim())){
			filter.eq("groupId", Long.parseLong(groupId));
		}
		if(null!=advertName&&!"".equals(advertName.trim())){
			filter.eq("advertName", advertName);
		}
		if(null!=actived&&!"".equals(actived.trim())){
			filter.eq("actived", actived.equals("1")?Boolean.TRUE:Boolean.FALSE);
		}
		if(null!=actived&&!"".equals(actived.trim())){
			filter.eq("actived", actived.equals("1")?Boolean.TRUE:Boolean.FALSE);
		}
		if(null!=updateTimeEnd&&!"".equals(updateTimeEnd.trim())){
			filter.eq("updateTimeEnd", DateUtils.getTimestamp(updateTimeEnd+" 23:59:59"));
		}
		if(null!=updateTimeStart&&!"".equals(updateTimeStart.trim())){
			filter.eq("updateTimeStart", DateUtils.getTimestamp(updateTimeStart+" 00:00:00"));
		}
		UserSession userSession = (UserSession)request.getSession().getAttribute(FishWebUtils.USER);
		filter.eq("orgId", userSession.getOrgId());
		IPageResult<Object> pageResult = bsAdvertService.page(start, limit, filter);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}
	
	
	/**
	 * 激活广告
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value="/actived",method = RequestMethod.POST)
	public @ResponseBody ModelMap activedBsAdvert(@RequestParam long advertId,HttpServletRequest request, WebRequest webRequest) {
		logger.info("activedBsAdvert "+advertId);
		ModelMap result = new ModelMap();
		IBsAdvert bsAdvert = bsAdvertService.getById(advertId);
		if(null==bsAdvert){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "广告不存在，请刷新后操作。");
			return result;
		}
		else if(bsAdvert.getActived()){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "广告已激活。");
			return result;
		}
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
			bsAdvert.setActivePersonId(userSession.getUserId());
			bsAdvertService.actived(bsAdvert);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "广告激活异常。");
			return result;
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
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
		IBsAdvert advert = bsAdvertService.getById(id);
		String workHome = VersionCfg.getBsAdvertDir();
		String contextPath = this.getRealPath(request);
		File targetDir = new File(contextPath + File.separator + id + File.separator + screen);
		if (!targetDir.exists()&&!targetDir.mkdirs()){
			throw new AppException(getVersionI18n("advert.createDir.fail", new Object[]{targetDir.getName()}));
		}
		String zipFilePath = VersionCfg.getBsAdvertDir()+File.separator+advert.getId()+".zip";
		ZipUtils.unZip(zipFilePath, VersionCfg.getBsAdvertDir()+File.separator+advert.getId(), "UTF-8");
		StringBuffer result = new StringBuffer("[");
		for (IBsAdvertResource resource : advert.getAdvertResources()) {
			if (getEnumI18n(resource.getScreen().getText()).equals(screen)) {
				String sourceFilePath = workHome + File.separator + id + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType()) + File.separator + getEnumI18n(resource.getScreen().getText())
						+ File.separator + resource.getContent();
				IOUtils.copyFileToDirectory(sourceFilePath, targetDir.getAbsolutePath());
				String image = "tmp/bsAdvert/" + id + "/" + screen + "/" + resource.getContent();
				result.append("{'picName':'").append(image).append("','playTime':'").append(resource.getPlayTime()).append("'}").append(",");
			}
		}

		if (!result.toString().equals("[")) {
			String r = result.toString().substring(0, result.toString().length() - 1);
			return r + "]";
		}
		return result.append("]").toString();
	}

	private String getVersionI18n(String key,Object[] value){
		return messageSourceVersion.getMessage(key,value, FishCfg.locale);
	}
	/**
	 * bs广告的目录结构 1.zip  --AD_IDLE ----1024 ------zh_${timestamp}.jpg
	 * ------zh_${timestamp}.jpg ------..... ------config.json ----800 ------zh_${timestamp}.jpg ------.....
	 * ------config.json
	 *
	 * @param form
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ModelMap add(@RequestBody BsAdvertForm form, HttpServletRequest request) {
		logger.info(" add advert...");

		ModelMap result = new ModelMap();
		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		IFilter groupFilter = new Filter();
		groupFilter.eq("orgId", userSession.getOrgId());
		List<IAdvertGroup> groupList = advertGroupService.list(groupFilter);
		List<Long> groupIdList = new ArrayList<Long>();
		for(IAdvertGroup group:groupList){
			groupIdList.add(group.getId());
		}
		IFilter filter = new Filter();
		filter.in("groupId", groupIdList);
		filter.eq("advertName", form.getAdvertName());
		List<IBsAdvert> bsAdvertList = bsAdvertService.list(filter);
		if(bsAdvertList.size()>0){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, "当前机构广告名称重复");
			return result;
		}
		IBsAdvert advert = bsAdvertService.make();
		advert.setLastTime(new Date());
		advert.setAdvertName(form.getAdvertName());
		advert.setGroupId(form.getGroupId());
		advert.setAdvertType(AdvertType.valueOf(form.getAdvertType()));
//		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		advert.setPersonId(userSession.getUserId());

		String tempDir = getTempRealDir(request);
		List<ScreenFile> fileNames = new ArrayList<ScreenFile>();
		for (AdvertResourceForm resForm : form.getAdvertResources()) {
			IBsAdvertResource res = bsAdvertResourceService.make();
			res.setPlayTime(resForm.getPlayTime());
			res.setBeginTime(resForm.getBeginTime());
			res.setEndTime(resForm.getEndTime());
			if (resForm.getBeginDate() != null && !"".equals(resForm.getBeginDate())) {
				res.setBeginDate(DateUtils.getDate(resForm.getBeginDate()));
			}
			if (resForm.getEndDate() != null && resForm.getEndDate().length() > 9) {
				res.setEndDate(DateUtils.getDate(resForm.getEndDate()));
			}
			if (AdvertType.isWords(advert.getAdvertType())) {
				res.setContent(resForm.getContent());
			} else {
				String oFileName = resForm.getContent();
				res.setContent(oFileName);
				fileNames.add(new ScreenFile(resForm.getScreen(), File.separator + oFileName));
				res.setScreen(resForm.getScreen());
			}
			res.setBsAdvert(advert);
			advert.addAdvertResource(res);
		}
		bsAdvertService.save(advert);

		// 拷贝临时目录中的文件到advert目录中
		for (ScreenFile screenFile : fileNames) {
			IOUtils.copyFileToDirectory(tempDir + File.separator + getEnumI18n(screenFile.getScreen().getText()) + File.separator + screenFile.getFileName(), VersionCfg.getBsAdvertDir()
					+ File.separator + advert.getId() + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType()) + File.separator + getEnumI18n(screenFile.getScreen().getText()));
		}
		// 删除临时目录
		IOUtils.deleteDir(tempDir);

		// 生成广告版本文件
		saveConfigInfoToFileByScreen(advert, Screen.SCREEN_1024,request);
		saveConfigInfoToFileByScreen(advert, Screen.SCREEN_800,request);
		saveConfigInfoToFileByScreen(advert, Screen.SCREEN_600,request);
		ZipUtils.zip(getAdvertSourcePath(advert), VersionCfg.getBsAdvertDir()+File.separator+advert.getId()+".zip", "UTF-8");
		ZipUtils.delFolder(getAdvertSourcePath(advert));
		bsAdvertService.update(advert);
		// 回填值到form中
		form.setId(advert.getId());
		form.setLastTime(DateUtils.getTimestamp(advert.getLastTime()));
		form.setPersonName(userSession.getUserName());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, form);
		return result;
	}
	
	

	private String getEnumI18n(String enumText) {
		if (null == enumText) {
			return "";
		}
		return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
	}

	private void saveConfigInfoToFileByScreen(IBsAdvert advert, Screen screen,HttpServletRequest request) {
		String configInfo = advert.getAdvertConfigByScreen(screen);
		IOUtils.writeStringToFile(getConfigFileBasePath(advert) + File.separator + getEnumI18n(screen.getText()) + File.separator + "config.json", configInfo);
		IOUtils.copyFileToDirectory(getBsResourcePath(request),getConfigFileBasePath(advert) + File.separator + getEnumI18n(screen.getText()) + File.separator);
	}

	protected String getConfigFileBasePath(IBsAdvert advert) {
		return getAdvertSourcePath(advert) + File.separator + AdvertTypeConversionService.convert(advert.getAdvertType());
	}

	/**
	 * 获取广告资源的原路径
	 * @param advert
	 * @return
	 */
	private String getAdvertSourcePath(IBsAdvert advert) {
		return VersionCfg.getBsAdvertDir() + File.separator + advert.getId();
	}

	private List<BsAdvertForm> convert(List<Object> advertObjList) {
		List<BsAdvertForm> formList = new ArrayList<BsAdvertForm>();
		for (Object advertObj : advertObjList) {
			Object[] advertObjs = (Object[])advertObj;
			IAdvertGroup advertGroup = (IAdvertGroup)advertObjs[0];
			IBsAdvert bsAdvert = (IBsAdvert)advertObjs[1];
			BsAdvertForm form = new BsAdvertForm();
			form.setActived(bsAdvert.getActived());
			form.setActivePersonId(bsAdvert.getActivePersonId());
			IUser activeUser = userService.get(bsAdvert.getActivePersonId());
			form.setActivePersonName(activeUser==null?"":activeUser.getName());
			form.setAdvertName(bsAdvert.getAdvertName());
			form.setGroupId(bsAdvert.getGroupId());
			form.setGroupName(advertGroup.getGroupName());
			form.setId(bsAdvert.getId());
			form.setAdvertFileName(getBsAdvertFile(bsAdvert));
			form.setLastTime(DateUtils.getDate(bsAdvert.getLastTime()));
			form.setPersonId(bsAdvert.getPersonId());
			IUser createUser = userService.get(bsAdvert.getPersonId());
			form.setPersonName(createUser==null?"":createUser.getName());
			formList.add(form);
		}
		return formList;
	}
	
	private String getBsAdvertFile(IBsAdvert bsAdvert) {
        File file = new File(VersionCfg.getBsAdvertDir() + File.separator + bsAdvert.getId() + ".zip");
        return file.exists() ? file.getName() : null;
    }

	@RequestMapping(value = "/uploadRes/screen", method = RequestMethod.POST)
	@ResponseBody
	public String uploadByScreen(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=UTF-8");
		String oFileName = file.getOriginalFilename();
		long fileSize = file.getSize();
		if (fileSize > ADVERTBYTESIZE) {
			return "{'success':false,'errors':'" + messageSourceVersion.getMessage("advert.fileSize", new Object[] { ADVERTBYTESIZE / MILLO }, FishCfg.locale) + "'}";
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
			return "{'success':false,'errors':'" + messageSourceVersion.getMessage("advert.upload.exception", new Object[] { saveFileName, e.getMessage() }, FishCfg.locale) + "'}";
		}
	}

	public boolean containsChinese(String s) {
		String pattern = "[u4e00-u9fa5]+";
		Pattern p = Pattern.compile(pattern);
		Matcher result = p.matcher(s);
		return result.find();
	}

	private String getTempWebDir(HttpServletRequest request, String screen, String saveFileName) {
		return "tmp/bsAdvert/" + this.getSessionDir(request) + "/" + screen + "/" + saveFileName;
	}
	
	private String getBsResourcePath(HttpServletRequest request){
		return FishWebUtils.getContentRealPath(request)+ File.separator +"resources/bsAdvert/advertisement.html";
	}

	private String getTempRealDir(HttpServletRequest request) {
		return this.getRealPath(request) + File.separator + this.getSessionDir(request);
	}

	private String getSessionDir(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		UserSession user = (UserSession) session.getAttribute(FishWebUtils.USER);
		return user.getUserCode() + "_" + sessionId;
	}

	private String getRealPath(HttpServletRequest request) {
		return FishWebUtils.getRealPathByTmp(request) + "/bsAdvert";
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
