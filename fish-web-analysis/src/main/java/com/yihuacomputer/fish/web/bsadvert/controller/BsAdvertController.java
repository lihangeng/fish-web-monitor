package com.yihuacomputer.fish.web.bsadvert.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






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
import com.yihuacomputer.common.exception.AppException;
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.common.util.ZipUtils;
import com.yihuacomputer.fish.api.advert.AdvertType;
import com.yihuacomputer.fish.api.advert.Screen;
import com.yihuacomputer.fish.api.advert.bs.BsAdvertStatus;
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
import com.yihuacomputer.fish.web.advert.form.UploadResourceForm;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertForm;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertResourceForm;
import com.yihuacomputer.fish.web.bsadvert.form.BsAdvertScreenForm;
import com.yihuacomputer.fish.web.bsadvert.form.BsUploadResourceForm;
import com.yihuacomputer.fish.web.util.FishWebUtils;

@Controller
@RequestMapping("bsadvert/advert")
public class BsAdvertController {

	private Logger logger = LoggerFactory.getLogger(BsAdvertController.class);

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

	/**
	 * 查找广告列表
	 * @param limit
	 * @param start
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap seachBsAdvertList(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search bsAdvert group ");
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		String groupId = request.getParameter("groupId");
		String advertName = request.getParameter("advertName");
		String bsAdvertStatus = request.getParameter("bsAdvertStatus");
		String updateTimeEnd = request.getParameter("updateTimeEnd");
		String updateTimeStart = request.getParameter("updateTimeStart");
		if(null!=groupId&&!"".equals(groupId.trim())){
			filter.eq("groupId", Long.parseLong(groupId));
		}
		if(null!=advertName&&!"".equals(advertName.trim())){
			filter.eq("advertName", advertName);
		}
		if(null!=bsAdvertStatus&&!"".equals(bsAdvertStatus.trim())){
			filter.eq("bsAdvertStatus", BsAdvertStatus.getBsAdvertStatusById(Integer.parseInt(bsAdvertStatus)));
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
	@RequestMapping(value = "/{id}",method=RequestMethod.DELETE)
	public @ResponseBody ModelMap deleteBsAdvert(@PathVariable long id,HttpServletRequest request, WebRequest webRequest) {
		logger.info("deleteBsAdvert "+id);
		ModelMap result = new ModelMap();
		IBsAdvert bsAdvert = bsAdvertService.getById(id);
		if(null==bsAdvert){
			result.addAttribute(FishConstant.SUCCESS, true);
			return result;
		}
		//bsadvert.delete.fail=激活状态的广告无法删除
		if(bsAdvert.getBsAdvertStatus()!=BsAdvertStatus.UNACTIVE){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.delete.fail",null));
			return result;
		}
		else if(bsAdvert.getBsAdvertStatus()==BsAdvertStatus.UNACTIVE){
			bsAdvertService.delete(bsAdvert);
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}
	
	private String getI18NResource(String code,String args[]){
		return messageSourceVersion.getMessage(code, args, FishCfg.locale);
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
		//bsadvert.notExist=广告不存在，请刷新后操作
		if(null==bsAdvert){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.notExist",null));
			return result;
		}
		//bsadvert.active.actived=广告已激活。
		else if(bsAdvert.getBsAdvertStatus().equals(BsAdvertStatus.ACTIVED)){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.active.actived",null));
			return result;
		}
		try {
			UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
			bsAdvert.setActiveUserId(userSession.getUserId());
			bsAdvertService.actived(bsAdvert);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result.addAttribute(FishConstant.SUCCESS, false);
			//bsadvert.active.exception"广告激活异常。"
			result.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.active.exception",null));
			return result;
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		return result;
	}

	
	/**
	 * 加载广告进行更改
	 * @param advertId
	 * @param request
	 * @param webRequest
	 * @return
	 */
	@RequestMapping(value="/loadAdvertToUpdate",method = RequestMethod.POST)
	public @ResponseBody ModelMap loadAdvertToUpdate(@RequestParam long advertId,HttpServletRequest request, WebRequest webRequest) {
		logger.info("activedBsAdvert "+advertId);
		ModelMap result = new ModelMap();
		IBsAdvert bsAdvert = bsAdvertService.getById(advertId);
		ScreenResources screenResources = null;
		//bsadvert.notExist=广告不存在，请刷新后操作
		if(null==bsAdvert){
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.notExist",null));
			return result;
		}
		try {
			List<IBsAdvertResource> resourceList = bsAdvert.getAdvertResources();
			@SuppressWarnings("deprecation")
			String tempDir = stringConcat(request.getRealPath("/"),File.separator,"tmp",File.separator,"bsAdvert",File.separator,this.getSessionDir(request),File.separator);
			String zipFilePath = stringConcat(VersionCfg.getBsAdvertDir(),File.separator,bsAdvert.getId(),".zip");
			String unzipTarget = stringConcat(tempDir,File.separator,bsAdvert.getId());
			
			ZipUtils.unZip(zipFilePath, unzipTarget, "UTF-8");
			String willCopyFileName = stringConcat(tempDir,File.separator,bsAdvert.getId(),File.separator,"AD_IDLE");
			for(IBsAdvertResource resource:resourceList){
				Screen screen = resource.getScreen();
				String fileName = resource.getContent();
				String screenName = getEnumI18n(screen.getText());
				String advertResourceFile = willCopyFileName.concat(File.separator).concat(screenName).concat(File.separator).concat(fileName);
				IOUtils.copyFileToDirectory(advertResourceFile, tempDir.concat(File.separator).concat(screenName));
			}
			screenResources = getResoucesJson(willCopyFileName,request);
			new File(willCopyFileName).delete();
		} catch (Exception e) {
			logger.error(e.getMessage());
//			bsadvert.load.exception=加载广告资源异常。
			result.addAttribute(FishConstant.SUCCESS, false);
			result.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.load.exception", null));
			return result;
		}
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, screenResources);
		return result;
	}
	
	private ScreenResources getResoucesJson(String willCopyFileName,HttpServletRequest request){
		
		String screen1024 = getEnumI18n(Screen.SCREEN_1024.getText());
		String screen800 = getEnumI18n(Screen.SCREEN_800.getText());
		String screen600 = getEnumI18n(Screen.SCREEN_600.getText());
		BsAdvertScreenForm screen1024Form = getAdvertResourceInfos(stringConcat(willCopyFileName,File.separator,screen1024,File.separator,"config.json"));
		BsAdvertScreenForm screen800Form = getAdvertResourceInfos(stringConcat(willCopyFileName,File.separator,screen800,File.separator+"config.json"));
		BsAdvertScreenForm screen600Form = getAdvertResourceInfos(stringConcat(willCopyFileName,File.separator,screen600,File.separator+"config.json"));
		ScreenResources screenResource = new ScreenResources();
		screenResource.setScreen1024(getResource(screen1024Form.getResources(),request,screen1024));
		screenResource.setScreen800(getResource(screen800Form.getResources(),request,screen800));
		screenResource.setScreen600(getResource(screen600Form.getResources(),request,screen600));
		return screenResource;
	}
	
	private List<UploadResourceForm> getResource(List<BsUploadResourceForm> resourceFormList,HttpServletRequest request,String screen){
		List<UploadResourceForm> list = new ArrayList<UploadResourceForm>();
			for(int index=0;index<resourceFormList.size();index++){
				BsUploadResourceForm resourceForm = resourceFormList.get(index);
				
				String fileName = resourceForm.getContent();
				UploadResourceForm form = new UploadResourceForm(fileName, fileName, getTempWebDir(request, screen, fileName), screen);
				form.setBeginDate(resourceForm.getBeginDate());
				String beginTime = resourceForm.getBeginTime();
				try{
					if(null!=beginTime&&!"".equals(beginTime)){
						String beginTimes[] = beginTime.split("\\:");
						
						form.setBeginHour(beginTimes[0]);
						form.setBeginMinute(beginTimes[1]);
						form.setBeginSecond(beginTimes[2]);
					}
				}catch(Exception e){
					logger.error(e.getMessage());
				}
				form.setDisplayName(resourceForm.getContent());
				form.setEndDate(resourceForm.getEndDate());
				String endTime = resourceForm.getEndTime();
				try{
					if(null!=endTime&&!"".equals(endTime)){
						String endTimes[] = endTime.split("\\:");
						form.setEndHour(endTimes[0]);
						form.setEndMinute(endTimes[1]);
						form.setEndSecond(endTimes[2]);
					}
				}catch(Exception e){
					logger.error(e.getMessage());
				}
				form.setFileName(fileName);
				form.setId(String.valueOf(resourceForm.getId()));
				form.setOriginalFileName(fileName);
				form.setPlayTime(resourceForm.getPlayTime());
				list.add(form);
			}
		return list;
	}
	
	private BsAdvertScreenForm getAdvertResourceInfos(String willCopyFileName){
		BsAdvertScreenForm screenAdvert = null;
		FileReader reader = null;
		BufferedReader br = null;
		try {
			reader = new FileReader(willCopyFileName);
            br = new BufferedReader(reader);
			String str = br.readLine();
			if(str!=null){
				str = str.replaceAll("resourceId", "id");
				screenAdvert = JsonUtils.fromJson(str, BsAdvertScreenForm.class);
			}
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally{
			if(null!=br){
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
			}
		}
		return screenAdvert;
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
				String sourceFilePath = stringConcat(workHome , File.separator , id , File.separator , AdvertTypeConversionService.convert(advert.getAdvertType()) , File.separator , getEnumI18n(resource.getScreen().getText())
						, File.separator , resource.getContent());
				IOUtils.copyFileToDirectory(sourceFilePath, targetDir.getAbsolutePath());
				String image = stringConcat("tmp/bsAdvert/" , id , "/" , screen , "/" + resource.getContent());
				result.append("{'picName':'").append(image).append("','playTime':'").append(resource.getPlayTime()).append("'}").append(",");
			}
		}

		if (!result.toString().equals("[")) {
			String r = result.toString().substring(0, result.toString().length() - 1);
			return r + "]";
		}
		return result.append("]").toString();
	}

	private String stringConcat(Object... args){
		StringBuffer sb = new StringBuffer();
		for(Object arg:args){
			sb.append(arg);
		}
		return sb.toString();
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
		if(exsitSameAdvert(userSession.getOrgId(),form.getAdvertName(),0)){
			result.addAttribute(FishConstant.SUCCESS, false);
//			bsadvert.org.sameName=当前机构存在相同的广告名称
			result.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.org.sameName", null));
			return result;
		}
		IBsAdvert advert = bsAdvertService.make();
		advert.setLastTime(new Date());
		advert.setAdvertName(form.getAdvertName());
		advert.setGroupId(form.getGroupId());
		advert.setBsAdvertStatus(BsAdvertStatus.UNACTIVE);
		advert.setAdvertType(AdvertType.valueOf(form.getAdvertType()));
		advert.setUserId(userSession.getUserId());

		String tempDir = getTempRealDir(request);
		List<ScreenFile> fileNames = new ArrayList<ScreenFile>();
		for (BsAdvertResourceForm resForm : form.getAdvertResources()) {
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
		zipAdvertFile(advert,request,fileNames,tempDir);
		IFilter filter = new Filter();
		filter.eq("groupId", advert.getGroupId());
		List<IBsAdvert> groupBsAdvertList = bsAdvertService.list(filter);
		//如果为当前组的第一个广告则直接激活；第一条及以后的广告不做自动激活处理
		if(groupBsAdvertList.size()==1){
			advert.setActiveUserId(userSession.getUserId());
			bsAdvertService.actived(advert);
		}else{
			bsAdvertService.update(advert);
		}
		// 回填值到form中
		form.setId(advert.getId());
		form.setLastTime(DateUtils.getTimestamp(advert.getLastTime()));
		form.setUserName(userSession.getUserName());
		result.addAttribute(FishConstant.SUCCESS, true);
		result.addAttribute(FishConstant.DATA, form);
		return result;
	}
	
	private boolean exsitSameAdvert(long id,String advertName,long advertId){
		List<IBsAdvert> bsAdvertList = bsAdvertService.getBsAdvertByNameAndOrgId(id,advertName,advertId);
		return bsAdvertList.size()>0;
	}
	/**
	 * 确认更改BsAdvert
	 * @param id
	 * @param form
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	ModelMap updateBsAdvert(@PathVariable long id, @RequestBody BsAdvertForm form, HttpServletRequest request) {
		logger.info("update bsAdvert: bsAdvert.id = " + id);
		ModelMap model = new ModelMap();
		UserSession session =(UserSession)request.getSession().getAttribute(FishWebUtils.USER);
		UserSession userSession = (UserSession) request.getSession().getAttribute(FishWebUtils.USER);
		IBsAdvert advert = bsAdvertService.getById(id);
		if(exsitSameAdvert(userSession.getOrgId(),form.getAdvertName(),id)){
			model.addAttribute(FishConstant.SUCCESS, false);
			model.addAttribute(FishConstant.ERROR_MSG, getI18NResource("bsadvert.org.sameName", null));
			return model;
		}
		advert.setAdvertName(form.getAdvertName());
		advert.setUserId(session.getUserId());
		advert.setLastTime(new Date());
		advert.setAdvertType(AdvertType.valueOf(form.getAdvertType()));
		advert.insertBsAdvertService(bsAdvertService);
		advert.getAdvertResources().clear();
		String tempDir = getTempRealDir(request);
		List<ScreenFile> fileNames = new ArrayList<ScreenFile>();
		for (BsAdvertResourceForm resForm : form.getAdvertResources()) {
			IBsAdvertResource res = bsAdvertResourceService.getById(resForm.getId());
			if(null==res){
				res= bsAdvertResourceService.make();
			}
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
		zipAdvertFile(advert,request,fileNames,tempDir);
		bsAdvertService.update(advert);
		// 回填值到form中
		
		form.setActiveUserId(advert.getActiveUserId());
		IUser activeUser = userService.get(advert.getActiveUserId());
		form.setActiveUserName(activeUser==null?"":activeUser.getName());
		form.setAdvertName(advert.getAdvertName());
		form.setGroupId(advert.getGroupId());
		IAdvertGroup advertGroup= advertGroupService.getById(advert.getGroupId());
		if(advertGroup!=null){
			form.setGroupName(advertGroup.getGroupName());
		}
		form.setBsAdvertStatus(getEnumI18n(advert.getBsAdvertStatus().getName()));
		form.setId(advert.getId());
		form.setAdvertFileName(getBsAdvertFile(advert));
		form.setLastTime(DateUtils.getDate(advert.getLastTime()));
		form.setUserId(advert.getUserId());
		IUser createUser = userService.get(advert.getUserId());
		form.setUserName(createUser==null?"":createUser.getName());
		//如果当前广告更改之前是激活状态的，直接进行激活操作
		if(advert.getBsAdvertStatus().equals(BsAdvertStatus.ACTIVED)){
			bsAdvertService.actived(advert);
		}
		model.addAttribute(FishConstant.SUCCESS, true);
		model.addAttribute(FishConstant.DATA,form);
		return model;
	}
	
	private void zipAdvertFile(IBsAdvert advert,HttpServletRequest request,List<ScreenFile> fileNames,String tempDir){
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
	}
	
	@RequestMapping(value = "/deleteResource")
	public @ResponseBody
	ModelMap deleteResource(@RequestParam long id, HttpServletRequest request) {
		logger.info("delete bsAdvertResource: bsAdvertResource.id = " + id);
		ModelMap result = new ModelMap();
		IBsAdvertResource bsAdvertResource = bsAdvertResourceService.getById(id);
		if(null!=bsAdvertResource){
			File file = new File(getTempRealDir(request) + File.separator + getEnumI18n(bsAdvertResource.getScreen().getText()) + File.separator + bsAdvertResource.getContent());
			file.delete();
			bsAdvertResourceService.delete(bsAdvertResource);
		}

		result.addAttribute(FishConstant.SUCCESS, true);
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
			form.setBsAdvertStatus(getEnumI18n(bsAdvert.getBsAdvertStatus().getName()));
			form.setActiveUserId(bsAdvert.getActiveUserId());
			IUser activeUser = userService.get(bsAdvert.getActiveUserId());
			form.setActiveUserName(activeUser==null?"":activeUser.getName());
			form.setAdvertName(bsAdvert.getAdvertName());
			form.setGroupId(bsAdvert.getGroupId());
			form.setGroupName(advertGroup.getGroupName());
			form.setId(bsAdvert.getId());
			form.setAdvertFileName(getBsAdvertFile(bsAdvert));
			form.setLastTime(DateUtils.getDate(bsAdvert.getLastTime()));
			form.setUserId(bsAdvert.getUserId());
			IUser createUser = userService.get(bsAdvert.getUserId());
			form.setUserName(createUser==null?"":createUser.getName());
			formList.add(form);
		}
		return formList;
	}
	
	private String getBsAdvertFile(IBsAdvert bsAdvert) {
        File file = new File(VersionCfg.getBsAdvertDir() + File.separator + bsAdvert.getId() + ".zip");
        return file.exists() ? file.getName() : null;
    }

	/**
	 * 上传资源
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
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

	/**
	 * 是否有中文
	 * @param s
	 * @return
	 */
	public boolean containsChinese(String s) {
		String pattern = "[u4e00-u9fa5]+";
		Pattern p = Pattern.compile(pattern);
		Matcher result = p.matcher(s);
		return result.find();
	}

	/**
	 * 获取web临时目录
	 * @param request
	 * @param screen
	 * @param saveFileName
	 * @return
	 */
	private String getTempWebDir(HttpServletRequest request, String screen, String saveFileName) {
		return "tmp/bsAdvert/" + this.getSessionDir(request) + "/" + screen + "/" + saveFileName;
	}
	
	/**
	 * @param request
	 * @return
	 */
	private String getTempRealDir(HttpServletRequest request) {
		return this.getRealPath(request) + File.separator + this.getSessionDir(request);
	}

	/**
	 * 获取session目录名称
	 * @param request
	 * @return
	 */
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
	
	/**
	 * 加载更改页面的广告资源进行使用
	 * @author GQ
	 *
	 */
	class ScreenResources {
		private List<UploadResourceForm> screen1024;
		private List<UploadResourceForm> screen800;
		private List<UploadResourceForm> screen600;
		public List<UploadResourceForm> getScreen1024() {
			return screen1024;
		}
		public void setScreen1024(List<UploadResourceForm> screen1024) {
			this.screen1024 = screen1024;
		}
		public List<UploadResourceForm> getScreen800() {
			return screen800;
		}
		public void setScreen800(List<UploadResourceForm> screen800) {
			this.screen800 = screen800;
		}
		public List<UploadResourceForm> getScreen600() {
			return screen600;
		}
		public void setScreen600(List<UploadResourceForm> screen600) {
			this.screen600 = screen600;
		}
	}

}
