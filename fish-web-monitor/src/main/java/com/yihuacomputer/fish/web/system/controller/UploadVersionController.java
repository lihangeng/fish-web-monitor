package com.yihuacomputer.fish.web.system.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yihuacomputer.common.FishConstant;

@Controller
@RequestMapping("/agent")
public class UploadVersionController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(UploadVersionController.class);
    
	@RequestMapping(value = "/uploadVersion",method = RequestMethod.POST)
	public @ResponseBody ModelMap upload(@RequestParam(value = "file",required=false)MultipartFile file, HttpServletRequest request, ModelMap model) {

		ModelMap map = new ModelMap();

		String fileName = file.getOriginalFilename();

		File targetFile = new File("D:/", fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
			map.put(FishConstant.SUCCESS, true);
		} catch (Exception e) {
			map.put(FishConstant.SUCCESS, false);
			logger.info(String.format("[%s]", e));
		}
//		model.addAttribute("fileUrl", request.getContextPath() + "/upload/" + fileName);

		return map;
	}

}
