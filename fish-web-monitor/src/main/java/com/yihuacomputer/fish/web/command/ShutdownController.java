package com.yihuacomputer.fish.web.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.yihuacomputer.common.FishConstant;
import com.yihuacomputer.common.http.HttpProxy;
import com.yihuacomputer.fish.api.monitor.ICollectService;
import com.yihuacomputer.fish.api.monitor.business.IRunInfo;
import com.yihuacomputer.fish.api.monitor.business.IRunInfoService;
import com.yihuacomputer.fish.api.monitor.business.RunStatus;
import com.yihuacomputer.fish.api.system.config.MonitorCfg;
import com.yihuacomputer.fish.web.command.format.CommandLevel;
import com.yihuacomputer.fish.web.command.format.MonitorResponseCode;
import com.yihuacomputer.fish.web.command.format.ShutdownForm;
import com.yihuacomputer.fish.web.command.format.ShutdownParamForm;

/**
 * 远程关机
 *
 * @author wangchao
 *
 */
@Controller
@RequestMapping("agent")
public class ShutdownController {

    @Autowired
    private ICollectService collectService;

    @Autowired
    private IRunInfoService runInfoService;

    /**
     * 强制关机
     * @param terminalId
     * @param shutdownType
     * @param ip
     * @param request
     * @return
     */
    @RequestMapping(value = "/shutdown", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap shutdown(@RequestParam String terminalId, @RequestParam CommandLevel shutdownType,
            @RequestParam String ip, WebRequest request) {

        IRunInfo runInfo = runInfoService.make();
        runInfo.setTerminalId(terminalId);

        ModelMap result = new ModelMap();

        try {
        	String url = MonitorCfg.getHttpUrl(ip) + "/ctr/shutdown";
            ShutdownParamForm shutdownParamForm = new ShutdownParamForm();
            shutdownParamForm.setShutdownType(shutdownType);
            ShutdownForm shutdownForm = (ShutdownForm) HttpProxy.httpPost(url, shutdownParamForm, ShutdownForm.class, 5000);
            String appRet = shutdownForm.getAppRet();
            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);

            runInfo.setRunStatus(RunStatus.Halt);
            collectService.collectATMCRunInfo(terminalId, runInfo);
        }
        catch (Exception e) {
            result.put(FishConstant.SUCCESS, false);
            result.addAttribute("appRet", MonitorResponseCode.FAILURE);
        }


        return result;
    }

    /**
     * 正常关机
     * @param terminalId
     * @param shutdownType
     * @param ip
     * @param request
     * @return
     */
    @RequestMapping(value = "/normalShutdown", method = RequestMethod.POST)
    public @ResponseBody
    ModelMap normalShutdown(@RequestParam String terminalId, @RequestParam CommandLevel shutdownType,
            @RequestParam String ip, WebRequest request) {

        IRunInfo runInfo = runInfoService.make();
        runInfo.setTerminalId(terminalId);

        ModelMap result = new ModelMap();

        try {
        	String url = MonitorCfg.getHttpUrl(ip) + "/ctr/normalShutdown";
            ShutdownParamForm shutdownParamForm = new ShutdownParamForm();
            shutdownParamForm.setShutdownType(shutdownType);
            ShutdownForm shutdownForm = (ShutdownForm) HttpProxy.httpPost(url, shutdownParamForm, ShutdownForm.class, 5000);
            String appRet = shutdownForm.getAppRet();
            result.put(FishConstant.SUCCESS, true);
            result.addAttribute("appRet", appRet);

            if("00".equalsIgnoreCase(appRet)){//关机成功了在修改服务器端的状态
	            runInfo.setRunStatus(RunStatus.Halt);
	            collectService.collectATMCRunInfo(terminalId, runInfo);
            }
        }
        catch (Exception e) {
            result.put(FishConstant.SUCCESS, false);
            result.addAttribute("appRet", MonitorResponseCode.FAILURE);
        }
        return result;
    }
}
