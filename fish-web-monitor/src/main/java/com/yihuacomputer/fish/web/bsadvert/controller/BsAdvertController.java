package com.yihuacomputer.fish.web.bsadvert.controller;

import java.io.File;
import java.util.ArrayList;
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
import com.yihuacomputer.common.filter.Filter;
import com.yihuacomputer.common.jackson.JsonUtils;
import com.yihuacomputer.common.util.DateUtils;
import com.yihuacomputer.common.util.IOUtils;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvert;
import com.yihuacomputer.fish.api.advert.bs.IBsAdvertService;
import com.yihuacomputer.fish.api.person.UserSession;
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
	private IBsAdvertService bsAdvertService;
	
	@Autowired
	private MessageSource messageSourceVersion;
	

	private final static long ADVERTBYTESIZE=5242880;
	private final static long MILLO=1024*1024l;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody ModelMap getBsAdvert(@RequestParam int limit, @RequestParam int start, HttpServletRequest request, WebRequest webRequest) {
		logger.info("search bsAdvert group ");
		ModelMap result = new ModelMap();
		IFilter filter = new Filter();
		IPageResult<IBsAdvert> pageResult = bsAdvertService.page(start, limit, filter);
		result.addAttribute(FishConstant.TOTAL, pageResult.getTotal());
		result.addAttribute(FishConstant.DATA, convert(pageResult.list()));
		return result;
	}
	
	private List<BsAdvertForm> convert(List<IBsAdvert> advertList){
		List<BsAdvertForm> formList = new ArrayList<BsAdvertForm>();
		for(IBsAdvert bsAdvert:advertList){
			BsAdvertForm form = new BsAdvertForm();
			form.setActived(bsAdvert.getActived());
			form.setActivePersonId(bsAdvert.getActivePersonId());
//			form.setActivePersonName(activePersonName);
			form.setAdvertName(bsAdvert.getAdvertName());
			form.setGroupId(bsAdvert.getGroupId());
//			form.setGroupName(bsAdvert.get);
			form.setId(bsAdvert.getId());
			form.setLastTime(DateUtils.getDate(bsAdvert.getLastTime()));
			form.setPersonId(bsAdvert.getPersonId());
//			form.setPersonName();
			formList.add(form);
		}
		return formList;
	}
	
	@RequestMapping(value = "/uploadRes/screen", method = RequestMethod.POST)
	@ResponseBody
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
	
	private String getTempWebDir(HttpServletRequest request, String screen, String saveFileName) {
		return "tmp/advert/" + this.getSessionDir(request) + "/" + screen + "/" + saveFileName;
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
		return FishWebUtils.getRealPathByTmp(request) + "/advert";
	}

}
