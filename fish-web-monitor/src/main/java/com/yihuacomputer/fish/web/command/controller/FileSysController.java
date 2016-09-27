package com.yihuacomputer.fish.web.command.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yihuacomputer.common.FishCfg;
import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.FileSysOperaterForm;
import com.yihuacomputer.fish.web.command.format.FileSysOperatorType;



/**
 * 文件系统操作
 * @author GQ
 */
@Controller
@RequestMapping("/agent/fileSysOperator")
public class FileSysController {


	private Logger logger = LoggerFactory.getLogger(FileSysController.class);

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value="/mkFileSys",method = RequestMethod.POST)
    public @ResponseBody ModelMap mkFileSys(@RequestParam String path, @RequestParam String file,@RequestParam String ip,@RequestParam String isDirectory)
    {
		logger.info(String.format("mkFileSys the path is 【%s】; the file is 【%s】; the ip is 【%s】",path,file,ip));
        ModelMap result = new ModelMap();
        FileSysOperaterForm fsof = new FileSysOperaterForm();
        fsof.setFilePath(path);
        fsof.setFileName(file);
        if(!isDirectory.isEmpty()&&isDirectory.equals("true")){
        	fsof.setFileSysOperatorType(FileSysOperatorType.MKDIR);
        }
        else{
            fsof.setFileSysOperatorType(FileSysOperatorType.MKFILE);
        }
		String url = MonitorCfg.getHttpUrl(ip)+"/ctr/remoteMkFileSys";
		FileSysOperaterForm explorerForm = (FileSysOperaterForm) HttpProxy.httpPost(url,fsof,FileSysOperaterForm.class, 5000);
        if(explorerForm.isSuccess()){
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute(FishConstant.DATA, messageSource.getMessage("filesys.operator.createSuccess", null, FishCfg.locale));
        }else{
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG,  messageSource.getMessage("filesys.operator.createFail", null, FishCfg.locale));
        }
        return result;
    }


	@RequestMapping(value="/deleteFile",method = RequestMethod.POST)
    public @ResponseBody ModelMap deleteFile(@RequestParam String path, @RequestParam String file,@RequestParam String ip)
    {
		logger.info(String.format("deleteFile the path is 【%s】; the file is 【%s】; the ip is 【%s】",path,file,ip));
        ModelMap result = new ModelMap();
        FileSysOperaterForm fsof = new FileSysOperaterForm();
        fsof.setFilePath(path);
        fsof.setFileName(file);
        fsof.setFileSysOperatorType(FileSysOperatorType.DELFILE);
		String url = MonitorCfg.getHttpUrl(ip)+"/ctr/remoteMkFileSys";
		FileSysOperaterForm explorerForm = (FileSysOperaterForm) HttpProxy.httpPost(url,fsof,FileSysOperaterForm.class, 5000);
        if(explorerForm.isSuccess()){
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute(FishConstant.DATA, messageSource.getMessage("filesys.operator.deleteSuccess", null, FishCfg.locale));
        }else{
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG,messageSource.getMessage("filesys.operator.deleteFail", null, FishCfg.locale));
        }
        return result;
    }


	@RequestMapping(value="/execFile",method = RequestMethod.POST)
    public @ResponseBody ModelMap execFile(@RequestParam String path, @RequestParam String file,@RequestParam String ip)
    {
		logger.info(String.format("execFile the path is 【%s】; the file is 【%s】; the ip is 【%s】",path,file,ip));
        ModelMap result = new ModelMap();
        FileSysOperaterForm fsof = new FileSysOperaterForm();
        fsof.setFilePath(path);
        fsof.setFileName(file);
        fsof.setFileSysOperatorType(FileSysOperatorType.FILEEXEC);
		String url = MonitorCfg.getHttpUrl(ip)+"/ctr/remoteMkFileSys";
		FileSysOperaterForm explorerForm = (FileSysOperaterForm) HttpProxy.httpPost(url,fsof,FileSysOperaterForm.class, 5000);
        if(explorerForm.isSuccess()){
            result.addAttribute(FishConstant.SUCCESS, true);
            result.addAttribute(FishConstant.DATA, messageSource.getMessage("filesys.operator.success", null, FishCfg.locale));
        }else{
            result.addAttribute(FishConstant.SUCCESS, false);
            result.addAttribute(FishConstant.ERROR_MSG, explorerForm.getFailReason());
        }
        return result;
    }
}
