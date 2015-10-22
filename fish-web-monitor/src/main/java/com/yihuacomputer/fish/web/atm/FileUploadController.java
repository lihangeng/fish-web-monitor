package com.yihuacomputer.fish.web.atm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.http.HttpFileCfg;
import com.yihuacomputer.common.http.HttpFileClient;
import com.yihuacomputer.common.http.HttpFileRet;

/**
 * 提供文件上传请求接口
 * @author YiHua 
 * */

@Controller
@RequestMapping("/msg/fileupload")
public class FileUploadController {
	
		
	/**
	 * 接收文件上传请求报告
	 * @param msg
	 * @return
	 */
	private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody ModelMap uploadFile(@RequestBody HttpFileCfg fileCfg){		
		ModelMap result = new ModelMap();
		HttpFileRet ret = HttpFileClient.downloadFile(fileCfg);	       
        logger.info(String.format("file upload result is [%s]", getEnumI18n(ret.getText())));
		result.addAttribute("ret", ret);        
		return result;
	}

	@Autowired
	private MessageSource messageSourceEnum;
    private String getEnumI18n(String enumText){
    	if(null==enumText){
    		return "";
    	}
    	return messageSourceEnum.getMessage(enumText, null, FishCfg.locale);
    }
}
